<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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
	<%@ include file="/header.jsp"%>
	<h1>도서 상세 페이지</h1>

	<a href="${root }/books?action=list">목록으로</a>
	<table>
		<tr>
			<th>ISBN</th>
			<td>${requestScope.book.isbn}<%-- TODO: 39. book.isbn 값을 EL로 출력한다. --%></td>
		</tr>
		<tr>
			<th>도서명</th>
			<td>${requestScope.book.title}<%-- TODO: 40. book.title 값을 EL로 출력한다. --%></td>
		</tr>
		<tr>
			<th>저자</th>
			<td>${requestScope.book.author}<%-- TODO: 41. book.author 값을 EL로 출력한다. --%></td>
		</tr>
		<tr>
			<th>가격</th>
			<td>${requestScope.book.price}<%-- TODO: 42. book.price 값을 EL로 출력한다. --%>원</td>
		</tr>
	</table>

	<a href="${root}/books?action=delete&isbn=${requestScope.book.isbn}">삭제</a>
</body>
</html>
