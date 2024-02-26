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
	<div class="logo-txt-cover">
   <%@ include file ="/WEB-INF/views/header/topBar.jsp" %>
  </div>
  <%@ include file ="/WEB-INF/views/header/logo.jsp" %>
</header>

<div id="pw_find_box">
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