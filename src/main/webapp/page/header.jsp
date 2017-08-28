<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="float_head">
	<div id="list_title_inner">
		<div id="navigation_left">
			<span class="login">LI-NING&nbsp;&nbsp;李宁</span>
			<input id="UidHidden"  hidden="hidden" value="${sessionScope.usersinfo.u_id}" />
			<c:choose>
				<c:when test="${sessionScope.usersinfo.username != null}">
						 欢迎您,${sessionScope.usersinfo.username}
						 <a onClick="loginout()" id="loginout" href="javascript:void(0)">注销</a>
				</c:when>
				<c:otherwise>
					<a href="login.jsp" onclick="login()" id="loginaa">登录</a>
					<span class="row">|</span>
					<a href="register.jsp" onclick="register()">注册</a>
				</c:otherwise>
			</c:choose>

<script type="text/javascript">
    function searchInfo(index){
    	$("#lowPrice").val("");
		$("#highPrice").val("");
    	var pro_Name=$("#search").val();
		 $.post("findByType.action",{searchName:pro_Name,num:index},function(data){
			var val="";
			if(0 == data[1].length || null == data[1]){
				val+= '很抱歉，没有您所要的相关数据!! :)';
				$("#mainContent_center_center").html(val);
				$("#pageutil").text("当前第  "+1+"页/总共"+1+" 页");
   				$("#pageutil").attr("flag","findByType");
			}else{
				$.each(data[1], function(indexs,items){
					val+= '<div id="mainContent_center_center_div1" >'
	                    		+'<div id="mainContent_center_center_div1_1" onClick="getid('+items.pro_number+')">'
	                    	    +'<a href="pro_details.action?pro_number='+items.pro_number+'"><img src="../'+items.color.split(",")[0]+'" class="big_top" id="big_top1" style="display:block"></a>'
	                           +'<a href="pro_details.action?pro_number='+items.pro_number+'"><img src="../'+items.color.split(",")[1]+'" class="big_top" id="big_top2" style="display:none"></a>'
	                         	+'<a href="pro_details.action?pro_number='+items.pro_number+'"><img src="../'+items.color.split(",")[2]+'" class="big_top" id="big_top3" style="display:none"></a>'
	                           +' </div>'
	                            +'<div id="mainContent_center_center_div1_2" class="small_button">'
	                            +'<img src="../'+items.color.split(",")[0]+'" class="img1" id="img1_1" onMouseOver="show(1)">'
	                            +'<img src="../'+items.color.split(",")[1]+'" class="img2" id="img1_2" onMouseOver="show(2)">'
	                            +'<img src="../'+items.color.split(",")[2]+'" class="img3" id="img1_3" onMouseOver="show(3)">'
	                           +' </div>'
	                           +' <div id="mainContent_center_center_div1_3">'
	                        +items.pro_name
	                           +' </div>'
	                          +'  <div id="mainContent_center_center_div1_4">￥'+items.pro_tagprice+'</div>'
	               		     +'   </div>'
				});
	   				$("#mainContent_center_center").html(val);
	   				$("#pageutil").text("当前第  "+data[0].pageNo+"页/总共"+data[0].totalPages+" 页");
	   				$("#pageutil").attr("flag","findByType");
			}
   		},"json");
	}
$("#loginout").click(function(){
	$.ajax({
		type: "POST",
		url: "user_loginout.action",
		success: function(msg){			//查询成功后弹窗
			window.location.href="index.jsp";
			     			
		}
	});
});


var u_id=0;
u_id = $("#UidHidden").val();
//刷新收藏栏
$.ajax({
		type: "POST",
		url: "collectionById.action",
		data:"u_id="+u_id,
		dataType:"JSON",
		success: function(data){			//查询成功后弹窗
			var item = data;
			var valuee='';
			if(null ==item){
				valuee+="您暂时还没有收藏商品";
			}else{
				for(var i=0;i<item.length;i++){
					valuee +='<a href="pro_details.action?pro_number='+item[i].pro_number+'">'+
							 '<img src="../'+item[i].pro_img.split(",")[0]+'" style="width:90px; height:90px; margin-left:50px;"></a>'+
							 '<p style="color:#FFF; margin-left:50px;">'+item[i].pro_tagprice+'</p>';
				}
			}
			$("#r_view_hide #liulan").html(valuee);
		}
});

//刷新购物车
</script>

			<span class="row">|</span> <a href="user_mycart.action"> <span class="shopping_chart"></span>
				<span class="shopping_count">(<c:choose><c:when test="${sessionScope.cartList == null}">0</c:when><c:otherwise>${sessionScope.cartList.size()}</c:otherwise></c:choose>)</span>
				<span style="color: red;">${sessionScope.errorMsg}</span>
				<c:remove var="errorMsg" />
			</a>
		</div>
		<div id="navigation_right">
			<a href="myning_comment?u_id=${sessionScope.usersinfo.u_id}">会员中心</a> <span>|</span> <a href="#">消息中心</a>
		</div>
	</div>
	<div id="comm_logo_contain">
		<div id="comm_contain_left">
			<ul>
				<li><a href="index.jsp"><img src="../images/logo.png" /></a></li>
				<div style="background: rgb(76, 74, 70) none repeat scroll 0% 0%;"
					class="logo_inter"></div>
				<li><a class="li_a" style="color: #fff;" href="product.jsp">所有分类</a><img
					class="down_img" src="../images/content_down.png" />
					<div id="font_content—1" style="display: none;">
						<div class="sub_box">
							<style>
								#font_content—1  .sub_box .box_1 {
									float: left;
									border-left: 1px solid #ccc;
									margin-top: 40px;
									margin-left: 80px;
								}
								
								#font_content—1  .sub_box  .box_2 {
									float: left;
									border-left: 1px solid #ccc;
									margin-top: 40px;
								}
								
								#font_content—1	.sub_box  .box_3 {
									float: left;
									border-left: 1px solid #ccc;
									border-right: 1px solid #ccc;
									margin-top: 40px;
								}
								
								#font_content—1	.sub_box  a {
									font-size: 12px;
									color: rgb(64, 64, 64);
									display: block;
									margin-left: 20px;
									line-height: 20px;
								}
								
								#font_content—1  .boxAll {
									text-align: center;
									margin-left: 40px;
								}
								
								#font_content—1  .boxAll img {
									margin-left: 40px;
									margin-top: 30px;
								}
								
								#font_content—1  .logo_down {
									width: 92%;
									height: 123px;
									font-family: 'Microsoft YaHei UI';
									background: #CCC;
									text-align: center;
									margin-left: 74px;
									margin-top: 12px;
								}
								
								#font_content—1  .logo_down .first_img {
									margin-top: 16px;
									margin-left: 6px;
								}
								
								#font_content—1  .logo_down .last_img {
									margin-top: -14px;
									margin-left: 6px;
								}
							</style>
							<div style="width: 1348px; height: 540px; background: rgb(246, 246, 246); font-family: 'Microsoft YaHei UI'; text-align: left;">
								<div class="boxAll">
									<div class="box_1" style="width: 180px; height: 224px;">
										<a class="box_head" 
												style="font-size: 28px; color: red; margin-bottom: 20px;">男鞋</a>
										<a>跑步鞋</a> 
										<a href="http://www.baidu.com">篮球鞋</a>
										<a>休闲鞋</a>
									</div>
									<div class="box_2" style="width: 197px; height: 224px;">
										<a class="box2_head"
											style="font-size: 28px; color: red; margin-bottom: 20px;">女鞋</a>
										<a>跑步鞋</a> 
										<a href="http://www.baidu.com">篮球鞋</a>
										<a>休闲鞋</a>
									</div>
									<div class="box_2" style="width: 197px; height: 224px;">
										<a class="box_head"
											style="font-size: 28px; color: red; margin-bottom: 20px;">男装</a>
										<a>T恤/Polo衫/背心</a> <a>卫衣</a><a>羽绒服</a>
									</div>
									<div class="box_2" style="width: 179px; height: 224px;">
										<a class="box_head"
											style="font-size: 28px; color: red; margin-bottom: 20px;">女装</a>
										<a>T恤/Polo衫/背心</a> <a>卫衣</a><a>羽绒服</a>
									</div>
									<div class="box_2" style="width: 197px; height: 224px;">
										<a class="box_head"
											style="font-size: 28px; color: red; margin-bottom: 20px;">配件</a>
									</div>
									<div class="box_3" style="width: 197px; height: 224px;">
										<a class="box_head"
											style="font-size: 28px; color: red; margin-bottom: 20px;">器材</a>
									</div>
									<img src="../images/fl-0929_02.jpg" /> <img
										src="../images/fl-0929_04.jpg" /> <img
										src="../images/fl-0929_06.jpg" /> <img
										src="../images/fl-0929_08.jpg" /> <img
										src="../images/fl-0929_10.jpg" /> <img
										src="../images/fl-0929_12.jpg" /> <img
										src="../images/fl-0929_14.jpg" /> <img
										src="../images/fl-0929_16.jpg" />
								</div>

								<div class="logo_down">
									<img class="first_img" src="../images/logo_51.jpg" /> <img
										class="first_img" src="../images/logo_51.jpg" /> <img
										class="first_img" src="../images/logo_53.jpg" /> <img
										class="first_img" src="../images/logo_57.jpg" /> <img
										class="first_img" src="../images/logo_61.jpg" /> <img
										class="first_img" src="../images/logo_63.jpg" /> <img
										class="first_img" src="../images/logo_63.jpg" /> <br /> <img
										class="last_img" src="../images/logo_73.jpg" /> <img
										class="last_img" src="../images/logo_75.jpg" /> <img
										class="last_img" src="../images/logo_77.jpg" /> <img
										class="last_img" src="../images/logo_79.jpg" />
								</div>
							</div>
						</div>
					</div></li>
				<div style="background: rgb(76, 74, 70) none repeat scroll 0% 0%;" class="logo_inter"></div>
				<li>
					<a style="color: #fff;" class="li_a2">新品</a>
					<img class="down_img" src="../images/content_down.png" />
				</li>
				<div style="background: rgb(76, 74, 70) none repeat scroll 0% 0%;" class="logo_inter"></div>
				<li>
					<a class="li_a3" style="color: #fff;" href="#nanzi.html">男子</a>
					<img class="down_img" src="../images/content_down.png" />
					<div id="font_content—3"
						style="display: none; background: rgb(246, 246, 246);">
						<div class="sub_box">
							<style>
								#font_content—3  .sub_box .box_1 {
									float: left;
									border-left: 1px solid #ccc;
									margin-top: 40px;
									margin-left: 80px;
									color: black;
								}
								
								#font_content—3  .sub_box  .box_2 {
									float: left;
									border-left: 1px solid #ccc;
									margin-top: 40px;
								}
								
								#font_content—3	.sub_box  .box_3 {
									float: left;
									border-left: 1px solid #ccc;
									border-right: 1px solid #ccc;
									margin-top: 40px;
								}
								
								#font_content—3	.sub_box  a {
									font-size: 12px;
									color: black;
									display: block;
									margin-left: 20px;
									line-height: 20px;
								}
							</style>
							<div style="width: 1349px; height: 354px; background: rgb(246, 246, 246); font-family: 'Microsoft YaHei UI'; text-align: left;">
								<div class="box_1" style="width: 180px; height: 224px;">
									<a class="box_head"
										style="font-size: 28px; color: red; margin-bottom: 20px;">主推专题</a>
									<a>新品上市</a> <a>热卖商品</a>
								</div>
								<div class="box_2" style="width: 197px; height: 224px;">
									<a class="box2_head"
										style="font-size: 28px; color: red; margin-bottom: 20px;">鞋类</a>
									<a>跑步鞋</a> <a>篮球鞋</a><a>休闲鞋</a>
								</div>
								<div class="box_2" style="width: 197px; height: 224px;">
									<a class="box_head"
										style="font-size: 28px; color: red; margin-bottom: 20px;">服装</a>
									<a>T恤/Polo衫/背心</a> <a>卫衣</a> <a>羽绒服</a><a>其他服装</a>
								</div>
								
								<div class="box_3" style="width: 197px; height: 224px;">
									<a class="box_head"
										style="font-size: 28px; color: red; margin-bottom: 20px;">系列</a>
									<a>lining-新活力系列</a> <a>CBA-球迷专属定制</a> <a>智跑生活触手可得</a> <a>CBA-新赛季新装备</a>
								</div>
							</div>
						</div>
					</div>
				</li>
				<div style="background: rgb(76, 74, 70) none repeat scroll 0% 0%;" class="logo_inter"></div>
				<li>
					<a class="li_a4" style="color: #fff;" href="#nvzi.html">女子</a>
					<img class="down_img" src="../images/content_down.png" />
					<div id="font_content—4" style="display: none;">
						<div class="sub_box">
							<style>
								#font_content—4  .sub_box .box_1 {
									float: left;
									border-left: 1px solid #ccc;
									margin-top: 40px;
									margin-left: 80px;
									color: black;
								}
								#font_content—4  .sub_box  .box_2 {
									float: left;
									border-left: 1px solid #ccc;
									margin-top: 40px;
								}
								#font_content—4	.sub_box  .box_3 {
									float: left;
									border-left: 1px solid #ccc;
									border-right: 1px solid #ccc;
									margin-top: 40px;
								}
								#font_content—4	.sub_box  a {
									font-size: 12px;
									color: black;
									display: block;
									margin-left: 20px;
									line-height: 20px;
								}
							</style>
							<div style="width: 1349px; height: 354px; background: rgb(246, 246, 246); font-family: 'Microsoft YaHei UI'; text-align: left;">
								<div class="box_1" style="width: 180px; height: 224px;">
									<a class="box_head"
										style="font-size: 28px; color: red; margin-bottom: 20px;">主推专题</a>
									<a>新品上市</a> <a>热卖商品</a>
								</div>
								<div class="box_2" style="width: 197px; height: 224px;">
									<a class="box2_head"
										style="font-size: 28px; color: red; margin-bottom: 20px;">鞋类</a>
									<a>跑步鞋</a> <a>运动生活鞋</a><a>休闲鞋</a>
								</div>
								<div class="box_2" style="width: 197px; height: 224px;">
									<a class="box_head"
										style="font-size: 28px; color: red; margin-bottom: 20px;">服装</a>
									<a>T恤/Polo衫/背心</a> <a>卫衣</a><a>羽绒服</a> <a>其他服装</a>
								</div>
								<div class="box_3" style="width: 197px; height: 224px;">
									<a class="box_head"
										style="font-size: 28px; color: red; margin-bottom: 20px;">系列</a>
									<a>lining-新活力系列</a> <a>型走冬日 温暖一冬</a> <a>智跑生活 触手可得</a> <a>LNG-型自首尔</a>
								</div>
							</div>
						</div>
					</div></li>
				<div style="background: rgb(76, 74, 70) none repeat scroll 0% 0%;" class="logo_inter"></div>
				<li>
					<a style="color: #fff;" class="li_a5">运动</a>
					<img class="down_img" src="../images/content_down.png" />
					<div id="font_content—5" style="display: none;">
						<div class="sub_box">
							<style>
								#font_content—5 .sub_box .box_1 {
									float: left;
									border-left: 1px solid #ccc;
									border-right: 1px solid #ccc;
									margin-top: 30px;
									margin-left: 80px;
								}
								
								#font_content—5 .sub_box .box_2 {
									float: left;
									border-right: 1px solid #ccc;
									margin-top: 30px;
									margin-left: 20px;
								}
								
								#font_content—5	.sub_box  a {
									font-size: 12px;
									display: block;
									margin-left: 20px;
									line-height: 20px;
								}
								
								#font_content—5	.sub_box p {
									font-family: "Microsoft YaHei UI";
									font-size: 20px;
									font-weight: 600;
									color: red;
								}
							</style>
							<div style="width: 1349px; height: 200px; background: rgb(246, 246, 246); font-family: 'Microsoft YaHei UI'; text-align: center;">
								<div class="box_1" style="width: 200px; height: 154px;">
									<a>
										<img with="135px" height="115px" alt="篮球" src="../images/basketball.png" />
									</a>
									<p style="font-family: 'Microsoft YaHei UI';">篮球</p>
								</div>
								<div class="box_2" style="width: 200px; height: 154px;">
									<a> 
										<img with="135px" height="115px" alt="跑步" src="../images/run.png" />
									</a>
									<p style="font-family: 'Microsoft YaHei UI';">跑步</p>
								</div>
								<div class="box_2" style="width: 200px; height: 154px;">
									<a> 
										<img with="135px" height="115px" alt="运动生活" src="../images/lnss.png" />
									</a>
									<p style="font-family: 'Microsoft YaHei UI';">运动生活</p>
								</div>
								<div class="box_2" style="width: 200px; height: 154px;">
									<a> 
										<img with="135px" height="115px" alt="训练" src="../images/life.png" />
									</a>
									<p style="font-family: 'Microsoft YaHei UI';">训练</p>
								</div>
								<div class="box_2" style="width: 200px; height: 154px;">
									<a> 
										<img with="135px" height="115px" alt="羽毛球" src="../images/5846D07192F71FBADD5311F16C4BB81C.png" />
									</a>
									<p style="font-family: 'Microsoft YaHei UI';">羽毛球</p>
								</div>
							</div>
						</div>
					</div></li>
			</ul>
		</div>
		<div id="comm_contain_right">
			<input type="text" name="search" id="search" /> <span> <img
				style="position: absolute; top: 0px;" src="../images/search.png"
				onclick="searchInfo()" />
			</span>
		</div>
	</div>
	
	<!--公共登陆注册层-->
	<div id="login_main" class="comm_pop" style="top:48px;display:none;">

	<div class="eb_div" style="width:640px;background:white;">
	
		<div class="eb_title">
			<span style="font-size:14px;font-weight: bold;line-height: 32px;">登录/注册</span>
			<span class="eb_close" title="关闭" onclick="customer.close_login();">关闭</span>
		</div>
		<div class="eb_opencont" style="height:100%;">
			<div id="cust_main">
				<h3 id="h3_open_reglogin">
					<span class="span_open_login now" onclick="customer.switchover(this, 1);">登 录</span>
					<span class="span_open_reg" onclick="customer.switchover(this, 2);">注 册</span>
				</h3>
				<div id="span_open_login">
					<div class="blank8"></div>
					<p class="pspt_back_msg" id="login_login_error"></p>
					<p class="p_item">
						<label class="itemtitle">用户名：</label>
						<input maxlength="60" name="userName" id="login_userName" class="txt" type="text">
						<span class="pspt_msg" id="login_userName_error"></span>
					</p>
					<p class="p_item">
						<label class="itemtitle">密码：</label>
						<input maxlength="30" name="pwd" id="login_pwd" class="txt" type="password">
						<span class="pspt_msg" id="login_pwd_error"></span>
					</p>
					<p class="p_item p_item_vcode">
						<label class="itemtitle">验证码：</label>
						<input maxlength="4" style="width:77px;float:left;margin-right:5px;" name="yzm" id="login_yzm" class="txt" type="text">
                                                <span style="float:left;">
							<img onclick="get_yzm_comm(this);" src="%E6%9D%8E%E5%AE%81%E5%AE%98%E6%96%B9%E7%BD%91%E7%AB%99_files/1f69e0d83e2b2632655e000adb554eea.jpg" id="yzm_img_pop" title="看不清楚，点击获得新图片" style="cursor:pointer;height: 39px;">
						</span>&nbsp;
						<span class="pspt_msg" id="login_yzm_error"></span>
						<input name="mobile" id="mobile" value="" type="hidden">
					</p>
					<p class="p_item p_btn">
						<button class="eb_btn login_btn" id="login_btn_res" type="button" onclick="customer.login(2);"><h3>登 录</h3></button>&nbsp;&nbsp;
						<a class="c_g" target="_blank" href="http://store.lining.com/shop/getpwd.php">忘记密码？</a>
					</p>
					<div class="open_otherlogin" style="margin-left:54px;margin-top:24px;">
						<h4 class="h4_otherlogin">您也可以使用合作网站帐号登录</h4>
                        <div class="union_login">
						    <a href="http://store.lining.com/shop/unionlogin.php?uniontype=alipay" class="union_alipay upay">支付宝</a>
						    <a href="http://store.lining.com/shop/unionlogin.php?uniontype=sina" class="union_sina upay">新浪微博</a>
						    <a href="http://store.lining.com/shop/unionlogin.php?uniontype=qq" class="union_qq upay">QQ</a>
						    <a href="http://store.lining.com/shop/unionlogin.php?uniontype=renren" class="union_renren upay">人人网</a>
						    <a href="http://store.lining.com/shop/unionlogin.php?uniontype=wanlitong" class="union_wanlitong upay">平安万里通</a>
						    <a href="http://store.lining.com/shop/unionlogin.php?uniontype=netease" class="union_netease upay">网易</a>
						    <a href="http://store.lining.com/shop/unionlogin.php?uniontype=weixin" class="union_weixin wpay"></a>
    
						</div>
					</div>
				</div>
				<div id="span_open_reg" class="hide">
					<div class="blank8"></div>
					<p class="p_item">
						<label class="itemtitle"><span style="color:red;">*</span>&nbsp;用户名：</label>
						<input autocomplete="off" maxlength="20" name="reg_userName" id="reg_userName" class="txt" type="text">
						<span class="pspt_msg" id="reg_userName_error"></span>
						<span class="reg-newUser-notice"></span>
					</p>
					<p class="p_item">
						<label class="itemtitle"><span style="color:red;">*</span>&nbsp;请设置密码：</label>
						<input maxlength="20" name="reg_pwd" id="reg_pwd" class="txt" type="password">
						<span class="pspt_msg" id="reg_pwd_error"></span>
						<span class="reg-newUser-notice"></span>
					</p>
					<p class="p_item">
						<label class="itemtitle"><span style="color:red;">*</span>&nbsp;请确认密码：</label>
						<input maxlength="20" name="reg_pwd2" id="reg_pwd2" class="txt" type="password">
						<span class="pspt_msg" id="reg_pwd2_error"></span>
						<span class="reg-newUser-notice"></span>
					</p>
					<p class="p_item">
						<label class="itemtitle"><span style="color:red;">*</span>&nbsp;验证手机：</label>
						<input maxlength="11" name="reg_mobile" id="reg_mobile" class="txt" type="text">
						<span class="pspt_msg" id="reg_mobile_error"></span>
						<span class="reg-newUser-notice"></span>
					</p>
					<p class="p_item">
                        <label class="itemtitle"><span style="color:red;">*</span>&nbsp;图案验证码：</label>
                        <input maxlength="8" name="reg_pic" id="reg_pic" class="txt" style="width:143px;float:left;margin-right:5px;" type="text">
                        <img onclick="get_yzm_comm(this);" src="%E6%9D%8E%E5%AE%81%E5%AE%98%E6%96%B9%E7%BD%91%E7%AB%99_files/88623c1e858009d8da93691fe5b342f2.jpg" id="yzm_reg_pop" title="看不清楚，点击获得新图片" style="cursor:pointer;height:33px;float:left;">
                        &nbsp;
                        <span class="pspt_msg" id="reg_pic_error"></span>
                        <span class="reg-newUser-notice"></span>
                    </p>
					<p class="p_item">
						<label class="itemtitle"><span style="color:red;">*</span>&nbsp;短信验证码：</label>
						<input maxlength="20" name="reg_smsCode" id="reg_smsCode" class="txt" style="width:143px;" type="password">
						<span id="reg_smsSend" onclick="sms.sendSms();">免费获取验证码</span>
						<span class="pspt_msg" id="reg_smsCode_error"></span>
					</p>
					<p class="p_item">
						<label class="itemtitle">邮箱：</label>
						<input maxlength="80" name="reg_email" id="reg_email" class="txt" type="text">
						<span class="pspt_msg_note">备注：邮箱提供后不可修改</span>
						<span class="reg-newUser-notice"></span>
					</p>
					
					<p class="p_item p_btn">
						</p><div style="margin:0 0 16px 120px;">
							<input checked="checked" name="layer_read_rule" style="vertical-align:middle;" type="checkbox">
							&nbsp;
							阅读《<a href="http://store.lining.com/shop/help-18.html" target="_blank" style="color:#0778f9;">李宁官方网站服务协议</a>》
						</div>
						<button class="reg_btn" type="button" onclick="customer.verify_register(2,2);">
							立即注册
						</button>
					<p></p>
				</div>
			</div>
			<div class="blank0"></div>
		</div>
	</div>
</div>
<!-- 以上为公共登录注册层 -->

	<div id="r_logo_hide"
		style="margin-top: 252px; display: none; float: right;">
		<img src="../images/hide_bar_3.png" />
	</div>
	<div id="r_logo" style="display: block;">
		<img src="../images/logor.png" />
	</div>

	<div id="content" style="display: block;">
		<div id="r_show" class="bar">
			<img src="../images/show_bar_1.png" />
		</div>
		<div class="solid">
			<img src="../images/solid.png" />
		</div>
		<div id="r_lining" class="bar">
			<img src="../images/my_lining_1.png" />
			<div id="r_lining_hide"
				style="display: none; width: 216px; height: 180px; margin-left: -205px; margin-top: -40px; background: url(../images/login_background.png) no-repeat;">
				<p class="personInfo">个人信息</p>
				<img class="hide_loginimg1" src="../images/login.png" />
				<img class="hide_loginimg2" src="../images/register.png" />
			</div>
		</div>
		<div class="solid">
			<img src="../images/solid.png" />
		</div>
		<div id="r_cart" class="bar">
			<img src="../images/cart_icon_1.png" />
			<span>(<c:choose><c:when test="${sessionScope.cartList == null}">0</c:when><c:otherwise>${sessionScope.cartList.size()}</c:otherwise></c:choose>)</span>
		</div>
		<!--购物车隐藏栏-->
		<div id="r_cart_hide"
			style="display: none; width: 213px; height: 577px; background: url(../images/cart_background_1.png);">
			<div id="r_cart_close"
				style="width: 30px; height: 30px; background: #CCC; text-align: center; float: right;">
				<img style="margin-top: 3px;" src="../images/close.png" />
			</div>
			<div style="width: 100%; height: 60px; text-align: center; float: right;">
				<p style="color: #FFF; line-height: 20px; font-size: 16px; margin-top: 20px; font-weight: bold;">购物车</p>
			</div>
			<div
				style="width: 100%; height: 46px; float: right; margin-top: 20px;">
				<input type="checkbox"
					style="float: left; width: 10px; margin-left: 8px; margin-top: -12px;" />
				<p
					style="float: left; margin-left: 4px; margin-top: -16px; color: #FFF;">全选</p>
				<img style="margin-top: -16px; margin-right: 6px; float: right;"
					src="../images/no_cart_more.png" /> <img style="margin-top: 18px;"
					src="../images/cart_line.png" />
			</div>

			<div style="width:89%;height: 300px; margin-top: 8px; margin-right: 2px; float:left;">
				<c:forEach items="${sessionScope.cartList}" var="cart">
				<div style="margin:0px 0 0 0;color:white;">
					<table cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td width="12%">
									<input type="checkbox" checked="" pid="347879" flg="1" name="check_slide_goods">
								</td>
								<td width="*">
									<img width="35" height="35" src="../${cart.color.split(',')[0]}" style="width:30px;height:30px;"/>
								</td>
								<td width="29%">
									<div style="text-align:left;margin-left:14px;">暗红...</div>
									<div style="height:1px;"></div>
									<div style="text-align:left;margin-left:14px;">42</div>
								</td>
								<td width="15%">1</td>
								<td width="28%">￥${cart.pro_price}</td>
							</tr>
						</tbody>
					</table>
				</div>
				</c:forEach>
			</div>
			<div style="width: 94%; height: 26px; background: #CCC; float: right; margin-top: 0px; margin-right: 6px; font-size: 14px;">
				<span style="float: left; margin-left: 6px; margin-top: 2px;">已选择
				<span style="color: red;">0</span>件商品
				</span> 
				<span style="float: right; margin-right: 6px; margin-top: 2px; color: red;">￥0.00</span>
			</div>
			<div style="width: 100%; height: 60px; float: right; margin-top: 10px;">
				<img src="../images/no_checkout.png" />
			</div>

		</div>

		<div class="solid">
			<img src="../images/solid.png" />
		</div>
		<div id="account" class="bar">
			<img src="../images/account_1.png" />
			<div id="r_account_hide"
				style="display: none; width: 216px; height: 180px; margin-left: -206px; margin-top: -50px; background: url(../images/login_background.png) no-repeat;">
				<p class="personInfo">个人信息</p>
				<a><img class="hide_loginimg1" src="../images/login.png" /></a> <a><img
					class="hide_loginimg2" src="../images/register.png" /></a>
			</div>
		</div>
		<div class="solid">
			<img src="../images/solid.png" />
		</div>
		<div id="r_collect" class="bar">
			<img src="../images/collect_1.png" />
			<div id="r_collect_hide"
				style="display: none; width: 216px; height: 180px; margin-left: -206px; margin-top: -50px; background: url(../images/login_background.png) no-repeat;">
				<p class="personInfo">个人信息</p>
				<a><img class="hide_loginimg1" src="../images/login.png" /></a> <a><img
					class="hide_loginimg2" src="../images/register.png" /></a>
			</div>
			
		</div>
		<div class="solid">
			<img src="../images/solid.png" />
		</div>
		<div id="r_view" class="bar">
			<img src="../images/view_1.png" />

		</div>

		<div id="r_view_hide"
			style="display: none; width: 213px; height: 577px; background: url(../images/cart_background_1.png); text-align: center;">
			<div id="r_view_close"
				style="width: 30px; height: 30px; background: #CCC; text-align: center; float: right;">
				<img style="margin-top: 3px;" src="../images/close.png" />
			</div>
			<div
				style="width: 100%; height: 60px; text-align: center; float: right;">
				<p
					style="color: #FFF; line-height: 20px; font-size: 16px; margin-top: 20px; font-weight: bold;">我的收藏</p>
			</div>
			<div id="liulan" style="width:75%;height:106px;float:left;margin-top:10px;">
			
			</div>
		</div>

		<div class="solid">
			<img src="../images/solid.png" />
		</div>
		<div id="r_service" class="bar">
			<img src="../images/service_1.png" />
			<div id="r_service_hide"
				style="display: none; width: 117px; height: 66px; margin-left: -110px; margin-top: -60px; text-align: center; background: url(../images/server_background.png) no-repeat;">
				<img style="margin-left: -66px;" src="../images/service.png" />
				<p style="margin-top: -36px; margin-left: 20px; line-height: 14px;">
					售前咨询<br />---------<br />售后咨询
				</p>
			</div>
		</div>
		<div class="solid">
			<img src="../images/solid.png" />
		</div>
		<div id="r_scan" class="bar">
			<img src="../images/scan_ground.png" />
			<div id="r_scan_hide"
				style="display: none; width: 150px; height: 142px; margin-left: -140px; margin-top: -70px;">
				<img src="../images/scan.png" />
			</div>
		</div>
		<div class="solid">
			<img src="../images/solid.png" />
		</div>
		<div id="r_top" class="bar" style="display: none;">
			<img style="margin-top: 8px;" src="../images/top_1.png" />
			<p style="color: #FFF;">回到</p>
			<p style="color: #FFF; margin-top: -6px;">顶部</p>
		</div>

	</div>
</div>
