<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ForgetPwd.jsp' starting page</title>
    <link type="text/css" href="css/Common1.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="css/ForgetPwd.css" />
	<script type="text/javascript" src="js/jquery-1.11.3.js" /></script>
	<script type="text/javascript" src="js/public.js" /></script>
	<script type="text/javascript" src="js/ForgetPwd.js"></script>
  </head>
  
  <body>
       <jsp:include page="header.jsp"></jsp:include>
    <div id="ForgetPwd_main">
    	<div id="ForgetPwd_header">
        	<img src="images/password_reset_title.jpg">
        </div>
        <div id="ForgetPwd_center_left">
  			<div id="ForgetPwd_username" class="ForgetPwd_username">用户名：</div>
           	<div id="first">
            	<input type="text" name="text" class="text" id="F_userName">
                <span class="span_name" id="span_name">请输入用户名!</span>
            </div>
            <div class="ForgetPwd_useryzm">验证码：</div>
            <div id="second">
            	<input type="text" name="text" class="text_yzm" id="F_userYzm">
                <img src="images/yzm.php.png" class="img">
                <span class="span_yzm" id="span_yzm">请输入验证码！</span>
            </div>
            <div id="button"><span class="F_mima">将密码发给我</span></div>
        </div>
        
        <div id="ForgetPwd_center_center">
        	<img src="images/reg_sp.gif" class="ForgetPwd_center">
        </div>
        
        <div id="ForgetPwd_center_right">
        	<div id="ForgetPwd_center_right_top">
            	还没有李宁官方网站账户？
                <br/>
                快来注册吧
         	</div>
            <div id="ForgetPwd_center_right_center">
            	<span class="F_zhuce"><a href="register.jsp" style="text-decoration:none; color:white;">立即注册</a></span>
            </div>
        </div>
        
        <div id="ForgetPwd_center_right_button">
        	<div class="Introduction">您也可以使用合作网站账号登陆</div>
            <div class="F_Link">
            	<a href="###" style="text-decoration:none;font-size:12px;" class="F_style"><img src="images/F_zfb.jpg">支付宝</a>
                <a href="###" style="text-decoration:none;font-size:12px;" class="F_style"><img src="images/F_wb.jpg">新浪微博</a>
                <a href="###" style="text-decoration:none;font-size:12px;" class="F_style"><img src="images/F_qq.jpg"></a>
                <a href="###" style="text-decoration:none;font-size:12px;" class="F_style"><img src="images/F_rr.jpg">人人网</a>
                <a href="###" style="text-decoration:none;font-size:12px;" class="F_style"><img src="images/F_pawl.jpg">平安万里通</a>
                <a href="###" style="text-decoration:none;font-size:12px;" class="F_style"><img src="images/F_wy.jpg">网易</a>
                <a href="###" style="text-decoration:none;font-size:12px;" class="F_style"><img src="images/F_wx.jpg"></a>

            </div>
        </div>
        
    </div>
         <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
