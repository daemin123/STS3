<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	
</head>
<body>
	
		<form action="${pageContext.request.contextPath}/board/post" method="post" enctype="multipart/form-data">
				<input name="title" placeHolder="title" /> <br>
				<input name="contents" placeHolder="contents" /> <br>
				<input name="writer" placeHolder="writer" /> <br>		
				<input type="file" name="files"  multiple/> <br>
				<button>게시글 작성</button>
		</form>
	
</body>
</html>
