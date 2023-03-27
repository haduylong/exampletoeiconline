<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login test</title>
</head>
<body>
	<form id="form-1" method="post" action="login.html">
		<c:if test="${messageResponse != null }">
			<h1>Đăng nhập thành công</h1>
		</c:if>
		<input type="text" placeholder="user name" name = "pojo.name"/>
		<hr/>
		<input type="password" placeholder="user name" name = "pojo.passWord"/>
		<input type="submit" value="Submit" id="button-1"/>
	</form>
</body>
</html>