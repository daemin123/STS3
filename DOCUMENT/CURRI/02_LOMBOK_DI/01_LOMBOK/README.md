# LOMBOK




src/main/resources/Log4j.xml
---
> Appenders <br>
---
 
```
	<appender name="console" class="org.apache.log4j.ConsoleAppender">		- 출력 대상 선정
		<param name="Target" value="System.out" /> 				- 로그 출력을 Console에 함
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="%-5p: %c - %m%n" />	- 로그 포매팅 설정 
		</layout>
	</appender>
```
<br>

> Application Loggers <br>

> > 패키지별 로그 레벨에 따른 로깅 처리(com.example.app 안의 info 수준의 상황이 발생하면 로깅하겠다)<br>
---
```
로그레벨 정리 
	TRACE: 가장 상세한 로그 레벨입니다. 코드의 특정 부분이 실행될 때마다 세부 정보를 기록합니다. 일반적으로 개발 중 디버깅 목적으로 사용됩니다. 보통 프로덕션 환경에서는 사용되지 않습니다.
	DEBUG: 디버그 목적으로 사용되며, 애플리케이션 실행 중에 중요하지 않은 세부 정보를 기록합니다. TRACE보다는 덜 상세하지만 개발 중 디버깅에 유용합니다.
	INFO: 일반적인 정보를 기록하는 레벨입니다. 애플리케이션의 주요 이벤트나 실행 정보를 기록합니다. 애플리케이션이 예상대로 작동하는지 확인할 때 유용합니다.
	WARN: 경고 메시지를 기록하는 레벨입니다. 프로그램의 잠재적인 문제를 나타내지만, 심각한 문제는 아닙니다. 예를 들어, 잘못된 구성 설정이나 잠재적인 버그와 같은 경우에 사용됩니다.
	ERROR: 오류 메시지를 기록하는 레벨입니다. 심각한 문제가 발생했음을 나타내며, 애플리케이션의 기능이 올바르게 수행되지 않았음을 알립니다. 일반적으로 예외가 발생한 경우 이 레벨을 사용합니다.
	FATAL: 매우 심각한 오류를 나타내며, 애플리케이션의 실행이 중단되었음을 나타냅니다. 애플리케이션을 중단시켜야 하는 치명적인 오류가 발생한 경우에만 사용됩니다.
			
	로그 레벨은 보통 위의 순서대로 우선순위가 높아집니다. 예를 들어, TRACE 레벨은 가장 낮은 우선순위이며, 
	FATAL 레벨은 가장 높은 우선순위입니다. 설정된 로그 레벨보다 높은 우선순위의 메시지만이 출력됩니다. 
	예를 들어, INFO 레벨을 설정하면 INFO, WARN, ERROR 및 FATAL 메시지가 출력되지만, DEBUG 및 TRACE 메시지는 출력되지 않습니다.
```
<br>

> Root Logger
---
```
Root Logger

	모든 로깅 이벤트를 받는 최상위 로거 - 모든 패키지에 적용
	<priority value="warn" /> : 최소 로그 레벨 warn 이상만 출력
	<appender-ref ref="console" /> : 로그 출력 위치 위에 지정한 appender 로 지정

	<root>
		<priority value="warn" />
		<appender-ref ref="console" />
	</root>

```

LOMBOK 사용하기
---
> 개발자가 반복적으로 작성해야 하는 보일러플레이트 코드(boilerplate code)를 줄이고, 코드를 더 간결하고 가독성 있게 만드는데 사용되는 라이브러리<br>

> 게터, 세터, toString(), equals(), hashCode() 등의 메서드를 자동으로 생성이나 로깅처리도 가능 <br>

> com.example.app.domain.common.dto.PersonDto
---

```
//@Getter
//@Setter
//@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
	private String name;
	private int age;
	private String addr;	

}
```
> Junit5 Lombok Tests 

> > pom.xml 에 test dependencies 추가
---
```
		<!-- Spring TEST -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-test</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
```

> > 프로젝트 maven update <br>

> > Junit Test lib 추가
---
|-|
|-|
|<img width="" height="" src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/02/1.png" />|
|<img width="" height="" src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/02/2.png" />|
|<img width="" height="" src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/02/3.png" />|
|<img width="" height="" src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/02/4.png" />|
|<img width="" height="" src="https://github.com/MY-ALL-LECTURE/STS3/blob/main/DOCUMENT/IMG/02/5.png" />|

> > Junit Tests(src/test/java.ex01_Parameter.lombok.lombok.lombokTests.java;
---
```
@Slf4j
public class lombokTests {

	@Test
	public void personDtoTests() {
		PersonDto obj =new PersonDto();
		obj.setName("홍길동"); 
		obj.setAddr("대구");
		obj.setAge(55);
		log.info(obj.toString());
		
		PersonDto obj2 = PersonDto.builder()
						.name("철수")
						.age(11)
						.addr("서울")
						.build();
		log.info(obj2.toString());

	}	
}

```

