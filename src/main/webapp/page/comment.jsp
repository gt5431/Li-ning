<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的李宁</title>
			 <link type="text/css" href="../css/Common1.css" rel="stylesheet" />
            <link type="text/css" href="../css/myNing.css" rel="stylesheet" />
		    <script type="text/javascript" src="../js/jquery-1.11.3.js"/></script>
			<script type="text/javascript" src="../js/public.js" /></script>
		    <script type="text/javascript" src="../js/comment.js" /></script>
		    <script type="text/javascript" src="../js/login.js" /></script>
</head>
<body>
   	 <jsp:include page="header.jsp"></jsp:include>
        <div id="member" style="font-family: 微软雅黑;">
        <!--会员中心左侧-->
          <div id="member_left">
               <div id="menber_top_left"><img src="../images/member_center_icon.png"></div>
                <!--交易管理-->
                <div class="menu">
                   <div class="kind">
                   <img src="../images/trade_manager.png"/><span style="font-size:18px;margin-left:16px;font-weight:bold;">交易管理</span>
                   </div>
                   <ul>
                   <a href="javaScript:void(0)" onClick=""><li>我的订单</li></a>
                   <a href="javaScript:void(0)" onClick=""><li>历史订单</li></a>
                   <a href="javaScript:void(0)" onClick="comment1()"><li id="s4">商品待评价</li></a>
                   <a href="javaScript:void(0)" onClick=""><li>退换货查询</li></a>
                   <a href="javaScript:void(0)" onClick=""><li>投诉进度查询</li></a>
                   </ul>
                </div>
                <!--个人应用-->
                <div class="menu">
                  <div class="kind">
                   <img src="../images/person_appli.png"/><span style="font-size:18px;margin-left:16px;font-weight:bold;">个人应用</span>
                   </div>
                    <ul>
                    <a href="javaScript:void(0)"><li>我的咨询</li></a>
                    <a href="javaScript:void(0)"><li>我的收藏</li></a>
                   </ul>
                </div>
                <!--账户信息-->
                <div class="menu">
                   <div class="kind">
                   <img src="../images/account_info.png"/><span style="font-size:18px;margin-left:16px;font-weight:bold;">账户信息</span>
                   </div>
                    <ul>
                    <a href="javaScript:void(0)"><li>账户安全</li></a>
                    <a href="javaScript:void(0)"><li>账户余额</li></a>
                    <a href="javaScript:void(0)"><li>我的积分</li></a>
                    <a href="javaScript:void(0)"><li>优惠券</li></a>
                   </ul>
                </div>
                <!--个人设置-->
                <div  class="menu">
                     <div class="kind">
                     <img src="../images/person_set.png"/><span style="font-size:18px;margin-left:16px;font-weight:bold;">个人设置</span> 
                     </div>
                     <ul>
                        <a href="javaScript:void(0)"><li>个人信息</li></a>
                        <a href="javaScript:void(0)"><li>收货地址</li></a>
                        <a href="javaScript:void(0)"><li>发票管理</li></a>
                     </ul>
                </div>
            </div>
            <!--会员中心右侧-->
            <div id="member_right">
                <div id="rule">
                   <p class="r">评价奖励积分规则</p>
                   <p class="u">您的积分可以帮其他用户做出购物参考，同时您可以或得积分奖励。<br/>小积分大用处，积分可兑换礼品和商品优惠券，快来评价吧。</p> 
                   
                </div>
                 <div id="comment1">
                     <div id="pingjia">
                         <h3 class="title">评价宝贝</h3>
                     </div>
                     <div id="picture">
                        <img src="../${OrderImg.color.split(',')[0]}" class="img1"/>
                        <p>${OrderImg.pro_name}</p>
                        <img src="../images/c.PNG"/>
                     </div>
                     <div id="comment2">
                         <span>亲，写点评价吧，你的评价对其他买家有很大帮助的。</span>
                         <img src="../images/hua.PNG"/>
                         <img src="../images/hua.PNG"/> 
                              <form action="comment_comment1.action" method="post">
                                 <textarea rows="6" cols="60" name="comment_content" type="text"></textarea>
                                 <input type="hidden" name="orderid" value="${OrderImg.orderid}"/>
                                 <input type="hidden" name="u_id" value="${sessionScope.usersinfo.u_id}"/>
                                 <a href=""><img src="../images/zhao.PNG" style="margin-top:2px;"/><img src="../images/shai.PNG" style="margin-top:2px;margin-left:3px;"/></a>
                                 <input type="submit" value="提交评价" style="margin-left:440px;margin-top:0px;"/>
                              </form>
                     </div>
                 </div>
                
				  
            </div>
            
        </div>
                  
     <jsp:include page="footer.jsp"></jsp:include>

</body>
</html>