//  爆款推荐下的各种分类
function findProductById(typesid) {
	$("#chioce_1"+typesid).css("background","white");
	$("#s"+typesid).css("color","red");

	$.post("baokuan.action",{
		typesid : typesid
	}, function(data) {
		var value = "";
		for (var i = 0; i < data.length; i++) {
			value+='<div class="selinter"></div>'+
            '<div class="selItem">'+
            	'<div class="selMainPic">'+
                	'<a  id="god_1" href="pro_details.action?pro_number='+data[i].pro_number+'"><img  src="../'+data[i].color.split(",")[0]+'"/></a>'+
                    '<div class="slaveSwiperContent">'+
                    	'<div class="slaveItem"><img  src="../'+data[i].color.split(",")[0]+'"/><font></font></div>'+
                        '<div class="slaveItem"><img  src="../'+data[i].color.split(",")[1]+'"/><font></font></div>'+
                        '<div class="slaveItem"><img  src="../'+data[i].color.split(",")[2]+'"/><font></font></div>'+
                    '</div>'+
                '</div>'+
                '<div class="hgoodsName" id="hgoodsName">'+data[i].pro_name+'</div>'+
                '<font class="ft_price">￥'+data[i].pro_tagprice+'</font>'+
                '<div class="hgoodsLine"></div>'+
            '</div>';
		}
		$("#selectItmdiv_1").html(value);
	}, "json");
}
function mouseout(typesid){
	$("#chioce_1"+typesid).css("background","#EEEEEE");
	$("#s"+typesid).css("color","#000");
}