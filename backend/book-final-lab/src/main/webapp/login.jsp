<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<c:if test="${not empty sessionScope.alertMsg}">
		<script>
			alert("${sessionScope.alertMsg}");
		</script>
		<c:remove var="alertMsg" scope="session" />
	</c:if>
	<h1>로그인</h1>

	<form action="${root }/auth" method="post">
		<input type="hidden" name="action" value="login">
		<p>
			<label>아이디 <input type="text" name="id" value="${cookie.savedId}"></label>
		</p>
		<p>
			<label>비밀번호 <input type="password" name="password"></label>
		</p>
		<p>
			<label>아이디 기억하기 <input type="checkbox" name="remember-id"  ${empty cookie.savedId ? "" : "checked"}>
			</label>
		</p>
		<input type="submit" value="로그인">
	</form>
</body>
</html>
