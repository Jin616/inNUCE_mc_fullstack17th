<%@page import="com.mc.innuce.domain.user.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>

<jsp:include page="/WEB-INF/views/header/head.jsp"/>
<link rel="stylesheet" type="text/css" href="/css/header.css">
<link rel="stylesheet" type="text/css" href="/css/mypage.css">

<script defer src="/js/main.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<style>

</style>
</head>
<body>
<script>
	$(document).ready(function(){
		
	
		
		if(${empty sessionScope.login_user})
		{
			alert("세션이 만료되었습니다. 메인화면으로 이동합니다.");
		    location.href = "main";
		}
		else{
			
			// 회원 관리 눌렀을때
			 $("#user_manage").on('click',function(){
			 	
				 // 좌측 네비게이터 바에 css 바꾸기
				 $("#user_manage").attr("clicked","yes");
				 $("#restore_manage").attr("clicked","none");
				 
				
				 
				 $.ajax({ 
						url : "usermanage",
						type : 'get',
						dataType : "json",
						success : function(response){
							
							// 페이지 내용 바꾸기
							$("#adminPage_main").html(response.member_table)//html
							
							
						}//success
				 })// ajax
				 
				 
			})//user_manage_click
			
			// 탈퇴 회원 관리 눌렀을때
			 $("#restore_manage").on('click',function(){
			 	
				 // 좌측 네비게이터 바에 css 바꾸기
				 $("#user_manage").attr("clicked","none");
				 $("#restore_manage").attr("clicked","yes");
				 
				
				 
				 $.ajax({ 
						url : "userrestoremanage",
						type : 'get',
						dataType : "json",
						success : function(response){
							
							// 페이지 내용 바꾸기
							$("#adminPage_main").html(response.member_table)//html
							
							
						}//success
				 })// ajax
				 
				 
			})//user_manage_click
			// 채팅방 관리 버튼 눌렀을때
		
		}// else
		})// on
	
</script>
<!--  header -->
<header>
	<div class="logo-txt-cover">
	<%@ include file ="/WEB-INF/views/header/topBar.jsp" %>
	<!--  좌측 네비바 -->
	<div id = "myPage_navigater" >
		<div class='title'>마이페이지</div>
		<button id="user_manage" clicked="none"><span class="material-symbols-outlined">manage_accounts</span>&nbsp;회원 관리</button>
		<button id="restore_manage" clicked="none"><span class="material-symbols-outlined">manage_accounts</span>&nbsp;탈퇴 회원 관리 </button>
		
	</div>
	
	<%@ include file="/WEB-INF/views/header/chattingroomlist.jsp"%>
  	</div>
	<%@ include file ="/WEB-INF/views/header/logo.jsp" %>
</header>

<main style="position: realtive;">
<!--  마이페이지 내용 -->
<div id = "adminPage_main" style="position: absolute; margin-left: 60px;"></div>

</main>
</body>
</html>