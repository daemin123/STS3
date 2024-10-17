<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	
</head>
<body>

	<h1>FILE LIST</h1>
	BASE DIR : ${root} <br>
	<hr>
	<!-- SUB DIR -->
	<c:forEach  var="subdir" items="${root.listFiles()}">
		SUBDIR :${subdir.getPath()}
		<hr>
		
		<c:forEach var="file" items="${subdir.listFiles()}">
			<a class="item" href="javascript:void(0)" data-file="${file.getPath()}">
				${file}
			</a><br/>
		</c:forEach>
		<hr>
	</c:forEach>
	
	
	<hr>
	
	<script>
	
		const projectPath="${pageContext.request.contextPath}";
	
		const itemEls = document.querySelectorAll(".item");
		itemEls.forEach(item=>{
			
			item.addEventListener('click',function(){
				//console.log("CLICKED...",item);
				const filepath=item.getAttribute('data-file');
				console.log(filepath);
				location.href=projectPath+"/download?filepath="+encodeURIComponent(filepath);
			})
		})
	
	</script>

	
</body>
</html>
