<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>아이디찾기 </title>
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

<script>
window.onload = function(){
$("#id_find_button").on('click',function(){
	if($("#user_name").val().length==0){
		alert("이름을 입력해주세요");
		$("#user_name").focus()
	}
	else if ($("#email").val().length==0){
		alert("이메일을 입력해주세요");
		$("#email").focus()
	}
	else{
	
		location.href ='idfindresult?user_name='+ $("#user_name").val()+'&email='+$("#email").val()	
	
	}//else
	}); //id_find_button click	
}// window onload
</script>
</head>
<body>
<!--  header -->
<header>
	<div class="logo-txt-cover">
   <%@ include file ="/WEB-INF/views/header/topBar.jsp" %>
  </div>
</header>
<div id="id_find_box">
이름<br>
<input class="mypage_input" type = 'text' id="user_name" name="user_name" ><br>
이메일<br>
<input class="mypage_input" type = 'email' id="email" name="email" ><br>
<input type="button" id="id_find_button" value="아이디 찾기">
</div>

</body>
</html>