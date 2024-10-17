# UPLOAD/DOWNLOAD


pom.xml
---
> - <br>
```
		<!-- FILE UP/DOWNLAOD -->
		<!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
		<dependency>
		    <groupId>commons-fileupload</groupId>
		    <artifactId>commons-fileupload</artifactId>
		    <version>1.4</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
		<dependency>
		    <groupId>commons-io</groupId>
		    <artifactId>commons-io</artifactId>
		    <version>2.11.0</version>
		</dependency>	
```

---
#
---

WebMvcConfig
---
> - <br>

```
@Configuration
@EnableWebMvc
@ComponentScans({
	@ComponentScan("com.test.app.controller"),
	@ComponentScan("com.test.app.restcontroller")
})
public class WebMvcConfig implements WebMvcConfigurer{

	@Bean
	public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(20971520); 			// 20MB	//전체 업로드 허용 사이즈
        multipartResolver.setMaxUploadSizePerFile(20971520); 	// 20MB	//파일 1개당 허용가능한 업로드 사이즈
        multipartResolver.setMaxInMemorySize(20971520); 		// 20MB //캐시 공간
        return multipartResolver;
	}
	//ViewResolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	
}
```

---
#
---

form.jsp
---
> - <br>

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1> UPLOAD FORM </h1>
<div>MSG : ${msg}</div>
<form action="${pageContext.request.contextPath}/upload/reqUpload" method="POST" enctype="multipart/form-data" >
	<input type="file" name="files"		multiple/>
	<input type="submit" value="upload" />
</form>
<hr>
<a href="${pageContext.request.contextPath}/upload/list">Show FILEList</a>


</body>
</html>

```


---
#
---

UploadController.java
---
> - <br>

```
@Controller
@Slf4j
@RequestMapping("/upload")
public class UploadController {

	private String uploadDir = "c:\\upload";
	
	@GetMapping("/form")
	public void uploadForm() {
		log.info("GET /upload/form..");
	}
	
	@PostMapping("/reqUpload")
	public String upload(@RequestParam("files")   MultipartFile[] uploadfiles,Model model) throws IllegalStateException, IOException
	{
		System.out.println("upload File Count : " +uploadfiles.length);
		
		
		//Upload Dir 미존재시 생성
		String path = uploadDir+File.separator+UUID.randomUUID();
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		for(MultipartFile file  : uploadfiles)
		{
			System.out.println("--------------------");
			System.out.println("FILE NAME : " + file.getOriginalFilename());
			System.out.println("FILE SIZE : " + file.getSize() + " Byte");
			System.out.println("--------------------");
			
			//파일명 추출
			String filename = file.getOriginalFilename();
			//파일객체 생성
			File fileobj = new File(path,filename);
			//업로드
			file.transferTo(fileobj);
			
		}
		
		model.addAttribute("msg","upload 성공!");
		return "upload/form";
	}
	
	@GetMapping("/list")
	public void ShowFileList(Model model)
	{
		File root = new File(uploadDir);
		File[] uuidDirs =  root.listFiles();
		for(File dir : uuidDirs)
		{
			System.out.println("------------------------");
			System.out.println("FOLDER : " + dir);
			System.out.println("------------------------");
			
			File subDir = new File(dir.getPath());
			for(File file :  subDir.listFiles())
			{
				System.out.println("FILE : " + file);
				
			}
		}
		
		model.addAttribute("rootDir",root);
		
		
	}
	
}
```


---
#
---

list.jsp
---
> - <br>

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1> LIST FILE </h1>
<div>UPLOAD DIR : ${rootDir} </div>

<div>
	<c:forEach items='${rootDir.listFiles()}' var='subdir'>
		<hr>
		FOLDER : ${subdir.getPath() }
		<hr>
		<c:forEach items='${subdir.listFiles()}' var='file'>
			
			<a class="item"	href="javascript:void(0)" data-dir="${subdir.getPath()}" data-file="${file.getName() }">
				${file} 
			</a><br/>

		</c:forEach>
	
	</c:forEach>

</div>

<script>
	const projectPath = '${pageContext.request.contextPath}';
	
	const item_els = document.querySelectorAll('.item');
	item_els.forEach((item)=>{
		
		item.addEventListener('click',function(){
			
			const filepath=  encodeURIComponent(item.getAttribute('data-dir')+"\\"+item.getAttribute('data-file'))
			alert(filepath);
			location.href=projectPath+"/download?path="+filepath;
		})
		
	})

</script>

</body>
</html>

```

---
#
---

DownloadController
---
> - <br>

|-|
|-|
|[참고](https://nankisu.tistory.com/9)|

```

@RestController
@Slf4j
public class RestController_03Download {
	
	@GetMapping(value="/download" ,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> Download(String path) throws UnsupportedEncodingException
	{
		
		log.info("RestController_03Download's Download Call.." + path);
		//FileSystemResource : 파일시스템의 특정 파일로부터 정보를 가져오는데 사용
		Resource resource = new FileSystemResource(path);
		//파일명 추출
		String filename = resource.getFilename();
		//헤더 정보 추가
		HttpHeaders headers = new HttpHeaders();
		//ISO-8859-1 : 라틴어(특수문자등 깨짐 방지)
		headers.add("Content-Disposition","attachment; filename="+new String(filename.getBytes("UTF-8"),"ISO-8859-1"));
		//리소스,파일정보가 포함된 헤더,상태정보를 전달
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
		
	}

}


```
