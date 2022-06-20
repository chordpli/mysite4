<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="header" class="clearfix">
	<h1>
		<a href="/mysite4/main">MySite</a>
	</h1>

	<c:if test="${authUser != null }">
		<ul>
			<li>${authUser.name } 님 안녕하세요^^</li>
			<li><a href="/mysite4/user/logout" class="btn_s">로그아웃</a></li>
			<li><a href="/mysite4/user/modifyform" class="btn_s">회원정보수정</a></li>
		</ul>
	</c:if>
	<c:if test ="${authUser == null }">
		<ul>
			<li><a href="/mysite4/user/loginform" class="btn_s">로그인</a></li>
			<li><a href="/mysite4/user/joinform" class="btn_s">회원가입</a></li>
		</ul>
	</c:if>

</div>