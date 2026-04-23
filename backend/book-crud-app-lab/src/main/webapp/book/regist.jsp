<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>도서 등록 페이지</title>
</head>
<body>
<%-- header.jsp를 포함 --%>
<%@ include file="/header.jsp" %>

<h1>도서 등록 페이지</h1>

<%-- TODO: 23. form의 action 속성에 books 서블릿 URL을 EL로 작성, method는 POST --%>
<form action="" method="POST">
    <fieldset>
        <legend>도서 등록</legend>
        <%-- TODO: 24. hidden input의 value에 등록 처리를 위한 action 파라미터값 작성 --%>
        <input type="hidden" name="action" value="">

        <p>
            <label>ISBN <input type="text" name="isbn" placeholder="예: 9788966261208" required></label>
        </p>

        <p>
            <label>도서명 <input type="text" name="title" placeholder="도서명을 입력하세요" required></label>
        </p>

        <p>
            <label>저자 <input type="text" name="author" placeholder="저자를 입력하세요" required></label>
        </p>

        <p>
            <label>가격 <input type="number" name="price" placeholder="가격을 입력하세요" min="0" required></label>
        </p>

        <input type="submit" value="도서 등록"><br><br>
        <%-- TODO: 25. href에 목록 페이지로 이동하는 URL을 EL로 작성 --%>
        <a href="">목록으로 돌아가기</a>
    </fieldset>
</form>
</body>
</html>
