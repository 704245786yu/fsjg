<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form class="form-inline" action="basicUser/editPersonInfo" method="post">
	<input type="hidden" name="id" value="${userInfo.basicUser.id}">
	<div style="border-bottom:1px solid #cccccc;line-height:49px;">
		<strong style="font-size:18px;padding-left:20px;">详细信息</strong>
		<div style="float:right;margin-right:20px;">
			<button type="submit" class="btn btn-primary">保存修改</button>
			<button type="reset" class="btn btn-primary">重&nbsp;&nbsp;&nbsp;&nbsp;置</button>
		</div>
	</div>
	<div class="form-group" style="margin-left:50px;margin-top:10px">
		<table width=650 height=300>						
			<tr>
				<td>
					<label for="name">QQ号码：</label>
					<input type="text" name="qq" value="${userInfo.qq}" class="form-control">
				</td>
				<td>
					<label for="name">固定电话：</label>
					<input type="text" name="fixPhone" value="${userInfo.fixPhone}"	class="form-control">
				</td>
			</tr>
			<tr>
				<td>
					<label for="name">微信号码：</label>
					<input type="text" name="wechat" value="${userInfo.wechat}"	class="form-control">
				</td>
				<td>
					<label for="name">所在地区：</label>
					<button id="districtBtn" type="button" class="btn btn-default" style="min-width:150px;" data-toggle="modal" data-target="#districtModal">
						<c:forEach var="districtName" items="${districtNames}">
							${districtName}
						</c:forEach>
					</button>
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
											<select class="form-control" id="province" name="province"></select>
										</div>
										<div class="col-sm-3">
											<select class="form-control" id="city" name="city"></select>
										</div>
										<div class="col-sm-3">
											<select class="form-control" id="county" name="county"></select>
										</div>
										<div class="col-sm-3">
											<select class="form-control" id="town" name="town"></select>
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
				<td>
					<label for="name">邮政编码：</label>
					<input type="text" name="postalCode" value="${userInfo.postalCode}" class="form-control">
				</td>
				<td>
					<label for="name">详细地址：</label>
					<input type="text" name="detailAddr" value="${userInfo.detailAddr}" class="form-control">
				</td>							
			</tr>						
		</table>
	</div>
</form>

<script src="JS/util/districtCascade.js"></script>
<script type="text/javascript">
function checkDistrict(){
	var $select = $('#districtContainer :selected');
	var str = '';
	for(var i=0; i<$select.length; i++){
		if($($select[i]).val() != '')
			str += $($select[i]).text()+' ';
	}
	$('#districtBtn').html(str);
	query();
}
</script>