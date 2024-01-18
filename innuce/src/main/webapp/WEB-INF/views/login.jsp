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
		let id = document.getElementById("user_id");
		let pw = document.getElementById("user_pw");
		
		$("#loginButton").on('click',function(ev) {
			// 아이디나 패스워드 공란
			if($('#user_id').val() == null || $('#user_pw').val()==null) {
				alert("아이디/비밀번호를 입력해 주세요.");
			}
			
			$.ajax({
				type : 'post',
				url : 'loginresult',
				data : { 'user_id' : $("#user_id").val(), 'user_pw' : $("#user_pw").val() },
				dataType: 'json',
				success : function(response){
						alert(response.login_result);
						if(response.login_result == "로그인 성공."){
							window.location.href = "main"
						}
					}		
				})
			
		})//on
		
		
		
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
			<input id="user_id" name="user_id" type="text">
		</div>

		<div class="pw">
			<div class="text">비밀번호</div>
			<input id="user_pw" name="user_pw" type="password">
		</div>

	</div>

	<div class="button">
		<input id="loginButton" type="button" value="로그인">&nbsp;&nbsp;
		
	</div>
	
</body>

</html>