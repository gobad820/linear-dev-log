<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.servletContext.contextPath}"/>
<div>
	<span> <a href="${root }">홈으로</a> <a
            href="${root }/books?action=list">목록 조회</a>
	</span>
    <span>
		 <c:if test="${ empty sessionScope.loginMember}">
             <a href="${root }/auth?action=login-form">로그인</a>
         </c:if>
		 <c:if test="${not empty sessionScope.loginMember}">
             ${sessionScope.loginMember.id }님 환영합니다.
             <a href="${root }/auth?action=logout">로그아웃</a>
         </c:if>
	</span>
    <hr/>
</div>
