<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>구구단을 외자</title>
<link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet" />
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
        <h1>구구단을 외자.</h1>
        <%
        // 페이지의 루트 경로를 구한다.
        String root = request.getContextPath();
        %>
        <div>
                <h1>알고싶은 단을 입력하세요.</h1>
                <!--TODO: 07. 서버로 구구단 요청을 위한 form을 만들어보세요.-->
                <!-- action=gugu로 구구단을 요청. 파라미터는 dan으로 한다.-->
                <form action="<%=root%>/main">
                        <input type="hidden" name="action" value="gugu" />
                        <input type="number" name="dan" />
                        <button>조회</button>
                </form>
                <!--END-->
        </div>

</body>
<!-- TODO: 12. <script>에서 attribute에 alertMsg가 있다면 alert으로 출력하세요. -->
<!--  메시지 자체에 ""가 포함 되기도 하기 때문에 ``을 이용해서 묶어주자. -->
<script>
    const showAlertMessage = (msg) => {
        if(msg && msg !== "null" && msg.trim() !== ""){
            alert(msg);
        }
    }
    showAlertMessage(`<%=request.getAttribute("alertMsg")%>`);
</script>
<!--END-->
</html>
