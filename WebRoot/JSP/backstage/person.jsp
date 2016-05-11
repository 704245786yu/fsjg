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
    <title>后台个人用户管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
	<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
	<link href="plugin/jquery-confirm/jquery-confirm.min.css" rel="stylesheet">
	<link href="plugin/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
</head>
  
<body>
<div class="panel panel-primary">
	<div class="panel-heading">
		个人用户管理
	</div>
	<div class="panel-body">
		<!-- 搜索框、新增按钮 -->
		<div id="tb" class="row" style="width:100%;padding:10px;">
			<div class="col-sm-4">
				<div class="input-group">
					<span class="input-group-btn">
						<button type="button" class="btn btn-primary" onclick="search()">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</span>
					<input type="text" class="form-control" id="searchText" placeholder="常量名称">
				</div><!-- /input-group -->
			</div><!-- /.col-sm-4 -->
	    
			<div class="col-sm-1">
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#formModal" onclick="add()">
					<span class="glyphicon glyphicon-plus"></span> 添加
				</button>
			</div>
		</div><!-- .row -->
	
		<!-- 数据表格 -->
		<table id="dg" data-toggle="table" data-url="person/getAllByPage" data-unique-id="id"
				data-pagination="true"
				data-side-pagination="server"
				data-query-params="getQueryParams"
				data-page-size="5"
				data-page-list="[5,10]">
		    <thead>
		        <tr>
		        	<th data-formatter="seqnumFormatter" class="col-xs-1" data-align="center">序号</th>
		            <th data-field="userName" data-align="center">用户名</th>
		            <th data-field="realName" data-align="center">真实姓名</th>
		            <th data-field="telephone" data-align="center">手机号码</th>
		            <th data-field="createTime" data-align="center">注册时间</th>
		            <th data-field="authenticationState" data-align="center" data-formatter="authenFormatter">个人实名状态</th>
		            <th data-field="auditState" data-align="center" data-formatter="auditFormatter">审核状态</th>
		            <th data-field="personState" data-align="center" data-formatter="personFormatter">用户状态</th>
		            <th data-formatter="operFormatter" class="col-sm-1" data-align="center">操作</th>
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
				<h4 class="modal-title" id="formModalLabel">编辑个人用户</h4>
			</div>
			<div class="modal-body">
				<form id="ff" method="post" class="form-horizontal" action="person/save">
					<div class="form-group">
						<label for="userName" class="col-sm-3 control-label">用户名 </label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="userName" name="userName">
						</div>
					</div>
					<div class="form-group">
						<label for="realName" class="col-sm-3 control-label">真实姓名</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="realName" name="realName">
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-3 control-label">密码</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="password" name="password">
						</div>
					</div>
					<div class="form-group">
						<label for="gender" class="col-sm-3 control-label">性别</label>
						<div class="col-sm-9">
							<select class="form-control" id="gender" name="gender">
								<c:forEach var="constantDict" items="${gender}">
									<option value="${constantDict.constantValue}">${constantDict.constantName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="age" class="col-sm-3 control-label">年龄</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="age" name="age">
						</div>
					</div>
					<div class="form-group">
						<label for="province" class="col-sm-3 control-label">省</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="province" name="province">
						</div>
					</div>
					<div class="form-group">
						<label for="city" class="col-sm-3 control-label">市</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="city" name="city">
						</div>
					</div>
					<div class="form-group">
						<label for="county" class="col-sm-3 control-label">县</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="county" name="county">
						</div>
					</div>
					<div class="form-group">
						<label for="town" class="col-sm-3 control-label">镇/街道</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="town" name="town">
						</div>
					</div>
					<div class="form-group">
						<label for="detailAddr" class="col-sm-3 control-label">详细地址</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="detailAddr" name="detailAddr">
						</div>
					</div>
					<div class="form-group">
						<label for="telephone" class="col-sm-3 control-label">电话</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="telephone" name="telephone">
						</div>
					</div>
					<div class="form-group">
						<label for="gender" class="col-sm-3 control-label">审核状态</label>
						<div class="col-sm-9">
							<select class="form-control" id="auditState" name="auditState">
								<c:forEach var="constantDict" items="${auditState}">
									<option value="${constantDict.constantValue}">${constantDict.constantName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="personState" class="col-sm-3 control-label">用户状态</label>
						<div class="col-sm-9">
							<select class="form-control" id="personState" name="personState">
								<c:forEach var="constantDict" items="${personState}">
									<option value="${constantDict.constantValue}">${constantDict.constantName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-3 control-label">电子邮箱</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="email" name="email">
						</div>
					</div>
					<div class="form-group">
						<label for="qq" class="col-sm-3 control-label">QQ</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="qq" name="qq">
						</div>
					</div>
					<div class="form-group">
						<label for="fixPhone" class="col-sm-3 control-label">固定电话</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="fixPhone" name="fixPhone">
						</div>
					</div>
					<div class="form-group">
						<label for="wechat" class="col-sm-3 control-label">微信</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="wechat" name="wechat">
						</div>
					</div>
					<div class="form-group">
						<label for="idCard" class="col-sm-3 control-label">身份证号</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="idCard" name="idCard">
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
<script src="plugin/jquery-confirm/jquery-confirm.min.js"></script>
<script src="plugin/bootstrap-table/bootstrap-table.min.js"></script>
<script src="plugin/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="JS/util/bsFormTableExtend.js"></script>
<script src="JS/util/jqConfirmExtend.js"></script>
<script src="JS/backstage/person.js"></script>
</html>
