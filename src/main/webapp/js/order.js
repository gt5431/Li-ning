$(function(){
	$("#other_diliver").click(function(){
		
		$("#add_deliver").css("display","block");
		$("#order_center").css("height","960px");
	});
	
	$(".closes").click(function(){
		$("#add_deliver").css("display","none");
		$("#order_center").css("height","600px");
	});
	
	$(".sprite").click(function(){
		alert("保存收货信息");
		/*$.ajax({
			type: "POST",
			url: "order_addAdress.action",
			dataType:"JSON",
			success: function(msg){			//查询成功后弹窗
	 			window.location.href="order.jsp";
			}
				
		});*/
		$("#add_deliver").css("display","none");
		$("#order_center").css("height","600px");
	});
	
	
	
});
