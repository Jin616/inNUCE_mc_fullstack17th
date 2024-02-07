<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" href="/images/inNUCE_logo.png" />
<script src="/js/jquery-3.7.1.min.js"></script>

</head>
<body>
<form>
질문 입력: <input type=text id='request' name='request'>
<input type=submit value='대화'>
<div id="myQ" style="border:1px solid orange; background-color: royalblue; margin: 10px; 	text-align: end;"></div>
<div id="response" style="border:1px solid royalblue; background-color: orange; margin: 10px;"></div>
</form>
<script>
	$("form").on('submit',function(e) {
		e.preventDefault();
		
		$.ajax({
			url: "/main/chatbotProcess",
			data: {"chotbot":$("#request").val() },
			dataType: "json",
			success: function(json) {
				$("#myQ").append("내 질문: "+$("#request").val()+"<br>");
				
				if (json.bubbles[0].type == "text") {
					$("#response").append("기본답변<br>"+json.bubbles[0].data.description + "<br>");
				} else if (json.bubbles[0].data.cover.type == "text") {
					$("#response").append("멀티링크답변<br>");
					let description=json.bubbles[0].data.cover.data.dexcription;
					let contentTable = json.bubbles[0].data.contentTable;
					for(let i in contentTable) {
						
						for(let j in contentTable[i]) {
							
							let linkstring=contentTable[i][j].data.title;
							let href=contentTable[i][j].data.data.action.data.url;
							
							$("#response").append("<a href='"+href+"'>"+linkstring+"</a><br>");
						}
					}
				}
				
			}//success
		})//ajax
	})//on
</script>
</body>
</html>