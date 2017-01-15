<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
<style>
#ff table tr{
	height:45px;
}
#ff table td{
	vertical-align:top;
}

#ff table td:first + td{
	width:520px;
}

#ff > table select{
	width:180px;
}

/*此处需要使用直接子类，否则会对选择服饰类别模态框中的样式产生影响*/
#ff > table > tbody > tr >td > label{
	width:90px;
	margin-top:7px;
	margin-right:7px;
}
#ff > table > tbody > tr > td > label span{
	color:red;
}
#costumeBtn{
	width:400px;
	overflow:hidden;
	background: url('image/select-btn.png') no-repeat 90%;
}
</style>
<div class="panel">
<div id="editPanel" class="panel" style="display:none;">
	<div class="panel-body">
		<form id="ff" method="post" enctype="multipart/form-data" class="form-inline">
			<input type="hidden" name="id"/>
			<input type="hidden" name="num"/>
			<table>
				<tr>
					<td><label><span>*</span>样品名称</label></td>
					<td>
						<div class="form-group"><input type="text" class="form-control" name="name"></div>
					</td>
				</tr>
				<tr>
					<td><label><span>*</span>关联工厂</label></td>
					<td>
						<input type="hidden" name="enterpriseNum">
						<div class="form-group"><input type="text" class="form-control" data-provide="typeahead" name="enterpriseName" autocomplete="OFF"></div>
					</td>
				</tr>
				<tr>
					<td><label><span>*</span>样品类别</label></td>
					<td>
						<div class="form-group">
							<jsp:include page="/JSP/main/common/costumeCategoryModal.jsp">
								<jsp:param name="limitChkNum" value="1"/>
							</jsp:include>
						</div>
					</td>
				</tr>
				<tr>
					<td><label>样品图片</label></td>
					<td colspan="3">
						<!-- IE9下无法多选 -->
						<input type="hidden" name="smImg">
						<input type="file" name="smPic" accept="image/jpeg,image/png" multiple="multiple" onchange="smImgChange(this,200)">
						<p style="color:grey;">支持jpg、png格式图片,尺寸410*410,最大200kb</p>
						<div style="float:left;display:none;margin-right:10px;">
							<img style="width:150px;height:100px" class="img-thumbnail">
							<div><button type="button" class="btn btn-primary btn-sm" onclick="delImg(this,'smImg')">删除</button></div>
						</div>
					</td>
				</tr>
				<tr>
					<td><label><span>*</span>下单数量(件)</label></td>
					<td>
						<div class="form-group"><input type="text" class="form-control" name="orderAmount"></div>
					</td>
				</tr>
				<tr>
					<td><label><span>*</span>单价</label></td>
					<td>
						<div class="form-group"><input type="text" class="form-control" name="price"></div>
					</td>
				</tr>
				<tr>
					<td><label><span>*</span>销售市场</label></td>
					<td>
						<div class="form-group">
							<label class="radio-inline"><input type="radio" name="saleMarket" value="0">内销</label>
							<label class="radio-inline"><input type="radio" name="saleMarket" value="1">外销</label>
						</div>
					</td>
				</tr>
				<tr>
					<td><label><span>*</span>售后保障</label></td>
					<td>
						<div class="form-group">
							<label class="checkbox-inline"><input type="checkbox" name="support" value="0">支持该款</label>
							<label class="checkbox-inline"><input type="checkbox" name="support" value="1">贴牌生产</label>
							<label class="checkbox-inline"><input type="checkbox" name="support" value="2">支持看样</label>
						</div>
					</td>
				</tr>
				<tr style="height:100px">
					<td><label>加工说明</label></td>
					<td colspan="3"><div class="form-group"><textarea class="form-control" rows="3" name="processDesc" style="width:1011px;"></textarea></div></td>
				</tr>
				<tr>
					<td><label>样品详图</label></td>
					<td colspan="3">
						<!-- IE9下无法多选 -->
						<input type="hidden" name="detailImg">
						<input type="file" name="detailPic" accept="image/jpeg,image/png" multiple="multiple" onchange="detailImgChange(this,200)">
						<p style="color:grey;">支持jpg、png格式图片,尺寸790*530,最大200kb</p>
						<div style="float:left;display:none;margin-right:10px;">
							<img style="width:150px;height:100px" class="img-thumbnail">
							<div><button type="button" class="btn btn-primary btn-sm" onclick="delImg(this,'detailImg')">删除</button></div>
						</div>
					</td>
				</tr>
			</table>
			<div style="margin-top:20px;text-align:right;padding-right:100px;">
				<button type="submit" name="save" class="btn btn-primary" style="width:80px;margin-right:10px;">保存</button>
				<button type="button" class="btn btn-default" onclick="cancel()" style="width:80px;">取消</button>
			</div>
		</form>
	</div><!-- panel-body -->
</div><!-- panel -->

</div>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery.form.min.js"></script>
<script src="plugin/jquery.formautofill.min.js"></script>
<script src="plugin/bootstrap3-typeahead.min.js"></script>
<script src="JS/backstage/costumeSample/editCostumeSample.js"></script>