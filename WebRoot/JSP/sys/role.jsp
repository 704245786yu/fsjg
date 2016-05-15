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
	<title>角色管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
	<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
	<link href="plugin/jquery-confirm/jquery-confirm.min.css" rel="stylesheet">
	<link href="plugin/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	<link rel="stylesheet" href="plugin/jquery-treegrid/css/jquery.treegrid.css">
	<link rel="stylesheet" href="plugin/zTree/css/metroStyle/metroStyle.css">
</head>
<body>

<div class="panel panel-primary">
  <div class="panel-heading">
    	角色管理
  </div>
  <div class="panel-body">
  	<!-- 搜索框、新增按钮 -->
    <div  class="row" style="width:100%;padding:10px;">
		<div class="col-sm-4">
			<div class="input-group">
		      <span class="input-group-btn">
		        <button type="button" class="btn btn-primary" onclick="search()">
		        	<span class="glyphicon glyphicon-search"></span>
		        </button>
		      </span>
		      <input type="text" class="form-control" id="searchText" placeholder="角色名">
		    </div><!-- /input-group -->
	    </div><!-- /.col-sm-4 -->
	    
	    <div class="col-sm-1">
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#formModal" onclick="add()">
				<span class="glyphicon glyphicon-plus"></span> 添加
			</button>
		</div>
	</div><!-- .row -->
	
	<!-- 数据表格 -->
	<table id="dg" data-toggle="table" data-url="role/getAll" data-unique-id="id">
	    <thead>
	        <tr>
	        	<th data-formatter="seqnumFormatter" class="col-xs-1" data-align="center">序号</th>
	            <th data-field="roleName" data-align="center" data-sortable="true">角色名</th>
	            <th data-field="description" data-align="center">描述</th>
	            <th data-field="updateTime" data-align="center">更新时间</th>
	            <th data-formatter="operFormatter" class="col-sm-2" data-align="center">操作</th>
	        </tr>
	    </thead>
	</table>
  </div><!-- panel-body -->
</div><!-- panel -->
	
<!-- 添加/更新模态框 -->
<div class="modal fade" id="formModal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="dialog" style="width:600px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
				<h4 class="modal-title" id="formModalLabel">编辑角色</h4>
			</div>
			<div class="modal-body">
				<form id="ff" method="post" class="form-horizontal row">
					<!-- 菜单树 -->
					<div class="col-sm-6">
						<h5><b>菜单</b></h5>
						<ul id="tree" class="ztree"></ul>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label for="roleName" class="col-sm-3 control-label">角色名</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="roleName" name="roleName">
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-3 control-label">描述</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="description" name="description">
							</div>
						</div>
						<!-- 权限列表 -->
						<div class="form-group">
							<label class="col-sm-3 control-label">权限</label>
							<div class="col-sm-9">
								<div class="list-group">
									<c:forEach var="authority" items="${authorities}">
										<a href="javascript:void(0)" class="list-group-item">
											<div class="checkbox">
												<label>
													<input type="checkbox" name="authorityId" value="${authority.id}"/> ${authority.authorityName}
												</label>
											</div>
										</a>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-4 col-sm-offset-8" style="text-align:right">
						<button type="submit" name="save" class="btn btn-primary">保存</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
					<input type="hidden" name="id"/>
				</form>
			</div><!-- modal-body -->
		</div><!-- modal-content -->
	</div><!-- modal-dialog -->
</div><!-- modal -->

<!-- 详细信息 -->
<div class="modal fade" id="detailModal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="dialog" style="width:500px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
				<h4 class="modal-title" id="modalTitle"></h4>
			</div>
			<div class="modal-body">
			
				<!-- 树形菜单表 -->
				<div class="panel panel-success">
					<div class="panel-heading">菜单</div>
					<div class="panel-body">
						<table id="menuDg" data-classes="table" data-toggle="table" data-row-style="rowStyle" data-show-header="false">
							<thead>
								<tr>
									<th data-field="menuName">菜单</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				
				<!-- 权限列表 -->
				<ul id="listGroup" class="list-group">
					<li class="list-group-item list-group-item-success">权限</li>
				</ul>
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
<script src="plugin/zTree/js/jquery.ztree.core.min.js"></script>
<script src="plugin/zTree/js/jquery.ztree.excheck.min.js"></script>

<script src="JS/util/bsFormTableExtend.js"></script>
<script src="JS/util/jqConfirmExtend.js"></script>
<script src="JS/util/treeUtil.js"></script>
<script src="JS/sys/role.js"></script>
</html>
