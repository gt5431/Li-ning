<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>订单页面</title>
<link type="text/css" href="../css/Common1.css" rel="stylesheet" />
<link type="text/css" href="../css/ok.css" rel="stylesheet" />
<!-- <link type="text/css" href="../css/comm_header.css" rel="stylesheet" /> -->

<script type="text/javascript" src="../js/jquery-1.11.3.js" /></script>
<script type="text/javascript" src="../js/public.js" /></script>
<script type="text/javascript" src="../js/login.js" /></script>
<script type="text/javascript" src="../js/global.js" /></script>
<script type="text/javascript">
	      var people ={
	             "username":"mary",
	             "age":"20",
	             "info":{"tel":"1234566","celltelphone":788666},
	             "address":[
	                     {"city":"beijing","code":"1000022"},
	                     {"city":"shanghai","code":"2210444"}
	              ]
	        };
</script>

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<form action="user_list.action" method="POST">
	<div id="ok_content">
		<div class="clearfix" style="font-family: 微软雅黑;">
			<div id="finish" style="font-family: 微软雅黑;"></div>
		</div>
		<div   style="text-align: center; border: 1px solid rgb(243, 243, 243); width: 986px; height: 500px;">
			<div id="paymentinfo">
				<h2>
					<img /> 订单已提交成功，请尽快付款！
				</h2>
				<p>
					您的订单号： 
					<span class="price f14" style="font-family: 微软雅黑;">ABC123XX-
										${sessionScope.orderform.pid}</span>
				</p>
				<p>
					订单金额： <span class="price f14" style="font-family: 微软雅黑;">
						${sessionScope.orderform.buyprice} </span>元
				</p>
			</div>
			<div id="cent"></div>
			<div id="text1">在线支付成功后，请勿关闭支付成功页面，等待返回到商城</div>
			<div id="button">
				<input class="payment_submit sprite" type="submit"
					onclick="pay()" value="确认无误，立即支付"
					style="font-family: 微软雅黑;" />
			</div>
		</div>
	</div>
	</form>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
