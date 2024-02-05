<%@page import="java.util.List"%>
<%@page import="com.mc.innuce.domain.news.dto.NewsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>in NUCE</title>

<jsp:include page="/WEB-INF/views/header/head.jsp"/>

<!-- css -->
<link rel="stylesheet" type="text/css" href="/css/main.css">


</head>

<body>




<!-- HEADER -->
<header>

<%@ include file ="/WEB-INF/views/header/header.jsp" %>

</header>

<!-- nav -->
<nav>
<%@ include file ="/WEB-INF/views/nav/nav.jsp" %>
</nav>

<!-- MAIN -->
<main>
	<div class="total-container">
	<!-- 키워드 -->
	<div class="news-container">
	
		<div class="news-content-cover" id="keyword">
		
			<div class="sub-menu">
				<ul class='menu'>
					<li class="tab-link current" data-tab="tab-1-keyword">
		<!-- 				<form action="javascript:void(0)">
							<input type=button value="키워드1">
						</form> -->
						키워드1
					</li>
					<li class="tab-link" data-tab="tab-2-keyword">
<!-- 						<form action="javascript:void(0)">
							<input type=button value="키워드2">
						</form> -->
						키워드2
					</li>
					<li class="tab-link" data-tab="tab-3-keyword">
<!-- 						<form action="javascript:void(0)">
							<input type=button value="키워드3">
						</form> -->
						키워드3
					</li>
				</ul>
			</div>
			
			<div class="main-title">오늘의 키워드 뉴스</div>
			
		<div id="tab-1-keyword" class="tab-content current">
      
      <c:forEach var="index" begin='1' end='5'>
      	<div class='content-cover'>
      		<img id='img-${index}' alt='images' />
      		<div class='a'>
      			<div id='${index }-1' class='date'></div>
      			<div id='${index }-2' class='main'></div>
      			<div id='${index }-3' class='cont'><br/></div>
      		</div>
      			
     			<div class='bookmark-cover'>
     				<i class='fa-solid fa-bookmark'></i>
     			</div>
      			
      	</div>
      </c:forEach>
      
      
      </div> <!-- tab-1-keyword -->
      
			<div id="tab-2-keyword" class="tab-content">
      
      <c:forEach var="index" begin='1' end='5'>
      	<div class='content-cover'>
      		<img id='img-${index }' alt='images' />
      		<div class='a'>
      			<div id='${index }-1' class='date'></div>
      			<div id='${index }-2' class='main'></div>
      			<div id='${index }-3' class='cont'><br/></div>
      		</div>
     			<div class='bookmark-cover'>
     				<i class='fa-solid fa-bookmark'></i>
     			</div>
      	</div>
      </c:forEach>
      </div> <!-- tab-2-keyword -->
      
      
			<div id="tab-3-keyword" class="tab-content">
			
      <c:forEach var="index" begin='1' end='5'>
      	<div class='content-cover'>
      		<img id='img-${index}' alt='images' />
      		<div class='a'>
      			<div id='${index }-1' class='date'></div>
      			<div id='${index }-2' class='main'></div>
      			<div id='${index }-3' class='cont'><br/></div>
      		</div>
      			
     			<div class='bookmark-cover'>
     				<i class='fa-solid fa-bookmark'></i>
     			</div>
      	</div>
      </c:forEach>
      
      </div> <!-- tab-3-keyword -->
      
		</div>
		
	</div>

	<!-- 카테고리 -->
	<div class="news-container">
		<div class="news-content-cover" id="category">
			<div class="sub-menu">
				<ul class='menu'>
					<li class="tab-link current" data-tab="tab-1-category">
<!-- 						<form action="javascript:void(0)">
							<input class="category" type=button value="정치">
						</form> -->
						정치
					</li>
					<li class="tab-link" data-tab="tab-2-category">
<!-- 						<form action="javascript:void(0)">
							<input class="category" type=button value="경제">
						</form> -->
						경제
					</li>
					<li class="tab-link" data-tab="tab-3-category">
<!-- 						<form action="javascript:void(0)">
							<input class="category" type=button value="사회">
						</form> -->
						사회
					</li>
					<li class="tab-link" data-tab="tab-4-category">
<!-- 						<form action="javascript:void(0)">
							<input class="category" type=button value="생활">
						</form> -->
						생활
					</li>
					<li class="tab-link" data-tab="tab-5-category">
<!-- 						<form action="javascript:void(0)">
							<input class="category" type=button value="세계">
						</form> -->
						세계
					</li>
					<li class="tab-link" data-tab="tab-6-category">
<!-- 						<form action="javascript:void(0)">
							<input class="category" type=button value="사설/컬럼">
						</form> -->
						사설/컬럼
					</li>
				</ul>
			</div>
			
				<div class="main-title">오늘의 카테고리 뉴스</div>
				<div id="tab-1-category" class="tab-content current">
				
      <c:forEach var="index" begin='1' end='5'>
      	<div class='content-cover'>
      		<img id='img-${index}' alt='images' />
      		<div class='a'>
      			<div id='${index }-1' class='date'></div>
      			<div id='${index }-2' class='main'></div>
      			<div id='${index }-3' class='cont'><br/></div>
      		</div>
     			<div class='bookmark-cover'>
     				<i class='fa-solid fa-bookmark'></i>
     			</div>
      	</div>
      </c:forEach>
      
      </div>
      
				<div id="tab-2-category" class="tab-content">
				
      <c:forEach var="index" begin='1' end='5'>
      	<div class='content-cover'>
      		<img id='img-${index}' alt='images' />
      		<div class='a'>
      			<div id='${index }-1' class='date'></div>
      			<div id='${index }-2' class='main'></div>
      			<div id='${index }-3' class='cont'><br/></div>
      		</div>
     			<div class='bookmark-cover'>
     				<i class='fa-solid fa-bookmark'></i>
     			</div>
      	</div>
      </c:forEach>
      </div><!-- 2 -->
      
				<div id="tab-3-category" class="tab-content">
				
      <c:forEach var="index" begin='1' end='5'>
      	<div class='content-cover'>
      		<img id='img-${index}' alt='images' />
      		<div class='a'>
      			<div id='${index }-1' class='date'></div>
      			<div id='${index }-2' class='main'></div>
      			<div id='${index }-3' class='cont'><br/></div>
      		</div>
     			<div class='bookmark-cover'>
     				<i class='fa-solid fa-bookmark'></i>
     			</div>
      	</div>
      </c:forEach>
      </div><!-- 3 -->
      
				<div id="tab-4-category" class="tab-content">
				
      <c:forEach var="index" begin='1' end='5'>
      	<div class='content-cover'>
      		<img id='img-${index}' alt='images' />
      		<div class='a'>
      			<div id='${index }-1' class='date'></div>
      			<div id='${index }-2' class='main'></div>
      			<div id='${index }-3' class='cont'><br/></div>
      		</div>
     			<div class='bookmark-cover'>
     				<i class='fa-solid fa-bookmark'></i>
     			</div>
      	</div>
      </c:forEach>
      
      </div><!-- 4 -->
				<div id="tab-5-category" class="tab-content">
				
      <c:forEach var="index" begin='1' end='5'>
      	<div class='content-cover'>
      		<img id='img-${index}' alt='images' />
      		<div class='a'>
      			<div id='${index }-1' class='date'></div>
      			<div id='${index }-2' class='main'></div>
      			<div id='${index }-3' class='cont'><br/></div>
      		</div>
     			<div class='bookmark-cover'>
     				<i class='fa-solid fa-bookmark'></i>
     			</div>
      	</div>
      </c:forEach>
      </div> <!-- 5 -->
      
				<div id="tab-6-category" class="tab-content">
				
      
      <c:forEach var="index" begin='1' end='5'>
      	<div class='content-cover'>
      		<img id='img-${index}' alt='images' />
      		<div class='a'>
      			<div id='${index }-1' class='date'></div>
      			<div id='${index }-2' class='main'></div>
      			<div id='${index }-3' class='cont'><br/></div>
      			<div class='bookmark-cover'>
      				<i class='fa-solid fa-bookmark'></i>
      			</div>
      		</div>
      	</div>
      </c:forEach>
      </div><!-- 6 -->
			
			
		</div>
	</div>
	
</div>	
</main>


<!-- FOOTER -->
<footer>
 <!-- HOT WORD --> 
 <div class='word-container'>

 	<div class="title-cover"></div> 
  <div class="word-object">
 		<div class='title'>HOT WORD</div>
  </div>
     
  <div class="box-cover"> 
  
	<div class='box'>
	
	 	<div class="category-container">
 	
	<div class="category-button">
		
		<!-- 정치 -->
		<div class='tab-link' data-tab="con-tab-0" id="politics" value="100">정치</div>
		<!-- 경제 -->	
		<div class='tab-link' data-tab="con-tab-1" id="economy" value="101">경제</div>
		<!-- 사회 -->
		<div class='tab-link' data-tab="con-tab-2" id="society" value="102">사회</div>
		<!-- 생활 -->
		<div class='tab-link' data-tab="con-tab-3" id="life" value="103">생활/문화</div>
		<!-- 세계 -->
		<div class='tab-link' data-tab="con-tab-4" id="world" value="104">세계</div>
		<!-- 사설/컬럼 -->
		<div class='tab-link' data-tab="con-tab-5" id="edit-col" value="105">IT/과학</div>
			
	</div>
 	
 	</div>
 	
 	<div class="main-container">
 	
		<div id="choice" class="tab-container current">
		<img src="/images/inNUCE_logo.png" alter="logo">
		카테고리를 클릭해주세요
		</div>
  <!-- 정치 -->
     <div id="con-tab-0" class="tab-container">
   <!--   <img src="/images/loading.gif" id="loadingimg"> -->
     </div>
 	<!-- 경제 -->
     <div id="con-tab-1" class="tab-container">
   <!--   <img src="/images/loading.gif" id="loadingimg"> -->
     </div>
 	<!-- 사회 -->
     <div id="con-tab-2" class="tab-container">
    <!--  <img src="/images/loading.gif" id="loadingimg"> -->
     </div>
 	<!-- 생활 -->
     <div id="con-tab-3" class="tab-container">
   <!--   <img src="/images/loading.gif" id="loadingimg"> -->
     </div>
 	<!-- 세계 -->
     <div id="con-tab-4" class="tab-container">
<!--      <img src="/images/loading.gif" id="loadingimg"> -->
     </div>
 	<!-- 사설/컬럼 -->
     <div id="con-tab-5" class="tab-container">
<!--      <img src="/images/loading.gif" id="loadingimg"> -->
     </div>
 	
 	</div>
 	

	</div>
	</div>
 </div>
 
			
</footer>

 <!--TO TOP BUTTON-->
 <div id="to-top">
   <div class="material-icons">arrow_upward</div>
 </div>

<script>

let msg = "<c:out value='${placeMassage}'/>";
if(msg != ""){
	alert(msg);
}
</script>
<script defer src="/js/main.js"></script>
</body>
</html>