<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>">
	<title>服饰加工</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
</head>

<frameset rows="60,*" cols="*" frameborder="yes" border="0" framespacing="0">
  <frame src="JSP/sys/top.jsp" name="top_frame" scrolling="No" noresize="noresize" id="top_frame" title="top_frame" />
  <frameset rows="*" cols="260,*" framespacing="0" frameborder="no" border="0">
    <frame src="JSP/sys/left.jsp" name="left_frame" scrolling="No" noresize="noresize" id="left_frame" title="left_frame" />
    <frame src="" name="right_frame" id="right_frame" title="right_frame" />
  </frameset>
</frameset>

</html>
