<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
let user_id
if(${!empty sessionScope.login_user}){
	user_id = '${sessionScope.login_user.user_id}'
}
else{
	user_id=null
}



</script>