# JSP (Jakarta Server Pages)

- 기존 Servlet의 문제점: Java 코드 안에 HTML을 작성해야 하므로 자동완성이 지원되지 않고, 디자인 변경 시 자바 코드를 재컴파일해야 하는 불편함이 있음.
- **JSP (Jakarta Server Pages)**: HTML 태그 기반에 Java 코드를 작성하여 동적 웹 페이지를 생성하는 기술. Servlet이 Java 중심이라면, JSP는 HTML 중심의 템플릿 엔진 역할을 수행함.

## 동작 원리
- **Servlet과 관계**: JSP는 클라이언트의 요청 시 서블릿(Java 파일)으로 변환된 후 컴파일되어 실행됨. 즉, JSP는 서블릿을 만들기 위한 중간 단계이며 최종 실행 형태는 서블릿임.

```mermaid
graph LR
    A[JSP File] $\rightarrow$ B["Java File (Servlet)"]
    B $\rightarrow$ C[Class File]
    C $\rightarrow$ D[Servlet Instance]
    D $\rightarrow$ E[Response]
```

## 작성
### Page directive
- **Directive (JSP 지시자)**: JSP 페이지 전체에 영향을 주는 설정 정보를 컨테이너에 전달함.
- **역할 및 개념**: JSP 페이지가 서블릿으로 변환될 때 필요한 정보(인코딩, 임포트, 에러 페이지 등)를 정의함.

### JSP LifeCycle
JSP는 서블릿 컨테이너에 의해 관리되며 다음과 같은 생명주기를 가짐.

```mermaid
graph TD
    A[Request] $\rightarrow$ B{Servlet Exist?}
    B -- No --$\rightarrow$ C[Translation & Compilation]
    C $\rightarrow$ D[jspInit]
    B -- Yes --$\rightarrow$ E[_jspService]
    D $\rightarrow$ E
    E $\rightarrow$ F[Response]
    F $\rightarrow$ G[jspDestroy]
```

- `contentType`: `<%@ page contentType="text/html;charset=UTF-8" %>` (응답의 MIME 타입 및 문자 인코딩 설정)
- `import`: `<%@ page import="java.util.*" %>` (사용할 자바 클래스 임포트)

### JSP 스크립트 요소

- **script-let** (`<% ... %>`)
  - 자바 실행문을 작성하며, `_jspService` 메서드 내부에 삽입됨.
  - **Local 영역**이므로 선언된 변수는 지역 변수가 되며, 내장 객체를 자유롭게 사용 가능함.

- **declaration** (`<%! ... %>`)
  - 멤버 변수 또는 메서드를 선언함. 서블릿 클래스의 멤버 변수/메서드 영역에 위치하므로 전역적으로 사용 가능함.

- **expression** (`<%= ... %>`)
  - 변수나 메서드의 반환값을 출력함. 서블릿 변환 시 `out.print()` 형태로 변형됨.

- **comment** (`<%-- ... --%>`)
  - JSP 주석으로, 서블릿으로 변환될 때 제외되어 클라이언트(브라우저)에게 전달되지 않음.

## Built-in Object (내장 객체)
JSP 페이지의 `_jspService` 메서드 내부에서 별도의 선언 없이 즉시 사용 가능한 객체임.

| 변수명 | 변수 타입 | 용도 |
|:---:|:---|:---|
| **request** | `HttpServletRequest` | 클라이언트의 요청 정보 및 파라미터 관리 |
| **response** | `HttpServletResponse` | 클라이언트 응답 설정 (Redirect, Header 등) |
| **out** | `JspWriter` | 브라우저에 텍스트 데이터 출력 |
| **session** | `HttpSession` | 사용자 세션 정보 관리 (로그인 상태 등) |
| **application** | `ServletContext` | 웹 애플리케이션 전체의 공유 자원 및 환경 설정 관리 |
| **pageContext**| `PageContext` | 현재 페이지의 컨텍스트 정보 및 다른 내장 객체 접근 제공 |
| **config** | `ServletConfig` | 해당 JSP(서블릿)의 초기화 파라미터 정보 |
| **page** | `Object` | 현재 JSP 인스턴스 (`this`와 동일) |
| **exception** | `Throwable` | 에러 페이지(`isErrorPage="true"`)에서 발생한 예외 정보 |

### Web Scope
웹 애플리케이션에서 데이터를 저장하고 공유하는 영역으로, 생명주기에 따라 4가지로 나뉨.

| Scope | 접근 영역 | 설명 | 특징 |
|:---:|:---|:---|:---|
| **page** | 현재 페이지 | 현재 JSP 페이지 내에서만 유효 | `PageContext` 객체 사용 |
| **request** | 요청 범위 | `forward`나 `include`로 연결된 페이지까지 공유 | 하나의 요청 응답 사이클 동안 유지 |
| **session** | 세션 범위 | 동일 브라우저 내에서 브라우저 종료 전까지 공유 | 사용자별 상태 유지에 적합 |
| **application**| 웹 앱 범위 | 웹 애플리케이션 전체에서 모든 사용자가 공유 | 서버 시작부터 종료 시까지 유지 |

#### 사용 방법
내장 객체(Scope 객체)를 통해 데이터를 관리하는 공통 메서드임.

| 메서드 | 설명 |
|:---|:---|
| `void setAttribute(String name, Object value)` | 해당 Scope에 데이터를 이름(Key)과 값(Value) 쌍으로 저장 |
| `Object getAttribute(String name)` | 이름(Key)을 통해 저장된 데이터를 조회 (반환 타입 Object) |
| `void removeAttribute(String name)` | 이름(Key)을 통해 특정 데이터를 삭제 |
| `Enumeration getAttributeNames()` | 현재 Scope에 저장된 모든 속성 이름을 반환 |

## Parameter VS Attribute

| 구분 | Parameter | Attribute |
|:---|:---|:---|
| **출처** | 클라이언트 (Form, URL Query String) | 서버 (Servlet/JSP 내부 로직) |
| **설정 방법** | 브라우저 요청 시 전달 (서버에서 설정 불가) | `setAttribute()` 메서드로 서버에서 설정 |
| **조회 방법** | `getParameter()` | `getAttribute()` |
| **데이터 수정** | 불가능 (Read-Only) | 가능 (수정 및 삭제 자유로움) |
| **사용 시기** | 사용자 입력을 서버로 전달할 때 | 서버 내부에서 데이터를 공유 및 전달할 때 |

## Servlet VS JSP

| 구분 | Servlet | JSP |
|:---|:---|:---|
| **실행 과정** | 컴파일 후 바로 실행 | 변환 $\rightarrow$ 컴파일 $\rightarrow$ 실행 단계 거침 |
| **핵심 구성** | Java 코드 중심 (HTML은 문자열 출력) | HTML 중심 (Java 코드는 스크립트 요소 사용) |
| **개발 생산성**| 로직 구현이 편리하나 화면 설계가 어려움 | 화면 설계가 매우 편리하여 UI 작업에 적합 |
| **주요 역할** | Controller (비즈니스 로직 처리) | View (결과 화면 출력) |
