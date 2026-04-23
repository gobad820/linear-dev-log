<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<%@ include file="/header.jsp" %>
<h1>도서 목록 페이지</h1>

<a href="${root }">메인 화면으로</a>
<a href="${root}">등록하기</a>
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
    <c:forEach var="book" items="${requestScope.bookList}">
        <tr>
            <td>
                <a href="${root }/books?action=detail&isbn=${book.isbn}">${book.isbn}</a>
            </td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.price}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
