<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>로그인 폼</title>
	
	<script src="/js/jquery-3.7.1.min.js"></script>
	<script>
	
	
	$(document).ready(function() {
		let cancelButton = document.getElementById("cancelButton");
		let id = document.getElementById("id");
		let pw = document.getElementById("pw");
		
		$("#loginButton").on('click',function() {
			
			if(id.value =='' || pw.value=='') {
				alert("아이디/비밀번호를 입력해 주세요");
			} else {
			
			$.ajax({
				url:"/",
				data: {'id':$("#id").val(),'pw':$("#pw").val()},
				type: "post",
				dataType :"json",
				success: function(response) {
          let message = response.message;
          alert(message);
				},
				error : function(request, e){
					alert("코드=" + request.status + " 메시지=" + request.responseText + " 오류=" + e);
				}
			})//ajax 
			}//else
		})//on
		
		cancelButton.addEventListener('click', () => {
			alert("취소를 클릭하셨습니다.");
		});
		
		
	}); // ready
	
	</script>
	<style>
		h1 {
			margin: 30px;
			display: flex;
			justify-content: center;
		}

		.id,
		.pw {
			display: flex;
			justify-content: center;
			position: relative;
			margin: 5px
		}

		.text {
			position: absolute;
			left: 0px;
		}

		.button {
			display: flex;
			justify-content: center;
			margin: 15px;
		}
	</style>
</head>

<body>
	<h1>로그인</h1>
	<hr>
	<div class="input">
		<div class="id">
			<div class="text">아이디</div>
			<input id="id" name="id" type="text">
		</div>

		<div class="pw">
			<div class="text">비밀번호</div>
			<input id="pw" name="pw" type="password">
		</div>

	</div>

	<div class="button">
		<input id="loginButton" type="button" value="로그인">&nbsp;&nbsp;
		<input id="cancelButton" type="button" value="취   소">
	</div>

</body>

</html>