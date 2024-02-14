<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="search-container" >
	
	<form name="searchFrom" action="search" onsubmit="searchOptionCheck()" autocomplete="off">  
	
	<div class="input-keyword">
		<div class="material-symbols-outlined">search</div>
		<input id="searchBar" name="keyword" type=text placeholder="검색어를 입력해주세요" required maxlength='25'/>
		<input type="hidden" id="ds" name="ds" value="">
		<input type="hidden" id="de" name="de" value="">
		<input type="hidden" id="pressString" name="pressString" value="">
	</div>
	
	<div class="button">
	 	<input id="search-button" type=submit value='검색'>
	</div>
	
	<div class="serach-myPlace">
		<i id="earth" class="fa-solid fa-earth-asia" style="color: #0a4e6b;">
			<input class="myPlace" type=submit value=''>
		</i>
	</div>
	</form>
 
</div>

