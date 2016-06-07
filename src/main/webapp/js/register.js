$(function(){
//表单验证
	$("#username").bind({
		focus:function(){
			$('#content_register .line .info_user').css('display', 'block');
			$('#content_register .line .error_user').css('display', 'none');
			$('#content_register .line  .succ_user').css('display', 'none');
			$('#content_register .line  .succ_user_img').css('display', 'none');
			$('#content_register .line  .error_user_img').css('display', 'none');
		},
		blur:function(){
			if ($("#username").val()==null) {
				$('#content_register .line .info_user').css('display', 'block');
				$('#content_register .line .error_user').css('display', 'none');
				$('#content_register .line  .succ_user_img').css('display', 'none');
				$('#content_register .line  .error_user_img').css('display', 'block');
				
				$('#content_register .line  .succ_user').css('display', 'none');
			} else if (!/[a-zA-Z0-9_]{2,20}/.test($("#username").val())) {
				$('#content_register .line  .error_user').css('display', 'block');
				$('#content_register .line  .succ_user_img').css('display', 'none');
				$('#content_register .line  .error_user_img').css('display', 'block');
				$('#content_register .line  .info_user').css('display', 'none');
				$('#content_register .line  .succ_user').css('display', 'none');
				$("#username").val('');
				
			} else {
				$('#content_register .line  .succ_user').css('display', 'block');
				$('#content_register .line  .succ_user_img').css('display', 'block');
				$('#content_register .line  .error_user_img').css('display', 'none');
				$('#content_register .line  .error_user').css('display', 'none');
				$('#content_register .line   .info_user').css('display', 'none');
			}
		}
	});
			
			
	//密码验证
	$("#content_register #userpwd").bind({//error_pass_img
		focus:function(){
			$('#content_register .info_pass').css('display', 'block');
			$('#content_register .error_pass').css('display', 'none');
			$('#content_register .succ_pass').css('display', 'none');
			$('#content_register .line  .succ_pass_img').css('display', 'none');
			$('#content_register .line  .error_pass_img').css('display', 'none');
		},
		blur:function(){
			if ($("#content_register  #userpwd").val()== null) {
				$('#content_register .info_pass').css('display', 'none');
				$('#content_register .line  .succ_pass_img').css('display', 'none');
				$('#content_register .line  .error_pass_img').css('display', 'block');
			} else {
				if (check_pass(this)) {
					$('#content_register .info_pass').css('display', 'none');
					$('#content_register .error_pass').css('display', 'none');
					$('#content_register .succ_pass').css('display', 'block');
					$('#content_register .line  .succ_pass_img').css('display', 'block');
					$('#content_register .line  .error_pass_img').css('display', 'none');
				} else {
					$('#content_register .info_pass').css('display', 'none');
					$('#content_register .error_pass').css('display', 'block');
					$('#content_register .succ_pass').css('display', 'none');
					$('#content_register .line  .succ_pass_img').css('display', 'none');
					$('#content_register .line  .error_pass_img').css('display', 'block');
					$("#content_register  #userpwd").val('');
				}
			}
		}
	});
			
			
	
	//密码强度验证
	$("#content_register  #userpwd").bind({
			keyup:function () {
				check_pass(this);
			}
	});
	
	//密码验证函数
	function check_pass(_this) {
		var value = $(_this).val();
		var value_length = value.length;
		var code_length = 0;
		var flag = false;
		
		//第一个必须条件的验证6-20位之间
		if (value_length >= 6 && value_length <= 20) {
			$('#qiangdu .q1').css('background', 'green');
		} else {
			$('#qiangdu .q1').css('background', '#666');
		}
		
		//第二个必须条件的验证，字母或数字或非空字符，任意一个即可
		if (value_length > 0 && !/\s/.test(value)) {
			$('#qiangdu .q2').css('background', 'green');
		} else {
			$('#qiangdu .q2').css('background', '#666');
		}
		
		//第三个必须条件的验证，大写字母，小写字母，数字，非空字符 任意两种混拼即可
		if (/[0-9]/.test(value)) {
			code_length++;
		}
		
		if (/[a-z]/.test(value)) {
			code_length++;
		}
		
		if (/[A-Z]/.test(value)) {
			code_length++;
		}
		
		if (/[^0-9a-zA-Z]/.test(value)) {
			code_length++;
		}
		
		if (code_length >= 2) {
			$('#qiangdu .q3').css('background', 'green');
		} else {
			$('#qiangdu .q3').css('background', '#666');
		}
		
		//安全级别
		if (value_length >= 10 && code_length >= 3) {
			$('#qiangdu  .q1').css('background', 'green');
			$('#qiangdu .q2').css('background', 'green');
			$('#qiangdu .q3').css('background', 'green');
			$('#qiangdu .q4').html('高').css('color', 'green');
		} else if (value_length >= 8 && code_length >= 2) {
			$('#qiangdu  .q1').css('background', '#f60');
			$('#qiangdu  .q2').css('background', '#f60');
			$('#qiangdu  .q3').css('background', '#ccc');
			$('#qiangdu  .q4').html('中').css('color', '#f60');
		} else if (value_length >= 1) {
			$('#qiangdu  .q1').css('background', 'maroon');
			$('#qiangdu  .q2').css('background', '#ccc');
			$('#qiangdu  .q3').css('background', '#ccc');
			$('#qiangdu  .q4').html('低').css('color', 'maroon');
		} else {
			$('#qiangdu  .q1').css('background', '#ccc');
			$('#qiangdu  .q2').css('background', '#ccc');
			$('#qiangdu  .q3').css('background', '#ccc');
			$('#qiangdu  .q4').html(' ');
		}	
		
		if (value_length >= 6 && value_length <= 20 && !/\s/.test(value) && code_length >= 2) 
			flag = true;
		
		return flag;
	}
	
	//密码是否相同
	$("#content_register  .sure_pd").bind({
		focus:function(){	
			('#content_register .line  .error_sure_img').css('display', 'none');
			$("#content_register  .sure_pd  .suret1").css('display', 'none');
		},
		blur:function(){
			if($("#content_register  .sure_pd").val()!=$("#content_register  #userpwd").val()){
				$("#content_register   .suret1").css('display','block');
				$('#content_register .line  .error_sure_img').css('display', 'block');
			}else{
				$("#content_register   .suret1").css('display','none');
				$('#content_register .line  .error_sure_img').css('display', 'none');
			}
		}
	});
	
	
	//表单提交测试
	function regCheck(){//$("#username").val()==null  !/[a-zA-Z0-9_]{2,20}/.test($("#username").val()
		if(     $("#username").val()==null 
				|| !/[a-zA-Z0-9_]{2,20}/.test($("#username").val())
				|| $("#content_register  #userpwd").val()== null 
				||  $("#content_register  .sure_pd").val()==null
				|| $("#content_register  .sure_pd").val()!=$("#content_register  #userpwd").val()
				|| $("#checkBox").is(':checked')==false
				|| !/^[1][358][0-9]{9}$/.test($("#telphone").val())
				|| !/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test($("#email").val())
				 ){
			
				return false;	
		}	
		return true;
		
	}
	$("#regsubmit").click(function(){
		if(true==regCheck()){
			$.ajax({
	   			type: "POST",
	   			url: "user_register.action",
	   			data:$("#regiterMyform").serialize(),
	   			dataType:"JSON",
<<<<<<< HEAD
	   			success: function(data){			//查询成功后弹窗
	   				if(data == null){
=======
	   			success: function(msg){			//查询成功后弹窗
	   				if(msg == null){
>>>>>>> refs/remotes/origin/luohongmei
	   					window.location.href="login.jsp";
	   				}else{
	   					window.location.href="register.jsp";
	   				}
	   			}
			});
			
		}else{
			if(!/^[1][358][0-9]{9}$/.test($("#telphone").val())){
				$("#content_register   .checktel").css('display','block');
			}
			if(!/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test($("#email").val())){
				$("#content_register   .sureEmail").css('display','block');
			}
		}
	});

});
