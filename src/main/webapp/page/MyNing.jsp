<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>会员信息</title>
		    <style type="text/css"></style>
			<link href="css/myNing.css" type="text/css" rel="stylesheet">
			<link href="css/Common.css" rel="stylesheet" type="text/css"></link>
		    <script type="text/javascript" src="js/jquery-1.11.3.js" /></script>
			<script type="text/javascript" src="js/public.js" /></script>
    </head>
  
  <body>
  	<jsp:include page="header.jsp"></jsp:include>
    <div id="member">
    	<div id="menber_top_left"><img src="images/member_center_icon.png"></div>
          <div id="menber_top_right">
           <h3 style="border-bottom:solid;font-size:18px;" id="line" >会员信息</h3>
          </div>
           <div id="member_content">
           		<div id="membber_loginname" class="second">
               		<label class="roew_style">登录名：</label>
                    <label  id="login_name" style="color:#0F0"></label>
               </div>
               <div id="membber_name" class="second">
               		<label class="roew_style">真实姓名：</label>
                    <input type="text" class="rows_style" id="username" >
               </div>
                <div id="membber_sex" class="second">
               	<label class="roew_style">性别：</label>
                   <input class="sex"type="radio"checked="true"value="0"name="sex"style="font-family:微软雅黑;">
                   保密
				<input class="sex" type="radio" value="1" name="sex" style="font-family: 微软雅黑;">男
				<input class="sex" type="radio" value="2" name="sex" style="font-family: 微软雅黑;">女 	
               </div>
               <div id="membber_birth" class="second">
               		<label id="birth" class="roew_style">生日：</label>
                    <select  id="birthYear" class="year" name="year" style="font-family: 微软雅黑;">
                    	<option></option>
                    </select>年
                   <select id="birthMonth" class="year" name="month" style="font-family: 微软雅黑;">
                   </select>月
                   <select id="birthDay" class="year" name="day" style="font-family: 微软雅黑;">
                   </select>日 
               </div>
               <div id="membber_email" class="second">
               		<label class="roew_style">邮箱：</label>
                     <label  id="login_email" style="color:#0F0"></label>
               </div>
               <div id="membber_mobil" class="second">
               		<label class="roew_style">手机号码：</label>
                     <input type="text" class="rows_style" id="mobil" >
               </div>
               <div id="membber_addr" class="second">
               		<label class="roew_style">收货地址：</label>
                     <input type="text" class="rows_style" id="maddr" >
               </div>
               <div id="membber_button" class="second">
               		<input type="button" value="提交" id="mbutton" class="mbutton">
               </div>
            </div>
    </div>
     <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
