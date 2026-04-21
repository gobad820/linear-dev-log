<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html><html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
  </head>

  <body>
    <%
      pageContext.setAttribute("pageattr", "pageattr" );
      request.setAttribute("reqattr", "reqattr" );
      session.setAttribute("sessattr", "sessattr" );
      application.setAttribute("appattr", "appattr" );
    %>

    <%=pageContext.getAttribute("pageattr")%>
    <%=request.getAttribute("reqattr")%>
    <%=session.getAttribute("sessattr")%>
    <%=application.getAttribute("appattr")%>
  </body>
</html>
