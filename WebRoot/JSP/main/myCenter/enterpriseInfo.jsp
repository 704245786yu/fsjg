<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="plugin/jquery-confirm/jquery-confirm.min.css" rel="stylesheet">

<style>
#ff table tr{
	height:45px;
}
#ff table td{
	vertical-align:top;
}

#ff table td:FIRST-CHILD + td{
	width:450px;
}

#ff > table select{
	width:180px;
}

/*此处需要使用直接子类，否则会对选择服饰类别模态框中的样式产生影响*/
#ff > table > tbody > tr >td > label{
	width:90px;
	margin-top:7px;
	margin-right:7px;
}
#ff > table > tbody > tr > td > label span{
	color:red;
}
#costumeBtn{
	width:400px;
	overflow:hidden;
	background: url('image/select-btn.png') no-repeat 90%;
}
</style>

<div style="border-bottom:1px solid #cccccc;line-height:49px;">
	<strong style="font-size:18px;padding-left:20px;">详细信息</strong>
</div>

<input type="hidden" name="province" value="${userInfo.province}">
<input type="hidden" name="city" value="${userInfo.city}">
<input type="hidden" name="county" value="${userInfo.county}">
<input type="hidden" name="town" value="${userInfo.town}">

<form id="ff" action="enterprise/updateEnterprise" method="post" enctype="multipart/form-data" class="form-inline" style="margin-left:50px;margin-top:10px" autocomplete="off">
	<table id="districtContainer">
		<tr>
			<td><label>工厂编号</label></td>
			<td>
				<div class="form-group"><input type="text" class="form-control" name="number" readonly value="${userInfo.number}"></div>
			</td>
			<td><label><span>*</span>用户名 </label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="basicUser.userName" value="${userInfo.basicUser.userName}"></div></td>
		</tr>
		<tr>
			<td><label><span>*</span>工厂名称</label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="enterpriseName" value="${userInfo.enterpriseName}"></div></td>
			<td><label><span>*</span>联系人 </label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="linkman" value="${userInfo.linkman}"></div></td>
		</tr>
		<tr>
			<td><label><span>*</span>省</label></td>
			<td>
				<div class="form-group"><select class="form-control" id="province" name="province"></select></div>
			</td>
			<td><label><span>*</span>手机号码 </label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="basicUser.telephone" value="${userInfo.basicUser.telephone}"></div></td>
		</tr>
		<tr>
			<td><label><span>*</span>市</label></td>
			<td>
				<div class="form-group"><select class="form-control" id="city" name="city"></select></div>
			</td>
			<td><label>电子邮箱 </label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="email" value="${userInfo.email}"></div></td>
		</tr>
		<tr>
			<td><label><span>*</span>区县</label></td>
			<td>
				<div class="form-group"><select class="form-control" id="county" name="county"></select></div>
			</td>
			<td><label>QQ</label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="qq" value="${userInfo.qq}"></div></td>
		</tr>
		<tr>
			<td><label>镇/乡/街道</label></td>
			<td>
				<div class="form-group"><select class="form-control" id="town" name="town"></select></div>
			</td>
			<td><label>固定电话</label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="fixPhone" value="${userInfo.fixPhone}"></div></td>
		</tr>
		<tr>
			<td><label><span>*</span>详细地址</label></td>
			<td>
				<div class="form-group"><input type="text" class="form-control" name="detailAddr" style="width:350px;" value="${userInfo.detailAddr}"></div>
			</td>
			<td><label>微信</label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="wechat" value="${userInfo.wechat}"></div></td>
		</tr>
		<tr>
			<td><label>最近档期</label></td>
			<td>
				<div class="form-group"><input type="text" class="form-control" name="schedule" style="width:350px;" value="${userInfo.schedule}"></div>
			</td>
			<td><label>邮政编码</label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="postalCode" value="${userInfo.postalCode}"></div></td>
		</tr>
		<tr>
			<td><label><span>*</span>行业分类</label></td>
			<td>
				<input type="hidden" id="trade" value="${userInfo.trade}">
				<div class="form-group">
					<label class="checkbox-inline"><input type="checkbox" name="trade" value="1">服装</label>
					<label class="checkbox-inline"><input type="checkbox" name="trade" value="2">服饰</label>
					<label class="checkbox-inline"><input type="checkbox" name="trade" value="3">家纺</label>
					<label class="checkbox-inline"><input type="checkbox" name="trade" value="4">面料</label>
				</div>
			</td>
			<td><label>组织机构代码</label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="orgCode" value="${userInfo.orgCode}"></div></td>
		</tr>
		<tr>
			<td><label><span>*</span>加工类型</label></td>
			<td>
				<input type="hidden" id="processType" value="${userInfo.processType}">
				<div class="form-group">
					<c:forEach var="constantDict" items="${processTypes}">
						<label class="checkbox-inline"><input type="checkbox" name="processType" value="${constantDict.constantValue}">${constantDict.constantName}</label>
					</c:forEach>
				</div>
			</td>
		</tr>
		<tr>
			<td><label><span>*</span>主营产品</label></td>
			<td>
				<input type="hidden" id="costumeCode" value="${userInfo.costumeCode}">
				<div class="form-group">
					<%@include file="/JSP/main/common/costumeCategoryModal.jsp"%>
				</div>
			</td>
			<td><label>高速车工人数</label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="highSpeedStaffNumber" value="${userInfo.highSpeedStaffNumber}"></div></td>
		</tr>
		<tr>
			<td><label><span>*</span>销售市场</label></td>
			<td>
				<input type="hidden" id="saleMarket" value="${userInfo.saleMarket}">
				<div class="form-group">
					<label class="radio-inline"><input type="radio" name="saleMarket" value="0">内销</label>
					<label class="radio-inline"><input type="radio" name="saleMarket" value="1">外销</label>
				</div>
			</td>
			<td><label>其他加工人数</label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="otherStaffNumber" value="${userInfo.otherStaffNumber}"></div></td>
		</tr>
		<tr>
			<td><label><span>*</span>工人数量</label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="staffNumber" value="${userInfo.staffNumber}"></div></td>
			<td><label>经营年限</label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="enterpriseAge" value="${userInfo.enterpriseAge}"></div></td>
		</tr>
		<tr>
			<td><label>产值产量</label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="yield" value="${userInfo.yield}"></div></td>
			<td><label>企业网址</label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="websiteUrl" value="${userInfo.websiteUrl}"></div></td>
		</tr>
		<tr>
			<td><label>合作客户</label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="cooperator" style="width:280px;" value="${userInfo.cooperator}"></div></td>
			<td><label>生产设备</label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="equipment" style="width:280px;" value="${userInfo.equipment}"></div></td>
		</tr>
		<tr style="height:100px">
			<td><label>工厂介绍</label></td>
			<td colspan="3"><div class="form-group"><textarea class="form-control" rows="3" name="description" style="width:830px;" value="${userInfo.description}"></textarea></div></td>
		</tr>
		<tr style="height:190px;">
			<td><label>工厂logo</label></td>
			<td>
				<input type="hidden" name="logo" value="${userInfo.logo}">
				<input type="file" name="logoImg" accept="image/jpeg,image/png" onchange="imgChange(this,50)">
				<p style="color:grey;">支持jpg、png格式图片,尺寸90*90,最大50kb</p>
				<div style="display:none;">
					<img width="90px" height="90px" src="">
					<div><button type="button" class="btn btn-primary btn-sm" onclick="delImg('logo')">删除</button></div>
				</div>
			</td>
			<td><label>营业执照</label></td>
			<td>
				<input type="hidden" name="licenseImg" value="${userInfo.licenseImg}">
				<input type="file" name="licensePic" accept="image/jpeg,image/png">
				<p style="color:grey;">支持jpg、png格式图片,最大200kb</p>
				<div style="display:none;">
					<img width="90px" height="90px" src="">
					<div><button type="button" class="btn btn-primary btn-sm" onclick="delImg('licenseImg')">删除</button></div>
				</div>
			</td>
		</tr>
		<tr>
			<td><label>工厂图片</label></td>
			<td colspan="3">
				<!-- IE9下无法多选 -->
				<input type="hidden" name="enterpriseImg" value="${userInfo.enterpriseImg}">
				<input type="file" name="enterprisePic" accept="image/jpeg,image/png" multiple="multiple" onchange="enterpriseImgChange(this,200)">
				<p style="color:grey;">支持jpg、png格式图片,尺寸400*240,最大200kb</p>
				<div style="float:left;display:none;margin-right:10px;">
					<img style="width:150px;height:100px" class="img-thumbnail" src="">
					<div><button type="button" class="btn btn-primary btn-sm" onclick="delEnterpriseImg(this)">删除</button></div>
				</div>
			</td>
		</tr>
	</table>
	<div style="margin-top:20px;text-align:right;padding-right:100px;">
		<button type="submit" name="save" class="btn btn-primary" style="width:80px;margin-right:10px;">保存</button>
	</div>
</form>

<script src="plugin/jquery-confirm/jquery-confirm.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery.form.min.js"></script>

<script src="JS/util/jqConfirmExtend.js"></script>
<script src="JS/main/common/districtCascade.js"></script>
<script src="JS/util/treeUtil.js"></script>
<script src="JS/main/myCenter/enterpriseInfo.js"></script>