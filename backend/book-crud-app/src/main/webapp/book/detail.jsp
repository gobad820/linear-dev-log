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

	<a href="${root }/books?action=list">목록으로</a>
	<table>
		<tr>
			<th>ISBN</th>
			<td>${book.isbn}</td>
		</tr>
		<tr>
			<th>도서명</th>
			<td>${book.title }</td>
		</tr>
		<tr>
			<th>저자</th>
			<td>${book.author }</td>
		</tr>
		<tr>
			<th>가격</th>
			<td>${book.price }</td>
		</tr>
	</table>

	<a href="${root }/books?action=delete&isbn=${book.isbn}">삭제</a>
</body>
</html>
