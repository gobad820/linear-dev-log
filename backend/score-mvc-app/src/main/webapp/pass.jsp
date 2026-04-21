<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>합불 판정</title>
</head>
<body>
    <h2>합불 판정</h2>
    <p>국어: <%= request.getAttribute("kor") %>점</p>
    <p>영어: <%= request.getAttribute("eng") %>점</p>
    <p>수학: <%= request.getAttribute("math") %>점</p>
    <p>결과: <%= request.getAttribute("pass") %></p>
</body>
</html>
