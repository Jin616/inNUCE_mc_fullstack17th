<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 검색 -->
	<div class="search-container" >
	
	<form action="/search" autocomplete="off">  
	
	<div class="input-keyword">
		<div class="material-symbols-outlined">search</div>
		<input id="keyword" name="keyword" type=text placeholder="검색어를 입력해주세요" required />
	</div>
	
	<div class="button">
	 	<input id="search-button" type=submit value='검색옵션'>
	</div>
	
	<ul class="search-option">
		<li class='item'>
		
		 <div class="item__name">
 		 	<input type="checkbox" />
		 </div>
		 
		 <div class="item__contents"></div>
		 
		</li>
	</ul>
	
	<div class="serach-myPlace">
		<input class="myPlace" type=submit value=''>
	</div>
	
	</form>
 
</div>

