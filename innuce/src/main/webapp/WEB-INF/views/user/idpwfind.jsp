<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>아이디 비밀번호 찾기 </title>
<jsp:include page="/WEB-INF/views/header/head.jsp"/>

<link rel="stylesheet" type="text/css" href="/css/header.css">
<link rel="stylesheet" type="text/css" href="/css/mypage.css">
<script>
window.onload = function(){
	
	let id_find_button = document.getElementById("id_find_button")
	let pw_find_button = document.getElementById("pw_find_button")
	// 아이디 찾기 버튼 눌렀을때
	$("#id_find_button").on("click",function(){
		location.href ="idfind"
	})//id_find_button click
	
	$("#pw_find_button").on("click",function(){
		location.href ="pwfind"
	})//pw_find_button click
}// onload
</script>
</head>
<body>
<!--  header -->
<header>
	<div class="logo-txt-cover">
   <%@ include file ="/WEB-INF/views/header/topBar.jsp" %>
  </div>
  <%@ include file ="/WEB-INF/views/header/logo.jsp" %>
</header>

<div id="find_box">

	<div class="button">
			<input id="id_find_button" type="button" value="아이디 찾기">&nbsp;&nbsp;
			<input id="pw_find_button" type="button" value="비밀번호 찾기">
	</div>

</div>

</body>
</html>