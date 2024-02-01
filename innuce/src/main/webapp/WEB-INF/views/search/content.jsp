<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="news-cover">

	<div class="news-content">
		<div class="cover">

			<div class="main-title">
				<p>"${keyword }"</p>
			</div>
	
			<div id="tab-1-keyword" class="tab-content">
			
		<!-- 		<div class='content'>
					<div class="img-cover">
						<img id="img-1" alt="images" src="/images/article_picture1.png"/>
					</div>
					<div class="a">
						<div id="first-1" class="date">2024-02-01</div>
						<div id="first-2" class="main">충돌의 계기가 됐던 김건희 여사 문제에 대해선을 하는 것이고 정부는 정부의 일을 하는 </div>
						<div id="first-3" class="cont">
							을 놓고 뒷말이 이어짐. 충당의 일을 하는 것이고 정부는 정부의 일을 하는 것이기 때문에 각자의 그런 입장이 있지 않겠음? 좀 국민들이 듣고 싶은 이야기가 있다면 또 할 필요는 있다.""한 위원장은 자신의 측근인 김경율 비대위원이라든가 또는 사무총장 그런 사람하고 사는 같이가 아니고 혼자서 갔죠.윤재옥 원내대표는 한 위원장 사람이라고 보기는 어렵죠.거기에 나온 분들은 대부분 다 윤석열 대통령의 사람들이라고 할 수 있고 그래서 이런 모습을 보면 한 위원장이 계속 차별화를 고집하면서 윤 대통령과의 긴장 국면을 조성하기보다는 이제 양자 간의 안정화 국면을 더 추구하는 쪽으로 방향을 튼 것이 아닌가 하는 그런 생각을 함. 저희가 박정희 대통령을 지금 평가할 때도 공과를 다 보고 있지 않음? 박정희 대통령에 대한 평가를 일방적으로 부정하거나 일방적으로 긍정하지 아니하고 공과 잘한 것과 잘못한 것을 구분해서 어떻게 볼 것인가를 평가하는 게 그런 방향으로 우리 사회가 대단히 성숙한 사회로 갔는데 일방적으로 혐오와 갈라치기를 중심으로 운동권을 바라보는 것 자체가 제가 보기에는 대단히 좁고 국민 상식의 눈보다는 특수부 검사의 눈으로 그렇게 보고 있지 않나 해서 대단히 안타까움
						</div>
					</div>
					
					<div class='bookmark-cover'>
						<i class="fa-solid fa-bookmark"></i>
					</div>
					
				</div> -->
			<c:forEach var="newsDTO" items="${newsList }" varStatus="status">
      	<div class='content'>
					<div class="img-cover">
						<img id="img-1" alt="images" src="${newsDTO.news_thumbnailuri2 }" onerror="this.src='/images/inNUCE_logo.png'"/>
					</div>
      		<div class='a'>
      			<div id='${status.index }-1' class='date'>${newsDTO.news_writendate }</div>
      			<div id='${status.index }-2' class='main'>${newsDTO.news_title }</div>
      			<div id='${status.index }-3' class='cont'>${newsDTO.summ_content }<br/></div>
      		</div>
     			<div class='bookmark-cover'>
     				<i class='fa-solid fa-bookmark'></i>
     			</div>
      	</div>
      </c:forEach> 
				
			</div> <!-- tab-1-keyword -->
		





		<div class="paging">
		<%
		int pageCount = (Integer) request.getAttribute("pageCount");
		int totalCount = (Integer) request.getAttribute("totalCount");

		int totalPage = 0;
		if (totalCount % pageCount == 0) {
			totalPage = totalCount / pageCount;
		} else {
			totalPage = totalCount / pageCount + 1;
		}
		
		for (int i = 1; i <= totalPage; i++) {
		%>

		<a href="/search?keyword=${keyword }&pageNum=<%=i%>"><%=i%></a>&nbsp;
		<%
		}
		%>
	</div>


	</div>
</div>


</div>