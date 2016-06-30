<div id="product_sale" style="width:500px;height: 500px;"> </div>
<script type="text/javascript">
        
       		
       		
                var myChart =echarts.init(document.getElementById('product_sale')); 
                
                var option = {
                    tooltip: {
                        show: true
                    },
                    legend: {
                        data:['销量']
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : (function(){
                            	var arr=[];
                            		$.ajax({
                            			type:"post",
                            			async:false,
                            			url:"bar.do",
                            			data:{},
                            			dataType:"json",
                            			success:function(result){
                            				if(result){
                            					for(var i=0;i<result.length;i++){
                            						console.log(result[i].pro_name);
                            						arr.push(result[i].pro_name);
                            					}
                            				}
                            			},
                            			error:function(errorMsg){
                            				alert("å¾è¡¨è¯·æ±æ°æ®å¤±è´¥ï¼");
                            				myChart.hideLoading();
                            			}
                            		})
                            		return arr;
                            })
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"销量",
                            "type":"bar",
                            "data":(function(){
                            	var arr=[];
                            	  $.ajax({
                                        type : "post",
                                        async : false, //åæ­¥æ§è¡
                                        url : "bar.do",
                                        data : {},
                                        dataType : "json", //è¿åæ°æ®å½¢å¼ä¸ºjson
                                        success : function(result) {
                                        if (result) {
                                               for(var i=0;i<result.length;i++){
                                                  console.log(result[i].total);
                                                  arr.push(result[i].total);
                                                }  
                                        }
                                    },
                                    error : function(errorMsg) {
                                        alert("å¾è¡¨è¯·æ±æ°æ®å¤±è´¥å¦!");
                                        myChart.hideLoading();
                                    }
                                   })
                                  return arr;
                            
                            })
                        }
                    ]
                };
                
        	 // ä¸ºechartså¯¹è±¡å è½½æ°æ® 
                myChart.setOption(option); 

        
         
       
</script>
