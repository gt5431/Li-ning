$(function(){
	$("#content_index").tabs("add",{
		title:'欢迎登录',
		selected:true,
		closable:false,
		href:'back/lining.html'
	});
	
	
	$("#menu_tree").tree({
		onClick:function(node){
			var obj=$("#content_index");
			if(node.id=='findManagers_content'){//说明是查看所有管理员
				//判断管理员界面是否展开
				if(obj.tabs('exists','管理员信息管理')){
					obj.tabs('select','管理员信息管理');
				}else{
					$("#content_index").tabs("add",{
						title:'管理员信息管理',
						selected:true,
						closable:true,
						href:'back/manager.html'
					});
				}
			}else if(node.id=='findUsers_content'){
				if(obj.tabs('exists','会员信息管理')){
					obj.tabs('select','会员信息管理');
					
				}else{
					$("#content_index").tabs("add",{
						title:'会员信息管理',
						selected:true,
						closable:true,
						href:'back/user.html'
					});
				}
			}else if(node.id=='selectType_content'){
				if(obj.tabs('exists','商品类型管理')){
					obj.tabs('select','商品类型管理');
				}else{
					$("#content_index").tabs("add",{
						title:'商品类型管理',
						selected:true,
						closable:true,
						href:'back/type.html'
					});
				}
				
			}else if(node.id=='findProducts_content'){
				if(obj.tabs('exists','商品信息管理')){
					obj.tabs('select','商品信息管理');
				}else{
					$("#content_index").tabs("add",{
						title:'商品信息管理',
						selected:true,
						closable:true,
						href:'back/product.html'
					});
				}
			}else if(node.id=='findOrderform_content'){
				if(obj.tabs('exists','订单信息管理')){
					obj.tabs('select','订单信息管理');
				}else{
					$("#content_index").tabs("add",{
						title:'订单信息管理',
						selected:true,
						closable:true,
						href:'back/orderform.html'
					});
				}
			}else if(node.id=='findPlace_content'){
				if(obj.tabs('exists','地址信息管理')){
					obj.tabs('select','地址信息管理');
				}else{
					$("#content_index").tabs("add",{
						title:'地址信息管理',
						selected:true,
						closable:true,
						href:'back/place.html'
					});
				}
				
			}else if(node.id=='product_sale'){
				if(obj.tabs('exists','商品销售情况')){
					obj.tabs('select','商品销售情况');
				}else{
					$("#content_index").tabs("add",{
						title:'商品销售情况',
						selected:true,
						closable:true,
						href:'back/product_sale.html'
					});
				}
				
			}
			
		}
		
	});
});


