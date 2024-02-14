<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<div class="search-option">
	<ul class='main-menu'>
	<li class='item'>
		
		
		<div class="item__contents">
		
		<!-- 1 - 언론사 -->
 		 <div id="media-title">언론사</div>
 		 
		 <div class="contents__media">
		 
		 <ul id="press-info" class='inner'></ul>
			 
		 </div>
		 
		 <!-- 2 - 기간 -->
		 <div class="contents__duration">
		  <div class='inner'>
		  	<h4>기간</h4>
		  	<input type="date" id="SD" value="${ds }">&nbsp; ~ &nbsp;<input type="date" id="ED" value="${de }">
		  	
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
<script defer src="/js/press.js"></script>