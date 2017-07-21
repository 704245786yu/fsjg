<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="plugin/jquery.min.js"></script>
<script src="<%=basePath%>JS/main/note.js"></script>
<style type="text/css">
ul.menu_li{
	padding:0px;
	list-style:none;
	border:1px solid #DCDCDC;
}
/*左边菜单栏*/
.menu_li .level1{
	line-height:50px;
	font-weight:bold;
	font-size:16px;
}
.menu_li li{
	line-height:48px;
	border-bottom:1px solid #DCDCDC;
	font-size:15px;
	cursor:pointer;
	padding-left:55px;
}

#mainContent div{
	min-height:550px;
	font-size:18px;
	padding-left:20px;
	padding-right:20px;
	border:1px solid #DCDCDC;
}
#mainContent h2{
	text-align:center;
	margin-bottom:30px;
}
#mainContent p{
	text-indent:2em;
	line-height:200%;
}
iframe{
	width:100%;
	height:2500px;
	border:0px;
}
</style>
</head>

<body>
<%@ include file="top.jsp"%>
<input type="hidden" name="index" value="${param.index}"/>
<table style="width:1190px; margin:0 auto;">
	<tr>
		<!-- 左边菜单 -->
		<td style="padding:0px;width:200px;vertical-align:top;">
			<ul class="menu_li">
				<li class="level1">新手指南</li>
				<li name="fdzn" style="color:#00b8ef">发单指南</li>
				<li name="jdbd">接单宝典</li>
				<li class="level1">认证优势</li>
				<li name="">个人实名</li>
				<li name="">企业资质认证</li>
				<li class="level1">服务中心</li>
				<li name="">广告服务</li>
				<li name="">认证服务</li>
				<li class="level1" name="gywm">关于我们</li>
				<li class="level1" name="cpyc">诚聘英才</li>
			</ul>
		</td>
		<!-- 主体内容 -->
		<td id="mainContent" style="padding-left:5px;vertical-align:top;">
			<div name="fdzn" style="display:none;">
				<iframe src="<%=basePath%>/html/fdzn.html"></iframe>
			</div>
			<div name="jdbd" style="display:none;">
				<iframe src="<%=basePath%>/html/jdbd.html"></iframe>
			</div>
			<div style="display:none;">
			</div>
			<div style="display:none;">
			</div>
			<div style="display:none;">
			</div>
			<div style="display:none;">
			</div>
			<div name="gywm" style="display:none;">
				<h2>关于服饰加工网（www.fsjgw.com）</h2>
				<p>台州市顺天网络技术有限公司，成立于2014年，是一家致力于为服饰行业提供服饰生产与贸易协同服务的新兴电子商务公司。公司总部位于“海上名山”之城台州市，公司有着20年实体服饰加工行业背景、经验丰富的研发团队和雄厚的资金实力。</p>
				<p>我们凭借在服饰行业丰富的经验和专业的知识，结合深厚的互联网技术积累，成功推出了服饰加工网平台。
					服饰加工网平台专注服饰在线交易，致力于为服饰产业链的各个环节提供专业的交易平台。服饰企业内部之间的交易、企业之间的交易（如品牌采购商、供应商、生产商、经销商、客户、合作伙伴等之间的交易），都将在服饰加工网平台得到一站式服务。
				</p>
			</div>
			<div name="cpyc" style="display:none;">
				诚聘英才
			</div>
		</td>
	</tr>
</table>

</body>

</html>