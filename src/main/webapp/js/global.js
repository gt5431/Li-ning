//定义一个函数,函数同时也是一个对象
function Utils(){

}

Utils.trim = function(str){
	return $.trim(str);
};

Utils.isEmpty = function(val){
  switch (typeof(val)){
    case "string":
      return Utils.trim(val).length == 0 ? true : false;
      break;
    case "number":
      return val == 0;
      break;
    case "object":
      return val == null;
      break;
    case "array":
      return val.length == 0;
      break;
    default:
      return true;
  }
};

Utils.isDigital = function(val) {
	var reg = /^\d+$/;
  	return reg.exec(val);
};

//检查注册用户名（4-40位字符，支持汉字、字母、数字及“－”、“_”组合）
Utils.reg_userName = function(val) {
	var reg = /^[\u4e00-\u9fa5\w\-@.]{4,40}$/;
	return reg.test(val);
};

Utils.isChinese = function(val) {
	var reg = /^[\u4e00-\u9fa5]+$/;
  	return reg.exec(val) ;
};

Utils.isNumber = function(val){
  var number;
     if (val==null) return false;
     if (val=="") return false;
     number = new Number(val);
   	 if (!isNaN(number) && val == number.toString())
   	 	return true;
   	 return false;
};

Utils.isInt = function(val){
  if (this.trim(val) == "")
    return false;
  var reg = /\D+/;
  if (!reg.test(val)){
  	var i = parseInt(val);
  	return i.toString() == val;
  }else
  	return false;
};

Utils.isMoney = function(val) {
	var reg = /^(([0-9]\d*))(\.(\d{1,2}))?$/;
	if (!reg.test(val)) {
		return false;
	} else {
		return true;
	}
};

Utils.isUserName = function(val) {
	var reg1 = /(^[a-zA-Z0-9][a-zA-Z0-9_\-\.]*\@[a-zA-Z0-9]+$)|(^[A-Za-z0-9_\-\.]*\@+$)|(^[a-zA-Z0-9][a-zA-Z0-9_\-\.]+$)/;
	return reg1.test( val );
};

Utils.isEmail = function(email){
  var reg1 = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)/;
  return reg1.test( email );
};

Utils.isMobile = function(val) {
	var reg = /^1[345678]+\d{9}$/;
	return reg.test( val );
};

//字母的匹配
Utils.isAlphabet = function(val){
	var reg = /^[A-Za-z]*$/;
  	return reg.test(val);
};

Utils.isTel = function (tel){
  var reg = /^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$/; //只允许使用数字-空格等
  return reg.test( tel );
};

Utils.urlencode = function (str){
    str = str.replace(/\r\n/g,"\n");
		var utftext = "";

		for (var n = 0; n < str.length; n++) {

			var c = str.charCodeAt(n);

			if (c < 128) {
				utftext += String.fromCharCode(c);
			}
			else if((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			}
			else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}

		}
		return utftext;
};

Utils.urldecode = function (utftext){
	var str = "";
	var i = 0;
	var c = c1 = c2 = 0;

	while ( i < utftext.length ) {

		c = utftext.charCodeAt(i);

		if (c < 128) {
		    str += String.fromCharCode(c);
			i++;
		}
		else if((c > 191) && (c < 224)) {
			c2 = utftext.charCodeAt(i+1);
			str += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
			i += 2;
		}
		else {
			c2 = utftext.charCodeAt(i+1);
			c3 = utftext.charCodeAt(i+2);
			str += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
			i += 3;
		}

	}

	return str;
};

Utils.len = function(val) {
    var l = 0;
    var a = val.split("");
    for (var i=0;i<a.length;i++) {
        if (a[i].charCodeAt(0)<299) {
            l++;
        } else {
            l+=2;
        }
    }
    return l;
};

Utils.sb_substr = function(str, startp, endp) {

    var i=0; c = 0; unicode=0; rstr = '';
    var len = str.length;
    var sblen = this.len(str);
    if (startp < 0) {
        startp = sblen + startp;
    }
    if (endp < 1) {
        endp = sblen + endp;// - ((str.charCodeAt(len-1) < 127) ? 1 : 2);
    }
    for(i = 0; i < len; i++) {
        if (c >= startp) {
            break;
        }
        var unicode = str.charCodeAt(i);
        if (unicode < 127) {
            c += 1;
        } else {
            c += 2;
        }
    }
    for(i = i; i < len; i++) {
        var unicode = str.charCodeAt(i);
        if (unicode < 127) {
            c += 1;
        } else {
            c += 2;
        }
        rstr += str.charAt(i);

        if (c >= endp) {
            break;
        }
    }
    return rstr;
};

//头部搜索
var G_SEARCH_READY = 1;
var G_LAST_SEARCH_KEYWORDS = "";
var G_RESULT_COUNT = 0;
var G_SUGGESTION_CURSOR = 0;
var G_SEARCH_TYPE = 0;
var G_TRACTOR_HANDLE = 0;
var trigerObj;

function suggestRegTracker(){
	var vv = Utils.trim(trigerObj.value);
	if (vv == ""){
		G_LAST_SEARCH_KEYWORDS = "";
		close_suggestions();
		return false;
	}
	if (vv == G_LAST_SEARCH_KEYWORDS){
		if (document.getElementById("suggestion_containter").style.display == "none")
			document.getElementById("suggestion_containter").style.display = "block";
		return false;
	}

	if (G_SEARCH_READY == 0)
		return false;
	suggestSearch(vv);
}

function init_search_suggestion(triger,searchType,flg){
	trigerObj = triger;
	G_SEARCH_TYPE = searchType;
	var parent = trigerObj.offsetParent;
	var x,y;
	x = trigerObj.offsetLeft;
	y = trigerObj.offsetTop;
	var cnt = 0;
	while (parent){
		cnt++;
		x += parent.offsetLeft;
		y += parent.offsetTop;
		parent = parent.offsetParent;
	}
//	y += 24;
//	x -= 150;
//	x += "px";
//	y += "px";
//	var suggestion_containter = document.getElementById("suggestion_containter");
//	suggestion_containter.style.left = x;
//	suggestion_containter.style.top = y;

	trigerObj.onfocus = function (){
		G_TRACTOR_HANDLE = setInterval("suggestRegTracker()",200);
		if (this.value =="冬季羽绒") {
			this.value ="";
		} else {
			var reg = new RegExp("-","g");
			$("#keywords").val(this.value.replace(reg, "++"));
		}
	};
	trigerObj.onblur = function (){
		setTimeout("close_suggestions()", 500);
		if (this.value ==""){
			this.value ="";
		} else {
			var reg = new RegExp("-","g");
			$("#keywords").val(this.value.replace(reg, "++"));
		}
	};
	trigerObj.onkeyup = function (event){
		event = window.event || event;
		switch(event.keyCode){
			case 13: // enter
				if (G_SEARCH_TYPE == 1){
					if (G_SUGGESTION_CURSOR > 0){
						G_SEARCH_READY = 0;
						var searchValue = document.getElementById("_ak_" + G_SUGGESTION_CURSOR.toString()).value;
						trigerObj.value = searchValue;
						var reg = new RegExp("-","g");
						$("#keywords").val(searchValue.replace(reg, "++"));
					} else {
						var searchValue = $("#searchTemp").val();
						var reg = new RegExp("-","g");
						$("#keywords").val(searchValue.replace(reg, "++"));
					}
					keywords =  new_estr(searchValue);
					window.location.href = '/shop/goodsCate-0-0-0-0-0-0-min,max-'+keywords+'\.html';
				}else if (G_SEARCH_TYPE == 2){
					if (G_SUGGESTION_CURSOR > 0){
						var searchValue = document.getElementById("_ak_" + G_SUGGESTION_CURSOR.toString()).value;
						trigerObj.value = searchValue;
						var reg = new RegExp("-","g");
						$("#keywords").val(searchValue.replace(reg, "++"));
						close_suggestions();
					}else
						document.forms.search_form.submit();
				}
				return;
				break;
			case 27: // esc
				close_suggestions();
				return;
				break;
			case 37: // left
				return;
				break;
			case 39: // right
				return;
				break;
			case 38: // up
				var next_cursor;
				if (G_RESULT_COUNT > 0){
					if (G_SUGGESTION_CURSOR > 1){
						next_cursor = G_SUGGESTION_CURSOR - 1;
					}else if (G_SUGGESTION_CURSOR == 1 && G_RESULT_COUNT > 1){
						next_cursor = G_RESULT_COUNT;
					}else{
						break;
						return;
					}
					var next_sugg = document.getElementById("_ds_" + next_cursor.toString());
					if (next_sugg != null){
						if (G_SUGGESTION_CURSOR > 0){
		    				var current_sugg = document.getElementById("_ds_" + G_SUGGESTION_CURSOR.toString());
		    				current_sugg.style.backgroundColor = "";
							current_sugg.style.color="#000000";
						}
						next_sugg.style.backgroundColor="#3399FF";
						next_sugg.style.color="#ffffff";
					}
					G_SUGGESTION_CURSOR = next_cursor;
				}
				break;
			case 40: // down
				var next_cursor;
				if (G_RESULT_COUNT > 0){
					if (G_SUGGESTION_CURSOR < G_RESULT_COUNT){
						next_cursor = G_SUGGESTION_CURSOR + 1;
					}else if (G_SUGGESTION_CURSOR == G_RESULT_COUNT && G_RESULT_COUNT > 1){
						next_cursor = 1;
					}else{
						return;
						break;
					}

					var next_sugg = document.getElementById("_ds_" + next_cursor.toString());
					if (next_sugg != null){
						if (G_SUGGESTION_CURSOR > 0){
		    				var current_sugg = document.getElementById("_ds_" + G_SUGGESTION_CURSOR.toString());
		    				current_sugg.style.backgroundColor = "";
							current_sugg.style.color="#000000";
						}
						next_sugg.style.backgroundColor="#3399FF";
						next_sugg.style.color="#ffffff";
					}
					G_SUGGESTION_CURSOR = next_cursor;
				}
				break;
			default:
				if (G_TRACTOR_HANDLE == 0)
					G_TRACTOR_HANDLE = setInterval("suggestRegTracker()",200);
				break;
		}
	};
}

function suggestSearch(v){
	G_SEARCH_READY = 0;
	G_LAST_SEARCH_KEYWORDS = v;
	var query_arr = new Array(estr(v),G_SEARCH_TYPE);
	xajax_do_req_header(query_arr,1);
}

function close_suggestions(){
	window.clearInterval(G_TRACTOR_HANDLE);
	G_TRACTOR_HANDLE = 0;
	G_SEARCH_READY = 1;
	G_RESULT_COUNT = 0;
	G_SUGGESTION_CURSOR = 0;
	//G_LAST_SEARCH_KEYWORDS = "";
	//document.getElementById("search_suggestion").innerHTML = "";
	document.getElementById("suggestion_containter").style.display = "none";
}

function assign_keywords(id){
	trigerObj.value = document.getElementById("_ak_" + id.toString()).value;
	close_suggestions();
}

function suggestion_moveover(obj){
	if (G_SUGGESTION_CURSOR > 0){
		var last_obj = document.getElementById("_ds_" + G_SUGGESTION_CURSOR.toString());
		suggestion_moveout(last_obj);
		var reg = new RegExp("-","g");
		$("#searchTemp").val(obj.innerHTML.replace(reg, "++"));
	}
	var reg = new RegExp("-","g");
	$("#keywords").val(obj.innerHTML.replace(reg, "++"));
	obj.style.backgroundColor="#3399FF";
	obj.style.color="#ffffff";
	obj.style.cursor="pointer";

}

function suggestion_moveout(obj){
	obj.style.backgroundColor = "";
	obj.style.color="#000000";
}

// 获得cookie
function get_COOKIE_INFO(cookie_name){
	var strCookie = document.cookie;
	var arrCookie = strCookie.split("; ");
	var arrCookieCount = arrCookie.length;
	var arr,identifyContent = null;
	for(var i = 0; i < arrCookie.length ; i++){
		arr = arrCookie[i].split("=");
		if(cookie_name == arr[0]){
			var arrStr = document.cookie.split("; ");
			identifyContent = decodeURIComponent(decodeURIComponent(arr[1]));
			break;
		}
	}
	arrCookie = null;
	if (identifyContent == null)
		return null;
	else
		return identifyContent;
}

//设置cookie 默认cookie时效24小时
function set_COOKIE_INFO(cookie_name,cookie_value,cookie_time) {
	var exp = new Date();
	exp.setTime(exp.getTime() + cookie_time * 1000);
	if (cookie_time == 0)
		document.cookie = cookie_name + "=" + encodeURI(cookie_value) + ";path=/;domain=" + global.cookie_domain + ";";
	else
		document.cookie = cookie_name + "=" + encodeURI(cookie_value) + ";expires=" + exp.toGMTString() + ";path=/;domain=" + global.cookie_domain + ";";
}

//删除cookie
function omniture_cookie_del(){
	var ev21 = get_COOKIE_INFO("ev21");
	if (ev21 != null){
		set_COOKIE_INFO("ev21",ev21,-1000);
	}
}

//公共的方法
var common = {

	/*
		获得验证码
		flg 1：得到4位验证码 2：得到2位验证码
		id: img标签的id名称
		span: 是否生成img标签
	*/
	captcha: function(flg, id, span) {
        if (flg == 1)
            var img = "/core/yzm.php?code=" + Math.ceil(Math.random() * 10000);
        else
            var img = "/core/yzm.php?len=2&code=" + Math.ceil(Math.random() * 10000);
	    if (span) {
	    	$("#" + span).html("<img onclick=\"common.captcha(" + flg + ", '" + id + "')\" title=\"看不到验证码？点击重新换一个\" style=\"cursor:pointer;\" id=\"" + id + "\" src=\"" + img + "\" />&nbsp;");
	    } else {
	    	$("#" + id).attr("src", img);
	    }

	},
	
	//订单提交刷新验证码
	refreshYzm: function (id) {
		var yzm_array = new Array(
			'ca1ecb8010456f7f999ba885cff14e48','ba3dee3f701f3d8f62eb6c07d35372ba','8bda62759c24fc39549bffc47bc1323e',
			'db217a84a9d6018488a63fe5b9c26b64','8aaa8c54085dcb53d540b8fdff25d8f3','88623c1e858009d8da93691fe5b342f2',
			'6ffd8f44f663ce5830358fe9923f2dbf','3f4acfa6531402202e10465040988d74','ab96cdccb13fa78c309525dfd0c8597e',
			'f467793d3e076e7b9c567e326d310517','462aad889c048cbe252ae61691a8846f','6ecec93a64d5b7986d52e17ad513d1e3',
			'645baa7f76143cbd7e245514aee485d5','7c4e4d31c2c7d9504ef628e436f5f53d','f8a2cc3f4bb335b80a246d285132a979',
			'7c4a11927cd0af55533bf5c4f891c4b9','87a6b76cf6d3778d368e740d027dc4bf','7f93ceb1c36b9fb8b4415492b78eb175',
			'45d8116aea27a263fdba1e4d54a10ad2','fdc9fe9835bbabf97c864abf729eb1db','8e56654533979769f568e29085aadf1e',
			'cacc7073990a04ea731565ef9ea3c694','b261277913be1571b935f5871e80796a','b03ba89f3c7b598fc0e20efc2e7c9f62',
			'9b839b9c1d1066dc239ed4e986fad9b9','f46f897e4f8dbdfe05663ac7bd1d2ef3','1bc4e62492bdcd023b9f1304fae0e1a9',
			'5f3ce1e2eb57b3b68a83c05131340674','c0feffce7271950d9c637c5add07ec3c','ac3e2cd8fc170ad5f6fe4717c7555b0a',
			'f71f7bc25b02713ceae55ca5e9fa1c0d','473aea00a98197d47a8d4d72b9f741bc','abb005d9eebd8c00511a329821198381',
			'b206d14e7c242d596f948aa373663009','9b0709c6a83c0a40e4656e209cd6a7cb','7574ed22ede3b3a051c3d6d42bcaedb5',
			'f9e9c7ef6e5bfcc819a4fd091e8d9ea8','5288d06c512b497d4925a57e4bea7289','829f863a759c4d80a8beefebc175c0af',
			'd792166e0175297ec123c49cb644d7b6','f973b24610f50aa66de9c313d06900b5','4279590a34bf29018d58ea956138a024',
			'a8771a07f73f2701de1a0ada235e755d','88b85638eee90248c4242fe0cbb1c518','227491fea17383fb59d8b47af4c14a04',
			'9106032239fbf0669a8b76e94d36ffae','24a0c59f556c71f35a111c9c2b2356e3','8d9a6e908ed2b731fb96151d9bb94d49',
			'8152a272a43b63f7b0147039fbc8149c','56ebd6c4d722f898e928e365d9a95c6f','04a670a35434d8a0ed10c60e9acbaad5',
			'114ebb675efaf15009ac794d82cdd0ab','d9f0ab66a453830eee6f73fb070b7350','8cd18a0dea9a9980b60d9c534f792a7c',
			'1654e81cb0cc9e3e79624b84541034b2','5c0cb32af1dc3d1dac1c08870b8ebd3a','4c6b62f61a9d2075b43702d7d4f8de0d',
			'86f835eb203702e5d9d603beaf116f9f','c3f9d07abad82d34b3437d330afaea2d','2d7e07514cdf6d687df1d891f64583ab',
			'dc43ec3b5e2bb8b5c50b3b6f2534392d','f1bbe055c6cae376ec50fab33a8147c2','d3b952684ecb345341fd4fb27154789d',
			'2498b062e29271bd305bac2f27f71a98','5764f3f904f6500722bdcfbfca44d577','cb6c86ede31465d0af273476d54c7b91',
			'4a6756b5ee0ed10ef7883f1003636439','e49a1dbec7ae4f2bca624588f4098cba','1a0e1fc8bc4e1aa4634bb72abc745937',
			'9cfcdc0ccfdaa6ab31ea0fd25a48f86c','28ad8f3ee024bbde09eca7bcc3a3a4f3','028991b7a35b6a459d6e11b74bef056c',
			'a6ba1607f2d097af2c24addf2ef25609','e66f82658abfa5eee2344e85305b5215','8c67387d83e125b073f395bdfad5bb66',
			'6d77a959033dbbd30a740f9d448e8a4a','faceee3598123666636b32f16bec5254','acd66cde8b2d4dfde46320600cd56ac5',
			'2cb172a2b0ee0551d62c34752ec91f90','63ad8762789618a04686ad911f11f89b','60d41f84408877a5ceaa1dd8194efa2b',
			'308ad4020268e4e5a52cb1db04405b07','24849509814cfe78395faee2fa5f026b','abfc2bf2a90d8dff5c2ab8c9ff0bae88',
			'66c9a8415a27a962ee48a48f7f868a22','1f69e0d83e2b2632655e000adb554eea','dcfc194e5a2a63d3563377f34a47e6ce',
			'dd5ac8ce80f303da973825712419b78e','12e6c04ef937b0d9d225a20f9f408b58','4594aa03e2b661b1318ab9f4fe61408c',
			'5effff2a1e8c5739421b20c45268ab9e','73b31b5aa819118c23570eb7b48e9f66','a0670385b124f534d3987563710657c6',
			'11022993fe2c27ee602bd8ae4cbd5385','8b87d06b3b698fcef65d784bd18d6d1d','1761a6bd80bc8fff8e738f563861c160',
			'87cc3dd1d8c3242a017124ee601da5d4','d9dfea92cc665bb0bf3fce15f0d9d213','0a62a974a4fe97aa33b802573105cf88',
			'ec9fab478e229334627953935ade3473'
		);
		var randNum = parseInt(Math.random()*(99-0+0)+0);
		set_COOKIE_INFO('submit_bill_yzm', yzm_array[randNum], 0);
		var img = 'http://cdn.lining.com/postsystem/docroot/images/yzmImages/' + yzm_array[randNum] + '.jpg?code=' + Math.ceil(Math.random() * 10000);
		$('#' + id).attr('src', img);
	},

	//刷新页面
	refresh: function() {
		window.location.reload();
	},

	userBrowser: function(){
	    var browserName=navigator.userAgent.toLowerCase();
	    if(/msie/i.test(browserName) && !/opera/.test(browserName)){
	        return "IE";
	    }else if(/firefox/i.test(browserName)){
	        return "Firefox";
	    }else if(/chrome/i.test(browserName) && /webkit/i.test(browserName) && /mozilla/i.test(browserName)){
	        return "Chrome";
	    }else if(/opera/i.test(browserName)){
	        return "Opera";
	    }else if(/webkit/i.test(browserName) &&!(/chrome/i.test(browserName) && /webkit/i.test(browserName) && /mozilla/i.test(browserName))){
	        return "Safari";
	    }else{
	        return "unKnow";
	    }
	}

};

//用户
var customer = {

	//用户名
	cust_id: get_COOKIE_INFO("WANSONSHOP_IDENTIFIER"),

	//交通银行头部标识
	refid : get_COOKIE_INFO("refid"),

	//内购用户规则
	internalRule: ['li-ning.com.cn'],

	//内购用户标识 0:非内购用户; 1:内购用户;
	internal_flg: 0,

	//设置用户cookie
	set_cookie: function(val) {
        set_COOKIE_INFO("WANSONSHOP_IDENTIFIER", val, 0);
        set_COOKIE_INFO("WANSONSHOP_IDENTIFIER1", val, 0);
        this.cust_id = val;
	},

	//显示欢迎项
	show_IDENTIFIER: function() {

		if(this.refid != undefined && this.refid != null && this.refid == 'bankcomm'){

			//获取交行头和交行尾
			var bcm_header = this.bcm_header();
			var bcm_footer = this.bcm_footer();

			$('#note_list').html(bcm_header);
			$('#footer_list').html(bcm_footer);

			if (this.cust_id == null)
				$("#bcm_welcome").html("<a href='javascript:bankLoginOpen();' style='height:82px;line-height:82px;'>请登录</a>");
			else
				$("#bcm_welcome").html("欢迎您，" + this.cust_id + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"/shop/member_user.php\" target='_blank' style='color:red;'>我的订单</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"/shop/showcart.html\" target='_blank' style='color:red;'>购物车</a>　<a href=\"/shop/logout.php\">[注销]</a>");

			//隐藏
			$('.channel-nav2 .menu-list').width('1250px');

		}else{

			if (this.cust_id == null){
				$("#welcome").html("<a href=\"https://store.lining.com/shop/login.html\" style=\"font-family: SimHei;\">登录</a><div class=\"top_inter\"></div><a href=\"https://store.lining.com/shop/register.html\" style=\"font-family: SimHei;\">注册</a>");
			}else {
	            if (Utils.len(this.cust_id) > 20) {
	                this.cust_id = Utils.sb_substr(this.cust_id, 0, 18) + '...';
	            }
				$("#welcome").html("<a style=\"font-family: SimHei;\">欢迎您，" +this.cust_id+ "</a><div class=\"top_inter\"></div><a href=\"/shop/logout.php\" style=\"font-family: SimHei;\">注销</a>");
				unread.get_timestamp();
			}
		}
	},

	//交行头
	bcm_header : function(){
		var bcm_header_str = '<div style="background-color:white;">';
				bcm_header_str += '<div id="headerBCM" style="width:900px;margin:10 auto;">';
					bcm_header_str += '<div style="float:left;">';
						bcm_header_str += '<a style="margin-left:100px; width:250px;height:65px;background: #fff url(http://cdn.lining.com/shop/lining/theme/xth2/images/banks/bocom_header.gif) top left no-repeat;position:absolute;top:5px;left: 73px;" href="http://club.bankcomm.com" target="_blank"></a>';
					bcm_header_str += '</div>';
					bcm_header_str += '<div style="float:left;margin-left:480px;height:82px;line-height:82px;cursor:pointer;" id="bcm_welcome">请登录</div>';
					bcm_header_str += '<div style="float:right;">';
						bcm_header_str += '<a style="margin-top:10px;width:300px;height:60px;background: #fff url(http://cdn.lining.com/shop/lining/theme/xth2/images/banks/bocom_header.png) bottom left no-repeat;position:absolute;top:0;right:0;" href="http://club.bankcomm.com" target="_blank"></a>';
					bcm_header_str += '</div>';
					bcm_header_str += '<div style="clear:both;"></div>';
				bcm_header_str += '</div>';
			bcm_header_str += '</div>';

		return bcm_header_str;
	},

	//交行尾
	bcm_footer : function(){
		var bcm_footer_str = '';

		return bcm_footer_str;
	},

	//登录/注册弹出层切换 1:登录 2：注册
	switchover: function(obj, flg) {
		$("#h3_open_reglogin").find("span").removeClass("now").eq($(obj).index()).addClass("now");
		if (flg == 1) {
			$("#span_open_login").removeClass("hide");
			$("#span_open_reg").addClass("hide");
			var yzmObj = $('#yzm_img_pop');
			get_yzm_comm(yzmObj);
//			common.captcha(1, "login_yzm_img", "login_yzm_span");
		} else {
			$("#span_open_login").addClass("hide");
			$("#span_open_reg").removeClass("hide");
			var yzmObj = $('#yzm_reg_pop');
			get_yzm_comm(yzmObj);
//			common.captcha(1, "reg_yzm_img", "reg_yzm_span");
		}
		$(".pspt_msg").text("");
	},

	//打开登录/注册弹出层
	open_login: function(success_func) {

		if (this.cust_id == null) {
			$("#login_userName").focus();
		} else {
			$("#login_userName").val(this.cust_id);
			$("#login_pwd").focus();
		}

		$(".span_open_login").trigger("click");

		if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style) {
			$("#login_main").css("top", parseInt($(document).scrollTop()) + 200 + "px");
		}

		this.success_func = success_func;

		getsize();
		$('#login_main').removeClass("hide");
		var yzmObj = $('#yzm_img_pop');
		get_yzm_comm(yzmObj);
	},

	//关闭登录/注册弹出层
	close_login: function() {
		$("#login_main").css("display","none");
		$("#r_lining_hide").css("display","none");
		$("#r_account_hide").css("display","none");
		$("#r_collect_hide").css("display","none");
	},

	userName_empty: "请输入会员名称",
	userName_error: "请输入正确的会员名称",
	userName_exist: "该会员名称已被使用，请使用其它会员名称",
	pwd_empty: "请输入密码",
	pwd_error: "密码只能是6-30位英文、数字及“_”、“-”组成",
	pwd2_error: "您两次输入的密码不一致",
	yzm_empty: "请输入验证码",
	yzm_error: "输入的验证码错误",
	flg: 1,//1：正常登录页登录 2：弹出层登录
	prefix: "",
	//客户登录
	login: function(flg) {

		if (flg == 1) {
			this.prefix = "#";
		} else {
			this.prefix = "#login_";
		}
		this.flg = flg;
		var params = {
			userName: Utils.trim($(this.prefix + "userName").val()),
			pwd: Utils.trim($(this.prefix + "pwd").val()),
			yzm: Utils.trim($(this.prefix + "yzm").val())
		};
		var reg = /^[A-Za-z0-9_-]{6,30}$/;
		var error_arr = {},error_msg = true;

		if (Utils.isEmpty(params.userName)) {
			error_arr[this.prefix + "userName_error"] = this.userName_empty;
		} 
		if (Utils.isEmpty(params.pwd)) {
			error_arr[this.prefix + "pwd_error"] = this.pwd_empty;
		} else if (!reg.test(params.pwd)) {
			error_arr[this.prefix + "pwd_error"] = this.pwd_error;
		}
		if (Utils.isEmpty(params.yzm)) {
			error_arr[this.prefix + "yzm_error"] = this.yzm_empty;
		} else if (params.yzm.length != 4) {
			error_arr[this.prefix + "yzm_error"] = this.yzm_error;
		}

		$.each(error_arr,function(k, v) {
			$(k).addClass("note").text(v);
			error_msg = false;
		});

		if (error_msg) {
                    $(this.prefix + "userName_error").removeClass("note").text("");
                    $(this.prefix + "pwd_error").removeClass("note").text("");
                    $(this.prefix + "yzm_error").removeClass("note").text("");
                    $(this.prefix + "login_btn").hide();
                    $(this.prefix + "login_error").text("正在登录，请稍候...").show();

                    comm_box.loadding();
                    var username = params.userName;
                    var password = params.pwd;
                    $.ajax({
                        type: 'POST',
                        url: 'user_login.action',
                        //dataType:'json',
                        data:{username:username,password:password},
                        timeout: 15000,
                        success: function (data){
                        	window.location.href="index.jsp";
                            comm_box.loadding_close();
                            if (result_obj.code != 200){
                            	
                                var code = Number(result_obj.data);
                                /*if (flg == 1){
                                    var yzmObj = $('#yzm_img');
                                    get_yzm_img(yzmObj);
                                }else {
                                    var yzmObj = $('#yzm_img_pop');
                                    get_yzm_comm(yzmObj);
                                }
                                customer.login_fail(code);*/
                                
                            }else{
                                var userName = result_obj.data.userName;
                                var islevelUp = (result_obj.data.islevelUp) ? Number(result_obj.data.islevelUp) : 0;
                                var upGrade = result_obj.data.upGrade;
                                var births = result_obj.data.births;                           
                                if(upGrade){
                                	customer.updateGrade(userName,upGrade);////会员等级升级礼包
                                }else if(births == 1 || births == 2){//是当前生日或可旧订单换礼品
                                    customer.birthsRemind(userName,islevelUp,births);
                                }else{
                                    customer.login_succeed(userName,islevelUp);
                                }       
                            }
                        },
                        error: function( XMLHttpRequest, textStatus, errorThrown ) {
                            comm_box.loadding_close();
                            switch (params) {
                                case 'timeout':
                                    $(this.prefix + "login_btn").show();
                                    $(this.prefix + "login_error").text("");	
                                case 'error':
                                    comm_alert('网络爆棚，请重新尝试！');
                                break;
                                default:
                                break;
                            }
                            if (flg == 1){
                                var yzmObj = $('#yzm_img');
                                //get_yzm_img(yzmObj);
                            }else {
                                var yzmObj = $('#yzm_img_pop');
                               // get_yzm_comm(yzmObj);
                            }
                        }
                    });
		}
	},

	//手机登录
	moblogin: function(flg){
		if (flg == 1) {
			this.prefix = "#";
		} else {
			this.prefix = "#login_";
		}

		this.flg = flg;
		var params = {
			mobile: Utils.trim($(this.prefix + "reg_mobile").val()),
			dxyzm: Utils.trim($(this.prefix + "dxyzm").val())
		};
		
		var error_arr = {},error_msg = true;

		if (Utils.isEmpty(params.mobile)) {
			error_arr[this.prefix + "dxmobile_error"] = '请输入手机号';
		} else if (!Utils.isMobile(params.mobile)){
			error_arr[this.prefix + "dxmobile_error"] = '手机号不符和规则';
		}
		if (Utils.isEmpty(params.dxyzm)) {
			error_arr[this.prefix + "dxyzm_error"] = '请输入短信验证码';
		}

		$.each(error_arr,function(k, v) {
			$(k).addClass("note").text(v);
			error_msg = false;
		});

		if (error_msg) {
			$(this.prefix + "dxmobile_error").removeClass("note").text("");
			$(this.prefix + "dxyzm_error").removeClass("note").text("");
			$(this.prefix + "login_btn").hide();
			$(this.prefix + "login_error").text("正在登录，请稍候...").show();
			
			comm_box.loadding();
	        $.ajax({
	            type: 'POST',
	            url: '/shop/comm_tplfun/xajax_commUser_pro.php?operFlg=6',
	            dataType: 'json',
	            data: params,
	            timeout: 15000,
	            success: function (result_obj){
	                comm_box.loadding_close();
	                if (result_obj.code != 200){
                            comm_alert(result_obj.message);
                            $(this.prefix + "login_btn").show();
                            $(this.prefix + "login_error").text("");
                            var imgObj = $('#yzm_pic');
                            get_yzm_img(imgObj);
	                }else {
	                    var userName = result_obj.data.userName;
	                    var islevelUp = (result_obj.data.islevelUp) ? Number(result_obj.data.islevelUp) : 0;
	                    var upGrade = result_obj.data.upGrade;
                        var births = result_obj.data.births;                           
                        if(upGrade){
                        	customer.updateGrade(userName,upGrade);//会员等级升级礼包
                        }else if(births == 1 || births == 2){//是当前生日或可旧订单换礼品
                            customer.birthsRemind(userName,islevelUp,births);
	                    }else{
	                    	customer.login_succeed(userName,islevelUp);
	                    } 
	                }
	            },
	            error: function( XMLHttpRequest, textStatus, errorThrown ) {
	                comm_box.loadding_close();
	                switch (params) {
	                    case 'timeout':
	                    	$(this.prefix + "login_btn").show();
							$(this.prefix + "login_error").text("");	
	                    case 'error':
	                        comm_alert('网络爆棚，请重新尝试！');
	                    break;
	                    default:
	                    break;
	                }
                        var imgObj = $('#yzm_pic');
                        get_yzm_img(imgObj);
	            }
	        });
		}
	},
	
	//登录失败
	login_fail: function(code) {
		if(code <0){
			comm_box.loadding_close();
		}
		$(this.prefix + "login_btn").show();
		$(this.prefix + "login_error").text("");
		if (code == -1) {
			$(this.prefix + "userName_error").addClass("note").text(this.userName_error);
		} else if (code == -2) {
			$(this.prefix + "pwd_error").addClass("note").text(this.userName_error);
		} else if (code == -3 || code == -4) {
			$(this.prefix + "pwd_error").addClass("note").text("用户名与密码不匹配");
		} else if (code == -5) {
			$(this.prefix + "userName_error").addClass("note").text('您的帐户已被禁用，请联系网站客服');
		} else if (code == -6) {
			$(this.prefix + "userName_error").addClass("note").text("用户名不存在");
		} else if (code == -100 || code == -101) {
			$(this.prefix + "login_error").text("参数错误，请重试或联系网站客服").show();
		} else if (code == -102 || code == -103) {
			$(this.prefix + "yzm_error").addClass("note").text(this.yzm_error);
		} else if (code == -105) {
			$(this.prefix + "login_error").text("网络链接超时，请重试登录").show();
		}
		if (code != -105 && code != -100 && code != -101) {
			$(this.prefix + "login_error").text("").hide();
		}	
		$(this.prefix + "yzm").val("");

//		var yzmFunc = $("#yzm_img").attr("onclick");
//		if(yzmFunc == "get_yzm_img()"){
//			get_yzm_img();
//		}else{
//			get_yzm_comm();
//		}
		
	},
	//births是否满足显示生日卡
	birthsRemind: function(userName,islevelUp,births){
		if(births == 1 || births == 2){
			birthRemind(births,userName);
		}
	},
	
	//升级礼包
	updateGrade: function(userName,upGrade){
		custUpgrade(userName,upGrade);
	},
	
	//登录成功islevelUp老会员等级需要升级判断0：不要升级1：需要升级 
	login_succeed: function(userName,islevelUp){
		customer.set_cookie(userName);
		if (this.flg == 1) {
			if (common.userBrowser() == "Safari"){
				location.replace(customer.last_url);
			}else{
				self.location = customer.last_url;
			}				
		} else {
			this.close_login();
			customer.show_IDENTIFIER();
			eval(this.success_func + "()");
		}

	},
	//内购用户显示提示层
	login_purchase_succeed: function(userArray,islevelUp){
		if (userArray.notice == 1) {
			this.purchase_name = userArray.userName;
			$('#purchase_login').removeClass('hide');
		} else {
			customer.login_succeed(userArray.userName, '');
		}
	},

	//内购用户确定提示
	confirm_purchase: function(flg){

		$('#purchase_login').addClass('hide');
		if (flg == 2) {
			//判断是否勾选了多选框
			var purchaseNotice = $('#purchaseNotice').attr('checked');
			if (purchaseNotice == 'checked') {
				xajax_do_req_user(this.purchase_name, 12);
			} else {
				customer.login_succeed(this.purchase_name, '');
			}
		} else {
			customer.login_succeed(this.purchase_name, '');
		}
	},

	joinClub :function(){
		var params={};
		var mobile = Utils.trim($("#mobile").val());
		if(mobile != ''){
			if(!Utils.isMobile(mobile)){
				$('#send_span').html('<span><font color="red">请填写正确的手机号</font></span>');
				return false;
			}
		}
		var birthYear = Utils.trim($("#birthYear").val());
		var birthMonth = Utils.trim($("#birthMonth").val());
		var birthDay = Utils.trim($("#birthDay").val());
		if(birthYear == '' || birthMonth=='' || birthDay==''){
			$('.birthRes').html('<span><font color="red">请填写生日</font></span>');
			return false;
		}
		var birth=birthYear+'-'+birthMonth+"-"+birthDay;//出生年月日
		var applyZone='';
		for(var i=0;i<$(".applyZone:checked").length;i++){
			applyZone += $(".applyZone:checked")[i].value+',';
		}
		if(applyZone == ''){
			$('.applyRes').html('<span><font color="red">申请加入的营区未选择</font></span>');
			return false;
		}
		var zoneLength =applyZone.length;
		applyZone = applyZone.slice(0,zoneLength-1);
		if($(".desicion:checked").length == 0){
			$('.applyRes').html('<span><font color="red">未加入宁盟俱乐部</font></span>');
			return false;
		}
		params['mobile'] = mobile;
		params['birth'] = birth;
		params['applyZone'] = applyZone;
		params['mobile'] = mobile;
		params['isChk'] = sms.isChk;
		xajax_do_req_user(params,7);
	},
	
	joinResult : function(code){
		if(code == -1){
			$(".lastRes").html('<span><font color="red">参数错误，请联系网站客服!</font></span>');
		}else if(code == -2){
			$(".lastRes").html('<span><font color="red">请填写正确的手机号!</font></span>');
		}else if(code == -3){
			$(".lastRes").html('<span><font color="red">参数错误，用户不存在!</font></span>');
		}else if(code == -4){
			$(".lastRes").html('<span><font color="red">优惠券信息未找到!</font></span>');
		}else if(code == 1){
			cancel_join();
		}else{
			$(".lastRes").html('<span><font color="red">'+code+'</font></span>');
		}
	},
	userNameExist: false,
	
	//新的注册
	verify_register: function(flg, step){
		
		this.flg = flg;
		
		var error_flg = 0;

		if (flg == 1) {	//注册页
			this.prefix = "#";
		} else {	//弹出层注册
			this.prefix = "#reg_";
		}
		
		//获取参数
		var params = {
			userName: Utils.trim($(this.prefix + "userName").val()),
			pwd: Utils.trim($(this.prefix + "pwd").val()),
			pwd2: Utils.trim($(this.prefix + "pwd2").val()),
			mobile: Utils.trim($(this.prefix + "mobile").val()),
			dxyzm: Utils.trim($(this.prefix + "smsCode").val())
		};

		//检查用户名
		if (!Utils.reg_userName(params.userName)) {
			
			error_flg = 1;
			
			if (flg == 1) {
				$(this.prefix + "userName_error").addClass("note").html('4-40位字符，支持汉字、字母、数字及“－”、“_”、“.”、“@”组合');
				$(this.prefix + "userName_error").css({'lineHeight': '17px', 'paddingTop': '4px'}).addClass('reg_boxModel');
				$(this.prefix + 'userName_error').next('.reg-newUser-notice')
												 .html('<img src="../images/reg_error.png" width="15px" height="15px">')
												 .css('display', 'inline-block');
			} else {
				$(this.prefix + "userName_error").html('4-40位字符，支持汉字、字母、数字及“－”、“_”、“.”、“@”组合');
				$(this.prefix + "userName_error").next('.reg-newUser-notice')
				                                 .html('<img src="../images/reg_error.png" width="15px" height="15px">')
				                                 .css('display', 'inline-block');
			}
								
		} else if (customer.userNameExist == true) {
			error_flg = 1;
			
			if (flg == 1) {
				$(this.prefix + "userName_error").addClass("note").html('该用户名已被使用，请重新输入。<br/>如果您是该用户，请立即&nbsp;<a href="https://store.lining.com/shop/login.html" style="color:#EE2737;font-size:12px;text-decoration:underline;">登录</a>');
				$(this.prefix + "userName_error").css({'lineHeight': '17px', 'paddingTop': '4px'}).addClass('reg_boxModel');
				$(this.prefix + 'userName_error').next('.reg-newUser-notice')
												 .html('<img src="../images/reg_error.png" width="15px" height="15px">')
												 .css('display', 'inline-block');
			} else {
				$(this.prefix + "userName_error").html('该用户名已被使用，请重新输入。<br/>如果您是该用户，请立即&nbsp;<a href="https://store.lining.com/shop/login.html" style="color:#EE2737;font-size:12px;text-decoration:underline;">登录</a>');
				$(this.prefix + "userName_error").next('.reg-newUser-notice')
					                             .html('<img src="../images/reg_error.png" width="15px" height="15px">')
					                             .css('display', 'inline-block');
			}
		}
		
		//检查密码
		var reg = /^[A-Za-z0-9]{6,20}$/;
		if (!reg.test(params.pwd)) {
			error_flg = 1;
			
			if (flg == 1) {
				$(this.prefix + "pwd_error").addClass("note").text('密码是由6－20位字符组成，建议由字母，数字和符合两种以上组合');	
				$(this.prefix + "pwd_error").css({'lineHeight': '17px', 'paddingTop': '4px'}).addClass('reg_boxModel');
				$(this.prefix + "pwd_error").next('.reg-newUser-notice')
											.html('<img src="../images/reg_error.png" width="15px" height="15px">')
											.css('display', 'inline-block');
			} else {
				$(this.prefix + "pwd_error").text('密码是由6－20位字符组成，建议由字母，数字和符合两种以上组合');
				$(this.prefix + "pwd_error").next('.reg-newUser-notice')
				                   		    .html('<img src="../images/reg_error.png" width="15px" height="15px">')
				                   			.css('display', 'inline-block');
			}
		}
		
		//检查确认密码
		if (params.pwd != params.pwd2) {
			
			error_flg = 1;
			
			if (flg == 1) {
				$(this.prefix + "pwd2_error").addClass("note").text('您两次输入的密码不一致');
				$(this.prefix + "pwd2_error").css({'lineHeight': '44px', 'paddingTop': '0'}).removeClass('reg_boxModel');
				$(this.prefix + 'pwd2_error').next('.reg-newUser-notice')
										 	 .html('<img src="../images/reg_error.png" width="15px" height="15px">')
										 	 .css('display', 'inline-block');
			} else {
				$(this.prefix + "pwd2_error").addClass("note").text('您两次输入的密码不一致');
				$(this.prefix + "pwd2_error").css({'lineHeight': '35px'});
				$(this.prefix + "pwd2_error").next('.reg-newUser-notice')
				                    		 .html('<img src="../images/reg_error.png" width="15px" height="15px">')
				                    		 .css('display', 'inline-block');
			}
		}
		
		//验证手机
		if (!Utils.isMobile(params.mobile)) {
			
			error_flg = 1;
			
			if (flg == 1) {
				$(this.prefix + "mobile_error").addClass("note").html("请输入正确的手机号码");	
				$(this.prefix + "mobile_error").css({'lineHeight': '44px', 'paddingTop': '0'}).removeClass('reg_boxModel');
				$(this.prefix + 'mobile_error').next('.reg-newUser-notice')
				                  			   .html('<img src="../images/reg_error.png" width="15px" height="15px">')
				                  			   .css('display', 'inline-block');
			} else {
				$(this.prefix + "mobile_error").addClass("note").html("请输入正确的手机号码");	
				$(this.prefix + "mobile_error").css({'lineHeight': '35px'});
				$(this.prefix + "mobile_error").next('.reg-newUser-notice')
				                      		   .html('<img src="../images/reg_error.png" width="15px" height="15px">')
				                      		   .css('display', 'inline-block');
			}
		} else if (reg_mobile_flg == 1) {
			
			error_flg = 1;
			
			if (flg == 1) {
				$(this.prefix + "mobile_error").addClass("note").html('该手机号已与官网帐号绑定，是否&nbsp;<a href="https://store.lining.com/shop/login.html" style="color:#EE2737;font-size:12px;text-decoration:underline;">登录</a>');	
				$(this.prefix + "mobile_error").css({'lineHeight': '17px', 'paddingTop': '4px'}).addClass('reg_boxModel');
				$(this.prefix + 'mobile_error').next('.reg-newUser-notice')
				                  			   .html('<img src="../images/reg_error.png" width="15px" height="15px">')
				                  			   .css('display', 'inline-block');
			} else {
				$(this.prefix + "mobile_error").html('该手机号已与官网帐号绑定，是否<br/><a href="https://store.lining.com/shop/login.html" style="color:#EE2737;font-size:12px;text-decoration:underline;">登录</a>');
				$(this.prefix + "mobile_error").css({'lineHeight': '17px'});
				$(this.prefix + "mobile_error").next('.reg-newUser-notice')
				                      		   .html('<img src="../images/reg_error.png" width="15px" height="15px">')
				                               .css('display', 'inline-block');
			}
		}
		
		//回到顶部
		if (error_flg == 1 && flg == 1) {
			document.documentElement.scrollTop = 0;
		}
		
		//短信验证码
		if (error_flg == 0) {
			if (params.dxyzm == '') {
				comm_alert('请输入短信验证码');
				return false;
			}
		}
		
		//如果是第一步则停止
		params.step = step;
		params.source = flg;
		
		//第二步
		if (error_flg == 1) {
			return false;
		}
		
		//邮箱
		var email = Utils.trim($(this.prefix + "email").val());
		if (email != '') {
			if(!Utils.isEmail(email)){
				comm_alert('请填写正确的邮箱地址');
				return false;
			}
		}
		params.email = email;
		comm_box.loadding();
		
		$.ajax({
                    type: 'POST',
                    url: '/shop/comm_tplfun/xajax_commUser_pro.php?operFlg=4',
                    dataType: 'json',
                    data: params,
                    timeout: 15000,
                    success: function (result_obj){
                        comm_box.loadding_close();
                        if (result_obj.code != 200){
                            var code = Number(result_obj.data);
                            var msg = result_obj.message;
                            if (msg){
                                comm_alert(msg);
                            }
                            
                            if (flg == 1){
                                var yzmObj = $('#yzm_img');
                                get_yzm_img(yzmObj);
                            }else {
                                var yzmObj = $('#yzm_img_pop');
                                get_yzm_comm(yzmObj);
                            }
                            
                            if (code){
                                customer.verify_register_fail(code);
                            }
                            
                        }else {
                            var userName = result_obj.data.userName;
                            var cardFlg = (result_obj.data.cardFlg) ? Number(result_obj.data.cardFlg) : 1;
                            customer.register_success(userName,cardFlg);
                        }
                    },
                    error: function( XMLHttpRequest, textStatus, errorThrown ) {
                        comm_box.loadding_close();
                        switch (params) {
                            case 'timeout':
                            case 'error':
                                comm_alert('网络爆棚，请重新尝试！');
                            break;
                            default:
                            break;
                        }
                        if (flg == 1){
                            var yzmObj = $('#yzm_img');
                            get_yzm_img(yzmObj);
                        }else {
                            var yzmObj = $('#yzm_img_pop');
                            get_yzm_comm(yzmObj);
                        }
                    }
                });
	},
	
	verify_register_fail: function(code){
		
		comm_box.loadding_close();
		
		if (code == -1) {
			$(this.prefix + "userName_error").addClass("note").html('4-40位字符，支持汉字、字母、数字及“－”、“_”、“.”、“@”组合');
			$(this.prefix + "userName_error").css({'lineHeight': '17px', 'paddingTop': '4px'}).addClass('reg_boxModel');
			$(this.prefix + 'userName_error').next('.reg-newUser-notice')
				                			 .html('<img src="../images/reg_error.png" width="15px" height="15px">')				                			
				                			 .css('display', 'inline-block');
		} else if (code == -2) {
			$(this.prefix + "pwd_error").addClass("note").text('密码是由6－20位字符组成，建议由字母，数字和符合两种以上组合');	
			$(this.prefix + "pwd_error").css({'lineHeight': '17px', 'paddingTop': '4px'}).addClass('reg_boxModel');
			$(this.prefix + 'pwd_error').next('.reg-newUser-notice')
			               				.html('<img src="../images/reg_error.png" width="15px" height="15px">')
			               				.css('display', 'inline-block');
		} else if (code == -3) {
			$(this.prefix + "pwd2_error").addClass("note").text('您两次输入的密码不一致');
			$(this.prefix + "pwd2_error").css({'lineHeight': '44px', 'paddingTop': '0'}).removeClass('reg_boxModel');
			$(this.prefix + 'pwd2_error').next('.reg-newUser-notice')
			                			 .html('<img src="../images/reg_error.png" width="15px" height="15px">')
			                			 .css('display', 'inline-block');
		} else if (code == -4) {
			$(this.prefix + "userName_error").addClass("note").html('该用户名已被使用，请重新输入。<br/>如果您是该用户，请立即&nbsp;<a href="https://store.lining.com/shop/login.html" style="color:#EE2737;font-size:12px;text-decoration:underline;">登录</a>');
			$(this.prefix + "userName_error").css({'lineHeight': '17px', 'paddingTop': '4px'}).addClass('reg_boxModel');
			$(this.prefix + 'userName_error').next('.reg-newUser-notice')
				                			 .html('<img src="../images/reg_error.png" width="15px" height="15px">')
				                			 .css('display', 'inline-block');
		} else if (code == -5) {
			$(this.prefix + "mobile_error").addClass("note").html("请输入正确的手机号码");	
			$(this.prefix + "mobile_error").css({'lineHeight': '44px', 'paddingTop': '0'}).removeClass('reg_boxModel');
			$(this.prefix + 'mobile_error').next('.reg-newUser-notice')
			                  .html('<img src="../images/reg_error.png" width="15px" height="15px">')
			                  .css('display', 'inline-block');
		} else if (code == -6) {
			$(this.prefix + "mobile_error").addClass("note").html('该手机号已与官网帐号绑定，是否&nbsp;<a href="https://store.lining.com/shop/login.html" style="color:#EE2737;font-size:12px;text-decoration:underline;">登录</a>');	
			$(this.prefix + "mobile_error").css({'lineHeight': '17px', 'paddingTop': '4px'}).addClass('reg_boxModel');
			$(this.prefix + 'mobile_error').next('.reg-newUser-notice')
			                  		       .html('<img src="../images/reg_error.png" width="15px" height="15px">')
			                  			   .css('display', 'inline-block');
			
			
		} else if (code == -7) {
			comm_alert('请输入正确的邮箱地址');
		} else if (code == -100) {
			comm_alert('缺少参数，请刷新页面后重新操作');
		} else if (code == -101 || code == -105 || code == -106) {
			comm_alert('注册失败');
		}
	},

	//用户注册第一步成功
	register_first :function(){
		comm_box.loadding_close();
		$("#regist_1").css('display','none');
		$("#regist_2").css('display','block');
	},
	
	//用户注册成功
	register_success: function(userName,cardFlg) {
		customer.set_cookie(userName);
		customer.show_IDENTIFIER();
		comm_box.loadding_close();
		//检测是否为内购用户
		for(var i=0;i<this.internalRule.length;i++){
			if(userName.indexOf(this.internalRule[i],0)>0){
				this.internal_flg = 1;
				break;
			}
		}

		//礼品券赠送成功
		var cardContent = '<p>亲爱的会员,注册送券</p>';
			cardContent += '<p>已将20元现金抵用券放至您的帐号，请至“我的李宁-礼品券”进行查询。祝您购物愉快！</p>';
		if (this.flg == 1) {
			if(this.internal_flg ==1){
				if(cardFlg == 0){
					if (common.userBrowser() == "Safari")
						location.replace("http://store.lining.com/shop/register_success.php");
					else
						self.location = "http://store.lining.com/shop/register_success.php";
				}else{
					comm_alert("内购用户注册成功,请注意查收邮件", 1, "http://store.lining.com/shop/register_success.php");
				}
			}else{
				if(cardFlg == 0){
					comm_alert(cardContent,1, "http://store.lining.com/shop/register_success.php");
				}else{
					if (common.userBrowser() == "Safari")
						location.replace("http://store.lining.com/shop/register_success.php");
					else
						self.location = "http://store.lining.com/shop/register_success.php";
				}
			}
		} else {
			this.close_login();
			if(this.internal_flg == 1){
				if(cardFlg == 0){
					comm_alert(cardContent,3);
				}else{
					comm_alert("内购用户注册成功,请注意查收邮件", 3);

					//调用品众统计
					customer.PZ_count();
				}
			}else{

				if(cardFlg == 0){
					comm_alert(cardContent,3);
				}else{
					comm_alert("用户注册成功", 3);
                    eval(this.success_func + "()");
					//调用品众统计
					customer.PZ_count();
				}
			}
		}
	},

	//品众大电话
	PZ_count : function(){
		_pzt.events.push({type:'target',category:'category',action:'reg',opt_label:'reg_variable',value:1});
		PzoomTracker.trackPageView();
	}

};

//未读信息
var unread = {

	//打开页面时的时间戳
	open_page_timestamp: "",

	//setInterval函数的ID
	unread_id: "",

	//获得打开页面时的时间戳
	get_timestamp: function() {
		var exp = new Date();
		this.open_page_timestamp = exp.getTime();
		set_COOKIE_INFO("OPEN_PAGE_TIMESTAMP", this.open_page_timestamp, 86400);
		if (this.unread_id != "")
			clearInterval(this.unread_id);
		this.search();
		this.unread_id = setInterval("unread.search()",30000);
	},

	//查询未读信息
	search: function() {
		if (this.open_page_timestamp == get_COOKIE_INFO("OPEN_PAGE_TIMESTAMP") && get_COOKIE_INFO("G_QUERY_UNREAD") != 0 && get_COOKIE_INFO("G_QUERY_UNREAD") != null) {
			xajax_do_req_header("", 4);
		}
	},

	//关闭未读信息
	close: function() {
		xajax_do_req_header("", 5);
	},

	clear: function(str) {
		switch (str) {
			case "unread_consultation":
				$("#unread_consultation").hide();
				break;
			case "unread_ask":
				$("#unread_ask").hide();
				break;
		}
		if ($("#unread_consultation").css("display") == "none" && $("#unread_ask").css("display") == "none") {
			$("#unread_yellowtip").hide();
		}
	}
};

//购物车
var cart = {

	//统计购物车商品数量
	goods_amount: function() {

		var cart_amount = get_COOKIE_INFO("CARTAMOUNT");

		if (cart_amount == null) {
			$("#js_cart_goods_number").text(0);
			$("#slide-goods-amount").text(0);
		} else {
			$("#js_cart_goods_number").text(cart_amount);
			$("#slide-goods-amount").text(cart_amount);
		}

	},

	//清空购物车
	empty: function() {
		comm_box.confirm_open("您确定清空购物车里面的所有商品吗？", "cart.confirm_empty");

	},

	//确认清空购物车
	confirm_empty: function() {
		xajax_do_req_cart('', 16);
	},

	//添加到购物车成功后，关闭弹出层，继续购物
	successClose: function() {
		$("#add_cart_success").hide();
	},

	//添加到购物车成功后，转跳到购物车页面
	successCart: function() {
		this.successClose();
		comm_box.loadding();
		if (common.userBrowser() == "Safari") {
			location.replace("/shop/showcart.html");
		} else {
			self.location = "/shop/showcart.html";
		}
	}

};



//普通商品购物车
var general_cart = {

	amount: 1,
	
	flg: 1,

	//添加普通商品
	//cartflg=1 立即购买
	//cartflg=2 加入购物车
	add: function(postID, amount, type, cartflg) {
		
		comm_box.loadding();

		//商品网购号(三级商品)
		if (postID == undefined || postID == null || postID == '') {
			var postID = this.later_postID;
		} else {
			id = $("#" + postID).val();
			if(id != undefined && postID != null && postID != ''){//详情页or再购买（再购买没有value值）
				postID = Utils.trim($("#" + postID).val());
			}			
			this.later_postID = postID;
		}
		/*
		if (t) {
			clearTimeout(t);
		}

		
		if (postID == 345202 || postID == 345203 || postID == 345204 || postID == 345205 || postID == 345206
		    || postID == 345207 || postID == 345208 || postID == 345209 || postID == 345210 || postID == 345211
		    || postID == 345212 || postID == 345213 || postID == 345214 || postID == 345215 || postID == 345216) {
		    	
		    $('#loadding > div').width('315px');	
		    $('#loadding .loadding_str').html('下单的小伙伴太多啦，服务器君正在排队维持秩序');
		    	
			var	appointKey = get_COOKIE_INFO('appointKey');
			if (appointKey == 'none') {
				var t = setTimeout("comm_box.loadding_close();comm_alert('排队超时，刷新页面重新排队')", 10000);
				return false;
			}
		}
		*/
		
		if (!postID || !Utils.isDigital(postID)) {
			comm_box.loadding_close();
			comm_alert('参数错误，请刷新页面重新购买');
			return false;
		}
		
		//购买数量
		if (amount == undefined || amount == null || amount == '') {
			this.amount = this.later_amount;
		} else {
			amount_val = $("#" + amount).val();
			if(amount_val != undefined && amount_val != null && amount_val != ''){//详情页or再购买（再购买没有value值）
				this.amount = $("#" + amount).val();
			}			
			this.later_amount = this.amount;
		}
		
		if (!this.amount || this.amount <= 0 || !Utils.isInt(this.amount.toString()) || this.amount.length > 2) {
			comm_box.loadding_close();
			comm_alert("输入的数量有误,应为[1-99]");
			$("#" + amount).val(1);
			return false;
		}

		//商品类型
		if (type == undefined || type == null || type == '') {
			var type = this.later_type;
		} else {
			this.later_type = type;
		}
		
		//按钮事件类型(立即购买 or 加入购物车)
		if (cartflg == undefined || cartflg == null || cartflg == '') {
			var cartflg = this.later_cartflg;
		} else {
			this.later_cartflg = cartflg;
		}

		$.ajax({
			type: 'POST',
			url: '/shop/comm_tplfun/ajax_commCart.php?openFlg=1',
			dataType: 'JSON',
			data: {
				postID: postID,
				amount: this.amount,
				type: type,
				cartflg: cartflg
			},
			timeout: 15000,
			success: function( o ) {
				
				if (o.code == 200) {
					comm_box.loadding_close();
					if (typeof o.data == 'string') {
						if (o.data == 'refid') {
							bankLoginOpen();
						} else if (o.data == 'general') {
							customer.open_login('general_cart.add');
						} else {
							window.location.href = '/shop/order.html';
						}
					} else {
						general_cart.success(1, o.data);
					}
				} else if (o.code == 201) {
					var t = setTimeout("comm_box.loadding_close();comm_alert('排队超时，刷新页面重新排队')", 10000);
				} else {
					comm_box.loadding_close();
					comm_alert(o.message);
				}
			},
			error: function( XMLHttpRequest, textStatus, errorThrown ) {
               comm_box.loadding_close();
                switch (textStatus) {
                    case 'timeout':
                    case 'error':
                        comm_alert('太多人剁手，网站繁忙，您先排排队吧！');
                    break;
                    default:
                    	
                    break;
                }
            }
		});
	},

	//定制商品加入购物车
	customAdd: function(postID, input_id, type) {
		this.flg = 0;
		if (!Utils.isDigital(postID)) {
			postID = Utils.trim($("#postID_306687").val());
		}
		if (input_id) {
			this.amount = $("#" + input_id).val();
			if (!this.amount || this.amount <= 0 || !Utils.isInt(this.amount.toString()) || this.amount.length > 2) {
				comm_alert("输入的数量有误,应为[1-99]");
				$("#" + input_id).val(1);
				return false;
			}
		}

		//var printType = $('#printType').val();

		comm_box.loadding();
		xajax_do_req_cart( [postID, this.amount, type, 0], 20 );
	},

	//添加或修改普通商品返回值（错误）
	fail: function (val, str) {
		comm_box.loadding_close();
		if (val == -1) {
			comm_alert("商品不存在或已下架");
		} else if (val == -2) {
			comm_alert("暂无库存，近期补货请及时关注");
		} else if (val == -3) {
			comm_alert("请选择收货区域",2);
		} else if (val == -4) {
			comm_alert("此商品为特价商品，只能购买" + str + "件");
		} else if (val == -5) {
			comm_alert("购买数量无效");
		} else if(val == -6){
			comm_alert("此商品已经积分兑换，不能再正常购买");
		} else if(val == -7){
			comm_alert("此商品已经正常购买，不能再积分兑换");
		} else if(val == -8){
			comm_alert("此商品为限购商品，指定会员才能购买");
		}else if(val == -9){
			comm_alert("此商品为赠品，不能正常购买");
		}else if(val == -10){
			comm_alert("此商品不是积分商品，不能兑换");
		}else if(val == -11){
			comm_alert("添加积分商品，请先登录");
		}else if(val == -12){
			comm_alert("此积分商品已经添加");
		}else if(val == -13){
			comm_alert("此积分商品只能购买一件");
		}else if(val == -14){
			comm_alert(str);
		}

		if (this.before_amount) {
			$("#buy_" + this.postID).val(this.before_amount);
		}
	},

	//添加普通商品返回值
	success: function(val, arr, type) {

		cart.goods_amount();

		if (this.flg) {
			comm_box.loadding_close();
	    	$("#cp_title").html("&nbsp;&nbsp;" + this.amount + "件商品加入购物车");
	    	if(arr[4] == 4)
	    		var listHtml = "<tr><td rowspan=\"5\"><img class=\"goods_img\" src=\"" + arr[2] + "\"></td><td class=\"tal blod\">" + arr[1] + "</td></tr>";
	    	else
	    		var listHtml = "<tr><td rowspan=\"4\"><img class=\"goods_img\" src=\"" + arr[2] + "\"></td><td class=\"tal blod\">" + arr[1] + "</td></tr>";
	    	listHtml += "<tr><td class=\"tal\">加入数量：<span class=\"red\">" + this.amount + "</span></td></tr>";
	    	listHtml += "<tr><td class=\"tal\">总计金额：<span class=\"red\">¥"+parseFloat(arr[0]).toFixed(2)+"</span></td></tr>";
	    	if(arr[4] == 4)
	    		listHtml += "<tr><td class=\"tal\">总计积分：<span class=\"red\">"+parseInt(arr[3])+"</span></td></tr>";
	    	listHtml += "<tr><td class=\"tal\" style=\"height: 50px;\">";
            listHtml += "<input type=\"button\" onclick=\"cart.successClose();\" value=\"继续购物\" class=\"input_action\">";
            listHtml += "<input type=\"button\" onclick=\"cart.successCart();\" value=\"去结算\" class=\"input_action_off ml10\"></td></tr>";
	    	$("#success_html").html(listHtml);
	    	$("#add_cart_success").show();
		} else {
			if (type == 1) {
				xajax_do_req_cart('showcart', 28);
			} else {
				xajax_do_req_cart('', 28);
			}
		}
	}

};

//商品收藏
var favorites = {

	postID: "",//商品网购号

	flg: 0,//1:购物车页面 2：商品收藏页面

	//收藏商品加入购物车
	cart: function(postID) {
		general_cart.flg = 0;
		general_cart.add(postID);
	},

	//添加商品收藏
	add: function(postID) {
		if (this.postID == "") {
			if (!Utils.isDigital(postID)) {
				comm_alert("参数错误");
				return false;
			}
			this.postID = postID;
		}
		if (customer.cust_id == null) {
			customer.open_login('favorites.add');
		} else {
			xajax_do_req_header(this.postID, 12);
		}
	},

	//删除商品收藏
	del: function(postID, flg) {
		this.postID = postID;
		this.flg = flg;
		xajax_do_req_header(postID, 13);
	},

	//商品收藏操作返回值
	result: function(result_code) {
		if (result_code == -1) {
			comm_alert("参数错误，请联系网站客服");
		} else if (result_code == -2) {
			comm_alert("该商品不存在");
		} else if (result_code == -3) {
			comm_alert("此商品已经被收藏");
		} else if (result_code == -4) {
			comm_alert("您要删除收藏商品不存在");
		} else if (result_code == -100) {
			comm_alert("系统错误，请联系网站客服");
		} else if (result_code == -101) {
			customer.open_login('favorites.add');
		} else if (result_code == 1) {
			comm_alert("商品已收藏", 3);
		} else if (result_code == 2) {
			if (this.flg == 1) {
				comm_alert("收藏商品已删除", 3);
				$("#favorites_" + this.postID).remove();
				this.postID = "";
			} else {
				window.location.reload();
			}
		}
	}

};

//公共的提示窗口
var COME_BACK_FLG = "";
var COMM_BACK_HREF = "";
/*
正确的操作  flg=1 提示成功信息（参数str），1.5秒后自动调用函数come_bcak_href（关闭提示层，根据href值跳转页面）。
           flg=3 提示成功信息（参数str），1.5秒后自动调用函数come_bcak_href（关闭提示层，但不跳转页面）。

错误的操作  flg=0 提示错误信息（参数str），点击提示层上的确定按钮函数come_bcak_href（关闭提示层）。
		   flg=2 提示错误信息（参数str），点击提示层上的确定按钮函数come_bcak_href（关闭提示层，调用其他函数）。
		   flg=4 提示错误信息（参数str），点击提示层上的确定按钮函数come_bcak_href（关闭提示层，根据href值跳转页面）。
*/
function comm_alert(str,flg,href) {
	COME_BACK_FLG = flg ? flg : 0;
	COMM_BACK_HREF = href ? href : "/shop/showcart.html";
	if (flg == 1 || flg == 3 || flg == 7 || flg == 8) {
		setTimeout("come_bcak_href()",1500);
		if(flg == 8)
			$(".alert_img").addClass("alert_error_img");
		else
			$(".alert_img").addClass("alert_img2");
		$("#linkbox").hide();
	} else {
		$(".alert_img").removeClass("alert_img2");
		$("#linkbox").show();
	}
	if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style) {
		$("#comm_alert").css("top", parseInt($(document).scrollTop()) + 200 + "px");
	}
	$("#comm_error").html(str);
	getsize();
	$("#mask").removeClass("hide");
	$("#comm_alert").removeClass("hide");
	$("#btnClose_dialog").focus();

}
//回调函数
function come_bcak_href() {
	$("#comm_alert").addClass("hide");
	$("#mask").addClass("hide");
	if (COME_BACK_FLG == 1 || COME_BACK_FLG == 4 || COME_BACK_FLG == 8) {
		self.location = COMM_BACK_HREF;
	} else if (COME_BACK_FLG == 2) {
		get_all_region();
	}else if(COME_BACK_FLG == 7){
		parent.window.opener = null;
		parent.window.open("", "_self");
		parent.window.close();
	}
}

/**
 * 公共的提示层
 * cf_open 确认提示层
 */
var comm_box = {

	//确认时回调函数名称
	confirmFunc: "",

	//取消时回调的函数名称
	cancelFunc:"",

	//确认提示层长度
	width: 300,

	timeID: "",

	/*
		打开确认提示层
	 	str 提示信息
	 	confirmFunc 确认时回调函数名称
	 	cancelFunc 取消时回调的函数名称
	*/
	confirm_open: function(str, confirmFunc, cancelFunc) {
		this.confirmFunc = confirmFunc;
		if (cancelFunc)
			this.cancelFunc = cancelFunc;
		if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style) {
			$("#comm_confirm").css("top", parseInt($(document).scrollTop()) + 200 + "px");
		}
		$("#confirm_msg").html(str);
		$("#comm_confirm .pb-content").css("width",this.width+"px");
		$("#mask").removeClass("hide");
		$("#comm_confirm").removeClass("hide");
	},

	/**
	 * 关闭确认提示层
	 * isConfirm 1：确认 0：取消
	 */
	confirm_close: function(isConfirm) {
		$("#comm_confirm").addClass("hide");
		$("#mask").addClass("hide");
		if (isConfirm) {
			eval(this.confirmFunc + "()");
		} else {
			if (this.cancelFunc != "")
				eval(this.cancelFunc + "()");
		}
	},

	loadding: function(str) {
		if (str) {
			$("#loadding").find(".loadding_str").text(str);
		}
		$("#mask").removeClass("hide");
		$("#loadding").removeClass("hide");
		this.timeID = setTimeout("comm_box.loadding_fail()", 15000);
	},

	loadding_fail: function() {
		comm_box.loadding_close();
		clearTimeout(this.timeID);
		comm_alert("网络爆棚，请重新尝试！");
		//common.refresh();
	},

	loadding_close: function() {
		clearTimeout(this.timeID);
		$("#loadding").addClass("hide");
		$("#mask").addClass("hide");
	}

};

//简单的确认层
function delete_pop(obj,str) {
	$(".relative .absolute").hide();
	$(obj).next("div").show();
	$(obj).next("div").children("p").html(str);
}
//关闭简单的确认层
function cancal_delete_pop() {
	$(".relative .absolute").hide();
}

//简单的确认层(右边)
function delete_pop_right(obj,str) {
	$(".relative .absolute_right").hide();
	$(obj).next("div").show();
	$(obj).next("div").children("p").html(str);
}
//关闭简单的确认层(右边)
function cancal_delete_pop_right() {
	$(".relative .absolute_right").hide();
}

//遮盖层高度和宽度
function getsize() {
	width = document.body.scrollWidth;
	height = document.body.scrollHeight;
	if(document.documentElement){
		width = Math.max(width, document.documentElement.scrollWidth);
		height = Math.max(height, document.documentElement.scrollHeight);
	}
	$(".mask").css("width",width).css("height",height);

}

//滚动条滚动
$(window).scroll(function() {

	//检查客户是否有未读信息
	if (customer.cust_id && unread.open_page_timestamp < get_COOKIE_INFO("OPEN_PAGE_TIMESTAMP")) {
		unread.get_timestamp();
	}

	//滚动条高度
	var t = $(document).scrollTop();

	//未读提示层位置
	if ($("#unread_yellowtip").css("display") == "block") {
		if (t > 26) {
			if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style) {
				$("#unread_yellowtip").css("top", parseInt(t) + "px");
			} else {
				$("#unread_yellowtip").css("top", 0);
			}
		} else {
			$("#unread_yellowtip").css("top", "26px");
		}
	}

	//回顶部层位置

	if (t >= 200) {
		$("#go-top").css("display", "inline");
	} else if(t < 200){
		$("#go-top").css("display", "none");
	}

	//提示弹出层位置
	if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style && !$("#comm_alert").hasClass("hide")) {
		$("#comm_alert").css("top", parseInt(t) + 200 + "px");
	}

	//登录层位置
	if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style && !$("#login_main").hasClass("hide")) {
		$("#login_main").css("top", parseInt(t) + 200 + "px");
	}

	//提示弹出层位置(客服)
	if($.browser.msie && ($.browser.version == "6.0") && !$.support.style){
		$("#onlinediv").css("top", parseInt(t) + 200 + "px");
	}

	//加载层
	if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style && !$("#loadding").hasClass("hide")) {
		$("#loadding").css("top", parseInt(t) + 300 + "px");
	}

});

//公告滚动
function setScroll() {
    var c = 30;
    var d = document.getElementById("textScrollArea");
    var e = document.getElementById("textScrollCon");
    if (d == null || e == null) {
        return
    }
    var f = e.offsetWidth;
    function a() {
        if (f - d.scrollLeft <= 0) {
            d.scrollLeft = 0;
        } else {
            d.scrollLeft++;
        }
    }
    var b = setInterval(a, c);
    d.onmouseover = function() {
        clearInterval(b);
    };
    d.onmouseout = function() {
        b = setInterval(a, c);
    };
}
setScroll();


//查询
function do_search(keywords) {
	if (keywords) {
		var reg = new RegExp("-","g");
		$("#keywords").val(keywords.replace(reg, "++"));
		$("#searchTemp").val(keywords);
	} else {
		keywords = Utils.trim(document.getElementById("searchTemp").value);
//		if (keywords == "冬季羽绒") {
//			keywords =  '';
//			document.getElementById("searchTemp").value = "";
//		}
	}
	keywords =  new_estr(keywords);
	window.location.href = '/shop/goodsCate-0-0-0-0-0-0-min,max-'+keywords+'\.html';
}

//收藏网站
function addfavorite() {
	if (document.all) {
		window.external.addFavorite('','李宁官方网上商城');
	} else if (window.sidebar) {
		window.sidebar.addPanel('李宁官方网上商城', '', "");
	}
}

$(document).ready(function() {
	
	getsize();

	var containLeft = $('.comm_contain_left');
	if (containLeft.length > 0){
		var offset_left = containLeft.offset().left;
		if (offset_left > 0) {
			$('.comm_contain_left').css('marginLeft', '-192px');
		}
	}
});

var GC_img_preload_gap = 500;
var GC_delay_todoload = 100;

var GF_img_index1 = true;
var GF_do_loading = false;
var GV_loadimg_handler = 0;
var GV_lazytype = 0;
var GV_img_indexs1 = new Array();
var GV_img_indexs2 = new Array();
var GV_img_loading = new Array();

function create_imgItem(index,top,bottom){
	var o = new Object();
	o.index = index;
	o.top = top;
	o.bottom = bottom;
	return o;
}

function get_visionBottom(){
	var t, h;
	t = document.documentElement.scrollTop || document.body.scrollTop;
	h = document.documentElement.clientHeight || document.body.clientHeight;
	return t + h;
}


function get_targetTop(p){
	var t = 0;
	while(p){
		if (p.offsetTop == undefined){
			return t;
		}
		t += p.offsetTop;
		p = p.offsetParent;
	}
	return t;
}

function do_loadimg(){
	if (GV_img_loading.length == 0){
	   GF_do_loading = false;
		return false;
	}

	var vision_top = 0;
	var vision_bottom = 0;

	if (GV_lazytype == 1){
		vision_top = document.documentElement.scrollTop || document.body.scrollTop;
		vision_bottom = get_visionBottom();
	}

	var img_count = GV_img_loading.length;
	var img_tmp = null;
	for (var i=0;i<img_count;i++){
		img_tmp = document.getElementById(GV_img_loading[i]);
		if (img_tmp == null)
		    continue;
		if (img_tmp.getAttribute("orginalsrc") != null)
			img_tmp.src = img_tmp.getAttribute("orginalsrc");
		else if (img_tmp.getAttribute("lazycall") != null)
			eval(img_tmp.getAttribute("lazycall"));
	}
	GV_img_loading.length = 0;
	GF_do_loading = false;
	return true;
}

function start_loadimg(){
	GF_do_loading = true;
	if (GV_lazytype == 0){
		GV_loadimg_handler = setTimeout("do_loadimg();",GC_delay_todoload);
	}else{
		do_loadimg();
	}
}

function clear_lazyloadimg(){
	if (GV_loadimg_handler > 0){
		clearTimeout(GV_loadimg_handler);
		GV_loadimg_handler = 0;
	}
}


function check_scroll(event){
	if (GF_do_loading){
		if (GV_lazytype == 0)
			clear_lazyloadimg();
		else
			return false;
	}

	var img_count = 0;
	var img_top = 0;
	var img_bottom = 0;
	var img_tmp = null;
	var loaded = false;

	var vision_top = document.documentElement.scrollTop || document.body.scrollTop;
	var vision_bottom = get_visionBottom();
	if (GF_img_index1){
		img_count = GV_img_indexs1.length;
		for (var i=0; i < img_count; i++){
			if (GV_lazytype == 0){
				img_top = GV_img_indexs1[i].top;
				img_bottom = GV_img_indexs1[i].bottom;
				loaded = img_top - vision_bottom <= GC_img_preload_gap && img_bottom > vision_top;
			}else{
				img_tmp = document.images[GV_img_indexs1[i].index];
				img_top = get_targetTop(img_tmp);
				loaded = img_top - vision_bottom <= GC_img_preload_gap;
			}
			if (loaded)
				GV_img_loading.push(GV_img_indexs1[i].index);
			else
				GV_img_indexs2.push(GV_img_indexs1[i]);
		}
		GV_img_indexs1.length = 0;
	}else{
		img_count = GV_img_indexs2.length;
		for (var i=0; i < img_count; i++){
			if (GV_lazytype == 0){
				img_top = GV_img_indexs2[i].top;
				img_bottom = GV_img_indexs2[i].bottom;
				loaded = img_top - vision_bottom <= GC_img_preload_gap && img_bottom > vision_top;
			}else{
				img_tmp = document.images[GV_img_indexs2[i].index];
				img_top = get_targetTop(img_tmp);
				loaded = img_top - vision_bottom <= GC_img_preload_gap;
			}
			if (loaded)
				GV_img_loading.push(GV_img_indexs2[i].index);
			else
				GV_img_indexs1.push(GV_img_indexs2[i]);
		}
		GV_img_indexs2.length = 0;
	}

	GF_img_index1 = !GF_img_index1;
	if (GV_img_loading.length > 0){
		start_loadimg();
	}
}

function wanson_init_lazy(){
	var img_count = document.images.length;
	if (img_count == 0)
		return false;

	var vision_top = document.documentElement.scrollTop || document.body.scrollTop;
	var vision_bottom = get_visionBottom();
	var img_top = 0;
	var img_bottom = 0;
	var img_tmp = null;
	for (var i=0;i<img_count;i++){
		img_tmp = document.images[i];
		if (img_tmp == undefined)
			continue;
		if (img_tmp.getAttribute("orginalsrc") == null && img_tmp.getAttribute("lazycall") == null)
			continue;

		img_top = get_targetTop(img_tmp);
		img_bottom = img_top + img_tmp.offsetHeight;

		img_tmp.id = (((1+Math.random())*0x10000)|0).toString(16).substring(1)+
                		(((1+Math.random())*0x10000)|0).toString(16).substring(1)+
                		(((1+Math.random())*0x10000)|0).toString(16).substring(1)+
                		(((1+Math.random())*0x10000)|0).toString(16).substring(1)+
                		(((1+Math.random())*0x10000)|0).toString(16).substring(1)+
                		(((1+Math.random())*0x10000)|0).toString(16).substring(1)+
                		(((1+Math.random())*0x10000)|0).toString(16).substring(1)+
                		(((1+Math.random())*0x10000)|0).toString(16).substring(1);
		if (img_top - vision_bottom <= GC_img_preload_gap && img_bottom > vision_top)
			GV_img_loading.push(img_tmp.id);
		else{
			GV_img_indexs1.push(create_imgItem(img_tmp.id,img_top,img_bottom));
		}
	}
	if (GV_img_loading.length == 0 && GV_img_indexs1.length == 0)
		return false;

	if (window.attachEvent){
		window.attachEvent("onscroll", check_scroll, false);
	}else{
		window.addEventListener("scroll", check_scroll, false);
	}
	window.onresize = check_scroll;

	if (GV_img_loading.length > 0){
		start_loadimg();
	}

	return true;
}


function init_lazy(){
	GV_lazytype = 0;
	setTimeout("wanson_init_lazy();",100);
}

function init_dynamic_lazy(){
	GV_lazytype = 1;
	setTimeout("wanson_init_lazy();",100);
}

//短信验证码验证
var sms={
	isChk:0,//是否进行了验证码验证 0:未验证 1：已验证
	sendSms:function(){//发送手机短信
		var phoneNum=Utils.trim($('#reg_mobile').val());
		if(phoneNum != ''){
			if(!Utils.isMobile(phoneNum)){
				$('#send_span').html('<span><font color="red">手机号码格式不正确</font></span>');
				comm_alert('手机号码格式不正确!');
				return false;
			}
		}
		
		var reg_pic = Utils.trim($('#reg_pic').val());
		if(reg_pic == ''){
			comm_alert('请输入图案验证码!');
			return false;
		}
		
		var params = {
				mobile:	phoneNum,
				yzm: reg_pic,
				sendFlg: 4
			};
		$.ajax({
            type: 'POST',
            url: '/shop/comm_tplfun/xajax_commUser_pro.php?operFlg=5',
            dataType: 'json',
            data: params,
            timeout: 15000,
            async:false,
            success: function (result_obj){
            	if (result_obj.code != 200){
                    sms.sendResult(result_obj.message);
                }else {
                	sms.sendResult('');
                }
//                if (result_obj.code != 200){
//                    var code = Number(result_obj.data);
//                    sms.sendResult(code);
//                }else {
//                	sms.sendResult(1);
//                }
            }
        });
	},
	sendResult:function(message){//手机短信发送结果
		if (message == ''){
			comm_alert('信息已发送',3);
			$('#send_span').html('<span><font color="green">信息已发送</font></span>');
			$("#send_sms_btn").removeAttr('onclick');
		}else {
			comm_alert(message);
			$('#send_span').html('<span><font color="red">'+message+'</font></span>');
		}
//		if(code == -1){
//			$('#send_span').html('<span><font color="red">手机号码参数错误</font></span>');
//			return false;
//		}else if(code == -2){
//			$('#send_span').html('<span><font color="red">请填写正确的手机号码</font></span>');
//		}else if(code == -3){
//			$('#send_span').html('<span><font color="red">手机号已经被绑定</font></span>');
//		}else if(code == -4){
//			$('#send_span').html('<span><font color="red">生成短信验证码出错</font></span>');
//		}else if(code == -5){
//			$('#send_span').html('<span><font color="red">该手机号获取验证码已超过三次上限，请24小时后重试</font></span>');
//		}else{
//			$('#send_span').html('<span><font color="green">信息已发送</font></span>');
//			$("#send_sms_btn").removeAttr('onclick');
//		}
	},
	chkMsg:function(){//手机短信验证
		var checkCode=Utils.trim($('#code').val());
		if(!checkCode){
			comm_alert('请输入正确的验证码');
			return false;
		}
		xajax_member(checkCode,11);
	},
	chkResult:function(code){//手机短信验证结果
		if(code == -1){
			this.isChk = 0;
			$('#check_span').html('<span><font color="red">手机号验证参数有错误</font></span>');
		}else if(code == -2){
			this.isChk = 0;
			$('#check_span').html('<span><font color="red">短信验证码已失效!</font></span>');
		}else if(code == -3){
			this.isChk = 0;
			$('#check_span').html('<span><font color="red">短信验证码不正确!</font></span>');
		}else{
			this.isChk = 1;
			$('#check_span').html('<span><font color="green">短信验证成功!</font></span>');
			$("#chk_msg_btn").removeAttr('onclick');
		}
	}
};

//取消
//modified by yinfulin 2012-08-29 增加对url跳转的判断，如果没有last_url则不跳转
function cancel_join(){
	if (typeof(customer.last_url) == "undefined") {
	    $("#brg").hide();
		$("#registdiv").hide();
	}else{
		self.location = customer.last_url;
	}
}
function estr(kw) {
    if (!kw) {
        return "";
    }
    var c = "123456abcdefghijklmnopqrstuvwxyz";
    kw = u(kw).toLocaleLowerCase();
    var bstr = '';
    for (i = 0; i < kw.length; i++) {
        b8 = new String(kw.charCodeAt(i).toString(2));
        var x = b8.length;
        if (x < 8) {
            for (j = 0; j < 8 - x; j++) {
                b8 = '0' + b8;
            }
        }
        bstr += b8;
    }
    var l5 = 0;
    if (bstr.length % 5 == 0) {
        l5 = bstr.length / 5;
    } else {
        l5 = parseInt(bstr.length / 5) + 1;
    }
    var e = "";
    for (i = 0; i < l5; i++) {
        b5 = bstr.substring(i * 5, (i + 1) * 5);
        bit = '000' + b5;
        var y = bit.length;
        if (y < 8) {
            for (j = 0; j < 8 - y; j++) {
				for (j = 0; j < 8 - y; j++) {
					bit = bit + '0';
				}
			}
		}
        var s = parseInt(parseInt(bit, 2).toString(10));
        e += c.substring(s, s + 1);
    }
    return e;
}
//分词查询用
function new_estr(kw) {
    if (!kw) {
        return "";
    }
    var kw_arr = kw.split(' ');
    var result = "";
    var space = estr('&nbsp;');
    for (var i=0; i<kw_arr.length; i++){
    	if (Utils.isEmpty(kw_arr[i])){
    		continue;
    	}
    	result += estr(kw_arr[i]) + space;
    }
    
    return result;
}

function dstr(s) {
    if (!s) {
        return "";
    }
    var c = "123456abcdefghijklmnopqrstuvwxyz";
    var dstr = "";
    var bs = "";
    for (i = 0; i < s.length; i++) {
        var d = new String(c.indexOf(s[i]).toString(2));
        var x = d.length;
        if (x < 8) {
            for (j = 0; j < 8 - x; j++) {
                d = "0" + d;
            }
        }
        bs += d;
    }
    var b5s = "";
    for (i = 0; i < (bs.length / 8); i++) {
        var b8 = bs.substring(i * 8, (i + 1) * 8);
        var b5 = b8.substring(3, 8);
        b5s += b5;
    }
    var l8 = parseInt(b5s.length / 8);
    for (i = 0; i < l8; i++) {
        var b8 = parseInt(b5s.substring(i * 8, (i + 1) * 8), 2).toString(10);
        dstr += String.fromCharCode(parseInt(b8));
    }
    return r(dstr);
}
function u(s) {
    return s.replace(/[^\u0000-\u00FF]/g,
    function($0) {
        return escape($0).replace(/(%u)(\w{4})/gi, "\\u$2")
    })
}
function r(s) {
    s = s.replace(/(\\u)(\w{4})/gi,
    function($0) {
        return (String.fromCharCode(parseInt((escape($0).replace(/(%5Cu)(\w{4})/g, "$2")), 16)))
    });
    return s
}


//生日日期变化
function birth_day_change(day){
	var str = "<select name='birthDay' id='birthDay'><option value=''>请选择</option>";
	for(var i=1;i<=day;i++){
		if(i<=9){
			var day_str='0'+i;
		}else{
			var day_str=i;
		}
		str += "<option value='"+day_str+"'>"+day_str+"</option>";
	}
	str+='</select>';
	$("#birthday_span").html(str);
}
function monthday_change(){
	$("#birthMonth").val("");
    $("#birthDay").val("");
}

function day_change(){
	$("#birthDay").val("");
	var nowMonth = $("#birthMonth").val();
   	if (nowMonth == 2) {
        var nowYear = $("#birthYear").val();
        if ((nowYear % 4) == 0) {
        	birth_day_change(29);
        } else {
        	birth_day_change(28);
        }
    } else if (nowMonth == 4 || nowMonth == 6 || nowMonth == 9 || nowMonth == 11) {
       birth_day_change(30);
    } else {
        birth_day_change(31);
    }
}


// +----------------------------------------------------------------------
// | 页面头部改版 2014-04-26
// +----------------------------------------------------------------------

//更多品牌
$('.more_brand').on('mouseover',function(){
	$('.pull_down_input').css('background','url("http://cdn.lining.com/shop/lining/theme/xth2/images/comm/select_top.png") no-repeat scroll 75px center #FFFFFF');
	$('.more_brand_list').removeClass('hide');
}).on('mouseout',function(){
	$('.pull_down_input').css('background','url("http://cdn.lining.com/shop/lining/theme/xth2/images/comm/select_bottom.png") no-repeat scroll 75px center #FFFFFF');
	$('.more_brand_list').addClass('hide');
});


//收藏
$('#collectionSite').on('click',function(){
	comm_alert('对不起，您的浏览器不支持此操作!<br/>请您使用菜单栏或Ctrl+D收藏本站。');
	return false;
});

// +----------------------------------------------------------------------
// | 商品预售start
// +----------------------------------------------------------------------
$(function(){
	$('[name=cartgoods]').click(function(){
		var selectedLen = 0;
		$('[name=cartgoods]:checked').each(function(k,v){
			var failure = $(this).attr('failure');
			if (failure == 1) {
				return true;
			}

			selectedLen++;
		});
		$('#totalQty').html(selectedLen);

		if (selectedLen > 0) {
			$('.sale-a-gray').addClass('carthide');
			$('.sale-a-checkout').removeClass('carthide');
		} else {
			$('.sale-a-gray').removeClass('carthide');
			$('.sale-a-checkout').addClass('carthide');
		}

		var totalMoney = 0;
		var saveMoney = 0;
		$('[name=cartgoods]:checked').each(function(k,v){
			var failure = $(this).attr('failure');
			if (failure == 1) {
				return true;
			}
			totalMoney += (parseFloat($(this).attr('p1')) * parseInt($(this).attr('p3')));
			saveMoney += (parseFloat($(this).attr('p2')) * parseInt($(this).attr('p3')));
		});

		$('#saleTotalPaid').html('￥' + totalMoney.toFixed(2));
		$('#saleTotalPaidHide').val(totalMoney.toFixed(2));
		$('#totalCartMoney').html('总计：' + totalMoney.toFixed(2));
		$('#totalSaveMoney').html('优惠：' + saveMoney.toFixed(2));
	});
});

var sale_cart = {

	amount: 1,	//商品数量

	type: 1,	//单品or套装

	saleId: 0,

	key: 0,

	quantity: 0,

	//添加预售商品 type 1:单品 2:套装
	add: function(type){

		this.type = type;

		if (type == 1) {	//单品
			var saleID = goods.saleID;
			var postID = $('#postID_306687').val();
			var quantity = $('#quantity_306687').val();

			this.amount = quantity;
			if (!Utils.isDigital(saleID) || !Utils.isDigital(postID)) {
				comm_alert('参数错误');
				return false;
			}

			if (quantity < 1 || quantity > 99) {
				$('#quantity_306687').val(1);
				comm_alert('输入的数量有误,应为[1-99]');
				return false;
			}

			var params = [type,saleID,quantity,postID];

		} else {	//套装
			var saleID = salegroup.saleID;
			var quantity = 1;
			var postArray = [];
			var sizeFlg = 0;
			$('[id*=goods_select_]').each(function(k,v){
				var postID = $(this).val();
				if (postID != '' && postID != 0) {
					postArray.push(postID);
				} else {
					sizeFlg = 1;
				}
			});

			if (sizeFlg == 1) {
				comm_alert('请选择套装尺码');
				return false;
			}

			this.amount = quantity;

			if (!Utils.isDigital(saleID)) {
				comm_alert('参数错误');
				return false;
			}

			if (quantity < 1 || quantity > 99) {
				comm_alert('输入的数量有误,应为[1-99]');
				return false;
			}

			var params = [type,saleID,quantity,postArray];
		}

		comm_box.loadding();

		xajax_do_req_cart(params, 22);
	},

	backtrack: function(){
		comm_box.loadding_close();
		$('.comm_pop .eb_div').css('width', '430px');
		customer.open_login('sale_cart.jump');
	},

	jump: function(){
		if (this.type == 1) {
			window.location.href = goods.link;
		} else {
			window.location.href = salegroup.link;
		}
	},

	sale_amount: function(){

		var sale_amount = get_COOKIE_INFO("CARTSALEAMOUNT");

		if (sale_amount == null) {
			$("#cart_sale_num").text(0);
		} else {
			$("#cart_sale_num").text(sale_amount);
		}
	},

	success: function(result){

		comm_box.loadding_close();

		sale_cart.sale_amount();

		$('#cp_title').width('33%');

		if (result[0] == 1) {	//单品
			$("#cp_title").html("&nbsp;&nbsp;" + this.amount + "件预售商品加入购物车");
	    	var listHtml = "<tr><td rowspan=\"4\"><img class=\"goods_img\" src=\"" + result[1].imglink + "\"></td><td class=\"tal blod\">" + result[1].goodsName + "</td></tr>";
	    	listHtml += "<tr><td class=\"tal\">加入数量：<span class=\"red\">" + this.amount + "</span></td></tr>";
	    	listHtml += "<tr><td class=\"tal\">总计金额：<span class=\"red\">¥"+parseFloat(result[1].salePrice).toFixed(2)+"</span></td></tr>";
	    	listHtml += "<tr><td class=\"tal\" style=\"height: 50px;\">";
	        listHtml += "<input type=\"button\" onclick=\"sale_cart.close();\" value=\"继续购物\" class=\"input_action\">";
	        listHtml += "<input type=\"button\" onclick=\"sale_cart.goCheckout();\" value=\"去结算\" class=\"input_action_off ml10\"></td></tr>";
	    	$("#success_html").html(listHtml);
	    	$("#add_cart_success").show();
		} else {	//预售
			$("#cp_title").html("&nbsp;&nbsp;" + this.amount + "件预售套装加入购物车");
			$("#cp_title").attr('colspan','3');
			$("#add_cart_success table").width('618px');
			var listHtml = '';
			var salecount = 0;
			var rowcount = 0;

			for(k in result[1]) {

				if (k == 'goodsAmount' || k == 'marketPrice' || k == 'salePrice' || k == 'saleID'
					|| k == 'discount' || k == 'goodsCount' || k == 'salePoints' ||
					k == 'store' || k == 'addPoints' || k == 'spreadPrice' || k == 'saleName' || k == 'key'
					|| k == 'failure') {
						continue;
				}

				rowcount++;
			}

			for(k in result[1]) {

				if (k == 'goodsAmount' || k == 'salePrice' || k == 'marketPrice'
				|| k == 'saleID' || k == 'discount' || k == 'goodsCount' || k == 'saleName') {
					continue;
				}

				listHtml += "<tr>";
					listHtml += "<td width=\"20%\"><img style=\"width:60px; height:60px;\" class=\"goods_img\" src=\"" + result[1][k].imglink + "\"></td>";
					listHtml += "<td width=\"36%\" class=\"tal blod\">" + result[1][k].goodsName + "</td>";
					listHtml += "<td width=\"24%\" class=\"tal blod\">" + result[1][k].spec + "</td>";
					if (salecount == 0) {
						listHtml += "<td width=\"20%\" rowspan=\""+rowcount+"\" class=\"tal blod\">";
							listHtml += "<div>加入数量：<span class=\"red\">"+this.amount+"</span></div>";
							listHtml += "<div style=\"margin-top:28px;\">总计金额：<span class=\"red\">"+result[1]['salePrice']+"</span></div>";
						listHtml += "</td>";
					}
				listHtml += "</tr>";

				salecount++;
			}

			listHtml += "<tr style=\"height:50px;\">";
				listHtml += "<td colspan=\"4\">";
					 listHtml += "<input type=\"button\" onclick=\"sale_cart.close();\" value=\"继续购物\" class=\"input_action\">";
	        		listHtml += "<input type=\"button\" onclick=\"sale_cart.goCheckout();\" value=\"去结算\" class=\"input_action_off ml10\"></td></tr>";
				listHtml += "</td>";
			listHtml += "</tr>";

			$("#success_html").html(listHtml);
	    	$("#add_cart_success").show();
		}
	},

	close: function(){
		$("#add_cart_success").hide();
	},

	goCheckout: function(){
		this.close();
		comm_box.loadding();
		if (common.userBrowser() == "Safari") {
			location.replace("/shop/salecart.php");
		} else {
			self.location = "/shop/salecart.php";
		}
	},

	//更新购物车商品数量
	update: function(saleID,key,quantity){

		this.saleId = saleID;
		this.key = key;
		this.quantity = quantity;

		//检查参数
		if (!Utils.isDigital(saleID) || !Utils.isDigital(key)) {
			comm_alert('更新数量失败');
			return false;
		}

		var input_quantity = $('#quantity_'+saleID+'_'+key).val();

		if (parseInt(quantity) + parseInt(input_quantity) <= 0) {
			comm_box.confirm_open('您确认不购买该此商品？', 'sale_cart.confirmRemoval', null);
		}  else {
			comm_box.loadding();
			var params = [saleID,key,quantity];
			xajax_do_req_cart(params, 23);
		}
	},

	//删除购物车商品
	removal: function(saleID,key){

		this.saleId = saleID;

		this.key = key;

		comm_box.confirm_open('您确认不购买该此商品？', 'sale_cart.confirmRemoval', null);
	},

	//确认删除
	confirmRemoval: function(){
		comm_box.loadding();
		var params = [this.saleId,this.key];
		xajax_do_req_cart(params, 24);
	},

	//删除选中项
	delAll: function(){

		var checkLen = $('[name=cartgoods]:checked').length;
		if (checkLen <= 0) {
			comm_alert('请选择需要删除的商品');
			return false;
		}

		comm_box.confirm_open('您确认删除选中的商品吗？', 'sale_cart.confrimDelAll', null);
	},

	//确认删除选中项
	confrimDelAll: function(){
		var params = [];
		$('[name=cartgoods]:checked').each(function(k,v){
			var valStr = $(this).attr('value');
			var valArray = valStr.split('_');
			params.push([valArray[0],valArray[1]]);
		});

		xajax_do_req_cart(params, 25);
	},

	//操作返回
	cartResult: function(result){
		window.location.href = '/shop/salecart.php';
	},

	//选择全部
	checkAll: function(){
		var allCheck = $('[name=allCheck]').attr('checked');
		if (allCheck == 'checked') {
			$('[name=cartgoods]').attr('checked','true');
		} else {
			$('[name=cartgoods]').removeAttr('checked');
		}

		var selectedLen = 0;
		$('[name=cartgoods]:checked').each(function(k,v){
			var failure = $(this).attr('failure');
			if (failure == 1) {
				return true;
			}
			selectedLen++;
		});
		$('#totalQty').html(selectedLen);

		if (selectedLen > 0) {
			$('.sale-a-gray').addClass('carthide');
			$('.sale-a-checkout').removeClass('carthide');
		} else {
			$('.sale-a-gray').removeClass('carthide');
			$('.sale-a-checkout').addClass('carthide');
		}

		var totalMoney = 0;
		var saveMoney = 0;
		$('[name=cartgoods]:checked').each(function(k,v){
			var failure = $(this).attr('failure');
			if (failure == 1) {
				return true;
			}
			totalMoney += (parseFloat($(this).attr('p1')) * parseInt($(this).attr('p3')));
			saveMoney += (parseFloat($(this).attr('p2')) * parseInt($(this).attr('p3')));
		});

		$('#saleTotalPaid').html('￥' + totalMoney.toFixed(2));
		$('#saleTotalPaidHide').val(totalMoney.toFixed(2));
		$('#totalCartMoney').html('总计：' + totalMoney.toFixed(2));
		$('#totalSaveMoney').html('优惠：' + saveMoney.toFixed(2));
	},

	endCheck: function(){
		var endCheck = $('[name=endCheck]').attr('checked');

		if (endCheck == 'checked') {
			$('[name=cartgoods]').attr('checked','true');
		} else {
			$('[name=cartgoods]').removeAttr('checked');
		}

		var selectedLen = 0;
		$('[name=cartgoods]:checked').each(function(k,v){
			var failure = $(this).attr('failure');
			if (failure == 1) {
				return true;
			}
			selectedLen++;
		});
		$('#totalQty').html(selectedLen);

		if (selectedLen > 0) {
			$('.sale-a-gray').addClass('carthide');
			$('.sale-a-checkout').removeClass('carthide');
		} else {
			$('.sale-a-gray').removeClass('carthide');
			$('.sale-a-checkout').addClass('carthide');
		}

		var totalMoney = 0;
		var saveMoney = 0;
		$('[name=cartgoods]:checked').each(function(k,v){
			var failure = $(this).attr('failure');
			if (failure == 1) {
				return true;
			}
			totalMoney += (parseFloat($(this).attr('p1')) * parseInt($(this).attr('p3')));
			saveMoney += (parseFloat($(this).attr('p2')) * parseInt($(this).attr('p3')));
		});

		$('#saleTotalPaid').html('￥' + totalMoney.toFixed(2));
		$('#saleTotalPaidHide').val(totalMoney.toFixed(2));
		$('#totalCartMoney').html('总计：' + totalMoney.toFixed(2));
		$('#totalSaveMoney').html('优惠：' + saveMoney.toFixed(2));
	},

	inputBlur: function(saleID,key){
		var blurNum = parseInt($('#quantity_'+saleID+'_'+key).val());
		if (!Utils.isDigital(blurNum) || blurNum <= 0) {
			$('#quantity_'+saleID+'_'+key).val(this.focusNum);
			comm_alert('请输入正确的商品数量');
			return false;
		}

		if (blurNum == this.focusNum) {
			return false;
		}

		var diffNum = blurNum - parseInt(this.focusNum);
		params = [saleID,key,diffNum];
		xajax_do_req_cart(params, 23);
	},

	inputFocus: function(saleID,key){
		this.focusNum = parseInt($('#quantity_'+saleID+'_'+key).val());
	},

	//是否拆单
	isSingle: function(){
		if ($('#single').attr('checked') == 'checked') {
			set_COOKIE_INFO('salesingle','1',0);
		} else {
			set_COOKIE_INFO('salesingle','0',0);
		}
	},

	saleCheckout: function(){
		var checkLen = $('[name=cartgoods]:checked').length;
		if (checkLen <= 0) {
			comm_alert('请选择结算商品');
			return false;
		}

		var params = [];
		$('[name=cartgoods]').each(function(k,v){

			var unCheck = $(this).attr('checked');
			var failure = $(this).attr('failure');
			if (unCheck != 'checked' || failure == 1) {
				var valStr = $(this).attr('value');
				var valArray = valStr.split('_');
				params.push([valArray[0],valArray[1]]);
			}
		});

		xajax_do_req_cart(params, 26);
	},

	//购物车回调
	cartCallback: function(o){
		var iscarthide = $('.sale-a-gray').is('.carthide');
		if (!iscarthide) {
			return false;
		}

		var inputSel = $('#input_' + o.saleID + '_' + o.key).attr('checked');
		if (inputSel != 'checked') {
			return false;
		}

		var money = parseFloat($('#saleTotalPaidHide').val());
		var diffMoney = (money + parseFloat(o.qtyMoney)).toFixed(2);
		$('#saleTotalPaidHide').val(diffMoney);
		$('#saleTotalPaid').html('￥' + diffMoney);
	}
};
// +----------------------------------------------------------------------
// | 商品预售end
// +----------------------------------------------------------------------


// +----------------------------------------------------------------------
// | 定制商品start
// +----------------------------------------------------------------------


//cba定制
var cbaCustom = {

	//球队名称
	team: {
		329851:'beijing',329842:'liaoning',329878:'guangdonghongyuan',329887:'sichuan',
		329806:'xinjiangguanghui',329860:'shanghai',330565:'zhejiangguangsha',
		330574:'jiangsukendiya',330583:'qingdao',330592:'tianjin',330619:'shenzhenxinshiji',
		330610:'jilin',330601:'shanxi',330628:'bayi',330637:'fujianlalian',330646:'shandong',
		330655:'jiangsutongxi',330673:'foshanlongshi',330664:'zhejiangchouzhou',
		337643:'south',337634:'north'
	},

    //球队队员信息
    members: {
        bayi: {'许钟豪':24,'邹雨宸':10,'韩硕':33},
		beijing: {'方硕':8,'朱彦西':13,'翟晓川':20,'孙悦':9,'张庆鹏':5,'马布里':3,'莫里斯':32,'段江鹏':11},
		shenzhenxinshiji: {'李慕豪':21,'布朗':6,'麦克奎尔':1},
		foshanlongshi: {'唐正东':11,'贝尼蒙':20,'霍普森':24},
		fujianlalian: {'赵泰隆':9,'陈林坚':5,'王哲林':22,'拜克斯':13},
		guangdonghongyuan: {'易建联':9,'陈江华':6,'王仕鹏':7,'朱芳雨':8,'周鹏':11,'拜纳姆':1},
		jiangsukendiya: {'衡艺丰':21,'胡雪峰':55,'常林':45,'易立':8,'陈磊':22},
		jiangsutongxi: {'宋建骅':8,'韦弗':13,'兰姆':50},
		jilin: {'拉莫斯':35,'马库斯':22},
		liaoning: {'贺天举':6,'赵继伟':3,'杨鸣':12,'郭艾伦':13,'李晓旭':22,'韩德君':55,'哈德森':14},
		qingdao: {'张骋宇':6,'罗旭东':9,'威廉姆斯':15,'吉布森':22},
		shandong: {'丁彦雨航':23,'睢冉':1,'陶汉林':20,'杰特':3},
		shanghai: {'张兆旭':23,'翟逸':22,'曾文鼎':76,'李学林':2,'刘晓宇':10},
		shanxi: {'张学文':33,'曾令旭':6,'琼斯':55},
		sichuan: {'于澍龙':11,'孟达':12,'哈达迪':15,'哈里斯':33,'邓特蒙':55},
		tianjin: {'威尔':22,'马克希尔':54},
		xinjiangguanghui: {'刘炜':8,'可兰白克':13,'周琦':9,'西热力江':20,'古德洛克':2,'布拉切':0},
		zhejiangchouzhou: {'丁锦辉':4,'张大宇':24,'巴赫拉米':14},
		zhejiangguangsha: {'林志杰':27,'王征':13,'赵岩昊':25,'胡金秋':21,'霍尔曼':32},
		south: {'易建联':9,'朱芳雨':8,'周鹏':11,'林志杰':27},
    	north: {'周琦':9,'贺天举':6,'郭艾伦':13,'孙悦':9}
    },

	//打开定制层
	open: function(){
		var scroll_h = document.documentElement.scrollHeight;
		$("#mask_black").removeClass("hide").height(scroll_h);
		$('#cba_custom').removeClass('hide');
	},

	//关闭定制层
	close: function(flg){
		var twoPostID = $('#cba_postID').val();
		if (flg == 1 || flg == 3) {
			if (flg == 1 && twoPostID != 337643 && twoPostID != 337634) {
				$('#cba_custom').addClass('hide');
				$('#mask_black').addClass('hide');
				$('.cl .mxdz').addClass('hide');
				$('.cl .zsdz').removeClass('hide');
				$('.cr .c_r_tab').find('img')
				       			 .attr('src', 'http://cdn.lining.com/shop/lining/theme/xth2/images/custom/cba2015_tab_zsdz.png');
			} else if (flg == 1 && (twoPostID == 337643 || twoPostID == 337634)) {
				$('#cba_custom').addClass('hide');
				$('#mask_black').addClass('hide');
			}
			
			$('#cba_star').val('').attr('name', '');
			$('#cba_size').val('').attr('name', '');
			$('#cba_quantity').val(1);
			$('#cba_name').val('请输入5个中文/7个大写字母')
			              .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});
			$('#cba_num').val('最多输入两位数字')
			           	 .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});

			//显示默认图片
			var front_src = $('#h_front').val();
			var back_src = $('#h_back').val();
			$('.c_front').attr('src',front_src);
			$('.c_back').attr('src',back_src);
			$('.c_r_img').removeClass('hide');
			$('.c_r_jersey').addClass('hide');
			$('.c_r_jersey img').attr('src', '');
			
			$('.select_size p').css('border', '2px solid transparent');
			$('.zsdz ul li').css('border', '2px solid transparent');
			$('.select_star p').css('border', '2px solid transparent');
		} else if (flg == 2) {
			$('#cba_size_img').addClass('hide');
			$('#mask').addClass('hide');
		}
	},
	
	focus: function(flg){
		if (flg == 2) {
			if ($('#cba_num').val() == '最多输入两位数字') {
				$('#cba_num').val('').css({'color': '#6A6A6A', 'fontSize': '14px', 'fontWeight': 'bold'});
			}
		} else if (flg == 3) {
			if ($('#cba_name').val() == '请输入5个中文/7个大写字母') {
				$('#cba_name').val('').css({'color': '#6A6A6A', 'fontSize': '14px', 'fontWeight': 'bold'});
			}
		}
	},

	//商品数量失去焦点
	blur: function(flg){
		if (flg == 1) {	//商品数量
			var q = $.trim($('#cba_quantity').val());
			var reg = /^\d+$/;
	        if (!reg.exec(q) || q <= 0) {
	        	$('#cba_quantity').val(1);
	        }
		} else if (flg == 2) {	//号码
			$('#cba_star').val('').attr('name', '');
			var n = $.trim($('#cba_num').val());
			if (n == '' || n == '最多输入两位数字') {
				var m = $.trim($('#cba_name').val());
				if (m == '' || m == '请输入5个中文/7个大写字母') {
					$('#cba_name').val('请输入5个中文/7个大写字母')
								  .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});
				}

				//显示默认图片
				var front_src = $('#h_front').val();
				var back_src = $('#h_back').val();
				$('.c_front').attr('src',front_src);
				$('.c_back').attr('src',back_src);
				$('.c_r_img').removeClass('hide');
				$('.c_r_jersey').addClass('hide');
				$('.c_r_jersey img').attr('src', '');
				$('#cba_num').val('最多输入两位数字')
				             .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});
				return false;
			}

			var reg = /^\d+$/;
			if (!reg.exec(n) || n < 0) {
				comm_alert('请输入正确的战衣号码');
				$('#cba_num').val('最多输入两位数字')
	                         .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});
				var m = $.trim($('#cba_name').val());
				if (m == '' || m == '请输入5个中文/7个大写字母') {
					$('#cba_name').val('请输入5个中文/7个大写字母')
					              .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});
				}
				
				//显示默认图片
				var front_src = $('#h_front').val();
				var back_src = $('#h_back').val();
				$('.c_front').attr('src',front_src);
				$('.c_back').attr('src',back_src);
				$('.c_r_img').removeClass('hide');
				$('.c_r_jersey').addClass('hide');
				$('.c_r_jersey img').attr('src', '');
				return false;
			} else {
				$('#cba_num').val(parseInt(n));
			}

			//判断名称是否为空
			var m = $.trim($('#cba_name').val());
			if (m != '' && m != '请输入5个中文/7个大写字母') {
				var teamName = $('#h_team').val();

				//检查加印是否合法
				var chkUrl = "/shop/clothes_preview2015/index.php";
				chkUrl += "?action=check&source=buypage&cloth=" + teamName + "&name=" + encodeURI(m) + "&num="+parseInt(n);
				$.ajax({
					type: 'GET',
					url: chkUrl,
					dataType: 'JSON',
					success: function( o ) {
						if (o.name.success != 1) {
							$('#cba_name').val('请输入5个中文/7个大写字母')
							              .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});
							$('#cba_num').val('最多输入两位数字')
				                         .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});

							//显示默认图片
							var front_src = $('#h_front').val();
							var back_src = $('#h_back').val();
							$('.c_front').attr('src',front_src);
							$('.c_back').attr('src',back_src);
							$('.c_r_img').removeClass('hide');
							$('.c_r_jersey').addClass('hide');
							$('.c_r_jersey img').attr('src', '');
							comm_alert(o.name.msg);
							return false;
						}

						if (o.num.success != 1) {
							$('#cba_name').val('请输入5个中文/7个大写字母')
				              			  .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});
							$('#cba_num').val('最多输入两位数字')
				                         .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});

							//显示默认图片
							var front_src = $('#h_front').val();
							var back_src = $('#h_back').val();
							$('.c_front').attr('src',front_src);
							$('.c_back').attr('src',back_src);
							$('.c_r_img').removeClass('hide');
							$('.c_r_jersey').addClass('hide');
							$('.c_r_jersey img').attr('src', '');
							comm_alert(o.num.msg);
							return false;
						}
					}
				});

				var serverUrl = "/shop/clothes_preview2015/index.php";
				serverUrl += "?action=preview&source=buypage&cloth=" + teamName + "&name=" + m + "&num="+parseInt(n);

				//显示图片
				$('.c_r_img').addClass('hide');
				$('.c_r_jersey').removeClass('hide');
				$('.c_r_jersey img').attr('src', serverUrl);
			}

		} else if (flg == 3) {	//名称
			$('#cba_star').val('').attr('name', '');
			var n = $.trim($('#cba_name').val());
			if (n == '' || n == '请输入5个中文/7个大写字母') {
				var m = $.trim($('#cba_num').val());
				if (m == '' || m == '最多输入两位数字') {
					$('#cba_num').val('最多输入两位数字')
                    			 .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});
				}
				
				var front_src = $('#h_front').val();
				var back_src = $('#h_back').val();
				$('.c_front').attr('src',front_src);
				$('.c_back').attr('src',back_src);
				$('.c_r_img').removeClass('hide');
				$('.c_r_jersey').addClass('hide');
				$('.c_r_jersey img').attr('src', '');

				$('#cba_name').val('请输入5个中文/7个大写字母')
    			  			  .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});
				return false;
			}

			//五个中文字符\7个英文大写字母(可包含“.” 但是不能连续两个)
			if (this.check(n) < 0) {
				$('#cba_name').val('请输入5个中文/7个大写字母')
				  			  .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});
				
				var m = $.trim($('#cba_num').val());
				
				if (m == '' || m == '最多输入两位数字') {
					$('#cba_num').val('最多输入两位数字')
					 		     .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});
				}
				
				if (this.check(n) == -1) {
					comm_alert('请输入您需定制的姓名');
				} else if (this.check(n) == -2) {
					comm_alert('个性名称不支持含有空格，请重新输入');
				} else if (this.check(n) == -3) {
					comm_alert('个性名称超出字数限制，请重新输入');
				} else if (this.check(n) == -4) {
					comm_alert('个性名称不支持小写字母，请重新输入');
				} else if (this.check(n) == -5) {
					comm_alert('个性名称不支持数字，请重新输入');
				} else if (this.check(n) == -6) {
					comm_alert('个性名称仅支持纯中文或纯英文，请重新输入');
				} else if (this.check(n) == -7) {
					comm_alert('中文不可加“.”请重新输入');
				} else if (this.check(n) == -8) {
					comm_alert('不支持连续两个“.”请重新输入');
				} else if (this.check(n) == -9) {
					comm_alert('个性名称不支持符号，请重新输入');
				} else {
					comm_alert('请输入5个中文/7个大写字母');
				}
				
				var front_src = $('#h_front').val();
				var back_src = $('#h_back').val();
				$('.c_front').attr('src',front_src);
				$('.c_back').attr('src',back_src);
				$('.c_r_img').removeClass('hide');
				$('.c_r_jersey').addClass('hide');
				$('.c_r_jersey img').attr('src', '');
				return false;
			}

			//判断名称是否为空
			var m = $.trim($('#cba_num').val());
			if (m != '' && m != '最多输入两位数字') {
				var teamName = $('#h_team').val();

				//检查加印是否合法
				var chkUrl = "/shop/clothes_preview2015/index.php";
				chkUrl += "?action=check&source=buypage&cloth=" + teamName + "&name=" + encodeURI(n) + "&num="+parseInt(m);
				$.ajax({
					type: 'GET',
					url: chkUrl,
					dataType: 'JSON',
					success: function( o ) {
						if (o.name.success != 1) {

							$('#cba_name').val('请输入5个中文/7个大写字母')
							              .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});
							$('#cba_num').val('最多输入两位数字')
				                         .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});

							//显示默认图片
							var front_src = $('#h_front').val();
							var back_src = $('#h_back').val();
							$('.c_front').attr('src',front_src);
							$('.c_back').attr('src',back_src);
							$('.c_r_img').removeClass('hide');
							$('.c_r_jersey').addClass('hide');
							$('.c_r_jersey img').attr('src', '');
							comm_alert(o.name.msg);
							return false;
						}

						if (o.num.success != 1) {

							$('#cba_name').val('请输入5个中文/7个大写字母')
							              .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});
							$('#cba_num').val('最多输入两位数字')
				                         .css({'color': '#f38895', 'fontSize': '12px', 'fontWeight': 'normal'});

							//显示默认图片
							var front_src = $('#h_front').val();
							var back_src = $('#h_back').val();
							$('.c_front').attr('src',front_src);
							$('.c_back').attr('src',back_src);
							$('.c_r_img').removeClass('hide');
							$('.c_r_jersey').addClass('hide');
							$('.c_r_jersey img').attr('src', '');
							comm_alert(o.num.msg);
							return false;
						}
					}
				});

				var serverUrl = "/shop/clothes_preview2015/index.php";
				serverUrl += "?action=preview&source=buypage&cloth=" + teamName + "&name=" + n + "&num="+parseInt(m);

				//显示图片
				$('.c_r_img').addClass('hide');
				$('.c_r_jersey').removeClass('hide');
				$('.c_r_jersey img').attr('src', serverUrl);
			}
		}
	},
	
	//检查用户输入姓名
	check: function(name){
		
		var reg_dxzm = /^[A-Z.]+$/;
		
		//检查是否为空
		if (name == '' || name == '请输入5个中文/7个大写字母') {
			return -1;
		}
		
		//检查是否包含空格
		var reg_space = /\s/;
		if (reg_space.test(name)) {
			return -2;
		}
		
		//超出字符
		if ((Utils.isChinese(name) && name.length > 5) || (reg_dxzm.test(name) && name.length > 7)) {
			return -3;
		}
		
		//是否含小写字母
		var reg_letter = /[a-z]+/;
		if (reg_letter.test(name)) {
			return -4;
		}
		
		//是否包含数字
		var reg_num = /[0-9]+/;
		if (reg_num.test(name)) {
			return -5;
		}
		
		//中英文
		var reg_mix = /^[\u4e00-\u9fa5A-Z]{1,7}$/;
		if (!Utils.isChinese(name) && !reg_dxzm.test(name) && reg_mix.test(name)) {
			return -6;
		}
		
		//中文含.
		if (Utils.isChinese(name)) {
			var arr = name.split('.');
			if (arr.length > 1) {
				return -7;
			}
		}
		
		//英文连续含.
		if (reg_dxzm.test(name)) {
			var arr = name.split('..');
			if (arr.length > 1) {
				return -8;
			}
		}
		
		//是否包含符号
		if (!Utils.isChinese(name) && !reg_dxzm.test(name)) {
			return -9;
		}
		
		return 1;
	},
	
	//加数量
	plus: function(){
		var q = $('#cba_quantity').val();
		if (q == 99) {
			return false;
		}
		$('#cba_quantity').val(parseInt(q) + 1);
	},

	//减数量
	less: function(){
		var q = $('#cba_quantity').val();
		if (q == 1) {
			return false;
		}
		$('#cba_quantity').val(parseInt(q) - 1);
	},

	//查看尺码图
	chkSize: function(){
		$('#mask').removeClass('hide').height('10600px');
		$('#cba_size_img').removeClass('hide');
	},

	//加入购物车 flg 1:加入购物车 2:立即购买
	add: function(flg){

		if (!flg) {
			flg = 2;
		}

		//球队
		var team = $('#h_team').val();
		if (team == '' || team == null) {
			comm_alert('球队信息错误，请刷新页面重新操作或联系客服');
			return false;
		}
		
		//球星定制
		var star_name = $('#cba_star').attr('name');
		var star_num = $('#cba_star').val();

		//专属定制
		var cba_name = $.trim($('#cba_name').val());
		if (cba_name == '请输入5个中文/7个大写字母') {
			cba_name = '';
		}
		var cba_num = $.trim($('#cba_num').val());
		if (cba_num == '最多输入两位数字') {
			cba_num = '';
		}

		//尺码
		var postID = $('#cba_size').val();

		//购买数量
		var cba_quantity = $.trim($('#cba_quantity').val());
		
		//检验参数
		var reg = /^\d+$/;
		var src_type = 0;
		var src = $('.cr .c_r_tab').find('img').attr('src');
		if (src == 'http://cdn.lining.com/shop/lining/theme/xth2/images/custom/cba2015_tab_zsdz.png') {	//专属定制
			src_type = 1;
			if (cba_name == '') {
				comm_alert('请输入您需定制的姓名');
				return false;
			}
			
			if (this.check(cba_name) < 0) {
				if (this.check(cba_name) == -1) {
					comm_alert('请输入您需定制的姓名');
				} else if (this.check(cba_name) == -2) {
					comm_alert('个性名称不支持含有空格，请重新输入');
				} else if (this.check(cba_name) == -3) {
					comm_alert('个性名称超出字数限制，请重新输入');
				} else if (this.check(cba_name) == -4) {
					comm_alert('个性名称不支持小写字母，请重新输入');
				} else if (this.check(cba_name) == -5) {
					comm_alert('个性名称不支持数字，请重新输入');
				} else if (this.check(cba_name) == -6) {
					comm_alert('个性名称仅支持纯中文或纯英文，请重新输入');
				} else if (this.check(cba_name) == -7) {
					comm_alert('中文不可加“.”请重新输入');
				} else if (this.check(cba_name) == -8) {
					comm_alert('不支持连续两个“.”请重新输入');
				} else if (this.check(cba_name) == -9) {
					comm_alert('个性名称不支持符号，请重新输入');
				} else {
					comm_alert('请输入5个中文/7个大写字母');
				}
				return false;
			}
			
			if (cba_num == '') {
				comm_alert('请输入您需定制的号码');
				return false;
			}
			
			var reg = /^\d+$/;
			if (!reg.exec(cba_num) || cba_num < 0) {
				comm_alert('请输入正确的定制号码');
				return false;
			}
			
		} else {	//球星定制
			src_type = 2;
			if (star_name == '' || star_num == '') {
				comm_alert('请选择定制球星');
				return false;
			}
		}
		
		//尺码
		if (!reg.exec(postID) || postID <= 0) {
			comm_alert('请选择您需定制的球衣尺码');
			return false;
		}

		//数量
		if (!reg.exec(cba_quantity) || cba_quantity <= 0) {
			comm_alert('请选择定制数量');
			return false;
		}

		var params = {
			flg: flg,
			team: team,
			postID: postID,
			quant: cba_quantity
		};

		if (src_type == 2) {	//球星定制
			params.type = 1;
			params.name = star_name;
			params.number = star_num;

		} else {	//专属定制
			params.type = 2;
			params.name = cba_name;
			params.number = cba_num;
		}

		//调用xajax
		comm_box.loadding();
		xajax_do_req_cart(params, 29);
	},

	//加入购物车后回调
	success: function(params){

		comm_box.loadding_close();
		
		cart.goods_amount();

		if (params.type == 1) {
			cbaCustom.close(3);
			var scroll_h = document.documentElement.scrollHeight;
			$("#add_cart_success").css('zIndex', '1000');
			$("#cp_title").html("&nbsp;&nbsp;" + params.amount + "件商品加入购物车");
			var listHtml = "<tr><td rowspan=\"4\"><img class=\"goods_img\" src=\"" + params.imgLink + "\"></td><td class=\"tal blod\">" + params.goodsName + "</td></tr>";
			listHtml += "<tr><td class=\"tal\">加入数量：<span class=\"red\">" + params.amount + "</span></td></tr>";
    		listHtml += "<tr><td class=\"tal\">总计金额：<span class=\"red\">¥"+parseFloat(params.salePrice).toFixed(2)+"</span></td></tr>";
    		listHtml += "<tr><td class=\"tal\" style=\"height: 50px;\">";
	        listHtml += "<input type=\"button\" onclick=\"cart.successClose();\" value=\"继续购物\" class=\"input_action\">";
	        listHtml += "<input type=\"button\" onclick=\"cart.successCart();\" value=\"去结算\" class=\"input_action_off ml10\"></td></tr>";
	    	$("#success_html").html(listHtml);
	    	$("#add_cart_success").show();
		} else {
			xajax_do_req_cart('', 28);
		}
	},

	fail: function(str){
		comm_box.loadding_close();
		comm_alert(str);
	},

	//检查登录
	chkLogin: function(){
		customer.open_login('cbaCustom.add');
	}
};

// +----------------------------------------------------------------------
// | 定制商品end
// +----------------------------------------------------------------------

// +----------------------------------------------------------------------
// | 短T定制start
// +----------------------------------------------------------------------

//短T定制
var tshirtCustom = {
	//显示球队
	showTeam: function(){
		$('#tshirt_select_team').removeClass('hide');
		$('#tshirt_select_star').addClass('hide');
		$('#tshirt_select_size').addClass('hide');
	},
	//选择球队
	fillteam: function(obj){
		var teamCode = $(obj).attr('pystr');
		var teamName = $(obj).html();
		$('#tshirt_team_span').html(teamName);
		$('#tshirt_team').val(teamCode);
		$('#tshirt_star_span').html('请选择');
		$('#tshirt_select_team').addClass('hide');
		$('#tshirt_star').val('');
		$('#tshirt_name').val('');
		$('#tshirt_num').val('');
		$('#tshirt_size_span').html('请选择');
		$('#tshirt_size').val('');
		$('#tshirt_quantity').val('1');
		if (teamCode != ''){
			var htmlStr = '<span pystr="" class="tshirt_team_item" onclick="tshirtCustom.fillstar(this);">请选择</span>';
			$.each(this.members, function(k,v){
				if (k == teamCode){
					$.each(v, function(i,it){
						htmlStr += '<span pystr="'+it+'" class="tshirt_team_item" onclick="tshirtCustom.fillstar(this);">'+i+'</span>';
					});
				}
			});
			$('#tshirt_select_star').html(htmlStr).addClass('hide');
			var picUrl = '/postsystem/docroot/images/cps/company/tshirt/index.php?action=preview&team='+teamCode+'&name=&num=';
		}else {
			var picUrl = 'http://cdn.lining.com/shop/lining/theme/xth2/images/custom/tshirt_emptyprev.jpg';
			$('#tshirt_select_star').html('').addClass('hide');
		}
		$('#c_r_pic').attr('src',picUrl);
	},
	//显示球星
	showStar: function(){
		$('#tshirt_select_star').removeClass('hide');
		$('#tshirt_select_team').addClass('hide');
		$('#tshirt_select_size').addClass('hide');
	},
	//选择球星
	fillstar: function(obj){
		var starNum = $(obj).attr('pystr');
		var starName = $(obj).html();
		$('#tshirt_star_span').html(starName);
		$('#tshirt_star').val(starNum);
		var teamCode = $('#tshirt_team').val();
		$('#tshirt_num').val('');
		$('#tshirt_name').val('');
		$('#tshirt_size_span').html('请选择');
		$('#tshirt_size').val('');
		$('#tshirt_quantity').val('1');
		if (starNum != ''){
			$('#tshirt_name').attr('disabled','disabled').css('background','#eeeeee');
			$('#tshirt_num').attr('disabled','disabled').css('background','#eeeeee');
			var picUrl = '/postsystem/docroot/images/cps/company/tshirt/index.php?action=preview&team='+teamCode+'&name='+starName+'&num='+starNum;
		}else {
			$('#tshirt_name').removeAttr('disabled').css('background','#ffebeb');
			$('#tshirt_num').removeAttr('disabled').css('background','#ffebeb');
			var picUrl = '/postsystem/docroot/images/cps/company/tshirt/index.php?action=preview&team='+teamCode+'&name=&num=';
		}
		$('#tshirt_select_star').addClass('hide');
		$('#c_r_pic').attr('src',picUrl);
	},
	//显示尺码
	showSize: function(){
		var sizeObj = $('#sizelist').children();
		var htmlStr = '<span pystr="" class="tshirt_team_item" onclick="tshirtCustom.fullSize(this);">请选择</span>';
		if (sizeObj.length >= 1){
			$.each(sizeObj, function(){
				var enablesaleamount = Number($(this).attr('enablesaleamount'));//可售数量
				var postID = $(this).attr('id');//三级商品postID
				postID = postID.substr(10);
				var size = $(this).attr('value');
				if (enablesaleamount > 0){
					htmlStr += '<span pystr="'+postID+'" class="tshirt_team_item" onclick="tshirtCustom.fullSize(this);">'+size+'</span>';
				}
			});
		}
		$('#tshirt_select_size').html(htmlStr).removeClass('hide');
		$('#tshirt_select_star').addClass('hide');
		$('#tshirt_select_team').addClass('hide');
	},
	fullSize: function(obj){
		var postID = $(obj).attr('pystr');
		var size = $(obj).html();
		$('#tshirt_size_span').html(size);
		$('#tshirt_size').val(postID);
		$('#tshirt_select_size').addClass('hide');
	},
	cba_team: {
		'bayifubang': '八一男篮',
		'fujianxunxing': '福建男篮',
		'liaoninghengye': '辽宁男篮',
		'shanxifenjiu': '山西男篮',
		'zhejiangguangsha': '浙江广厦男篮',
		'beijingjinyu': '北京男篮',
		'guangdonghongyuan': '广东宏远男篮',
		'qingdaoshuangxing': '青岛男篮',
		'sichuanaijia': '四川男篮',
		'zhejiangjinniu': '浙江男篮',
		'dongguanliebao': '广东东莞男篮',
		'jiangsuzhongtie': '江苏男篮',
		'shandonggaosu': '山东男篮',
		'tianjinrongbao': '天津男篮',
		'guangdongfoshan': '广东佛山男篮',
		'jilinjiutainongshang': '吉林男篮',
		'shanghaimajisi': '上海男篮',
		'xinjiangguanghui': '新疆男篮'
	},
	//球队名称
	team: {
		//广东宏远、新疆广汇、天津荣刚、福建SBS浔兴
		280159: 'guangdonghongyuan',
		280114: 'xinjiangguanghui',
		245032: 'tianjinrongbao',
		245041: 'fujianxunxing',

		//八一富邦、北京首钢、佛山龙狮、江苏南钢
		245050: 'bayifubang',
		280168: 'beijingjinyu',
		245077: 'jiangsuzhongtie',
		245023: 'xinjiangguanghui',

		280456: 'jiangsuzhongtie',
		280492: 'dongguanliebao',

		//山东黄金、浙江广厦、青岛双星、上海东方
		280141: 'shandonggaosu',
		245095: 'zhejiangguangsha',
		245104: 'qingdaoshuangxing',
		280123: 'shanghaimajisi',

		//浙江稠州、吉林东北虎、东莞新世纪、辽宁恒业
		245140: 'dongguanliebao',
		280150: 'liaoninghengye',
		280483: 'fujianxunxing',
		280474: 'tianjinrongbao',

		//四川金强、山西中宇
		245696: 'sichuanaijia',
		245933: 'shanxifenjiu',
		245122: 'zhejiangjinniu',
		245131: 'jilinjiutainongshang',

		245068: 'guangdongfoshan',
		280087: 'zhejiangguangsha',
		280465: 'shanxifenjiu'
	},

    //球队队员信息
    members: {
        jiangsuzhongtie: {'胡雪峰': 55,'易立': 8,'孟达': 12,'刘亚晖': 9,'侯逸凡': 31},

        zhejiangjinniu: {'邱彪': 6,'张大宇': 24},

        shanxifenjiu: {'罗智': 10,'葛昭宝': 35, '邢志强': 11,'张学文': 33},

        qingdaoshuangxing: {'杨庚霖': 10,'李涛': 11,'罗旭东': 9},

        shandonggaosu: {'李敬宇': 8,'丁彦雨航': 23,'睢冉': 1,'陶汉林': 20,'普·杰特': 3},

        bayifubang: {'田宇翔': 7,'韩硕': 33,'王磊': 8,'德勒黑': 22,'许钟豪': 24,'邹雨宸': 10},

        fujianxunxing: {'赵泰隆': 9,'周启新': 1,'陈林坚': 15,'郭磊': 11,'王哲林': 22},

        dongguanliebao: {'罗汉琛': 9,'何忠勉': 20,'顾全': 12,'张凯': 7,'孙桐林': 55,'李慕豪': 21},

        guangdongfoshan: {'郑准': 41,'曾令旭': 6,'鞠明欣': 8,'史鸿飞': 7,'史俊': 3},

        sichuanaijia: {'于澍龙': 11,'徐韬': 9,'陈晓东': 15,'姚锴夫': 34},

        guangdonghongyuan: {'易建联': 9,'陈江华': 6,'王仕鹏': 7,'朱芳雨': 8,'刘晓宇': 10,'周鹏': 11},

        xinjiangguanghui: {'西热力江': 20,'苏伟': 10,'可兰白克': 13,'刘炜': 8},

        beijingjinyu: {'王骁辉': 33,'朱彦西': 13,'翟晓川': 20,'李根': 1,'张松涛': 35,'孙悦': 9,'马布里': 3,'张庆鹏': 5, '段江鹏': 11},

        tianjinrongbao: {'张智涵': 9,'张楠': 11,'张骥': 13,'孟祥龙': 14,'尚平': 20},

        jilinjiutainongshang: {'张彪': 14,'钟诚': 12},

        liaoninghengye: {'韩德君': 55,'赵继伟': 3,'郭艾伦': 13,'杨鸣': 12,'贺天举': 6,'李晓旭': 22,'吴扬': 18,'刘大为': 16},

        zhejiangguangsha: {'赵大鹏': 30,'苏若禹': 5,'王子瑞': 1,'王征': 13},

        shanghaimajisi: {'葛暘': 24,'蔡亮': 1,'翟逸': 22,'张兆旭': 23,'卢伟': 9}
    },

	//打开定制层
	open: function(){
		var htmlStr = '';
		$.each(this.cba_team, function(k,v){
			htmlStr += '<span pystr="'+k+'" class="tshirt_team_item" onclick="tshirtCustom.fillteam(this);">'+v+'</span>';
		});
		$('#tshirt_select_team').html(htmlStr);
		$("#pop_mask").removeClass("hide");
		$('#tshirt_custom').removeClass('hide').css('z-index','998');
		$('.tshirt_team_item').eq(5).click();
	},

	//关闭定制层
	close: function(flg){
		if (flg == 1) {
			$("#pop_mask").addClass("hide");
			$('#tshirt_custom').addClass('hide');

			$('#tshirt_select_team').addClass('hide');
			$('#tshirt_team').val('');
			$('#tshirt_select_star').addClass('hide');
			$('#tshirt_star').val('');
			$('#tshirt_name').val('');
			$('#tshirt_num').val('');
			$('#tshirt_select_size').addClass('hide');
			$('#tshirt_size').val('');
			$('#tshirt_select_star').html('');
			$('#tshirt_select_size').html('');

			$('#tshirt_team_span').html('请选择');
			$('#tshirt_star_span').html('请选择');
			$('#tshirt_size_span').html('请选择');
			$('#tshirt_name').removeAttr('disabled').css('background','#ffebeb');
			$('#tshirt_num').removeAttr('disabled').css('background','#ffebeb');

			$('#c_r_pic').attr('src','http://cdn.lining.com/shop/lining/theme/xth2/images/custom/tshirt_emptyprev.jpg');

		} else if (flg == 2) {
			$('#cba_size_img').addClass('hide');
			$('#mask').addClass('hide');
		}
	},

	//商品数量失去焦点
	blur: function(flg){
		var teamCode = $('#tshirt_team').val();
		if (teamCode == ''){
			comm_alert('请先选择球队');
			return false;
		}
		if (flg == 1) {	//商品数量
			var q = $.trim($('#tshirt_quantity').val());
			var reg = /^\d+$/;
	        if (!reg.exec(q) || q <= 0) {
	        	$('#tshirt_quantity').val(1);
	        }
		} else {
			//号码
			var starNum = $.trim($('#tshirt_num').val());
			//名字
			var starName = $.trim($('#tshirt_name').val());

			//号码不为空的情况下检测号码是否合法
			if (starNum != ''){
				var reg = /^\d+$/;
				if (!reg.exec(starNum) || starNum <= 0) {
					comm_alert('请输入正确的战衣号码');
					$('#tshirt_num').val('');
					return false;
				}
			}

			//名字不为空的情况下检测名字是否不合法
			if (starName != ''){
				//检查加印是否合法
				var chkUrl = "/shop/clothes_preview2014/index.php";
				chkUrl += "?action=check&source=buypage&cloth=" + teamCode + "&name=" + encodeURI(starName) + "&num="+starNum;
				$.ajax({
					type: 'GET',
					url: chkUrl,
					dataType: 'JSON',
					success: function( o ) {
						if (o.name.success != 1) {
							$('#tshirt_name').val('');
							comm_alert(o.name.msg);
							return false;
						}
						if (o.num.success != 1) {
							$('#tshirt_num').val('');
							comm_alert(o.name.msg);
							return false;
						}
					}
				});
			}
			var picUrl = '/postsystem/docroot/images/cps/company/tshirt/index.php?action=preview&team='+teamCode+'&name='+starName+'&num='+starNum;

			$('#c_r_pic').attr('src',picUrl);
		}
	},

	//加数量
	plus: function(){
		var q = $('#tshirt_quantity').val();
		if (q == 99) {
			return false;
		}
		$('#tshirt_quantity').val(parseInt(q) + 1);
	},

	//减数量
	less: function(){
		var q = $('#tshirt_quantity').val();
		if (q == 1) {
			return false;
		}
		$('#tshirt_quantity').val(parseInt(q) - 1);
	},

	//查看尺码图
	chkSize: function(){
		$('#mask').removeClass('hide').height('10600px');
		$('#cba_size_img').removeClass('hide');
	},

	//加入购物车 flg 1:加入购物车 2:立即购买
	add: function(flg){

		if (!flg) {
			flg = 2;
		}

		//球队拼音
		var team = $('#tshirt_team').val();
		var teamName = $('#tshirt_team_span').html();

		//明星号码、明星名称
		var star_name = $('#tshirt_star_span').html();
		var star_num = $.trim($('#tshirt_star').val());

		//个性名称
		var cba_name = $.trim($('#tshirt_name').val());

		//战衣号码
		var cba_num = $.trim($('#tshirt_num').val());

		//战衣尺码
		var postID = $('#tshirt_size').val();
		var spec = $('#tshirt_size_span').html();

		//选择数量
		var cba_quantity = $.trim($('#tshirt_quantity').val());

		//检验参数
		if (star_num != '' && (cba_name != '' || cba_num != '')) {
			comm_alert('球星定制和专属定制不能同时存在');
			return false;
		}

		if (team == '') {
			comm_alert('请选择定制球队');
			return false;
		}



		if (star_num == '' && cba_name == '' && cba_num == '') {
			comm_alert('请选择球星定制或填写专属定制');
			return false;
		}

		if (star_num == '' && (cba_name == '' || cba_num == '')) {
			comm_alert('请填写完整的专属定制信息');
			return false;
		}

		if (star_num == '' && !Utils.isChinese(cba_name)) {
			comm_alert('个性名称必须为中文');
			return false;
		}

		var reg = /^\d+$/;
		if (star_num == '' && (!reg.exec(cba_num) || cba_num <= 0)) {
			comm_alert('请输入正确的战衣号码');
			return false;
		}

		if (star_num != '' && (!reg.exec(star_num) || !Utils.isChinese(star_name))) {
			comm_alert('请选择正确的球星定制信息');
			return false;
		}

		if (!reg.exec(postID) || postID <= 0) {
			comm_alert('请选择战衣尺码');
			return false;
		}


		if (!reg.exec(cba_quantity) || cba_quantity <= 0) {
			comm_alert('请选择定制数量');
			return false;
		}


		var params = {
			flg: flg,
			team: team,
			postID: postID,
			quant: cba_quantity,
			teamName:teamName
		};

		if (star_num != '') {	//球星定制
			params.type = 1;
			params.name = star_name;
			params.number = star_num;

		} else {	//专属定制
			params.type = 2;
			params.name = cba_name;
			params.number = cba_num;
		}

		//调用xajax
		comm_box.loadding();
		xajax_do_req_cart(params, 44);
	},

	//加入购物车 flg 1:加入购物车 2:立即购买
	outadd: function(flg, arg){

		if (!flg) {
			flg = 2;
		}

		//球队拼音
		var team = arg.team;
		var teamName = arg.teamName;

		//明星号码、明星名称
		var star_name = arg.star_name;
		var star_num = Number(arg.star_num);

		//个性名称
		var cba_name = arg.cba_name;

		//战衣号码
		var cba_num = Number(arg.cba_num);

		//战衣尺码
		var postID = Number(arg.postID);

		//选择数量
		var cba_quantity = Number(arg.cba_quantity);

		//检验参数
		if (star_num != '' && (cba_name != '' || cba_num != '')) {
			comm_alert('球星定制和专属定制不能同时存在');
			return false;
		}

		if (team == '') {
			comm_alert('请选择定制球队');
			return false;
		}


		if (star_num == '' && cba_name == '' && cba_num == '') {
			comm_alert('请选择球星定制或填写专属定制');
			return false;
		}

		if (star_num == '' && (cba_name == '' || cba_num == '')) {
			comm_alert('请填写完整的专属定制信息');
			return false;
		}

		if (star_num == '' && !Utils.isChinese(cba_name)) {
			comm_alert('个性名称必须为中文');
			return false;
		}

		var reg = /^\d+$/;
		if (star_num == '' && (!reg.exec(cba_num) || cba_num <= 0)) {
			comm_alert('请输入正确的战衣号码');
			return false;
		}

		if (star_num != '' && (!reg.exec(star_num) || !Utils.isChinese(star_name))) {
			comm_alert('请选择正确的球星定制信息');
			return false;
		}

		if (!reg.exec(postID) || postID <= 0) {
			comm_alert('请选择战衣尺码');
			return false;
		}


		if (!reg.exec(cba_quantity) || cba_quantity <= 0) {
			comm_alert('请选择定制数量');
			return false;
		}


		var params = {
			flg: flg,
			team: team,
			postID: postID,
			quant: cba_quantity,
			teamName:teamName
		};

		if (star_num != '') {	//球星定制
			params.type = 1;
			params.name = star_name;
			params.number = star_num;

		} else {	//专属定制
			params.type = 2;
			params.name = cba_name;
			params.number = cba_num;
		}

		//调用xajax
		comm_box.loadding();
		xajax_do_req_cart(params, 44);
	},

	//加入购物车后回调
	success: function(params){

		comm_box.loadding_close();
		tshirtCustom.close(1);
		tshirtCustom.close(2);
		cart.goods_amount();

		if (params.type == 1) {
			$("#cp_title").html("&nbsp;&nbsp;" + params.amount + "件商品加入购物车");
			var listHtml = "<tr><td rowspan=\"4\"><img class=\"goods_img\" src=\"" + params.imgLink + "\"></td><td class=\"tal blod\">" + params.goodsName + "</td></tr>";
			listHtml += "<tr><td class=\"tal\">加入数量：<span class=\"red\">" + params.amount + "</span></td></tr>";
    		listHtml += "<tr><td class=\"tal\">总计金额：<span class=\"red\">¥"+parseFloat(params.salePrice).toFixed(2)+"</span></td></tr>";
    		listHtml += "<tr><td class=\"tal\" style=\"height: 50px;\">";
	        listHtml += "<input type=\"button\" onclick=\"cart.successClose();\" value=\"继续购物\" class=\"input_action\">";
	        listHtml += "<input type=\"button\" onclick=\"cart.successCart();\" value=\"去结算\" class=\"input_action_off ml10\"></td></tr>";
	    	$("#success_html").html(listHtml);
	    	$("#add_cart_success").show();
		} else {
			xajax_do_req_cart('', 28);
		}
	},

	fail: function(str){
		comm_box.loadding_close();
		comm_alert(str);
	},

	//检查登录
	chkLogin: function(){
		//$(".comm_pop .eb_div").css("width", "620px");
		customer.open_login('tshirtCustom.add');
	}
};

// +----------------------------------------------------------------------
// | 短T定制end
// +----------------------------------------------------------------------

// +----------------------------------------------------------------------
// | 复古套装start
// +----------------------------------------------------------------------

//复古套装
var retroGoods = {

	team : {
		285343 : 'bayi',
		285335 : 'guangdong',
		285403 : 'liaoning',
		285388 : 'jiangsu',
		285373 : 'shandong',
		285358 : 'zhejiang',
		285324 : 'beijing'
	},

	//打开定制层
	open: function(){
		//获取球队球衣的默认图片
		var retroID = $('#retroID').val();	//获取鞋的尺码,根据鞋的尺码获取上衣
		xajax_do_req_goods([retroID],14);
	},

	fail:function(error_msg){
		comm_alert(error_msg);
		return false;
	},

	//设置球衣图片
	pic:function(twoPostID){
		var team = retroGoods.team[twoPostID];
		if (team != null && team != undefined && team != '') {
			var src = '/postsystem/docroot/images/cps/company/fugu/index.php?action=preview&sku='+team+'&name=&num=';
			$('.c_r_jersey img').attr('src',src);

			$('#retro_src').val(src);
			$('#retro_team').val(team);
		}

		$('#retroGoods').removeClass('hide');	//显示定制层
	},

	//关闭定制层
	close: function(){
		$('#retroGoods').addClass('hide');
		$('#retro_name').val('');
		$('#retro_num').val('');
		$('#retro_quantity').val(1);

		//显示默认图片
		var retro_src = $('#retro_src').val();
		$('.c_r_jersey img').attr('src',retro_src);
	},

	//商品数量失去焦点
	blur: function(flg){
		if (flg == 1) {	//商品数量
			var q = $.trim($('#retro_quantity').val());
			var reg = /^\d+$/;
	        if (!reg.exec(q) || q <= 0) {
	        	$('#retro_quantity').val(1);
	        }
		} else if (flg == 2) {	//号码
			var m = $.trim($('#retro_name').val());
			var n = $.trim($('#retro_num').val());
			var teamName = $('#retro_team').val();

			if (n == '') {
				if (m == '') {
					//显示默认图片
					var retro_src = $('#retro_src').val();
					$('.c_r_jersey img').attr('src',retro_src);
				}
				return false;
			}

			var reg = /^\d+$/;
			if (!reg.exec(n) || n <= 0) {
				comm_alert('请输入正确的战衣号码');
				$('#retro_num').val('');
				return false;
			} else {
				$('#retro_num').val(parseInt(n));
			}

			m = encodeURI(m);

			//检查加印是否合法
			var chkUrl = "/postsystem/docroot/images/cps/company/fugu/index.php";
			chkUrl += "?action=preview&sku=" + teamName + "&name=" + m + "&num="+n;
			$.ajax({
				type: 'GET',
				url: chkUrl,
				dataType: 'JSON',
				success: function( o ) {
					if (o.name.success != 1) {

						$('#retro_name').val('');
						$('#retro_num').val('');

						//显示默认图片
						var retro_src = $('#retro_src').val();
						$('.c_r_jersey img').attr('src',retro_src);
						comm_alert(o.name.msg);
						return false;
					}

					if (o.num.success != 1) {

						$('#retro_name').val('');
						$('#retro_num').val('');

						//显示默认图片
						var retro_src = $('#retro_src').val();
						$('.c_r_jersey img').attr('src',retro_src);
						comm_alert(o.num.msg);
						return false;
					}
				}
			});

			//检查成功后，赋值
			chkUrl = '/postsystem/docroot/images/cps/company/fugu/index.php?action=preview&sku='+teamName+'&name='+m+'&num='+n;
			$('.c_r_jersey img').attr('src',chkUrl);

		} else if (flg == 3) {	//名称
			var m = $.trim($('#retro_name').val());
            var n = $.trim($('#retro_num').val());
            var teamName = $('#retro_team').val();

			if (m == '') {
				if (n == '') {
					//显示默认图片
					var retro_src = $('#retro_src').val();
					$('.c_r_jersey img').attr('src',retro_src);
				}
				return false;
			}

			if (!Utils.isChinese(m)) {
				$('#retro_name').val('');
				comm_alert('个性名称必须为中文');
				return false;
			}

			m = encodeURI(m);

			//检查加印是否合法
			var chkUrl = "/postsystem/docroot/images/cps/company/fugu/index.php?action=check&sku=" + teamName + "&name=" + m + "&num="+n;
			$.ajax({
				type: 'GET',
				url: chkUrl,
				dataType: 'JSON',
				success: function( o ) {
					if (o.name.success != 1) {
						$('#retro_name').val('');
						$('#retro_num').val('');

						//显示默认图片
						var retro_src = $('#retro_src').val();
						$('.c_r_jersey img').attr('src',retro_src);
						comm_alert(o.name.msg);
						return false;
					}

					if (o.num.success != 1) {
						$('#retro_name').val('');
						$('#retro_num').val('');

						//显示默认图片
						var retro_src = $('#retro_src').val();
						$('.c_r_jersey img').attr('src',retro_src);
						comm_alert(o.num.msg);
						return false;
					}
				}
			});

			//检查成功后，赋值
			chkUrl = '/postsystem/docroot/images/cps/company/fugu/index.php?action=preview&sku='+teamName+'&name='+m+'&num='+n;
			$('.c_r_jersey img').attr('src',chkUrl);
		}
	},

	//加数量
	plus: function(){
		var q = $('#retro_quantity').val();
		if (q == 99) {
			return false;
		}
		$('#retro_quantity').val(parseInt(q) + 1);
	},

	//减数量
	less: function(){
		var q = $('#retro_quantity').val();
		if (q == 1) {
			return false;
		}
		$('#retro_quantity').val(parseInt(q) - 1);
	},

	//加入购物车 flg 1:加入购物车 2:立即购买
	add: function(flg){

		if (!flg) {
			flg = 2;
		}

		//球队
		var team = $('#retro_team').val();
		if (team == '' || team == null || team == undefined) {
			team = 'bayi';
		}

		//获取主商品的postID
		var retroID = $('#retroID').val();

		//个性名称
		var retro_name = $.trim($('#retro_name').val());
		//战衣号码
		var retro_num = $.trim($('#retro_num').val());
		//选择数量
		var retro_quantity = $.trim($('#retro_quantity').val());

		//检验参数
		var error_msg = '';
		if (retro_name == '' || !Utils.isChinese(retro_name)) {
			error_msg += '个性名称必须为中文<br />';
		}
		var reg = /^\d+$/;
		if (retro_num == '' || !reg.exec(retro_num) || retro_num <= 0) {
			error_msg += '请输入正确的战衣号码<br />';
		}
		if (!reg.exec(retro_quantity) || retro_quantity <= 0) {
			error_msg += '请选择定制数量<br />';
		}

		if (error_msg != '') {
			comm_alert(error_msg);
			return false;
		}

		var params = {
			flg: 	flg,			//1，加入购物车 2，立即购买
			team: 	team,			//球队名称
			quant: 	retro_quantity,	//定制数量
			name: 	retro_name,		//定制姓名
			number: retro_num,		//定制号码
			retroID:retroID			//主商品的postID
		};

		//调用xajax
		xajax_do_req_cart(params, 39);
	},

	//加入购物车 详情页没有定制，直接加入购物车
	add2: function(){

		//获取主商品的postID
		var retroID = $('#retroID').val();
		var quant = $('#quantity_306687').val();

		var params = {
			flg 	: 1,
			retroID : retroID,
			quant 	: quant,
			type 	: 1
		};

		//调用xajax
		xajax_do_req_cart(params, 39);
	},

	//加入购物车后回调
    success: function(params){

        retroGoods.close();
        cart.goods_amount();

        if (params.type == 1) {
            $("#cp_title").html("&nbsp;&nbsp;" + params.amount + "件商品加入购物车");
            var listHtml = "<tr><td rowspan=\"4\"><img class=\"goods_img\" src=\"" + params.imgLink + "\"></td><td class=\"tal blod\">" + params.goodsName + "</td></tr>";
            listHtml += "<tr><td class=\"tal\">加入数量：<span class=\"red\">" + params.amount + "</span></td></tr>";
            listHtml += "<tr><td class=\"tal\">总计金额：<span class=\"red\">¥"+parseFloat(params.salePrice).toFixed(2)+"</span></td></tr>";
            listHtml += "<tr><td class=\"tal\" style=\"height: 50px;\">";
            listHtml += "<input type=\"button\" onclick=\"cart.successClose();\" value=\"继续购物\" class=\"input_action\">";
            listHtml += "<input type=\"button\" onclick=\"cart.successCart();\" value=\"去结算\" class=\"input_action_off ml10\"></td></tr>";
            $("#success_html").html(listHtml);
            $("#add_cart_success").show();
        } else {
            xajax_do_req_cart('', 28);
        }
    },

	//检查登录
	chkLogin: function(){
		//$(".comm_pop .eb_div").css("width", "620px");
		customer.open_login('retroGoods.add');
	}
};

// +----------------------------------------------------------------------
// | 复古套装end
// +----------------------------------------------------------------------


/****************************************************定制商品  加入购物车   display页面    start********************************************************/
var DISPLAYGOODS = {

	display_sku: '',
	display_amount: '',
	display_name: '',
	display_number: '',
	display_type: '',
	display_clickFlg: '',

    /**
     * 加入购物车
     * @param sku           商品SKU
     * @param amount        商品数量
     * @param name          商品定制姓名
     * @param number        商品定制号码
     * @param type          商品定制类型   1:球星定制     2：私人定制
     * @param clickFlg      点击类型        1,加入购物车   2，直接购买
     */
    add_to_cart : function(sku,amount,name,number,type,clickFlg){

    	this.display_sku = sku;
    	this.display_amount = amount;
    	this.display_name = name;
    	this.display_number = number;
    	this.display_type = type;
    	this.display_clickFlg = clickFlg;

    	this.real_add_cart();
    },

    real_add_cart: function(){
    	var sku = this.display_sku;
    	var amount = this.display_amount;
    	var name = this.display_name;
    	var number = this.display_number;
    	var type = this.display_type;
    	var clickFlg = this.display_clickFlg;

        if (type == '' ) {
            comm_alert('请选择球星定制或填写专属定制');
            return false;
        }

        if(type == 1){
            if (name == '' ||  number == '') {
                comm_alert('请填写完整的球星定制信息');
                return false;
            }
        }else{
            if ( number == '') {
                comm_alert('请填写完整的专属定制号码');
                return false;
            }
            if (name == '' && !Utils.isChinese(cba_name)) {
                comm_alert('个性名称必须为中文');
                return false;
            }
        }

        var reg = /^\d+$/;
        if (!reg.exec(number) || number <= 0) {
            comm_alert('请输入正确的战衣号码');
            return false;
        }

        if (!sku) {
            comm_alert('请选择战衣尺码');
            return false;
        }

        if (!amount) {
            comm_alert('请选择定制数量');
            return false;
        }

        var params = {
            flg: clickFlg,
            type: type,
            name: name,
            number: number,
            team: '',
            postID: '',
            sku: sku,
            quant: amount
        };

        //调用xajax
        comm_box.loadding();
        xajax_do_req_cart(params, 34);
    },

    //检查登录
	chkLogin: function(){
		//$(".comm_pop .eb_div").css("width", "620px");
		customer.open_login('DISPLAYGOODS.real_add_cart');
	},

    //加入收藏
    collect: function(sku){
        if (!sku) {
            comm_alert("参数错误");
            return false;
        }
        xajax_do_req_header(sku, 22);

    }
};
/****************************************************定制商品  加入购物车   display页面    end**********************************************************/

//搜索框获得焦点
$(document).on('focus','#searchTemp',function(){
    var keywords = $("#keywords_hidden").val();
    var searchTemp = $("#searchTemp").val();

    if( keywords == searchTemp ){
        $("#searchTemp").attr("value","");
    }
});

//搜索框失去焦点
//$(document).on('blur','#searchTemp',function(){
//    var keywords = $("#keywords_hidden").val();
//    var searchTemp = $("#searchTemp").val();
//    if( searchTemp == '' ){
//        $("#searchTemp").attr("value",keywords);
//    }
//});

