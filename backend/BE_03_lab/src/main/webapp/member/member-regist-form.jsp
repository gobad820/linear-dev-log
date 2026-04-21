<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <%
    // 페이지의 루트 경로를 구한다.
    String root = request.getContextPath();
    %>
    <div class="container">
        <h1>회원가입</h1>
        <form action="<%=root %>/member" method="post" class="m-3">
            <input type="hidden" name="action" value="member-regist" />

            <div class="mb-3 row">
                <label for="name" class="col-sm-2 col-form-label">이름</label>
                <div class="col-sm-10">
                    <input type="text" name="name" id="name" class="form-control" required />
                </div>
            </div>

            <div class="mb-3 row">
                <label for="email" class="col-sm-2 col-form-label">이메일</label>
                <div class="col-sm-10">
                    <input type="email" name="email" id="email" class="form-control" required />
                </div>
            </div>

            <div class="mb-3 row">
                <label for="password" class="col-sm-2 col-form-label">비밀번호</label>
                <div class="col-sm-10">
                    <input type="password" name="pass" id="pass" class="form-control" required />
                </div>
            </div>

            <button type="submit" class="btn btn-primary">등록</button>
        </form>



    </div>
</body>
</html>
