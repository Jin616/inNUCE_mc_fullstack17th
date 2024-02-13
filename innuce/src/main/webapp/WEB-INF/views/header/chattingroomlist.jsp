<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 채팅바 -->
<div class="badges">
	<div class="title">실시간 인기 토론방</div>
	<div class="badge">
		<button id="chatroombtn-0" type=button></button>
	</div>
	<div class="badge">
		<button id="chatroombtn-1" type=button></button>
	</div>
	<div class="badge">
		<button id="chatroombtn-2" type=button></button>
	</div>
	<div class="badge">
		<button id="chatroombtn-3" type=button></button>
	</div>
	<a href="/debate">더보기<span class="material-symbols-outlined">
			trending_flat</span></a>
</div>

<script>
	$(document).ready(function(){
		$.ajax({
		type : 'post',
		url : 'chattingroomlist',
		success : function(response) {
			for(let i = 0 ; i < response.length; i++){
				let position = "#chatroombtn-" + i;
				$(position).text(response[i].debate_room_name);
				$(position).on('click', function() {
					location.href = '/debate/' + response[i].debate_room_key;
				});
			}
		},//success
		error : function(request, e) {
			alert("코드 : " + request.status + " 메시지 : " + request.responseText + " 오류" + e);
		}
		});//ajax
	});
		
	
</script>