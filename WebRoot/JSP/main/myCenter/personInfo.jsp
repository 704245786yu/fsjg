<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<form class="form-inline" role="form" action="basicUser/editPersonInfo" method="post" accept-charset="utf-8">
	<div class="title" style="border-bottom:1px solid #cccccc">
		<p style="line-height:40px">
			<span style="font-size:20px;font-weight:bold;padding-left:20px;">详细信息</span><span
				style="padding-left:700px">&nbsp;</span>
				<input type="hidden" value=${userInfo.basicUser.id } name="id" class="form-control" id="myid">
			<button type="submit" class="btn btn-primary ">保存修改</button>
			<button type="submit" class="btn btn-primary">重&nbsp;&nbsp;&nbsp;&nbsp;置</button>
		</p>
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
					<td><label for="name">微信号码：</label><input type="text"
						name=wechat value=${userInfo.wechat }
						class="form-control" id="wechat"></td>
					<td><label for="name">所在地区：</label>
					<input type="text"
						name=district value=<c:forEach var="districtName" items="${districtNames}">
					<c:out value="${districtName}" />
				</c:forEach>
						class="form-control" id="district"></td>
				</tr>
				<tr>
					<td><label for="name">邮政编码：</label><input type="text"
						name=postalCode value=${userInfo.postalCode }
						class="form-control" id=postalCode></td>
					<td><label for="name">详细地址：</label>
					<input type="text"
						name=detailAddr value=${userInfo.detailAddr }
						class="form-control" id=detailAddr></td>							
				</tr>						
			</table>
		</div>
</form>