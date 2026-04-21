<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<style>
table {
    border-collapse: collapse;
}

td {
    border: 1px solid black;
}
</style>
</head>

<body>
    <%
    try {
        int dan = Integer.parseInt(request.getParameter("dan"));
    %>
    <table>
        <tbody>
            <%
            for (int i = 1; i < 10; i++) {
            %>
            <tr>
                <td><%=dan + " * " + i%></td>
                <td><%=dan * i%></td>
            </tr>
            <%
            }
            %>
        </tbody>
    </table>

    <%
    } catch (NumberFormatException e) {
        e.printStackTrace();
      out.println("<h1>dan 파라미터를 입력하세요.</h1>");
    }
    %>
</body>
</html>
