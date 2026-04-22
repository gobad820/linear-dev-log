<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2>화씨 → 섭씨 변환 결과</h2>
    <p>입력: <%=request.getAttribute("fahrenheit") %>°C</p>
	<p>결과: <%=request.getAttribute("celsius")%>°F</p>
</body>
</html>
