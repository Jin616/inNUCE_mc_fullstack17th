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
		let roomId = ${debateroom.debate_room_key};
		let debateuser = ${debateuser.debate_user_key};
		
		let sockJS = new SockJS("/ws");
		let stomp = Stomp.over(sockJS);
		
		stomp.connect({}, function() {
			
			stomp.subscribe('/sub/debate/' + roomId, function(debateMessage) {
				
				let message = JSON.parse(debateMessage.body);
				
				let key = message.opinion_key;
				let id = message.user_id;
				let contents = message.opinion_contents;
				let regdate = message.opinion_regdate;
				let like = message.opinion_like;
				
				$('.debate_room_opinion_list').append('<p>' + key + ' ' + id + ' ' + contents + ' ' + regdate + ' ' + like + '</p>');
			});
			
		});
		
		$('#sendbtn').on('click', function() {
			let message = $('#message').val();
			stomp.send('/pub/debate/' + roomId, {}, JSON.stringify({debate_user_key : debateuser, opinion_contents : message}));
			$('#message').val('');
		});
		
	});
</script>
</head>
<body>
	<div class="debate_room_name"><h1>${debateroom.debate_room_name }</h1></div>
	<div class="debate_room_opinion_list"></div>
	<div class="send_opinion">
		<input type="text" class="send_message" id="message">
		<button class="send_message" id="sendbtn" type="button">전송</button>
	</div>
</body>
</html>