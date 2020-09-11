<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

	if(${requestScope.m == 1}) {
		alert("친구 추가성공 !!");
		javascript:history.back();
	}
	else {
		alert("친구 추가 실패 !!");
		javascript:history.back();
	}
		
</script>