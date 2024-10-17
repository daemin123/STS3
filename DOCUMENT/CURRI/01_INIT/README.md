# STS3 설치 | 기본환경 설정
---



STS3 설치
---
|-|
|-|
|[STS_DOWNLOAD](https://spring.io/tools)|
|<img src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/01/1.png" width="800px" height="300px" style="border:1px solid green" alt="TEMPLATE" />|
|<img src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/01/2.png" width="800px" height="300px" style="border:1px solid green" alt="TEMPLATE" />|


JDK 11 설정
---
|-|
|-|
|[JDK_11_DOWNLOAD](https://jdk.java.net/archive/)|
|-|
|1 환경변수 PATH 경로 설정|
|2 STS.ini 설정|
|<img src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/01/3.png" width="100%" height="300px" style="border:1px solid green" alt="TEMPLATE" />|

STS 문자 기본설정
---
> window > preferences >  General > Workspace > Text file encoding > UTF-8<br>

> window > preferences > files 검색 > UTF-8 변경<br>

|-|
|-|
|<img src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/01/4.png" style="border:1px solid green" alt="TEMPLATE" />|

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
> System Lib Version 변경<br>

> > Project 우클릭 -> properties -> Project Facets -> Java Version 11 선택 -> Runtimes -> Apache Tomcat 체크 -> Apply<br>

> SpringFramework Version & Jdk Version 변경<br>
---
|-|
|-|
|<img src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/01/5.png" style="border:1px solid green" alt="TEMPLATE" />|
 


> Junit Version 변경<br>
---
|-|
|-|
|<img src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/01/6.png" style="border:1px solid green" alt="TEMPLATE" />|


> WORKSPACE 한글포함시 문제 오류 방지 Dependencies 추가<br>
---

|-|
|-|
|<img src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/01/7.png" style="border:1px solid green" alt="TEMPLATE" />|


> Maven compiler Plugin Version 변경<br>
---
|-|
|-|
|<img src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/01/8.png" style="border:1px solid green" alt="TEMPLATE" />|



> 전체코드 <br>
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.example</groupId>
	<artifactId>app</artifactId>
	<name>ex00</name>
	<packaging>war</packaging>
	<version>1.0.0-BUILD-SNAPSHOT</version>
	<properties>
		<java-version>11</java-version>
		<org.springframework-version>5.0.7.RELEASE</org.springframework-version>
		<org.aspectj-version>1.6.10</org.aspectj-version>
		<org.slf4j-version>1.6.6</org.slf4j-version>
	</properties>
	<dependencies>
		<!-- Spring -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${org.springframework-version}</version>
			<exclusions>
				<!-- Exclude Commons Logging in favor of SLF4j -->
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				 </exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
				
		<!-- AspectJ -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>	
		
		<!-- Logging -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${org.slf4j-version}</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>jcl-over-slf4j</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.15</version>
			<exclusions>
				<exclusion>
					<groupId>javax.mail</groupId>
					<artifactId>mail</artifactId>
				</exclusion>
				<exclusion>
					<groupId>javax.jms</groupId>
					<artifactId>jms</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jdmk</groupId>
					<artifactId>jmxtools</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jmx</groupId>
					<artifactId>jmxri</artifactId>
				</exclusion>
			</exclusions>
			<!-- <scope>runtime</scope> -->
		</dependency>

		<!-- @Inject -->
		<dependency>
			<groupId>javax.inject</groupId>
			<artifactId>javax.inject</artifactId>
			<version>1</version>
		</dependency>
				
		<!-- Servlet -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>servlet-api</artifactId>
			<version>2.5</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.1</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>
	
		<!-- Test -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.12</version>
			<scope>test</scope>
		</dependency>     
		
		<!-- Workspace 한글경로 포함문제 해결 -->
		<dependency>
		    <groupId>xerces</groupId>
		    <artifactId>xercesImpl</artifactId>
		    <version>2.12.2</version>
		</dependency>
		   
		<!-- lombok added -->
		<dependency>
		    <groupId>org.projectlombok</groupId>
		    <artifactId>lombok</artifactId>
		    <version>1.18.32</version>
		    <scope>provided</scope>
		</dependency>
				
						   
	</dependencies>
    <build>
        <plugins>
            <plugin>
                <artifactId>maven-eclipse-plugin</artifactId>
                <version>2.9</version>
                <configuration>
                    <additionalProjectnatures>
                        <projectnature>org.springframework.ide.eclipse.core.springnature</projectnature>
                    </additionalProjectnatures>
                    <additionalBuildcommands>
                        <buildcommand>org.springframework.ide.eclipse.core.springbuilder</buildcommand>
                    </additionalBuildcommands>
                    <downloadSources>true</downloadSources>
                    <downloadJavadocs>true</downloadJavadocs>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.5.1</version>
                <configuration>
                    <source>${java-version}</source>
                    <target>${java-version}</target>
                    <compilerArgument>-Xlint:all</compilerArgument>
                    <showWarnings>true</showWarnings>
                    <showDeprecation>true</showDeprecation>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>1.2.1</version>
                <configuration>
                    <mainClass>org.test.int1.Main</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>


```


> Project Maven Update<br>

> > Project 우클릭 -> Run as -> Maven Clean<br>

> > Project 우클릭 -> Run as -> Maven Install<br>

> > Project 우클릭 -> maven -> Update Project -> ok<br>

web.xml 설정  
---

> UTF 문자열 필터 설정 <br>

|-|
|-|
|<img src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/01/9.png" style="border:1px solid green" alt="TEMPLATE" />|

> 전체코드<br>
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee https://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">

	<!-- The definition of the Root Spring Container shared by all Servlets and Filters -->
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/root-context.xml</param-value>
	</context-param>
	
	<!-- Creates the Spring Container shared by all Servlets and Filters -->
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

	<!-- Processes application requests -->
	<servlet>
		<servlet-name>appServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
		
	<servlet-mapping>
		<servlet-name>appServlet</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>

		<!-- 한글 변환 필터 시작 -->
		<filter>
			<filter-name>encodingFilter</filter-name>
			<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
			<init-param>
				<param-name>encoding</param-name>
				<param-value>UTF-8</param-value>
			</init-param>
			<init-param>
				<param-name>forceEncoding</param-name>
				<param-value>true</param-value>
			</init-param>
		</filter>
		
		<filter-mapping>
			<filter-name>encodingFilter</filter-name>
			<url-pattern>/*</url-pattern>
		</filter-mapping>
		
</web-app>

```

lombok 설정 (Pom.xml)
---

> 기존 log4j 설정 변경(Scope 변경)<br>

|-|
|-|
|<img src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/01/10.png" style="border:1px solid green" alt="TEMPLATE" />|


> > Lombok Dependencies Code 가져오기(Maven Repository)<br>

> > 프로젝트 Maven Clean -> Install , Project Update<br>

> > Maven Dependencies -> lombok.jar 우클릭 Run -> Application<br>

> > Lombok 설치창 뜨면 STS 폴더 경로 선택후 Install<br>

> > STS3 종료<br>

> > STS3.ini 에서 lombok 경로를 상대경로로 지정 <br>

|-|
|-|
|<img src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/01/11.png" style="border:1px solid green" alt="TEMPLATE" />|
|<img src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/01/12.png" style="border:1px solid green" alt="TEMPLATE" />|

- STS3 실행
- HomeController 에서 log.info() 사용 확인 


