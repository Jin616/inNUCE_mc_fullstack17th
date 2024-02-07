<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인 </title>

<jsp:include page="/WEB-INF/views/header/head.jsp"/>

<!-- css -->
<link rel="stylesheet" type="text/css" href="/css/header.css">
<link rel="stylesheet" type="text/css" href="/css/mypage.css">

<script>
	
	
	$(document).ready(function() {
		
		// 로그인 버튼 클릭
		$("#loginButton").on('click',function(ev) {
			
			// 아이디나 패스워드 공란
			if($('#login_user_id').val().length ==0 || $('#login_user_pw').val().length ==0) {
				alert("아이디/비밀번호를 입력해 주세요.");
			}//if
			else{
			$.ajax({
				type : 'post',
				url : 'loginresult',
				data : { 'user_id' : $("#login_user_id").val(), 'user_pw' : $("#login_user_pw").val() },
				dataType: 'json',
				success : function(response){			
						if(response.login_result == "로그인 성공."){
							alert(response.login_result);
							window.location.href = "main"
						}//if
						// 회원 탈퇴한지 10분이 안 지났을경우
						else if(response.login_result == "회원 복구 페이지로 이동합니다"){
							$("#login_box").html(
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
	<div class="logo-txt-cover">
   <%@ include file ="/WEB-INF/views/header/topBar.jsp" %>
  </div>
  <%@ include file ="/WEB-INF/views/header/logo.jsp" %>
</header>


<div id ="login_box">
	<div class="login_input_box">
		<div class="id">
			<div class="text">아이디</div>
			<input class ="login_input" id="login_user_id" name="user_id" type="text">
		</div>

		<div class="pw">
			<div class="text">비밀번호</div>
			<input class ="login_input" id="login_user_pw" name="user_pw" type="password">
		</div>
		
	</div>
	<div class="login_button">
		<input id="loginButton" type="button" value="로그인">&nbsp;&nbsp;
		<input id="idpw_search_button" type="button" value="아이디/비밀번호 찾기">
	</div>
</div>
</body>

</html>