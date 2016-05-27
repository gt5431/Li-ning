var xmlHttp;
//创建
function createXMLHttpRequest(){
	 	if(window.XMLHttpRequest){
	 		xmlHttp =new XMLHttpRequest();
	 	}else if(window.ActiveXObject){
	 		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	 	}
	 	return xmlHttp;
	 }
	 
	 //请求方法
	 function startRequest(){
	 	createXMLHttpRequest();
	 	xmlHttp.onreadystatechange=handleStateChange;  //状态发生改变时调用handleStateChange
	 	xmlHttp.open("POST", "doRes.jsp?username=lydia&userpwd=aaaaaa", true);
	 	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	 	xmlHttp.send("username=lydia&userpwd=aaaaaa");
	 	xmlHttp.send(null);
	 }
	 
	 //回调函数
	 function handleStateChange(){
	     if(xmlHttp.readyState ==4){
	     	if(xmlHttp.status ==200){//服务器响应正常
	     		 var data =xmlHttp.responseText;
	     		 return data;
	     	}
	     }
	 }
	 
	 
	 
	 