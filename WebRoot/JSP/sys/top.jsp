<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>主页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<style type="text/css">
		body{background:#f0f0f0;}
	</style>
</head>
  
  <body>
    <div id="aa" style="margin:0">   
    	<div >
   			<h3 class="bb" >系统后台管理</h3>
   		</div>   		
	</div>
  </body>
</html>
