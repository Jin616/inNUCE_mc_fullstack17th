<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<title>in NUCE</title>
<!-- ICON -->
<link rel="icon" href="/images/inNUCE_logo.png" />
<!-- 구글 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Brygada+1918:ital,wght@1,700&family=Nanum+Gothic:wght@400;700&display=swap" rel="stylesheet">
<!-- 구글 아이콘 -->
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
<!-- css -->
<link rel="stylesheet" type="text/css" href="/css/main.css">
<!-- 브라우저의 초기설정을 reset -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js" integrity="sha512-WFN04846sdKMIP5LKNphMaWzU7YpMyCU245etK3g/2ARYbPK9Ub18eG+ljU96qKRCWh+quCY7yefSmlkQw1ANQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.4/gsap.min.js" integrity="sha512-EZI2cBcGPnmR89wTgVnN3602Yyi7muWo8y1B3a8WmIv1J9tYG+udH4LvmYjLiGp37yHB7FfaPBo8ly178m9g4Q==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="/js/jquery-3.7.1.min.js"></script>
<script defer src="/js/main.js"></script>
<script>
$(document).ready(function(){
	if(${!empty sessionScope.login_user}){
		$("#btn2").on('click', function(){ 
		$.ajax({
			url: "logout",
			type: "post",
			success: function(response){
				location.reload(true);
			},
			error : function(request, e){
				alert("코드=" + request.status + " 메시지=" + request.responseText + " 오류=" + e);
			}
		});//ajax
	});// btn2 click
}//if
});//ready
</script>
</head>

<body>
<!-- HEADER -->
<header>

  <!-- 상단바 -->
		<div class="logo-txt-cover">
			
			
   		<input id="logo-txt" type=button  value='in NUCE' onclick="location.href='main'">  
 
   <div class="btn-cover">
   <ul class="button">
   	<li>
   		<c:choose>
   			<c:when test="${empty sessionScope.login_user}">
   				<input class="button" type=button id='btn1' value='SignUp' onclick="location.href='registermember'">
   			</c:when>
   			<c:otherwise>
   				<input class="button" type=button id='btn1' value="MyPage" onclick="location.href='mypage'">
   			</c:otherwise>
   		</c:choose>
   	</li>
   	<li>
   		<c:choose>
   			<c:when test="${empty sessionScope.login_user}">
   				<input class="button" type=button id='btn2' value='Login' onclick="location.href='login'">
   			</c:when>
   			<c:otherwise>	
   				<input class="button" type=button id='btn2' value='Logout' >
   			</c:otherwise>
   		</c:choose>	
   	</li>
   </ul>
   
   </div>
   
  </div><!-- logo-txt-cover -->
  
  <!-- 로고 -->
  <div class="logo-container">
  <a href="/main" class="logo-img">
   <img src="/images/inNUCE_logo.png"/> 
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

 
</header>
</body>
</html>