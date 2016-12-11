<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
<style>
#ff table tr{
	height:45px;
}
#ff table td{
	vertical-align:top;
}

#ff table td:first + td{
	width:520px;
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
<div class="panel">
<div id="editPanel" class="panel panel-primary" style="display:none;">
	<div class="panel-body">
		<form id="ff" method="post" enctype="multipart/form-data" class="form-inline">
			<input type="hidden" name="person.id"/>
			<input type="hidden" name="person.headPic"/>
			<input type="hidden" name="contractor.personId"/>
			<input type="hidden" name="person.basicUser.id"/>
			<input type="hidden" name="person.basicUser.state"/>
			<input type="hidden" name="person.basicUser.password"/>
			<table id="districtContainer">
				<tr>
					<td><label><span>*</span>用户名 </label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="person.basicUser.userName"></div></td>
					<td><label>姓名 </label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="person.realName"></div></td>
				</tr>
				<tr>
					<td><label><span>*</span>省</label></td>
					<td>
						<div class="form-group"><select class="form-control" id="province" name="person.province"></select></div>
					</td>
					<td><label><span>*</span>手机号码 </label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="person.basicUser.telephone"></div></td>
				</tr>
				<tr>
					<td><label><span>*</span>市</label></td>
					<td>
						<div class="form-group"><select class="form-control" id="city" name="person.city"></select></div>
					</td>
					<td><label>电子邮箱 </label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="person.email"></div></td>
				</tr>
				<tr>
					<td><label><span>*</span>区县</label></td>
					<td>
						<div class="form-group"><select class="form-control" id="county" name="person.county"></select></div>
					</td>
					<td><label>QQ</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="person.qq"></div></td>
				</tr>
				<tr>
					<td><label>镇/乡/街道</label></td>
					<td>
						<div class="form-group"><select class="form-control" id="town" name="person.town"></select></div>
					</td>
					<td><label>固定电话</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="person.fixPhone"></div></td>
				</tr>
				<tr>
					<td><label><span>*</span>详细地址</label></td>
					<td>
						<div class="form-group"><input type="text" class="form-control" name="person.detailAddr" style="width:350px;"></div>
					</td>
					<td><label>微信</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="person.wechat"></div></td>
				</tr>
				<tr>
					<td><label>邮政编码</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="person.postalCode"></div></td>
					<td><label>身份证号</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="person.idNum"></div></td>
				</tr>
				<tr>
					<td><label>身份证照(正)</label></td>
					<td>
						<input type="hidden" name="person.idFrontPhoto">
						<input type="file" name="frontPhoto" accept="image/jpeg,image/png" onchange="imgChange(this,200)">
						<p style="color:grey;">支持jpg、png格式图片,尺寸400*240,最大200kb</p>
						<div style="display:none;">
							<img style="width:150px;height:100px" src="">
						</div>
					</td>
					<td><label>身份证照(反)</label></td>
					<td>
						<input type="hidden" name="person.idBackPhoto">
						<input type="file" name="backPhoto" accept="image/jpeg,image/png" onchange="imgChange(this,200)">
						<p style="color:grey;">支持jpg、png格式图片,尺寸400*240,最大200kb</p>
						<div style="display:none;">
							<img style="width:150px;height:100px" src="">
						</div>
					</td>
				</tr>
				<tr>
					<td><label><span>*</span>加工类型</label></td>
					<td>
						<div class="form-group">
							<c:forEach var="constantDict" items="${processTypes}">
								<label class="checkbox-inline"><input type="checkbox" name="contractor.processType" value="${constantDict.constantValue}">${constantDict.constantName}</label>
							</c:forEach>
						</div>
					</td>
					<td><label><span>*</span>主营产品</label></td>
					<td>
						<div class="form-group">
							<%@include file="/JSP/main/common/costumeCategoryModal.jsp"%>
						</div>
					</td>
				</tr>
				<tr>
					<td><label><span>*</span>加工年限</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="contractor.processYear"></div></td>
					<td><label><span>*</span>工人数量</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="contractor.workerAmount"></div></td>
				</tr>
				<tr>
					<td><label>报价</label></td>
					<td><div class="form-group" style="width:100%;"><input type="text" class="form-control" name="contractor.quote" style="width:100%;"></div></td>
					<td><label><span>*</span>专业技能</label></td>
					<td><div class="form-group" style="width:100%;"><input type="text" class="form-control" name="contractor.skill" style="width:100%;"></div></td>
				</tr>
				<tr>
					<td><label>生产设备</label></td>
					<td colspan="3"><div class="form-group" style="width:100%;"><input type="text" class="form-control" name="contractor.equipment" style="width:100%;"></div></td>
				</tr>
				<tr>
					<td><label>加工说明</label></td>
					<td colspan="3"><div class="form-group" style="width:100%;"><input type="text" class="form-control" name="contractor.processDesc" style="width:100%;"></div></td>
				</tr>
				<tr>
					<td><label>认证审核</label></td>
					<td>
						<div class="form-group" style="padding-top:5px;">
							<label class="radio-inline"><input type="radio" name="person.auditState" value="1">不通过</label>
							<label class="radio-inline"><input type="radio" name="person.auditState" value="2">通过</label>
						</div>
					</td>
				</tr>
			</table>
			<div style="margin-top:20px;text-align:right;padding-right:100px;">
				<button type="submit" name="save" class="btn btn-primary" style="width:80px;margin-right:10px;">保存</button>
				<button type="button" class="btn btn-default" onclick="cancel()" style="width:80px;">取消</button>
			</div>
		</form>
	</div><!-- panel-body -->
</div><!-- panel -->

</div>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery.form.min.js"></script>
<script src="plugin/jquery.formFill.js"></script>
<script src="JS/main/common/districtCascade.js"></script>
<script src="JS/backstage/contractor/editContractor.js"></script>