<%@page import="com.yc.lining.entity.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>订单页面</title>
<link type="text/css" href="../css/Common1.css" rel="stylesheet" />
<link type="text/css" href="../css/dingdan.css" rel="stylesheet" />

<script type="text/javascript" src="../js/jquery-1.11.3.js" /></script>
<script type="text/javascript" src="../js/public.js" /></script>
<script type="text/javascript" src="../js/order.js" /></script>
<script type="text/javascript">
	$(function() {
		//刷新收货地址信息
		$.ajax({
			type : "POST",
			url : "order_order.action",
			dataType : "JSON",
			success : function(msg) {
				var item = msg.addrList;
				var value = '';
				if (item.length > 3) {
					for (var i = 0; i < 3; i++) {
						value += '<ul>'
							+ '<li class="address_area_li deliver_top" >'
							+ '<label>'
							+ '<div id="deliver_name" style="margin-left:0px;font-size:13px;width:94%;">'
							+ '<span id="name_info" style="float:left;display:inline-block;margin-top:10px;">'
							+ item[i].shname
							+ '&nbsp;&nbsp;&nbsp;收</span>'
							+ '<input type="checkbox" style="float:right;display:inline-block;margin-top:10px;"/>'
							+ '</div>'
							+ '<div id="deliver_addr" style="margin-left:0px;font-size:12px;">'
							+ '<span>'
							+ item[i].placename
							+ '</span>'
							+ '</div>'
							+ '<div id="deliver_tel" style="margin-top:-20px;font-size:12px;">'
							+ '<span>'
							+ '电话：'
							+ item[i].shtel
							+ '</span>'
							+ '<span style="float:right;margin-right:14px;">'
							+ '邮编：'
							+ item[i].postcode
							+ '</span>'
							+ '</div>'
							+ '</label>'
							+ '</li>'
							+ '</ul>';
						}
							$(".addr").html(value);
						} else {
							for (var i = 0; i < item.length; i++) {
								value += '<ul>'
										+ '<li class="address_area_li deliver_top" >'
										+ '<label>'
										+ '<div id="deliver_name" style="margin-left:0px;font-size:13px;width:94%;">'
										+ '<span id="name_info" style="float:left;display:inline-block;margin-top:10px;">'
										+ item[i].shname
										+ '&nbsp;&nbsp;&nbsp;收</span>'
										+ '<input type="checkbox" style="float:right;display:inline-block;margin-top:10px;"/>'
										+ '</div>'
										+ '<div id="deliver_addr" style="margin-left:0px;font-size:12px;">'
										+ '<span>'
										+ item[i].placename
										+ '</span>'
										+ '</div>'
										+ '<div id="deliver_tel" style="margin-top:-20px;font-size:12px;">'
										+ '<span>'
										+ '电话：'
										+ item[i].shtel
										+ '</span>'
										+ '<span style="float:right;margin-right:14px;">'
										+ '邮编：'
										+ item[i].postcode
										+ '</span>'
										+ '</div>'
										+ '</label>'
										+ '</li>'
										+ '</ul>';
							}
							$(".addr").html(value);
						}
					}
				});

		//点击提交时验证成功信息
		$("#ordersub").click(function() {
			alert("提交订单");
			$.ajax({
				type : "POST",
				url : "order_hand.action",
				data : $("#submitOrder").serialize(),
				success : function(msg) { //查询成功后弹窗
					window.location.href = "orderOk.jsp";
				}

			});
		});

	});
</script>

</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div id="order_center">
		<div class="clearfix1" style="font-family: 微软雅黑;">
			<div id="orderbag" style="font-family: 微软雅黑;"></div>
		</div>
		<div class="reciever_info">
			<div class="info_label">
				收货人信息 <span class="xiugai">[修改/Edit]</span>
			</div>
			<div class="background"></div>
			<div class="addr">
				<c:forEach items="${sessionScope.placeList}" var="place">
					<ul>
						<li class="address_area_li deliver_top"><label>
								<div id="deliver_name"
									style="margin-left: 0px; font-size: 13px; width: 94%;">
									<span id="name_info"
										style="float: left; display: inline-block; margin-top: 10px;">${place.shname}&nbsp;&nbsp;&nbsp;收</span>
									<input type="checkbox"
										style="float: right; display: inline-block; margin-top: 10px;" />
								</div>
								<div id="deliver_addr"
									style="margin-left: 0px; font-size: 12px;">
									<span>${place.placename}</span>
								</div>
								<div id="deliver_tel"
									style="margin-top: -20px; font-size: 12px;">
									<span>手机：${place.shtel}</span> <span
										style="float: right; margin-right: 14px;">邮编：${place.postcode}</span>
								</div>
						</label></li>
					</ul>
				</c:forEach>
			</div>
			<div class="info_button">
				<span id="other_diliver">使用新地址</span> <span id="manager_diliver"><a>管理收货地址</a></span>
			</div>

			<form  id="address_form" action="order_addAdress.action" method="POST">
				<div id="add_deliver" style="display: none;">
					<div class="clearfix">
						<span class="red">*</span> 
						收货人/Consignee：
						<input id="custName" type="text" name="shname" size="20" maxlength="20" />
					</div>
					<div class="clearfix">
						<span class="red">*</span> 
						省市区/Provinces：
							 <select id="provinceid"  style="width: 100px;"onchange="deliver.province_change()">
							 	<option>湖南省</option>
							 	<option>湖北省</option>
							 	<option>和南省</option>
							 </select> 
							 <select id="cityid"  style="width: 100px;"onchange="deliver.city_change()">
							 	<option>衡阳市</option>
							 	<option>邵阳市</option>
							 	<option>长沙市</option>
							 </select> 
							 <select id="countyid"name="countyid" onchange="deliver.county_change()"
										style="width: 100px;" size="1"></select> 
							<span class="gray tip blue">如没有 县区 可以不用选择</span>
					</div>
					<div class="clearfix">
						<span class="red">*</span> 
						收货地址/Address：
						<span id="render_province" class="render_province"></span> 
						<span id="render_city" class="render_city"></span> 
						<span id="render_district" class="render_district"></span> 
						<input id="address" type="text" name="placename" size="80" maxlength="300">
					</div>
					<div class="clearfix">
						<span class="red">*</span> 
						邮编/Zip Code： 
						<input id="postalCode"	type="text" name="postcode" size="5" maxlength="6"> 
						<span  class="tip gray blue"> 请填写六位邮政编码</span>
					</div>
					<div class="clearfix">
						<span class="red">*</span> 
						手机/Mobile：
						<input id="mobile" type="text" name="shtel" maxlength="11" value="" size="20" /> 
						<span  class="gray tip blue"> 手机号码，电话号码必须填一项</span>
					</div>
					<div class="clearfix">
						Email/Email： 
						<input id="email" type="text" name="email" value="" size="40" maxlength="200" />
					</div>
					<div class="clearfix button" style="height:24px;width:850px;">
						<input class="sprite" type="submit" value="保存收货人信息" /> 
						<input class="closes" type="button" value="关闭" />
					</div>
				</div>
			</form>
		</div>

		<form  id="submitOrder">
			<div class="good_order">
				<div class="goodinfo_label">
					商品清单 <span class="xiugaicart"><a>[修改购物车]</a></span>
				</div>
				<table cellspacing="0" cellpadding="0" style="text-align: center;">
					<thead>
						<tr style="background: rgb(229, 229, 229);">
							<td width="60"></td>
							<td width="*">商品名称</td>
							<td width="50">库存</td>
							<td width="120">规格</td>
							<td width="90">吊牌价</td>
							<td width="100">销售价</td>
							<td width="70">花费积分</td>
							<td width="70">优惠</td>
							<td width="80">商品数量</td>
						</tr>
					</thead>
					<tbody style="border: 2px solid blue;">
						<c:forEach items="${sessionScope.cartList}" var="product">
							<tr>
								<td><a> <img src="" style="width: 40px; height: 40px;" /></a>
								</td>
								<td class="tal"><span> <a target="_blank"
										href="/shop/goods-320816.html">${product.pro_name}</a>
								</span></td>
								<td>${product.stock}</td>
								<td>默认(新基础黑/铁青灰 39)</td>
								<td>${product.pro_tagprice}元</td>
								<td>${product.pro_price}元</td>
								<td>0</td>
								<td>0元</td>
								<td>1</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="check_order">
				<h2>结算清单</h2>
				<p style="margin-top: 20px; margin-left: 14px;">
					<span>销售价：
					<%
							List<Product> list= (List<Product>) session.getAttribute("cartList");
							double sum=0.0;
							for(Product p:list){
								sum+=p.getPro_price();
							}
					%> 
					<%=sum %>
					元</span> -<span>余额支付：0.00元</span> -<span>总花费积分：0</span>
				</p>
				<p style="float: right; margin-right: 30px; font-size: 16px;">
					应付总金额： <font id="font_total_amount" class="red"
						style="font-weight: bold; font-size: 14px; color: red;"><%=sum %></font>元
				</p>
			</div>
			<input type="button" id="ordersub" value="提交订单"
				style="width: 200px; height: 50px; background: red; float: right; margin-top: 30px; color: white; font-size: 14px;" />
		</form>

	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
