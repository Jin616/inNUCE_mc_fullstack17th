<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link href="https://fonts.googleapis.com/css2?family=BrygaSda+1918:ital,wght@1,700&family=Nanum+Gothic:wght@400;700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Merriweather:wght@700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
<!-- 구글 아이콘 -->
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<!-- fontawesome -->
<script src="https://kit.fontawesome.com/1ff0b3ea8a.js" crossorigin="anonymous"></script>
<!-- css -->
<link rel="stylesheet" type="text/css" href="/css/main.css">
<!-- <link rel="stylesheet" type="text/css" href="/css/searchPage.css">-->
<link rel="stylesheet" type="text/css" href="/css/mypage.css">
<!-- 브라우저의 초기설정을 reset -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js" integrity="sha512-WFN04846sdKMIP5LKNphMaWzU7YpMyCU245etK3g/2ARYbPK9Ub18eG+ljU96qKRCWh+quCY7yefSmlkQw1ANQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.4/gsap.min.js" integrity="sha512-EZI2cBcGPnmR89wTgVnN3602Yyi7muWo8y1B3a8WmIv1J9tYG+udH4LvmYjLiGp37yHB7FfaPBo8ly178m9g4Q==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
 <!--    anyChart -->
 <script src="https://cdn.anychart.com/releases/8.12.0/js/anychart-core.min.js"></script>
<script src="https://cdn.anychart.com/releases/8.12.0/js/anychart-tag-cloud.min.js"></script>

<script src="/js/jquery-3.7.1.min.js"></script>
</head>

<body>

<header>

	<jsp:include page="../header/header.jsp"/>
	<jsp:include page="../header/chattingroomlist.jsp"/>
	
	
	
</header>

<main>

	<div class="news-container">
	
		<div class="news-content-cover" id="keyword">
		
		
			
		<div id="tab-1-keyword" class="tab-content current">
     	<div class='content-cover'>
        <img id="img-1" alt="images"/>
        <div class="a">
          <div id="first-1" class="date"></div>
          <div id="first-2" class="main"></div>
          <div id="first-3" class="cont"><br/></div>
        </div>
      	<div class='bookmark-cover'>
      		<i  class="fa-solid fa-bookmark"></i>
      	</div>
      </div>
      
      <div class="content-cover">
      <img id="img-2" alt="images"/>
        <div class="a">
          <div id="second-1" class="date"></div>
          <div id="second-2" class="main"></div>
          <div id="second-3" class="cont"><br/></div>
        </div>
              	<div class='bookmark-cover'>
      		<i  class="fa-solid fa-bookmark"></i>
      	</div>
      </div>
      
      <div class="content-cover">
      <img id="img-3" alt="images"/>
        <div class="a">
          <div id="third-1" class="date"></div>
          <div id="third-2" class="main"></div>
          <div id="third-3" class="cont"><br/></div>
        </div>
              	<div class='bookmark-cover'>
      		<i  class="fa-solid fa-bookmark"></i>
      	</div>
      </div>
      
      <div class="content-cover">
      <img id="img-4" alt="images"/>
        <div class="a">
          <div id="fourth-1" class="date"></div>
          <div id="fourth-2" class="main"></div>
          <div id="fourth-3" class="cont"><br/></div>
        </div>
              	<div class='bookmark-cover'>
      		<i  class="fa-solid fa-bookmark"></i>
      	</div>
      </div>
      
      <div class="content-cover" id="last-c">
      <img id="img-5" alt="images"/>
        <div class="a">
          <div id="fifth-1" class="date"></div>
          <div id="fifth-2" class="main"></div>
          <div id="fifth-3" class="cont"><br/></div>
        </div>
              	<div class='bookmark-cover'>
      		<i  class="fa-solid fa-bookmark"></i>
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
              	<div class='bookmark-cover'>
      		<i  class="fa-solid fa-bookmark"></i>
      	</div>
      </div>
      
      <div class="content-cover">
      <img id="img-2" alt="images"/>
        <div class="a">
          <div id="second-1" class="date"></div>
          <div id="second-2" class="main"></div>
          <div id="second-3" class="cont"><br/></div>
        </div>
              	<div class='bookmark-cover'>
      		<i  class="fa-solid fa-bookmark"></i>
      	</div>
      </div>
      
      <div class="content-cover">
      <img id="img-3" alt="images"/>
        <div class="a">
          <div id="third-1" class="date"></div>
          <div id="third-2" class="main"></div>
          <div id="third-3" class="cont"><br/></div>
        </div>
              	<div class='bookmark-cover'>
      		<i  class="fa-solid fa-bookmark"></i>
      	</div>
      </div>
      
      <div class="content-cover">
      <img id="img-4" alt="images"/>
        <div class="a">
          <div id="fourth-1" class="date"></div>
          <div id="fourth-2" class="main"></div>
          <div id="fourth-3" class="cont"><br/></div>
        </div>
              	<div class='bookmark-cover'>
      		<i  class="fa-solid fa-bookmark"></i>
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
            	<div class='bookmark-cover'>
      		<i  class="fa-solid fa-bookmark"></i>
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
              	<div class='bookmark-cover'>
      		<i  class="fa-solid fa-bookmark"></i>
      	</div>
      </div>
      
      <div class="content-cover">
      <img id="img-2" alt="images"/>
        <div class="a">
          <div id="second-1" class="date"></div>
          <div id="second-2" class="main"></div>
          <div id="second-3" class="cont"><br/></div>
        </div>
              	<div class='bookmark-cover'>
      		<i class="fa-solid fa-bookmark"></i>
      	</div>
      </div>
      
      <div class="content-cover">
      <img id="img-3" alt="images"/>
        <div class="a">
          <div id="third-1" class="date"></div>
          <div id="third-2" class="main"></div>
          <div id="third-3" class="cont"><br/></div>
        </div>
              	<div class='bookmark-cover'>
      		<i  class="fa-solid fa-bookmark"></i>
      	</div>
      </div>
      
      <div class="content-cover">
      <img id="img-4" alt="images"/>
        <div class="a">
          <div id="fourth-1" class="date"></div>
          <div id="fourth-2" class="main"></div>
          <div id="fourth-3" class="cont"><br/></div>
        </div>
              	<div class='bookmark-cover'>
      		<i  class="fa-solid fa-bookmark"></i>
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
            	<div class='bookmark-cover'>
      		<i  class="fa-solid fa-bookmark"></i>
      	</div>
      </div>
      
		</div>
		
	</div>

</main>

</body>
</html>