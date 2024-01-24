<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<c:set var="SignUpMyPage" value="${sessionScope.id==null ? 'SignUp' : 'MyPage' }" />
<c:set var="loginOut" value="${sessionScope.id==null ? 'login' : 'logout' }" />

<!DOCTYPE html>
<html>
<body>

  <!-- 상단바 -->
		<div class="logo-txt-cover">
		<!-- 채팅바 -->
		<div class="badges">
			<div class="title">실시간 인기 토론방</div>
			<div class="badge">
				<input type=button value="채팅방1"> 
			</div>
			<div class="badge">
				<input type=button value="채팅방1"> 
			</div>
			<div class="badge">
				<input type=button value="채팅방1"> 
			</div>
			<div class="badge">
				<input type=button value="채팅방1"> 
			</div>
			
		</div>
		
   <form action="/main">
   	<input id="logo-txt" type=submit value='in NUCE'> 
   </form>
   
   <div class="btn-cover">
   
   <ul class="button">
   	<li>
   	<form action="javascript:void(0)">
       <input class="button" type=button id='btn1' value='${SignUpMyPage }'>
   	</form>
   	</li>
   	
   	<li>
   	<form action="javascript:void(0)">
   		<input class="button" type=button id='btn2' value="${loginOut }">
   	</form>
   	</li>
   </ul>
   
   </div>
   
  </div><!-- logo-txt-cover -->
  
  <!-- 로고 -->
  <div class="logo-container">
  <a href="/main" class="logo-img">
   <img src="/images/inNUCE_logo_header.png"/> 
  </a>
  
   <div class="logo-ex">
     <span>
     	in NUCE <br/>
     </span>
     
     <span>
     	[인누케]<br/>
     	호두(껍데기) 안에 라는 뜻의 라틴어<br/>
     </span>
     
     <span>
     	= 한 마디로 요약하면<br/>
     </span>
   </div>

 </div>
 




</body>
</html> --%>