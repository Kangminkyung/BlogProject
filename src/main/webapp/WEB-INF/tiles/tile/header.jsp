<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.spring.member.model.MemberVO" %>      
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: #474e5d;
    padding-top: 50px;
}

/* Modal Content/Box */
.modal-content {
    background-color: #ffffff;
    margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
    border: 1px solid #888;
    width: 50%; /* Could be more or less, depending on sceereen size */
}


/* The Close Button (x) */
.close {
    position: absolute;
    right: 35px;
    top: 15px;
    font-size: 40px;
    font-weight: bold;
    color: #f1f1f1;
}

.close:hover,
.close:focus {
    color: #f44336;
    cursor: pointer;
}

/* Clear floats */
.clearfix::after {
    content: "";
    clear: both;
    display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
    .cancelbtn, .signupbtn {
       width: 30%; 
    }
}


</style>
<script>

	$(document).ready(function(){
		
	 
	}); // end of ready 
	
	
	
	function goLogin(){        	
		var data_form = {email : email, pwd : pwd};              	
	    $.ajax({        		
	    url : "login.action",
	    type:"POST",
	    data:data_form,
	    dataType:"JSON",
	    success : function(json){
	         var frm = document.LoginFrm;
	  		 frm.email.value=email;		      		        	
	  		 frm.pwd.value=pwd;
	  		 frm.method="POST";
	  		 frm.action="loginEnd.action";
	  		 frm.submit();						
	     },
	   error: function(request, status, error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}   	
	        		
	  });    	
	}
	     

	function logout(){
		 Kakao.Auth.logout();
	 }

	
</script>
        
<header id="top-header" style="border-bottom: 0.5px solid #e6e6e6;">
  <!-- Navigation -->
  <div class="container" >  
  	<nav class="navbar navbar-light bg-light static-top">
    	<div style="border-left: 50px;">
    	   <a class="navbar-brand" href="index.action">MyTwit</a>
    	    <c:if test="${sessionScope.loginuser != null && sessionScope.loginuser.idx ne '0'}">
    	    <div style="text-align: right; margin-top: 10px;">${sessionScope.loginuser.email} 님 환영합니다.
    	    <a href="logout.action">Logout</a>
    	    <!--  <button class="btn btn-primary" type="button" onclick="<%= request.getContextPath() %>/logout.action" style="margin-left: 900px; margin-top: 10px"">로그아웃</button>  -->
    	  	</div>	
    	   </c:if>
    	   <c:if test="${sessionScope.loginuser == null}">
    	   	<button class="btn btn-primary" type="button" onclick="javascript:location.href='<%=request.getContextPath()%>/login.action'" style="margin-left:80%; margin-top: 10px; width: 80px;">Login</button>
    	   </c:if>
    	</div>
  	</nav>
  	
  </div>
</header>

