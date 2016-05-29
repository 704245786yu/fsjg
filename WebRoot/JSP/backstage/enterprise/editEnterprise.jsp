<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">

<div id="editPanel" class="panel panel-primary" style="display:none;">
	<div class="panel-heading">编辑工厂用户信息</div>
	<div class="panel-body">
		<form id="ff" method="post" class="form-horizontal">
			<input type="hidden" name="id"/>
			<div class="form-group">
				<label for="userName" class="col-sm-3 control-label">用户名 </label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="userName" name="userName">
				</div>
			</div>
			
			<!-- trade_id 行业分类 -->
			
			<div class="form-group">
				<label for="enterpriseNumber" class="col-sm-3 control-label">工厂编号</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="enterpriseNumber" name="enterpriseNumber">
				</div>
			</div>
			<div class="form-group">
				<label for="enterpriseName" class="col-sm-3 control-label">工厂名称</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="enterpriseName" name="enterpriseName">
				</div>
			</div>
			<div class="form-group">
				<label for="linkman" class="col-sm-3 control-label">联系人</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="linkman" name="linkman">
				</div>
			</div>
			<div class="form-group">
				<label for="province" class="col-sm-3 control-label">省</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="province" name="person.province">
				</div>
			</div>
			<div class="form-group">
				<label for="city" class="col-sm-3 control-label">市</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="city" name="person.city">
				</div>
			</div>
			<div class="form-group">
				<label for="county" class="col-sm-3 control-label">区县</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="county" name="person.county">
				</div>
			</div>
			<div class="form-group">
				<label for="town" class="col-sm-3 control-label">镇/乡/街道</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="town" name="person.town">
				</div>
			</div>
			<div class="form-group">
				<label for="detailAddr" class="col-sm-3 control-label">详细地址</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="detailAddr" name="detailAddr">
				</div>
			</div>
			<div class="form-group">
				<label for="telephone" class="col-sm-3 control-label">手机号</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="telephone" name="telephone">
				</div>
			</div>
			<div class="form-group">
				<label for="fixPhone" class="col-sm-3 control-label">固定电话</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="fixPhone" name="fixPhone">
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-3 control-label">电子邮箱</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="email" name="person.email">
				</div>
			</div>
			<div class="form-group">
				<label for="qq" class="col-sm-3 control-label">QQ</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="qq" name="person.qq">
				</div>
			</div>
			
			<div class="form-group">
				<label for="wechat" class="col-sm-3 control-label">微信</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="wechat" name="person.wechat">
				</div>
			</div>
			<div class="form-group">
				<label for="postalCode" class="col-sm-3 control-label">邮政编码</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="postalCode" name="person.postalCode">
				</div>
			</div>
			
			<input type="hidden" name="contractor.id"/>
			<div class="form-group">
				<label for="auditState" class="col-sm-3 control-label">加工类型</label>
				<div class="col-sm-9">
					<select class="form-control" id="processType" name="contractor.processType">
						<c:forEach var="constantDict" items="${processTypes}">
							<option value="${constantDict.constantValue}">${constantDict.constantName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="processYear" class="col-sm-3 control-label">加工年限</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="processYear" name="contractor.processYear">
				</div>
			</div>
			<div class="form-group">
				<label for="workerAmount" class="col-sm-3 control-label">工人数量</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="workerAmount" name="contractor.workerAmount">
				</div>
			</div>
			<div class="form-group">
				<label for="quote" class="col-sm-3 control-label">报价</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="quote" name="contractor.quote">
				</div>
			</div>
			<div class="form-group">
				<label for="equipment" class="col-sm-3 control-label">生产设备</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="equipment" name="contractor.equipment">
				</div>
			</div>
			<div class="form-group">
				<label for="processDesc" class="col-sm-3 control-label">加工说明</label>
				<div class="col-sm-9">
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

<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery.formFill.js"></script>
<script src="JS/backstage/contractor/editEnterprise.js"></script>