<%@ page contentType="text/html;charset=utf-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/views/header/head.jsp"/>

<link rel="stylesheet" type="text/css" href="/css/header.css">

</head>
<body>
<header>
	<div class="logo-txt-cover">
	<%@ include file ="/WEB-INF/views/header/topBar.jsp" %>
		<%@ include file="/WEB-INF/views/header/chattingroomlist.jsp"%>
  </div>
  <%@ include file ="/WEB-INF/views/header/logo.jsp" %>
</header>

<main >
<h1>죄송합니다. 지원하지 않는 서비스입니다.</h1>
<h2>메인으로 돌아가주세요</h2>
발생한 예외 : ${pageContext.exception}<br>
예외 메시지 : ${pageContext.exception.message}<br>
</main>

</body>
</html>
