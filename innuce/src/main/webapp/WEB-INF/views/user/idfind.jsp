<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>비밀번호찾기</title>

<jsp:include page="/WEB-INF/views/header/head.jsp"/>

<!-- css -->
<link rel="stylesheet" type="text/css" href="/css/main.css">
<link rel="stylesheet" type="text/css" href="/css/mypage.css">

<script>
window.onload = function(){0
$("#id_find_button").on('click',function(){
	if($("#user_name").val().length==0){
		alert("이름을 입력해주세요");
		$("#user_name").focus()
	}
	else if ($("#email").val().length==0){
		alert("이메일을 입력해주세요");
		$("#email").focus()
	}
	else{
		$.ajax({
		type : 'post',
		url : 'idfindresult',
		data : { 'user_name' : $("#user_name").val(), 'email' : $("#email").val() },
		dataType: 'json',
		success : function(response){
			let result;
			// 일치하는 회원이 없는경우
			if(response[0].length == 0){
				result = "검색된 결과가 없습니다"+"<br>"
				result += "<input type='button' value='이전페이지로 이동' id='back_button' onclick ='location.href =&quot;idpwfind &quot;'>"
			}
			else{
				result = "<table id= 'id_seach_table'>"+
				"<thead><tr> <th> 가입된 아이디</th> <th> 가입일자 </th> </tr></thead><tbody>"
				for (i=0 ; i<response[0].length; i++){
					result +="<tr> <td>"+response[0][i]+"</td><td>"+response[1][i]+"</td>"
				}
				result += "</tbody></table>"
				result += "<input type='button' value='비밀번호 찾기 페이지로 이동' id='pw_find_button'>"
			}
			$("#id_find_main").html(result)// html
			
			$("#pw_find_button").on("click",function(){
				location.href ="pwfind"
			})//pw_find_button click
		}//success
		}); //ajax 
	}//else
	}); //id_find_button click	
}// window onload
</script>
</head>
<body>
<!--  header -->
<header>
	<div class="logo-txt-cover">
   <%@ include file ="/WEB-INF/views/header/topBar.jsp" %>
  </div>
</header>

<div id="id_find_main">
이름<br>
<input class="mypage_input" type = 'text' id="user_name" name="user_name" ><br>
이메일<br>
<input class="mypage_input" type = 'email' id="email" name="email" ><br>
<input type="button" id="id_find_button" value="아이디 찾기">
</div>

</body>
</html>