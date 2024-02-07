<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>inNUCE</title>

<jsp:include page="/WEB-INF/views/header/head.jsp" />

<!-- css -->
<link rel="stylesheet" type="text/css" href="/css/header.css">
<link rel="stylesheet" type="text/css" href="/css/nav.css">
<link rel="stylesheet" type="text/css" href="/css/news_main.css">

<script defer src="/js/news_main.js"></script>
<script defer src="/js/searchPage.js"></script>

</head>
<body>
	<!-- HEADER -->
	<header>

		<%@ include file="/WEB-INF/views/header/header.jsp"%>


	</header>

	<nav>
		<%@ include file="/WEB-INF/views/nav/nav.jsp"%>
	</nav>

	<!-- MAIN -->
	<main>

		<div class="news-cover">
			<div class="news-content">
				<div class="cover">

					<div class="title">
						<p>${News.news_title }</p>
					</div>

					<div class="image">
						<img alt="이미지" src="${News.news_thumbnailuri2 }">
					</div>
					<!-- 탭버튼 -->
					<div class="sub-menu" id="change">
						<ul class='menu'>
							<!-- <li class="tab-link current" data-tab="tab-1">짧은 요약</li> -->
							<li class="tab-link current" data-tab="tab-1">기사 요약</li>
							<li class="tab-link" data-tab="tab-2">기사 전문</li>
						</ul>
						
						
						<div id="tab-1" class="tab-content current">
	
							<div class="content">
								<p>${News.summ_content }</p>
							</div>
	
						</div>
	
						<div id="tab-2" class="tab-content">
	
							<div class="content">
								<p>${News.news_content }</p>
							</div>
	
						</div>
	
		<!-- 				<div id="tab-3" class="tab-content">
	
							<div class="content">
							<p>

삼성전자가 중국에서 출시하는 갤럭시 S24 시리즈에 바이두의 인공지능(AI) 모델을 탑재한다. 중국 스마트폰 시장에서 고전 중인 삼성전자가 바이두와 협력을 계기로 현지 시장에서 긍정적인 영향을 줄지 여부에 관심이 모인다.

29일 블룸버그통신에 따르면 바이두의 AI 모델인 ‘어니’이 삼성전자가 중국에서 출시하는 갤럭시 S24 시리즈에 적용될 전망이다. 이를 통해 실시간 통화 통역, 텍스트 번역, 노트 요약 기능 등에 활용된다. 특히 화면에서 원을 그려 검색이 되는 ‘서클 투 서치(Circle to Search)’ 기능에 대한 백엔드 지원도 한다.

중국에서 판매되는 많은 스마트폰은 구글의 안드로이드 체제를 기반으로 한다. 하지만 앱스토어 등 일부 기능이 제한돼 중국 텐센트의 ‘위챗’ 등이 대안으로 이용됐다. 이에 안정적 AI 서비스를 제공하기 위해서는 중국 기업과 협력이 불가피했던 상황으로 해석된다.

아울러 삼성전자가 글로벌 스마트폰 시장에서 2위로 내려 앉은 상황과도 무관하지 않다는 해석이 나온다. 삼성전자는 지난해 수년 만에 처음으로 애플에 밀려 글로벌 선두 업체라는 명성을 빼앗겼다. 여기에 중국에서는 샤오미, 화웨이 등 현지 기업들에도 밀리는 처지다. 이제 바이두와의 협력으로 중국 내 소비자들에게 관심을 끌 수 있을지 여부가 주목된다.

바이두 입장에서도 긍정적일 것이라는 관측이다. 이번 삼성전자와 협력은 자사 AI의 활용 기회가 될 수 있다는 판단이다.

한편 시장조사기관 IDC에 따르면 삼성전자는 지난해 글로벌 스마트폰 시장에서 19.4%의 점유율을 기록한 것으로 나타난다. 이는 20.1%의 애플에 이은 2위다. 삼성전자의 스마트폰 출하량은 약 2억 2660만 대로 전년 대비 13.6% 역성장한 것으로 추정된다.
							</p>
							</div>
	
						</div> -->
					</div>

					<div class="end">
						<a class="original" href="${News.news_originuri }">기사 원문</a>
					</div>

				</div>
			</div>
		</div>

	</main>

 <!--TO TOP BUTTON-->
 <div id="to-top">
   <div class="material-icons">arrow_upward</div>
 </div>

</body>
</html>