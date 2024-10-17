# STS3 설치 | 기본환경 설정
---



STS3 설치
---
|-|
|-|
|[STS_DOWNLOAD](https://spring.io/tools)|
|<img src="https://github.com/MY-ALL-LECTURE/STS3/assets/84259104/d4bc47ed-03eb-4f6c-adbd-cf7e44c63ca5" width="800px" height="300px" style="border:1px solid green" alt="TEMPLATE" />|
|<img src="https://github.com/MY-ALL-LECTURE/STS3/assets/84259104/1cacace5-1239-4561-990a-8d3a1ae593eb" width="800px" height="300px" style="border:1px solid green" alt="TEMPLATE" />|


JDK 11 설정
---
|-|
|-|
|[JDK_11_DOWNLOAD](https://jdk.java.net/archive/)|
|1 환경변수 PATH 경로 설정|
|2 STS.ini 설정|
|<img src="https://github.com/MY-ALL-LECTURE/STS3/assets/84259104/a089ed39-d4d7-4b64-aa5d-f9ca00d18692" width="100%" height="300px" style="border:1px solid green" alt="TEMPLATE" />|

STS 문자 기본설정
---
  - window > preferences >  General > Workspace > Text file encoding > UTF-8
  - window > preferences > files 검색 > UTF-8 변경

|-|
|-|
|![20240428170016](https://github.com/MY-ALL-LECTURE/STS3/assets/84259104/afb5966c-7c30-42b2-ae60-724e2a7039b4)|

톰캣 설정(생략)
---

Spring MVC Lagacy Project
---
  - file > new > Spring Lagacy Project
    - Project name  : ex01
    - Configure templates ->  spring-defaults 를 제외한 temnplate 삭제 -> apply
    - STS3종료
    - 본인지정WORKSPACE\\.metadata\.plugins\org.springsource.ide.eclipse.commons.content.core 에 https-content.xml 추가
    - STS3실행 후 file > new > Spring Lagacy Project 다시 진행
     
- Project name : ex01
- Templates : Spring MVC Project > next(오류발생!) > STS3종료
- [오류해결]
  -  본인지정Workspace\.metadata\.sts\content\org.springframework.templates.mvc-3.2.2 폴더 안에 org.springframework.templates.mvc-3.2.2.zip 파일 압축풀기 & 해당 파일 삭제
  -  STS3실행 후 file > new > Spring Lagacy Project 다시 진행
  -  com.example.app 로 패키지경로 지정

pom.xml 설정  
---
- System Lib Version 변경
  - Project 우클릭 -> properties -> Project Facets -> Java Version 11 선택 -> Runtimes -> Apache Tomcat 체크 -> Apply
- SpringFramework Version & Jdk Version 변경<br>
![20240417211232](https://github.com/MY-ALL-LECTURE/STS3/assets/84259104/ceba8d9d-3cc9-45f5-8fb0-48e2573eec3b)
 
- Junit Version 변경<br>
![20240417211329](https://github.com/MY-ALL-LECTURE/STS3/assets/84259104/470f9685-258c-47cb-9c52-a3a8c0aa25d1)
 
- WORKSPACE 한글포함시 문제 오류 방지 Dependencies 추가<br>
![20240417211455](https://github.com/MY-ALL-LECTURE/STS3/assets/84259104/86f42283-a3ec-45d8-b976-ea2f93d99c33)
 
- Maven compiler Plugin Version 변경<br>
![20240417211431](https://github.com/MY-ALL-LECTURE/STS3/assets/84259104/5e110663-58ea-4f40-bf42-de97d9a1f6e1)
 
- Project Maven Update
  - Project 우클릭 -> Run as -> Maven Clean
  - Project 우클릭 -> Run as -> Maven Install
  - Project 우클릭 -> maven -> Update Project -> ok

web.xml 설정  
---
- UTF 문자열 필터 설정 <br>

![20240417215003](https://github.com/MY-ALL-LECTURE/STS3/assets/84259104/2853caed-809c-4401-948b-cf68fd852619)


lombok 설정 (Pom.xml)
---
- 기존 log4j 설정 변경(Scope 변경)<br>
![20240418120051](https://github.com/MY-ALL-LECTURE/STS3/assets/84259104/3c43d476-f32c-49a5-a067-a84d17dfb1bd)

- Lombok Dependencies Code 가져오기(Maven Repository)
- 프로젝트 Maven Clean -> Install , Project Update
- Maven Dependencies -> lombok.jar 우클릭 Run -> Application
- Lombok 설치창 뜨면 STS 폴더 경로 선택후 Install
- STS3 종료
- STS3.ini 에서 lombok 경로를 상대경로로 지정

  |-|
  |-|
  |<img  width="" height="" src="https://github.com/MY-ALL-LECTURE/STS3/assets/84259104/dc91e511-ca11-45bd-b69f-1815cb078da5" />|
  |<img  width="" height="" src="https://github.com/MY-ALL-LECTURE/STS3/assets/84259104/a58939de-6024-4103-91d1-586169dfa4d5" />|

- STS3 실행
- HomeController 에서 log.info() 사용 확인 


