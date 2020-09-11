<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.spring.member.model.MemberVO" %>
<!DOCTYPE html>
<html>


<script>

var flag = 0;

$(document).ready(function(){		
	
	
	$("#email").blur(function(){			
		
		var emailchk = $("#email").val().trim();				
		$.ajax({							
			url: "emailCheck.action",
			type: "GET",
			data : {email: $("#email").val()},
			dataType :"json",
			success : function(json){	
				if(json.cnt > 0 || emailchk.length == 0){				
					alert("사용할 수 없는 아이디입니다.");
					return;					
				}else{
					alert("사용가능한 아이디입니다.");
					console.log(json.cnt)
					$("#pwd").focus();
					flag=1;
				
				}						
			}								
		});		   	
		
	});// end of $("#email").blur(function()

}); // document


function goRegister(event){
		
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
			var frm = document.registerFrm;
			frm.method = "post";
			frm.action = "memberRegisterEnd.action";
			frm.submit();
		}

	}// end of function goRegister(event)---------


	
</script>

  <!-- Masthead -->
  <header class="masthead text-white text-center">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-xl-9 mx-auto">
          <h1 class="mb-5">타임라인에 글을 올리고 더 많은 친구들과 소통해보세요!!</h1>
        </div>
         	
        <div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
          <form name="registerFrm">
         <div class="form-row" style="margin-left: 50%">
            <c:if test="${sessionScope.loginuser == null}">
              <div class="col-12 col-md-9 mb-2 mb-md-0" style="width: 400px;">
                <input type="email" id = "email" name="email" class="requiredInfo" value ="${email}" placeholder="Enter your email..." style="margin-top: 10px;">                	
                <input type="password" id="pwd" name="pwd" class="requiredInfo" placeholder="Enter your password..." style="margin-top: 20px;">
              </div>
              <div class="col-12 col-md-3" style="width: 400px; margin-top: 10px;">
                <button type="submit" id="btnRegister" class="btn btn-block btn-lg btn-primary" onClick="goRegister(event);">Sign up!</button>
              </div>
           	</c:if>
            <c:if test="${sessionScope.loginuser != null}">
             <div class="col-12 col-md-3" style="width: 400px; margin-top: 10px;">
               <a href="timeLine.action?email=${sessionScope.loginuser.email}">TimeLine</a>
              </div>
             </c:if>
          </div>
          </form>
        </div>
        
      </div>
    </div>
  </header>

  <!-- Icons Grid -->
  <section class="features-icons bg-light text-center">
    <div class="container">
      <div class="row">
        <div class="col-lg-4">
          <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
            <div class="features-icons-icon d-flex">
              <i class="icon-screen-desktop m-auto text-primary"></i>
            </div>
            <h3>많은 즐거움!!</h3>
            <p class="lead mb-0">친구들과 재미있는 추억을 만들어보세요.</p>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
            <div class="features-icons-icon d-flex">
              <i class="icon-layers m-auto text-primary"></i>
            </div>
            <h3>실시간 소통!!</h3>
            <p class="lead mb-0">타임라인에 친구들이 쓴 글을 확인해보세요.</p>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="features-icons-item mx-auto mb-0 mb-lg-3">
            <div class="features-icons-icon d-flex">
              <i class="icon-check m-auto text-primary"></i>
            </div>
            <h3>실시간 트랜드!!</h3>
            <p class="lead mb-0">현재 인기있는 검색어 랭킹을 확인하세요.</p>
          </div>
        </div>
      </div>
    </div>
  </section>


 
  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<form name="LoginFrm">
	<input type="hidden" name="email" id="email"/>
	<input type="hidden" name="pwd" id="pwd"/>
</form>
<form name = "memberFrm">
   <input type="hidden" name="email"/>
</form>


</body>

</html>
