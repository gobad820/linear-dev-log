<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div>
		<%
		// TODO: 04. request 내장 객체를 이용해서 context 루트 경로를 설정해두자.
		String root = request.getContextPath();
		%>

		<h1>구구단 처리</h1>
		<!-- TODO: 05. 구구단 조회를 위한 페이지를 요청하는 링크(action=gugu-form)를 만들어보자. -->
		<a href="<%=root%>/main?action=gugu-form">구구단을 외자</a>
		<!-- END -->
	</div>
</body>
<script>
    const showAlertMessage = (msg) => {
        if (msg && msg != "null") {
            alert(msg);
        }
    }
    showAlertMessage(`<%=request.getAttribute("alertMsg")%>`)
  </script>
</html>
