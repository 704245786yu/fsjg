<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>">
	<title>服饰类型</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
	<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
	<link href="plugin/jquery-confirm/jquery-confirm.min.css" rel="stylesheet">
	<link href="plugin/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	<link rel="stylesheet" href="plugin/jquery-treegrid/css/jquery.treegrid.css">
</head>
<body>
<div class="panel panel-primary">
  <div class="panel-heading">服饰类型</div>
  <div class="panel-body">
  	<!-- 搜索框、新增按钮 -->
    <div class="row" style="width:100%;padding:10px;">
		<div class="col-sm-4">
			<div class="input-group">
		      <span class="input-group-btn">
		        <button type="button" class="btn btn-primary" onclick="search()">
		        	<span class="glyphicon glyphicon-search"></span>
		        </button>
		      </span>
		      <input type="text" class="form-control" id="searchText" placeholder="服饰类型名">
		    </div><!-- /input-group -->
	    </div><!-- /.col-sm-4 -->
	</div><!-- .row -->
	
	<!-- 数据表格 -->
	<table id="dg" data-toggle="table" data-url="costumeCategory/getAdjTree" 
		data-row-style="rowStyle" data-unique-id="id">
	    <thead>
	        <tr>
	            <th data-field="categoryName">服饰类型名</th>
	            <th data-field="categoryCode" data-align="center">服饰类型编码</th>
	            <th data-formatter="operFormatter" class="col-sm-2" data-align="center">操作</th>
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
				<h4 class="modal-title" id="formModalLabel">编辑服饰类型</h4>
			</div>
			<div class="modal-body">
				<form id="ff" method="post" class="form-horizontal" action="constantType/save">
					<div id="newNodePos" class="form-group" style="display:block;">
						<label for="pId" class="col-sm-3 control-label">新节点位置</label>
						<div class="col-sm-9">
							<select class="form-control" id="pId" name="pId"></select>
						</div>
					</div>
					<div class="form-group">
						<label for="categoryName" class="col-sm-3 control-label">服饰类型名</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="categoryName" name="categoryName">
						</div>
					</div>
					<div class="form-group">
						<label for="categoryCode" class="col-sm-3 control-label">服饰类型编码 </label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="categoryCode" name="categoryCode">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-8" style="text-align:right">
							<button type="submit" name="save" class="btn btn-primary">保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</div>
					<input type="hidden" name="id"/>
					<%--若无此pId表单域，在执行修改时，会由于该表单select被隐藏，导致无pId提交，在更新成功执行operFormatter()方法时会不显示删除按钮 --%>
					<input type="hidden" name="pId"/>
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
<script src="plugin/jquery-confirm/jquery-confirm.min.js"></script>
<script src="plugin/bootstrap-table/bootstrap-table.min.js"></script>
<script src="plugin/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="plugin/jquery-treegrid/js/jquery.treegrid.min.js"></script>
<script src="plugin/jquery-treegrid/js/jquery.treegrid.bootstrap3.js"></script>

<script src="JS/util/bsFormTableExtend.js"></script>
<script src="JS/util/jqConfirmExtend.js"></script>
<script src="JS/backstage/costumeCategory.js"></script>
</html>
