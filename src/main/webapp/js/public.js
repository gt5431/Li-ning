
var mytime=0;//定时器
var total=0;//图片总数
var index=0;//图片索引
var change_time=3000;//改变时间


//滚动浏览器的进度条到30px时显示  到顶部
$(function(){   //#content的7个点击事件
	$(document).scroll(function(){
		if(document.documentElement.scrollTop>260){
			$("#r_top").css("display","block");
		}else{
			$("#r_top").css("display","none");
		}
		
		$("#r_top").bind("click",function(){
				document.documentElement.scrollTop=0;
				$("#r_top").css("display","none");
			
		});
	});
	
	$("#r_logo_hide").bind("click",function(){
		$("#r_logo_hide").css("display","none");
		$("#content").css("display","block");
		$("#r_logo").css("display","block");
		$("#r_lining_hide").css("display","none");
		$("#r_cart_hide").css("display","none");
		$("#r_account_hide").css("display","none");
		$("#r_view_hide").css("display","none");
		$("#r_scan_hide").css("display","none");
		$("#r_service_hide").css("display","none");
		$("#r_collect_hide").css("display","none");
	});
	$("#r_show").bind("click",function(){
		$("#r_logo_hide").css("display","block");
		
		$("#content").css("display","none");
		$("#r_logo").css("display","none");
		
		$("#r_lining_hide").css("display","none");
		$("#r_cart_hide").css("display","none");
		$("#r_account_hide").css("display","none");
		$("#r_view_hide").css("display","none");
		$("#r_scan_hide").css("display","none");
		$("#r_service_hide").css("display","none");
		$("#r_collect_hide").css("display","none");
	});
	$("#r_lining").bind("click",function(){
		$("#content").css("margin-right","0px");
		$("#content").css("margin-top","0px");
		$("#r_logo").css("top","0px");
		$("#r_logo").css("left","0px");
		
		$("#r_lining_hide").css("display","block");
		$("#r_cart_hide").css("display","none");
		$("#r_account_hide").css("display","none");
		$("#r_view_hide").css("display","none");
		$("#r_scan_hide").css("display","none");
		$("#r_service_hide").css("display","none");
		$("#r_collect_hide").css("display","none");
		
	});
	$("#r_cart").bind("click",function(){
		$("#content").css("margin-right","213px");
		$("#content").css("margin-top","-84px");
		$("#r_cart_hide").css("display","block");
		$("#r_cart_hide").css("position","absolute");
		$("#r_cart_hide").css("right","0px");
		$("#r_cart_hide").css("top","80px");
		
		$("#r_collect_hide").css("display","none");
		$("#r_lining_hide").css("display","none");
		$("#r_account_hide").css("display","none");
		$("#r_view_hide").css("display","none");
		$("#r_scan_hide").css("display","none");
		$("#r_service_hide").css("display","none");
		
		$("#r_logo").css("position","relative");
		$("#r_logo").css("top","0px");
		$("#r_logo").css("left","-213px");
	});
	$("#account").bind("click",function(){
		$("#content").css("margin-right","0px");
		$("#content").css("margin-top","0px");
		$("#r_logo").css("top","0px");
		$("#r_logo").css("left","0px");
		
		$("#r_account_hide").css("display","block");
		$("#r_lining_hide").css("display","none");
		$("#r_cart_hide").css("display","none");
		$("#r_view_hide").css("display","none");
		$("#r_scan_hide").css("display","none");
		$("#r_service_hide").css("display","none");
		$("#r_collect_hide").css("display","none");
	});
	$("#r_collect").bind("click",function(){
		$("#content").css("margin-right","0px");
		$("#content").css("margin-top","0px");
		$("#r_logo").css("top","0px");
		$("#r_logo").css("left","0px");
		
		$("#r_collect_hide").css("display","block");
		$("#r_lining_hide").css("display","none");
		$("#r_cart_hide").css("display","none");
		$("#r_account_hide").css("display","none");
		$("#r_view_hide").css("display","none");
		$("#r_scan_hide").css("display","none");
		$("#r_service_hide").css("display","none");
	});
	$("#r_view").bind("click",function(){
		$("#content").css("margin-right","213px");
		$("#content").css("margin-top","-84px");
		
		$("#r_view_hide").css("display","block");
		$("#r_view_hide").css("position","absolute");
		$("#r_view_hide").css("right","0px");
		$("#r_view_hide").css("top","80px");
		
		$("#r_collect_hide").css("display","none");
		$("#r_lining_hide").css("display","none");
		$("#r_cart_hide").css("display","none");
		$("#r_account_hide").css("display","none");
		$("#r_scan_hide").css("display","none");
		$("#r_service_hide").css("display","none");
		
		
		$("#r_logo").css("position","relative");
		$("#r_logo").css("top","0px");
		$("#r_logo").css("left","-213px");
	});
	$("#r_service").bind("click",function(){
		$("#content").css("margin-right","0px");
		$("#content").css("margin-top","0px");
		$("#r_logo").css("top","0px");
		$("#r_logo").css("left","0px");
		
		$("#r_service_hide").css("display","block");
		$("#r_account_hide").css("display","none");
		$("#r_lining_hide").css("display","none");
		$("#r_cart_hide").css("display","none");
		$("#r_view_hide").css("display","none");
		$("#r_scan_hide").css("display","none");
		$("#r_collect_hide").css("display","none");
	});
	$("#r_scan").bind("click",function(){
		$("#content").css("margin-right","0px");
		$("#content").css("margin-top","0px");
		$("#r_logo").css("top","0px");
		$("#r_logo").css("left","0px");
		
		$("#r_scan_hide").css("display","block");
		$("#r_account_hide").css("display","none");
		$("#r_lining_hide").css("display","none");
		$("#r_cart_hide").css("display","none");
		$("#r_view_hide").css("display","none");
		$("#r_service_hide").css("display","none");
		$("#r_collect_hide").css("display","none");
	});
	
	//关闭事件////点击之后发生位移
	$("#r_view_close").bind("click",function(){
		$("#r_view_hide").slideUp("normal");
		$("#content").css("margin-right","0px");
		$("#content").css("margin-top","0px");
		$("#r_logo").css("position","relative");
		$("#r_logo").css("top","0px");
		$("#r_logo").css("left","0px");
		
	});
	$("#r_cart_close").bind("click",function(){
		$("#r_cart_hide").slideUp("normal");
		$("#content").css("margin-right","0px");
		$("#content").css("margin-top","0px");
		
		$("#r_logo").css("position","relative");
		$("#r_logo").css("top","0px");
		$("#r_logo").css("left","-600px");
		console.info($("#float_head #content .bar  .hide_loginimg1"));

	});
	//点击查看信息后弹出的div 层#float_head #content .bar  .hide_loginimg1
	$("#float_head #content .bar  .hide_loginimg1").bind("click",function(){
		alert("先登录");
		$("#Mywhole").css("display","block");
		$("#Mywhole").css("position","relative");
		$("#Mywhole").css("top","200px");
		$("#Mywhole").css("left","100px");
		
	});
	

	});


//comm_logo_contain 这是所有分类的div sub_bg hide
$(function(){
	length=$("#comm_logo_contain #comm_contain_left ul li").length;
		$($("#comm_logo_contain #comm_contain_left ul li")[1]).bind({
			mouseover:function(){
				$("#font_content—1").css("display","block");
				$("#font_content—1").css("margin-left","-80px");
				$("#font_content—1 .box_head").css("color","red");
				$($("#comm_logo_contain #comm_contain_left ul li")[1]).css("background","rgb(246,246,246)");
				$($("#comm_logo_contain #comm_contain_left ul li a")[1]).css("color","red");
			},
			mouseout:function(){
				$("#font_content—1").css("display","none");
				$($("#comm_logo_contain #comm_contain_left ul li")[1]).css("background","");
				$($("#comm_logo_contain #comm_contain_left ul li a")[1]).css("color","#fff");
			}
		});
		$($("#comm_logo_contain #comm_contain_left ul li")[2]).bind({
			mouseover:function(){
				$($("#comm_logo_contain #comm_contain_left ul li")[2]).css("background","white");
				$($("#comm_logo_contain #comm_contain_left ul li .li_a2")).css("color","red");
			},
			mouseout:function(){
				$($("#comm_logo_contain #comm_contain_left ul li")[2]).css("background","");
				$($("#comm_logo_contain #comm_contain_left ul li .li_a2")).css("color","#fff");
			}
		});
		
		
		//////////////////
		$($("#comm_logo_contain #comm_contain_left ul li")[3]).bind({
			mouseover:function(){
				$("#font_content—3").css("display","block");
				$("#font_content—3").css("margin-left","-240px");
				$("#font_content—3 .box_head").css("color","red");
				$($("#comm_logo_contain #comm_contain_left ul li")[3]).css("background","rgb(246,246,246)");
				$($("#comm_logo_contain #comm_contain_left ul li .li_a3")).css("color","red");
				console.info($("#comm_logo_contain #comm_contain_left ul li a"));
			},
			mouseout:function(){
				$("#font_content—3").css("display","none");
				$($("#comm_logo_contain #comm_contain_left ul li")[3]).css("background","");
				$($("#comm_logo_contain #comm_contain_left ul li .li_a3")).css("color","#fff");
			}
		});
		$($("#comm_logo_contain #comm_contain_left ul li")[4]).bind({
			mouseover:function(){
				$("#font_content—4").css("display","block");
				$("#font_content—4").css("margin-left","-320px");
				$("#font_content—4 .box_head").css("color","red");
				$($("#comm_logo_contain #comm_contain_left ul li")[4]).css("background","rgb(246,246,246)");
				$($("#comm_logo_contain #comm_contain_left ul li .li_a4")).css("color","red");
			},
			mouseout:function(){
				$("#font_content—4").css("display","none");
				$($("#comm_logo_contain #comm_contain_left ul li")[4]).css("background","");
				$($("#comm_logo_contain #comm_contain_left ul li .li_a4")).css("color","#fff");
			}
		});
		$($("#comm_logo_contain #comm_contain_left ul li")[5]).bind({
			mouseover:function(){
				$("#font_content—5").css("display","block");
				$("#font_content—5").css("margin-left","-405px");
				$("#font_content—5 .box_head").css("color","red");
				$($("#comm_logo_contain #comm_contain_left ul li")[5]).css("background","rgb(246,246,246)");
				$($("#comm_logo_contain #comm_contain_left ul li .li_a5")).css("color","red");
			},
			mouseout:function(){
				$("#font_content—5").css("display","none");
				$($("#comm_logo_contain #comm_contain_left ul li")[5]).css("background","");
				$($("#comm_logo_contain #comm_contain_left ul li .li_a5")).css("color","#fff");
			}
		});
		$($("#comm_logo_contain #comm_contain_left ul li")[6]).bind({
			mouseover:function(){
				$("#font_content—6").css("display","block");
				$("#font_content—6").css("margin-left","-486px");
				$("#font_content—6 .box_head").css("color","red");
				$($("#comm_logo_contain #comm_contain_left ul li")[6]).css("background","rgb(246,246,246)");
				$($("#comm_logo_contain #comm_contain_left ul li .li_a6")).css("color","red");
			},
			mouseout:function(){
				$("#font_content—6").css("display","none");
				$($("#comm_logo_contain #comm_contain_left ul li")[6]).css("background","");
				$($("#comm_logo_contain #comm_contain_left ul li .li_a6")).css("color","#fff");
			}
		});
		$($("#comm_logo_contain #comm_contain_left ul li")[7]).bind({
			mouseover:function(){
				$("#font_content—7").css("display","block");
				$("#font_content—7").css("margin-left","-567px");/*567px*/
				$("#font_content—7 .box_head").css("color","red");
				
				$($("#comm_logo_contain #comm_contain_left ul li")[7]).css("background","rgb(246,246,246)");
				$($("#comm_logo_contain #comm_contain_left ul li .li_a7")).css("color","red");
			},
			mouseout:function(){
				$("#font_content—7").css("display","none");
				$($("#comm_logo_contain #comm_contain_left ul li")[7]).css("background","");
				$($("#comm_logo_contain #comm_contain_left ul li .li_a7")).css("color","#fff");
			}
		});
		$($("#comm_logo_contain #comm_contain_left ul li")[8]).bind({
			mouseover:function(){
				$($("#comm_logo_contain #comm_contain_left ul li")[8]).css("background","white");
				$($("#comm_logo_contain #comm_contain_left ul li .li_a8")).css("color","red");
			},
			mouseout:function(){
				$("#font_content—7").css("display","none");
				$($("#comm_logo_contain #comm_contain_left ul li")[8]).css("background","");
				$($("#comm_logo_contain #comm_contain_left ul li .li_a8")).css("color","#fff");
			}
		});
		$($("#comm_logo_contain #comm_contain_left ul li")[9]).bind({
			mouseover:function(){
				$("#font_content—8").css("display","block");
				$("#font_content—8").css("margin-left","-729px");
				$("#font_content—9 .box_head").css("color","red");
				$($("#comm_logo_contain #comm_contain_left ul li")[9]).css("background","rgb(246,246,246)");
				$($("#comm_logo_contain #comm_contain_left ul li .li_a9")).css("color","red");
			},
			mouseout:function(){
				$("#font_content—8").css("display","none");
				$($("#comm_logo_contain #comm_contain_left ul li")[9]).css("background","");
				$($("#comm_logo_contain #comm_contain_left ul li .li_a9")).css("color","#fff");			
			}
		});
		
		
});



//----------------#center  .swiper_title 大图图片轮播
//1.左右对应位置顺序滚动图片(左右图标点击事件)自定义播放
$(function(){
	var i=0;
	$("#center .arrow_left").click(function(){
		window.clearInterval(mytime);
		i++;
		if(i>=4){
			i=0;
			$("#swiper_slide").animate({left: -document.body.clientWidth*i}, "fast");
		}else{
  			$("#swiper_slide").animate({left: -document.body.clientWidth*i}, "slow");
		}
	});
	$("#center .arrow_right").click(function(){
		window.clearInterval(mytime);
		i--;	
		if(i<0){
			i=3;
			$("#swiper_slide").animate({left: -document.body.clientWidth*i}, "fast");
		}else{
			$("#swiper_slide").animate({left: -document.body.clientWidth*i}, "slow");
		}
	});
	
	total =$("#center  .swiper_title a").length;
	totalLeft=$("#gray_bg .banner_star  .banner_list1 a").length;
	totalRight=$("#gray_bg .banner_new  .banner_list2 a").length;
	
	//mytime =window.setInterval("showPic()",change_time);
	$("#center .swiper_title   .swiper_a").bind({
		mouseover:function(){
			window.clearInterval(mytime);
			showPic();//$(this).parent().parent().index()
		},
		mouseout:function(){
			
			window.clearInterval(mytime);
			mytime=window.setInterval("showPic()",change_time);
		}	
	});
});
//-------------大图片轮播
function showPic(){
	if(arguments.length>0){
		if(arguments[0]<0){
			index=0;
		}else if(arguments[0]>total){
			index=total;
		}else{
			index=arguments[0];
		}
	}else{
		index=index%total;
	}
	
	$("#swiper_slide .swiper").css("display","none");
	$("#center .swiper_title   .swiper_a").css("background","#999");
	$("#swiper_slide .swiper").eq(index).css("display","block");
	$("#center .swiper_title   .swiper_a").eq(index).css("background","red");
	index++;
	
	if(index==4){
		index=0;
	}
	
}


 


$(function(){
	total =$("#center  .swiper_title a").length;
	totalLeft=$("#gray_bg .banner_star  .banner_list1 a").length;
	totalRight=$("#gray_bg .banner_new  .banner_list2 a").length;
	
	mytime =window.setInterval("showPic()",change_time);
	$("#swiper_slide .swiper_title   .swiper_a").bind({
		mouseover:function(){
			window.clearInterval(mytime);
			showPic();//$(this).parent().parent().index()
		},
		mouseout:function(){
			
			window.clearInterval(mytime);
			mytime=window.setInterval("showPic()",change_time);
		}	
	});
	$("#gray_bg .banner_star  .banner_list1 a").bind({
		mouseover:function(){
			window.clearInterval(mytime);
			showPicLeft();//$(this).parent().parent().index()
		},
		mouseout:function(){
			window.clearInterval(mytime);
			mytime=window.setInterval("showPicLeft()",change_time);
		}	
	});
	$("#gray_bg .banner_new  .banner_list2 a").bind({
		mouseover:function(){
			window.clearInterval(mytime);
			showPicRight();//$(this).parent().parent().index()
		},
		mouseout:function(){
			window.clearInterval(mytime);
			mytime=window.setInterval("showPicRight()",change_time);
		}	
	});
});

//显示#gray_bg .banner_star list1左边下的图片
function  showPicLeft(){
	if(arguments.length>0){
		if(arguments[0]<0){
			index=0;
		}else if(arguments[0]>totalLeft){
			index=totalLeft;
		}
	}else{
		index=index%totalLeft;
	}
	$("#gray_bg .banner_star  .banner_list1 a").css("display","none");
	$("#gray_bg .banner_star  .banner_list1 a").eq(index).css("display","block");
	$("#gray_bg .slide-number-box .left li").css("color","#fff");
	$("#gray_bg .slide-number-box .left li").eq(index).css("color","red");
	
	index++;
	
	if(index==4){
		index=0;
	}
}

//显示#gray_bg .banner_new list2右边下的图片
function  showPicRight(){
	if(arguments.length>0){
		if(arguments[0]<0){
			index=0;
		}else if(arguments[0]>(totalRight)){
			index=totalRight;
		}
	}else{
		index=index%totalRight;
	}
	$("#gray_bg .banner_new  .banner_list2 a").css("display","none");
	$("#gray_bg .banner_new  .banner_list2 a").eq(index).css("display","block");
	$("#gray_bg .slide-number-box .right li").css("color","#fff");
	$("#gray_bg .slide-number-box .right li").eq(index).css("color","red");
	
	index++;
	
	if(index==4){
		index=0;
	}
}
