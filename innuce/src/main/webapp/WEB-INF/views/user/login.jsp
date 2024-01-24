<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인 </title>
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
<script src="/js/jquery-3.7.1.min.js"></script>
<script>
	
	
	$(document).ready(function() {
		
		// 로그인 버튼 클릭
		$("#loginButton").on('click',function(ev) {
			
			// 아이디나 패스워드 공란
			if($('#user_id').val().length ==0 || $('#user_pw').val().length ==0) {
				alert("아이디/비밀번호를 입력해 주세요.");
			}//if
			else{
			$.ajax({
				type : 'post',
				url : 'loginresult',
				data : { 'user_id' : $("#user_id").val(), 'user_pw' : $("#user_pw").val() },
				dataType: 'json',
				success : function(response){			
						if(response.login_result == "로그인 성공."){
							alert(response.login_result);
							window.location.href = "main"
						}//if
						// 회원 탈퇴한지 10분이 안 지났을경우
						else if(response.login_result == "회원 복구 페이지로 이동합니다"){
							$("#login_main").html(
									"<div> 회원 복구를 진행하시겠습니까? </div>"+
									"<input id='restore_button' type='button' value = '회원 복구'>"
							) // html
							
							$("#restore_button").on("click",function(){
								window.location.href = "restoreuser"
								
							})//restore_button click
						}// else if
						// 잘못된 비밀번호 or 회원탈퇴 10분 지난 회원
						else{
							alert(response.login_result)
						}
					},//success1
				error : function(request, e){
					alert("코드=" + request.status + " 메시지=" + request.responseText + " 오류=" + e);
				}//error 1
			})//ajax1
			}//else
		})//login_button click
		
		$("#idpw_search_button").on("click",function(){
			location.href = "idpwfind";
		})
		
		
	}); // ready
	
	</script>
<style>

</style>
</head>

<body>
<!--  header -->
<header>
<%@ include file="../header/header.jsp"%>
</header>
<div id ="login_main">
	<div class="input">
		<div class="id">
			<div class="text">아이디</div>
			<input class ="mypage_input" id="user_id" name="user_id" type="text">
		</div>

		<div class="pw">
			<div class="text">비밀번호</div>
			<input class ="mypage_input" id="user_pw" name="user_pw" type="password">
		</div>

	</div>

	<div class="button">
		<input id="loginButton" type="button" value="로그인">&nbsp;&nbsp;
		<input id="idpw_search_button" type="button" value="아이디/비밀번호 찾기">
	</div>
 </div>
</body>

</html>