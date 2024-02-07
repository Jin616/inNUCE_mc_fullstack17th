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