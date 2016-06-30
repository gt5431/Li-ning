<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<%-- <span style="display: none;" id="proNum"
	class="${sessionScope.goodsinfo.pro_number}"></span> --%>
<title>商品详情</title>
<link href="../css/login.css" type="text/css" rel="stylesheet" />
<link type="text/css" href="../css/Common1.css" rel="stylesheet" />
<link href="../css/shop.css" type="text/css" rel="stylesheet" />
<link type="text/css" href="../css/comm_header.css" rel="stylesheet" />

<script type="text/javascript" src="../js/jquery-1.11.3.js" /></script>
<script type="text/javascript" src="../js/public.js" /></script>
<script type="text/javascript" src="../js/login.js" /></script>
<script type="text/javascript" src="../js/global.js" /></script>
<script type="text/javascript">
	//切换大图片
	function click1(index) {
		$("#photo_li li").css("display", "none");
		$("#photo_li li").eq(index).css("display", "block");
	}
	//切换商品评论
	function click2(index) {
		$("#main #info #info2").css("display", "none");
		$("#main #info #info3").css("display", "none");
		$("#main #info #info4").css("display", "none");
		$("#main #info #info" + index).css("display", "block");
		$("#main #info #info1 #head_info1 li").css("background", "#000");
		$("#main #info #info1 #head_info1 li").eq(index - 2).css("background","#F03");
	}
	//	选择购买数量
	function goods_buy(index) {
		var i = parseInt($("#quantity_306687").val());
		if (index == -1 && i == 1) {
			return false;
		} else {
			$("#quantity_306687").val(i + index);
		}

	}
	
	$(function() {
		var pNum = $("#proNum").attr("class");
		var buyNum = $("#quantity_306687").val();//当前购买数量

		$("#quantity_306687").blur(function() {
			var pNum = $("#proNum").attr("class");
			var buyNum = $("#quantity_306687").val();

			$.ajax({
				type : "POST",
				url : "pro_buyAmount.action",
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
				url : "pro_buyAmount.action",
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

	//选择尺码、颜色
	function choose_color(index) {
		$("#lu2 img").css("border", "1px solid #DDD");
		$("#lu2 img").eq(index).css("border", "1px solid red");
		//console.info(j);
	}
	function choose_ma(index) {
		$("#choose_chi li").css("border", "1px solid #DDD");
		$("#choose_chi li").eq(index).css("border", "1px solid red");
	}
</script>

<!-----------------------图片放大--------------------------------------->
<script type="text/javascript">
	function getEventObject(W3CEvent) { //事件标准化函数
		return W3CEvent || window.event;
	}

	function getPointerPosition(e) { //兼容浏览器的鼠标x,y获得函数
		e = e || getEventObject(e);
		var x = e.pageX
				|| (e.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft));
		var y = e.pageY
				|| (e.clientY + (document.documentElement.scrollTop || document.body.scrollTop));
		return {
			'x' : x,
			'y' : y
		};
	}

	function setOpacity(elem, level) { //兼容浏览器设置透明值
		if (elem.filters) {
			elem.style.filter = 'alpha(opacity=' + level * 100 + ')';
		} else {
			elem.style.opacity = level;
		}
	}

	function css(elem, prop) { //css设置函数,方便设置css值,并且兼容设置透明值
		for ( var i in prop) {
			if (i == 'opacity') {
				setOpacity(elem, prop[i]);
			} else {
				elem.style[i] = prop[i];
			}
		}
		return elem;
	}

	var magnifier = {
		m : null,

		init : function(magni) {
			var m = this.m = magni || {
				cont : null, //装载原始图像的div
				img : null, //放大的图像
				mag : null, //放大框
				scale : 15
			//比例值,设置的值越大放大越大,但是这里有个问题就是如果不可以整除时,会产生些很小的白边,目前不知道如何解决
			}
			/*
			 指定放大后图片的大小
			 */
			css(m.img, {
				'position' : 'absolute',
				'width' : (m.cont.clientWidth * m.scale) + 'px', //原始图像的宽*比例值    
				'height' : (m.cont.clientHeight * m.scale) + 'px' //原始图像的高*比例值
			})

			/*
			 指定放大框的大小
			 */
			css(m.mag, {
				'display' : 'none',
				//'width' : (m.cont.clientWidth * m.scale)  + 'px',            //m.cont为原始图像,与原始图像等宽
				//'height' : (m.cont.clientHeight * m.scale)  + 'px',
				//m.cont为原始图像,与原始图像等高
				'width' : m.cont.clientWidth + 'px', //m.cont为原始图像,与原始图像等宽
				'height' : m.cont.clientHeight + 'px',
				'position' : 'absolute',
				'left' : m.cont.offsetLeft + m.cont.offsetWidth + 10 + 'px', //放大框的位置为原始图像的右方远10px
				'top' : m.cont.offsetTop + 'px' //放大框的位置与原始图像top相同
			});

			var borderWid = m.cont.getElementsByTagName('div')[0].offsetWidth
					- m.cont.getElementsByTagName('div')[0].clientWidth; //获取border的宽

			css(m.cont.getElementsByTagName('div')[0], { //m.cont.getElementsByTagName('div')[0]为浏览框
				'display' : 'none', //开始设置为不可见
				'width' : m.cont.clientWidth / m.scale - borderWid + 'px', //原始图片的宽/比例值 - border的宽度
				'height' : m.cont.clientHeight / m.scale - borderWid + 'px', //原始图片的高/比例值 - border的宽度
				'opacity' : 0.5
			//设置透明度
			});

			m.img.src = m.cont.getElementsByTagName('img')[0].src; //让原始图像的src值给予放大图像
			m.cont.style.cursor = 'crosshair'; //滑动时鼠标为十字型

			m.cont.onmouseover = magnifier.start;

		},
		/*
		鼠标进入图片时触发
		 */
		start : function(e) {
			if (document.all) { //只在IE下执行,主要避免IE6的select无法覆盖
			//创建一个iframe，将放大的img放进去
				magnifier.createIframe(magnifier.m.img);
			}

			this.onmousemove = magnifier.move; //this指向m.cont
			//this.onmouseout = magnifier.end;
		},
		/*
		鼠标移动时触发
		 */
		move : function(e) {
			var pos = getPointerPosition(e); //事件标准化

			this.getElementsByTagName('div')[0].style.display = '';

			//this.offsetLeft:250px.原始图片所在div的left
			//this.getElementsByTagName('div')[0].style.width:112

			//this.clientWidth:原始图片:342
			//this.getElementsByTagName('div')[0].offsetWidth:114/0

			//this.getElementsByTagName('div')[0].id:browser

			//确定broswer的显示范围

			css(
					this.getElementsByTagName('div')[0],
					{
						'top' : Math
								.min(
										Math
												.max(
														pos.y
																- this.offsetTop
																- parseInt(this
																		.getElementsByTagName('div')[0].style.height)
																/ 2, 0),
										this.clientHeight
												- this
														.getElementsByTagName('div')[0].offsetHeight)
								+ 'px',
						'left' : Math
								.min(
										Math
												.max(
														pos.x
																- this.offsetLeft
																- parseInt(this
																		.getElementsByTagName('div')[0].style.width)
																/ 2, 0),
										this.clientWidth
												- this
														.getElementsByTagName('div')[0].offsetWidth)
								+ 'px'
					//当鼠标在图片中过左位置时，会用Math.max来限制   当在过右位置时，会用 Math.min来限制
					});

			//显示出黑框
			magnifier.m.mag.style.display = '';

			/*
			改变右侧图的显示
			 */
			css(
					magnifier.m.img,
					{
						'top' : -(parseInt(this.getElementsByTagName('div')[0].style.top) * magnifier.m.scale)
								+ 'px',
						'left' : -(parseInt(this.getElementsByTagName('div')[0].style.left) * magnifier.m.scale)
								+ 'px'
					//'left':'-200px',  //图片向左移动200px
					//'top':'500px'
					});

		},
		/*
		鼠标离开时触发
		 */
		end : function(e) {
			this.getElementsByTagName('div')[0].style.display = 'none';//div Browser   //鼠标所在的黑框
			magnifier.removeIframe(magnifier.m.img); //销毁iframe
			magnifier.m.mag.style.display = 'none'; //div mag //放大的图片
		},

		/*
		 鼠标进入时触发
		 */
		createIframe : function(elem) {
			var layer = document.createElement('iframe');
			layer.tabIndex = '-1';
			layer.src = 'javascript:false;';
			elem.parentNode.appendChild(layer);

			layer.style.width = elem.offsetWidth + 'px';
			layer.style.height = elem.offsetHeight + 'px';
		},
		/*
		鼠标离开时触发
		 */
		removeIframe : function(elem) {
			var layers = elem.parentNode.getElementsByTagName('iframe');
			while (layers.length > 0) {
				layers[0].parentNode.removeChild(layers[0]);
			}
		}
	}

	window.onload = function() {
		magnifier.init({
			cont : document.getElementById('photo_li'),
			img : document.getElementById('magnifierImg'),
			mag : document.getElementById('mag'),
			scale : 3
		});
	}
	$(function() {
		$("#photo_li").bind({
			mouseover : function() {
				$("#mag").css("display", "block");
			},
			mouseout : function() {
				$("#mag").css("display", "none");
			}
		});
	});
</script>
<!-- 点击购买跳转到购物车页面 -->
</head>

<body>

	<jsp:include page="header.jsp"></jsp:include>
	<!-----------------------------------头部开始(2015-11-20)---------------------------------------->

	<!--公共部分结束（不包括尾部）-->

	<div id="head">
		<div id="left">
			<div id="photo">
				<ul id="photo_li" style="height: 300px;">
					<li style="display: block"><img
						src=" ../${sessionScope.goodsinfoimg[0]}" /></li>
					<li><img src=" ../${sessionScope.goodsinfoimg[1]}" /></li>
					<li><img src=" ../${sessionScope.goodsinfoimg[2]}" /></li>
					<li><img src=" ../${sessionScope.goodsinfoimg[3]}" /></li>
					<li><img src=" ../${sessionScope.goodsinfoimg[4]}" /></li>
					<div id="Browser"></div>
				</ul>
			</div>
			<div id="small_photo">
				<ul id="smallphoto">
					<li><a onMouseOver="click1(0)"><img
							src=" ../${sessionScope.goodsinfoimg[0]}" /></a></li>
					<li><a onMouseOver="click1(1)"><img
							src=" ../${sessionScope.goodsinfoimg[1]}" /></a></li>
					<li><a onMouseOver="click1(2)"><img
							src=" ../${sessionScope.goodsinfoimg[2]}" /></a></li>
					<li><a onMouseOver="click1(3)"><img
							src=" ../${sessionScope.goodsinfoimg[3]}" /></a></li>
					<li><a onMouseOver="click1(4)"><img
							src=" ../${sessionScope.goodsinfoimg[4]}" /></a></li>
				</ul>
			</div>
			<div id="fenxiang">
				<img src="../images/fenxiang.png" />
			</div>
		</div>
		<div id="mag">
			<img id="magnifierImg" />
		</div>
		<div id="center">
			<form action="" method="POST">
				<div id="main">
					<span> ${sessionScope.goodsinfo.pro_name}</span>
					<p class="a">全场满99包邮</p>
					<p>商品编码：ABPJ021-4-7</p>
					<p>吊牌价:￥${sessionScope.goodsinfo.pro_tagprice}</p>
					<p class="b">销售价:￥${sessionScope.goodsinfo.pro_price}</p>
					<p>折&nbsp;&nbsp;扣:100%节省:￥0</p>
					<p>选择颜色：</p>
				</div>
				<div id="main2">
					<ul id="lu2">
						<li onClick="choose_color(0)"><a title="黑/李宁蓝"><img
								src="../${sessionScope.goodsinfocolor[0]}" /></a></li>
						<li onClick="choose_color(1)"><a title="黑/黄"><img
								src="../${sessionScope.goodsinfocolor[1]}" /></a></li>
						<li onClick="choose_color(2)"><a title="白/马尔斯红"><img
								src="../${sessionScope.goodsinfocolor[2]}" /></a></li>
					</ul>
				</div>
				<div id="choose">
					<p>选择尺码：</p>
					<ul id="choose_chi">
						<li><a href="javascript:void(0)" onClick="choose_ma(0)">39</a></li>
						<li><a href="javascript:void(0)" onClick="choose_ma(1)">40</a></li>
						<li><a href="javascript:void(0)" onClick="choose_ma(2)">41</a></li>
						<li><a href="javascript:void(0)" onClick="choose_ma(3)">42</a></li>
						<li><a href="javascript:void(0)" onClick="choose_ma(4)">43</a></li>
					</ul>
				</div>
				<div id="number">
					<p>我要买：</p>
					<a class="reduce" href="javascript:void(0)"
						onclick="goods_buy(-1);" style="font-family: 微软雅黑;">-</a> <input
						id="quantity_306687" class="quantity" type="text"
						name="quantity_306687" maxlength="2" size="3" value="1"
						style="font-family: 微软雅黑;" /> <a class="add"
						href="javascript:void(0)" onclick="goods_buy(1);"
						style="font-family: 微软雅黑;">+</a>
					<div id="shop">
						<a href="order_order.action"
							title="下单完成后请务必于6个小时内付款，否则订单将自动取消(特殊抢购商品除外)"><img
							src="../images/goumai.png" /> </a> <a
							href="pro_cart.action?pro_number=${sessionScope.goodsinfo.pro_number}"
							title="下单完成后请务必于6个小时内付款，否则订单将自动取消(特殊抢购商品除外)"><img
							src="../images/jiaru.png" /> </a>
					</div>
				</div>
			</form>
		</div>

	</div>
	<div id="main">
		<div id="find">
			<div id="f1" style="cursor: pointer; font-family: 微软雅黑;">查看所有分类</div>

			<div id="f2" style="cursor: pointer; font-family: 微软雅黑;">男鞋</div>
			</li>
			<div id="f3">
				<a href="">篮球鞋</a> <a href="">跑步鞋</a> <a href="">网球鞋</a> <a href="">户外鞋</a>
			</div>
			<div id="f2" style="cursor: pointer; font-family: 微软雅黑;">女鞋</div>
			</li>
			<div id="f3">
				<a href="">篮球鞋</a> <a href="">跑步鞋</a> <a href="">网球鞋</a> <a href="">户外鞋</a>
				<a href="">运动生活鞋</a> <a href="">训练鞋</a> <a href="">羽毛球鞋</a> <a
					href="">凉鞋/拖鞋</a>
			</div>
			<div id="f2" style="cursor: pointer; font-family: 微软雅黑;">男装</div>
			</li>
			<div id="f3">
				<a href="">T恤</a> <a href="">比赛服</a> <a href="">卫衣</a> <a href="">外套/马甲</a>
				<a href="">裤装</a> <a href="">棉服</a>
			</div>
			<div id="f2" style="cursor: pointer; font-family: 微软雅黑;">女装</div>
			</li>
			<div id="f3">
				<a href="">T恤</a> <a href="">比赛服</a> <a href="">卫衣</a> <a href="">外套/马甲</a>
				<a href="">裤装</a> <a href="">棉服</a>
			</div>

		</div>
		<div id="info">
			<div id="info1">
				<div id="head_info1">
					<ul>
						<a onClick="click2(2)"><li id="li1">商品描述</li></a>
						<a onClick="click2(3,${sessionScope.goodsinfo.pro_number})"><li>商品评论</li></a>
						<a onClick="click2(4)"><li>购物须知</li></a>
					</ul>
				</div>
			</div>
			<div id="info2">
				<img src="../images/jieshao.png">
				<div id="jieshao_wenzi">
					<pre class="PD_desc"
						style="word-wrap: break-word; white-space: pre-wrap;">&nbsp;&nbsp;&nbsp;&nbsp;李宁云三代Smart智能减震跑鞋，通过鞋中底的智能芯片与小米运动APP相结合，可查阅运动记录、对落地方式进行监测，给出统计数据及相关建议帮助跑者提高、参与里程换购、给与购鞋指导。鞋子运用简洁的配色，简单大方。云三代跑鞋统一使用改善后的宽楦；满足不同人群穿着，带来舒适脚感。一体织网布结合无缝工艺，支撑与透气结合，带来舒适的跑步体验。鞋子上的李宁品牌LOGO,做工细致，彰显品牌魅力。鞋底采用橡胶+EVA复合底材质，耐磨防滑。 智能芯片 李宁智能跑鞋，内置智能芯片，记录跑程、热量消耗等数据，以技术改写你的跑步生活 √ 鞋面：纺织品 √ 鞋底：橡胶+EVA复合底 √ 通过鞋中底的智能芯片与小米运动APP相结合，可查阅运动记录、对落地方式进行监测，给出统计数据及相关建议帮助跑者提高、参与里程换购、给与购鞋指导 √ 一体织网布结合无缝工艺，支撑与透气结合，带来舒适的跑步体验 √ 鞋底采用橡胶+EVA复合底材质，耐磨防滑 √ 智能芯片</pre>
				</div>
				<div id="duizhaobiao">
					<img src="../images/duizhaobiao.png">
				</div>
			</div>
			<div id="info3" style="display: none">
				
			</div>
			<div id="info4" style="display: none">
				<img src="../images/xuzhi.png">
			</div>
		</div>
	</div>
</body>
</html>
