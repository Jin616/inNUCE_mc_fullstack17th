<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>질문 : ${param.request }</h4>
	<h4>답변 : ${result }</h4>
	
	<h4 id='length'></h4>
	<h4 id='response'></h4>
	<script>
		let json = JSON.parse('${result }');
		document.getElementById("length").innerHTML = json.bubbles.length
				+ "<br>";

		if (json.bubbles[0].type == "text") {
			document.getElementById("response").innerHTML 
			= "기본답변<br>"+json.bubbles[0].data.description + "<br>";
		} else if (json.bubbles[0].data.cover.type == "text") {
			document.getElementById("response").innerHTML 
			= "멀티링크답변<br>"+"<br>";
			let description=json.bubbles[0].data.cover.data.dexcription;
			let contentTable = json.bubbles[0].data.contentTable;
			for(let i in contentTable) {
				
				for(let j in contentTable[i]) {
					
					let linkstring=contentTable[i][j].data.title;
					let href=contentTable[i][j].data.data.action.data.url;
					
					document.getElementById("response").innerHTML +=
						"<a href='"+href+"'>"+linkstring+"</a><br>";
				}
			}
		}
	</script>
</body>
</html>