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
<script type="text/javascript" src="../js/jquery-1.11.3.js" /></script>
<script type="text/javascript" src="../js/public.js" /></script>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="shopincart_content">
		<div class="clearfix" style="font-family: 微软雅黑;">
			<div id="shoppingbag" style="font-family: 微软雅黑;"></div>
		</div>
		<div id="container" style="padding-bottom: 10px; font-family: 微软雅黑;">
			<div class="items clearfix">
				<table cellspacing="0" cellpadding="0" class="grid">
					<thead
						style="background: rgb(239, 239, 239); height: 38px; width: 984px;">
						<tr>
							<td width="50"><input type="checkbox"
								style="vertical-align: middle;" name="chkAll" checked="checked"></td>
							<td width="60" style="text-align: left;">全选</td>
							<td width="*" style="text-align:center; padding: 0 0px 0 5px;">商品</td>
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
						<c:forEach begin="0" end="3" step="1">
							<tr>
							<td><input type="checkbox" checked="checked" id="chkGoods"  name="chkGoods"></td>
							<td style="text-align:left;">
								<a target="_blank" title="李宁弧四代植绒版男子减震跑鞋ARHJ049-2" href="#">
	                            	<img style="width:60px;height:60px;" class="item" 
	                            			alt="李宁弧四代植绒版男子减震跑鞋ARHJ049-2" src="../images/small1_img.png">
	                            </a>
							</td>
							<td class="tal">
								<span>
									<a target="_blank" href="/shop/goods-278459.html?intcmp=cartproduct">跑鞋</a>
								</span>
							</td>
							<td><div style="line-height:2em;">黑/焰红色 42</div></td>
							<td>
								<p><del>¥499</del></p>
								<p id="danjia">¥499</p>
							</td>
							<td>
	                        	<p class="quantity">5</p>
							</td>
							<td>¥0.00</td>
							<td><div class="price">¥1999</div></td>
							<td>
								<div onclick="collecte()"style="margin-bottom:4px;">
									<a style="text-decoration:none;" class="line" href="javascript:void(0);">收藏</a>
								</div>
								<div class="relative">
									<a style="text-decoration:none;"  class="line" href="javascript:void(0);">删除</a>
									<div class="absolute hide">
										<p></p>
									</div>
								</div>
							</td>
						</tr>
						</c:forEach>
						
					</tbody>
					<tfoot style="background: rgb(239, 239, 239);">
						<tr>
							<td style="text-align: center; height: 52px;"><input
								type="checkbox" style="vertical-align: middle;"
								id="chkAll_bottom" onclick="selectAll()" name="chkAll_bottom"
								checked="checked"></td>
							<td style="text-align: left;">全选</td>
							<td><span style="color: #CC0000;">X</span> <a
								style="color: #555555;" href="javascript:void(0);">删除选中商品</a></td>
							<td colspan="2">&nbsp;</td>
							<td style="text-align: center;" colspan="2">已选商品<span
								style="font-weight: bold; color: #CC0000; font-size: 14px;"
								id="alGoods">1</span>种
							</td>
							<td colspan="2">
								<div
									style="height: 20px; line-height: 20px; text-align: center;">
									总计：&nbsp;&nbsp;&nbsp;¥<span id="totalPrice">56.0</span>
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
			<div style="width: 980px; height: 60px;" id="heji"></div>
			<form style="float: right; margin-button: -10px;" action="order.jsp"
				id="orderMyform">
				<input type="button" id="continueBuy" value="继续购物"
					style="width: 135px; height: 56px; font-size: 14px; margin-left: -656px; background: #000; color: white;" />
				<input type="submit" value="去结算"
					style="width: 135px; height: 56px; background: red; color: white; font-size: 14px;" />
			</form>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
