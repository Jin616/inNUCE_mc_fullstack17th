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
<script>
window.onload = function(){
	// 세션에 로그인된 유저가 없으면 스크랩 기능을 막기 위한 코드
	
	if(${empty sessionScope.login_user} ){
	
		$('main i').on('click',function(){
			
			var user_will_login = window.confirm("로그인이 필요한 서비스입니다. 로그인 하시겠습니까?")
			// 로그인 하겠다고 하는 경우
			if(user_will_login == true){
				location.href ='/login'
			}
			// 로그인 안한다고 하는경우 (그냥 confirm창만 닫아주면 됨.)
			else{}
			
		})//on
	}
	// 세션에 로그인 된 유저 있으면 스크랩 기능을 준다
	else{
		
		let user_id = '${sessionScope.login_user.user_id}'
			// 해당 유저가 스크랩한 기사리스트
			let scrap_list=[];
			//우선 ajax로 해당 유저가 스크랩한 기사들의 news_key를 ajax로 받아온다
			$.ajax({
				type : 'post',
				url : 'getscraplist',
				data : {'user_id' : user_id},
				success : function(response){
					
					for (var i = 0 ; i < response.length;i++){
						scrap_list.push(response[i])
					}//for
						
			
			
			// 유저가 스크랩한 기사인경우 북마크바 속성 클릭된거로 바꿔주기
			for(var i = 0; i < $('.bookmark-cover').find('i').length; i++){
				
				if(scrap_list.includes(Number($("[scrapNum]")[i].getAttribute('news')))){
					$("[scrapNum]")[i].setAttribute("class",'fa-solid fa-bookmark checked')
					
				}
				else{
					$("[scrapNum]")[i].setAttribute("class",'fa-solid fa-bookmark')
					
				}//else
			}//for
			
		$('main i').on('click',function(){
			
			// 이미 스크랩 된 기사인경우
			if($(this).attr("class")=='fa-solid fa-bookmark checked'){
				
				// 북바크 색상 변경
				$(this).removeClass("checked")
				// 북마크 취소 했다는 사실 서버/db에 알리기
				$.ajax({
					type : 'post',
					url : 'scrapnewscancel',
					data : {'user_id' : user_id, 'news_key' : $(this).attr('news')},
					success : function(response){
						alert("스크랩이 취소 됐습니다")
						location.reload(true)
					}//success
				})//ajax
			}
			// 아닌경우
			else{
				
				// 북바크 색상 변경
				$(this).addClass("checked")
			
				// 북마크 했다는 사실 서버/db에 알리기
				$.ajax({
					type : 'post',
					url : 'scrapnews',
					data : {'user_id' : user_id, 'news_key' : $(this).attr('news')},
					success : function(response){
						alert("스크랩이 완료 됐습니다")
						location.reload(true)
					}//success
				})//ajax
			}//else
		})// on
		}//success
	})//ajax
	}//else
	
}//onload
</script>



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
					<li id="keyword1" class="tab-link current" data-tab="tab-1-keyword">
		<!-- 				<form action="javascript:void(0)">
							<input type=button value="키워드1">
						</form> -->
						${keywordKeys[2] }
					</li>
					<li id="keyword1" class="tab-link" data-tab="tab-2-keyword">
<!-- 						<form action="javascript:void(0)">
							<input type=button value="키워드2">
						</form> -->
						${keywordKeys[1] }
					</li>
					<li id="keyword1" class="tab-link" data-tab="tab-3-keyword">
<!-- 						<form action="javascript:void(0)">
							<input type=button value="키워드3">
						</form> -->
						${keywordKeys[0] }
					</li>
				</ul>
			</div>
			
			<div class="main-title">오늘의 키워드 뉴스</div>
		<%-- 	
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
     				<i class='fa-solid fa-bookmark' news='${newsDTO.news_key}' ></i>
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
     				<i class='fa-solid fa-bookmark' news='${newsDTO.news_key}' ></i>
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
     				<i class='fa-solid fa-bookmark' news='${newsDTO.news_key}'></i>
     			</div>
     			
      	</div>
      </c:forEach>
      
      </div> <!-- tab-3-keyword --> --%>
      <c:forEach var="tabindex" begin='1' end='6'>
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
	     				<i class='fa-solid fa-bookmark'></i>
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


	<!-- 0208 seo modify 중복된 코드 리팩토링 -->
	<!-- index 주의... 하려다 id 중복되는 거보고 전체 수정합니다. -->
	<!-- css 보고 클래스로만 css 입히신 거 같아서 id 바꿔서 테스트 해보고 바꿔둡니다. -->
	<!-- 바꿀라고 했는데 어디서 하드코딩 되었는지 찾을 수가 없어서 인덱스 꼬인 상태로 둡니다... -->
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
	     				<i class='fa-solid fa-bookmark'></i>
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