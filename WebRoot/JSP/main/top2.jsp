<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="CSS/top.css" rel="stylesheet">
<div id="topBanner" class="top">
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
			<a href="basicUser/showMyCenter">
				<c:choose>
					<c:when test="${loginBasicUser.userName!=null}">
						${loginBasicUser.userName}
					</c:when>
					<c:when test="${loginBasicUser.telephone!=null}">
						${loginBasicUser.telephone}
					</c:when>
				</c:choose>
			</a>
		</c:if>
		<c:if test="${loginBasicUser != null}">
			<span><a href="login/logout">退出</a></span>
		</c:if>
		<span><span class="glyphicon glyphicon-earphone"></span> <b>400-168-1978</b></span>
	</div>
</div>

<!-- banner -->
<div class="banner">
	<div  style="width:1220px">
		<div style="width:370px;float:left;">
			<a href="">
				<img src="image/logo.png">
			</a>
		</div>
		<div class="searchBox" style="float:left">
			<!-- 判断搜索框tab的选中状态 -->
	    	<input type="hidden" name="tabIndex" value="${tabIndex}">
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
		            <input type="text" id="s1" name="indentKeyword" class="search-input" value="${indnetKeyword}">
		            <input type="text" id="s2" name="enterpriseKeyword" class="search-input" value="${enterpriseKeyword}">
		            <input type="text" id="s3" name="sampleKeyword" class="search-input" value="${sampleKeyword}">
		            <button id="submit" class="btn-search" value="搜索">搜索</button>
		        </div>
			</form>
			</div>
			<div class="col-md-1" style="padding-top:26px;">
				<a href="indent/showRelease" class="btn btn-default"><span class="glyphicon glyphicon-list-alt"></span> 发布订单</a>
		</div>
	</div>
</div>

<!-- 由于上面的nav样式会影响其他页面的布局，通过此div进行影响的隔离 -->
<!-- <div style="height:5px"></div> -->

<script src="JS/main/top2.js"></script>