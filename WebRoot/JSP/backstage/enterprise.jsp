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
    
    <title>企业信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
	<link href="plugin/jquery-confirm/jquery-confirm.min.css" rel="stylesheet">
	<link href="plugin/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	<link href="plugin/jQuery-File-Upload/css/jquery.fileupload.css" rel="stylesheet">

  </head>
  
  <body>
   <div class="panel panel-primary">
		<div class="panel-heading">企业信息管理</div>
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
			<span class="btn btn-success fileinput-button"> <i
				class="glyphicon glyphicon-plus"></i> <span>批量导入</span>
				<!-- The file input field used as target for the file upload widget -->
				<input id="fileupload" type="file" accept="application/vnd.ms-excel"
				name="file" data-url="enterprise/uploadExcel">
			</span>
		</div><!-- .row -->
		
	
			<!-- 进度条 -->
			<div id="progress" class="progress">
				<div class="progress-bar progress-bar-success"></div>
			</div>
			
			<!-- 数据表格 -->
		<table id="dg" data-toggle="table" data-url="enterprise/getAllByPage" data-unique-id="id"
				data-pagination="true"
				data-side-pagination="server"
				data-query-params="getQueryParams"
				data-page-size="5"
				data-page-list="[5,10]">
		    <thead>
		        <tr>
		        	<th data-formatter="seqnumFormatter" class="col-xs-1" data-align="center">编号</th>
		            <th data-field="userName" data-align="center">企业名称</th>
		            <th data-field="realName" data-align="center">联系人</th>
		            <th data-field="telephone" data-align="center">省份</th>
		            <th data-field="createTime" data-align="center">地市</th>
		            <th data-field="createTime" data-align="center">区县</th>
		            <th data-field="createTime" data-align="center">镇乡</th>
		            <th data-field="createTime" data-align="center">详细地址</th>
		            <th data-field="createTime" data-align="center">固定电话</th>
		            <th data-field="createTime" data-align="center">手机</th>
		            <th data-field="createTime" data-align="center">QQ</th>
		            <th data-field="createTime" data-align="center">销售市场</th>
		            <th data-field="createTime" data-align="center">营业执照</th>
		            <th data-field="createTime" data-align="center">组织机构代码</th>
		            <th data-field="createTime" data-align="center">行业分类</th>
		            <th data-field="createTime" data-align="center">加工类型</th>
		            <th data-field="createTime" data-align="center">主营产品</th>
		            <th data-field="createTime" data-align="center">员工总人数</th>
		            <th data-field="createTime" data-align="center">高速车工人数</th>
		            <th data-field="createTime" data-align="center">其他加工人数</th>
		            <th data-field="createTime" data-align="center">工厂年限</th>
		             <th data-field="createTime" data-align="center">生产设备</th>
		              <th data-field="createTime" data-align="center">产值</th>
		               <th data-field="createTime" data-align="center">合作客户</th>
		                <th data-field="createTime" data-align="center">企业网址</th>
		                 <th data-field="createTime" data-align="center">微信号</th>
		                  <th data-field="createTime" data-align="center">邮箱</th>
		        </tr>
		    </thead>
		</table>
			
			
			<!-- 已上传图片文件列表 -->
			<div id="files" class="files"></div>
		</div>
	</div>
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

<script src="JS/util/bsFormTableExtend.js"></script>
<script src="JS/util/jqConfirmExtend.js"></script>
<script src="JS/backstage/district.js"></script>
</html>
