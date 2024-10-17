### 기본개념
---


FRAMEWORK란
---
> 작업에 필요한 여러가지 것들을 따로 직접 구할 필요없이 FRAME내에서 가져와서 쓸수있도록 제공하는 틀 <br>

> FRAMEWORK / LIBRARY / API 차이 <br>

|-|능동/수동|UTILITY POSITION|개발자POSITION|
|-|-|-|-|
|FRAMEWORK|능동|제작을 의뢰|조건 제공|
|LIBRARY|수동|직접 도구를 사용하서 제작|직접 사용|
|API|능동|프로그램간(or 시스템-프로그램 간) 연결|통신|

|참고|
|-|
|[FRAMEWORK Vs LIBRARY Vs API](https://www.youtube.com/watch?v=yKEwNVbAFC0)|

---
[참고](https://www.youtube.com/watch?v=yKEwNVbAFC0)
---

SPRINGFRAMEWORK 란
---
> Java 플랫폼을 위한 오픈 소스 애플리케이션 프레임워크<br>

|핵심기능|설명|
|-|-|
|의존성 주입 (Dependency Injection, DI)|Spring은 객체 간의 의존성을 주입하는 기능을 제공합니다. DI를 통해 객체 간의 결합도를 낮추고 코드의 유연성을 향상시킵니다.|
|제어의 역전 (Inversion of Control, IoC)|Spring은 제어의 역전 원칙을 따르며, 객체의 생명주기와 의존성 관리를 프레임워크가 담당합니다. 이를 통해 개발자는 객체의 생성과 관리에 대한 부담을 줄일 수 있습니다.|
|AOP (Aspect-Oriented Programming)|Spring은 관점 지향 프로그래밍을 지원하여 횡단 관심사를 모듈화할 수 있습니다. 이를 통해 핵심 비즈니스 로직과 각광 받는 부가 기능을 분리할 수 있습니다.|
|트랜잭션 관리|Spring은 선언적 트랜잭션 관리를 지원하여 개발자가 트랜잭션 처리에 대한 복잡한 코드를 작성하지 않아도 됩니다. 데이터베이스 연산의 일관성과 안정성을 보장합니다.|
|스프링 MVC (Spring Model-View-Controller)|Spring은 웹 애플리케이션을 개발하기 위한 MVC 아키텍처를 제공합니다. Spring MVC를 사용하면 요청을 처리하는 컨트롤러, 데이터를 표시하는 뷰, 비즈니스 로직을 처리하는 모델을 분리하여 개발할 수 있습니다.|
|스프링 데이터 (Spring Data)|Spring은 데이터 액세스를 위한 다양한 기능을 제공합니다|-|-|
|스프링 시큐리티 (Spring Security)|Spring은 보안을 위한 스프링 시큐리티 프레임워크를 제공합니다. 스프링 시큐리티를 사용하면 인증, 권한 부여, 보안 설정 등을 쉽게 구현할 수 있습니다.|
|스프링 배치 (Spring Batch):|Spring은 대용량 데이터 처리를 위한 스프링 배치 프레임워크를 제공합니다. 스프링 배치는 일괄 처리 작업을 생성하고 구성할 수 있으며, 작업을 스케줄링하고 청크 기반 처리를 통해 대용량 데이터를 효율적으로 처리할 수 있습니다.|

STS란
---
> Spring 개발업체 SpringSource가 직접 만들어 제공하는 이클립스의 확장판<br>

> Eclipse 와 거의 유사<br>

|-|공통점|차이점|
|-|-|-|
|STS3|스프링 개발환경<br>이클립스 플러그인|xml기반<br>스프링부트 미지원<br>maven build 기본 <br>|
|STS4|-|java Config기반<br>스프링부트 지원<br> gradle build 지원 <br>|


|참고|
|-|
|[전자정부프레임워크](https://www.egovframe.go.kr/home/sub.do?menuNo=9)|



BUILD란
---
|참고-용어정리|
|-|
|[빌드](https://ee-22-joo.tistory.com/34)|
<br>

> 소스 코드를 컴파일하고 패키징하여 실행 가능한 애플리케이션 또는 라이브러리를 생성하는 도구<br>

> 소프트웨어 개발 과정 중 하나로, 소스 코드 및 리소스를 컴퓨터가 이해할 수 있는 실행 가능한 프로그램 또는 라이브러리로 변환하는 과정<br>

> JAVA 빌드도구 <br>

|-|-|-|-|
|-|-|-|-|
|ANT|Apache Ant는 자바 애플리케이션을 빌드하기 위한 자유 형식의 빌드 도구<br>XML 기반의 빌드 스크립트를 사용하여 프로젝트를 빌드하고 관리<br>절차적 프로그래밍 스타일을 따르며, 각 단계를 명시적으로 정의해야 함<br>초기 구축에는 유용하지만 복잡한 의존성 관리 및 유지보수에는 부적합할 수 있음|[공식Doc](https://ant.apache.org/)|-|
|MAVEN|Apache Maven은 프로젝트 관리 및 자동화 도구로 널리 사용됨<br>프로젝트 구조, 라이브러리 종속성, 빌드 프로세스 등을 관리하는 데 중점을 둠<br>POM(Project Object Model)을 사용하여 프로젝트를 설명하며, 중앙 저장소에서 의존성을 관리<br>표준화된 프로젝트 구조 및 빌드 프로세스를 제공하여 프로젝트 간의 일관성을 유지할 수 있음|[공식Doc](https://maven.apache.org/)<br>[MAVEN REPO SITE](https://mvnrepository.com/)|-|
|GRADLE|Gradle은 Apache Ant와 Apache Maven의 장점을 결합한 빌드 자동화 도구<br>Groovy DSL(Domain Specific Language)을 사용하여 빌드 스크립트를 작성하며, Ant와 Maven의 유연성을 제공<br>높은 수준의 선언적 DSL을 사용하여 의존성 관리, 테스팅, 배포 등을 지원<br>Incremental build 및 스레드 병렬 처리 등 성능 향상 기능을 제공|[공식doc](https://gradle.org/)<br>[start.spring.io](https://start.spring.io/)|-|





