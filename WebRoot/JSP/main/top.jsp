<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="CSS/top.css" rel="stylesheet">
<div class="top">
	欢迎来到服饰加工网!
	<span class="signIn">
		<a href="login.jsp">[登录]</a>
		<a href="JSP/signUp.jsp">[免费注册]</a>
	</span>
	<div>
		<span style="color:#00AEE3">欢迎你，游客</span>
		<span>我的关注</span>
		<span><span class="glyphicon glyphicon-earphone"></span> <b>400-888888</b></span>
	</div>
</div>

<!-- banner -->
<div class="row banner">
	<div class="col-md-3">
		<img src="image/logo.png">
	</div>
	<div class="col-md-8 col-md-offset-1 searchBox">
		<ul class="nav nav-pills">
		  <li class="active"><a href="#">订单</a></li>
		  <li><a href="#">工厂</a></li>
		  <li><a href="#">样品</a></li>
		</ul>
		<div class="row">
			<div class="col-md-8">
				<div class="input-group">
					<input type="text" class="form-control">
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button" style="width:100px">搜索</button>
					</span>
				</div>
			</div>
			<div class="col-md-3">
				<button type="button" class="btn btn-default"><span class="glyphicon glyphicon-list-alt"></span> 发布订单</button>
			</div>
		</div>
	</div>
</div>

<nav class="topNav">
	<ul class="nav navbar-nav" >
		<li name="li-home"><a href="home.jsp">首页</a></li>
		<li name="li-enterprise"><a href="JSP/main/enterprise.jsp">加工工厂</a></li>
		<li><a href="#">加工订单</a></li>
		<li><a href="#">快产专家</a></li>
		<li><a href="#">服饰样品</a></li>
	</ul>
</nav>

<script src="JS/main/top.js"></script>