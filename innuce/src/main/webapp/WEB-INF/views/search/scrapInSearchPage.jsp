<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
window.onload = function(){
	
	
	// 세션에 로그인된 유저가 없으면 스크랩 기능을 막기 위한 코드
	if(${empty sessionScope.login_user} ){
	
		$('main i').on('click',function(){
			
			var user_will_login = window.confirm("로그인이 필요한 서비스입니다. 로그인 하시겠습니까?")
			// 로그인 하겠다고 하는 경우
			if(user_will_login == true){
				location.href ='/login'
			}
			// 로그인 안한다고 하는경우 (그냥 confirm창만 닫아주면 됨.)
			else{}
			
		})//on
	}//if
	// 세션에 로그인 된 유저 있으면 스크랩 기능을 준다
	else{
		
		let user_id = '${sessionScope.login_user.user_id}'
		
		// 해당 유저가 스크랩한 기사리스트
		let scrap_list=[];
		//우선 ajax로 해당 유저가 스크랩한 기사들의 news_key를 ajax로 받아온다
		$.ajax({
			type : 'post',
			url : 'getscraplist',
			data : {'user_id' : user_id},
			success : function(response){
				
				for (var i = 0 ; i < response.length;i++){
					scrap_list.push(response[i])
				}//for
					
		
		
		// 유저가 스크랩한 기사인경우 북마크바 속성 클릭된거로 바꿔주기
		for(var i = 0; i < $('.bookmark-cover').find('i').length; i++){
			
			if(scrap_list.includes(Number($("[scrapNum]")[i].getAttribute('news')))){
				$("[scrapNum]")[i].setAttribute("class",'fa-solid fa-bookmark checked')
				
			}
			else{
				$("[scrapNum]")[i].setAttribute("class",'fa-solid fa-bookmark')
				
			}//else
		}//for
		
		
		$('main i').on('click',function(){
			// 이미 스크랩 된 기사인경우
			if($(this).attr("class")=='fa-solid fa-bookmark checked'){
				
				// 북바크 색상 변경
				$(this).attr("class",'fa-solid fa-bookmark')
				// 북마크 했다는 사실 서버/db에 알리기
				$.ajax({
					type : 'post',
					url : 'scrapnewscancel',
					data : {'user_id' : user_id, 'news_key' : $(this).attr('news')},
					success : function(response){
			/* 			alert("스크랩이 취소 됐습니다") */
						
					}//success
				})//ajax
			}
			// 아닌경우
			else{
				
				// 북바크 색상 변경
				$(this).attr("class",'fa-solid fa-bookmark checked')
			
				// 북마크 했다는 사실 서버/db에 알리기
				$.ajax({
					type : 'post',
					url : 'scrapnews',
					data : {'user_id' : user_id, 'news_key' : $(this).attr('news')},
					success : function(response){
						/* alert("스크랩이 완료 됐습니다") */
						
					}//success
				})//ajax
			}//else
		})// on
		}//success
	})//ajax
	}//else
	
}//onload
</script>