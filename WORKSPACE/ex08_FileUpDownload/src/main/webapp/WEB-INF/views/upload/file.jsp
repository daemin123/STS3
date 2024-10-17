<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	
</head>
<body>

	<form action="${pageContext.request.contextPath}/upload/file" method="post" enctype="multipart/form-data" >
		<input type="file" name="singleFile" />
		<button>UPLOAD</button>
	</form>
	
</body>
</html>
