<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>평균 점수</title>
</head>
<body>
    <h2>평균 점수</h2>
    <p>국어: <%= request.getAttribute("kor") %>점</p>
    <p>영어: <%= request.getAttribute("eng") %>점</p>
    <p>수학: <%= request.getAttribute("math") %>점</p>
    <p>평균: <%= request.getAttribute("average") %>점</p>
</body>
</html>
