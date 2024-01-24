<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="/js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/mypage.css">
</head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	
	window.onload = function(){
		
		let input_pw = document.getElementById('user_pw');
		let input_pw_re = document.getElementById('user_pw_re');
		let submit_btn = document.getElementById("submit");
		console.log("윈도우 온로드");
		
		// 아이디 중복체크 확인버튼 눌렀을때
		$('#id_check_button').on('click',function(){
			$.ajax({
				type : 'post',
				url : 'idcheck',
				data : { 'user_id' : $("#user_id").val()},
				dataType: 'json',
				success: function(response){
					//중복된 아이디
					if(response.id_check_result == "아이디 중복"){
						alert("아이디가 중복되었습니다. 다른 아이디를 사용해주세요")
						$("#user_id").focus();
					}
					else if ($("#user_id").val().length == 0){
						alert("아이디를 입력해주세요");
						$("#user_id").focus();
					}
					//정상적 아이디
					else{
						alert("사용이 가능한 아이디입니다. ")
						$("#user_name").focus();
					}
					
					
				}//success
			})//ajax	
		})// id_check_button click
		
		// 우편번호 찾기 버튼 클릭
		$(address_search_button).on("click",function(){
			//카카오 주소찾기 api
		    new daum.Postcode({
		        oncomplete: function(data) {
		            $("#address_number").val(data.zonecode)
		             $("#address_doro").val(data.roadAddress)
		        }
		    }).open();
	
			
		})// address_search_button click
		
		// 회원가입폼 서브밋 버튼 눌렀을떄
		submit_btn.addEventListener("click",function(ev){
			console.log("서브밋 클릭후");
			var phoneRegex =  new RegExp("01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}");  
			
			// 공란 체크들
			if($("#user_id").val().length == 0){
				alert("아이디를 입력해주세요");
				$("#user_id").focus();
				ev.preventDefault();
			}
			else if($("#user_name").val().length == 0)	{
				alert("이름을 입력해주세요");
				$("#user_name").focus();
				ev.preventDefault();
			}
			else if($("#user_pw").val().length == 0){
				alert("비밀번호를 입력해주세요");
				$("#user_pw").focus();
				ev.preventDefault();
			}
			else if($("#user_pw_re").val().length == 0){
				alert("비밀번호를 재입력해주세요");
				$("#user_pw_re").focus();
				ev.preventDefault();
			}
			else if($("#phone").val().length == 0){
				alert("휴대폰 번호를 입력해주세요");
				$("#phone").focus();
				ev.preventDefault();
			}
			else if($("#email").val().length == 0){
				alert("이메일을 입력해주세요");
				$("#email").focus();
				ev.preventDefault();
			}
			
			else if($("#birthday").val().length == 0){
				alert("생년월일을 입력해주세요");
				$("#birthday").focus();
				ev.preventDefault();
			}
			else if($("#gender").val().length == 0){
				alert("성별을 입력해주세요");
				$("#gender").focus();
				ev.preventDefault();
			}
			else if($("#address_number").val().length == 0){
				alert("우편번호를 입력해주세요");
				$("#address_number").focus();
				ev.preventDefault();
			}
			else if($("#address_doro").val().length == 0){
				alert("도로명주소를 입력해주세요");
				$("#address_doro").focus();
				ev.preventDefault();
			}
			else if($("#address_specific").val().length == 0){
				alert("상세주소를 입력해주세요");
				$("#address_specific").focus();
				ev.preventDefault();
			}
			
			// 입력 비밀번호랑 비밀 번호 재확인이 다른 경우
			else if(input_pw.value != input_pw_re.value){
				alert("같은 비밀번호를 입력해주세요");
				$("#input_pw_re").focus();
				ev.preventDefault();
			}
			// 핸드폰 번호 형식이 다른경우
			else if(! phoneRegex.test($("#phone").val()) ){
				alert("핸드폰 번호를 010-XXXX-XXXX 의 형식으로 입력해주세요");
				$("#phone").focus();
				ev.preventDefault();
			}
			else{
				
			}

		});//submit_btn event listener
		
		var register_result = "${register_result}";
		console.log(register_result);
		if (register_result == "정상 회원가입이 되었습니다"){
			alert(register_result);
			location.href ="/main";
		};
		if (register_result == "아이디가 중복되었습니다"){
			alert(register_result);
		};
		
	} // onload
	
	
</script>

<body>
<!--  header -->
<header>
<%@ include file="../header/header.jsp"%>
</header>

	
	<div id ="register box" >
		<form action= "registerresult" method ="post">
		아이디<br><input class="mypage_input" type= text name= "user_id" id="user_id">
		<input id='id_check_button' type = 'button' value ='아이디 중복 체크'><br>
		이름<br><input class="mypage_input" type= text name= "user_name" id="user_name"><br>
		비밀번호<br> <input class="mypage_input" id = "user_pw" type="password" name ="user_pw"><br>
		비밀번호 재확인<br> <input class="mypage_input" id="user_pw_re" type="password" "><br>
		핸드폰 번호<br><input class="mypage_input" type='tel' name="phone" id="phone"> <br>
		e-mail<br><input class="mypage_input"  type=email name="email" id="email"><br>
		생년월일 <br><input type="date" name="birthday" id="birthday"> <br>
		성별<br> <select  name="gender"  id="gender" >
		<option value="M">남자</option>
		<option value="F">여자</option>
		</select> <br>
		주소<br> <input class="mypage_input" type="text" name="address_number" id="address_number" placeholder="우편번호"><input id ="address_search_button" type ="button" value ="우편번호찾기"><br>
		<input class="mypage_input" type="text" name="address_doro" id="address_doro" placeholder="도로명주소"><br>
		<input class="mypage_input" type="text" name="address_specific" id="address_specific" placeholder="상세주소"><br>
		<input type="submit" value="확인"  id="submit">
		</form>
	</div>
	

</body>
</html>