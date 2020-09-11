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
  
 
	function goFriendAllow(fk_email) {
		alert(fk_email);

		var frm = document.goEmailFrm;
		frm.friend_email.value = friend_email;
		frm.fk_email.value = fk_email;

		frm.action = "friendAllow.action";
		frm.method = "get";
		frm.submit();
	}// end of function goFriendAdd()---------------------
		
	function goFriendDel(fk_email) {
				
		var frm = document.goEmailFrm;
		frm.friend_email.value = friend_email;
		frm.fk_email.value = fk_email;
				
		frm.action = "friendDel.action";
		frm.method = "get";
		frm.submit();
	}// end of function goFriendDel()---------------------	
	
</script>

<div class="container" style="width: 90%;">
<form name="friendrFrm">	
	<h2 align="center">FriendAllow</h2>
	<table class="table table-condensed">
	
	<!-- 로그인 계정과 현 타임라인 계정이 같을때, ==> 친구승인, 친구취소 버튼 -->
	<thead>
			<tr>
				<th style="width: 25%;">Email</th>
				<th style="width: 25">date</th>
				<th style="width: 25%;">status</th>
			</tr>
		</thead>	
		<tbody>
			<c:forEach var="friend" items="${friendList}">
				<tr>
					<td><a href="<%= request.getContextPath() %>/timeLine.action?email=${friend.fk_email}" >${friend.fk_email}</a></td>
					<td>${friend.friend_date}</td>
					<c:if test="${friend.status == 1}">
						<td>친구신청
							<c:if test="${friend.friend_email == sessionScope.loginuser.email }">
							
							     <a style="font-weight: bold; margin-left: 30px;" href="<%= request.getContextPath()%>/friendAllow.action?fk_email=${friend.fk_email}">승인</a>
		
							</c:if>
						</td>
					</c:if>
					<c:if test="${friend.status == 2}">
						<td>
							친구
							<c:if test="${friend.friend_email == sessionScope.loginuser.email }">
							     <a style="font-weight: bold; margin-left: 30px;" href="<%= request.getContextPath()%>/friendAllowDel.action?fk_email=${friend.fk_email}">취소</a>
							</c:if>
						</td>
					</c:if>
					
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
				
		
</form>
	
	<form name="goEmailFrm">
		<input type="hidden" name="friend_email" />
		<input type="hidden" name="fk_email" />
	</form>		
</div>



</body>
</html>