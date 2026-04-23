<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 상세 페이지</title>
<style type="text/css">
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<%-- header.jsp를 포함 --%>
	<%@ include file="/header.jsp" %>

	<h1>도서 상세 페이지</h1>

	<%-- TODO: 17. href에 목록 조회 action을 포함한 URL을 EL로 작성 --%>
	T
	<a href="">목록으로</a>
	<table>
		<tr>
			<th>ISBN</th>
			<td><%-- TODO: 18. request scope의 book 객체에서 ISBN을 EL로 출력 --%></td>
		</tr>
		<tr>
			<th>도서명</th>
			<td><%-- TODO: 19. 도서명 EL 출력 --%></td>
		</tr>
		<tr>
			<th>저자</th>
			<td><%-- TODO: 20. 저자 EL 출력 --%></td>
		</tr>
		<tr>
			<th>가격</th>
			<td><%-- TODO: 21. 가격 EL 출력 --%></td>
		</tr>
	</table>

	<%-- TODO: 22. href에 삭제 action과 isbn 파라미터를 포함한 URL을 EL로 작성 --%>
	<a href="">삭제</a>
</body>
</html>
