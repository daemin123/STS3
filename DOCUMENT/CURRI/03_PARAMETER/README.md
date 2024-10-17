# PARAMETER

요청 URI MAPPING 애노테이션
---

|ANNOTATION|OPTION|DESCRIPTION|
|-|-|-|
|@RequestMapping|-|스프링 MVC에서 가장 기본적으로 사용되는 애노테이션으로, HTTP 요청과 메소드를 매핑|
||value or path|매핑할 URI 패턴을 지정|
||method| 요청을 처리할 HTTP 메소드를 지정, ex. RequestMethod.GET, RequestMethod.POST ..|
||params|요청의 파라미터를 제한|
||headers|요청의 헤더를 제한|
||consumes|요청의 컨텐츠 타입을 제한|
||produces|응답의 컨텐츠 타입을 제한|
|-|-|-|
|@GetMapping|-|HTTP GET 요청과 메소드를 매핑<br>@RequestMapping(method = RequestMethod.GET)과 동일|
|@PostMapping|-| HTTP POST 요청과 메소드를 매핑<br>@RequestMapping(method = RequestMethod.POST)와 동일|
|@PutMapping|-|HTTP PUT 요청과 메소드를 매핑<br>@RequestMapping(method = RequestMethod.PUT)와 동일|
|@DeleteMapping|-|HTTP DELETE 요청과 메소드를 매핑<br>@RequestMapping(method = RequestMethod.DELETE)와 동일|


요청 파라미터 MAPPING 애노테이션
---
|-|-|-|-|
|-|-|-|-|
|@RequestParam|-| HTTP 요청의 파라미터를 메소드의 파라미터와 바인딩<br>URL 쿼리 문자열이나 POST 요청의 form 데이터와 매핑<br>|-|
||value|요청 파라미터의 이름을 지정<br>기본값은 메소드 파라미터의 이름과 동일|-|
||required|파라미터가 필수인지 여부를 지정<br>기본값은 true|-|
||defaultValue|파라미터의 기본값을 지정|-|
||name|파라미터의 별칭을 지정<br>요청에서 사용되는 이름과 메소드 파라미터의 이름이 다를 때 사용|-|
|-|-|-|-|
|@RequestBody|-|HTTP 요청의 본문(body)을 메소드의 파라미터에 바인딩<br>주로 JSON 또는 XML 형식의 데이터를 객체로 변환할 때 사용|-|
|-|-|-|-|
|@PathVariable|-|URI의 일부를 메소드의 파라미터에 바인딩<br>주로 RESTful API에서 동적인 URI를 처리할 때 사용|-|
|-|-|-|-|
|@ModelAttribute|-|요청의 특정 속성을 메소드의 파라미터로 바인딩<br>주로 HTML 폼 데이터를 모델 객체로 변환할 때 사용|[[중요 - ModelAttribute와 WebDataBinder]](https://byungmin.tistory.com/68)|
|-|-|-|-|

View로 Data 전달에 사용되는 클래스
---
|-|-|-|
|-|-|-|
|Model|-|스프링 MVC에서 가장 일반적으로 사용되는 클래스 중 하나<br>컨트롤러 메소드에서 데이터를 View로 전달하는 데 사용|
|-|-|-|
|ModelAndView|-|Model과 View를 모두 포함하는 클래스<br>ViewResolver가 지정한 view가 아닌 원하는 특정View를 선택 |
|-|-|-|
|Map|-|Model과 유사한 역할을 하는 인터페이스로, 컨트롤러 메소드에서 데이터를 View로 전달하는 데 사용|
|-|-|-|
|ModelMap|-|Model과 유사한 역할을 하는 클래스로, 컨트롤러 메소드에서 데이터를 View로 전달하는 데 사용|

FORWARD / REDIRECT
---

|-|-|-|
|-|-|-|
|return "forward:/uri"|-|-|
|-|-|-|
|return "redirect:/uri"|-|-|
|-|RedirectAttributes|리다이렉트 시에 데이터를 전달하기 위한 클래스<br>리다이렉트 후에도 데이터를 유지하고자 할 때 사용|


