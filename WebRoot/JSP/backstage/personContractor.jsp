<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>快产专家管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
	<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
	<link href="plugin/jquery-confirm/jquery-confirm.min.css" rel="stylesheet">
	<link href="plugin/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	<link href="plugin/jQuery-File-Upload/css/jquery.fileupload.css" rel="stylesheet">
	<link href="plugin/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
</head>
  
<body>
<div class="panel panel-primary">
	<div class="panel-heading">快产专家管理</div>
	<div class="panel-body">
		<!-- 查询条件 -->
		<div class="form-inline" style="padding-bottom:10px;">
			<div class="form-group">
				<label for="startDate">发布日期</label>
				<input type="text" class="form-control" style="width:270px;" id="daterange" name="daterange">
			</div>
			<div class="form-group">
				<label for="workerAmount">员工数量</label>
				<input type="text" class="form-control" name="workerAmount">
			</div>
			<div class="form-group">
				<label for="processYear">加工年限</label>
				<input type="text" class="form-control" name="processYear">
			</div>
			<div class="form-group">
				<button type="button" class="btn btn-primary">查询</button>
				<button type="button" class="btn btn-primary">清空</button>
			</div>
	    </div><!-- form-inline -->
	    
	    <!-- 新增、批量导入按钮 -->
	    <div class="row" style="padding-bottom:10px;">
			<div class="col-sm-2">
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#formModal" onclick="add()">
					<span class="glyphicon glyphicon-plus"></span> 添加
				</button>
				<span class="btn btn-success fileinput-button">
					<i class="glyphicon glyphicon-upload"></i>
					<span>批量导入</span>
					<input id="fileupload" type="file" name="file" data-url="personContractor/uploadExcel">
				</span>
			</div>
		</div><!-- .row -->
	
		<!-- 数据表格 -->
		<table id="dg" data-toggle="table" data-url="personContractor/findByPageAndParams" data-unique-id="id"
				data-pagination="true"
				data-side-pagination="server"
				data-query-params="getQueryParams"
				data-page-size="5"
				data-page-list="[5,10]">
		    <thead>
		        <tr>
		        	<th data-formatter="seqnumFormatter" class="col-xs-1" data-align="center">序号</th>
		            <th data-field="realName" data-align="center">姓名</th>
		            <th data-field="processType" data-align="center" data-formatter="processFormatter">加工类型 </th>
		            <th data-field="processYear" data-align="center">加工年限(年)</th>
		            <th data-field="workerAmount" data-align="center">工人数量</th>
		            <th data-field="createTime" data-align="center" data-formatter="dateFormatter">创建时间</th>
		            <th data-formatter="operFormatterVUD" class="col-sm-2" data-align="center">操作</th>
		        </tr>
		    </thead>
		</table>
	</div><!-- panel-body -->
</div><!-- panel -->

<!-- 添加/更新模态框 -->
<div class="modal fade" id="formModal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="dialog" style="width:500px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
				<h4 class="modal-title" id="formModalLabel">编辑快产专家</h4>
			</div>
			<div class="modal-body">
				<form id="ff" method="post" class="form-horizontal" action="person/save">
					<div class="form-group">
						<label for="auditState" class="col-sm-3 control-label">加工类型</label>
						<div class="col-sm-9">
							<select class="form-control" id="processType" name="processType">
								<c:forEach var="constantDict" items="${processTypes}">
									<option value="${constantDict.constantValue}">${constantDict.constantName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="processYear" class="col-sm-3 control-label">加工年限</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="processYear" name="processYear">
						</div>
					</div>
					<div class="form-group">
						<label for="workerAmount" class="col-sm-3 control-label">工人数量</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="workerAmount" name="workerAmount">
						</div>
					</div>
					<div class="form-group">
						<label for="quote" class="col-sm-3 control-label">报价</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="quote" name="quote">
						</div>
					</div>
					<div class="form-group">
						<label for="equipment" class="col-sm-3 control-label">生产设备</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="equipment" name="equipment">
						</div>
					</div>
					<div class="form-group">
						<label for="processDesc" class="col-sm-3 control-label">加工说明</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="processDesc" name="processDesc">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-8" style="text-align:right">
							<button type="submit" name="save" class="btn btn-primary">保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</div>
					<input type="hidden" name="id"/>
				</form>
			</div><!-- modal-body -->
		</div><!-- modal-content -->
	</div><!-- modal-dialog -->
</div><!-- modal -->

<!-- 查看快产专家 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="dialog" style="width:500px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
				<h4 class="modal-title">查看快产专家</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-3 control-label">用户名 </label>
						<div class="col-sm-9">
							<p class="form-control-static" name="userName"></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">真实姓名</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="realName"></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">性别</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="gender"></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">年龄</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="age"></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">省</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="province"></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">市</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="city"></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">区县</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="county"></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">镇/乡/街道</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="town"></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">详细地址</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="detailAddr"></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">电话</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="telephone"></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">实名认证状态</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="authenticationState"></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">审核状态</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="auditState"></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">用户状态</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="personState"></p>
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-3 control-label">电子邮箱</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="email"></p>
						</div>
					</div>
					<div class="form-group">
						<label for="qq" class="col-sm-3 control-label">QQ</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="qq"></p>
						</div>
					</div>
					<div class="form-group">
						<label for="fixPhone" class="col-sm-3 control-label">固定电话</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="fixPhone"></p>
						</div>
					</div>
					<div class="form-group">
						<label for="wechat" class="col-sm-3 control-label">微信</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="wechat"></p>
						</div>
					</div>
					<div class="form-group">
						<label for="idCard" class="col-sm-3 control-label">身份证号</label>
						<div class="col-sm-9">
							<p class="form-control-static" name="idCard"></p>
						</div>
					</div>
				</div>
			</div><!-- modal-body -->
		</div><!-- modal-content -->
	</div><!-- modal-dialog -->
</div><!-- modal -->

</body>

<script src="plugin/jquery.min.js"></script>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery.formautofill.min.js"></script>
<script src="plugin/jquery-confirm/jquery-confirm.min.js"></script>
<script src="plugin/bootstrap-table/bootstrap-table.min.js"></script>
<script src="plugin/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="plugin/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
<script src="plugin/jQuery-File-Upload/js/jquery.fileupload.js"></script>

<script src="plugin/bootstrap-daterangepicker/moment.min.js"></script>
<script src="plugin/bootstrap-daterangepicker/daterangepicker.js"></script>

<script src="JS/util/bsFormTableExtend.js"></script>
<script src="JS/util/jqConfirmExtend.js"></script>
<script src="JS/util/MyJsDate.js"></script>
<script src="JS/backstage/personContractor.js"></script>
</html>
