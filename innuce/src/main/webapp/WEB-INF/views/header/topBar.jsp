<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<input id="logo-txt" type=button  value='in NUCE' onclick="location.href='main'">  

<div class="btn-cover">
<ul class="button">
	<li>
		<c:choose>
		<c:when test="${empty sessionScope.login_user}">
			<input class="button" type=button id='btn1' value='SignUp' onclick="location.href='registermember'">
		</c:when>
		<c:otherwise>
			<input class="button" type=button id='btn1' value="MyPage" onclick="location.href='mypage'">
		</c:otherwise>
	</c:choose>
</li>
<li>
	<c:choose>
		<c:when test="${empty sessionScope.login_user}">
			<input class="button" type=button id='btn2' value='Login' onclick="location.href='login'">
		</c:when>
		<c:otherwise>	
			<input class="button" type=button id='btn2' value='Logout' >
		</c:otherwise>
	</c:choose>	
	</li>
</ul>

</div>