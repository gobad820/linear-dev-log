<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>

	<%-- header.jsp를 포함 --%>
	<%@ include file="/header.jsp" %>
	<h1>메인 페이지입니다.</h1>
	<a href="${pageContext.request.contextPath}/books?action=list">목록 페이지</a>
</body>
</html>
