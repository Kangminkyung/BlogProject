<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.Calendar"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- ===== #38.  tiles 중 sideinfo 페이지 만들기 ===== --%>
<style>
*, *:after, *:before {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

body {
  font-size: 100%;
  color: #7f8c97;  
  background-color: #FFFFFF;
} 



/* -------------------------------- 

Modules - reusable parts of our design

-------------------------------- */
.cd-container {
  /* this class is used to give a max-width to the element it is applied to, and center it horizontally when it reaches that max-width */
  width: 90%;
  max-width: 1170px;
  margin: 0 auto;
}
.cd-container::after {
  /* clearfix */
  content: '';
  display: table;
  clear: both;
}

/* -------------------------------- 

Main components 

-------------------------------- */


#cd-timeline {
  position: relative;
  padding: 2em 0;
  margin-top: 2em;
  margin-bottom: 2em;
}
#cd-timeline::before {
  /* this is the vertical line */
  content: '';
  position: absolute;
  top: 0;
  left: 18px;
  height: 100%;
  width: 4px;
  background: #d7e4ed;
}
@media only screen and (min-width: 1170px) {
  #cd-timeline {
    margin-top: 3em;
    margin-bottom: 3em;
  }
  #cd-timeline::before {
    left: 50%;
    margin-left: -2px;
  }
}

.cd-timeline-block {
  position: relative;
  margin: 2em 0;
}
.cd-timeline-block:after {
  content: "";
  display: table;
  clear: both;
}
.cd-timeline-block:first-child {
  margin-top: 0;
}
.cd-timeline-block:last-child {
  margin-bottom: 0;
}
@media only screen and (min-width: 1170px) {
  .cd-timeline-block {
    margin: 4em 0;
  }
  .cd-timeline-block:first-child {
    margin-top: 0;
  }
  .cd-timeline-block:last-child {
    margin-bottom: 0;
  }
}
.cd-left {
    float: left;
}

.cd-left::before {
	top: 24px;
	left: 100%!important;
    border-color: transparent!important;
    border-left-color: white!important;
}

.cd-right {
	float: right;
}

.cd-rigth::before {
	top: 24px;
    left: 100%;
    right: auto;
    border-color: transparent;
    border-right-color: white;
}

@media screen and (max-width: 1169px) and (min-width: 240px) {
	.cd-timeline-content::before{
		border: none!important;
	}
	,cd-timeline-content {
		display: block;
	}
	.cd-left, .cd-right {
		float: left;
	}
	.cd-left::before {
		top: 15px!important;
		right: 100%!important;
		left: auto !important;
		border: 7px solid transparent!important;
		border-color: transparent!important;
		border-right: 7px solid white !important
	}
	
	.cd-timeline-img strong {
		font-size: 15px!important;
	}
	
}


.cd-timeline-img strong {
	font-size: 21px;
}


.cd-timeline-img {
	font-size: 21px;
    position: absolute;
    top: 0;
    left: 0;
    width: 48px;
    height: 48px;
    -webkit-border-radius: 50%;
    border-radius: 50%;
    background-color: #474e5d;
    color: #9c9c9c;
    text-align: center;
    line-height: 1;
    font-size: 12px;
    padding-top: 6px;
    -webkit-transform: translateZ(0);
    -webkit-backface-visibility: hidden;
}


.cd-timeline-img.cd-movie {
  background: #c03b44;
}
.cd-timeline-img.cd-location {
  background: #f0ca45;
}
@media only screen and (min-width: 1170px) {
  .cd-timeline-img {
    width: 60px;
    height: 60px;
    left: 50%;
    margin-left: -30px;
    /* Force Hardware Acceleration in WebKit */
    -webkit-transform: translateZ(0);
    -webkit-backface-visibility: hidden;
  }
  .cssanimations .cd-timeline-img.is-hidden {
    visibility: hidden;
  }
  .cssanimations .cd-timeline-img.bounce-in {
    visibility: visible;
    -webkit-animation: cd-bounce-1 0.6s;
    -moz-animation: cd-bounce-1 0.6s;
    animation: cd-bounce-1 0.6s;
  }
}

@-webkit-keyframes cd-bounce-1 {
  0% {
    opacity: 0;
    -webkit-transform: scale(0.5);
  }

  60% {
    opacity: 1;
    -webkit-transform: scale(1.2);
  }

  100% {
    -webkit-transform: scale(1);
  }
}
@-moz-keyframes cd-bounce-1 {
  0% {
    opacity: 0;
    -moz-transform: scale(0.5);
  }

  60% {
    opacity: 1;
    -moz-transform: scale(1.2);
  }

  100% {
    -moz-transform: scale(1);
  }
}
@keyframes cd-bounce-1 {
  0% {
    opacity: 0;
    -webkit-transform: scale(0.5);
    -moz-transform: scale(0.5);
    -ms-transform: scale(0.5);
    -o-transform: scale(0.5);
    transform: scale(0.5);
  }

  60% {
    opacity: 1;
    -webkit-transform: scale(1.2);
    -moz-transform: scale(1.2);
    -ms-transform: scale(1.2);
    -o-transform: scale(1.2);
    transform: scale(1.2);
  }

  100% {
    -webkit-transform: scale(1);
    -moz-transform: scale(1);
    -ms-transform: scale(1);
    -o-transform: scale(1);
    transform: scale(1);
  }
}
.cd-timeline-content {
  position: relative;
  margin-left: 60px;
  background: white;
  border-radius: 0.25em;
  padding: 1em;
  /*box-shadow: 0 3px 0 #d7e4ed;*/
  border: 1px solid #ddd;
}
.cd-timeline-content:after {
  content: "";
  display: table;
  clear: both;
}
.cd-timeline-content h2 {
  color: #303e49;
}
.cd-timeline-content p, .cd-timeline-content .cd-read-more, .cd-timeline-content .cd-date {
  font-size: 13px;
  font-size: 0.8125rem;
}
.cd-timeline-content .cd-read-more, .cd-timeline-content .cd-date {
  display: inline-block;
}
.cd-timeline-content p {
  margin: 1em 0;
  line-height: 1.6;
}
.cd-timeline-content .cd-read-more {
  float: right;
  padding: .8em 1em;
  background: #acb7c0;
  color: white;
  border-radius: 0.25em;
}
.no-touch .cd-timeline-content .cd-read-more:hover {
  background-color: #bac4cb;
}
.cd-timeline-content .cd-date {
  float: left;
  padding: .8em 0;
  opacity: .7;
}
.cd-timeline-content::before {
  content: '';
  position: absolute;
  top: 16px;
  right: 100%;
  height: 0;
  width: 0;
  border: 7px solid transparent;
  border-right: 7px solid white;
}
@media only screen and (min-width: 768px) {
  .cd-timeline-content h2 {
    font-size: 20px;
    font-size: 1.25rem;
  }
  .cd-timeline-content p {
    font-size: 16px;
    font-size: 1rem;
  }
  .cd-timeline-content .cd-read-more, .cd-timeline-content .cd-date {
    font-size: 14px;
    font-size: 0.875rem;
  }
}
@media only screen and (min-width: 1170px) {
  .cd-timeline-content {
    margin-left: 0;
    padding: 1.6em;
    width: 45%;
  }
  
  .cd-timeline-content .cd-read-more {
    float: left;
  }
  .cd-timeline-content .cd-date {
    position: absolute;
    width: 100%;
    left: 122%;
    top: 6px;
    font-size: 16px;
    font-size: 1rem;
  }
  
  .cd-timeline-block:nth-child(even) .cd-timeline-content .cd-date {
    left: auto;
    right: 122%;
    text-align: right;
  }
  .cssanimations .cd-timeline-content.is-hidden {
    visibility: hidden;
  }
  .cssanimations .cd-timeline-content.bounce-in {
    visibility: visible;
    -webkit-animation: cd-bounce-2 0.6s;
    -moz-animation: cd-bounce-2 0.6s;
    animation: cd-bounce-2 0.6s;
  }
}

@media only screen and (min-width: 1170px) {
  /* inverse bounce effect on even content blocks */
  .cssanimations .cd-timeline-block:nth-child(even) .cd-timeline-content.bounce-in {
    -webkit-animation: cd-bounce-2-inverse 0.6s;
    -moz-animation: cd-bounce-2-inverse 0.6s;
    animation: cd-bounce-2-inverse 0.6s;
  }
}
@-webkit-keyframes cd-bounce-2 {
  0% {
    opacity: 0;
    -webkit-transform: translateX(-100px);
  }

  60% {
    opacity: 1;
    -webkit-transform: translateX(20px);
  }

  100% {
    -webkit-transform: translateX(0);
  }
}
@-moz-keyframes cd-bounce-2 {
  0% {
    opacity: 0;
    -moz-transform: translateX(-100px);
  }

  60% {
    opacity: 1;
    -moz-transform: translateX(20px);
  }

  100% {
    -moz-transform: translateX(0);
  }
}
@keyframes cd-bounce-2 {
  0% {
    opacity: 0;
    -webkit-transform: translateX(-100px);
    -moz-transform: translateX(-100px);
    -ms-transform: translateX(-100px);
    -o-transform: translateX(-100px);
    transform: translateX(-100px);
  }

  60% {
    opacity: 1;
    -webkit-transform: translateX(20px);
    -moz-transform: translateX(20px);
    -ms-transform: translateX(20px);
    -o-transform: translateX(20px);
    transform: translateX(20px);
  }

  100% {
    -webkit-transform: translateX(0);
    -moz-transform: translateX(0);
    -ms-transform: translateX(0);
    -o-transform: translateX(0);
    transform: translateX(0);
  }
}
@-webkit-keyframes cd-bounce-2-inverse {
  0% {
    opacity: 0;
    -webkit-transform: translateX(100px);
  }

  60% {
    opacity: 1;
    -webkit-transform: translateX(-20px);
  }

  100% {
    -webkit-transform: translateX(0);
  }
}
@-moz-keyframes cd-bounce-2-inverse {
  0% {
    opacity: 0;
    -moz-transform: translateX(100px);
  }

  60% {
    opacity: 1;
    -moz-transform: translateX(-20px);
  }

  100% {
    -moz-transform: translateX(0);
  }
}
@keyframes cd-bounce-2-inverse {
  0% {
    opacity: 0;
    -webkit-transform: translateX(100px);
    -moz-transform: translateX(100px);
    -ms-transform: translateX(100px);
    -o-transform: translateX(100px);
    transform: translateX(100px);
  }

  60% {
    opacity: 1;
    -webkit-transform: translateX(-20px);	
    -moz-transform: translateX(-20px);
    -ms-transform: translateX(-20px);
    -o-transform: translateX(-20px);
    transform: translateX(-20px);
  }

  100% {
    -webkit-transform: translateX(0);
    -moz-transform: translateX(0);
    -ms-transform: translateX(0);
    -o-transform: translateX(0);
    transform: translateX(0);
  }
}

</style>


<body>

  <div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading">
		<div class="col-lg-6 text-white showcase-img" style="background-image: url('img/bg-showcase-2.jpg'); width: 50px;"> </div>     	
      		${email}의 타임라인.
      	</div>
      <div class="list-group list-group-flush">      
      	<a href="<%= request.getContextPath() %>/timeLine.action?email=${email}" class="list-group-item list-group-item-action bg-light">TimeLine</a>
        <a href="<%= request.getContextPath() %>/friendList.action?email=${email}"" class="list-group-item list-group-item-action bg-light">Friends</a>
        <a href="<%= request.getContextPath() %>/emailSearch.action" class="list-group-item list-group-item-action bg-light">Search</a>
        
        <c:if test="${sessionScope.loginuser.email == email}">
            <a href="myInfoEdit.action?email=${sessionScope.loginuser.email}" class="list-group-item list-group-item-action bg-light">MyInfo</a>
            <a href="memberDelete.action?idx=${sessionScope.loginuser.email}" class="list-group-item list-group-item-action bg-light">회원탈퇴</a>
        	<a href="<%= request.getContextPath() %>/friendAllowList.action?email=${sessionScope.loginuser.email}"" class="list-group-item list-group-item-action bg-light">Allow</a>
            
          </c:if>

      </div>
      
	<c:if test="${sessionScope.loginuser.email != email}">
      	<div>
      		<a class="btn btn-secondry btn-info" href="<%= request.getContextPath()%>/friendAdd.action?email=${email}">친구신청하기</a>
      	</div>
      </c:if>
      
    </div>
    <!-- /#sidebar-wrapper -->

	
   

<form name="emailFrm">
	<input type="hidden" name="email"/>
</form>	

  </div>
  <!-- /#wrapper -->

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Menu Toggle Script -->
  <script>
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>

</body>

	
	