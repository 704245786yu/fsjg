<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>用户管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
<link href="plugin/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet">
<link href="plugin/bootstrap-login-form/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="plugin/jquery-confirm/jquery-confirm.min.css" rel="stylesheet">
<link href="plugin/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="plugin/jquery-entropizer/css/jquery-entropizer.min.css" rel="stylesheet">
<style>
/* #meter .entropizer-track {
    background-color: #e8e8e8;
    border-radius: 2px;
    height: 4px;
}
#meter .entropizer-bar {
    height: 4px;
} */
</style>
</head>
<body>
	<div class="panel panel-primary">
		<div class="panel-heading">用户信息管理</div>
		<div class="panel-body">
			<!-- 搜索框、新增按钮 -->
			<div class="row" style="width:100%;padding:10px;">
				<div class="col-sm-4">
					<div class="input-group">
						<span class="input-group-btn">
							<button type="button" class="btn btn-primary" onclick="search()">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span> <input type="text" class="form-control" id="searchText"
							placeholder="用户名">
					</div><!-- /input-group -->
				</div><!-- /.col-sm-4 -->

				<div class="col-sm-1">
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#formModal" onclick="add()">
						<span class="glyphicon glyphicon-plus"></span> 添加
					</button>
				</div>
			</div>
			<!-- .row -->

			<!-- 数据表格 -->
			<table id="dg" data-toggle="table"
				data-url="user/findByPageAndParams" data-unique-id="id"
				data-pagination="true" data-side-pagination="server"
				data-query-params="getQueryParams" data-page-size="20"
				data-page-list="[10,20,40]">
				<thead>
					<tr>
						<th data-formatter="seqnumFormatter" data-align="center">序号</th>
						<th data-field="userName" data-align="center">用户名</th>
						<th data-field="realName" data-align="center">姓名</th>
						<th data-field="roleId" data-align="center" data-formatter="roleFormatter">角色</th>
						<th data-formatter="operFormatter" data-align="center">操作</th>
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
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 class="modal-title" id="formModalLabel">编辑用户信息</h4>
				</div>
				<div class="modal-body">
					<form id="ff" method="post" class="form-horizontal" action="user/save">
						<input type="hidden" name="id" />
						<div class="form-group">
							<label for="userName" class="col-sm-3 control-label">用户名</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="userName" name="userName">
							</div>
						</div>
						<div class="form-group">
							<label for="realName" class="col-sm-3 control-label">姓名</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="realName" name="realName">
							</div>
						</div>
						<div class="form-group">
							<label for="roleId" class="col-sm-3 control-label">角色</label>
							<div class="col-sm-9">
								<select class="selectpicker" id="roleId" name="roleId" data-width="100%">
									<c:forEach var="role" items="${roles}">
										<option value="${role.id}">${role.roleName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-8" style="text-align:right">
								<button type="submit" name="save" class="btn btn-primary">保存</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							</div>
						</div>
					</form>
				</div><!-- modal-body -->
			</div><!-- modal-content -->
		</div><!-- modal-dialog -->
	</div><!-- modal -->
	
	<!-- 修改密码模态框 -->
	<div class="modal fade" id="pwdformModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="dialog" style="width:500px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span>&times;</span>
					</button>
					<h4 class="modal-title" id="formModalLabel">修改密码</h4>
				</div>
				<div class="modal-body">
					<form id="modifyPwdForm" method="post" class="form-horizontal" action="user/modifyPwd">
						<input type="hidden" name="id"/>
						<div class="form-group">
							<label for="oldPassword" class="col-sm-3 control-label">原密码</label>
							<div class="col-sm-9">
								<input type="password" class="form-control" id="oldPassword" name="oldPassword" placeholder="初始密码为123456">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">新密码</label>
							<div class="col-sm-9">
								<input type="password" class="form-control" id="password" name="password">
							</div>
						</div>
						<div class="form-group">
	                    	<div id="meter" class="col-md-9 col-md-offset-3"></div>
                        </div>
						<div class="form-group">
							<label for="rePassword" class="col-sm-3 control-label">密码确认</label>
							<div class="col-sm-9">
								<input type="password" class="form-control" id="rePassword" name="rePassword" autocomplete="off">
							</div>
                        <span id="passstrength"></span>
						</div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-8" style="text-align:right">
								<button type="submit" name="save" class="btn btn-primary">保存</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							</div>
						</div>
					</form>
				</div><!-- modal-body -->
			</div><!-- modal-content -->
		</div><!-- modal-dialog -->
	</div><!-- modal -->
</body>
<script src="plugin/jquery.min.js"></script>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrap-select/js/bootstrap-select.min.js"></script>
<script src="plugin/bootstrap-select/js/i18n/defaults-zh_CN.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery.formautofill.min.js"></script>
<script src="plugin/jquery-confirm/jquery-confirm.min.js"></script>
<script src="plugin/bootstrap-table/bootstrap-table.min.js"></script>
<script src="plugin/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="plugin/jquery-entropizer/lib/entropizer.js"></script>
<script src="plugin/jquery-entropizer/js/jquery-entropizer.min.js"></script>

<script src="JS/util/bsFormTableExtend.js"></script>
<script src="JS/util/jqConfirmExtend.js"></script>
<script src="JS/sys/user.js"></script>
</html>