<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="float_head">
	<div id="list_title_inner">
    	<div id="navigation_left">
        	<span class="login">LI-NING&nbsp;&nbsp;李宁</span>
        	<c:choose>
					<c:when test="${sessionScope.usersinfo.username != null}">
						 欢迎您,${sessionScope.usersinfo.username}
						 <a onClick="loginout()" id="loginout" href="javascript:void(0)">注销</a>
					</c:when>
					<c:otherwise>
						<a href="login.jsp"	onclick="login()" id="loginaa">登录</a> 
				 		<span class="row">|</span>
				 		<a	href="register.jsp" onclick="register()">注册</a> 
					</c:otherwise>
			</c:choose>
        	<script type="text/javascript">
        		function searchInfo(firstSelect,num){
				var search=$.trim($("#search").val());
					$.post("product.do",{oop:"searchInfo",search:search,firstSelect:firstSelect,num:num},function(data){
					var val="";
   					$.each(data.rows, function(indexs,items){
					val+= '<div id="mainContent_center_center_div1">'
                    		+'<div id="mainContent_center_center_div1_1" onClick="getid('+items.pro_number+')">'
                    	    +'<img src="'+items.color.split(",")[0]+'" class="big_top" id="big_top1" style="display:block">'
                           +' <img src="'+items.color.split(",")[1]+'" class="big_top" id="big_top2" style="display:none">'
                         	+'<img src="'+items.color.split(",")[2]+'" class="big_top" id="big_top3" style="display:none">'
                           +' </div>'
                            +'<div id="mainContent_center_center_div1_2" class="small_button">'
                            +'<img src="'+items.color.split(",")[0]+'" class="img1" id="img1_1" onMouseOver="show(1)">'
                            +'<img src="'+items.color.split(",")[1]+'" class="img2" id="img1_2" onMouseOver="show(2)">'
                            +'<img src="'+items.color.split(",")[2]+'" class="img3" id="img1_3" onMouseOver="show(3)">'
                           +' </div>'
                           +' <div id="mainContent_center_center_div1_3">'
                        +items.pro_name
                           +' </div>'
                          +'  <div id="mainContent_center_center_div1_4">￥'+items.pro_tagprice+'</div>'
               		     +'   </div>'
			});
   				$("#mainContent_center_center").html(val);
   				$("#pageutil").text("当前第  "+data.pageutil.pageNo+"页/总共"+data.pageutil.totalPages+" 页");
   				$("#pageutil").attr("flag","searchInfo");
   			},"json");
	}
	
        	
        	$("#loginout").click(function(){
				//alert("----13");
				$.ajax({
		   			type: "POST",
		   			url: "login.do",
		   			data: "oop=loginout",
		   			success: function(msg){			//查询成功后弹窗
		     			alert( "注销成功---");
		     			window.location.href="index.jsp";
		     			
		   			}
				});
				
				
   		
   		
			});
        	$.ajax({
   			type: "POST",
   			url: "Collection.do",
   			data: "col=findAllcol",
   			dataType:"JSON",
   			success: function(msg){			//查询成功后弹窗
   				var item = msg.collectList;
   				var valuee='';
				
   				for(var i=0;i<item.length;i++){
   					valuee +='<img src="'+item[i].pro_img.split(",")[0]+'" onClick="getid('+item[i].pro_number+')" style="width:90px; height:90px; margin-left:50px;">'+
                		'<p style="color:#FFF; margin-left:62px; margin-top:10px">￥'+item[i].pro_tagprice+'</p>';
   				}
   				$("#r_view_hide   #liulan").html(valuee);
   			}
   		});
        	</script>

            <span class="row">|</span>
			<a>
				<span class="shopping_chart"></span> 
				<span class="shopping_count">(0)</span>
			</a>
        </div>
        <div id="navigation_right">
        	<a href="MyNing.jsp">会员中心</a>
           	<span>|</span>
            <a href="#">会员俱乐部</a>
        </div>
    </div>
    <div id="comm_logo_contain">
    	<div id="comm_contain_left">
        	<ul>
            	<li><a href="index.jsp"><img src="../images/logo.png"/></a></li>
                <div style="background: rgb(76, 74, 70) none repeat scroll 0% 0%;" class="logo_inter"></div>
                <li><a class="li_a" style="color:#fff;" href="product.do?oop=findBypage&num=1">所有分类</a><img class="down_img" src="../images/content_down.png"/>
                	<div id="font_content—1" style="display:none;">
                	<div class="sub_box">
                    	<meta content="text/html" http-equiv="content-type" charset="utf-8" />
                        <title>所有分类</title>
                        <style>
							 						 
							 #font_content—1  .sub_box .box_1{
								float:left;
								border-left:1px solid #ccc;
								margin-top:40px;
								margin-left:80px;
							}
							#font_content—1  .sub_box  .box_2{
								float:left;
								border-left:1px solid #ccc;
								margin-top:40px;
							}
							#font_content—1	.sub_box  .box_3{
								float:left;
								border-left:1px solid #ccc;
								border-right:1px solid #ccc;
								margin-top:40px;
							}
							#font_content—1	.sub_box  a{
								font-size:12px;
								color:rgb(64,64,64);
								display:block;
								margin-left:20px;
								line-height:20px;
							}
							#font_content—1  .boxAll{
								text-align:center;
								margin-left:40px;
							}
							#font_content—1  .boxAll img{
								margin-left:40px;
								margin-top:30px;								
							}
							#font_content—1  .logo_down{
								width:92%;
								height:123px; 
								font-family:'Microsoft YaHei UI';
								background:#CCC;
								text-align:center;
								margin-left:74px;
								margin-top:12px;
							}
							#font_content—1  .logo_down .first_img{
								margin-top:16px;
								margin-left:6px;
							}
							#font_content—1  .logo_down .last_img{
								margin-top:-14px;
								margin-left:6px;
							}
							
                        </style>
                        <div style="width:1348px; height:540px;background:rgb(246,246,246); font-family:'Microsoft YaHei UI'; text-align:left;">
                        	<div  class="boxAll" >
                            	<div class="box_1" style="width:180px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">男鞋</a>
                                    <a>跑步鞋</a>
                                    <a href="http://www.baidu.com">篮球鞋</a>
                                    <a>运动生活鞋</a>
                                    <a>网球鞋</a>
                                    <a>都市轻运动鞋</a>
                                    <a>训练鞋</a>
                                    <a>羽毛球鞋</a>
                                    <a>户外鞋</a>
                                    <a>凉鞋/拖鞋</a>
                                </div>
                                <div class="box_2" style="width:197px; height:224px;">
                                	<a class="box2_head" style="font-size:28px; color:red; margin-bottom:20px;">女鞋</a>
                                    <a>跑步鞋</a> 
                                    <a>运动生活鞋</a>
                                    <a>训练鞋</a>
                                    <a>网球鞋</a>
                                    <a>都市轻运动鞋</a>
                                    <a>羽毛球鞋</a>
                                    <a>户外鞋</a>
                                    <a>凉鞋/拖鞋</a>
                                </div>
                                <div class="box_2" style="width:197px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">男装</a>
                                    <a>T恤/Polo衫/背心</a>
                                    <a>卫衣</a>
                                    <a>外套/马甲</a>
                                    <a>比赛服</a>
                                    <a>紧身服</a>
                                    <a>裤装</a>
                                    <a>棉装</a>
                                    <a>羽绒服</a>
                                </div>
                                <div class="box_2" style="width:179px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">女装</a>
                                    <a>T恤/Polo衫/背心</a>
                                    <a>卫衣</a>
                                    <a>外套/马甲</a>
                                    <a>紧身服</a>
                                    <a>裙装</a>
                                    <a>裤装</a>
                                    <a>棉装</a>
                                    <a>羽绒服</a>
                                    <a>其他服装</a>
                                </div>
                                <div class="box_2" style="width:197px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">配件</a>
                                    <a>袜子</a>
                                    <a>包</a>
                                    <a>帽子</a>
                                    <a>护具</a>
                                    <a>手套/围巾</a>
                                </div>
                                <div class="box_3" style="width:197px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">器材</a>
                                    <a>羽毛球拍</a>
                                    <a>羽毛球</a>
                                    <a>羽毛球配件</a>
                                    <a>乒乓球拍</a>
                                    <a>乒乓套胶</a>
                                    <a>乒乓球台</a>
                                    <a>乒乓球</a>
                                </div>
                                
                                    <img  src="../images/fl-0929_02.jpg"/>
                                    <img  src="../images/fl-0929_04.jpg"/>
                                    <img  src="../images/fl-0929_06.jpg"/>
                                    <img  src="../images/fl-0929_08.jpg"/>
                                    <img  src="../images/fl-0929_10.jpg"/>
                                    <img  src="../images/fl-0929_12.jpg"/>
                                    <img  src="../images/fl-0929_14.jpg"/>
                                    <img  src="../images/fl-0929_16.jpg"/>
                                 
                            </div>
                            
                            <div  class="logo_down">
                            	<img  class="first_img" src="../images/logo_51.jpg"/>
                                <img  class="first_img" src="../images/logo_51.jpg"/>
                                <img  class="first_img" src="../images/logo_53.jpg"/>
                                <img  class="first_img" src="../images/logo_57.jpg"/>
                                <img  class="first_img" src="../images/logo_61.jpg"/>
                                <img  class="first_img" src="../images/logo_63.jpg"/>
                                <img  class="first_img" src="../images/logo_63.jpg"/>
                                <br/>
                                <img  class="last_img" src="../images/logo_73.jpg"/>
                                <img  class="last_img" src="../images/logo_75.jpg"/>
                                <img  class="last_img" src="../images/logo_77.jpg"/>
                                <img  class="last_img" src="../images/logo_79.jpg"/>
                            </div>
                        </div>
                    </div>
                </div>
                </li>
                <div style="background: rgb(76, 74, 70) none repeat scroll 0% 0%;" class="logo_inter"></div>
                <li><a style="color:#fff;" class="li_a2">新品</a><img class="down_img" src="../images/content_down.png"/></li>
                <div style="background: rgb(76, 74, 70) none repeat scroll 0% 0%;" class="logo_inter"></div>
                <li><a class="li_a3" style="color:#fff;" href="#nanzi.html">男子</a><img class="down_img" src="../images/content_down.png"/>
                	<div id="font_content—3" style="display:none;background:rgb(246,246,246);">
                	<div class="sub_box">
                    	<meta content="text/html" http-equiv="content-type" charset="utf-8" />
                        <title>男子</title>
                        <style>
							 
							 #font_content—3  .sub_box .box_1{
								float:left;
								border-left:1px solid #ccc;
								margin-top:40px;
								margin-left:80px;
							}
							#font_content—3  .sub_box  .box_2{
								float:left;
								border-left:1px solid #ccc;
								margin-top:40px;
							}
							#font_content—3	.sub_box  .box_3{
								float:left;
								border-left:1px solid #ccc;
								border-right:1px solid #ccc;
								margin-top:40px;
							}
							#font_content—3	.sub_box  a{
								font-size:12px;
								color:rgb(64,64,64);
								display:block;
								line-height:20px;
								text-align:left;
							}
                        </style>
                        <div style="width:1349px;height:354px;background:rgb(246,246,246);font-family:'宋体';">
                            	<div class="box_1" style="width:180px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">主推专题</a>
                                    <a>新品上市</a>
                                    <a>热卖商品</a>
                                    <a>炫彩中长袜</a>
                                    <a>篮球大尺码专区</a>
                                </div>
                                <div class="box_2" style="width:197px; height:224px;">
                                	<a class="box2_head" style="font-size:28px; color:red; margin-bottom:20px;">鞋类</a>
                                    <a>跑步鞋</a> 
                                    <a>篮球鞋</a>
                                    <a>运动生活鞋</a>
                                    <a>网球鞋</a>
                                    <a>都市轻运动鞋</a>
                                    <a>训练鞋</a>
                                    <a>羽毛球鞋</a>
                                    <a>户外鞋</a>
                                    <a>凉鞋/拖鞋</a>
                                </div>
                                <div class="box_2" style="width:197px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">服装</a>
                                    <a>T恤/Polo衫/背心</a>
                                    <a>卫衣</a>
                                    <a>外套/马甲</a>
                                    <a>比赛服</a>
                                    <a>紧身服</a>
                                    <a>裤装</a>
                                    <a>棉装</a>
                                    <a>羽绒服</a>
                                    <a>其他服装</a>
                                </div>
                                <div class="box_2" style="width:197px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">配件</a>
                                    <a>袜子</a>
                                    <a>包</a>
                                    <a>帽子</a>
                                    <a>护具</a>
                                    <a>手套/围巾</a>
                                    <a>配饰</a>
                                </div>
                                <div class="box_3" style="width:197px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">系列</a>
                                    <a>lining-新活力系列</a>
                                    <a>CBA-球迷专属定制</a>
                                    <a>智跑生活触手可得</a>
                                    <a>CBA-新赛季新装备</a>
                                    <a>李宁弓虽篮球鞋</a>
                                    <a>LNG-型自首尔</a>
                                </div>
                            
                        </div>
                    </div>
                </div>
                </li>
                <div style="background: rgb(76, 74, 70) none repeat scroll 0% 0%;" class="logo_inter"></div>
                <li><a class="li_a4" style="color:#fff;" href="#nvzi.html">女子</a><img class="down_img" src="../images/content_down.png"/>
                	<div id="font_content—4" style="display:none;">
                	<div class="sub_box">
                    	<meta content="text/html" http-equiv="content-type" charset="utf-8" />
                        <title>女子</title>
                        <style> 
							#font_content—4  .sub_box .box_1{
								float:left;
								border-left:1px solid #ccc;
								margin-top:40px;
								margin-left:80px;
                                color:black;
							}
							#font_content—4  .sub_box  .box_2{
								float:left;
								border-left:1px solid #ccc;
								margin-top:40px;
							}
							#font_content—4	.sub_box  .box_3{
								float:left;
								border-left:1px solid #ccc;
								border-right:1px solid #ccc;
								margin-top:40px;
							}
                            
							#font_content—4	.sub_box  a{
								font-size:12px;
								color:black;
								display:block;
								margin-left:20px;
								line-height:20px;
							}
                        </style>
                        <div style="width:1349px; height:354px;background:rgb(246,246,246); font-family:'Microsoft YaHei UI'; text-align:left;">
                            	<div class="box_1" style="width:180px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">主推专题</a>
                                    <a>新品上市</a>
                                    <a>热卖商品</a>  
                                </div>
                                <div class="box_2" style="width:197px; height:224px;">
                                	<a class="box2_head" style="font-size:28px; color:red; margin-bottom:20px;">鞋类</a>
                                    <a>跑步鞋</a> 
                                    <a>运动生活鞋</a>
                                    <a>训练鞋</a>
                                    <a>网球鞋</a>
                                    <a>都市轻运动鞋</a> 
                                    <a>羽毛球鞋</a>
                                    <a>户外鞋</a>
                                    <a>凉鞋/拖鞋</a>
                                </div>
                                <div class="box_2" style="width:197px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">服装</a>
                                    <a>T恤/Polo衫/背心</a>
                                    <a>卫衣</a>
                                    <a>外套/马甲</a>
                                    <a>比赛服</a>
                                    <a>紧身服</a>
                                    <a>裙装</a>
                                    <a>裤装</a>
                                    <a>棉装</a>
                                    <a>羽绒服</a>
                                    <a>其他服装</a>
                                </div>
                                <div class="box_2" style="width:197px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">配件</a>
                                    <a>袜子</a>
                                    <a>包</a>
                                    <a>帽子</a>
                                    <a>护具</a>
                                    <a>手套/围巾</a>
                                    <a>配饰</a>
                                </div>
                                <div class="box_3" style="width:197px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">系列</a>
                                    <a>lining-新活力系列</a>
                                    <a>型走冬日 温暖一冬</a>
                                    <a>智跑生活 触手可得</a>
                                    <a>LNG-型自首尔</a>
                                </div>
                 
                        </div>
                    </div>
                </div>
                </li>
                <div style="background: rgb(76, 74, 70) none repeat scroll 0% 0%;" class="logo_inter"></div>
                <li><a style="color:#fff;" class="li_a5">运动</a><img class="down_img" src="../images/content_down.png"/>
                	<div id="font_content—5" style="display:none;"> 
                	<div class="sub_box">
                    	<meta content="text/html" http-equiv="content-type" charset="utf-8" />
                        <title>运动</title>
                        <style>
							#font_content—5 .sub_box .box_1{
								float:left;
								border-left:1px solid #ccc;
								border-right:1px solid #ccc;
								margin-top:30px;
								margin-left:80px;
							}
							
							#font_content—5 .sub_box .box_2{
								float:left;
								border-right:1px solid #ccc;
								margin-top:30px;
								margin-left:20px;
							}
							
							#font_content—5	.sub_box  a{
								font-size:12px;
								display:block;
								margin-left:20px;
								line-height:20px;
							}
							#font_content—5	.sub_box p{
								font-family:"Microsoft YaHei UI";
								font-size:20px;
								font-weight:600;
								color:red;
							}
                        </style>
                        <div style="width:1349px; height:200px;background:rgb(246,246,246); font-family:'Microsoft YaHei UI'; text-align:center;">
                            	<div class="box_1" style="width:200px; height:154px;">
                                	<a>
                                    	<img with="135px" height="115px" alt="篮球" src="../images/basketball.png" />
                                        </a>
                                    <p style="font-family:'Microsoft YaHei UI';">篮球</p>
                                </div>
                                <div class="box_2" style="width:200px; height:154px;">
                                	<a>
                                    	<img with="135px" height="115px" alt="跑步"  src="../images/run.png"/>
                                    </a>
                                    <p style="font-family:'Microsoft YaHei UI';">跑步</p>
                                </div>
                                <div class="box_2" style="width:200px; height:154px;">
                                	<a>
                                    	<img with="135px" height="115px" alt="运动生活"  src="../images/lnss.png"/>
                                    </a>
                                    <p style="font-family:'Microsoft YaHei UI';">运动生活</p>
                                </div>
                                <div class="box_2" style="width:200px; height:154px;">
                                	<a>
                                    	<img with="135px" height="115px" alt="训练" src="../images/life.png"/>
                                    </a>
                                    <p style="font-family:'Microsoft YaHei UI';">训练</p>
                                </div>
                                <div class="box_2" style="width:200px; height:154px;">
                                	<a>
                                    	<img  with="135px" height="115px" alt="羽毛球"  src="../images/5846D07192F71FBADD5311F16C4BB81C.png"/>
                                    </a>
                                    <p style="font-family:'Microsoft YaHei UI';">羽毛球</p>
                                </div>             
                            
                        </div>
                    </div>
                </div>
                </li>
                <div style="background: rgb(76, 74, 70) none repeat scroll 0% 0%;" class="logo_inter"></div>
                <li><a style="color:#fff;" class="li_a6">韦德</a><img class="down_img" src="../images/content_down.png"/>
                	<div id="font_content—6" style="display:none;">
                	<div class="sub_box">
                    	<meta content="text/html" http-equiv="content-type" charset="utf-8" />
                        <title>韦德</title>
                        <style> 
							#font_content—6  .sub_box .box_1{
								float:left;
								border-left:1px solid #ccc;
								margin-top:40px;
								margin-left:80px;
							}
							#font_content—6  .sub_box  .box_2{
								float:left;
								border-left:1px solid #ccc;
								margin-top:40px;
							}
							#font_content—6	.sub_box  .box_3{
								float:left;
								border-left:1px solid #ccc;
								border-right:1px solid #ccc;
								margin-top:40px;
							}
							#font_content—6	.sub_box  a{
								font-size:12px;
								display:block;
								margin-left:20px;
								line-height:20px;
							}
                        </style>
                        
                        <div style="width:1349px; height:354px;background:rgb(246,246,246); font-family:'Microsoft YaHei UI'; text-align:left;">
                            	<div class="box_1" style="width:180px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">专题</a>
                                    <a>韦德之道全系列</a>
                                    <a>韦德之道4系列专业篮球比赛鞋</a>
                                    <a>新品上市</a>
                                    <a>热卖商品</a>  
                                </div>
                                <div class="box_2" style="width:197px; height:224px;">
                                	<a class="box2_head" style="font-size:28px; color:red; margin-bottom:20px;">鞋类</a>
                                    <a>韦德之道</a> 
                                    <a>篮球训练鞋</a>
                                    <a>篮球比赛鞋</a>
                                    <a>篮球文化鞋</a>
                                </div>
                                <div class="box_2" style="width:197px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">服装</a>
                                    <a>T恤/Polo衫/背心</a>
                                    <a>外套/马甲</a>
                                    <a>比赛服</a>
                                    <a>裤装</a>
                                    <a>棉装</a>
                                    <a>羽绒服</a>
                                  	<a>卫衣</a>
                                </div>
                                <div class="box_2" style="width:197px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">配件</a>
                                    <a>袜子</a>
                                    <a>包</a>
                                    <a>帽子</a>
                                    <a>护具</a>
                                    <a>手套/围巾</a>
                                </div>
                                <div class="box_3" style="width:197px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">器材</a>
                                    <a>篮球</a> 
                                </div>
                            
                        </div>
                    </div>
                </div>
                </li>
                <div style="background: rgb(76, 74, 70) none repeat scroll 0% 0%;" class="logo_inter"></div>
                <li><a style="color:#fff;" class="li_a7">乐途</a><img class="down_img" src="../images/content_down.png"/>
                	<div id="font_content—7" style="display:none;">
                	<div class="sub_box">
                    	<meta content="text/html" http-equiv="content-type" charset="utf-8" />
                        <title>乐途</title>
                        <style>
							#font_content—7  .sub_box .box_1{
								float:left;
								border-left:1px solid #ccc;
								margin-top:40px;
								margin-left:80px;
							}
                            
							#font_content—7  .sub_box  .box_2{
								float:left;
								border-left:1px solid #ccc;
								margin-top:40px;
							}
							
							#font_content—7	.sub_box  .box_3{
								float:left;
								border-left:1px solid #ccc;
								border-right:1px solid #ccc;
								margin-top:40px;
                            }
                           
							#font_content—7	.sub_box  a{
								font-size:12px;
								display:block;
								margin-left:20px;
								line-height:20px;
							}
                        </style>
                        <div style="width:1349px; height:354px;background:rgb(246,246,246); font-family:'Microsoft YaHei UI'; text-align:left;">        	
                        		<div class="box_1" style="width:180px; height:224px;">
                                	<a class="box_head" style="font-size:28px;margin-bottom:20px;">男鞋</a>
                                    <a  style="color:black;">跑步鞋</a> 
                                    <a style="color:black;">运动生活鞋</a>
                                    <a style="color:black;">户外鞋</a>
                                </div>
                                <div class="box_2" style="width:197px; height:224px;">
                                	<a class="box2_head" style="font-size:28px;margin-bottom:20px;color:red;">女鞋</a>
                                    <a style="color:black;">跑步鞋</a> 
                                    <a style="color:black;">运动生活鞋</a>
                                    <a style="color:black;">户外鞋</a>
                                </div>
                                <div class="box_2" style="width:197px; height:224px;">
                                	<a class="box_head" style="font-size:28px;margin-bottom:20px;">男装</a>
                                    <a style="color:black;">T恤/Polo衫/背心</a>
                                    <a style="color:black;">卫衣</a>
                                    <a style="color:black;">外套/马甲</a>
                                    <a style="color:black;">裤装</a>
                                    <a style="color:black;">棉装</a>
                                    <a style="color:black;">羽绒服</a>
                                    <a style="color:black;">冲锋衣</a>
                                    <a style="color:black;">比赛服</a>
                                </div>
                                <div class="box_2" style="width:179px; height:224px;">
                                	<a class="box_head" style="font-size:28px; margin-bottom:20px;">女装</a>
                                    <a>T恤/Polo衫/背心</a>
                                    <a>卫衣</a>
                                    <a>外套/马甲</a>
                                    <a>裤装</a>
                                    <a>棉装</a>
                                    <a>冲锋衣</a>
                                    <a>羽绒服</a>
                                </div>
                                <div class="box_3" style="width:197px; height:224px;">
                                	<a class="box_head" style="font-size:28px; color:red; margin-bottom:20px;">配件</a>
                                    <a>袜子</a>
                                    <a>包</a>
                                </div>              
                            
                        </div>
                    </div>
                </div>
                </li>
                <div style="background: rgb(76, 74, 70) none repeat scroll 0% 0%;" class="logo_inter"></div>
                
                <li><a style="color:#fff;" class="li_a8">10K报名</a><img class="down_img" src="../images/content_down.png"/></li>
                
                <div style="background: rgb(76, 74, 70) none repeat scroll 0% 0%;" class="logo_inter"></div>
                
                <li><a style="color:#fff;" href="#" class="li_a9">更多品牌</a><img class="down_img_end" src="../images/content_down.png"/>
                	<div id="font_content—8" style="display:none;">
                	<div class="sub_box">
                    	<meta content="text/html" http-equiv="content-type" charset="utf-8" />
                        <title>更多品牌</title>
                        <style>
							#font_content—8  .sub_box .box_1{
								float:left;
								border-left:1px solid #ccc;
								border-right:1px solid #ccc;
								margin-top:24px;
								margin-left:14%;
							}
							
							#font_content—8  .sub_box .box_2{
								float:left;
								border-right:1px solid #ccc;	
								margin-top:24px;
								margin-left:20px;
							}
							
							#font_content—8	.sub_box  a{
								font-size:12px;
								display:block;
								margin-left:20px;
								line-height:20px;
							}
                        </style>
                        <div style="width:1349px; height:102px;background:rgb(246,246,246); font-family:'Microsoft YaHei UI'; text-align:center;">
                            	<div class="box_1" style="width:200px; height:52px;">
                                	<a><img src="../images/lining1.png"/></a>
                                </div>
                                <div class="box_2" style="width:200px; height:52px;">
                                	<a><img src="../images/lining2.png"/></a> 	
                                </div>
                                <div class="box_2" style="width:200px; height:52px;">
                                	<a><img src="../images/shuaxi.png"/></a>
                                </div>
                                <div class="box_2" style="width:200px; height:52px;">
                                	<a><img src="../images/kaisheng.png"/></a>          
                                </div>   
                            
                            
                        </div>
                    </div>
                </div>
                </li>
            </ul>
            
        </div>
        <div id="comm_contain_right">
       
        		<input  type="text"   name="search" id="search"/>
                <span><img style="position:absolute; top:0px;"src="../images/search.png" onclick="searchInfo()"/></span>
            
        </div>
    </div>
    <!--第一个层：登录-->
            <div id="Mywhole" style="display:none;width:100px;height:100px;background:red;">
                <div id="top_left">
                    <span style="font-size:18px;">登录/注册</span>
                    <span class="close"><img src="../images/close1.jpg"/></span>
                </div>
            	<div id="bg_content">
                     <div id="content_first">
                        <span class="login" style="font-size:16px; ">登录</span>
                        <span class="registe" style="font-size:16px;">注册</span>
                     </div>
                     
             		 <div class="blank"></div>
                     
                     <div id="content_second">
                        <label class="itemtitle">用户名</label>
                        <input id="login_userName" class="txt" type="text" name="userName" maxlength="60">
                        <span class="yzN">请输入会员名称</span>
                     </div>
                     
                     <div id="third">
                        <label class="itemtitle">密码</label>
                        <input id="login_userPwd" class="txt" type="password" name="userPwd" maxlength="60">
                        <span class="yzP">密码只能是6-30位英文、数字及“_”、“-”组成</span>
                     </div>
                     
                      <div id="forth">
                        <label class="itemtitle">验证码</label>
                        <input id="login_userYzm" class="txt_y" type="text" name="userYzm" maxlength="60">
                        <img src="../images/yanzhma.jpg" class="tp">
                        <span class="yzM">请输入验证码</span>
                      </div>
                      <div id="five">
                          <div id="login_btn" class="login_btn" type="text">登录</div>
                          <a class="login_mi" href="">忘记密码？</a>
                      </div> 
                      <div id="six">
                            您也可以使用合作网站帐号登录
                      </div> 
                      <div id="seven">
                        <div class="senven_1">
                            <span><img src="../images/zhifubao.png"><a href="" style="text-decoration:none">支付宝</a></span>
                        </div>
                        <div class="senven_1">
                            <span><img src="../images/weibo.png"><a href="" style="text-decoration:none">新浪微博</a></span>
                        </div>
                        <div class="senven_1">
                            <span><img src="../images/qqlogin.png"><a href="" style="text-decoration:none"></a></span>
                        </div>
                        <div class="senven_1">
                            <span><img src="../images/renren.jpg"><a href="" style="text-decoration:none">人人网</a></span>
                        </div>
                        <div class="senven_1">
                            <span><img src="../images/zhu.png"><a href="" style="text-decoration:none">平安万里通</a></span>
                        </div>
                        <div class="senven_1">
                            <span><img src="../images/yi.png"><a href="" style="text-decoration:none">网易</a></span>
                        </div>
                        <div class="senven_2">
                            <span><img src="../images/weixing.png"><a href="" style="text-decoration:none"></a></span>
                        </div>
                     </div>
        	    </div>
          </div>
    <div id="r_logo_hide" style=" margin-top:252px;display:none; float:right;"><img  src="../images/hide_bar_3.png"/></div>
    <div id="r_logo" style="display:block;"><img  src="../images/logor.png"/></div>
        
    <div id="content" style="display:block;">
    	<!-----------右边登录注册隐藏栏--------------------------------------------------------------------------------------->
        <div id="bg">
            
          
          	<div id="zhuce" style="display:none;">
                <div id="zhuce_first" class="zhuce_first">
                    <label class="zhuce_text"><span class="star">*</span>用户名：</label>
                   <span>
                   <input id="reg_userName" class="txt" type="text" name="reg_userName" maxlength="20" autocomplete="off">
                   <span class="yzP">4-40位字符，支持汉字、字母、数字及字符组合</span>
                   </span>
          
                </div>
                <div id="zhuce_second" class="zhuce_second">
                    <label class="zhuce_text"><span class="star">*</span >请设置密码：</label>
                    <input id="reg_userPwd" class="txt" type="password" name="reg_userPwd"maxlength="20" autocomplete="off">
                    <span class="yzP">密码是由6－20位字符组成，建议两种以上组合</span>
                </div>
                <div id="zhuce_third" class="zhuce_second">
                    <label class="zhuce_text"><span class="star">*</span>请确定密码：</label>
                    <input id="reg_userYPwd" class="txt"type="password"name="reg_userYPwd"maxlength="20" autocomplete="off">
                </div>
                <div id="zhuce_forth" class="zhuce_second">
                    <label class="zhuce_text"><span class="star">*</span>邮箱：</label>
                    <input id="reg_userYx" class="txt" type="text" name="reg_userYx"maxlength="20" autocomplete="off">
                    <span class="yzN">请输入正确的邮箱</span>
                </div>
               <div id="zhuce_five" class="zhuce_second">
                    <label class="zhuce_text"><span class="star">*</span >验证码：</label>
                    <input id="reg_userYZM" class="txt" type="password" name="reg_userYZM"maxlength="20" autocomplete="off">
                    <span class="yzN">请输入正确的验证码</span>
                </div>
                <div id="yuedu">
                    <input type="checkbox" style="vertical-align:middle;" name="yuedu" checked="" class="yuedu">
                    <span class="sure">我已阅读《<a href="" style="text-decoration:none">李宁官方网站服务协议</a>》</span>
                </div>
            	<div id="reg">立刻注册</div>
            </div>
       </div>
       <!------------------------------->
       
       
    	<div id="r_show" class="bar">
        	<img  src="../images/show_bar_1.png"/>
        </div>
        <div class="solid"><img  src="../images/solid.png"/></div>
        <div id="r_lining" class="bar" >
        	<img src="../images/my_lining_1.png"/>
            <div id="r_lining_hide" style="display:none; width:216px; height:180px;margin-left:-205px;margin-top:-40px; background:url(../images/login_background.png) no-repeat;">
            	<p class="personInfo">个人信息</p>
            	<a><img class="hide_loginimg1" src="../images/login.png"/></a>
                <a><img class="hide_loginimg2" src="../images/register.png"/></a>
            </div>
        </div>
        <div class="solid"><img  src="../images/solid.png"/></div>
        <div id="r_cart" class="bar">
        	<img   src="../images/cart_icon_1.png"/>
            <span>(0)</span>
        </div>
        <!--购物车隐藏栏-->
        <div id="r_cart_hide" style="display:none; width:213px; height:577px; background:url(../images/cart_background_1.png);">
            	<div  id="r_cart_close" style="width:30px; height:30px; background:#CCC; text-align:center; float:right;">
                	<img style="margin-top:3px;" src="../images/close.png"/>
                </div>
                <div style="width:100%; height:60px; text-align:center; float:right;">
                	<p style="color:#FFF; line-height:20px; font-size:16px; margin-top:20px; font-weight:bold;">购物车</p>
                </div>
                <div style="width:100%; height:46px; float:right; margin-top:20px;">
                    <input  type="checkbox" style="float:left; width:10px; margin-left:8px; margin-top:-12px;"/>
                    <p style=" float:left; margin-left:4px; margin-top:-16px; color:#FFF;">全选</p>
                    <img   style=" margin-top:-16px; margin-right:6px; float:right;" src="../images/no_cart_more.png"/>
                    <img   style="margin-top:18px;" src="../images/cart_line.png"/>
                </div>
                
                <div style="width:98%;height:300px;margin-top:8px;margin-right:2px; float:right;">
                	
                </div>
                <div style="width:94%;height:26px; background:#CCC;float:right; margin-top:0px;margin-right:6px; font-size:14px;">
                	<span style="float:left; margin-left:6px; margin-top:2px;">已选择<span style="color:red;">0</span>件商品</span>
                    <span style="float:right; margin-right:6px;margin-top:2px;color:red;">￥6.00</span>
                </div>
                <div style="width:100%; height:60px; float:right; margin-top:10px;">
                	<img src="../images/no_checkout.png"/>
                </div>
                
        </div>
            
            
        <div class="solid"><img  src="../images/solid.png"/></div>
        <div id="account" class="bar">
        	<img  src="../images/account_1.png"/>
            <div id="r_account_hide" style="display:none; width:216px; height:180px;margin-left:-206px;margin-top:-50px; background:url(../images/login_background.png) no-repeat;">
            	<p class="personInfo">个人信息</p>
            	<a><img class="hide_loginimg1" src="../images/login.png"/></a>
                <a><img class="hide_loginimg2" src="../images/register.png"/></a>
            </div>
        </div>
        <div class="solid"><img  src="../images/solid.png"/></div>
        <div id="r_collect" class="bar">
        	<img  src="../images/collect_1.png"/>
            <div id="r_collect_hide" style="display:none; width:216px; height:180px;margin-left:-206px;margin-top:-50px; background:url(../images/login_background.png) no-repeat;">
            	<p class="personInfo">个人信息</p>
            	<a><img class="hide_loginimg1" src="../images/login.png"/></a>
                <a><img class="hide_loginimg2" src="../images/register.png"/></a>
            </div>
        </div>
        <div class="solid"><img  src="../images/solid.png"/></div>
        <div id="r_view" class="bar">
        	<img  src="../images/view_1.png"/>
            
        </div>   
        <!--最近浏览隐藏栏-->
        
        <div id="r_view_hide" style="display:none;width:213px; height:577px; background:url(../images/cart_background_1.png); text-align:center;">
                <div  id="r_view_close" style="width:30px; height:30px; background:#CCC; text-align:center; float:right;">
                        <img style="margin-top:3px;" src="../images/close.png"/>
                </div>
                <div style="width:100%; height:60px; text-align:center; float:right;">
                	<p style="color:#FFF; line-height:20px; font-size:16px; margin-top:20px; font-weight:bold;">我的收藏</p>
                </div>
         		
                <div id="liulan" style="width:50%; height:106px; float:left;">
                	
                 </div> 
               
        </div>   
            
       <div class="solid"><img  src="../images/solid.png"/></div>
        <div id="r_service" class="bar">
        	<img  src="../images/service_1.png"/>
            <div id="r_service_hide" style="display:none; width:117px; height:66px;margin-left:-110px;margin-top:-60px; text-align:center; background:url(../images/server_background.png) no-repeat;">
          		<img   style=" margin-left:-66px;" src="../images/service.png"/>
                <p style="margin-top:-36px; margin-left:20px; line-height:14px;">售前咨询<br/>---------<br/>售后咨询</p>
              
            </div>
        </div>
        <div class="solid"><img  src="../images/solid.png"/></div>
        <div id="r_scan" class="bar">
        	<img  src="../images/scan_ground.png"/>
            <div id="r_scan_hide" style="display:none; width:150px; height:142px;margin-left:-140px;margin-top:-70px;"><img  src="../images/scan.png"/></div>
        </div>
        <div class="solid"><img  src="../images/solid.png"/></div>
        <div id="r_top" class="bar" style="display:none;">
        	<img   style="margin-top:8px;" src="../images/top_1.png"/>
            <p style="color:#FFF;">回到</p>
            <p style="color:#FFF; margin-top:-6px;">顶部</p>
        </div>
        
    </div>
</div>