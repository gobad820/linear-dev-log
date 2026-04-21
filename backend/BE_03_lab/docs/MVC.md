# MVC (Model-View-Controller) 디자인 패턴: 백엔드 개발자의 필수 지침서

MVC 패턴은 현대 웹 애플리케이션 아키텍처의 근간을 이루는 디자인 패턴입니다. 단순히 코드를 나누는 것을 넘어, **'관심사의 분리(Separation of Concerns)'**를 통해 시스템의 복잡도를 낮추고 유지보수성을 극대화하는 것이 핵심 목적입니다.

---

## 1. MVC 패턴의 구성 요소와 역할

### 🛠 Model (데이터와 비즈니스 로직)
모델은 애플리케이션이 "무엇을 할 것인지"를 정의합니다. 데이터의 상태를 관리하고, 이를 조작하는 모든 비즈니스 규칙을 포함합니다.
- **DAO (Data Access Object)**: 데이터베이스와의 통신을 전담하는 객체.
- **DTO/VO (Data Transfer Object / Value Object)**: 계층 간 데이터 교환을 위한 바구니 역할.
- **Service**: 실질적인 비즈니스 로직이 구현되는 곳 (Model의 핵심부).

### 🖥 View (사용자 인터페이스)
뷰는 모델이 처리한 데이터를 사용자에게 "어떻게 보여줄 것인지"를 담당합니다.
- 비즈니스 로직이나 데이터베이스 접근 코드가 포함되어서는 안 됩니다.
- JSP, Thymeleaf, 또는 프론트엔드 프레임워크(React, Vue)에 전달할 JSON 데이터가 이에 해당합니다.

### 🎮 Controller (조율자)
컨트롤러는 사용자의 요청을 받아 모델과 뷰 사이의 다리 역할을 수행합니다.
- 요청(Request)을 분석하여 적절한 서비스(Model)를 호출합니다.
- 처리 결과 데이터(Attribute)를 뷰(View)에 전달하고, 화면 전환을 제어합니다.

---

## 2. 웹 아키텍처의 진화: Model 1 $\rightarrow$ Model 2

백엔드 개발자는 과거의 **Model 1** 방식에서 왜 현재의 **Model 2(표준 MVC)** 방식으로 발전했는지 이해해야 합니다.

```mermaid
graph LR
    subgraph "Model 1 (JSP Only)"
    M1_Client["Client (Browser)"] -- Request --$\rightarrow$ M1_JSP["JSP (Controller + View)"]
    M1_JSP -- SQL --$\rightarrow$ M1_DB[(Database)]
    end

    subgraph "Model 2 (Standard MVC)"
    M2_Client["Client (Browser)"] -- Request --$\rightarrow$ M2_Servlet["Servlet (Controller)"]
    M2_Servlet -- Business Logic --$\rightarrow$ M2_Model["Model (Service/DAO)"]
    M2_Model -- View Data --$\rightarrow$ M2_Servlet
    M2_Servlet -- Forward --$\rightarrow$ M2_View["View (JSP)"]
    end
```

- **Model 1**: 구현이 빠르지만 HTML과 자바 코드가 섞여 있어 유지보수가 지옥(Spaghetti Code)에 가깝습니다.
- **Model 2**: 역할이 명확히 분리되어 있어 협업이 용이하고, 백엔드 로직만 따로 테스트하기에 최적화되어 있습니다.

---

## 3. 백엔드 실무 계층형 구조 (Layered Architecture)

실제 현업에서의 MVC는 단순히 세 부분으로 나뉘지 않고, 다음과 같이 계층화된 흐름을 가집니다.

```mermaid
graph TD
    A["Client (Browser)"] -- "1. Request (Parameter)" --$\rightarrow$ B["Controller (Servlet)"]
    B -- "2. Call Method" --$\rightarrow$ C["Service (Business Logic)"]
    C -- "3. Data Access" --$\rightarrow$ D["DAO (JDBC/MyBatis/JPA)"]
    D -- "4. Query" --$\rightarrow$ E[(Database)]
    E -- "5. Result Set" --$\rightarrow$ D
    D -- "6. Return VO/DTO" --$\rightarrow$ C
    C -- "7. Return Result" --$\rightarrow$ B
    B -- "8. Set Attribute & Forward" --$\rightarrow$ F["View (JSP)"]
    F -- "9. Response (HTML)" --$\rightarrow$ A
```

---

## 4. 백엔드 개발자를 위한 MVC 설계 원칙

1. **Fat Controller를 경계하라**: 컨트롤러는 요청의 입구이자 출구일 뿐입니다. 복잡한 계산이나 로직은 반드시 **Service(Model)** 계층에서 처리하세요.
2. **View의 독립성**: 뷰가 모델의 구조를 너무 자세히 알게 하지 마세요. 필요한 데이터만 DTO에 담아 최소한으로 전달하는 것이 좋습니다.
3. **무결성 유지**: 파라미터(Parameter)는 날 것의 문자열입니다. 컨트롤러에서 이를 검증(Validation)한 뒤 안전한 객체로 변환하여 모델에 넘겨주어야 합니다.

---

## 📋 최종 요약 (Summarization)

**MVC 패턴**은 애플리케이션을 **Model(데이터/로직), View(화면), Controller(제어)**로 명확히 분리하여 **변경에 유연한 구조**를 만드는 디자인 패턴입니다.

백엔드 개발자에게 있어 MVC의 핵심은 **Model 2 방식의 계층 분리**입니다. 클라이언트로부터 받은 **Parameter**를 분석하여 **Service/DAO**를 통해 비즈니스 로직을 수행하고, 최종 결과물을 **Attribute**에 담아 **View**로 넘겨주는 일련의 흐름을 완벽히 이해하는 것이 백엔드 엔지니어링의 시작입니다.
