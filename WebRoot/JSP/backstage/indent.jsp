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
    <title>后台订单管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
	<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
	<link href="plugin/jquery-confirm/jquery-confirm.min.css" rel="stylesheet">
	<link href="plugin/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	<link href="plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
  
<body>
<div id="listPanel" class="panel panel-primary">
	<div class="panel-heading">
		订单管理
	</div>
	<div class="panel-body">
		<!-- 查询条件 -->
		<div class="form-inline" style="padding-bottom:10px;">
			<div class="form-group">
				<label>订单编号</label>
				<input type="text" class="form-control" name="indentNum">
			</div>
			<div class="form-group">
				<label>订单状态</label>
				<select class="form-control" name="state">
					<option value="">全部</option>
					<option value="0">未收到报价</option>
					<option value="1">已收到报价</option>
					<option value="2">已接单</option>
					<option value="3">已实效</option>
				</select>
			</div>
			<div class="form-group">
				<label for="startDate">发布日期</label>
				<div class="input-group date">
	                <input type="text" class="form-control" name="beginDate"/>
	                <span class="input-group-addon">
	                	<span class="glyphicon glyphicon-calendar"></span>
	                </span>
	            </div>
				至
				<div class="input-group date">
	                <input type="text" class="form-control" name="endDate"/>
	                <span class="input-group-addon">
	                	<span class="glyphicon glyphicon-calendar"></span>
	                </span>
	            </div>
			</div>
			<div class="form-group">
				<button type="button" class="btn btn-primary" onclick="search()">查询</button>
			</div>
	    </div><!-- form-inline -->
	
		<!-- 数据表格 -->
		<table id="dg" data-toggle="table" data-url="indent/findByPage" data-unique-id="id"
				data-pagination="true"
				data-side-pagination="server"
				data-query-params="queryParams"
				data-page-size="10"
				data-page-list="[10,20]">
		    <thead>
		        <tr>
		        	<th data-formatter="seqnumFormatter" class="col-xs-1" data-align="center">序号</th>
		            <th data-field="indentNum" data-align="center">订单编号</th>
		            <th data-field="userName" data-align="center">发单用户</th>
		            <th data-field="createUserType" data-align="center">用户类型</th>
		            <th data-field="indentName" data-align="center">订单名称</th>
		            <th data-field="quantity" data-align="center">订单数量(件)</th>
		            <th data-field="price" data-align="center">成交价(元)</th>
		            <th data-field="state" data-align="center">订单状态</th>
		            <th data-field="createTime" data-align="center">发布时间</th>
		            <th data-formatter="operFormatter" class="col-sm-2" data-align="center">操作</th>
		        </tr>
		    </thead>
		</table>
	</div><!-- panel-body -->
</div><!-- panel -->

<!-- 查看订单 -->
<div id="viewPanel" class="panel panel-primary" style="display:none;">
	<button type="button" class="btn btn-primary" onclick="hideView()" style="width:100px;"><span class="glyphicon glyphicon-step-backward"></span>返回</button>
	<div class="panel-body">
		<div class="form-horizontal">
			<input type="hidden" name="id">
			<div class="form-group">
				<label class="col-sm-1 control-label">订单编号:</label>
				<div class="col-sm-3">
					<p class="form-control-static" name="indentNum"></p>
				</div>
				<label class="col-sm-1 control-label">订单类别:</label>
				<div class="col-sm-3">
					<p class="form-control-static" name="indentType"></p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">订单名称:</label>
				<div class="col-sm-3">
					<p class="form-control-static" name="indentName"></p>
				</div>
				<label class="col-sm-1 control-label">订单数量:</label>
				<div class="col-sm-3">
					<p class="form-control-static" name="quantity"></p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">订单描述:</label>
				<div class="col-sm-3">
					<p class="form-control-static" name="description"></p>
				</div>
				<label class="col-sm-1 control-label">销售市场:</label>
				<div class="col-sm-3">
					<p class="form-control-static" name="telephone"></p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">加工类型:</label>
				<div class="col-sm-3">
					<p class="form-control-static" name="processType"></p>
				</div>
				<label class="col-sm-1 control-label">预期价:</label>
				<div class="col-sm-3">
					<p class="form-control-static" name="expectPrice"></p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">预计交货日期:</label>
				<div class="col-sm-3">
					<p class="form-control-static" name="preDeliveryDate"></p>
				</div>
				<label class="col-sm-1 control-label">是否急单:</label>
				<div class="col-sm-3">
					<p class="form-control-static" name="isUrgency"></p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">有效日期:</label>
				<div class="col-sm-3">
					<p class="form-control-static" name="effectiveDate"></p>
				</div>
				<label class="col-sm-1 control-label">详情:</label>
				<div class="col-sm-3">
					<p class="form-control-static" name="detail"></p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">身份证照:</label>
				<div class="col-sm-9">
					<p class="form-control-static" name="authenticationState">
						<img name="idFrontPhoto" style="width:200px;height:150px;margin-right:20px;"/>
						<img name="idBackPhoto" style="width:200px;height:150px"/>
					</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">审核状态:</label>
				<div class="col-sm-3">
					<p class="form-control-static" name="auditState"></p>
				</div>
				<label class="col-sm-1 control-label">用户状态:</label>
				<div class="col-sm-3">
					<p class="form-control-static" name="state"></p>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-4">
					<button type="button" class="btn btn-primary" onclick="audit(3)" style="width:100px;">审核通过</button>
					<button type="button" class="btn btn-primary" onclick="audit(2)" style="width:100px;">审核不通过</button>
				</div>
			</div>
		</div>
	</div><!-- panel-body -->
</div><!-- panel -->

</body>

<script src="plugin/jquery.min.js"></script>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery.formautofill.min.js"></script>
<script src="plugin/jquery-confirm/jquery-confirm.min.js"></script>
<script src="plugin/bootstrap-table/bootstrap-table.min.js"></script>
<script src="plugin/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="plugin/bootstrap-datetimepicker/js/moment-with-locales.js"></script>
<script src="plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="plugin/jquery.mask.min.js"></script>

<script src="JS/util/bsFormTableExtend.js"></script>
<script src="JS/util/jqConfirmExtend.js"></script>
<script src="JS/backstage/person.js"></script>
</html>
