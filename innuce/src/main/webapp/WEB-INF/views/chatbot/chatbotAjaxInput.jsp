<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="js/jquery-3.7.1.min.js"></script>

</head>
<body>
<form action="chatbotOutput">
질문 입력: <input type=text id='request' name='request'>
<input type=submit value='대화'>
<div id='response'>

</div>
</form>
<script>
	${"form"}.on('submit',function(e) {
		e.preventDefault();
		
		$.ajax({
			url: "main/chatbot",
			data: {"request":$("#request").val() },
			dataType: "json",
			success: function(res) {
				
			}
		})
	})
</script>
</body>
</html>