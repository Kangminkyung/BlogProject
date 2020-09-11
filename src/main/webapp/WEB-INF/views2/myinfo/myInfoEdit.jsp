<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.spring.member.model.MemberVO" %>      

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-signin-client_id" content="449624286584-1hjfpvu0g9ugqrmmft15io47nerj8d2j.apps.googleusercontent.com">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
}

* {
  box-sizing: border-box;
}

/* style the container */
.container {
  position: relative;
  border-radius: 5px;  
} 

/* style inputs and link buttons */
input,
.btn {
  width: 100%;
  border: none;
  border-radius: 4px;
  margin: 5px 0;
  opacity: 0.85;
  display: inline-block;
  font-size: 17px;
  line-height: 20px;
  text-decoration: none; /* remove underline from anchors */
}

input:hover,
.btn:hover {
  opacity: 1;
}

input{
	background-color: #f2f2f2;
}
</style>

<%

%>

</head>

<script type='text/javascript'>


$(document).ready(function(){

	var flag = 0;
		
		$(".error").hide();

		$("#pwdcheck").blur(function(){
			var passwd = $("#pwd").val();
			var passwdCheck = $(this).val();
			
			if(passwd != passwdCheck) {
				$(this).parent().find(".error").show();
				$(":input").attr("disabled",true).addClass("bgcol");
				$("#btnUpdate").attr("disabled",true);
				
			//	$("#pwd").attr("disabled",false).removeClass("bgcol");
				$(this).attr("disabled",false).removeClass("bgcol");
			}
			else {
				$(this).parent().find(".error").hide();
				$(":input").attr("disabled",false).removeClass("bgcol");
				$("#btnUpdate").attr("disabled",false);
			}
			
		});// end of $("#pwdcheck").blur()--------------		
		

	
}); // end of $(document).ready()-----------------------------------


function goEdit(event) {
	
	var flagBool = false;
	
	$(".requiredInfo").each(function(){
		var data = $(this).val().trim();
		if(data == "") {
			flagBool = true;
			return false;
			
		}
	});
	
	if(flagBool) {
		alert("필수입력란은 모두 입력하셔야 합니다.");
		event.preventDefault(); // click event 를 작동치 못하도록 한다.
		return;
	}
	
	
	else {
		var frm = document.editFrm;
		frm.method = "post";
		frm.action = "myInfoEditEnd.action";
		frm.submit();
	}
	
}// end of goBandAdd(event)------------------


</script>

<body>

<div class="container" style="width:95%;">
<form name="editFrm">
 <!--  <form action="loginEnd.action" method="post"> --> 
	
      <h3 style="text-align:center">비밀번호 변경하기</h3>
      	<div class="col" style="margin-left: 100px;">
      		<p>비밀번호 입력</p>
        	<input type="password" name="pwd" id="pwd" placeholder="새롭게 바꿀 비밀번호를 입력하세요" class="requiredInfo" required style="width: 500px; padding-top: 20px;"/><br/>
        		<p>비밀번호 확인</p>
        		
        	<input type="password" name="pwdcheck" id="pwdcheck"placeholder="비밀번호 확인" class="requiredInfo" required style="width: 500px; padding-top: 20px;"/><br/>
        	<span class="error">암호가 일치하지 않습니다.</span>
        		
			<button type="button" id="btnRegister" class="btn btn-secondry btn-info"  style="margin-right: 10px; margin-bottom: 10px; margin-left:200px; margin-top:50px; width: 100px; " onClick="goEdit(event);">수정</button>
        	<input type="hidden" id="email" name="email" value="${sessopnScope.loginuser.email }">
        </div>

	</form>
</div>


</body>
