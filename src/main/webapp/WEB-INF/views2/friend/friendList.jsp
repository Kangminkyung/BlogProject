<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style type="text/css">

	
	table#personInfoTbl tr {
		/* line-height: 100%; */
	}
	
	table#personInfoTbl td {
		border: 0px solid gray;
		border-collapse: collapse;
		font-size: 10pt;
	}
	
	table#personInfoTbl td.title {
		text-align: justify;
		font-size: 10pt;
		font-weight: bold;
	}
</style> 




<style type="text/css">
	
   div.div_inlineblock {display: inline-block;
   						border: 0px solid red;
   						margin-top: 20px;}

   .th {text-align: center;}
   .td {text-align: center;}
   
   .namestyle {background-color: cyan;  /* cyan => 하늘색 */
      	       font-weight: bold;
    	       font-size: 12pt;
    	       color: blue;
    	       cursor: pointer; }
  
</style>

<script type="text/javascript">
  
  $(document).ready(function(){
  $("#searchEmail").val("${searchEmail}");
	  
	  $("#searchEmail").bind("keydown", function(event){
		  var keyCode = event.keyCode;
		  if(keyCode == 13) {
			  goFriendSearch();
		  }
	  }); 
	  
/*
		$("#displayData").click(function(event){
			var word = "";
			var $target = $(event.target);
			if($target.is(".first")){
				word = $target.text() + $target.next().text() + $target.next().next().text();
			}
			else if($target.is(".second")) {
				word =  $target.prev().text() + $target.text() + $target.next().text();
			}
			else if($target.is(".third")) {
				word =  $target.prev().prev().text() + $target.prev().text() + $target.text();
			} 
			 word = $target.text(); 
			$("#searchEmail").val(word);
			// 텍스트박스에 검색된 결과의 문자열을 입력해준다.
			
			$("#display").hide();
			
			goSearch();
			});
	
		
	  
	  $("#searchEmail").val("${searchEmail}");
	  
	  $("#searchEmail").bind("keydown", function(event){
		  var keyCode = event.keyCode;
		  if(keyCode == 13) {
			  goSearch();
		  }
	  }); 
	  
	  
	  $(".name").bind("mouseover", function(event){
		  var $target = $(event.target);
		  $target.addClass("namestyle");
	  });
	  
	  
	  $(".name").bind("mouseout", function(event){
		  var $target = $(event.target);
		  $target.removeClass("namestyle");
	  });
*/	  
  });// end of $(document).ready()----------------------------------
	
	
	function goFriendSearch() {
			  
	     if( $("#searchEmail").val().trim() == "") {
	 		// 검색어가 공백으로만 되었다면 
	 		   alert("검색어를 입력하세요!!");
	 		   $("#searchEmail").val("");
	 		   $("#searchEmail").focus();
	 		
	 		 /*
	 		     javascript:history.go(-1); ==> 뒤로가기
	 		     javascript:history.go(1);  ==> 앞으로가기
	 		     javascript:history.go(0);  ==> 새로고침
	 		     
	 		     javascript:history.back();    ==> 뒤로가기
	 		     javascript:history.forward(); ==> 앞으로가기
	 		  */
	 	  }
	 	  else {
	 		  	var frm = document.friendrFrm;
	 	
	 		  	frm.method="get";
	 		  	frm.action="friendList.action";
	 		  	frm.submit();
	 	  }
	   }// end of function goSearch()-----------------------------------
	
	
</script>

<div class="container" style="width: 90%;">
<form name="friendrFrm">	
	<h2 align="center">Friend</h2>
	<table class="table table-condensed">
	
	<!-- 로그인 계정과 현 타임라인 계정이 같을때, ==> 친구승인, 친구취소 버튼 -->
	<thead>
			<tr>
				<th style="width: 25%;">Email</th>
				<th style="width: 25%">Date</th>
				<th style="width: 25%">Status</th>
				
			</tr>
		</thead>	
		<tbody>
			<c:forEach var="friend" items="${friendList}">
				<tr>
					<td><a href="<%= request.getContextPath() %>/timeLine.action?email=${friend.friend_email}" >${friend.friend_email}</a></td>
					<td>${friend.friend_date}</td>
					<td>
						<c:if test="${friend.fk_email == sessionScope.loginuser.email }">
							
						<a style="font-weight: bold; margin-left: 30px;" href="<%= request.getContextPath()%>/friendDel.action?friend_email=${friend.friend_email}">삭제</a>
							
						</c:if>
						</td>
					
					<input type="hidden" name="fk_email" id="fk_email" value="${friend.fk_email }"/>	
				</tr>
				</c:forEach>
			<c:if test="${empty friendList}">
				   <tr>
				      <td colspan="3" style="text-align: center">
				      		친구 회원이 없습니다.				      		
				      </td>
				   </tr>
			</c:if>
							
			</tbody>
		</table>
				
		<div align="center" class="input-group" style="display: inline-block; margin-left: 400px;">
			<input type="text" placeholder="이메일 검색" name="searchEmail" id="searchEmail" >				
			<button class="btn btn-default" type="submit">
				<i class="glyphicon glyphicon-search" onClick="goFriendSearch();"></i>
			</button>			
		</div>
		

</form>

<form name="searchfriendrFrm">	
<input type="hidden" name="searchEmail" />	
</form>
		
		<form name="goEmailFrm">
		<input type="hidden" name="friend_email" />
		<input type="hidden" name="fk_email" />
		
	</form>		
</div>



</body>
</html>