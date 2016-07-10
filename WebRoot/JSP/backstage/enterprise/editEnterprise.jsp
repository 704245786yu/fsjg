<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
<div class="panel">
<div id="editPanel" class="panel panel-primary" style="display:none;">
	<div class="panel-heading">编辑工厂用户信息</div>
	<div class="panel-body">
		<form id="ff" method="post" class="form-horizontal">
			<input type="hidden" name="basicUser.id"/>
			<input type="hidden" name="id"/>
			
			<div class="form-group">
				<label class="col-sm-1 control-label">用户名 </label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="basicUser.userName">
				</div>
				<label class="col-sm-1 control-label">工厂编号</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="enterpriseNumber">
				</div>
				<label class="col-sm-1 control-label">工厂名称</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" name="enterpriseName">
				</div>
				<label class="col-sm-1 control-label">联系人</label>
				<div class="col-sm-1">
					<input type="text" class="form-control" name="linkman">
				</div>
			</div>
			<div id="districtContainer" class="form-group">
				<label for="province" class="col-sm-1 control-label">省</label>
				<div class="col-sm-2">
					<select class="form-control" id="province" name="province"></select>
				</div>
				<label for="city" class="col-sm-1 control-label">市</label>
				<div class="col-sm-2">
					<select class="form-control" id="city" name="city"></select>
				</div>
				<label for="county" class="col-sm-1 control-label">区县</label>
				<div class="col-sm-2">
					<select class="form-control" id="county" name="county"></select>
				</div>
				<label for="town" class="col-sm-1 control-label">镇/乡/街道</label>
				<div class="col-sm-2">
					<select class="form-control" id="town" name="town"></select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">详细地址</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="detailAddr">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">电话</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="basicUser.telephone">
				</div>
				<label class="col-sm-1 control-label">电子邮箱</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="email">
				</div>
				<label class="col-sm-1 control-label">QQ</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="qq">
				</div>
				<label class="col-sm-1 control-label">固定电话</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="fixPhone">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">微信</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="wechat">
				</div>
				<!-- 销售市场、营业执照 -->
				<label class="col-sm-1 control-label">组织机构代码</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="orgCode">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">行业分类</label>
				<div class="col-sm-2">
					<select class="form-control" name="trade" multiple></select>
				</div>
				<label class="col-sm-1 control-label">加工类型</label>
				<div class="col-sm-2">
					<select class="form-control" name="processType" multiple>
						<c:forEach var="constantDict" items="${processTypes}">
							<option value="${constantDict.constantValue}">${constantDict.constantName}</option>
						</c:forEach>
					</select>
				</div>
				<!-- 主营产品 -->
				<label class="col-sm-1 control-label">经营年限</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="enterpriseAge">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">工人数量</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="staffNumber">
				</div>
				<label class="col-sm-1 control-label">高速车工人数</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="highSpeedStaffNumber">
				</div>
				<label class="col-sm-1 control-label">其他加工人数</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="otherStaffNumber">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">生产设备</label>
				<div class="col-sm-11">
					<input type="text" class="form-control" name="equipment">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">产值产量</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="yield">
				</div>
				<label class="col-sm-1 control-label">合作客户</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="cooperator">
				</div>
				<label class="col-sm-1 control-label">企业网址</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="websiteUrl">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">工厂描述</label>
				<div class="col-sm-11">
					<input type="text" class="form-control" name="description">
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
</div>

<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery.formautofill.min.js"></script>
<script src="JS/util/districtCascade.js"></script>
<script src="JS/backstage/enterprise/editEnterprise.js"></script>