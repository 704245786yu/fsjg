<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>中国服饰加工网-个人中心</title>
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
			<c:choose>
				<%--普通用户信息 --%>
				<c:when test="${userInfo.basicUser.roleId==1}">
					<table id="basic-info" class="table">
						<tr>
							<td rowspan="3" style="width:200px;text-align:center;">
								<img style="width:90px;height:90px;" src="uploadFile/enterprise/default_logo.png">
							</td>
							<td>
								<label>帐号名称：</label><span name="userName">${userInfo.basicUser.userName}</span>
							</td>
							<td>
								<label>姓名：</label><span name="realName">${userInfo.realName}</span>
							</td>
							<td>
								<label>电子邮箱：</label><span name="email">${userInfo.email}</span>
							</td>
						</tr>
						<tr>
							<td><label>手机号码：</label><span name="telephone">${userInfo.basicUser.telephone}</span></td>
							<td><label>身份证号：</label><span name="idNum">${userInfo.idNum}</span></td>
						</tr>
						<tr>
							<td colspan="3" class="auth">
								<c:if test="${userInfo.email==null}">
									<span><img src="image/enterpriseDetail/email_tg.png">邮箱认证</span>
								</c:if>
								<span><img src="image/enterpriseDetail/telephone_tg.png">手机认证</span>
								<c:if test="${userInfo.auditState==2}">
									<span><img src="image/enterpriseDetail/realname_tg.png">实名认证</span>
								</c:if>
							</td>
						</tr>
					</table>
				</c:when>
				<%--企业用户信息 --%>
				<c:when test="${userInfo.basicUser.roleId==2}">
					<table id="basic-info" class="table">
						<tr>
							<td rowspan="3" style="width:200px;text-align:center;">
								<img style="width:90px;height:90px;" src="uploadFile/enterprise/${userInfo.logo}">
							</td>
							<td>
								<label>帐号名称：</label><span name="userName">${userInfo.basicUser.userName}</span>
							</td>
							<td>
								<label>联系人：</label><span name="linkman">${userInfo.linkman}</span>
							</td>
							<td>
								<label>电子邮箱：</label><span name="email">${userInfo.email}</span>
							</td>
						</tr>
						<tr>
							<td><label>手机号码：</label><span name="telephone">${userInfo.basicUser.telephone}</span></td>
							<td><label>工厂名称：</label><span name="enterpriseName">${userInfo.enterpriseName}</span></td>
						</tr>
						<tr>
							<td colspan="3" class="auth">
								<c:if test="${userInfo.email==null}">
									<span><img src="image/enterpriseDetail/email_tg.png">邮箱认证</span>
								</c:if>
								<span><img src="image/enterpriseDetail/telephone_tg.png">手机认证</span>
								<c:if test="${userInfo.auditState==2}">
									<span><img src="image/enterpriseDetail/qualification_tg.png">资质认证</span>
								</c:if>
							</td>
						</tr>
					</table>
				</c:when>
			</c:choose>
		</td>
	</tr>
	<tr>
		<!-- 左边菜单 -->
		<input type="hidden" name="index" value="${index}">
		<td style="padding:0px;width:200px;background-color:#FCF9FA;">
			<ul class="menu_li">
				<li class="level1">帐号管理</li>
				<li name="1" style="color:#00b8ef">详细信息</li>
				<li name="2">修改密码</li>
				<c:if test="${userInfo.basicUser.roleId == 2}">
					<%--企业用户展示接单管理 --%>
					<li class="level1">接单管理</li>
					<li name="6">我的报价</li>
					<li name="7">我收到的订单</li>
				</c:if>
				<li class="level1">发单管理</li>
				<li name="3">我发布的订单</li>
				<li name="4">我收到的报价</li>
				<li name="5">我确认的订单</li>
			</ul>
		</td>
		<!-- 主体内容 -->
		<td id="mainContent" style="padding:0px;">
			<div>
				<c:choose>
					<%--普通用户详情 --%>
					<c:when test="${userInfo.basicUser.roleId==1}">
						<%@ include file="personInfo.jsp"%>
					</c:when>
					<%--企业用户详情 --%>
					<c:when test="${userInfo.basicUser.roleId==2}">
						<%@ include file="enterpriseInfo.jsp"%>
					</c:when>
				</c:choose>
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
			<c:if test="${userInfo.basicUser.roleId == 2}">
				<div style="display:none;">
					<iframe src="indent/showMyQuoted"></iframe>
				</div>
				<div style="display:none;">
					<iframe src="indent/showMyReceivedIndent"></iframe>
				</div>
			</c:if>
		</td>
	</tr>
</table>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery-entropizer/lib/entropizer.js"></script>
<script src="plugin/jquery-entropizer/js/jquery-entropizer.min.js"></script>
<script src="JS/main/myCenter/myCenter.js"></script>
</body>
</html>