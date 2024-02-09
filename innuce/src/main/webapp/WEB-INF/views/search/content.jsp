s<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="news-cover">
	<div class="news-content">
		<div class="cover">

			<div class="main-title">
				<p>${keyword }</p>
				<p>${noneKeyword }</p>
			</div>
	
			<div id="tab-1-keyword" class="tab-content">
			
			<c:forEach var="newsDTO" items="${newsList }" varStatus="status">
			
      	<div class='content' value="${newsDTO.news_key }">
					<a class="img-cover" href="news?newsKey=${newsDTO.news_key }">
						<img id="img-1" alt="images" src="${newsDTO.news_thumbnailuri2 }" onerror="this.src='/images/inNUCE_logo.png'"/>
					</a>
					
      		<a class='a' href="news?newsKey=${newsDTO.news_key }">
      			<div id='${status.index }-1' class='date'>${newsDTO.news_writendate }</div>
      			<div id='${status.index }-2' class='main'>${newsDTO.news_title }</div>
      			<div id='${status.index }-3' class='cont'>${newsDTO.summ_content }<br/></div>
      		</a>
      		
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