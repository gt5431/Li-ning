<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
    <head>
    <meta charset="utf-8"/>
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
                    <a href="javaScript:void(0)" onClick="comment()"><li id="s3">商品已评价</li></a>
                    <a href="javaScript:void(0)"><li id="s3">商品待评价</li></a>
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
                 <div id="comment">
                     <p class="comm">商品评价/晒单</p>
                      <!--待评价的商品详情-->
                     <table cellspacing="1" cellpadding="0" width="770px">
                     <thead style="background:#000; height: 38px; width:770px; color:#FFF;">
                       <tr align="center">
                          <td width="128.3px">订单编号</td>
                          <td width="128.3px">商品信息</td>
                          <td width="128.3px">商品详情</td>
                          <td width="128.3px">购买时间</td>
                          <td width="128.3px">评价状态</td>
                       </tr>
                     </thead>
                     <tbody style="height:40px;">
                      <c:forEach items="${sessionScope.OrderList}" var= "order" >
                       <tr align="center">
                          <td>${order.orderid}</td>
                          <td>${order.pro_name}</td>
                          <td><img src="../${order.color.split(',')[0]}"style="width:80px;height:80px;"/> </td>
                          <td>2016-5-28</td>
                          <td><input type="button" value="未评价"  onclick="javascript:location.href='myning_nocomment.action?orderid=${order.orderid}'"/></td>
                       </tr>
                      </c:forEach> 
                     </tbody> 
                  </table>
               </div>
            </div>
        </div>
     <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>