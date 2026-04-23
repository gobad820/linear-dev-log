<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메인 페이지</title>
</head>
<body>
<c:if test="${not empty sessionScope.alertMsg}">
    <script>
        alert('${sessionScope.alertMsg}');
    </script>
    <c:remove var="alertMsg" scope="session"/>
</c:if>
<%@ include file="/header.jsp" %>
<h1>메인 페이지입니다.</h1>
</body>
</html>
