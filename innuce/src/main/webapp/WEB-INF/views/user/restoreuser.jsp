<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<jsp:include page="/WEB-INF/views/header/head.jsp"/>

<link rel="stylesheet" type="text/css" href="/css/header.css">
<link rel="stylesheet" type="text/css" href="/css/mypage.css">

<script defer src="/js/main.js"></script>

<script>
$(document).ready(function() {
	
	$("#restore_button").on('click',function(ev) {

		// 아이디나 패스워드 공란
		if($('#user_id').val() == null || $('#user_pw').val()==null) {
			alert("아이디/비밀번호를 입력해 주세요.");
		}//if
		
		$.ajax({
			type : 'post',
			url : 'restoreresult',
			data : { 'user_id' : $("#user_id").val(), 'user_pw' : $("#user_pw").val() },
			dataType: 'json',
			success : function(response){
					
					if(response.restore_result == "회원 복구에 성공했습니다."){
						alert(response.restore_result+" 메인페이지로 이동합니다");
						window.location.href = "main"
					}//if
					else{
						alert(response.restore_result)
					}
					
			},//success
			error : function(request, e){
				alert("코드=" + request.status + " 메시지=" + request.responseText + " 오류=" + e);
			}//error 
		})//ajax
	})//on
})//ready
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
<div id ="restore_main">
	
	<div class="input">
		<div class="id">
			<div class="text">복구할 아이디</div>
			<input class ="mypage_input" id="user_id" name="user_id" type="text">
		</div>

		<div class="pw">
			<div class="text">비밀번호</div>
			<input class ="mypage_input" id="user_pw" name="user_pw" type="password">
		</div>

	</div>

	<div class="button">
		<input id="restore_button" type="button" value="복구하기">&nbsp;&nbsp;
		
	</div>
	
 </div>

</body>
</html>