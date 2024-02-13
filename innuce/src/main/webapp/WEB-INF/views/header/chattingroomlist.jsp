<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 채팅바 -->
<div class="badges">
	<div class="title">실시간 인기 토론방</div>
	<div class="badge" id="badge-0">
		<input id="chatroombtn-0" type=button></input>
	</div>
	<div class="badge" id="badge-1">
		<input id="chatroombtn-1" type=button></input>
	</div>
	<div class="badge" id="badge-2">
		<input id="chatroombtn-2" type=button></input>
	</div>
	<div class="badge" id="badge-3">
		<input id="chatroombtn-3" type=button></input>
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
				$("#chatroombtn-" + i).val(response[i].debate_room_name);
				$("#badge-" + i).on('click', function() {
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