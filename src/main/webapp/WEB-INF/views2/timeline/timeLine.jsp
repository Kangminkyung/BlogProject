<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%@ page import="com.spring.member.model.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">


</head>

<style type="text/css">


</style>

<script type="text/javascript">


function goComment(){	  
	  var frm = document.commentfrm;

  	var form_data = {useremail : $("#useremail").val(),
  			fk_seq: $("#fk_seq").val()};

	//  cultureSearch : $("#cultureSearch").val() 
	//  var data_form = {useremail: frm.useremail.value,
	//		  fk_seq :frm.fk_seq.value};
	
		var PopUp = window.open('comment.action', "comment", "width=500, height=650, left=500, top=100");

	
			$.ajax({
				url:"commentDetail.action",
				data: form_data,
				type: "POST",
				success: function(){			
					if(PopUp) PopUp.location.href=response;
				
				}
			}); 
		
			window.location.reload(true);

		//	 window.location.reload();
			 
}



function goDelete(seq) {
	var frm = document.seqFrm;

	frm.action = "timeLineDel.action";
	frm.method = "post";
	frm.submit();
}

</script>

<div class="container" style="width:95%;">
    	<c:if test="${sessionScope.loginuser.email == email}">
			<div align="center" style="margin-bottom: 30px;">
			<button type="button" style="background-color: #468EDE; color: #ffffff; font-size: 10pt;" onclick="javascript:location.href='<%=request.getContextPath()%>/timeLineAdd.action'">타임라인등록</button>
			</div>
		</c:if>

<div style="background-color: #e2f1f8; margin-left: 50px; margin-right: 50px;">  
<section id="cd-timeline" class="cd-container">
 	<c:if test="${empty timeLineList}">
		<div align="center" style="font-size: 20pt;">타임라인이 없습니다!</div>
	</c:if>

    <c:forEach var="tvo" items="${timeLineList}" varStatus="status">
    
	<c:if test="${tvo.status== 1}">
		<div class="cd-timeline-block" id="cd-timeline-block">
			<input type="hidden" name="email" value="${sessionScope.loginuser.email}" />
			<div class="cd-timeline-img cd-picture">
			<input type="hidden" name="seq" value="${tvo.seq}" />
			</div>
			
			
			<div class="cd-timeline-content cd-left">
				<h2 style="font-size: 10pt;">${tvo.email}</h2>
				<h3 style="font-size: 10pt;">${tvo.writedate}</h3>
				<p style="font-size: 10pt;">${tvo.content}</p>
				
				
				<button type="button" style="font-size: 10pt;" class="btn btn-secondry btn-info" onclick="goComment();">댓글</button>				
				
				<c:if test="${sessionScope.loginuser.email == email}">
					<form name="seqFrm">
					<input type="hidden" id="seq" name="seq"/>
						<button type="button" style="font-size: 10pt;" class="btn btn-secondry btn-info" onclick="javascript:location.href='<%= request.getContextPath() %>/timeLineEdit.action?seq=${tvo.seq}'">수정</button>
						<button type="button" style="font-size: 10pt;" class="btn btn-secondry btn-info" onclick="javascript:location.href='<%= request.getContextPath() %>/timeLineDel.action?seq=${tvo.seq}'">삭제</button>
					</form>
				</c:if>				
			</div> 	
			
			
			
		</div> 
	</c:if>
<form name="commentfrm">
	<input type="hidden" id="fk_seq" name="fk_seq" value="${tvo.seq}"/>
	<input type="hidden" id="useremail" name="useremail" value="${sessionScope.loginuser.email }"/>
</form>
	</c:forEach>



</section>
	


</div> 

</div>





</html>