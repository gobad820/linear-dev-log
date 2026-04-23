<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>

</head>
<body>
    <%@ include file="/header.jsp"%>
    <div class="container">
        해당 요청은 존재하지 않는 경로입니다.
        <br/>
        <a href="<%-- TODO: 56. root 값을 EL로 출력한다. --%>">메인으로</a>
    </div>

</body>
</html>
