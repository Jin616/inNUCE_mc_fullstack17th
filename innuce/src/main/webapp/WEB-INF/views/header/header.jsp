<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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


  <!-- 상단바 -->
		<div class="logo-txt-cover">
			
					<!-- 채팅바 -->
<%-- 		<jsp:include page="/WEB-INF/views/header/chattingroomlist.jsp"/> --%>
		<%@ include file ="/WEB-INF/views/header/chattingroomlist.jsp" %>
		
		
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

<!-- 검색바 -->
  <jsp:include page="/WEB-INF/views/header/searchBar.jsp"/>
  
