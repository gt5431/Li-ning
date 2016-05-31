<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>商品信息显示</title>

<link href="../css/Common1.css" rel="stylesheet" type="text/css"></link>
<link href="../css/Product.css" rel="stylesheet" type="text/css"></link>
<script type="text/javascript" src="../js/jquery-1.11.3.js" /></script>
<script type="text/javascript" src="../js/public.js" /></script>

<script type="text/javascript">
$(function(){
	$(".img_show").mouseover(function(){
	    var id =this.id;
	    var s =id.split("_")[1]; 
	    var  obj = $(this).parent().parent().find("div").first();
	    obj.find("img").css("display","none");
	    obj.find("#big_top"+s).css("display","block");
	    		
	});
});
  
    function show(index){
    		
			 for(var i=1;i<=3;i++){
			 	 
			 	var id ="#big_top"+i;
			 	
			       if(i==index){
			       	   document.getElementById("big_top"+index).style.display='block';
			       	  
			       }else{
			       	document.getElementById("big_top"+i).style.display='none';
			    
			       	}
			 }
		}	
		
		
		function findPageUtil(index){
			var flag=$("#pageutil").attr("flag");
			alert(flag);
  			if(flag=="findByPrice"){
  				findByPrice(index);
  			}else if(flag=="ByPriceDesc"){
  				findByPriceDesc(index);
  			}else if(flag=="findByDate"){
  				findByDate(index);
  			}else if(flag=="findByType"){
  				findByType(index);
  			}else{
  				alert("fenye--"+index);
  				$.post(
  					"pro_findByPage.action",
  					{num:index,flag:flag},
  					function(data){
  						console.info(data[1]);
  						var val="";
  						$.each(data[1], function(indexs,items){
  							alert(items.color.split(",")[0]);
  							val+= '<div id="mainContent_center_center_div1" >'
  			                    		+'<div id="mainContent_center_center_div1_1" onClick="getid('+items.pro_number+')">'
  			                    	    +'<a href="pro_details.action?pro_number='+items.pro_number+'"><img src="../'+items.color+'" class="big_top" id="big_top1" style="display:block" /></a>'
  			                           +'<a href="pro_details.action?pro_number='+items.pro_number+'"><img src="../'+items.color+'" class="big_top" id="big_top2" style="display:none" /></a>'
  			                         	+'<a href="pro_details.action?pro_number='+items.pro_number+'"><img src="../'+items.color+'" class="big_top" id="big_top3" style="display:none" /></a>'
  			                           +' </div>'
  			                            +'<div id="mainContent_center_center_div1_2" class="small_button">'
  			                            +'<img src="../'+items.color+'" class="img1" id="img1_1" onMouseOver="show(1)">'
  			                            +'<img src="../'+items.color+'" class="img2" id="img1_2" onMouseOver="show(2)">'
  			                            +'<img src="../'+items.color+'" class="img3" id="img1_3" onMouseOver="show(3)">'
  			                           +' </div>'
  			                           +' <div id="mainContent_center_center_div1_3">'
  			                        		+items.pro_name
  			                           +' </div>'
  			                          +'  <div id="mainContent_center_center_div1_4">￥'+items.pro_tagprice+'</div>'
  			               		     +'   </div>'
  								});
  						$("#mainContent_center_center").html(val);
  						$("#pageutil").text("当前第  "+data[0].pageNo+"页/总共"+data[0].totalPages+" 页");
  					},
  					"json"); 
			}
  		}
		
	   	//根据价格分页降序排列
	   	function findByPriceDesc(index){
	   		
	   			$.post("pro_findByPriceDesc.action",{num:index},function(data){
	   				alert("index"+index+"data==>"+data[1].pro_number);
	   			var val="";
	   				$.each(data[1], function(indexs,items){
					val+= '<div id="mainContent_center_center_div1">'
	                    		+'<div id="mainContent_center_center_div1_1" onClick="getid('+items.pro_number+')">'
	                    	    +'<a href="pro_details.action?pro_number='+items.pro_number+'"><img src="../'+items.color+'" class="big_top" id="big_top1" style="display:block" /></a>'
	                           +'<a href="pro_details.action?pro_number='+items.pro_number+'"><img src="../'+items.color+'" class="big_top" id="big_top2" style="display:none" /></a>'
	                         	+'<a href="pro_details.action?pro_number='+items.pro_number+'"><img src="../'+items.color+'" class="big_top" id="big_top3" style="display:none" /></a>'
	                           +'</div>'
	                            +'<div id="mainContent_center_center_div1_2" class="small_button">'
	                            +'<img src="../'+items.color+'" class="img1" id="img1_1" onMouseOver="show(1)">'
	                            +'<img src="../'+items.color+'" class="img2" id="img1_2" onMouseOver="show(2)">'
	                            +'<img src="../'+items.color+'" class="img3" id="img1_3" onMouseOver="show(3)">'
	                           +' </div>'
	                           +' <div id="mainContent_center_center_div1_3">'
	                        +items.pro_name
	                           +' </div>'
	                          +'  <div id="mainContent_center_center_div1_4">￥'+items.pro_tagprice+'</div>'
	               		     +'   </div>'
				});
	   				$("#mainContent_center_center").html(val);
	   				$("#pageutil").text("当前第  "+data[0].pageNo+"页/总共"+data[0].totalPages+" 页");
	   				$("#pageutil").attr("flag","ByPriceDesc");
	   			},"json");
	   	}
		
	  //根据日期分页查询
		function findByDate(index){
		alert("index"+index);
			$.post("pro_findByDate.action",{num:index},function(data){
			var val="";
				$.each(data[1], function(indexs,items){
			val+= '<div id="mainContent_center_center_div1">'
                		+'<div id="mainContent_center_center_div1_1" onClick="getid('+items.pro_number+')">'
                	    +'<a href="pro_details.action?pro_number='+items.pro_number+'"><img src="../'+items.color+'" class="big_top" id="big_top1" style="display:block"></a>'
                       +' <a href="pro_details.action?pro_number='+items.pro_number+'"><img src="../'+items.color+'" class="big_top" id="big_top2" style="display:none"></a>'
                     	+'<a href="pro_details.action?pro_number='+items.pro_number+'"><img src="../'+items.color+'" class="big_top" id="big_top3" style="display:none"></a>'
                       +' </div>'
                        +'<div id="mainContent_center_center_div1_2" class="small_button">'
                        +'<img src="../'+items.color+'" class="img1" id="img1_1" onMouseOver="show(1)">'
                        +'<img src="../'+items.color+'" class="img2" id="img1_2" onMouseOver="show(2)">'
                        +'<img src="../'+items.color+'" class="img3" id="img1_3" onMouseOver="show(3)">'
                       +' </div>'
                       +' <div id="mainContent_center_center_div1_3">'
                    +items.pro_name
                       +' </div>'
                      +'  <div id="mainContent_center_center_div1_4">￥'+items.pro_tagprice+'</div>'
           		     +'   </div>'
		});
				$("#mainContent_center_center").html(val);
				$("#pageutil").text("当前第  "+data[0].pageNo+"页/总共"+data[0].totalPages+" 页");
				$("#pageutil").attr("flag","findByDate");
			},"json");
	}
	   	
	 	//根据名称模糊查询
		function findByType(index){
	 		var pro_Name=$("#search").val();
			$.post("pro_findByType.action",{num:index,searchName:pro_Name},function(data){
				var val="";
	   				$.each(data[1], function(indexs,items){
					val+= '<div id="mainContent_center_center_div1" >'
	                    		+'<div id="mainContent_center_center_div1_1" onClick="getid('+items.pro_number+')">'
	                    	    +'<a href="pro_details.action?pro_number='+items.pro_number+'"><img src="../'+items.color+'" class="big_top" id="big_top1" style="display:block"></a>'
	                           +'<a href="pro_details.action?pro_number='+items.pro_number+'"> <img src="../'+items.color+'" class="big_top" id="big_top2" style="display:none"></a>'
	                         	+'<a href="pro_details.action?pro_number='+items.pro_number+'"><img src="../'+items.color+'" class="big_top" id="big_top3" style="display:none"></a>'
	                           +' </div>'
	                            +'<div id="mainContent_center_center_div1_2" class="small_button">'
	                            +'<img src="../'+items.color+'" class="img1" id="img1_1" onMouseOver="show(1)">'
	                            +'<img src="../'+items.color+'" class="img2" id="img1_2" onMouseOver="show(2)">'
	                            +'<img src="../'+items.color+'" class="img3" id="img1_3" onMouseOver="show(3)">'
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
	   			},"json");
		} 	
	   	
	   	
	   	
   	//按照价格区间查询
   	function findByPrice(index){
   		
			var lowPrice=$("#lowPrice").val();
			var highPrice=$("#highPrice").val();
			alert("findByPrice---lowPrice==>"+lowPrice+"---"+highPrice);//{lowPrice:lowPrice,num:index}
			$.post("pro_findByPrice.action","lowPrice="+lowPrice+"&highPrice="+highPrice+"&num="+index,function(data){
			var val="";
				$.each(data[1], function(indexs,items){
					val+= '<div id="mainContent_center_center_div1">'
                		+'<div id="mainContent_center_center_div1_1" onClick="getid('+items.pro_number+')">'
                	    +'<a href="pro_details.action?pro_number='+items.pro_number+'"><img src="../'+items.color+'" class="big_top" id="big_top1" style="display:block"></a>'
                       +'<a href="pro_details.action?pro_number='+items.pro_number+'"> <img src="" class="big_top" id="big_top2" style="display:none"></a>'
                     	+'<a href="pro_details.action?pro_number='+items.pro_number+'"><img src="" class="big_top" id="big_top3" style="display:none"></a>'
                       +' </div>'
                        +'<div id="mainContent_center_center_div1_2" class="small_button">'
                        +'<img src="" class="img1" id="img1_1" onMouseOver="show(1)">'
                        +'<img src="" useOver="show(2)">'
                        +'<img src="" class="img3" id="img1_3" onMouseOver="show(3)">'
                       +' </div>'
                       +' <div id="mainContent_center_center_div1_3">'
                    +items.pro_name
                       +' </div>'
                      +'  <div id="mainContent_center_center_div1_4">￥'+items.pro_tagprice+'</div>'
           		     +'   </div>'
		});
				$("#mainContent_center_center").html(val);
				$("#pageutil").text("当前第  "+data[0].pageNo+"页/总共"+data[0].totalPages+" 页");
				$("#pageutil").attr("flag","findByPrice");
			},"json");
			
	}
   	
		function getid(index){
			
			$.ajax({
		 				type:"POST",
		 				url:"goods.do",
		 				data:"oop=look&id="+index,
		 				success:function(data){
		 					
	 					}
	 				});
			
			$.ajax({
		 				type:"POST",
		 				url:"goods.do",
		 				data:"oop=finddetails&id="+index,
		 				success:function(data){
		 					window.location.href="goodsinfo.jsp";
	 					}
	 				});
		}
    </script>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>

	<!------------------------------------主体部分---------------------------------->
	<div id="mainContent" style="font-family: '微软雅黑'">
		<div id="mainContent_left" style="font-family: '微软雅黑'">
			<div id="mainContent_left_top" style="font-family: '微软雅黑'">
				<div id="f1" style="cursor: pointer; font-family: 微软雅黑;">查看所有分类</div>
				<div id="f2" style="cursor: pointer; font-family: 微软雅黑;">男鞋</div>
				<div id="f3">
					<a href="">篮球鞋</a> <a href="">跑步鞋</a> <a href="">网球鞋</a> <a href="">户外鞋</a>
					<a href="">居家鞋</a> <a href="">跑步鞋</a> <a href="">网球鞋</a> <a href="">户外鞋</a>

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
					<a href="">裤装</a> <a href="">棉服</a> <a href="">羽绒服</a> <a href="">针织衫</a>
					<a href="">衬衫</a>

				</div>
			</div>
		</div>
		<div id="mainContent_right">
			<div id="mainContent_center" style="font-family: '微软雅黑'">
				<span id="mainContent_center_top">排序:</span> <span
					id="mainContent_center_top2" onclick="findByDate()"><a
					href="javascript:void(0)"
					style="text-decoration: none; color: #000;">新品↓</a></span> <span
					id="mainContent_center_top3"><a href="javascript:void(0)"
					style="text-decoration: none; color: #000;">热销↓</a></span> <span
					id="mainContent_center_top4" onclick="findByPriceDesc()"><a
					href="javascript:void(0)"
					style="text-decoration: none; color: #000;">价格↓</a></span>

				<div id="mainContent_center_right">

					<form action="">
						<input class="mainContent_center_right_input1" id="lowPrice"
							name="lowPrice"></input> <span
							class="mainContent_center_right_span">-</span> <input
							class="mainContent_center_right_input2" id="highPrice"
							name="highPrice"></input>

						<div id="mainContent_center_right_ok">
							<span id="price_sure" onclick="findByPrice()"><a
								href="javascript:void(0)"
								style="text-decoration: none; color: #000;">确定</a> </span>
						</div>
					</form>

				</div>

			</div>

			<div id="mainContent_center_center">
				<c:forEach items="${sessionScope.products}" var="products">
					<div id="mainContent_center_center_div1">
						<div id="mainContent_center_center_div1_1"
							onClick="getId(${products.pro_number})">
							<a href="pro_details.action?pro_number=${products.pro_number}">
								<img src="../${products.color.split(",")[0]}" />
							</a>
						</div>
						<div id="mainContent_center_center_div1_2">
							<img src="../${products.color.split(",")[0]}" class="img1">
							<img src="../${products.color.split(",")[1]}" class="img2">
							<img src="../${products.color.split(",")[2]}" class="img3">
						</div>
						<div id="mainContent_center_center_div1_3">
							${products.pro_name}</div>
						<div id="mainContent_center_center_div1_4">￥${products.pro_tagprice}</div>
					</div>
				</c:forEach>
			</div>

			<div id="page">
				<center>
					<span onclick="findPageUtil(1)">首页</span> 
					<span onclick="findPageUtil(2)">上一页</span> 
					<span onclick="findPageUtil(3)">下一页</span>
					<span onclick="findPageUtil(4)">尾页</span> 
					<span id="pageutil" flag="defaultfind">当前第${sessionScope.pageUtil.pageNo}页/总共${sessionScope.pageUtil.totalPages} 页</span>
				</center>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
