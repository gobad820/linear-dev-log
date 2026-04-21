# JSP와 Catalina Base

## 1. Catalina Base란?

Tomcat은 내부적으로 **Catalina**라는 Servlet 컨테이너를 사용한다. Tomcat 디렉토리 구조는 두 가지 환경 변수로 구분된다.

| 변수 | 역할 | 내용 |
|------|------|------|
| `CATALINA_HOME` | Tomcat 설치 경로 | 바이너리, 공유 라이브러리 (`lib/`, `bin/`) |
| `CATALINA_BASE` | Tomcat 인스턴스 작업 경로 | 설정, 로그, 배포본, **JSP 컴파일 결과** |

> Eclipse/STS에서 "Servers" 뷰로 서버를 생성하면 workspace 안에 별도의 `CATALINA_BASE`가 만들어진다.
> 예: `workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/`

---

## 2. CATALINA_BASE 디렉토리 구조

```
CATALINA_BASE/
├── conf/          ← server.xml, web.xml (전역 설정)
├── logs/          ← catalina.out, access 로그
├── webapps/       ← 배포된 WAR / 프로젝트 폴더
└── work/          ← JSP → Java → class 컴파일 결과물 ★
    └── Catalina/
        └── localhost/
            └── <context-path>/
                └── org/apache/jsp/
                    └── index_jsp.java
                    └── index_jsp.class
```

### JSP 컴파일 흐름

```
index.jsp
  ↓  (최초 요청 or 변경 감지 시)
index_jsp.java      ← Tomcat이 자동 생성 (CATALINA_BASE/work/)
  ↓  (javac)
index_jsp.class     ← 이후 요청은 이 클래스를 재사용
```

> **work/** 디렉토리를 삭제하면 모든 JSP가 다음 요청 시 재컴파일된다.
> Eclipse에서 "Clean..." 후 서버를 재시작하면 내부적으로 이 작업이 수행된다.

---

## 3. JSP 기본 문법

### 3-1. page 디렉티브

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
```

| 속성 | 설명 |
|------|------|
| `language` | 스크립팅 언어 (현재 java만 지원) |
| `contentType` | 응답 MIME 타입 + 인코딩 |
| `pageEncoding` | JSP 파일 자체의 인코딩 |
| `import` | Java 클래스 import (예: `import="java.util.List"`) |

### 3-2. 스크립팅 요소

| 요소 | 문법 | 변환 위치 |
|------|------|-----------|
| 선언문 | `<%! ... %>` | 클래스 멤버 영역 |
| 스크립틀릿 | `<% ... %>` | `_jspService()` 메서드 내부 |
| 표현식 | `<%= ... %>` | `out.print(...)` 로 변환 |

BE_03의 `gugu.jsp` 예시:
```jsp
<%
int dan = Integer.parseInt(request.getParameter("dan"));
for (int i = 1; i < 10; i++) {
%>
<td><%= dan * i %></td>
<%
}
%>
```

---

## 4. JSP 내장 객체 (Implicit Objects)

JSP가 Servlet으로 변환될 때 `_jspService()` 메서드 안에 자동 선언되는 변수들이다.

| 내장 객체 | 타입 | 스코프 |
|-----------|------|--------|
| `request` | `HttpServletRequest` | request |
| `response` | `HttpServletResponse` | page |
| `session` | `HttpSession` | session |
| `application` | `ServletContext` | application |
| `pageContext` | `PageContext` | page |
| `out` | `JspWriter` | page |
| `config` | `ServletConfig` | page |
| `page` | `Object` (this) | page |

BE_03의 `scope.jsp`에서 스코프 비교:
```jsp
<%
pageContext.setAttribute("pageattr", "pageattr");   // 현재 페이지에서만 유효
request.setAttribute("reqattr", "reqattr");         // 하나의 요청 주기 동안 유효
session.setAttribute("sessattr", "sessattr");       // 브라우저 세션 동안 유효
application.setAttribute("appattr", "appattr");    // 서버 전체 (Context) 동안 유효
%>
```

### 스코프 생존 범위 비교

```
page < request < session < application
 │         │        │           │
현재 JSP  forward  브라우저   서버 재시작
까지만    포함 전체  닫힐 때까지  전까지
```

---

## 5. Context Path와 getContextPath()

JSP/Servlet에서 링크나 form action을 작성할 때 **Context Root 경로를 하드코딩하면 배포 위치가 바뀔 때 깨진다.**

BE_03 전체에서 아래 패턴을 일관되게 사용:

```jsp
<%
String root = request.getContextPath(); // 예: "/BE_03" 또는 ""
%>
<form action="<%= root %>/member" method="post">
<a href="<%= root %>/main?action=gugu-form">구구단</a>
```

> `getContextPath()`는 Catalina가 해당 앱을 어떤 경로에 배포했는지를 반환한다.
> Eclipse에서 Context Path는 `server.xml`의 `<Context path="...">` 값과 일치한다.

---

## 6. MVC 패턴과 JSP의 역할 (BE_03 구조)

BE_03은 **Front Controller 패턴**을 채택한다. JSP는 View만 담당하고 비즈니스 로직은 Servlet에서 처리한다.

```
브라우저
  │  GET /main?action=gugu-form
  ▼
MainController (Servlet)          ← Controller
  │  forward("/gugu/gugu-form.jsp")
  ▼
gugu-form.jsp                     ← View (입력 화면)
  │  POST /main?action=gugu
  ▼
MainController.gugu()             ← Controller
  │  SimpleService.getGugu(dan)   ← Model (Service)
  │  request.setAttribute("guguList", list)
  │  forward("/gugu/gugu-result.jsp")
  ▼
gugu-result.jsp                   ← View (결과 화면)
```

### forward vs redirect

| 구분 | forward | redirect |
|------|---------|----------|
| 처리 주체 | 서버 내부에서 이동 | 브라우저에게 재요청 지시 |
| URL 변화 | 없음 | 변경됨 |
| request 스코프 | 유지 | 초기화 |
| 사용 시점 | 결과 뷰로 이동 | 중복 제출 방지, 외부 이동 |

`ControllerHelper` 인터페이스의 설계 의도:
```java
// http로 시작하면 절대 URL → response.sendRedirect(path)
// 그 외 → request.getRequestDispatcher(contextPath + path).forward(...)
public default void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {}
public default void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {}
```

---

## 7. Filter와 Listener (BE_03)

### LoggingFilter

```java
@WebFilter("/*")
public class LoggingFilter extends HttpFilter {
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.printf("요청 경로: %s, 방식: %s\n", request.getRequestURI(), request.getMethod());
        request.getParameterMap().forEach((k, v) -> System.out.printf("name: %s, value: %s\n", k, Arrays.toString(v)));
        chain.doFilter(request, response); // 반드시 호출해야 다음 필터/서블릿으로 이동
    }
}
```

### XSSFilter — HttpServletRequestWrapper 활용

XSS 공격 방어를 위해 `<`, `>`를 HTML 엔티티로 변환. `HttpServletRequestWrapper`로 `getParameter()`를 오버라이드해 모든 파라미터를 자동 소독.

```java
private String cleanXss(String value) {
    return value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
}
```

### MyContextListener — 앱 생명주기

```java
@WebListener
public class MyContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        FileMemberDao.getInstance().load(); // 서버 시작 시 데이터 로딩
    }
    public void contextDestroyed(ServletContextEvent sce) {
        FileMemberDao.getInstance().save(); // 서버 종료 시 데이터 저장
    }
}
```

> Catalina가 Context(웹 앱)를 초기화·소멸할 때 호출된다. `CATALINA_BASE/work/` 삭제 후 재시작 시 `contextInitialized`가 다시 실행된다.

---

## 8. JSP 컴파일 오류 디버깅

| 증상 | 원인 | 해결 |
|------|------|------|
| 500 에러, `_jsp.java` 오류 | JSP 스크립틀릿 문법 오류 | `CATALINA_BASE/work/`의 `.java` 파일에서 줄 번호 확인 |
| 변경한 JSP가 반영 안 됨 | 이전 `.class` 캐시 | `work/` 삭제 또는 Eclipse "Clean" |
| `getContextPath()` 빈 문자열 | 루트 컨텍스트(/)로 배포됨 | `server.xml`에서 Context path 확인 |
| 한글 깨짐 | `pageEncoding` 누락 또는 파일 저장 인코딩 불일치 | `<%@ page pageEncoding="UTF-8" %>` + 파일 UTF-8 저장 |

---

## 관련 개념

- [[Servlet기초]]
- [[HttpServletRequest]]
- [[Filter와Listener]]
- [[MVC패턴]]
