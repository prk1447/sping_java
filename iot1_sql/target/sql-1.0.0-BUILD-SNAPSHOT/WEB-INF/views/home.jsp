<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  ${sessionScope.user}
</h1>
<c:if test="${(sessionScope.user != null) && sessionScope.user.userRoleLevel == '1' }">
<input type="button" value="sqlAdmin"/>
</c:if>
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
