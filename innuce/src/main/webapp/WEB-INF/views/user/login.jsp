<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

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
				data : { 'user_id' : $("#login_user_id").val(), 'user_pw' : $("#login_user_pw").val()
					, 'rememberId' : $('#rememberId').prop('checked')},
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
		
		$("#id_search_button").on("click",function(){
			location.href = "idfind";
		})
		$("#pw_search_button").on("click",function(){
			location.href = "pwfind";
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
	<div class="title"><p>in NUCE 로그인<p></div>
	<div class="login_input_box">
		<div class="id">
			<div class="text"></div>
			<input class ="login_input" id="login_user_id" value="${cookie.id.value }" name="user_id" type="text" placeholder="ID" required maxlength='25'>
		</div>

		<div class="pw">
			<div class="text"></div>
			<input class ="login_input" id="login_user_pw" name="user_pw" type="password" placeholder="Password" required maxlength='25'>
		</div>
		
	</div>
	
	<div class="checkbox-cover">
	<input id='rememberId' type="checkbox" name='rememberId' ${empty cookie.id.value ? "" :"checked"}/>&nbsp;아이디 저장
	</div>
	
	<div class="login_button">
		<input id="loginButton" type="button" value="로그인">&nbsp;&nbsp;
		<div class='idpw-cover'>
			<input id="id_search_button" type="button" value="아이디 찾기">
			<input id="pw_search_button" type="button" value="비밀번호 찾기">
		</div>
	</div>
</div>

</body>

</html>