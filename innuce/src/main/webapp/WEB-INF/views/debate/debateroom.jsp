<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mc.innuce.domain.user.dto.UserDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

<script>
	$(document).ready(function(){
		// Model로 전달 받은 데이터
		let roomId = ${debateroom.debate_room_key};
		let debateuser = ${debateuser.debate_user_key};
		
		// websocketconfig의 endpoint 연결
		let sockJS = new SockJS("/ws");
		let stomp = Stomp.over(sockJS);
		
		// stomp 연결
		stomp.connect({}, function() {
			// stomp 연결 시 구독하여 헤당 토론방에 전송된 메시지 수신
			stomp.subscribe('/sub/debate/' + roomId, function(debateMessage) {
				// 수신된 메시지를 채팅창 마지막에 붙임
				currentMessage(createMessage(debateMessage), $('.debate_room_opinion_list'));
			});
			
			// 채팅방 인원 정보 메시지 구독
			stomp.subscribe('/sub/debate/participants/' + roomId, function(participants) {
				// 참여 인원 정보를 화면에서 update
				updateParticipants(participants, $('#connected_user_count'), $('#participated_user_count'));
			});
			
			// 채팅방 입장시 메시지 전송
			// 실시간 참여 인원 + 1
			stomp.send('/pub/debate/connected/' + roomId, {}, JSON.stringify({message : 'connected'}));
		});
		
		// 전송 버튼 클릭 시 구독한 방으로 메시지 전송
		$('#sendbtn').on('click', function() {
			let message = $('#message').val();
			stomp.send('/pub/debate/' + roomId, {}, JSON.stringify({debate_user_key : debateuser, opinion_contents : message}));
			$('#message').val('');
		});
		
		// 이전 채팅 버튼 클릭 시 보여지는 채팅 이전의 채팅을 가져와 보여주기
		$('#loadbtn').on('click', function() {
			loadMessage($('.debate_room_opinion_list'), roomId);
		});
		
		// 해당 페이지를 나갈때 나가기 전 동작
		$(window).on('beforeunload', function(){
	        // 채팅방 페이지 나가기 메시지 전송하여 실시간 참여 인원 -1
			stomp.send('/pub/debate/disconnected/' + roomId, {}, JSON.stringify({message : 'disconnected'}));
			// stomp를 disconnect
	        stomp.disconnect();
	    });
		
		// 채팅방 나가기 기능
		// debate_user_key를 받아 채팅방 참여 인원에서 제외한다
		// debate_user에서 status 변경
		// 채팅방을 완전히 나가는 메시지 전송
		$('#leavebtn').on('click', function() {
			// 메시지 전송하여 참여 상태 0으로 변경
			stomp.send('/pub/debate/leaveroom/' + roomId, {}, JSON.stringify({message : debateuser}));
			// 이전 페이지 url 호출
			window.location.href = document.referrer;
		});
	});
	
	
	
	
	// position(채팅 공간)의 가장 이전의 채팅 key값과 debate_room_key로 그 이전의 채팅들을 가져와 보여주기
	function loadMessage(position, roomkey){
		let loadkey = position.children().first().attr('id');
		
		if(typeof loadkey == 'undefined'){
			loadkey = '0';
		}
		
		let params = {
				"opinion_key" : loadkey,
		}
		
		$.ajax({
			type : "post",
			url : "loadmessage/" + roomkey,
			data : params,
			dataType : "json",
			success : function(response) {
				//alert(JSON.stringify(response));
				for(let i = 0; i < response.length; i++) {
					// createMessage 메소드의 파라미터로 들어갈 JSON 형식으로 변환
					let jsonobj = {body : JSON.stringify(response[i])};
					// 채팅을 만들어 순서대로 채팅공간 제일 앞으로 붙이기
					preMessage(createMessage(jsonobj), $('.debate_room_opinion_list'));
				}
			},
			error : function(request, e) {
				alert("코드 : " + request.status + " 메시지 : " + request.responseText + " 오류" + e);
			}
		});
		
	}
	
	// 참여인원 json 데이터와 position 2 개를 받아 position의 데이터 update
	function updateParticipants(participants, connectedPosition, participatedPosition){
		// 수신된 json 데이터 파싱
		let count = JSON.parse(participants.body);
		
		let connectedCount = count.connected;
		let participatedCount = count.participated;
		
		// position의 내용 변경
		connectedPosition.text(connectedCount);
		participatedPosition.text(participatedCount);
	}
	
	// JSON 메시지 데이터를 append 하기 전의 string으로 만들어 반환
	function createMessage(debateMessage){
		// 수신된 메시지
		let message = JSON.parse(debateMessage.body);
		
		// 수신된 메시지 파싱
		let key = message.opinion_key;
		let id = message.user_id;
		let contents = message.opinion_contents;
		let timestamp_regdate = message.opinion_regdate;
		let like = message.opinion_like;
		
		let date_regdate = new Date(timestamp_regdate);
		
		// date_regdate 데이터를 조정하여 출력할 수 있음
		let regdate = date_regdate.toTimeString().split(' ')[0];
		
		// 각 채팅의 id를 key 값으로 줘야 이전 메시지 가져올 수 있음
		// 수정 시 loadMessage function과 함께 수정
		let result = '<p id=' + key + '>' + key + ' ' + id + ' ' + contents + ' ' + regdate + ' ' + like + '</p>';
		return result;
	}
	
	// createMessage로 만들어진 message를 position(채팅 공간)의 마지막 요소에 붙임
	function currentMessage(message, position) {
		position.append(message);
	}
	
	// createMessage로 만들어진 message를 position(채팅 공간)의 첫번째 요소에 붙임
	function preMessage(message, position) {
		position.prepend(message);
	}
	
</script>
</head>
<body>
	<button class="leave_debate_room" id="leavebtn" type="button">채팅방 나가기</button>
	<div class="debate_room_name"><h1>${debateroom.debate_room_name }</h1></div>
	<div class="debate_user-count">
		<table border="1">
			<tr>
				<th>실시간 참여자 수</th>
				<th>전체 참여자 수</th>
			</tr>
			<tr>
				<td id="connected_user_count"></td>
				<td id="participated_user_count"></td>
			</tr>
		</table>
	</div>
	<button class="load_message" id="loadbtn" type="button">이전 채팅</button>
	<div class="debate_room_opinion_list"></div>
	<div class="send_opinion">
		<input type="text" class="send_message" id="message">
		<button class="send_message" id="sendbtn" type="button">전송</button>
	</div>
</body>
</html>