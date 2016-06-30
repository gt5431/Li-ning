<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'main.jsp' st </title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=basePath %>/easyui/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>/easyui/css/icon.css" />
<script type="text/javascript" src="<%=basePath %>/easyui/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/easyui/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/main.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/showpic.js"></script>
<!-- 编辑器 -->
<script type="text/javascript" src="<%=basePath %>/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="<%=basePath %>/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/ajaxfileupload.js"></script>
<!-- echarts -->
<script type="text/javascript" src="<%=basePath %>/echarts-2.2.7/build/dist/echarts-all.js"></script>
<script type="text/javascript">
	function Systime(clock){
		var now=new Date();
		var year=now.getFullYear();
		var month=now.getMonth();
		var date=now.getDate();
		month=month+1;
		var time=year+"-"+month+"-"+date;
		clock.innerHTML=time;
	}
	/* $(function(){
		window.setInterval("Systime(clock)",1000);
		$("#oldpwd").blur(function(){
			var pwd=$("#truepwd").val();
			var inputpwd=$("#oldpwd").val();
			$.post("manager.do",{op:"checkInfo",pwd:pwd,inputpwd:inputpwd},function(data){
				data=parseInt($.trim(data));
				if(data==1){
					$("#check1").css('display','block');
				}else if(data==0){
					$("#check1").css('display','none');
				}
			});
			
		});
		$("#newpwd").blur(function(){
			var newpwd=$("#newpwd").val();
			var pwd=$("#truepwd").val();
			$.post("manager.do",{op:"checkInfo",pwd:pwd,inputpwd:newpwd},function(data){
				data=parseInt($.trim(data));
				if(data==0){
					$("#check2").css('display','block');
				}else if(data==1){
					$("#check2").css('display','none');
				}
			});
			
		});
		
	});
	function opendialog(){
		$("#changepwd").dialog("open");
	}
	function updateuser_pwd(){
		var newpwd=$("#newpwd").val();
		var mid=$("#mid").val();
		
		$.ajax({
			method:'POST',
			url:"manager.do",
			data:{
				op:'updatepwd',
				'mid':mid,
				'mpwd':newpwd
			},
			dataType:"JSON",
			success:function(data){
				if(data==1)	{
					alert("修改成功！");
					$("#newpwd").val("");
					$("#oldpwd").val("");
					$("#changepwd").dialog("close");
				}else{
					alert("修改失败！");
				}	
			}
		});
	}
	 */
</script>


</head>

<body class="easyui-layout">

	<div data-options="region:'north',border:false" id="newslogo"
		style="height:120px;">
		<ul>
			<p><a id="showname">欢迎${sessionScope.manager.mname}登录！！！！！！</a>
				<a id="clock" style="float:right;font-size:18px;color:red;"></a>
				<input id="truepwd"  type="hidden" value="">
				<input id="mid" type="hidden" value="${sessionScope.manager.mid}">
			</p>
			
			<li style="list-style:none"><a class="easyui-linkbutton" href="javascript:opendialog()" style="font-size:15px">修改密码</a></li>
			<li style="list-style:none"><a class="easyui-linkbutton" 
				href="backlogin/back_login.jsp" style="font-size:15px;margin-top:5px">安全退出</a></li>
			
		</ul>
	</div>
	<div data-options="region:'west',title:'导航'" style="width:180px;">
		<ul class="easyui-tree" id="menu_tree">
			<li><span>菜单</span>
				<ul>
					<li data-options="state:'closed'"><span>管理员员信息管理</span>
						<ul>
							<li id="findManagers_content">查看所有管理员</li>

						</ul></li>
					<li data-options="state:'closed'"><span>会员信息管理</span>
						<ul>
							<li id="findUsers_content">查看所有会员</li>

						</ul></li>
					<li data-options="state:'closed'"><span>商品类型管理</span>
						<ul>
							<li id="selectType_content"><span>查看所有商品类型</span>
							</li>
						</ul></li>
					<li data-options="state:'closed'"><span>商品信息管理</span>
						<ul>
							<li id="findProducts_content"><span>查看所有商品</span></li>
						</ul>
					</li>
					<li data-options="state:'closed'"><span>订单信息管理</span>
						<ul>
							<li id="findOrderform_content"><span>查看所有订单</span></li>
						</ul>
					</li>
					<li data-options="state:'closed'"><span>地址信息管理</span>
						<ul>
							<li id="findPlace_content"><span>查看所有地址</span></li>
						</ul>
					</li>
					<li data-options="state:'closed'"><span>销售统计</span>
						<ul>
							<li id="product_sale"><span>商品销售情况</span></li>
						</ul>
					</li>
				</ul></li>
		</ul>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'帮助'"
		style="width:100px;">
		<img src="../images/weixin.jpg" width="90px" height="90px" />
	</div>
	<div data-options="region:'south'" id="copyright"
		style="height:50px;background-color:#4D89BB;margin:auto;">
		<p>
			<a href="http://www.hyycinfo.com">20班第二组</a>
		</p>
	</div>
	<div data-options="region:'center'">
		<div id="content_index" class="easyui-tabs" data-options="fit:true"
			title="内容"></div>
	</div>
	<div id="changepwd" class="easyui-dialog" title="修改密码" 
	data-options="resizable:true,modal:true,closed:true" style="width:400px;height:200px;">
		<div>
			<p>
			<label>请输入旧密码：</label><input type="password" id="oldpwd" class="easyui-validatebox" data-options="required:true"/>
			<label id="check1" style="display:none">密码错误！！！</label>
			</p>
		</div>
		<div style="margin-top:20px">
			<label>请输入新密码：</label><input type="password" id="newpwd" class="easyui-validatebox" data-options="required:true"/>
			<label id="check2" style="display:none">新密码与旧密码重复！！！</label>
		</div>
			<a class="easyui-linkbutton" style="margin-top:20px" id="change" href="javascript:updateuser_pwd()">确定</a>
	</div>
	

</body>
</html>
