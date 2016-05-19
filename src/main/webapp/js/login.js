
function login(){
	alert("登录");
	$("#Login").submit();
}

//刷新验证码

	function recode(){
		$(".code").attr("src","code.do?t="+Math.random());
		$("#checkcode").val("");
	}

	function checkName(){
		
	}
///登录验证
$(function(){
	$("#Login").click(function(){
		$("#Login").submit();
		//TODO:判断三个字段是否为空，为空则不上传
		$.ajax({
   			type: "POST",
   			url: "user_login.action",
   			data:$("#loginMyform").serialize(),
   			success: function(data){			//查询成功后弹窗
   				if(data[0]=="0"){// 失败
	   				//TODO：弹出提示，清空登录内容框，刷新验证码
	   				alert("登陆失败 ");
   				if(data[1]=="x"){
   					$("#content_login #pwd_error").css("display","block");
   				}else if(data[1]=="y"){
   					$("#content_login  #yzm_error").css("display","block");
   				}else if(data[1]=="z"){
   					$("#content_login  #yzm_error").css("display","block");
   					$("#content_login  #pwd_error").css("display","block");
   				}
   				
	   			}else if(data=="1"){
	   				//如果成功了，用户已经存到session，直接跳转到index界面，index界面上的用户信息内容用el表达式来判断和填充
	   				//不处理，在服务器进行跳转
	   				location.href="index.jsp";
	   			}
   			}
		});
	});
	recode();	
});

