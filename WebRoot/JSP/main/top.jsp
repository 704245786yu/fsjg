<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="CSS/top.css" rel="stylesheet">
<div class="top">
	欢迎来到服饰加工网!
	<span class="signIn">
		<c:if test="${loginBasicUser == null}">
			<a href="login.jsp">[登录]</a>
			<a href="JSP/signUp.jsp">[免费注册]</a>
		</c:if>
	</span>
	<div>
		<c:if test="${loginBasicUser == null}">
			<span style="color:#00AEE3">欢迎你,游客</span>
		</c:if>
		<c:if test="${loginBasicUser != null}">
			<a href="basicUser/showMineInfo">我的服饰网</a>
		</c:if>
		<span>我的关注</span>
		<span><span class="glyphicon glyphicon-earphone"></span> <b>400-168-1978</b></span>
	</div>
</div>

<!-- banner -->
<div class="banner">
	<div class="row">
		<div class="col-md-3" style="width:275px;">
			<img src="image/logo.png">
		</div>
		<div class="col-md-6 col-md-offset-1 searchBox">
			<form id="globalSearchForm" class="jq22-search-form">
				<div id="search-bd" class="search-bd">
				    <ul>
				        <li class="selected">订单</li>
				        <li>工厂</li>
				        <li>样品</li>
				    </ul>
				</div>
		        <div id="search-hd" class="search-hd">
		            <div class="search-bg"></div>
		            <input type="text" id="s1" name="keyword" class="search-input">
		            <input type="text" id="s2" name="enterpriseKeyword" class="search-input">
		            <input type="text" id="s3" name="keyword" class="search-input">
		            <!-- <span class="s1 pholder">食品酒水半价抢疯</span>
		            <span class="s2 pholder">搜商家名称</span> -->
		            <button id="submit" class="btn-search" value="搜索">搜索</button>
		        </div>
			</form>
			</div>
			<div class="col-md-1" style="padding-top:26px;">
				<button type="button" class="btn btn-default"><span class="glyphicon glyphicon-list-alt"></span> 发布订单</button>
			<!-- <ul class="nav nav-pills">
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
			</div> -->
		</div>
	</div>
</div>

<nav class="topNav">
	<div style="width:1190px;margin:0 auto;">
		<ul class="nav navbar-nav" >
			<li name="li-home"><a href="home.jsp">首页</a></li>
			<li name="li-enterprise"><a href="JSP/main/enterprise.jsp">加工工厂</a></li>
			<li><a href="#">加工订单</a></li>
			<li><a href="#">快产专家</a></li>
			<li><a href="#">服饰样品</a></li>
		</ul>
	</div>
</nav>

<!-- 由于上面的nav样式会影响其他页面的布局，通过此div进行影响的隔离 -->
<div style="height:5px"></div>

<script src="JS/main/top.js"></script>