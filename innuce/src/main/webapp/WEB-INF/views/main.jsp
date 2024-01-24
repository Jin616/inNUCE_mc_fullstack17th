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
<!-- 구글 아이콘 -->
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
<!-- css -->
<link rel="stylesheet" type="text/css" href="/css/main.css">
<!-- 브라우저의 초기설정을 reset -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js" integrity="sha512-WFN04846sdKMIP5LKNphMaWzU7YpMyCU245etK3g/2ARYbPK9Ub18eG+ljU96qKRCWh+quCY7yefSmlkQw1ANQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.4/gsap.min.js" integrity="sha512-EZI2cBcGPnmR89wTgVnN3602Yyi7muWo8y1B3a8WmIv1J9tYG+udH4LvmYjLiGp37yHB7FfaPBo8ly178m9g4Q==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

 <!--    anyChart -->
 <script src="https://cdn.anychart.com/releases/8.12.0/js/anychart-core.min.js"></script>
<script src="https://cdn.anychart.com/releases/8.12.0/js/anychart-tag-cloud.min.js"></script>


<script src="/js/jquery-3.7.1.min.js"></script>
<script defer src="/js/main.js"></script>
</head>

<body>
<!-- header -->
<header>
<%@ include file="./header/header.jsp"%>
<%@ include file="./header/searchbar.jsp"%>
<%@ include file="./header/chattingroomlist.jsp"%>
</header>

<!-- MAIN -->
<main>
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
     	<div class='content-cover'>
        <img id="img-1" alt="images"/>
        <div class="a">
          <div id="first-1" class="date"></div>
          <div id="first-2" class="main"></div>
          <div id="first-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-2" alt="images"/>
        <div class="a">
          <div id="second-1" class="date"></div>
          <div id="second-2" class="main"></div>
          <div id="second-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-3" alt="images"/>
        <div class="a">
          <div id="third-1" class="date"></div>
          <div id="third-2" class="main"></div>
          <div id="third-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-4" alt="images"/>
        <div class="a">
          <div id="fourth-1" class="date"></div>
          <div id="fourth-2" class="main"></div>
          <div id="fourth-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover" id="last-c">
      <img id="img-5" alt="images"/>
        <div class="a">
          <div id="fifth-1" class="date"></div>
          <div id="fifth-2" class="main"></div>
          <div id="fifth-3" class="cont"><br/></div>
        </div>
      </div>
      </div>
      
			<div id="tab-2-keyword" class="tab-content">
     	<div class='content-cover'>
        <img id="img-1" alt="images"/>
        <div class="a">
          <div id="first-1" class="date"></div>
          <div id="first-2" class="main"></div>
          <div id="first-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-2" alt="images"/>
        <div class="a">
          <div id="second-1" class="date"></div>
          <div id="second-2" class="main"></div>
          <div id="second-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-3" alt="images"/>
        <div class="a">
          <div id="third-1" class="date"></div>
          <div id="third-2" class="main"></div>
          <div id="third-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-4" alt="images"/>
        <div class="a">
          <div id="fourth-1" class="date"></div>
          <div id="fourth-2" class="main"></div>
          <div id="fourth-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover" id="last-c">
      <img id="img-5" alt="images"/>
        <div class="a">
          <div id="fifth-1" class="date"></div>
          <div id="fifth-2" class="main"></div>
          <div id="fifth-3" class="cont"><br/></div>
        </div>
      </div>
      </div>
      
			<div id="tab-3-keyword" class="tab-content">
     	<div class='content-cover'>
        <img id="img-1" alt="images"/>
        <div class="a">
          <div id="first-1" class="date"></div>
          <div id="first-2" class="main"></div>
          <div id="first-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-2" alt="images"/>
        <div class="a">
          <div id="second-1" class="date"></div>
          <div id="second-2" class="main"></div>
          <div id="second-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-3" alt="images"/>
        <div class="a">
          <div id="third-1" class="date"></div>
          <div id="third-2" class="main"></div>
          <div id="third-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-4" alt="images"/>
        <div class="a">
          <div id="fourth-1" class="date"></div>
          <div id="fourth-2" class="main"></div>
          <div id="fourth-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover" id="last-c">
      <img id="img-5" alt="images"/>
        <div class="a">
          <div id="fifth-1" class="date"></div>
          <div id="fifth-2" class="main"></div>
          <div id="fifth-3" class="cont"><br/></div>
        </div>
      </div>
      </div>
      
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
				
     	<div class='content-cover'>
        <img id="img-1" alt="images"/>
        <div class="a">
          <div id="first-1" class="date"></div>
          <div id="first-2" class="main"></div>
          <div id="first-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-2" alt="images"/>
        <div class="a">
          <div id="second-1" class="date"></div>
          <div id="second-2" class="main"></div>
          <div id="second-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-3" alt="images"/>
        <div class="a">
          <div id="third-1" class="date"></div>
          <div id="third-2" class="main"></div>
          <div id="third-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-4" alt="images"/>
        <div class="a">
          <div id="fourth-1" class="date"></div>
          <div id="fourth-2" class="main"></div>
          <div id="fourth-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover" id="last-c">
      <img id="img-5" alt="images"/>
        <div class="a">
          <div id="fifth-1" class="date"></div>
          <div id="fifth-2" class="main"></div>
          <div id="fifth-3" class="cont"><br/></div>
        </div>
      </div>
      </div>
      
				<div id="tab-2-category" class="tab-content">
				
     	<div class='content-cover'>
        <img id="img-1" alt="images"/>
        <div class="a">
          <div id="first-1" class="date"></div>
          <div id="first-2" class="main"></div>
          <div id="first-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-2" alt="images"/>
        <div class="a">
          <div id="second-1" class="date"></div>
          <div id="second-2" class="main"></div>
          <div id="second-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-3" alt="images"/>
        <div class="a">
          <div id="third-1" class="date"></div>
          <div id="third-2" class="main"></div>
          <div id="third-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-4" alt="images"/>
        <div class="a">
          <div id="fourth-1" class="date"></div>
          <div id="fourth-2" class="main"></div>
          <div id="fourth-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover" id="last-c">
      <img id="img-5" alt="images"/>
        <div class="a">
          <div id="fifth-1" class="date"></div>
          <div id="fifth-2" class="main"></div>
          <div id="fifth-3" class="cont"><br/></div>
        </div>
      </div>
      </div>
      
				<div id="tab-3-category" class="tab-content">
				
     	<div class='content-cover'>
        <img id="img-1" alt="images"/>
        <div class="a">
          <div id="first-1" class="date"></div>
          <div id="first-2" class="main"></div>
          <div id="first-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-2" alt="images"/>
        <div class="a">
          <div id="second-1" class="date"></div>
          <div id="second-2" class="main"></div>
          <div id="second-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-3" alt="images"/>
        <div class="a">
          <div id="third-1" class="date"></div>
          <div id="third-2" class="main"></div>
          <div id="third-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-4" alt="images"/>
        <div class="a">
          <div id="fourth-1" class="date"></div>
          <div id="fourth-2" class="main"></div>
          <div id="fourth-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover" id="last-c">
      <img id="img-5" alt="images"/>
        <div class="a">
          <div id="fifth-1" class="date"></div>
          <div id="fifth-2" class="main"></div>
          <div id="fifth-3" class="cont"><br/></div>
        </div>
      </div>
      </div>
      
				<div id="tab-4-category" class="tab-content">
				
     	<div class='content-cover'>
        <img id="img-1" alt="images"/>
        <div class="a">
          <div id="first-1" class="date"></div>
          <div id="first-2" class="main"></div>
          <div id="first-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-2" alt="images"/>
        <div class="a">
          <div id="second-1" class="date"></div>
          <div id="second-2" class="main"></div>
          <div id="second-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-3" alt="images"/>
        <div class="a">
          <div id="third-1" class="date"></div>
          <div id="third-2" class="main"></div>
          <div id="third-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-4" alt="images"/>
        <div class="a">
          <div id="fourth-1" class="date"></div>
          <div id="fourth-2" class="main"></div>
          <div id="fourth-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover" id="last-c">
      <img id="img-5" alt="images"/>
        <div class="a">
          <div id="fifth-1" class="date"></div>
          <div id="fifth-2" class="main"></div>
          <div id="fifth-3" class="cont"><br/></div>
        </div>
      </div>
      </div>
				<div id="tab-5-category" class="tab-content">
				
     	<div class='content-cover'>
        <img id="img-1" alt="images"/>
        <div class="a">
          <div id="first-1" class="date"></div>
          <div id="first-2" class="main"></div>
          <div id="first-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-2" alt="images"/>
        <div class="a">
          <div id="second-1" class="date"></div>
          <div id="second-2" class="main"></div>
          <div id="second-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-3" alt="images"/>
        <div class="a">
          <div id="third-1" class="date"></div>
          <div id="third-2" class="main"></div>
          <div id="third-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-4" alt="images"/>
        <div class="a">
          <div id="fourth-1" class="date"></div>
          <div id="fourth-2" class="main"></div>
          <div id="fourth-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover" id="last-c">
      <img id="img-5" alt="images"/>
        <div class="a">
          <div id="fifth-1" class="date"></div>
          <div id="fifth-2" class="main"></div>
          <div id="fifth-3" class="cont"><br/></div>
        </div>
      </div>
      </div>
      
				<div id="tab-6-category" class="tab-content">
				
     	<div class='content-cover'>
        <img id="img-1" alt="images"/>
        <div class="a">
          <div id="first-1" class="date"></div>
          <div id="first-2" class="main"></div>
          <div id="first-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-2" alt="images"/>
        <div class="a">
          <div id="second-1" class="date"></div>
          <div id="second-2" class="main"></div>
          <div id="second-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-3" alt="images"/>
        <div class="a">
          <div id="third-1" class="date"></div>
          <div id="third-2" class="main"></div>
          <div id="third-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover">
      <img id="img-4" alt="images"/>
        <div class="a">
          <div id="fourth-1" class="date"></div>
          <div id="fourth-2" class="main"></div>
          <div id="fourth-3" class="cont"><br/></div>
        </div>
      </div>
      
      <div class="content-cover" id="last-c">
      <img id="img-5" alt="images"/>
        <div class="a">
          <div id="fifth-1" class="date"></div>
          <div id="fifth-2" class="main"></div>
          <div id="fifth-3" class="cont"><br/></div>
        </div>
      </div>
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
 	
 	<div class="category-container">
 	
	<div class="category-button">
		
		<!-- 정치 -->
		<div class='tab-link' data-tab="con-tab-0" id="politics" value="100">정치</div>
		<!-- 경제 -->	
		<div class='tab-link' data-tab="con-tab-1" id="economy" value="101">경제</div>
		<!-- 사회 -->
		<div class='tab-link' data-tab="con-tab-2" id="society" value="102">사회</div>
		<!-- 생활 -->
		<div class='tab-link' data-tab="con-tab-3" id="life" value="103">생활</div>
		<!-- 세계 -->
		<div class='tab-link' data-tab="con-tab-4" id="world" value="104">세계</div>
		<!-- 사설/컬럼 -->
		<div class='tab-link' data-tab="con-tab-5" id="edit-col" value="105">사설/컬럼</div>
			
	</div>
 	
 	</div>
	</div>
	</div>
 </div>
 
			
</footer>




</body>
</html>