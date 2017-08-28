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
<link type="text/css" href="../css/shoppingcart.css" rel="stylesheet" />
<!-- <link type="text/css" href="../css/comm_header.css" rel="stylesheet" /> -->

<script type="text/javascript" src="../js/jquery-1.11.3.js" /></script>
<script type="text/javascript" src="../js/public.js" /></script>
<script type="text/javascript" src="../js/login.js" /></script>
<script type="text/javascript" src="../js/global.js" /></script>
<script type="text/javascript">
	var u_id =0;
	u_id = $("#UidHidden").val();
	
	function collecte(pro_number){
		$.ajax({
			type:"POST",
			data:{u_id:u_id,pro_number:pro_number},
			url:"collection.action",
			success:function(msg){
				window.location.href="shoppingcart.jsp";
			}
		});
	}
	
	function ContinueShopping(){
		window.location.href="index.jsp";
	}
	
	function GoCheck(){
		var Buyamount=0;
		window.location.href="order_order.action";
	}
	
	
	$(function() {
		var pNum = $("#proNum").attr("class");
		var buyNum = $("#quantity_306687").val();//当前购买数量

		$("#quantity_306687").blur(function() {
			var pNum = $("#proNum").attr("class");
			var buyNum = $("#quantity_306687").val();

			$.ajax({
				type : "POST",
				url : "pro_cart.action",
				data : {"Buyamount":buyNum},
				success : function(msg) {

				}
			});
		});

		$(".reduce").click(function() {
			var pNum = $("#proNum").attr("class");
			var buyNum = $("#quantity_306687").val();//当前购买数量
			$.ajax({
				type : "POST",
				url : "order_order.action",
				data : {"Buyamount":buyNum},
				success : function(msg) {

				}
			});
		});
		$(".add").click(function() {
			var pNum = $("#proNum").attr("class");
			var buyNum = $("#quantity_306687").val();//当前购买数量
			$.ajax({
				type : "POST",
				url : "pro_buyAmount.action",
				data : {"Buyamount":buyNum},
				success : function(msg) {

				}
			});
		});
	});
		
	function deleteById(pro_number){
		$.ajax({
			type : "POST",
			url : "pro_deleteById.action",
			data : {"pro_number":pro_number},
			success : function(msg) {
				window.location.href="../page/shoppingcart.jsp";
			}
		});
	}
	
//通过选中项删除
function deleteBySel(){
	var selectId =""; //最终选中哪几个
	var pro_number =0;
	var allcheckBoxs = document.getElementsByName("chkGoods");
	for(var i=0;i<allcheckBoxs.length;i++){
		if(allcheckBoxs[i].type=='checkbox' && allcheckBoxs[i].checked==true){
			selectId += allcheckBoxs[i].value;
		}
	}
	
	var selects = selectId.split('');
	for(var j=0;j<selects.length;j++){
		pro_number = selects[j];
		$.ajax({
			type : "POST",
			url : "pro_deleteById.action",
			data : {"pro_number":pro_number},
			success : function(msg) {
				window.location.href="../page/shoppingcart.jsp";
			}
		});
	}
	
}

//全部选中或不选中
function selectAll(checkValue){
	var allcheckBoxs = document.getElementsByName("chkGoods");
	for(var i=0;i<allcheckBoxs.length;i++){
		//判断对象是否为复选框
		if(allcheckBoxs[i].type=='checkbox'){
			allcheckBoxs[i].checked=checkValue;
			document.getElementsByName("chkAll_bottom")[0].checked = checkValue;
		}
	}
}
function selectAllbutton(checkValue){
	var allcheckBoxs = document.getElementsByName("chkGoods");
	for(var i=0;i<allcheckBoxs.length;i++){
		//判断对象是否为复选框
		if(allcheckBoxs[i].type=='checkbox'){
			allcheckBoxs[i].checked=checkValue;
			document.getElementsByName("chkAll")[0].checked = checkValue;
		}
	}
}

//单选
function ch(){
	//获取复选框对象
	var allcheckBoxs=document.getElementsByName('chkGoods');
	for(var i=0;i<allcheckBoxs.length;i++){
		//判断复选框是否被选中chkAll_bottom
		if(allcheckBoxs[i].type=='checkbox'&& allcheckBoxs[i].checked==false){
			document.getElementsByName('chkAll')[0].checked=false;
			document.getElementsByName('chkAll_bottom')[0].checked=false;
			return;
		}
	}
	document.getElementsByName('chkAll')[0].checked=true;
	document.getElementsByName('chkAll_bottom')[0].checked=true;
}
</script>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="shopincart_content">
		<div class="clearfix" style="font-family: 微软雅黑;">
			<div id="shoppingbag" style="font-family: 微软雅黑;"></div>
		</div>
		<div id="container">
			<div class="items clearfix">
				<table cellspacing="0" cellpadding="0" class="grid">
					<thead
						style="background: rgb(239, 239, 239); height: 38px; width: 984px;">
						<tr>
							<td width="50"><input type="checkbox" id="chkAll"
								onclick="selectAll(this.checked)"
								style="vertical-align: middle;" name="chkAll"></td>
							<td width="60" style="text-align: left;">全选</td>
							<td width="*" style="text-align: center; padding: 0 0px 0 5px;">商品</td>
							<td width="140">规格</td>
							<td width="80" style="position: relative;">
								<div
									style="position: absolute; height: 15px; border: 1px solid #9C9B9B; right: 0; top: 11px;"></div>
								单价
							</td>
							<td width="80" style="position: relative;">
								<div
									style="position: absolute; height: 15px; border: 1px solid #9C9B9B; right: 0; top: 11px;"></div>
								数量
							</td>
							<td width="80" style="position: relative;">
								<div
									style="position: absolute; height: 15px; border: 1px solid #9C9B9B; right: 0; top: 11px;"></div>
								优惠
							</td>
							<td width="80" style="position: relative;">
								<div
									style="position: absolute; height: 15px; border: 1px solid #9C9B9B; right: 0; top: 11px;"></div>
								小计
							</td>
							<td width="80">操作</td>
						</tr>
					</thead>
					<tbody style="height: 52px;">
						<tr>
							<td
								style="text-align: left; border: 1px solid rgb(239, 239, 239);"
								colspan="9">
								<div
									style="padding: 6px; float: left; height: 30px; line-height: 30px;">
									<span style="color: #555555;">温馨提示：</span> <span
										style="color: #F05133;">选购清单中的商品无法保留库存，请您及时结算。 </span>
								</div>
							</td>
						</tr>
					</tbody>

					<tbody style="background: white; height: 240px;" id="butInfo">
						<c:choose>
							<c:when test="${sessionScope.cartList == null}">
								<tr>
									<td></td>
									<td></td>
									<td><h1 style="color: red; font-size: 16px;">亲，您还没有选购商品,快选购您喜欢的商品吧</h1></td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${sessionScope.cartList}" var="product">
									<tr>
										<td><input type="checkbox" id="chkGoods" name="chkGoods"
											onclick="ch()" value="${product.pro_number}" /></td>
										<td style="text-align: left;"><a target="_blank"
											title="李宁弧四代植绒版男子减震跑鞋ARHJ049-2" href="#"> <img
												style="width: 60px; height: 60px;" class="item"
												alt="李宁弧四代植绒版男子减震跑鞋ARHJ049-2"
												src="../${product.color.split(',')[0]}" />
										</a></td>
										<td class="tal"><span> <a target="_blank"
												href="/shop/goods-278459.html?intcmp=cartproduct">${product.pro_name}</a>
										</span></td>
										<td><div style="line-height: 2em;">黑/焰红色 42</div></td>
										<td>
											<p>
												<del>¥${product.pro_tagprice}</del>
											</p>
											<p id="danjia">¥${product.pro_price}</p>
										</td>
										<td><a class="reduce" href="javascript:void(0)"
											onclick="goods_buy(-1)">-</a> <input id="quantity_306687"
											class="quantity" value="${product.getBuyamount()}"
											style="top: 0; text-align: center; width: 30px;" /> <a
											class="add" href="javascript:void(0)" onclick="goods_buy(1)">+</a>
										</td>
										<td>¥0.00</td>
										<td><div class="price">¥${product.pro_price*product.getBuyamount()}</div></td>
										<td>
											<div onclick="collecte(${product.pro_number})"
												style="margin-bottom: 4px;">
												<a style="text-decoration: none;" class="line"
													href="javascript:void(0)">收藏</a>
											</div>
											<div class="relative">
												<a style="text-decoration: none;" class="line"
													href="javascript:void(0)"
													onclick="deleteById(${product.pro_number})">删除</a>
												<div class="absolute hide">
													<p></p>
												</div>
											</div>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>

					</tbody>
					<tfoot style="background: rgb(239, 239, 239);">
						<tr>
							<td style="text-align: center; height: 52px;"><input
								type="checkbox" style="vertical-align: middle;"
								onclick="selectAllbutton(this.checked)" id="chkAll_bottom"
								name="chkAll_bottom" /></td>
							<td style="text-align: left;">全选</td>
							<td><span style="color: #CC0000;">X</span> <a
								style="color: #555555;" href="javascript:void(0);"
								onclick="deleteBySel()">删除选中商品</a></td>
							<td colspan="2">&nbsp;</td>
							<td style="text-align: center;" colspan="2">已选商品<span
								style="font-weight: bold; color: #CC0000; font-size: 14px;"
								id="alGoods">${sessionScope.cartList.size()}</span>件
							</td>
							<td colspan="2">
								<div
									style="height: 20px; line-height: 20px; text-align: center;">
									总计：&nbsp;&nbsp;&nbsp;¥<span id="totalPrice"> <c:choose>
											<c:when test="${sessionScope.cartList == null}">
											0
										</c:when>
											<c:otherwise>
												<%
													List<Product> list = (List<Product>) session
																	.getAttribute("cartList");
															double sum = 0.0;
															for (Product p : list) {
																sum += p.getPro_price() * p.getBuyamount();
															}
												%>
												<%=sum%>
											</c:otherwise>
										</c:choose>
									</span>
								</div>
								<div
									style="height: 20px; line-height: 20px; text-align: center;">
									优惠：&nbsp;&nbsp;&nbsp;-¥<span id="totalOffer">0.00</span>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<div style="height: 15px;"></div>
			<div id="heji"></div>
			<div id="jixugouwu_Button">
				<form id="orderMyform" method="POST">
					<input type="button" id="continueBuy" value="继续购物"  onclick="ContinueShopping()"  style="background: #000;" />
					<input type="button" value="去结算" onclick="GoCheck()"  style="background: red;" />
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
