<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<link href="CSS/enterprise-detail.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>

	<%@ include file="top.jsp"%>
	<div class="info_detail_div">
		<div class="headphoto_div">
			<ul style="margin-left:30px">
				<li><img style="margin:0;padding:0"
					src="image/enterpriseDetail/headphoto.png"></li>
				<li><input style="width:122px;margin-top:10px" value="上传头像"
					type="submit" class="button"></input></li>
			</ul>
		</div>
		<div class="info_div">
			<table width=800 height=80 style="font-size:15px" cellspacing="42"
				cellpadding="0">
				<tr>
					<td><label>帐号名称：</label>${userInfo.basicUser.userName }</td>
					<td><label>姓名：</label>${userInfo.linkman }</td>
					<td><label>电子邮箱：</label>${userInfo.email }</td>
				</tr>
				<tr>
					<td><label>手机号码：</label>${userInfo.basicUser.telephone }</td>
					<td colspan="2"><label>工厂名称：</label>${userInfo.enterpriseName }</td>
				</tr>
				
			</table>
			<p style="line-height:70px">
				<span><img style="margin:0;padding:0"
					src="image/enterpriseDetail/qualification_tg.png">资质认证</span><span><img
					style="margin:0;padding:0"
					src="image/enterpriseDetail/email_tg.png">邮箱认证</span><span><img
					style="margin:0;padding:0"
					src="image/enterpriseDetail/telephone_tg.png">手机认证</span><span><img
					style="margin:0;padding:0"
					src="image/enterpriseDetail/realname_tg.png">实名认证</span>
			</p>
		</div>
	</div>
	<div class="user_main_div">
		<div class="left"
			style="width:200px;float:left;border-right:1px solid #cccccc;border-bottom:1px solid #cccccc;">
			<ul class="menu_li">
				<li
					style="line-height:50px;font-weight:bold;font-size:15px;list-style:none">帐号管理</li>
				<li>详细信息</li>
				<li>修改密码</li>
				<li
					style="line-height:50px;font-weight:bold;font-size:15px;list-style:none">个人管理</li>
				<li>快产信息</li>
				<li
					style="line-height:50px;font-weight:bold;font-size:15px;list-style:none">发单管理</li>
				<li>我发布的订单</li>
				<li>我收到的报价</li>
				<li>我确认单的订单</li>
			</ul>
		</div>

		<div class="right_info">
		<form class="form-inline" role="form" action="basicUser/editEnterpriseInfo" method="post" accept-charset="utf-8">
			<div class="title" style="border-bottom:1px solid #cccccc">
				<p style="line-height:40px">
					<span style="font-size:20px;font-weight:bold;padding-left:20px;">详细信息</span><span
						style="padding-left:700px">&nbsp;</span>
						<input type="hidden" value=${userInfo.basicUser.id } name="id" class="form-control" id="myid">
					<button type="submit" class="btn btn-primary ">保存修改</button>
					<button type="submit" class="btn btn-primary">重&nbsp;&nbsp;&nbsp;&nbsp;置</button>
				</p>
			</div>

			
				<div class="form-group" style="margin-left:50px;margin-top:10px">
					<table width=650 height=400>
						<tr>
							<td><label for="name">工厂类别：</label>
								
								<c:forEach var="tradeName" items="${tradeNames}">
							<c:out value="${tradeName}" />
						</c:forEach>
								</td>
							<td><label for="name">主营产品：</label>
								<c:forEach var="costumeName" items="${costumeNames}">
							<c:out value="${costumeName}" />
						</c:forEach>
									</td>
						</tr>
						<tr>
							<td><label for="name">加工类型：</label><input type="text"
								name="processType"
								
								<c:forEach var="processType" items="${processTypes}">
								<c:set var="processType_ph" value="${processType_ph} ${processType.constantName}" />      
						</c:forEach>
						value="${processType_ph}" 
								class="form-control" id="processType"></td>
							<td><label for="name">企业优势：</label><input type="text"
								name=enterpriseYs
								class="form-control" id="enterpriseYs"></td>
						</tr>
						<tr>
							<td><label for="name">员工人数：</label><input type="number"
								name=employeeCount value=${userInfo.staffNumber }
								class="form-control" id="employeeCount"></td>
							<td><label for="name">所在地区：</label>
							<input type="text"
								name="district"
								<c:forEach var="districtName" items="${districtNames}">
							<c:set var="districtName_ph" value="${districtName_ph} ${districtName}" />
						</c:forEach>
								 value="${districtName_ph }"
								class="form-control" id="district"></td>
						</tr>
						<tr>
							<td><label for="name">邮政编码：</label>
							<input type="number"
								name=postCode placeholder=""
								class="form-control" id="postCode"></td>
							<td><label for="name">详细地址：</label>
							<input type="text"
								name=detailAddr value=${userInfo.detailAddr }
								class="form-control" id=detailAddr></td>
							<td></td>
						</tr>
						<tr>
							<td><label for="name">QQ号码：</label>
							<input type="number"
								name=qq value=${userInfo.qq }
								class="form-control" id="qq">
							</td>
							<td><label for="name">固定电话：</label>
							<input type="text"
								name=fix_phone value=${userInfo.fixPhone }
								class="form-control" id="fix_phone"></td>
						</tr>
						<tr>
							<td><label for="name">微信号码：</label><input type="text"
								name=wechat value=${userInfo.wechat }
								class="form-control" id="wechat"></td>
							<td></td>
						</tr>
						<tr>
							<td><label for="name">营业执照：</label><button type="submit" class="btn btn-primary ">浏&nbsp;&nbsp;&nbsp;览</button>
							</td>
							<td><label for="name">组织机构代码：</label><input type="text"
								name=orgCode value=${userInfo.orgCode }
								class="form-control" id="orgCode"></td>
						</tr>
						<tr>
							<td><label for="name">企业LOGO：</label>
							<button type="submit" class="btn btn-primary ">上&nbsp;&nbsp;&nbsp;传</button>
							</td>
							<td><label for="name">成立时间：</label><input type="text"
								name=enterpriseAge value=${userInfo.enterpriseAge }
								class="form-control" id="enterpriseAge">年</td>
						</tr>
					</table>
				</div>
			</form>








		</div>



		<div class="right_order" style="display:none">
			<table>
				<tr>
					<td>订单编号：<input type="text" name="odernum"
						class="form-control" id="odernum"></td>
					<td>订单名称：<input type="text" name="odername"
						class="form-control" id="odername"></td>
					<td>订单状态：</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
		<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>

