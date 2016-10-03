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
	width:500px;
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

<form id="ff" action="person/updatePerson" method="post" enctype="multipart/form-data" class="form-inline" style="margin-left:50px;margin-top:10px" autocomplete="off">
	<table id="districtContainer">
		<tr>
			<td><label><span>*</span>用户名 </label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="basicUser.userName" value="${userInfo.basicUser.userName}"></div></td>
			<td><label>姓名 </label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="realName" value="${userInfo.realName}"></div></td>
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
			<td><label>邮政编码</label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="postalCode" value="${userInfo.postalCode}"></div></td>
			<td><label>身份证号</label></td>
			<td><div class="form-group"><input type="text" class="form-control" name="idNum" value="${userInfo.idNum}"></div></td>
		</tr>
		<tr>
			<td><label>身份证照(正)</label></td>
			<td>
				<input type="hidden" name="idFrontPhoto" value="${userInfo.idFrontPhoto}">
				<input type="file" name="frontPhoto" accept="image/jpeg,image/png" onchange="imgChange(this,200)">
				<p style="color:grey;">支持jpg、png格式图片,尺寸400*240,最大200kb</p>
				<div style="display:none;">
					<img style="width:150px;height:100px" src="">
				</div>
			</td>
			<td><label>身份证照(反)</label></td>
			<td>
				<input type="hidden" name="idBackPhoto" value="${userInfo.idBackPhoto}">
				<input type="file" name="backPhoto" accept="image/jpeg,image/png" onchange="imgChange(this,200)">
				<p style="color:grey;">支持jpg、png格式图片,尺寸400*240,最大200kb</p>
				<div style="display:none;">
					<img style="width:150px;height:100px" src="">
				</div>
			</td>
		</tr>
	</table>
	<div style="margin-top:20px;text-align:right;padding-right:100px;">
		<button type="submit" class="btn btn-primary" style="width:80px;margin-right:10px;">保存</button>
		<!-- <button type="reset" class="btn btn-default" style="width:80px;" onclick="resetForm()">重置</button> -->
	</div>
</form>

<script src="plugin/jquery-confirm/jquery-confirm.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery.form.min.js"></script>
<script src="JS/util/jqConfirmExtend.js"></script>
<script src="JS/main/common/districtCascade.js"></script>
<script src="JS/main/myCenter/personalInfo.js"></script>