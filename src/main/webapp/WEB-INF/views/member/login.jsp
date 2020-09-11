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

	$(".error").hide();
	
	$(".requiredInfo").each(function(){
		
		$(this).blur(function(){
			var data = $(this).val().trim();
			if(data.length == 0) {
				// 입력하지 않거나 공백만 입력했을 경우
				// alert("입력하지 않거나 공백만 입력했을 경우");
				
				$(this).parent().find(".error").show();
				$(":input").attr("disabled",true).addClass("bgcol");
				$("#btnRegister").attr("disabled",true); 
				$(this).attr("disabled",false).removeClass("bgcol");
			}
			else{
				// 공백이 아닌 글자를 입력한 경우
				// alert("공백이 아닌 글자를 입력한 경우");
				$(this).parent().find(".error").hide();
				$(":input").attr("disabled",false).removeClass("bgcol");
				$("#btnRegister").attr("disabled",false); 
			}
		});
		
	});// end of $(".requiredInfo").each()----------------
	
}); // end of $(document).ready()-----------------------------------


function goLogin(event) {
	
	var flagBool = false;
	
	$(".requiredInfo").each(function(){
		var data = $(this).val().trim();
		if(data == "") {
			flagBool = true;
			return false;
			/*
			   for문에서의 continue; 와 동일한 기능을 하는것이 
			   each(); 내에서는 return true; 이고,
			   for문에서의 break; 와 동일한 기능을 하는것이 
			   each(); 내에서는 return false; 이다.
			*/
		}
	});
	
	if(flagBool) {
		alert("필수입력란은 모두 입력하셔야 합니다.");
		event.preventDefault(); // click event 를 작동치 못하도록 한다.
		return;
	}
	
	else {
		var frm = document.loginFrm;
		frm.method = "post";
		frm.action = "loginEnd.action";
		frm.submit();
	}
	
}// end of goBandAdd(event)------------------


</script>

<body>

<div class="container" style="height: 550px;">
<form name="loginFrm">
 <!--  <form action="loginEnd.action" method="post"> --> 
	
	  <div class="row">
      	<h3 style="text-align:center">로그인 하기</h3>
      		<div class="col" style="margin-left: 350px;">
       			<input type="email" name="email" placeholder="email..." class="requiredInfo" style="width: 500px; padding-top: 20px;"/><br/>
        		<input type="password" name="pwd" placeholder="password..." class="requiredInfo" required style="width: 500px; padding-top: 20px;"/><br/>
				<button type="button" id="btnRegister" class="btn btn-secondry btn-info"  style="margin-right: 10px; margin-bottom: 10px; margin-left:200px; margin-top:50px; width: 100px; " onClick="goLogin(event);">Login</button>
        	<!--	<input type="submit" value="로그인" style="width: 500px;" onclick="goLogin(event);"><br/>   -->
        	</div>
      </div>
      	
      <div class ="row">
      	 <div class="col" style="margin-left: 520px;">
      			<a style="cursor: pointer;" data-toggle="modal" data-target="#userIdfind" data-dismiss="modal">아이디찾기</a>&nbsp;&nbsp;
      			<a style="cursor: pointer;" data-toggle="modal" data-target="#passwdFind" data-dismiss="modal">비밀번호찾기</a>		
    	</div>
   		
      </div>
	</form>
</div>



<%-- ****** 아이디 찾기 Modal ****** --%>
 <!-- <div class="modal fade" id="userIdfind" role="dialog">
    <div class="modal-dialog"> 
    
      <!-- Modal content-->
 <!--     <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close myclose" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">아이디 찾기</h4>
        </div>
        <div class="modal-body" style="height: 300px; width: 100%;">
          <div id="idFind">
          	<iframe style="border: none; width: 100%; height: 280px;" src="<%= request.getContextPath() %>/idFind.action">
          	</iframe>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default myclose" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>   

 --> 
  <%-- ****** 비밀번호 찾기 Modal ****** --%>
 <!--  <div class="modal fade" id="passwdFind" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
 <!--       <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close myclose" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">비밀번호 찾기</h4>
        </div>
        <div class="modal-body">
          <div id="pwFind">
          	<iframe style="border: none; width: 100%; height: 350px;" src="<%= request.getContextPath() %>/pwdFind.action">  
          	</iframe>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default myclose" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
</div>  --> 




</body>
