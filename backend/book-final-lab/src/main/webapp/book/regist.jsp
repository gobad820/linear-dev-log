<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 등록 페이지</title>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<h1>도서 등록 페이지</h1>

	<form action="<%-- TODO: 54. root 값을 EL로 출력한다. --%>/books" method="post">
		<fieldset>
			<legend>도서 등록</legend>
			<input type="hidden" name="action" value="regist">

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
			<a href="<%-- TODO: 55. root 값을 EL로 출력한다. --%>/books?action=list">목록으로 돌아가기</a>
		</fieldset>
	</form>
</body>
</html>
