<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>登录页面</title>
<link type="text/css" href="../css/Common1.css" rel="stylesheet" />
<link type="text/css" href="../css/login.css" rel="stylesheet" />
<link type="text/css" href="../css/comm_header.css" rel="stylesheet" />

<script type="text/javascript" src="../js/jquery-1.11.3.js" /></script>
<script type="text/javascript" src="../js/public.js" /></script>
<script type="text/javascript" src="../js/login.js" /></script>
<script type="text/javascript" src="../js/global.js" /></script>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="container">
		<div id="container_header">
			<img src="../images/userlogin.png" />
			<hr style="border: 1px red solid;" />
		</div>
		<div id="container_content">
			<div id="content_login">
				<form id="loginMyform">
					<label style="color:red;">${sessionScope.loginMsg}</label>
					<c:remove var="loginMsg" scope="session"/>
					<div class="line">
						<label style="font-weight: bold; margin-top: 14px;">用 户 名:</label>
						<input type="text" id="username" name="username" value="" placeholder="用户名/邮箱"
							style="padding-left: 16px; margin-top: 28px; width: 256px; height: 38px;font-size:14px;" />
						<span id="userName_error" class="note">请输入会员名称</span>
					</div>
					<div class="line">
						<label style="font-weight: bold; margin-top: 14px;">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label> 
						<input type="password" id="userpwd"	name="password" value="" placeholder="密码"
							style="padding-left: 14px; margin-top: 28px; width: 256px; height: 38px;font-size:14px;" />
						<span id="pwd_error" class="note">用户名与密码不匹配</span> <span
							id="pwd_error1" class="note">请输入密码</span>

					</div>
					<span style="margin-left: 60px;"><a href="ForgetPwd.jsp"
						style="text-decoration: none;color:blue;">忘记密码？</a></span>
					<div class="line" style="float:left;">
						<label style="font-weight: bold; margin-top: 14px;">验 证 码:</label>
						<input type="text" id="checkcode" name="checkcode" placeholder="验证码"
							style="padding-left: 16px;width: 100px; height: 38px;font-size:14px;" />
						 <img onclick="recode()"
							class="code" title="看不清楚，点击获得新图片"
							style="cursor: pointer; height: 39px;margin-left:30px;position:relative;top:12px;"
							src="../images/yanzhma.jpg" />
						<span id="yzm_error" class="note">输入的验证码错误</span> <span
							id="yzm_error1" class="note">请输入验证码</span>
					</div>
					<div class="line"
						style="margin-top: 20px; float: left; margin-left:49px;">
						<input type="button" id="Login"  value="会员登录"
							style="background: rgb(238, 39, 55); width: 272px; height: 50px; border: none; font-size: 16px; color: white;"></input>
					</div>
				</form>
			</div>
			<div
				style="width: 10px; height: 260px; margin-top: 26px; background: -moz-linear-gradient(right, #fff, rgb(236, 236, 236)); /*火狐*/ float: left; margin-left: 204px;">
			</div>
			<!-- --------主体的右边--------------->
			<div id="content_right">
				<div id="title">
					<p>还 没 有 李 宁 官 方 网 站 账 户 ？</p>
					<p>快 来 注 册 吧</p>
					<div class="zhuce">
						<a onClick="" href="register.jsp">立即注册</a>
					</div>
				</div>
				<div id="delu">
					<br />
					<p>您也可以使用合作网站账号登录</p>
					<br /> <img src="../images/zhifubao.png" /> <a href="#">支付宝</a>&nbsp;&nbsp;
					<img src="../images/weibo.png" /> <a href="#">新浪微博</a>&nbsp;&nbsp;&nbsp;
					<img src="../images/qqlogin.png" />&nbsp;&nbsp;&nbsp; <img
						src="../images/renren.png" /> <a href="#">人人网</a>&nbsp;&nbsp;&nbsp;<br />
					<img src="../images/zhu.png" /> <a href="#">平安万里通</a>&nbsp;&nbsp;
					<img src="../images/yi.png" /> <a href="#">网易</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<img src="../images/weixing.png" onClick="" />
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
