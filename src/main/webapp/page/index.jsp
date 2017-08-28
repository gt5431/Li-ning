<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8" />
	<link type="text/css" href="../css/shop.css" rel="stylesheet" />
	<link type="text/css" href="../css/Common1.css" rel="stylesheet" />
	<!-- <link type="text/css" href="../css/comm_header.css" rel="stylesheet" /> -->
	
	<script type="text/javascript" src="../js/jquery-1.11.3.js" /></script>
	<script type="text/javascript" src="../js/public.js" /></script>
	<script type="text/javascript" src="../js/login.js" /></script>
	<script type="text/javascript" src="../js/global.js" /></script>
	<script type="text/javascript" src="../js/baokuan.js" /></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="center">
		<div id="div_1">
			<div id="swiper_slide">
				<a class="swiper" href="pro_cart.action?pro_number=25"><img src="../images/swiper_1.png" /></a> 
				<a class="swiper" href="pro_details.action?pro_number=25"><img src="../images/swiper_2.png" /></a> 
				<a	class="swiper"><img src=" ../images/swiper_3.png"></a> 
				<a	class="swiper"><img src=" ../images/swiper_4.png"></a>
			</div>
		</div>
		<div>
			<a class="arrow_left"><img src=" ../images/arrow_left.png" /></a> <a
				class="arrow_right"><img src=" ../images/arrow_right.png" /></a>
		</div>
		<div class="swiper_title">
			<a class="swiper_a" style="font-family: 'Microsoft YaHei UI';">
				lining-新活力系列 </a> <a class="swiper_a"
				style="font-family: 'Microsoft YaHei UI';"> 2015棉羽新品上市 </a> <a
				class="swiper_a" style="font-family: 'Microsoft YaHei UI';">
				球迷专属定制 </a> <a class="swiper_a"
				style="font-family: 'Microsoft YaHei UI';"> 型走冬日 新暖潮 </a>
		</div>

		<div id="tables">
			<table>
				<tr>
					<td><a><img src=" ../images/hot.png" /></a></td>
					<td><a><img src=" ../images/keng.png" /></a></td>
					<td><a><img style="margin-top: 2px;"
							src=" ../images/keng_5.png"> <img style="margin-top: -5px;"
							src=" ../images/keng_4.png" /></a></td>
				</tr>
			</table>
			<div style="margin-top: 12px; margin-left: 79px;">
				<a><img src=" ../images/keng-new.jpg" /></a>
			</div>

		</div>
		<div id="gray_bg">
			<div class="banner">
				<ul>
					<li>
						<div class="banner_star">
							<div class="banner_list1">
								<a
									style="display: block; width: 590px; height: 355px; border: 0px;"><img
									src=" ../images/picLeft_1.png" /></a> <a
									style="display: none; width: 590px; height: 355px; border: 0;"><img
									src=" ../images/picLeft_2.png" /></a> <a
									style="display: none; width: 590px; height: 355px; border: 0;"><img
									src=" ../images/picLeft_3.png" /></a>

							</div>
							<em>明星系列</em> <a class="banner_left"
								style="position: absolute; top: 210px; left: 24px;"><img
								src=" ../images/left.png" /></a> <a class="banner_right"
								style="position: absolute; top: 210px; right: 24px;"><img
								src=" ../images/right.png" /></a>
						</div>
					<li>
						<div class="banner_new">
							<div class="banner_list2">
								<a
									style="display: block; width: 590px; height: 355px; border: 0;"><img
									src=" ../images/picRight_1.png" /></a> <a
									style="display: none; width: 590px; height: 355px; border: 0;"><img
									src=" ../images/picRight_2.png" /></a> <a
									style="display: none; width: 590px; height: 355px; border: 0;"><img
									src=" ../images/picRight_3.png" /></a>
							</div>
							<em>新品上市</em> <a class="banner_left"
								style="position: absolute; top: 210px; left: 24px;"><img
								src=" ../images/left.png" /></a> <a class="banner_right"
								style="position: absolute; top: 210px; right: 24px;"><img
								src=" ../images/right.png" /></a>
						</div>
					</li>
				</ul>
			</div>
			<div class="banner_buttom">
				<h2>爆款推荐</h2>
			</div>
		</div>

		<div id="indexContent_1">
			<div class="hotcake">
				<div class="hchoice">
					<c:forEach items="${sessionScope.Type}" var="type">
						<div id="chioce_1${type.typesid}" class="choice_Item"
							onmouseover="findProductById('${type.typesid}')"
							onmouseout="mouseout('${type.typesid}')">
							<a><span id="s${type.typesid}">${type.typename}</span></a>
						</div>
					</c:forEach>
				</div>
				<div class="hselect">
					<div id="select_1">
						<div class="selectItem">
							<div style="width: 1191px; height:666px;overflow: hidden;" id="selectItmdiv_1">
								<c:forEach items="${sessionScope.Product}" var="product">
									<div class="selinter"></div>
									<div class="selItem">
										<div class="selMainPic">
											<a id="god_1" href="pro_details.action?pro_number=${product.pro_number}"><img src="../${product.color.split(',')[0]}" /></a>
											<div class="slaveSwiperContent">
												<div class="slaveItem">
													<img src="../${product.color.split(',')[0]}" /><font></font>
												</div>
												<div class="slaveItem">
													<img src="../${product.color.split(',')[1]}" /><font></font>
												</div>
												<div class="slaveItem">
													<img src="../${product.color.split(',')[2]}" /><font></font>
												</div>
											</div>
										</div>
										<div class="hgoodsName" id="hgoodsName">${product.pro_name}</div>
										<font class="ft_price">￥${product.pro_tagprice}</font>
										<div class="hgoodsLine"></div>
									</div>
								</c:forEach>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="indexContent_2">
				<div class="Run">
					<div class="Run_head">
						<h2>
							<span>跑步系列</span> <i>RUNNING SERIES</i>
						</h2>
						<a><img src=" ../images/more.png" /></a>
					</div>
					<img style="margin-left: 50%;" src=" ../images/black_button.png" />
					<div class="Run_main">
						<div class="run_nav">
							<p>男子</p>
							<ul>
								<li>跑步鞋</li>
								<li>休闲鞋</li>
								<li>运动生活鞋</li>
								<li>T恤/Polo衫/背心</li>
								<li>卫衣</li>
								<li>羽绒服</li>
							</ul>
							<p>女子</p>
							<ul>
								<li>跑步鞋</li>
								<li>休闲鞋</li>
								<li>运动生活鞋</li>
								<li>T恤/Polo衫/背心</li>
								<li>卫衣</li>
								<li>羽绒服</li>
							</ul>
							<p>明星系列</p>
							<ul>
								<li>****</li>
								<li>****</li>
								<li>****</li>
							</ul>
						</div>
						<div class="run_banner">
							<span><img src=" ../images/run_select.png" /></span>
						</div>
						<div class="run_select_bnt">
							<div class="select_bnt1">
								<p>跑步鞋</p>
							</div>
							<div class="select_bnt1">
								<p>篮球鞋</p>
							</div>
							<div class="select_bnt2">
								<p>服装</p>
							</div>
							<div class="select_bnt1">
								<p>休闲鞋</p>
							</div>
						</div>
						<div class="run_list">
							<div class="run_list_content" id="run_list_content"
								style="font-family: 'Microsoft YaHei UI'; display: block;">
								<ul>
								<li style="font-family: \'Microsoft YaHei UI\';"><a
										href="shoppingcart.jsp"> <img src="../images/goods_3.png" />
											<span style="font-family: \'Microsoft YaHei UI\';"> <font
												class="rl_name">【2015新品】李宁</font> <font class="rl_category">ARBK097-1</font>
										</span> <em> <font class="ft_14" sku="AWYK001-3">¥ 239</font>
										</em>
									</a></li>
									<li style="font-family: \'Microsoft YaHei UI\';"><a
										href="shoppingcart.jsp"> <img src="../images/Wcloths_2.png" />
											<span style="font-family: \'Microsoft YaHei UI\';"> <font
												class="rl_name">【2015新品】李宁</font> <font class="rl_category">ARBK097-1</font>
										</span> <em> <font class="ft_14" sku="AWYK001-3">¥ 112</font>
										</em>
									</a></li>
									<li style="font-family: \'Microsoft YaHei UI\';"><a
										href="shoppingcart.jsp"> <img src="../images/Wcloths_1.png" />
											<span style="font-family: \'Microsoft YaHei UI\';"> <font
												class="rl_name">【2015新品】李宁</font> <font class="rl_category">ARBK097-1</font>
										</span> <em> <font class="ft_14" sku="AWYK001-3">¥ 112</font>
										</em>
									</a></li>
									<li style="font-family: \'Microsoft YaHei UI\';"><a
										href="shoppingcart.jsp"> <img src="../images/goods_5.png" />
											<span style="font-family: \'Microsoft YaHei UI\';"> <font
												class="rl_name">【2015新品】李宁</font> <font class="rl_category">ARBK097-1</font>
										</span> <em> <font class="ft_14" sku="AWYK001-3">¥ 162</font>
										</em>
									</a></li>
									<li style="font-family: \'Microsoft YaHei UI\';"><a
										href="shoppingcart.jsp"> <img src="../images/goods_4.png" />
											<span style="font-family: \'Microsoft YaHei UI\';"> <font
												class="rl_name">【2015新品】李宁</font> <font class="rl_category">ARBK097-1</font>
										</span> <em> <font class="ft_14" sku="AWYK001-3">¥ 162</font>
										</em>
									</a></li>
									<li style="font-family: \'Microsoft YaHei UI\';"><a
										href="shoppingcart.jsp"> <img src="../images/goods_2.png" />
											<span style="font-family: \'Microsoft YaHei UI\';"> <font
												class="rl_name">【2015新品】李宁</font> <font class="rl_category">ARBK097-1</font>
										</span> <em> <font class="ft_14" sku="AWYK001-3">¥ 162</font>
										</em>
									</a></li>
								</ul> 
							</div>
							<div class="clear" style="font-family: 'Microsoft YaHei UI';"></div>
						</div>
						<div class="clear" style="font-family: 'Microsoft YaHei UI';"></div>
					</div>
					<div class="Run_bottom">
						<ul>
							<li><a> <img width="346px" height="217px"
									src=" ../images/Q4ba02.jpg" />
									<div class="span">减震跑鞋</div>
							</a></li>
							<li><a> <img width="350" height="217"
									src=" ../images/Q4ba1.jpg" />
									<div class="span">智跑生活，出手可得</div>
							</a></li>
							<li><a> <img src=" ../images/122.png" />
									<div class="span">选择专属你的跑鞋</div>
							</a></li>
							<li><a> <img width="217" height="217"
									src=" ../images/jiaoxing.png" />
									<div class="span">一切皆有可能</div>
							</a></li>
						</ul>
					</div>
					<div class="clear" style="font-family: 'Microsoft YaHei UI';"></div>
				</div>
		</div> 

		<div class="indexCotent_3">
			<div>
				<div class="Sport">
					<div class="Sport_head">
						<h2>
							<span>运动生活</span> <i>SPORTS LIFE</i>
						</h2>
						<a><img src=" ../images/more.png" /></a>
					</div>
					<img style="margin-left: 50%;" src=" ../images/black_button.png" />
					<div class="Sport_main">
						<div class="Sport_nav">
							<p>男子</p>
							<ul>
								<li>休闲鞋</li>
								<li>运动生活鞋</li>
								<li>T恤/Polo衫/背心</li>
								<li>卫衣</li>
								<li>羽绒服</li>
							</ul>
							<p>女子</p>
							<ul>
								<li>跑步鞋</li>
								<li>运动生活鞋</li>
								<li>T恤/Polo衫/背心</li>
								<li>卫衣</li>
								<li>羽绒服</li>
							</ul>
							<p>明星系列</p>
							<ul>
								<li>$$$$</li>
								<li>$$$$</li>
								<li>$$$$</li>
							</ul>
						</div>
						<div class="Sport_banner">
							<a><img src=" ../images/Q4fashion-2.jpg" /></img></a>
						</div>
						<div class="Sport_select_bnt">
							<div class="select_bnt1">
								<p>休闲鞋</p>
							</div>
							<div class="select_bnt2">
								<p>服装</p>
							</div>
						</div>

						<div class="Sport_list">
							<div class="Sport_list_content" id="Sport_list_content"
								style="font-family: 'Microsoft YaHei UI'; display: block;">
								<ul>
									<c:forEach items="${sessionScope.Sport}" var="sport">
										<li class="b_t_0 b_r_0"	style="font-family: \'Microsoft YaHei UI\';">
											<a  href="pro_details.action?pro_number=${sport.pro_number}"> 
												<img src="../${sport.color.split(',')[0]}" /> 
												<span style="font-family: 微软雅黑;"> 
													<font class="rl_name">${sport.pro_name}</font>
													<font class="rl_category">AFDK068-1</font>
												</span> 
												<em> 
													<font class="ft_14" sku="AFDK068-1">¥${sport.pro_tagprice}</font>
												</em>
											</a>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- <div id="exterFooter"></div> -->
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
