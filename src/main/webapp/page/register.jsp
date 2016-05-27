<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
	<head>
	<meta charset="utf-8"/>
	<title>注册页面</title>
	<link type="text/css" href="../css/Common1.css" rel="stylesheet" />
	<link type="text/css" href="../css/register.css" rel="stylesheet" />
	<script type="text/javascript" src="../js/jquery-1.11.3.js" /></script>
	<script type="text/javascript" src="../js/public.js" /></script>
	<script type="text/javascript" src="../js/register.js" /></script>
	
	</head>
  
  <body>
     <jsp:include page="header.jsp"></jsp:include>
     <!----------------------------------主题部分------------------------------>
	<div id="container">
        <div id="container_header">
            <img src="../images/userregist.png" /> 
            <hr style="border:1px red solid;" />
        </div>
    
    <!-----------------主体左边---action=""    onsubmit="return regCheck()"------------------->
    <div id="container_content">
    	<div id="content_register">
    	<form  id="regiterMyform">
    		<label style="color:red;">${sessionScope.registerMsg}</label>
			<c:remove var="registerMsg" scope="session"/>
        	<div class="line">
                <label  class="label" style=" margin-top:14px;width:80px;margin-left:46px;">*用户名:</label>
                <input type="text" id="username" name="username" 
                			style="margin-left:18px;width:256px;height:38px;"/>
                
            	<span class="error error_user_img"  style="display:none;width:20px;height:20px;margin-top:-30px;margin-left:356px;"><img src="../images/reg_error.png"/></span>
            	<span class="succ succ_user_img"  style="display:none;width:20px;height:20px;margin-top:-30px;margin-left:356px;"><img src="../images/reg_succ.png"/></span>
            	
            	<span class="info info_user" style="display:none;width:160px;height:30px;margin-top:-36px;margin-left:400px;">请输入用户名，2~20位，由字母、数字和下划线组成！</span>
				<span class="error error_user" style="display:none;width:160px;height:30px;margin-top:-26px;margin-left:400px;">输入不合法，请重新输入！</span>
				<span class="succ succ_user" style="display:none;width:60px;height:30px;margin-top:-26px;margin-left:420px;">可用</span>
            </div>
            <div class="line">
               <label class="label" style=" margin-top:14px;width:80px;margin-left:20px;">*请设置密码:</label>
               <input type="password" id="userpwd" name="password" 
               					style="margin-left:18px;width:256px;height:38px;"/>
               
               	<span class="error error_pass_img"  style="display:none;width:20px;height:20px;margin-top:-30px;margin-left:356px;"><img src="../images/reg_error.png"/></span>
            	<span class="succ succ_pass_img"  style="display:none;width:20px;height:20px;margin-top:-30px;margin-left:356px;"><img src="../images/reg_succ.png"/></span>
            	
               	<span class="info info_pass" style="display:none;width:200px;height:30px;margin-top:-40px;margin-left:400px;">密码是由6－20位字符组成，建议由字母，数字和符合两种以上组合</span>
               	<span class="error error_pass"  style="display:none;width:160px;height:30px;margin-top:-22px;margin-left:420px;">输入不合法，请重新输入！</span>
				<span class="succ succ_pass"  style="display:none;width:60px;height:30px;margin-top:-22px;margin-left:420px;">可用</span>
            </div>
            <div id="qiangdu" style="width:470px;height:15px;margin-top:6px;">
            	<span class="q4" style="margin-left:82px;width:12px;height:14px; display:inline-block;"></span>
                <span class="q1" style="background:#ccc;width:22px;height:8px; display:inline-block;"></span>
                <span class="q2" style="background:#ccc;width:22px;height:8px; display:inline-block;"></span>
                <span class="q3" style="background:#ccc;width:22px;height:8px; display:inline-block;"></span>
            </div>
            <div class="line" >
             	<label class="label" style="margin-top:14px;width:80px;margin-left:20px;">*请确认密码:</label>
              	<input type="password"   class="sure  sure_pd"  
              						style="margin-left:18px;width:256px;height:38px;"/>
            	
            	<span class="error error_sure_img"  style="display:none;width:20px;height:20px;margin-top:-30px;margin-left:356px;"><img src="../images/reg_error.png"/></span>
            	<span class=" sure suret1" style="display:none;width:160px;height:30px;margin-top:-22px;margin-left:400px;">您两次输入的密码不一致</span>
            </div>
            
            <div class="line" >
             	<label class="label" style="margin-top:14px;width:80px;margin-left:34px;">*手机号码:</label>
              	<input type="text"  id="telphone"  name="phone"  value="15886435708"
              					style="margin-left:18px;width:256px;height:38px;"/>
              	
              	<span class="checktel" style="display:none;width:160px;height:30px;margin-top:-28px;margin-left:400px;">您输入的手机号码格式非法</span>
            </div>
            
            <div class="line">
            	<label class="label" style=" float:left;margin-top:14px;width:80px;margin-left:28px;">*验证邮箱:</label>
                <input type="text" id="email"  name="email" value="675436321@qq.com"
                				style="width:256px;height:38px; margin-left:18px;">
                
                <span class="sureEmail" style="display:none;width:160px;height:30px;margin-top:-28px;margin-left:400px;">您输入的邮箱格式不正确</span>
                    
            </div>
            <div style="margin-top:40px;margin-left:160px;width:260px;height:14px;">
            	<input type="checkbox" id="checkBox"  checked='checked'/>
            	
            	<span>阅读《《<a href="#">李宁官方网站服务协议</a>》》</span>
            </div>
            <!-- <input type="submit" value="用户注册"/> -->
            <div class="line"   style="margin-top:16px; float:left;margin-left:124px;">
        		<input type="button"   id="regsubmit"  value="用户注册" 
        				style="width:200px;height:50px;background:rgb(238,39,55);border:none;font-size:16px;color:white;"/>
            </div>
        </form>
        </div>
        
       <div 	style="width:16px;height:300px;margin-top:60px;
       					background:-moz-linear-gradient(right,#fff,rgb(236,236,236));float:left;margin-left:40px;">
	   </div>
        <!-- --------主体的右边--------------->
        <div id="content_right">
        	<div id="title">
                <p>已 有 李 宁 官 方 网 站 账 户 ？</p>
                <p>请 直 接 登 录</p>
                    <div class="zhuce">
                        <a onClick="" href="login.jsp">会员登录</a>
                    </div>
            </div>
            
            <div id="delu">
                <br/>
                <p>您也可以使用合作网站账号登录</p><br/>
                <img src="../images/zhifubao.png"/>
                <a href="#">支付宝</a>&nbsp;&nbsp;
                <img src="../images/weibo.png"/>
                <a href="#">新浪微博</a>&nbsp;&nbsp;&nbsp;
                <img src="../images/qqlogin.png"/>&nbsp;&nbsp;&nbsp;
                <img src="../images/renren.png"/>
                <a href="#">人人网</a>&nbsp;&nbsp;&nbsp;<br/>
                <img src="../images/zhu.png"/>
                <a href="#">平安万里通</a>&nbsp;&nbsp;
                <img src="../images/yi.png"/>
                <a href="#">网易</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <img src="../images/weixing.png" onClick=""/>
            </div>
        </div>
    </div>
	</div>
     <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
