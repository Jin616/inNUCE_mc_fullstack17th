<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(document).ready(function(){
		
	});
</script>
</head>
<body>
	<div class="open_debate_list">
		<table border="1">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>생성 일자</th>
					<th>실시간 참여자 수</th>
					<th>전체 참여자 수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${openDebateRoomList}" varStatus="room">
					<tr id="">
						<td>${room.count }</td>
						<td><a href="/debate/${room.current.debate_room_key }">${room.current.debate_room_name }</a></td>
						<td>${room.current.debate_room_regdate }</td>
						<td>${room.current.participants_num }</td>
						<td>${openDebateRoomUserCountList[room.index]}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>