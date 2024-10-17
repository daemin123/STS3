<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>MEMO</title>
	
</head>
<body class="m-2 p-2">
		
		<h1>LOGIN PAGE</h1>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<input name="username"><br>
			<input name="password"><br>
			<input type="checkbox" name="remember-me" /> REMEMBER_ME <br>
			<button>LOGIN</button>
			
			<!-- CSRF TOKEN 전달 -->
			<%-- <input type="hidden" name="_csrf" value="${_csrf.token}" /> --%>
		</form>
		
		MSG : ${msg}<br>
		
		
</body>
</html>
