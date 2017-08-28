	// JavaScript Document
	function comment2(){
		  $("#member #member_right").css("display","none");
		  $("#member #member_right1").css("display","block");
		  $("#s4").css("color","red");
		  $("#s3").css("color","black");
	}
	function comment1(){
		  $("#member #member_right").css("display","block");
		  $("#member #member_right1").css("display","none");

		  $("#s3").css("color","red");
		  $("#s4").css("color","black");
	}
	
	//图片预览
	function choosePic(obj){
		var fs=obj.files;
		var picStr="";
		for(var i=0;i<fs.length;i++){
			var picPath=window.URL.createObjectURL(obj.files[i]);
			picStr+="<img src='"+picPath+"' style='width:100px;'>"
		}
		document.getElementById("pics").innerHTML= picStr;
	}
	
	

    
	