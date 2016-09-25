<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>中国服务加工网-个人中心</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
<link href="plugin/jquery-entropizer/css/jquery-entropizer.min.css" rel="stylesheet">
<link href="CSS/my-center.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<%@ include file="../top.jsp"%>
<table class="table table-bordered" style="width:1190px; margin:0 auto;">
	<tr>
		<td colspan="2">
			<!-- 用户基本信息 -->
			<table id="basic-info" class="table">
				<tr>
					<td rowspan="3" style="width:200px;text-align:center;">
						<img src="uploadFile/enterprise/default_logo.png">
					</td>
					<td>
						<label>帐号名称：</label>${userInfo.basicUser.userName}
					</td>
					<td>
						<label>姓名：</label>${userInfo.realName}
					</td>
					<td>
						<label>电子邮箱：</label>${userInfo.email}
					</td>
				</tr>
				<tr>
					<td><label>手机号码：</label>${userInfo.basicUser.telephone}</td>
					<td><label>身份证号：</label>${userInfo.idCard}</td>
				</tr>
				<tr>
					<td colspan="3" class="auth">
						<span><img src="image/enterpriseDetail/email_tg.png">邮箱认证</span>
						<span><img src="image/enterpriseDetail/telephone_tg.png">手机认证</span>
						<span><img src="image/enterpriseDetail/realname_tg.png">实名认证</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<!-- 左边菜单 -->
		<td style="padding:0px;width:200px;background-color:#FCF9FA;">
			<ul class="menu_li">
				<li class="level1">帐号管理</li>
				<li name="1">详细信息</li>
				<li name="2">修改密码</li>
				<li class="level1">发单管理</li>
				<li name="3">我发布的订单</li>
				<li name="4">我收到的报价</li>
				<li name="5">我确认的订单</li>
			</ul>
		</td>
		<!-- 主体内容 -->
		<td id="mainContent" style="padding:0px;">
			<div>
				<%@ include file="personInfo.jsp"%>
			</div>
			<div style="display:none;">
				<div style="border-bottom:1px solid #cccccc;line-height:49px;">
					<strong style="font-size:18px;padding-left:20px;">修改密码</strong>
				</div>
				<form id="modifyPwdForm" method="post" class="form-horizontal" style="width:400px;margin-top:20px;" action="basicUser/modifyPwd">
					<div class="form-group">
						<label for="oldPassword" class="col-sm-3 control-label">当前密码</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" id="oldPassword" name="oldPassword">
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-3 control-label">新 密 码</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" id="password" name="password">
						</div>
					</div>
					<div class="form-group">
                    	<div id="meter" class="col-md-9 col-md-offset-3"></div>
                       </div>
					<div class="form-group">
						<label for="rePassword" class="col-sm-3 control-label">确认密码</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" id="rePassword" name="rePassword" autocomplete="off">
						</div>
                       <span id="passstrength"></span>
					</div>
					<div class="form-group">
						<div class="col-sm-8 col-sm-offset-4" style="text-align:right">
							<button type="submit" class="btn btn-primary" style="width:100px;">保存</button>
							<button type="button" class="btn btn-default" style="width:100px;" onclick="resetForm()">重置</button>
						</div>
					</div>
				</form>
			</div>
			<div style="display:none;">
				<iframe src="indent/showMyReleased"></iframe>
			</div>
			<div style="display:none;">
				<iframe src="indent/showMyReceivedQuote"></iframe>
			</div>
			<div style="display:none;">
				<iframe src="indent/showMyConfirmed"></iframe>
			</div>
		</td>
	</tr>
</table>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery-entropizer/lib/entropizer.js"></script>
<script src="plugin/jquery-entropizer/js/jquery-entropizer.min.js"></script>
<script src="JS/main/myCenter/personalCenter.js"></script>
</body>
</html>