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
			
			 $("#info_change").on('click',function(){
			 
				var user_pw_length = '${sessionScope.login_user.user_pw}'.length;
				var user_pw_hide = '*'.repeat(user_pw_length)
				// 마이페이지 내용 바꾸기
				$("#myPage_main").html(
				"<div id ='user_info'>"+ 
				"<div class='info_text'>내 아이디</div>"+
				"<input class ='user_info_input' type='text' name='user_id' id='user_id'  value='${sessionScope.login_user.getUser_id()}' readonly><br>"+
				"<div class='info_text'>이메일</div>"+
				"<input class ='user_info_input' type='email' name='email' id='email'  placeholder='${sessionScope.login_user.getEmail()}'><br>"+
				"<div class='info_text'>핸드폰 번호</div>"+
				"<input class ='user_info_input'  type='tel' name='phone' id='phone'  placeholder='${sessionScope.login_user.getPhone()}'><br>"+
				"<div class='info_text'>비밀번호 </div> "+
				"<input class ='user_info_input'  id = 'user_pw' type='password' name ='user_pw' placeholder='${sessionScope.login_user.getUser_pw()}'><br>"+
				"<div class='info_text'>비밀번호 재확인 </div> "+
				"<input class ='user_info_input'  id='user_pw_re' type='password'  defaultValue='${sessionScope.login_user.getUser_pw()}' ><br>"+
				"<div  class='info_text'>주소 </div> "+
				"<input class ='user_info_input'  type='text' name='address' id='address' placeholder='${sessionScope.login_user.getAddress()}' defaultValue='${sessionScope.login_user.getAddress()}'>"+ 
				" <input id= 'address_search_button' type='button' value='주소찾기'><br>"+
				"<input id= 'changeButton' type='button' value='수정하기'>"+
				"</div>"
				)//html 
				
				// 비밀번호를 직접 숫자로 보여주지 않기 위한것
				$("#user_pw").attr("placeholder",user_pw_hide)
				$("#user_pw_re").attr("placeholder",user_pw_hide)
				
				// 주소 찾기 버튼 눌렀을떄
				$(address_search_button).on("click",function(){
					// 카카오 주소찾기 api
		   			 new daum.Postcode({
		       			oncomplete: function(data) {
		             	$("#address").val(data.roadAddress)
		        		}
				    }).open();
	
				})// address_search_button click
				
				// 수정하기 버튼 눌렀을 때
				$("#changeButton").on('click',function(ev){
					let phoneRegex =  new RegExp("01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}");  
					let emailRegex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');	
					// 비밀번호와 비밀번호 재확인 값이 다른경우
					if($("#user_pw").val() !=$("#user_pw_re").val()){
						alert("같은 비밀번호를 입력해주세요");
						$("#user_pw_re").focus();
						ev.preventDefault();
					}//if
					// 핸드폰 번호 형식이 다른경우
					else if($("#phone").val().trim().length != 0 && ! phoneRegex.test($("#phone").val()) ){
						alert("핸드폰 번호를 010-XXXX-XXXX 의 형식으로 입력해주세요");
						$("#phone").focus();
						ev.preventDefault();
					}//else if
					// 이메일 형식이 다른경우
					else if($("#email").val().trim().length != 0 && ! emailRegex.test($("#email").val()) ){
						alert("올바른 이메일 형식을 입력해 주세요");
						$("#email").focus();
						ev.preventDefault();
					}//else if
					else{
						$.ajax({ 
							url : "infochange",
							data : {'email' : $("#email").val() , 'user_pw': $("#user_pw").val() ,'address': $("#address").val(),'phone': $("#phone").val()  },
							type : 'post',
				
							success : function(response){
								alert("회원정보 수정이 완료되었습니다.")
								location.reload(true)
							}//success
						});//ajax
					}//else
				})//changeButton click
			})//info_change_click
			
			// 회원탈퇴 버튼 눌렀을때
			$("#delete").on('click',function(){
				// 마이페이지 내용 바꾸기
				$("#myPage_main").html(
					"<div> 정말로 회원을 탈퇴하시겠습니까? </div>"+
					"<div> 회원 탈퇴 후 2분내로 로그인 하시면 복구하실 수 있습니다.</div> "+
					"<input id= 'deleteButton' type='button' value='탈퇴하기' >"
				)//html
				// 탈퇴하기 버튼 눌렀을때
				$("#deleteButton").on('click',function(){
					$.ajax({ 
						url : "deleteuser",
						type : 'post',
						success : function(response){
							alert("회원탈퇴가 완료됐습니다.")
							location.href = "main"
							
						}//success
					});//ajax
				})//delete button click
			})// delete click
		}// else
		})// on
	
</script>
<!--  header -->
<header>
	<div class="logo-txt-cover">
	<%@ include file="/WEB-INF/views/header/chattingroomlist.jsp"%>
  <%@ include file ="/WEB-INF/views/header/topBar.jsp" %>
  </div>

</header>

<!--  좌측 네비바 -->
<div id = "myPage_navigater" >
	<button id="info_change">  회원 정보 수정</button><hr >
	<button id="my_scrap"> 스크랩한 기사 </button><hr >
	<button id="my_chatting"> 참여중인 채팅방</button><hr >
	<button id="delete"> 회원 탈퇴</button><hr >
</div>
<!--  마이페이지 내용 -->
<div id = "myPage_main" > </div>

</body>
</html>