<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>back_login.html</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../easyui/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="../easyui/css/icon.css" />
<link rel="stylesheet" type="text/css" href="../css/main.css" />

<script type="text/javascript" src="../easyui/js/jquery.min.js"></script>
<script type="text/javascript" src="../easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../easyui/js/easyui-lang-zh_CN.js"></script>
<style type="text/css">
table tr {
	padding-top: 20px;
}
</style>

</head>

<body>

	<div style="position:absolute;top:100px;margin-left:500px;">
		<div class="easyui-panel" title="用户登录" data-options=""
			style="width:400px;">
			<form id="login_form" method="post" action="manager_login.action">
				<table align="center">
					<tr>
						<td>账号：</td>
						<td><input id="zhanghao" class="easyui-validatebox"
							data-options="required:true" type="text" name="mname">
						</td>
					</tr>
					<tr>
						<td>密码：</td>
						<td><input  id="mima" class="easyui-validatebox"
							data-options="required:true" type="password" name="mpwd">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input class="easyui-linkbutton login" type="submit" value="登录"/>
						
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</body>
</html>
