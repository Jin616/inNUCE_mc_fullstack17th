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
<link rel="stylesheet" type="text/css" href="/css/header.css">

</head>

<body>


<!-- HEADER -->
<header>

<%@ include file ="/WEB-INF/views/header/header.jsp" %>

</header>

<!-- 0213 수정 header/header.jsp로 이관 -->
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
					<li id="keyword1" class="tab-link current" data-tab="tab-1-keyword">

						${keywordKeys[0] }
					</li>
					<li id="keyword1" class="tab-link" data-tab="tab-2-keyword">

						${keywordKeys[1] }
					</li>
					<li id="keyword1" class="tab-link" data-tab="tab-3-keyword">

						${keywordKeys[2] }
					</li>
				</ul>
			</div>
			
			<div class="main-title">오늘의 키워드 뉴스</div>

      <c:forEach var="tabindex" begin='1' end='3'>
			<div id="tab-${tabindex }-keyword" class="tab-content <c:if test="${tabindex == 1}">current</c:if>">
		
	      <c:forEach var="index" begin='1' end='5'>
	      	<div class='content-cover'>
	      		<a id='${tabindex }-${index}-a-keyword'>
	      			<img id='${tabindex }-${index}-img-keyword' alt='images' />
	      		</a>
	      		<div class='a'>
	      			<div id='${tabindex }-${index }-date-keyword' class='date'></div>
	      			<a id='${tabindex }-${index }-main-keyword' class='main'></a>
	      			<a id='${tabindex }-${index }-cont-keyword' class='cont'><br/></a>
	      		</div>
	     			<div class='bookmark-cover'>
	     				<i id='${tabindex }-${index }-scrap-keyword' scrapNum= ${(tabindex-1)*5 +index} class='fa-solid fa-bookmark'></i>
	     			</div>
	      	</div>
	      </c:forEach>
      
      </div>
      </c:forEach>
      
      
		</div>
		
	</div>

	<!-- 카테고리 -->
	<div class="news-container">
		<div class="news-content-cover" id="category">
			<div class="sub-menu">
				<ul class='menu'>
					<li class="tab-link current" data-tab="tab-1-category">
						정치
					</li>
					<li class="tab-link" data-tab="tab-2-category">
						경제
					</li>
					<li class="tab-link" data-tab="tab-3-category">
						사회
					</li>
					<li class="tab-link" data-tab="tab-4-category">
						생활
					</li>
					<li class="tab-link" data-tab="tab-5-category">
						세계
					</li>
					<li class="tab-link" data-tab="tab-6-category">
						사설/컬럼
					</li>
				</ul>
			</div>


	<div class="main-title">오늘의 카테고리 뉴스</div>
		<c:forEach var="tabindex" begin='1' end='6'>
		<div id="tab-${tabindex }-category" class="tab-content <c:if test="${tabindex == 1}">current</c:if>">
		
	      <c:forEach var="index" begin='1' end='5'>
	      	<div class='content-cover'>
	      		<a id='${tabindex }-${index}-a-category'>
	      			<img id='${tabindex }-${index}-img-category' alt='images' />
	      		</a>
	      		<div class='a'>
	      			<div id='${tabindex }-${index }-date-category' class='date'></div>
	      			<a id='${tabindex }-${index }-main-category' class='main'></a>
	      			<a id='${tabindex }-${index }-cont-category' class='cont'><br/></a>
	      		</div>
	     			<div class='bookmark-cover'>
	     				<i id='${tabindex }-${index }-scrap-category' scrapNum= ${(tabindex-1)*5 +index+15} class='fa-solid fa-bookmark'></i>
	     			</div>
	      	</div>
	      </c:forEach>
      
      </div>
      </c:forEach>

			
			
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
     </div>
 	<!-- 경제 -->
     <div id="con-tab-1" class="tab-container">
     </div>
 	<!-- 사회 -->
     <div id="con-tab-2" class="tab-container">
     </div>
 	<!-- 생활 -->
     <div id="con-tab-3" class="tab-container">
     </div>
 	<!-- 세계 -->
     <div id="con-tab-4" class="tab-container">
     </div>
 	<!-- 사설/컬럼 -->
     <div id="con-tab-5" class="tab-container">
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
<jsp:include page="/WEB-INF/views/search/scrapInMain.jsp" />
<script defer src="/js/main.js"></script>
<script defer src="/js/search-option.js"></script>
</body>
</html>