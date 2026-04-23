<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath }" />

<div>
	<span>
	 <a href="${root}">홈으로</a>
	 <a href="${root }/books?action=list">목록 조회</a>
	</span>
	<hr />
</div>
