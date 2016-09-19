<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--服饰类型选择模态框 --%>
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
	<span name="data" style="display:none;">${applicationScope.costumeCategoryList}</span>
</div>

<script src="JS/util/treeUtil.js"></script>
<script src="JS/main/common/costumeCategoryModal.js"></script>