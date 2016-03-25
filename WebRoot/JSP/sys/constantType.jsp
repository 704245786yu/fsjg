<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>">
	<title>常量类型</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
	<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
	<link href="plugin/jquery-easyui/themes/default/easyui.css" rel="stylesheet"/>
	<link href="CSS/common/customEasyui.css" rel="stylesheet"/>
</head>
<body>
	<div id="tb" style="height:auto;padding:10px;">
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#formModal" onclick="add()">添加</button>
		用户名：<input class="easyui-textbox"type="text" name="searchValue"></input>
	</div>
	
	<table id="dg" class="easyui-datagrid"
		style="width:100%;max-height:600px;text-align:center;"
		data-options="
				singleSelect: true,
				toolbar: '',
				url:'constantType/getAll'
			">
		<thead>
			<tr>
			    <th data-options="field:'constantTypeCode',width:200,align:'center'">常量类型编码</th>
				<th data-options="field:'constantTypeName',width:200,align:'center'">常量类型名</th>
				<th data-options="field:'_operate',width:200,align:'center',formatter:formatOper">操作</th>  
			</tr>
		</thead>
	</table>
	
<!-- 添加/更新模态框 -->
<div class="modal fade" id="formModal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="dialog" style="width:500px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
				<h4 class="modal-title" id="formModalLabel">编辑常量类型</h4>
			</div>
			<div class="modal-body">
				<form id="ff" method="post" class="form-horizontal" action="constantType/save">
					<div class="form-group">
						<label for="constantTypeCode" class="col-sm-3 control-label">常量类型编码 </label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="constantTypeCode" name="constantTypeCode">
						</div>
					</div>
					<div class="form-group">
						<label for="constantTypeName" class="col-sm-3 control-label">常量类型名</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="constantTypeName" name="constantTypeName">
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

</body>

<script src="plugin/jquery.min.js"></script>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery.formautofill.min.js"></script>
<script src="plugin/jquery-easyui/jquery.easyui.min.js"></script>
<script src="plugin/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="JS/util/easyuiUtil.js"></script>
<script src="JS/sys/constantType.js"></script>
</html>
