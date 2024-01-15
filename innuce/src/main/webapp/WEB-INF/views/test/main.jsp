<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>in NUCE</title>
<!-- ICON -->
<link rel="icon" href="/images/inNUCE_logo.png" />
<!-- 구글 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Brygada+1918:ital,wght@1,700&family=Nanum+Gothic:wght@400;700&display=swap" rel="stylesheet">
<!-- 구글 아이콘 -->
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
<!-- css -->
<link rel="stylesheet" type="text/css" href="/css/main.css">
<!-- 브라우저의 초기설정을 reset -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css" />

<style type="text/css">

</style>
</head>
<body>
<div class="full-container">
		<!-- 상단 버튼 -->
  <div class="top-bar">
  
    <div class="logo-txt-cover">
	    <form action="/main">
	    	<input id="logo-txt" type=submit value='in NUCE'> 
	    </form>
    </div>
    
    <div class="btn1-cover">
    	<form action="javascript:void(0)">
        <input class="button" type=button id='btn1' value='SignUp'>
    	</form>
    </div>
    
    <div class="btn2-cover">
    	<form action="javascript:void(0)">
    		<input class="button" type=button id='btn2' value='Login'>
    	</form>
    </div>
    
  </div>

  <!-- LOGO -->
  <div class="logo">
    <div class="logo-ex">
      <span style="color: #333333; font-size: 20px; font-family: 'Brygada 1918', serif; font-weight: 800;">
      	in NUCE <br/>
      </span>
      
      <span style="color: #333333; font-size: 17px; font-weight: 300;">
      	[인누케]<br/>
      	호두(껍데기) 안에 라는 뜻의 라틴어<br/>
      </span>
      
      <span style="color: #333333; font-size: 17px; font-weight: 600;">
      	= 한 마디로 요약하면<br/>
      </span>
    </div>
  </div>
  
  <a href="/main" class="logo">
   <img style="width: 216px; height: 211px; left: 0px; top: 0px; position: absolute;" src="/images/inNUCE_logo.png"/> 
  </a>
    
  
<!-- SEARCH -->
 <div class="search-container" >
     
  <div class="input-keyword">
  	<div class="material-symbols-outlined">search</div>
  	<input type=text placeholder="검색어를 입력해주세요">
  </div>
  
  <div class="search-btn">
   	<input type=submit value='검색'>
  </div>
  
  <div>
  	<form action="javascript:void(0)">
 			<input class="myPlace" type=submit value=''>
  	</form>
  </div>
  
 </div>
    
  <!-- 채팅 -->
  
<!--   <div style="width: 100%; height: 100%; position: relative">
    <div style="width: 1019px; height: 249px; left: 0px; top: 0px; position: absolute; background: #C4C4C4; border-radius: 20px; border: 1px rgba(255, 255, 255, 0.20) solid"></div>
    <div style="left: 26px; top: 8px; position: absolute; color: white; font-size: 20px; font-family: Poppins; font-weight: 600; word-wrap: break-word">실시간 채팅방</div>
    <div style="width: 230px; height: 70px; left: 194px; top: 8px; position: absolute">
        <div style="width: 70px; height: 70px; left: 0px; top: 0px; position: absolute; background: #A07C45; border-radius: 9999px"></div>
        <div style="left: 18px; top: 22px; position: absolute; color: black; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">입장</div>
        <div style="left: 84px; top: 22px; position: absolute; color: white; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">Friends Reunion</div>
    </div>
    <div style="width: 230px; height: 70px; left: 434px; top: 8px; position: absolute">
        <div style="width: 70px; height: 70px; left: 0px; top: 0px; position: absolute; background: #A07C45; border-radius: 9999px"></div>
        <div style="left: 18px; top: 22px; position: absolute; color: black; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">입장</div>
        <div style="left: 84px; top: 22px; position: absolute; color: white; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">Friends Reunion</div>
    </div>
    <div style="width: 230px; height: 70px; left: 674px; top: 8px; position: absolute">
        <div style="width: 70px; height: 70px; left: 0px; top: 0px; position: absolute; background: #A07C45; border-radius: 9999px"></div>
        <div style="left: 18px; top: 22px; position: absolute; color: black; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">입장</div>
        <div style="left: 84px; top: 22px; position: absolute; color: white; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">Friends Reunion</div>
    </div>
    <div style="width: 230px; height: 70px; left: 194px; top: 89px; position: absolute">
        <div style="width: 70px; height: 70px; left: 0px; top: 0px; position: absolute; background: #A07C45; border-radius: 9999px"></div>
        <div style="left: 18px; top: 22px; position: absolute; color: black; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">입장</div>
        <div style="left: 84px; top: 22px; position: absolute; color: white; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">Friends Reunion</div>
    </div>
    <div style="width: 230px; height: 70px; left: 434px; top: 89px; position: absolute">
        <div style="width: 70px; height: 70px; left: 0px; top: 0px; position: absolute; background: #A07C45; border-radius: 9999px"></div>
        <div style="left: 18px; top: 22px; position: absolute; color: black; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">입장</div>
        <div style="left: 84px; top: 22px; position: absolute; color: white; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">Friends Reunion</div>
    </div>
    <div style="width: 230px; height: 70px; left: 674px; top: 89px; position: absolute">
        <div style="width: 70px; height: 70px; left: 0px; top: 0px; position: absolute; background: #A07C45; border-radius: 9999px"></div>
        <div style="left: 18px; top: 22px; position: absolute; color: black; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">입장</div>
        <div style="left: 84px; top: 22px; position: absolute; color: white; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">Friends Reunion</div>
    </div>
    <div style="width: 230px; height: 70px; left: 194px; top: 170px; position: absolute">
        <div style="width: 70px; height: 70px; left: 0px; top: 0px; position: absolute; background: #A07C45; border-radius: 9999px"></div>
        <div style="left: 18px; top: 22px; position: absolute; color: black; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">입장</div>
        <div style="left: 84px; top: 22px; position: absolute; color: white; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">Friends Reunion</div>
    </div>
    <div style="width: 230px; height: 70px; left: 434px; top: 170px; position: absolute">
        <div style="width: 70px; height: 70px; left: 0px; top: 0px; position: absolute; background: #A07C45; border-radius: 9999px"></div>
        <div style="left: 18px; top: 22px; position: absolute; color: black; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">입장</div>
        <div style="left: 84px; top: 22px; position: absolute; color: white; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">Friends Reunion</div>
    </div>
    <div style="width: 230px; height: 70px; left: 674px; top: 170px; position: absolute">
        <div style="width: 70px; height: 70px; left: 0px; top: 0px; position: absolute; background: #A07C45; border-radius: 9999px"></div>
        <div style="left: 18px; top: 22px; position: absolute; color: black; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">입장</div>
        <div style="left: 84px; top: 22px; position: absolute; color: white; font-size: 18px; font-family: Poppins; font-weight: 600; word-wrap: break-word">Friends Reunion</div>
    </div>
</div> -->
  
  
  <div class="debate-container" >
  	<div class="btn">
  		<input type=button value='입장'>
  	</div>
  	<div class="btn">
  		<input type=button value='입장'>
  	</div>
  	<div class="btn">
  		<input type=button value='입장'>
  	</div>
  	<div class="btn">
  		<input type=button value='입장'>
  	</div>
  
  
  	<div style="width: 1019px; height: 256px; left: 0px; top: 0px; position: absolute; opacity: 0.10; background: #A07C45"></div>
  
 	 <div  style="width: 120px; height: 40px; left: 820px; top: 65px; position: absolute; background: rgba(160.44, 123.80, 68.85, 0.55); border-radius: 15px; overflow: hidden; border: 1px #4E3B1E solid; flex-direction: column; justify-content: center; align-items: center; gap: 10px; display: inline-flex">
       <div style="color: #4E3B1E; font-size: 17px; font-family: Inter; font-weight: 300; word-wrap: break-word">입장</div>
   </div>
  
   <div style="width: 120px; height: 40px; left: 820px; top: 191px; position: absolute; background: rgba(160.44, 123.80, 68.85, 0.55); border-radius: 15px; overflow: hidden; border: 1px #4E3B1E solid; flex-direction: column; justify-content: center; align-items: center; gap: 10px; display: inline-flex">
       <div style="color: #4E3B1E; font-size: 17px; font-family: Inter; font-weight: 300; word-wrap: break-word">입장</div>
   </div>
   
   <div style="width: 221.92px; left: 26.02px; top: 197px; position: absolute; color: black; font-size: 17px; font-family: Inter; font-weight: 300; word-wrap: break-word"># 애플 토론방 </div>
   
   <div style="width: 120px; height: 40px; left: 820px; top: 151px; position: absolute; background: rgba(160.44, 123.80, 68.85, 0.55); border-radius: 15px; overflow: hidden; border: 1px #4E3B1E solid; flex-direction: column; justify-content: center; align-items: center; gap: 10px; display: inline-flex">
       <div style="color: #4E3B1E; font-size: 17px; font-family: Inter; font-weight: 300; word-wrap: break-word">입장</div>
   </div>
   
   <div style="width: 291.40px; left: 26.02px; top: 154px; position: absolute; color: black; font-size: 17px; font-family: Inter; font-weight: 300; word-wrap: break-word"># 이스라엘 토론방 </div>
   
   <div style="width: 120px; height: 40px; left: 820px; top: 108px; position: absolute; background: rgba(160.44, 123.80, 68.85, 0.55); border-radius: 15px; overflow: hidden; border: 1px #4E3B1E solid; flex-direction: column; justify-content: center; align-items: center; gap: 10px; display: inline-flex">
       <div style="color: #4E3B1E; font-size: 17px; font-family: Inter; font-weight: 300; word-wrap: break-word">입장</div>
   </div>
   
   <div style="width: 271.23px; left: 26.02px; top: 111px; position: absolute; color: black; font-size: 17px; font-family: Inter; font-weight: 300; word-wrap: break-word"># LG전자 토론방 </div>
   
   
   
   <div style="width: 257.78px; left: 26.02px; top: 68px; position: absolute; color: black; font-size: 17px; font-family: Inter; font-weight: 300; word-wrap: break-word"># 경기도 토론방 </div>
   
   <div style="width: 31.38px; height: 12px; left: 960.46px; top: 21px; position: absolute; opacity: 0.60; background: black; border-radius: 5px"></div>
   
   <div style="width: 197.26px; left: 758.83px; top: 18px; position: absolute; text-align: center; color: black; font-size: 15px; font-family: Inter; font-weight: 400; word-wrap: break-word">채팅방 더보기</div>
   
   <div style="width: 275.71px; height: 25px; left: 17.34px; top: 15px; position: absolute; text-align: center; color: black; font-size: 15px; font-family: Inter; font-weight: 400; word-wrap: break-word">실시간 인기 토론방</div>
   
   
 </div>
 
 
 <!-- HOT WORD --> 
 <div style="width: 1019px; height: 526px; left: 451px; top: 2234px; position: absolute">
     <div style="width: 1019px; height: 63px; left: 0px; top: 0px; position: absolute; background: #D9D9D9; border-top-left-radius: 15px; border-top-right-radius: 15px"></div>
     <div style="width: 625px; height: 62.61px; left: 221px; top: 0px; position: absolute; justify-content: center; align-items: center; display: inline-flex">
         <div style="width: 625px; height: 62.61px; text-align: center; color: #171A22; font-size: 25px; font-family: Montserrat; font-weight: 500; text-transform: uppercase; word-wrap: break-word">HOT WORD</div>
     </div>
     <img style="width: 1019px; height: 462px; left: 0px; top: 64px; position: absolute; border-top-left-radius: 15px; border-top-right-radius: 15px" src="https://via.placeholder.com/1019x462" />
 </div>
 
 
 
 
 
 
 
</div> <!-- full-container -->



</body>
</html>


