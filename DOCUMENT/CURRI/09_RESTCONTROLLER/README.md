# @RestController

|참고|
|-|
|[Controller vs RestController](https://velog.io/@dyunge_100/Spring-Controller%EC%99%80-RestController%EC%9D%98-%EC%B0%A8%EC%9D%B4)|


@RestController(BACKEND-SPRING)
---
> RESTful 웹 서비스를 제공하는 컨트롤러 <br>

> > REST : REpresentative State Transfer의 약자로 분산 시스템을 위한 아키텍처 <br>

> > 네트워크를 경유해서 외부 서버에 접속하거나 필요한 정보를 불러오기 위한 구조 <br>

> > 그리고 이 REST 개념을 바탕으로 설계된 시스템을 'RESTFul'이라고 표현 <br>


> 메서드의 반환 값이 JSON, XML 등과 같은 데이터로 처리 <br>

> @Controller + @ResponseBody 형태 <br>

```
- 
```

비동기요청함수(FRONTEND-JAVASCRIPT)
---
> 종류 <br>

```
XMLHttpRequest (XHR):
  연도: 1999년
  설명: Microsoft의 인터넷 익스플로러 5에서 처음 등장한 기술로, 웹 페이지에서 비동기적으로 데이터를 받아오는 데 사용됩니다.
Promise:
  연도: 2015년 (ECMAScript 2015, 일명 ES6)
  설명: Promise는 비동기 작업의 결과를 다루기 위한 객체로, 콜백 헬(callback hell)을 해결하고 코드를 보다 깔끔하게 작성할 수 있도록 도와줍니다.
Fetch API:
  연도: 2015년 (ECMAScript 2015, 일명 ES6)
  설명: Fetch API는 네트워크 요청을 생성 및 조작하기 위한 간단한 인터페이스를 제공합니다. XHR보다 더 간결하고 유연한 API를 제공하며 Promise를 사용하여 비동기 작업을 처리합니다.
Axios:
  연도: 2014년
  설명: Axios는 HTTP 클라이언트 라이브러리로, 브라우저 및 Node.js 환경에서 모두 사용할 수 있습니다. Promise 기반의 API를 제공하며,
  특히 간편한 인터셉터(interceptors), CSRF 보호, JSON 데이터 자동 직렬화 등을 제공하여 개발자들에게 매우 편리한 HTTP 요청 처리 방법을 제공합니다.

```

---
#
---

기본코드 
---

> pom.xml<br>
```
		<!-- RESTFUL -->		
		<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
		<dependency>
		    <groupId>com.fasterxml.jackson.core</groupId>
		    <artifactId>jackson-databind</artifactId>
		    <version>2.15.2</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-xml -->
		<dependency>
		    <groupId>com.fasterxml.jackson.dataformat</groupId>
		    <artifactId>jackson-dataformat-xml</artifactId>
		    <version>2.15.2</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
		<dependency>
		    <groupId>com.google.code.gson</groupId>
		    <artifactId>gson</artifactId>
		    <version>2.10.1</version>
		</dependency>
```


> RestController_01 <br>

```
package com.example.app.restcontroller;
@RestController
@Slf4j
@RequestMapping("/rest")
public class RestController_01 {
	
	@GetMapping(value="/getText" , produces=MediaType.TEXT_PLAIN_VALUE)
	public String f1() {
		log.info("GET /rest/getText...");
		return "HELLO WORLD";
	}

	@GetMapping(value="/getJson" , produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public MemoDto f2() {
		log.info("GET /rest/getJson...");
		return new MemoDto(11,"ABCD");
	}
	
	@GetMapping(value="/getXml" , produces=MediaType.APPLICATION_XML_VALUE)
	public MemoDto f3() {
		log.info("GET /rest/getXml...");
		return new MemoDto(11,"ABCD");
	}
	
	@GetMapping(value="/getXmlList" , produces=MediaType.APPLICATION_XML_VALUE)
	public List<MemoDto> f4() {
		log.info("GET /rest/getXmlList...");
		List<MemoDto> list = new ArrayList();
		for(int i=0;i<50;i++) {
			list.add(new MemoDto(i,"A"+i));
		}
		return list;
	}
	
	@GetMapping(value="/getXmlList2/{show}" , produces=MediaType.APPLICATION_XML_VALUE)
	public  ResponseEntity< List<MemoDto> > f5(@PathVariable("show") boolean show) {
		log.info("GET /rest/getXmlList2...");
		
		if(show) {
			List<MemoDto> list = new ArrayList();
			for(int i=0;i<50;i++) {
				list.add(new MemoDto(i,"A"+i));
			}
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}	
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);	
	}
	
	
}

```


---
#
---



MEMO
---
> - <br>

```
@RestController
@Slf4j
@RequestMapping("/memo")
public class RestController_02Memo {
	
	@Autowired
	private MemoService memoService;
	
	//메모확인전체
	@GetMapping(value="/getAll",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MemoDto> getAll() {
		log.info("GET /memo/getAll");
		return memoService.getAllMemo();
	}
	
	//메모확인(단건)
	@GetMapping("/get/{id}")
	public void get(@PathVariable int id) {
		log.info("GET /memo/get... "+id);
	}
	

	//메모쓰기
	@GetMapping("/add_rest_get")	//http://localhost:8080/app/memo/add
	public void add_get(MemoDto dto) {
		log.info("GET /memo/add_rest_get.."+dto);
		memoService.addMemo(dto);
	}
	//메모쓰기
	@PostMapping("/add_rest_post")	//http://localhost:8080/app/memo/add
	public void add(@RequestBody MemoDto dto) {
		log.info("POST /memo/add_rest_post.."+dto);
		memoService.addMemo(dto);
	}
	
	//메모수정
	@PutMapping("/put/{id}/{text}")
	public void put(MemoDto dto) {
		log.info("PUT /memo/put.."+dto);
		memoService.modifyMemo(dto);
	}
	
	@PutMapping("/put2")	//http://localhost:8080/app/memo/put2
	public void put2(@RequestBody MemoDto dto) {
		log.info("PUT /memo/put2.."+dto);
		memoService.modifyMemo(dto);
	}
	
	@PatchMapping("/patch/{id}/{text}")
	public void patch(MemoDto dto) {
		log.info("PATCH /memo/patch.."+dto);
	}
	//메모삭제
	@DeleteMapping("/remove/{id}")	//http://localhost:8080/app/memo/remove/{id}
	public void remove(@PathVariable int id) {
		log.info("DELETE /memo/remove.."+id);
		memoService.removeMemo(id);
	}
	
	
} 
```


---
#
---

VIEW
---
> RestTestController <br>

```
@Controller
@Slf4j
public class RestTestController {
	
	@Autowired
	private MemoService memoService;
	
	@GetMapping("/rest")
	public void rest() {
		log.info("GET /rest...");
	}
	
	@GetMapping("/add_get")	//http://localhost:8080/app/memo/add
	public void add(MemoDto dto) {
		log.info("GET /add_get.."+dto);
		memoService.addMemo(dto);
		//페이지 이동
	}
} 
```
> rest.jsp <br>

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	
	<style>
	.wrapper{
		display:flex;
		justify-content : left;
	}
	.wrapper>div{
			width : 200px; height : 500px;
			border : 1px solid;
			margin : 15px;
			text-align:center;
			
			overflow : auto;
	}
	.wrapper>div.result{
		width : 300px;
	}
	.wrapper>div>h1{
		border : 1px solid;
		margin-bottom : 15px;
	}
	.wrapper>div>div{
		border : 1px solid;
		margin : 5px;
		padding : 0px;
		opacity:.8;
	}
	.wrapper>div>div:hover{
		background-color:lightgray;
		opacity:1;
	}
	input {
		width : 55px;
	}
	</style>
</head>
<body>
<h1>REST TEST PAGE</h1>

	<div class="wrapper">
		
		<!--  -->
		<div class="xhr-block">
			<h1>XHR</h1>
						<div class="get">
				<h6>[ADD]동기 GET 요청</h6>
				<form method="get" action="${pageContext.request.contextPath}/add_get">
					<input name="id"  placeholder="id" />
					<input name="text" placeholder="text"  />
					<button>전송</button>  
				</form>
			</div>			
			<div class="get">
				<h6>[ADD]비동기 GET 요청</h6>
				<form name="xhrAsyncGetForm" method="" action="" onsubmit="return false">
					<input name="id"  placeholder="id" />
					<input name="text" placeholder="text"  />
					<button class="xhrAsyncGetBtn">전송</button>  
				</form>
			</div>
			<div class="post">
				<h6>[ADD]비동기 POST 요청</h6>
				<form name="xhrAsyncPostForm" method="" action="" onsubmit="return false">
					<input name="id"  placeholder="id" />
					<input name="text" placeholder="text"  />
					<button class="xhrAsyncPostBtn">전송</button>  
				</form>
			</div>
			<div class="put">
				<h6>[UPDATE]비동기 PUT 요청</h6>
				<form name="xhrAsyncPutForm" method="" action="" onsubmit="return false">
					<input name="id"  placeholder="id" />
					<input name="text" placeholder="text"  />
					<button class="xhrAsyncPutBtn">전송</button>  
				</form>
			</div>	
			<div class="petch">
				<h6>[UPDATE]비동기 PETCH 요청</h6>
				<form method="" action="" onsubmit="return false">
					<input name="id"  placeholder="id" />
					<input name="text" placeholder="text"  />
					<button>전송</button>  
				</form>
			</div>				
			<div class="delete">
				<h6>[DELETE]비동기 DELETE 요청</h6>
				<form name="xhrAsyncDeleteForm"  method="" action="" onsubmit="return false">
					<input name="id"  placeholder="id" />
					<input name="text" placeholder="text"  />
					<button class="xhrAsyncDeleteBtn">전송</button>  
				</form>
			</div>
			
		</div>
		
		<!--  -->
		<div class="ajax-block">
			<h1>AJAX</h1>
			<div></div>
		</div>
		
		<!--  -->
		<div class="fetch-block">
			<h1>FETCH</h1>
			<div></div>
		</div>
		
		
		<!--  -->
		<div class="axios-block">
			<h1>AXIOS</h1>
			<div class="get">
				<h6>[ADD]동기 GET 요청</h6>
				<form method="get" action="${pageContext.request.contextPath}/add_get">
					<input name="id"  placeholder="id" />
					<input name="text" placeholder="text"  />
					<button>전송</button>  
				</form>
			</div>			
			<div class="get">
				<h6>[ADD]비동기 GET 요청</h6>
				<form name="axiosAsyncGetForm" method="" action="" onsubmit="return false">
					<input name="id"  placeholder="id" />
					<input name="text" placeholder="text"  />
					<button class="axiosAsyncGetBtn">전송</button>  
				</form>
			</div>
			<div class="post">
				<h6>[ADD]비동기 POST 요청</h6>
				<form name="axiosAsyncPostForm" method="" action="" onsubmit="return false">
					<input name="id"  placeholder="id" />
					<input name="text" placeholder="text"  />
					<button class="axiosAsyncPostBtn">전송</button>  
				</form>
			</div>
			<div class="put">
				<h6>[UPDATE]비동기 PUT 요청</h6>
				<form name="axiosAsyncPutForm" method="" action="" onsubmit="return false">
					<input name="id"  placeholder="id" />
					<input name="text" placeholder="text"  />
					<button class="axiosAsyncPutBtn">전송</button>  
				</form>
			</div>	
			<div class="petch">
				<h6>[UPDATE]비동기 PETCH 요청</h6>
				<form method="" action="" onsubmit="return false">
					<input name="id"  placeholder="id" />
					<input name="text" placeholder="text"  />
					<button>전송</button>  
				</form>
			</div>				
			<div class="delete">
				<h6>[DELETE]비동기 DELETE 요청</h6>
				<form name="axiosAsyncDeleteForm"  method="" action="" onsubmit="return false">
					<input name="id"  placeholder="id" />
					<input name="text" placeholder="text"  />
					<button class="axiosAsyncDeleteBtn">전송</button>  
				</form>
			</div>	
		</div>
		
		
		<!-- SELECT BLOCK -->
		<div class="result">
			<h1>RESULT</h1>
			<div class="body">
			
			</div>
		</div>
	</div>


	<!--  
		XHR
	-->
	<script>
	
	</script>
	
	
	<!-- 
		AJAX
	 -->
	<script>
	</script>
	
	
	<script>
		//xhr 를 이용 비동기 요청
		
		//ajax를 이용 비동기 요청
		
		//fetch 를 이용 비동기 요청
		
		//axios 를 이용 비동기 요청
	
	</script>
	
	
	<!-- axios cdn-->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.6.8/axios.min.js" integrity="sha512-PJa3oQSLWRB7wHZ7GQ/g+qyv6r4mbuhmiDb8BjSFZ8NZ2a42oTtAq5n0ucWAwcQDlikAtkub+tPVCw4np27WCg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script>
			
		const projectPath='${pageContext.request.contextPath}';
		
		//------------------------
		//GET
		//------------------------
		const axiosAsyncGetBtn = document.querySelector('.axiosAsyncGetBtn');
		axiosAsyncGetBtn.addEventListener('click',function(){
			const axiosAsyncGetForm = document.axiosAsyncGetForm;
			
			axios.get(projectPath+"/memo/add_rest_get?id="+axiosAsyncGetForm.id.value+"&text="+axiosAsyncGetForm.text.value)
			.then(resp=>{console.log(resp);})
			.catch(err=>{console.log(err);});	
		})

		
		//------------------------		
		//POST
		//------------------------
		const axiosAsyncPostBtn = document.querySelector('.axiosAsyncPostBtn');
		axiosAsyncPostBtn.addEventListener('click',function(){
			//form
			const axiosAsyncPostForm = document.axiosAsyncPostForm;
			
			//header x
			const headers = {'Content-Type' : 'application/json'};
			//param
			const param = {
					"id" : axiosAsyncPostForm.id.value,
					"text" : axiosAsyncPostForm.text.value		
			}
			
			//요청
			axios.post(projectPath+"/memo/add_rest_post",param,headers)
			.then(resp=>{console.log(resp);})
			.catch(err=>{console.log(err);});	
		})
		
		//------------------------
		//PUT
		//------------------------
		const axiosAsyncPutBtn = document.querySelector('.axiosAsyncPutBtn');
		axiosAsyncPutBtn.addEventListener('click',function(){
			//form
			const axiosAsyncPutForm = document.axiosAsyncPutForm;
			
			//요청
			axios.put(projectPath+"/memo/put/"+axiosAsyncPutForm.id.value+"/"+axiosAsyncPutForm.text.value)
			.then(resp=>{console.log(resp);})
			.catch(err=>{console.log(err);});	
		})
		
		//------------------------
		//PETCH
		//------------------------
		
		//------------------------
		//DELETE
		//------------------------
		const axiosAsyncDeleteBtn = document.querySelector('.axiosAsyncDeleteBtn');
		axiosAsyncDeleteBtn.addEventListener('click',function(){
			//form
			const axiosAsyncDeleteForm = document.axiosAsyncDeleteForm;
			
			//요청
			axios.delete(projectPath+"/memo/remove/"+axiosAsyncDeleteForm.id.value)
			.then(resp=>{console.log(resp);})
			.catch(err=>{console.log(err);});	
		})		
		
	</script>
</body>
</html>

```

