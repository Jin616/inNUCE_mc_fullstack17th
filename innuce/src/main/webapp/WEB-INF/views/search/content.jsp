<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="news-container">

	<div class="news-content-cover" id="keyword">

		<div id="keyword" class="main-title">"${keyword }"</div>

		<div id="tab-1-keyword" class="tab-content current">
			<div class='content-cover'>
				<img id="img-1" alt="images" />
				<div class="a">
					<div id="first-1" class="date"></div>
					<div id="first-2" class="main"></div>
					<div id="first-3" class="cont">
						<br />
					</div>
				</div>
				<div class='bookmark-cover'>
					<i class="fa-solid fa-bookmark"></i>
				</div>
			</div>

			<div class="content-cover">
				<img id="img-2" alt="images" />
				<div class="a">
					<div id="second-1" class="date"></div>
					<div id="second-2" class="main"></div>
					<div id="second-3" class="cont">
						<br />
					</div>
				</div>
				<div class='bookmark-cover'>
					<i class="fa-solid fa-bookmark"></i>
				</div>
			</div>

			<div class="content-cover">
				<img id="img-3" alt="images" />
				<div class="a">
					<div id="third-1" class="date"></div>
					<div id="third-2" class="main"></div>
					<div id="third-3" class="cont">
						<br />
					</div>
				</div>
				<div class='bookmark-cover'>
					<i class="fa-solid fa-bookmark"></i>
				</div>
			</div>

			<div class="content-cover">
				<img id="img-4" alt="images" />
				<div class="a">
					<div id="fourth-1" class="date"></div>
					<div id="fourth-2" class="main"></div>
					<div id="fourth-3" class="cont">
						<br />
					</div>
				</div>
				<div class='bookmark-cover'>
					<i class="fa-solid fa-bookmark"></i>
				</div>
			</div>
			<div class="content-cover">
				<img id="img-5" alt="images" />
				<div class="a">
					<div id="fourth-1" class="date"></div>
					<div id="fourth-2" class="main"></div>
					<div id="fourth-3" class="cont">
						<br />
					</div>
				</div>
				<div class='bookmark-cover'>
					<i class="fa-solid fa-bookmark"></i>
				</div>
			</div>
			<div class="content-cover">
				<img id="img-6" alt="images" />
				<div class="a">
					<div id="fourth-1" class="date"></div>
					<div id="fourth-2" class="main"></div>
					<div id="fourth-3" class="cont">
						<br />
					</div>
				</div>
				<div class='bookmark-cover'>
					<i class="fa-solid fa-bookmark"></i>
				</div>
			</div>
			<div class="content-cover">
				<img id="img-7" alt="images" />
				<div class="a">
					<div id="fourth-1" class="date"></div>
					<div id="fourth-2" class="main"></div>
					<div id="fourth-3" class="cont">
						<br />
					</div>
				</div>
				<div class='bookmark-cover'>
					<i class="fa-solid fa-bookmark"></i>
				</div>
			</div>
			<div class="content-cover">
				<img id="img-8" alt="images" />
				<div class="a">
					<div id="fourth-1" class="date"></div>
					<div id="fourth-2" class="main"></div>
					<div id="fourth-3" class="cont">
						<br />
					</div>
				</div>
				<div class='bookmark-cover'>
					<i class="fa-solid fa-bookmark"></i>
				</div>
			</div>
			<div class="content-cover">
				<img id="img-9" alt="images" />
				<div class="a">
					<div id="fourth-1" class="date"></div>
					<div id="fourth-2" class="main"></div>
					<div id="fourth-3" class="cont">
						<br />
					</div>
				</div>
				<div class='bookmark-cover'>
					<i class="fa-solid fa-bookmark"></i>
				</div>
			</div>

			<div class="content-cover" id="last-c">
				<img id="img-10" alt="images" />
				<div class="a">
					<div id="fifth-1" class="date"></div>
					<div id="fifth-2" class="main"></div>
					<div id="fifth-3" class="cont">
						<br />
					</div>
				</div>
				<div class='bookmark-cover'>
					<i class="fa-solid fa-bookmark"></i>
				</div>
			</div>

		</div>

		<div class="paging">
			<%-- 		<%
		int pageCount = (Integer) request.getAttribute("pageCount");
		int totalCount = (Integer) request.getAttribute("totalCount");

		int totalPage = 0;
		if (totalCount % pageCount == 0)
			totalPage = totalCount / pageCount;
		else
			totalPage = totalCount / pageCount + 1; --%>
			<%
			for (int i = 1; i <= 3; i++) {
			%>

			<a href="/search?pageNum=<%=i%>"><%=i%></a>&nbsp;
			<%
			}
			%>
		</div>


	</div>



</div>