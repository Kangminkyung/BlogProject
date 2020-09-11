<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

	if(${requestScope.n == 1}) {
		alert("친구 취소 성공 !!");
		location.href = "<%= request.getContextPath() %>/friendAllowList.action?email=${sessionScope.loginuser.email}";
	}
	else {
		alert("친구 취소 실패 !!");
		javascript:history.back();
	}
		
</script>