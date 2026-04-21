<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 결과</title>
<link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet" />
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
        <div>
                <%
                String root = request.getContextPath();
                %>
                <!--TODO: 10. 구구단 결과를 화면에 반영하세요.-->
                <%
                List<String> result = (List) request.getAttribute("result");
                if (result != null) {
                    for (String s : result) {
                %>
                        <p><%= s %></p>
                <%
                    }
                }
                %>
                <!--END-->
                <a href="<%=root%>/main?action=gugu-form">다시하기</a>
        </div>
</body>
<script>
    const showAlertMessage = (msg) => {
        if(msg && msg !== "null" && msg.trim() !== ""){
            alert(msg);
        }
    }
    showAlertMessage(`<%=request.getAttribute("alertMsg")%>`);
</script>
</html>
