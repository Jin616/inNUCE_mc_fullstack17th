<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>비밀번호찾기 </title>
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
	$("#pw_find_button").on('click',function(){
		//아이디 입력 안된경우
		if($("#user_id").val().length ==0){
			alert("아이디를 입력해주세요")
		}
		//이름 입력 안된경우
		else if($("#user_name").val().length ==0){
			alert("이름을 입력해주세요")
		}
		//이메일 입력 안된경우
		else if($("#email").val().length ==0){
			alert("이메일을 입력해주세요")
		}
		else{
			$.ajax({
				type : 'post',
				url : 'pwfindresult',
				data : { 'user_id' : $("#user_id").val(), 'user_name' : $("#user_name").val(), 'email' : $("#email").val() },
				dataType: 'json',
				success : function(response){
					alert(response.pw_find_result);
					if(response.pw_find_result == "가입 당시 입력하신 이메일로 비밀번호가 발송되었습니다."){
						alert("로그인 페이지로 이동합니다.")
						location.href="login"
					}
				}//success
			})//ajax
		}// else
	})// pw_find_button click

}// window onload
</script>
</head>
<body>
<!--  header -->
<header>
<%@ include file="../header/header.jsp"%>
</header>
<div id="pw_find_main">
아이디<br>
<input class="mypage_input" type = 'text' id="user_id" name="user_id" ><br>
이름<br>
<input class="mypage_input" type = 'text' id="user_name" name="user_name" ><br>
이메일<br>
<input class="mypage_input" type = 'email' id="email" name="email" ><br>
<input type="button" id="pw_find_button" value="비밀번호 찾기">
</div>

</body>
</html>