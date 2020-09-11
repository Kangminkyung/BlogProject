<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script type="text/javascript">

$(document).ready(function(){
	
	// showRank();
	
	$("#displayData").hide();
	
	$("#emailSearch").val("${emailSearchWord}");
	
   $("#emailSearch").keyup(function(){
		// 사용자가 텍스트박스 안에서 키보드를 눌렀다가 up 했을 때 이벤트 발생함.
	  	var form_data = {emailSearch : $("#emailSearch").val() };
	//	alert(emailSearch);
		$.ajax({
			url: "emailSearchJSON.action",
			type: "GET",
			data: form_data,
			dataType: "JSON",
			success: function(json) {
				
				if(json.length > 0) {
					
					var html = "";
					
					$.each(json, function(entryIndex, entry) {
						
						var word = entry.emailName.trim(); 
						var index = word.toLowerCase().indexOf($("#emailSearch").val().toLowerCase());
						var str = "";
						var len = $("#emailSearch").val().length;
						str = "<span class='first' style='color: blue;'>" + word.substr(0, index) + "</span>" 
							+ "<span class='second' style='color: red; font-weight: bold;'>" + word.substr(index, len) + "</span>"
							+ "<span class='third' style='color: blue;'>" + word.substr(index+len) + "</span>";
						html += "<span style='cursor: pointer;'>" + str + "</span><br/>"; 
							
					//	html+=word+"<br>";  
						
					}); // end if $.each()
									
					$("#displayData").html(html);
					$("#displayData").show();
				} //end of if	
				else {
					// 검색된 데이터가 없는 경우
					$("#displayData").hide();
				}
				
			}
		
		}); //end of ajax

	  }); // end of  $("#searchword").keyup(function(){
		  
 	$("#displayData").click(function(event){
	 	var word = "";
		
		var $target = $(event.target);
		
		if($target.is(".first")) {
			word = $target.text() + $target.next().text() + $target.next().next().text(); 
		}
		else if($target.is(".second")) {
			word = $target.prev().text() + $target.text() + $target.next().text();
		}
		else if($target.is(".third")) {
			word = $target.prev().prev().text() + $target.prev().text() + $target.text();
		}
	
		$("#emailSearch").val(word);
		// 텍스트박스에 검색된 결과의 문자열을 입력해준다.
		
		$("#displayData").hide();
		
		goSearch2();
		});
 
  $("#emailSearch").bind("keydown", function(event){
	  var keyCode = event.keyCode;
	  if(keyCode == 13) {
		  goSearch2();
	  }
  }); 
 
}); // end of ready 

function goSearch2() {		  
			
		var emailSearch = $("#emailSearch").val();		
		var frm = document.emailSearchFrm;
	 
		frm.emailSearchFrm.value = emailSearch;
		frm.method="GET";
		frm.action="<%=request.getContextPath()%>/emailSearch.action";
		frm.submit();
		
}// end of function goSearch2()--------

</script>

<div class="container" style="width: 90%;">
<h2 align="center">Search</h2>
<input type="text" placeholder="아이디 검색" id="emailSearch" name="emailSearch" value="${emailSearchWord}" style="width: 600px; margin-top: 30px; "></input>
	<button type="button" style="width: 30px; text-align: center; background-color: #468EDE; color: #FFFFFF;" onclick="goSearch2();"><i class="fa fa-search"></i></button>
	
	<!-- 검색어 자동검색 -->
	<div id="display" style="width: 600px;  border: solid 0px gray;  left: 6.5%; top: 5px;" align="left">
		<div id="displayData" style="overflow:auto; height: 500px;border: solid 1px red;" ></div>
	</div>  
	
	 <div id="lightbox" class="row" style="margin-top: 50px;">
	 
	 <table class="table table-condensed" >
		<thead>
			<tr>
				<th style="width: 25%;">Email</th>
				<th style="width: 25">date</th>
				<th style="width: 25%;">status</th>
			</tr>
		</thead>
		<tbody>
      		<c:forEach var="mvo" items="${memberList}">
				<tr>
					<td><a href="<%= request.getContextPath() %>/timeLine.action?email=${mvo.email}" >${mvo.email}</a></td>
					<td>${mvo.registerday}</td>
					<c:if test="${tvo.status == 1}">
						<td>공개</td>
					</c:if>
					<c:if test="${tvo.status == 2}">
						<td>비공개</td>
					</c:if>
				</tr>
				</c:forEach>
				
			<c:if test="${empty memberList}">
				   <tr>
				      <td colspan="3" style="text-align: center">
				      		검색된 회원이 없습니다.				      		
				      </td>
				   </tr>
			</c:if>
							
			</tbody>
		</table>


      </div>
</div>
    	

 <form name="emailSearchFrm">
  		<input type="hidden" name="emailSearchFrm"/>
 </form>