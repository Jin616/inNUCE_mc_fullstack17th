<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/views/header/head.jsp"/>

<link rel="stylesheet" type="text/css" href="/css/header.css">
<link rel="stylesheet" type="text/css" href="/css/error.css">

</head>
<body>
<header>
	<div class="logo-txt-cover">
	<%@ include file ="/WEB-INF/views/header/topBar.jsp" %>
  </div>
</header>

<main>
<div class="error-container">

   <div class="logo-container">
	   
	   <div class='img-container'>
		  <a href="/main" class="logo-img">
		   <img src="/images/inNUCE_logo.png"/> 
		  </a>
	  </div>
  
	  <div style="font-size:30px; margin-top:150px;">죄송합니다. 지원하지 않는 서비스입니다.</div>
		<div style="font-size:30px">로고를 클릭해서 메인으로 돌아가주세요</div>
  </div>



</div>

</body>
</html>
