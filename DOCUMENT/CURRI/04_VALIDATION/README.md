# VALIDATION


WEBDATABINDER
---

|[참고](https://velog.io/@jipark09/Spring-%EB%8D%B0%EC%9D%B4%ED%84%B0%EC%9D%98-%EB%B3%80%ED%99%98%EA%B3%BC-%EA%B2%80%EC%A6%9D)|
|-|
|<img src="https://velog.velcdn.com/images/jipark09/post/68eff4fc-3d28-4e76-8f89-57947b040806/image.png" />|

```
스프링 MVC에서 사용되는 데이터 바인딩과 유효성 검사를 수행하는 클래스입니다.
이 클래스는 컨트롤러 메소드의 파라미터를 바인딩하고 유효성을 검사하는 역할을 합니다.
주로 폼 데이터를 처리하고 모델 객체에 바인딩하는 데 사용됩니다.
```

```
@InitBinder: 컨트롤러 클래스 내부에 선언하여, 특정 컨트롤러에 대한 WebDataBinder 초기화를 지정합니다.
주로 컨트롤러 클래스에 전역적으로 적용되는 초기화 작업을 수행할 때 사용됩니다.

@ModelAttribute: 컨트롤러 메소드의 파라미터에 사용되며, 해당 파라미터를 모델에 추가하고 뷰에서 사용할 수 있게 합니다.
폼 데이터를 모델 객체에 자동으로 바인딩할 때 주로 사용됩니다.

@Valid: 컨트롤러 메소드의 파라미터에 사용되며, 해당 파라미터에 대한 유효성 검사를 지정합니다.
주로 모델 객체에 대한 유효성 검사를 수행할 때 사용됩니다.

@Validated: 클래스나 그룹에 대한 유효성 검사를 수행하는 데 사용됩니다.
주로 폼 데이터를 처리하는 DTO(Data Transfer Object)나 VO(Value Object)에 유효성 검사를 수행할 때 사용됩니다.

유효성 검사 관련 애노테이션: 앞서 언급한 다양한 유효성 검사를 위한 애노테이션들도 WebDataBinder와 함께 사용됩니다.
이들 애노테이션은 데이터의 유효성을 검사하여 바인딩 과정에서 오류를 처리합니다.

```

의존처리(pom.xml)
---

```
		<dependency>
			<groupId>org.hibernate.validator</groupId>
			<artifactId>hibernate-validator</artifactId>
			<version>6.0.7.Final</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/javax.validation/validation-api -->
		<dependency>
    		<groupId>javax.validation</groupId>
    		<artifactId>validation-api</artifactId>
    		<version>2.0.1.Final</version>
		</dependency>
```


Validation 애노테이션 정리
---
[참고](https://hyeran-story.tistory.com/81)

```
숫자 유효성 검사:
  @Min(value, message): 숫자가 주어진 최솟값보다 큰지 확인합니다.
  @Max(value, message): 숫자가 주어진 최댓값보다 작은지 확인합니다.
  @Positive: 숫자가 양수인지 확인합니다.
  @PositiveOrZero: 숫자가 양수 또는 0인지 확인합니다.
  @Negative: 숫자가 음수인지 확인합니다.
  @NegativeOrZero: 숫자가 음수 또는 0인지 확인합니다.
문자열 유효성 검사:
  @NotBlank(message): 문자열이 비어 있지 않은지 확인합니다.
  @Size(min, max, message): 문자열의 길이가 주어진 범위 내에 있는지 확인합니다.
  @Email(message): 문자열이 유효한 이메일 주소인지 확인합니다.
  @Pattern(regexp, message): 정규 표현식에 맞는지 확인합니다.
  @URL(message): 문자열이 유효한 URL인지 확인합니다.
날짜와 시간 유효성 검사:
  @DateTimeFormat(pattern): 날짜와 시간의 형식을 지정합니다.
  @Future: 날짜가 미래인지 확인합니다.
  @FutureOrPresent: 날짜가 미래이거나 현재인지 확인합니다.
  @Past: 날짜가 과거인지 확인합니다.
  @PastOrPresent: 날짜가 과거이거나 현재인지 확인합니다.
기타:
  @AssertTrue: boolean 타입의 값이 true인지 확인합니다.
  @AssertFalse: boolean 타입의 값이 false인지 확인합니다.
  @CreditCardNumber: 신용카드 번호의 유효성을 검증합니다.
```



