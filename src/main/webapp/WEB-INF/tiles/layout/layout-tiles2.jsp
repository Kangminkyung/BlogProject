<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- ===== #35. tiles 를 사용하는 레이아웃 페이지 만들기  ===== --%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
 <meta name="author" content="">

<title>사이트</title>

<%-- 스마트 에디터 구현 시작(no frame 사용시) --%>
<link href="<%=request.getContextPath()%>/resources/smarteditor/css/smart_editor2.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/smarteditor/js/lib/jindo2.all.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/smarteditor/js/lib/jindo_component.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/smarteditor/js/SE2M_Configuration.js" charset="utf-8"></script>	<!-- 설정 파일 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/smarteditor/js/SE2BasicCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/smarteditor/js/smarteditor2.min.js" charset="utf-8"></script> 

<!-- 사진첨부샘플  --> 
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/smarteditor/sample/js/plugin/hp_SE2M_AttachQuickPhoto.js" charset="utf-8"></script> 
<%-- 스마트 에디터 구현 끝(no frame 사용시)     --%>
 
<!-- stylesheet -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.1.11.1.js"></script>
<!-- Bootstrap -->
  
  <title>Landing Page - Start Bootstrap Theme</title>

  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath() %>/resources/bootstrap-3.3.7-dist/css/bootstrap.css">
  
  <!-- Custom fonts for this template -->
  <link href="<%=request.getContextPath() %>/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath() %>/resources/vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template -->
  <link href="<%=request.getContextPath() %>/resources/css/landing-page.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath() %>/resources/css/simple-sidebar.css" rel="stylesheet">

</head>

<style type="text/css">  
    #mycontainer2	{padding-top: 0px;float:right; width:100%;}
  	#mymenu		{float:left; width:15%; min-height:1000px; margin-top: 50px;} 
	#mycontent2	{float:right; width:85%;min-height:1000px; margin-top: 50px; margin-left: 0px;} 
	#myfooter	{ clear:both;  }
	
	
</style> 

<body>
	<div id="mycontainer2">
		<div id="myheader">
			<tiles:insertAttribute name="header" />
		</div>
				
		<div id="mymenu">
			<tiles:insertAttribute name="menu" />
		</div>	
		
		<div id="mycontent2">
			<tiles:insertAttribute name="content" />
		</div>	

		<div id="myfooter">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>