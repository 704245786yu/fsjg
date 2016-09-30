<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="CSS/login.css" rel="stylesheet">

<div class="modal" id="loginModal" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width:500px">
		<div class="modal-content">
			<div class="modal-body" style="padding:0;">
				<%--根据条件判断是否显示关闭按钮，对于发布订单页面就不能显示关闭按钮 --%>
				<c:if test="${param.showCloseBtn==true}">
					<button type="button" class="btn btn-link" style="float:right;" data-dismiss="modal">关闭</button>
				</c:if>
				<div class="form-box" style="margin-top:0px;">
					<h4>用户登录</h4>
					<form class="form-group" action="login/asyLogin" method="post">
						<h4 id="errorMsg" style="color:red;display:none;">用户名或密码错误</h4>
						<div class="form-group form-group-lg">
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-user"></span>
								</span>
								<input type="text" name="userName" placeholder="用户名" class="form-control" id="userName">
							</div>
						</div>
						<br/>
						<div class="form-group form-group-lg">
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-lock"></span>
								</span>
								<input type="password" name="password" placeholder="密码" class="form-control" id="password">
							</div>
						</div>
						<div class="checkbox">
							<label>
								<input type="checkbox"> 记住密码
							</label>
							<div style="float:right;"><a href="login/showSignUp">注册</a></div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<button type="submit" class="btn btn-primary btn-lg">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</button>
							</div>
						</div>
					</form>
				</div>
			</div><!-- modal-body -->
		</div><!-- modal-content -->
	</div><!-- modal-dialog -->
</div><!-- modal -->
<script src="JS/main/loginModal.js"></script>
