<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>아이디 비밀번호 찾기 </title>
<!-- ICON -->
<link rel="icon" href="/images/inNUCE_logo.png" />
<!-- 구글 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Brygada+1918:ital,wght@1,700&family=Nanum+Gothic:wght@400;700&display=swap"
	rel="stylesheet">
<!-- 구글 아이콘 -->
<link
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined"
	rel="stylesheet" />
<!-- css -->
<link rel="stylesheet" type="text/css" href="/css/main.css">
<link rel="stylesheet" type="text/css" href="/css/mypage.css">
<!-- 브라우저의 초기설정을 reset -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js"
	integrity="sha512-WFN04846sdKMIP5LKNphMaWzU7YpMyCU245etK3g/2ARYbPK9Ub18eG+ljU96qKRCWh+quCY7yefSmlkQw1ANQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.4/gsap.min.js"
	integrity="sha512-EZI2cBcGPnmR89wTgVnN3602Yyi7muWo8y1B3a8WmIv1J9tYG+udH4LvmYjLiGp37yHB7FfaPBo8ly178m9g4Q=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

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
<%@ include file="../header/header.jsp"%>
</header>
<div id="find_main">
<div class="button">
		<input id="id_find_button" type="button" value="아이디 찾기">&nbsp;&nbsp;
		<input id="pw_find_button" type="button" value="비밀번호 찾기">
</div>
</div>
</body>
</html>