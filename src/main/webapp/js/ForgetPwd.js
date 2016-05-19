$(function(){
	$("#F_userName").bind({
		focus:function(){
			$('#span_name').css('display','none');	
		},
		blur:function(){
			if($('#F_userName').val()==''){
				$('#span_name').css('display','block');
			}
		}
	});
	
	$("#F_userYzm").bind({
		focus:function(){
			$('#span_yzm').css('display','none');	
		},
		blur:function(){
			if($('#F_userYzm').val()==''){
				$('#span_yzm').css('display','block');
			}
		}
	});
	
	
	
	
	
	
	
	
	
	
	
});









