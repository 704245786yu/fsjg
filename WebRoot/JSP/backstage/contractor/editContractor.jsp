<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">

<div id="editPanel" class="panel panel-primary" style="display:none;">
	<div class="panel-heading">编辑快产专家</div>
	<div class="panel-body">
		<form id="ff" method="post" enctype="multipart/form-data" class="form-horizontal">
			<%-- 快产专家的个人信息 --%>
			<input type="hidden" name="person.id"/>
			
			<div class="form-group">
				<label for="userName" class="col-sm-1 control-label">用户名 </label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="userName" name="person.userName">
				</div>
				<label for="realName" class="col-sm-1 control-label">真实姓名</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="realName" name="person.realName">
				</div>
				<label for="gender" class="col-sm-1 control-label">性别</label>
				<div class="col-sm-2">
					<select class="form-control" id="gender" name="person.gender">
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</div>
				<label for="age" class="col-sm-1 control-label">年龄</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="age" name="person.age">
				</div>
			</div>
			<div id="districtDiv" class="form-group">
				<label for="province" class="col-sm-1 control-label">省</label>
				<div class="col-sm-2">
					<select class="form-control" id="province" name="person.province"></select>
				</div>
				<label for="city" class="col-sm-1 control-label">市</label>
				<div class="col-sm-2">
					<select class="form-control" id="city" name="person.city"></select>
				</div>
				<label for="county" class="col-sm-1 control-label">区县</label>
				<div class="col-sm-2">
					<select class="form-control" id="county" name="person.county"></select>
				</div>
				<label for="town" class="col-sm-1 control-label">镇/乡/街道</label>
				<div class="col-sm-2">
					<select class="form-control" id="town" name="person.town"></select>
				</div>
			</div>
			<div class="form-group">
				<label for="detailAddr" class="col-sm-1 control-label">详细地址</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="detailAddr" name="person.detailAddr">
				</div>
			</div>
			<div class="form-group">
				<label for="telephone" class="col-sm-1 control-label">电话</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="telephone" name="person.telephone">
				</div>
				<label for="email" class="col-sm-1 control-label">电子邮箱</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="email" name="person.email">
				</div>
				<label for="qq" class="col-sm-1 control-label">QQ</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="qq" name="person.qq">
				</div>
				<label for="fixPhone" class="col-sm-1 control-label">固定电话</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="fixPhone" name="person.fixPhone">
				</div>
			</div>
			<div class="form-group">
				<label for="wechat" class="col-sm-1 control-label">微信</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="wechat" name="person.wechat">
				</div>
				<label for="postalCode" class="col-sm-1 control-label">邮政编码</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="postalCode" name="person.postalCode">
				</div>
				<label for="idCard" class="col-sm-1 control-label">身份证号</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="idCard" name="person.idCard">
				</div>
				<label for="idCardPhoto" class="col-sm-1 control-label">身份证号</label>
				<div class="col-sm-2">
					<span class="btn btn-success fileinput-button">
					<i class="glyphicon glyphicon-upload"></i>
					<span>上传照片</span>
				</span>
					<input id="idCardPhoto" type="file" name="files" multiple>
				</div>
			</div>
			
			<%-- 快产专家-承包信息 --%>
			<input type="hidden" name="contractor.id"/>
			<div class="form-group">
				<label for="auditState" class="col-sm-1 control-label">加工类型</label>
				<div class="col-sm-2">
					<select class="form-control" id="processType" name="contractor.processType">
						<c:forEach var="constantDict" items="${processTypes}">
							<option value="${constantDict.constantValue}">${constantDict.constantName}</option>
						</c:forEach>
					</select>
				</div>
				<label for="processYear" class="col-sm-1 control-label">加工年限</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="processYear" name="contractor.processYear">
				</div>
				<label for="workerAmount" class="col-sm-1 control-label">工人数量</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="workerAmount" name="contractor.workerAmount">
				</div>
				<label for="quote" class="col-sm-1 control-label">报价</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="quote" name="contractor.quote">
				</div>
			</div>
			<div class="form-group">
				<label for="equipment" class="col-sm-1 control-label">生产设备</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="equipment" name="contractor.equipment">
				</div>
			</div>
			<div class="form-group">
				<label for="processDesc" class="col-sm-1 control-label">加工说明</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="processDesc" name="contractor.processDesc">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-4 col-sm-offset-8" style="text-align:right">
					<button type="submit" name="save" class="btn btn-primary">保存</button>
					<button type="button" class="btn btn-default" onclick="cancel()">取消</button>
				</div>
			</div>
			
		</form>
	</div><!-- panel-body -->
</div><!-- panel -->

<script src="plugin/jquery.form.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery.formFill.js"></script>
<script src="JS/backstage/contractor/editContractor.js"></script>