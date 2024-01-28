<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>in NUCE</title>
<!-- ICON -->
<link rel="icon" href="/images/inNUCE_logo.png" />
<!-- 구글 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Brygada+1918:ital,wght@1,700&family=Nanum+Gothic:wght@400;700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Merriweather:wght@700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Orbit&display=swap" rel="stylesheet">
<!-- 구글 아이콘 -->
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<!-- fontawesome -->
<script src="https://kit.fontawesome.com/1ff0b3ea8a.js" crossorigin="anonymous"></script>
<!-- css -->
<link rel="stylesheet" type="text/css" href="/css/searchPage.css">
<link rel="stylesheet" type="text/css" href="/css/main_top.css">
<link rel="stylesheet" type="text/css" href="/css/mypage.css">
<link rel="stylesheet" type="text/css" href="/css/searchBar2.css">
<!-- 브라우저의 초기설정을 reset -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js" integrity="sha512-WFN04846sdKMIP5LKNphMaWzU7YpMyCU245etK3g/2ARYbPK9Ub18eG+ljU96qKRCWh+quCY7yefSmlkQw1ANQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.4/gsap.min.js" integrity="sha512-EZI2cBcGPnmR89wTgVnN3602Yyi7muWo8y1B3a8WmIv1J9tYG+udH4LvmYjLiGp37yHB7FfaPBo8ly178m9g4Q==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

 <!--    anyChart -->
 <script src="https://cdn.anychart.com/releases/8.12.0/js/anychart-core.min.js"></script>
<script src="https://cdn.anychart.com/releases/8.12.0/js/anychart-tag-cloud.min.js"></script>


<script src="/js/jquery-3.7.1.min.js"></script>
<script defer src="/js/searchPage.js"></script>
</head>
<body>
<!-- HEADER -->
<header>

<%@ include file ="/WEB-INF/views/header/header.jsp" %>


</header>

<nav style="display: none;">
<div class="search-option">
	<ul class='main-menu'>
	<li class='item'>
		
		
		<div class="item__contents">
		<!-- 1 - 언론사 -->
		 <div class="contents__media">
		 
	 		<div id="media-title">언론사</div>
			 <ul class='inner'>
			 
			 	<li>
		 			<ul>
		 				<li>
		 					<label>
		 						<input type="checkbox"> 언론사
		 					</label>
		 				</li>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 			</ul>
		 			
			 	</li>
			 	<li>
			 		
		 			<ul>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 			</ul>
			 	</li>
			 	
			 	<li>
			 		<ul>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 			</ul>
			 	</li>
			 	
			 	<li>
			 		
		 			<ul>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 			</ul>
			 	</li>
			 	
			 	<li>
			 		
		 			<ul>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 				<li>
		 					<label><input type="checkbox"> 언론사</label>
		 					
		 				</li>
		 			</ul>
			 	</li>
			 	
			 </ul>
			 
		 </div>
		 <!-- 2 - 기간 -->
		 <div class="contents__duration">
		  <div class='inner'>
		  	<h4>기간</h4>
		  	<input type="date" id="SD">&nbsp; ~ &nbsp;<input type="date" id="ED">
		  	
		  </div>
		 </div>
		  <!-- 3 - 내 위치 -->
		 <div class="contents__place" id="contents__place">
		 <div class="inner">
			 <div class="place-title">현재위치검색</div>
			<i id="myPlace" class="fa-solid fa-location-crosshairs fa-beat-fade"></i>
		 </div>
		 
		 </div>
		 
		</div>
	</li>
</ul>
</div>
</nav>

<!-- MAIN -->
<main>

<!-- newsList -->
<%@ include file ="/WEB-INF/views/search/content.jsp" %>
</main>


</body>
</html>