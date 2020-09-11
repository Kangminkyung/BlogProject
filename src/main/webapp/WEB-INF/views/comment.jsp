<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
 <link href="<%=request.getContextPath() %>/resources/css/reset.css" media="screen" rel="stylesheet" type="text/css">
 <link href="<%=request.getContextPath() %>/resources/css/slickQuiz.css" media="screen" rel="stylesheet" type="text/css">
 <link href="<%=request.getContextPath() %>/resources/css/master.css" media="screen" rel="stylesheet" type="text/css">
  
  <script type="text/javascript">

  function exit(){
	  
	//  alert("gg");
	  window.location.reload();
	   
	  self.close();  
  }
  </script>
  
<div>

<div>
	<h1>comment</h1>
	${fk_seq}
${useremail}
${email}


<!-- ==== #84. 댓글쓰기 form 추가 -->
	<div id="comments" class="comments-area text-left">
		<h2 class="comments-title"> 댓글쓰기 </h2>		
		<form name="addWriteFrm">
			<input type="hidden" name="email" value="${sessionScope.loginuser.email}"> 
			성명 : <input type="text" name="email" value="${sessionScope.loginuser.email}" readonly> 
			댓글내용 : <input type="text" name="content" size="80">
	
			<button type="button" onclick="goWrite();" style="color: #FFFFFF; background-color: #468EDE;">쓰기</button>
		</form>
	</div>

	<!-- ==== #95. 댓글내용 보여주기 -->
	<div id="comments" class="comments-area text-left">
		<h2 class="comments-title"> 댓글 </h2>
		<table class="table table-hover" id="table2" style="margin-top: 2%; margin-bottom: 50px;">
		<tr>
			<th style="width: 15%; text-align: center;">댓글작성자</th>
			<th style="width: 50%; text-align: center;">댓글내용</th>
			<th style="text-align: center;">작성일자</th>
		</tr>
		<tbody id="commentDisplay"></tbody>
	<!-- 	<c:if test="${not empty commentList}">
			<c:forEach var="commentvo" items="${commentList}">
				<tr>
					<td style="text-align: center;">${commentvo.name}</td>
					<td>${commentvo.content}</td>
					<td style="text-align: center;">${commentvo.regDate}</td>
				</tr>
			</c:forEach>
		</c:if>
		-->
	</table>
	</div>
	

</div>