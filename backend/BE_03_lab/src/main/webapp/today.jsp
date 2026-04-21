<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- sciprlet: java code 삽입 --%>
	<%
	LocalDateTime now = LocalDateTime.now();
	out.println("현재 시각: " + now);
	%>

	<%!private String getFormatted(LocalDateTime now) {
		return String.format("%tY-%<tm-%<td", now);
	}%>
	<br>
	<%= getFormatted(now) %>
	<!-- 잘 되고 있나요? -->
</body>
</html>
