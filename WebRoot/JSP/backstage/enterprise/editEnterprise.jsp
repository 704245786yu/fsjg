<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
<style>
#ff table tr{
	height:45px;
}
#ff table td{
	vertical-align:top;
}

#ff table td .form-group{
	width:520px;
}

#ff table select{
	width:180px;
}

#ff table td label{
	width:90px;
	margin-top:7px;
	margin-right:7px;
}
#ff table td label span{
	color:red;
}
#costumeBtn{
	width:400px;
	overflow:hidden;
	background: url('image/select-btn.png') no-repeat 90%;
}
</style>
<div class="panel">
<div id="editPanel" class="panel panel-primary" style="display:none;">
	<div class="panel-body">
		<form id="ff" method="post" enctype="multipart/form-data" class="form-inline">
			<input type="hidden" name="basicUser.id"/>
			<input type="hidden" name="id"/>
			<table id="districtContainer">
				<tr>
					<td><label>工厂编号</label></td>
					<td>
						<div class="form-group"><input type="text" class="form-control" name="number" readonly value="111"></div>
					</td>
					<td><label><span>*</span>用户名 </label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="basicUser.userName"></div></td>
				</tr>
				<tr>
					<td><label><span>*</span>工厂名称</label></td>
					<td>
						<div class="form-group"><input type="text" class="form-control" name="enterpriseName"></div>
					</td>
					<td><label><span>*</span>联系人 </label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="linkman"></div></td>
				</tr>
				<tr>
					<td><label><span>*</span>省</label></td>
					<td>
						<div class="form-group"><select class="form-control" id="province" name="province"></select></div>
					</td>
					<td><label><span>*</span>手机号码 </label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="basicUser.telephone"></div></td>
				</tr>
				<tr>
					<td><label><span>*</span>市</label></td>
					<td>
						<div class="form-group"><select class="form-control" id="city" name="city"></select></div>
					</td>
					<td><label>电子邮箱 </label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="email"></div></td>
				</tr>
				<tr>
					<td><label><span>*</span>区县</label></td>
					<td>
						<div class="form-group"><select class="form-control" id="county" name="county"></select></div>
					</td>
					<td><label>QQ</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="qq"></div></td>
				</tr>
				<tr>
					<td><label>镇/乡/街道</label></td>
					<td>
						<div class="form-group"><select class="form-control" id="town" name="town"></select></div>
					</td>
					<td><label>固定电话</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="fixPhone"></div></td>
				</tr>
				<tr>
					<td><label><span>*</span>详细地址</label></td>
					<td>
						<div class="form-group"><input type="text" class="form-control" name="detailAddr" style="width:400px;"></div>
					</td>
					<td><label>微信</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="wechat"></div></td>
				</tr>
				<tr>
					<td><label>最近档期</label></td>
					<td>
						<div class="form-group"><input type="text" class="form-control" name="schedule" style="width:400px;"></div>
					</td>
					<td><label>邮政编码</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="postalCode"></div></td>
				</tr>
				<tr>
					<td><label><span>*</span>行业分类</label></td>
					<td>
						<div class="form-group">
							<label class="checkbox-inline"><input type="checkbox" name="trade" value="option1">服装</label>
							<label class="checkbox-inline"><input type="checkbox" name="trade" value="option2">服饰</label>
							<label class="checkbox-inline"><input type="checkbox" name="trade" value="option3">家纺</label>
							<label class="checkbox-inline"><input type="checkbox" name="trade" value="option3">面料</label>
						</div>
					</td>
					<td><label>组织机构代码</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="orgCode"></div></td>
				</tr>
				<tr>
					<td><label><span>*</span>加工类型</label></td>
					<td>
						<div class="form-group">
							<c:forEach var="constantDict" items="${processTypes}">
								<label class="checkbox-inline"><input type="checkbox" name="processType" value="${constantDict.constantValue}">${constantDict.constantName}</label>
							</c:forEach>
						</div>
					</td>
				</tr>
				<tr>
					<td><label><span>*</span>主营产品</label></td>
					<td>
						<div class="form-group">
							<button id="costumeBtn" type="button" class="btn btn-default" data-toggle="modal" data-target="#costumeCategoryModal">选择产品类别</button>
							<!-- 选择服饰类别模态框 -->
							<div class="modal fade" id="costumeCategoryModal" tabindex="-1">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
											<h5 class="modal-title" id="myModalLabel">选择服饰类别</h5>
										</div>
										<div class="modal-body">
											<ul class="nav nav-tabs">
												<li class="active"><a href="#costume_1" data-toggle="tab">服装</a></li>
												<li><a href="#costume_2" data-toggle="tab">服饰</a></li>
												<li><a href="#costume_3" data-toggle="tab">家纺</a></li>
												<li><a href="#costume_4" data-toggle="tab">纺织消费品</a></li>
												<li><a href="#costume_5" data-toggle="tab">面料/皮革/纱线</a></li>
												<li><a href="#costume_6" data-toggle="tab">纺织辅料</a></li>
											</ul>
											<div class="tab-content">
												<div class="tab-pane active" id="costume_1">
													
												</div>
												<div class="tab-pane" id="costume_2"></div>
												<div class="tab-pane" id="costume_3"></div>
												<div class="tab-pane" id="costume_4"></div>
												<div class="tab-pane" id="costume_5"></div>
												<div class="tab-pane" id="costume_6"></div>
											</div>
											
											<table id="template" class="table" style="display:none;">
												<tr style="display:none;">
													<!-- 二级类目 -->
													<td style="width:100px;vertical-align:top;display:none;">
														<label style="cursor:pointer;">
															<input type="checkbox" name="costumeCode" onchange="checkAllSubBox(this)" readonly="readonly"> <span style="font-weight:normal;"></span>
														</label>
													</td>
													<!-- 三级类目 -->
													<td>
														<label style="display:none;width:140px;cursor:pointer;margin-right:10px;float:left;">
															<input type="checkbox" name="costumeCode" onchange="threeLevelCheck(this)"> <span style="font-weight:normal;"></span>
														</label>
													</td>
												</tr>
											</table>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="checkCostume()">确定</button>
											<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</td>
					<td><label>高速车工人数</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="highSpeedStaffNumber"></div></td>
				</tr>
				<tr>
					<td><label><span>*</span>销售市场</label></td>
					<td>
						<div class="form-group">
							<label class="radio-inline"><input type="radio" name="saleMarket" value="0">内销</label>
							<label class="radio-inline"><input type="radio" name="saleMarket" value="1">外销</label>
						</div>
					</td>
					<td><label>其他加工人数</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="otherStaffNumber"></div></td>
				</tr>
				<tr>
					<td><label><span>*</span>工人数量</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="staffNumber"></div></td>
					<td><label>经营年限</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="enterpriseAge"></div></td>
				</tr>
				<tr>
					<td><label>产值产量</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="yield"></div></td>
					<td><label>企业网址</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="websiteUrl"></div></td>
				</tr>
				<tr>
					<td><label>合作客户</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="cooperator" style="width:400px;"></div></td>
					<td><label>生产设备</label></td>
					<td><div class="form-group"><input type="text" class="form-control" name="equipment" style="width:400px;"></div></td>
				</tr>
				<tr style="height:100px">
					<td><label>工厂介绍</label></td>
					<td colspan="3"><div class="form-group"><textarea class="form-control" rows="3" name="description" style="width:1011px;"></textarea></div></td>
				</tr>
				<tr style="height:190px;">
					<td><label>工厂logo</label></td>
					<td>
						<input type="file" name="logo" accept="image/jpeg,image/png" onchange="imgChange(this,50)"><p style="color:grey;">支持jpg、png格式图片,尺寸90*90</p>
						<img src="image/enterpriseLogo/default_logo.png">
						<div><button type="button" class="btn btn-primary btn-sm">删除</button></div>
					</td>
					<td><label>营业执照</label></td>
					<td>
						<input type="file" name="businessLicenseImg" accept="image/jpeg,image/png"><p style="color:grey;">支持jpg、png格式图片</p>
						<img src="image/enterpriseLogo/default_logo.png">
						<div><button type="button" class="btn btn-primary btn-sm">删除</button></div>
					</td>
				</tr>
				<tr>
					<td><label>工厂图片</label></td>
					<td colspan="3">
						<!-- IE9下无法多选 -->
						<input type="file" name="enterpriseImg" accept="image/jpeg,image/png" multiple="multiple" onchange="imgChange(this,200)"><p style="color:grey;">支持jpg、png格式图片,尺寸400*240</p>
						<div style="float:left;">
							<img src="image/enterpriseLogo/default_logo.png">
							<div><button type="button" class="btn btn-primary btn-sm">删除</button></div>
						</div>
					</td>
				</tr>
			</table>
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
<script src="plugin/jquery.form.min.js"></script>
<script src="plugin/jquery.formautofill.min.js"></script>
<script src="JS/util/districtCascade.js"></script>
<script src="JS/backstage/enterprise/editEnterprise.js"></script>