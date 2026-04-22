# SSA-54: Spring MVC DispatcherServlet — Front Controller 패턴 이해

## 🎯 학습 목표 및 완료 기준
1. **Front Controller 패턴의 필요성 이해**: 개별 서블릿이 각각 공통 로직을 처리해야 했던 기존 방식의 단점을 파악하고, 진입점을 하나로 두어 공통 로직을 처리하는 Front Controller 패턴의 장점을 명확히 설명할 수 있다.
2. **요청 위임(Dispatch) 메커니즘 직접 구현**: 하나의 메인 서블릿(`FrontControllerServlet`)이 모든 요청을 받아, URI에 따라 적절한 하위 컨트롤러로 위임하는 구조를 코드로 작성한다.
3. **Spring MVC와의 연결점 파악**: 작성한 실습 코드가 Spring MVC 프레임워크의 핵심인 `DispatcherServlet`의 축소판임을 인지하고, 그 동작 원리를 구조적으로 매핑할 수 있다.

## 📝 학습 계획
1. **기초 개념 복습**: 기존 MVC 패턴에서 컨트롤러마다 중복되는 로직(예: Forward/Redirect 처리, 권한 체크 등)을 분리할 필요성 느끼기.
2. **인터페이스 설계**: 모든 하위 컨트롤러가 구현해야 할 표준 규격(`Controller` 인터페이스) 설계하기.
3. **FrontController 구현**: `/*` 패턴으로 모든 요청을 가로채어, Map에 매핑된 하위 컨트롤러를 찾아 실행하는 메인 서블릿 작성.
4. **리뷰 및 회고**: 직접 구현한 Front Controller 로직과 실제 Spring MVC의 `DispatcherServlet` 흐름을 비교하며 주석으로 요약 정리하기.

---

### 실습 파일 구조
- `Controller.java`: 하위 컨트롤러들의 공통 인터페이스
- `FrontControllerServlet.java`: 모든 요청을 중앙 집중적으로 받아 처리하는 메인 서블릿 (DispatcherServlet 역할)
- `MemberController.java`, `BoardController.java`: 각 요청에 대해 실제 비즈니스 로직을 수행하는 하위 컨트롤러
