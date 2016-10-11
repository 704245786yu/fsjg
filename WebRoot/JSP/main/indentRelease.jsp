<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>中国服务加工网-加工订单</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
<link href="plugin/jquery-confirm/jquery-confirm.min.css" rel="stylesheet">
<link href="plugin/jQuery-File-Upload/css/jquery.fileupload.css" rel="stylesheet">
<link href="plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="CSS/common/default.css" rel="stylesheet">
<link href="CSS/indent-release.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<%@ include file="top.jsp" %>
<div style="width:1190px; margin:0 auto;">
	<form id="ff" action="indent/release" method="post" autocomplete="off">
	<table class="title" style="height:78px;">
		<tr>
			<td style="width:365px;text-align:left;padding-left:15px;font-size:24px;color:white;background-color:#00B5FF;">
				<img src="image/indentRelease/release.png" style="margin-right:20px;"/>新建发布订单
			</td>
			<td style="width:260px">发布订单信息</td>
			<td>
				<span class="glyphicon glyphicon-circle-arrow-right" style="font-size:24px;color:#0089A9;"></span>
			</td>
			<td style="width:260px">收到来自工厂报价</td>
			<td>
				<span class="glyphicon glyphicon-circle-arrow-right" style="font-size:24px;color:#0089A9;"></span>
			</td>
			<td style="width:259px">选择合适的工厂洽谈</td>
		</tr>
	</table>
	<div class="panel panel-default">
		<div class="panel-body" style="padding:30px 0 50px 70px;">
			<div class="form-group">
				<div style="float:left;">订单类型：</div>
				<div class="radio" style="margin:0 100px 0 10px;float:left;">
					<label><input type="radio" name="indentType" value="1">来图/来样加工定制（按买家提供的款式加工生产）</label>
				</div>
				<div class="radio" style="margin:0;float:left;">
					<label><input type="radio" name="indentType" value="2">看款下单（挑选工厂的款式下单订货）</label>
				</div>
			</div>
		</div>
	</div>
	<ul id="indentUL" class="list-group">
		<li class="list-group-item">
			<h4 style="margin-bottom:10px;">订单信息</h4>
			<table class="mytable">
				<tr>
					<td style="width:120px;vertical-align: top">订单名称：</td>
					<td style="width:290px;">
						<div class="form-group">
							<input type="text" class="form-control" name="indentName">
						</div>
					</td>
				</tr>
				<tr>
					<td>产品类别：</td>
					<td>
						<jsp:include page="/JSP/main/common/costumeCategoryModal.jsp">
							<jsp:param name="isLimitCheck" value="true"/>
						</jsp:include>
						<input type="hidden" name="costumeCode"/>
					</td>
				</tr>
				<tr>
					<td>预计订单数量：</td>
					<td>
						<div class="form-group">
							<input type="text" class="form-control" name="quantity" style="width:50%;">
						</div>
					</td>
				</tr>
				<tr>
					<td>订单说明：</td>
					<td>
						<div class="form-group">
							<input type="text" class="form-control" name="description">
						</div>
					</td>
				</tr>
				<tr>
					<td>销售市场：</td>
					<td>
						<select class="form-control" name="saleMarket" style="width:50%;">
							<option value="1">内销</option>
							<option value="2">外销</option>
						</select>
					</td>
				</tr>
			</table>
		</li>
		<li class="list-group-item">
			<h4>交易条件</h4>
			<table class="mytable">
				<tr>
					<td style="width:120px;">加工类型：</td>
					<td colspan="2">
						<div class="form-group">
							<c:forEach items="${processTypes}" var="processType">
								<div class="radio" style="margin:0 50px 0 0;float:left;">
									<label><input type="radio" name="processType" value="${processType.constantValue}">${processType.constantName}</label>
								</div>
							</c:forEach>
						</div>
					</td>
				</tr>
				<tr>
					<td>订单目标价：</td>
					<td style="width:290px;">
						<div class="form-group">
							<input type="text" class="form-control" name="expectPrice">
						</div>
					</td>
					<td style="padding-left:10px;">
						<div class="form-group">
							<div class="checkbox">
								<label><input type="checkbox" name="expectPrice" value="-1">面谈</label>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>预计交货日期：</td>
					<td>
						<div class="form-group">
							<div class='input-group date'>
				                <input type='text' id="testDate2" class="form-control" name="preDeliveryDate"/>
				                <span class="input-group-addon">
				                	<span class="glyphicon glyphicon-calendar"></span>
				                </span>
				            </div>
			            </div>
					</td>
					<td style="padding-left:10px;">
						<div class="checkbox">
							<label><input type="checkbox" name="isUrgency" value="1"> 急单</label>
						</div>
					</td>
				</tr>
			</table>
		</li>
		<li class="list-group-item">
			<h4>详细说明</h4>
			<p>请提供详细的订单资料，确保工厂准确报价；资料越详细，排序越靠前，报价的工厂也越多！</p>
			<p>
				<span id="fileUploadImg" class="btn btn-info fileinput-button" style="font-size:20px;background-color:#00A1E8;">
					<span>上传款图</span>
					<input type="file" name="file">
				</span>
				<span id="fileUploadDoc" class="btn btn-info fileinput-button" style="font-size:20px;background-color:#00A1E8;">
					<span>上传附件</span>
					<input type="file" name="file">
				</span>
				<!-- 可上传多个图片 -->
				<input type="hidden" name="photo"/>
				<!-- 只可上传一个文档 -->
				<input type="hidden" name="document"/>
				<div id="imgNames"></div>
				<div id="docName"></div>
			</p>
			<p style="width:70%;">
				<script id="editor" name="detail" type="text/plain"></script>
			</p>
		</li>
		<li class="list-group-item">
			<h4>发布设置</h4>
			<table class="mytable">
				<tr>
					<td style="width:120px;">发布有效期：</td>
					<td style="width:180px;">
						<select id="effectiveDateSel" class="form-control">
							<option value="3">3天</option>
							<option value="7">7天</option>
							<option value="15">15天</option>
							<option value="30">30天</option>
						</select>
					</td>
					<td style="padding-left:10px;">
						在<span id="effectiveDateTxt" style="color:red;"></span>后，结束工厂报价
						<input type="hidden" name="effectiveDate"/>
					</td>
				</tr>
				<tr>
					<td style="width:120px;">工厂报名条件：</td>
				</tr>
				<tr>
					<td style="width:120px;padding-left:2em;">所在地区：</td>
					<td style="width:180px;">
						<button id="districtBtn" type="button" class="btn btn-default btn-select" data-toggle="modal" data-target="#districtModal">选择发单地区</button>
						<!-- 选择地区模态框 -->
						<div class="modal fade" id="districtModal" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
										<h5 class="modal-title" id="myModalLabel">选择地区</h5>
									</div>
									<div id="districtContainer" class="modal-body">
										<div class="row">
											<div class="col-sm-3">
												<select class="form-control" id="province" name="condProvince"></select>
											</div>
											<div class="col-sm-3">
												<select class="form-control" id="city" name="condCity"></select>
											</div>
											<div class="col-sm-3">
												<select class="form-control" id="county" name="condCounty"></select>
											</div>
											<div class="col-sm-3">
												<select class="form-control" id="town" name="condTown"></select>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="checkDistrict()">确定</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									</div>
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding-left:2em;">工人数量：</td>
					<td>
						<select class="form-control" name="condStaffNum">
							<option value="0">不限</option>
							<option value="50">50人以下</option>
							<option value="100">50~100人</option>
							<option value="200">100~200人</option>
							<option value="500">200~500人</option>
							<option value="1000">500~1000人</option>
							<option value="1001">1000人以上</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="padding-top:10px;padding-left:2em;vertical-align:top;">接单条件：</td>
					<td colspan="2" style="padding-top:10px;width:570px;">
						<div class="form-group">
							<textarea name="condDemand" class="form-control" rows="3"></textarea>
						</div>
					</td>
				</tr>
			</table>
		</li>
		<li class="list-group-item">
			<h4>联系方式</h4>
			<table class="mytable">
				<tr>
					<td>联系人：</td>
					<td>
						<div class="form-group">
							<input type="text" class="form-control" style="width:180px;" name="linkman">
						</div>
					</td>
				</tr>
				<tr>
					<td>联系电话：</td>
					<td style="width:290px;">
						<div class="form-group">
							<input type="text" class="form-control" name="telephone" value="${loginBasicUser.telephone}">
						</div>
					</td>
				</tr>
			</table>
		</li>
		<li class="list-group-item" style="padding:30px 0 50px 70px;">
			<button type="submit" class="btn btn-info" style="width:340px;height:40px;font-size:20px;background-color:#00A1E8;">确认发布</button>
			<button type="button" class="btn btn-warning" style="width:180px;height:40px;font-size:20px;background-color:#FF7623;">取消</button>
		</li>
	</ul>
	</form>
</div>

<%@ include file="/JSP/main/bottom.jsp"%>

<c:if test="${loginBasicUser==null}">
	<%@include file="loginModal.jsp"%>
</c:if>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery-confirm/jquery-confirm.min.js"></script>
<script src="plugin/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
<script src="plugin/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
<script src="plugin/jQuery-File-Upload/js/jquery.fileupload.js"></script>
<script src="plugin/bootstrap-datetimepicker/js/moment-with-locales.js"></script>
<script src="plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="plugin/UEditor/ueditor.config.js"></script>
<script src="plugin/UEditor/ueditor.all.js"></script>
<script src="plugin/UEditor/lang/zh-cn/zh-cn.js"></script>

<script src="JS/util/jqConfirmExtend.js"></script>
<script src="JS/main/common/districtCascade.js"></script>
<script src="JS/main/indentRelease.js"></script>
</body>
</html>

