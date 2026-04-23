<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 목록 페이지</title>
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

	<h1>도서 목록 페이지</h1>

	<a href="${root }">메인 화면으로</a>
	<a href="${root }/books?action=regist-form">등록하기</a>
	<table>
		<thead>
			<tr>
				<th>ISBN</th>
				<th>도서명</th>
				<th>저자</th>
				<th>가격</th>
			</tr>
		</thead>
		<tbody>
			<%-- TODO: 11. items 속성에 컨트롤러가 request scope에 저장한 도서 목록 속성을 EL로 작성 --%>
			<c:forEach var="book" items="${bookList}">
				<tr>
					<%-- TODO: 12. href에 상세조회 action과 isbn 파라미터를 포함한 URL을 EL로 작성 --%>
					<td><a href="${root }/books?action=detail&isbn=${book.isbn}">${book.isbn}
							<%-- TODO: 13. ISBN EL 출력 --%></a></td>
					<td>
						${book.title}
					</td>
					<td>${book.author}
							<%-- TODO: 15. 저자 EL 출력 --%></td>
					<td>${book.price}
							<%-- TODO: 16. 가격 EL 출력 --%></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
