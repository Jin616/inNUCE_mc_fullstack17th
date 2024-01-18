<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	var register_result = "${register_result}";
	if (register_result == "정상 회원가입이 되었습니다"){
		alert(register_result);
		location.href ="/main";
	};
	if (register_result == "아이디가 중복되었습니다" || register_result =="올바른 핸드폰 번호 형식을 입력해주세요"){
		alert(register_result);
	};
	
	window.onload = function(){
		
		let input_pw = document.getElementById('user_pw');
		let input_pw_re = document.getElementById('user_pw_re');
		let submit_btn = doument.getElementById("submit");
		
		submit_btn.addEventListener("click",function(ev){
			if(input_pw.value()!= input_pw_re.value()){
				ev.preventDefault();
				alert("같은 비밀번호를 입력해주세요");
			}
			
		});
		
	}
	
	
</script>

<body>

	<div id="header" style= 'border : 2px solid black'>
	헤더영역
	</div>
	
	<div id ="register box" style= 'border : 2px solid red'>
		<form action= "registerresult" method ="post">
		아이디<input type= text name= "user_id">
		e-mail<input type=email name="email"><br>
		이름<input type= text name= "user_name">
		핸드폰 번호<input type='tel' name="phone"> <br>
		비밀번호 <input id = "user_pw" type="password" name ="user_pw">
		생년월일 <input type="date" name="birthday"> <br>
		비밀번호 재확인 <input id="user_pw_re" type="password" ">
		성별 <select  name="gender">
		<option value="M">남자</option>
		<option value="F">여자</option>
		</select> <br>
		주소 <input type="text" name="address">
		<input type="submit" value="확인"  id="submit">
		</form>
	</div>
	

</body>
</html>