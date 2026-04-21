<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2>섭씨 → 켈빈  변환 결과</h2>
    <p>입력: <%=request.getAttribute("celsius") %>°C</p>
	<p>결과: <%=request.getAttribute("kelvin")%>°F</p>
</body>
</html>
