<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>服饰加工网</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

</head>

<body>
<%@ include file="top.jsp" %>
<input type="hidden" name="pageName" value="activity">
<span style="display:none;" id="adPositions">${adPositions}</span>
<table style="width:1190px; margin:0 auto;">
<tr>
	<td style="width:906px;vertical-align:top;">
		<div class="panel panel-default">
			<div class="panel-body" style="text-align:center;">
				<h3>${activity.title}<div style="float:right;font-size:14px;">来源：${activity.source}</div></h3>
				<p>时间：${activity.duration}</p>
				<p>${activity.content}</p>
			</div>
		</div>
	</td>
	<td style="width:274px;vertical-align:top;padding-left:10px;">
		<div style="margin-bottom:20px;">
			<a class="ad" target="_blank"><img style="height:210px;width:280px;"/></a>
		</div>
		<div style="margin-bottom:20px;">
			<a class="ad" target="_blank"><img style="height:210px;width:280px;"/></a>
		</div>
	</td>
</tr>
</table>
<%@ include file="/JSP/main/bottom.jsp"%>
<script src="plugin/jquery.min.js"></script>
<script>
$(function(){
	initAd();
});
//设置链接地址和图片，供initAd()方法调用
function setAdhrefAndImg($a,ad){
	if(ad.linkType==0)//外部链接
		$a.attr('href','http://'+ad.link);
	else if(ad.linkType==1)//工厂详情页
		$a.attr('href','enterprise/showDetail/'+ad.link);
	
	$a.children('img').attr('src','uploadFile/ad/'+ad.img);
}

function initAd(){
	var adPositions = $.parseJSON($('#adPositions').html());
	var $aAds = $('a.ad');
	for(var i=0;i<adPositions.length;i++){
		var $a = $($aAds[i]);
		setAdhrefAndImg($a,adPositions[i]);
	}
	
}
</script>
</body>
</html>

