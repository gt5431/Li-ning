drop table orderform;
drop table collection;
drop table place;
drop table usersinfo;
drop table product;
drop table typesinfo;
drop table manager;
drop table comments;

drop sequence seq_pronum;
drop sequence seq_uid;
drop sequence seq_orderid;
drop sequence seq_types;
drop sequence seq_manager;
drop sequence seq_place;
drop sequence seq_collection;


--管理员表
create table manager(
       mid int primary key,
       mname varchar2(10),
       mpwd varchar2(20)
);
create sequence seq_manager start with 1 increment by 1;

--商品类型表
create table typesinfo(
   typesid int primary key,
   typename varchar2(20)
);
create sequence seq_types start with 1 increment by 1;

--商品表
create  table  product(                --商品基本信息表
    pro_number   int primary key,                 --商品编号1
    pro_name     varchar2(100),                            --商品名2
    typesid  int,                                         --商品类别编号3
    pro_tagprice    int,                                --吊牌价4
    pro_price    int,              --销售价(2016-1-10增加)
    pro_img   varchar2(200),                            --商品介绍图片（路径）5
    detail varchar2(500),                                --商品的细节信息（介绍文字）6
    registertime date,                                   --上架日期7
    removetime date,                                     --下架日期8
    registerflag int,                              --是否已下架9(0,1)
    mid int ,                                             --操作员10
     stock   int,                                       --库存11
     sex varchar2(10),                                     --性别12
      pro_size  varchar2(100),                            --尺码（拼接）13
      color varchar(200),                                 --颜色（图片）14
   constraint mid_ck foreign key(mid) references manager(mid),	--15
   constraint fk_type foreign key(typesid) references typesinfo(typesid)	--16
);
create sequence seq_pronum start with 1 increment by 1;

--用户表
create table usersinfo(                     --用户信息表
    u_id     int primary key,              --用户编号
    username varchar(20) ,                    --登录名
    password    varchar(20),                    --密码
    realName varchar(10),                  --真实姓名
    sex   varchar2(8) constraint sex1_check  check(sex in('男','女','保密') ) , --性别
    birthday date,               --生日
    email    varchar2(20),       --邮箱
    phone varchar2(20),              --手机号码     
    balance   varchar2(10),         --余额
    score int,                   --积分
    expendTotal  varchar2(10)        --消费总额   
);
create sequence seq_uid start with 101 increment by 1;

--地址表
create table place(                      --收货地址表
       pid int primary key,                          --设置主键
       placename varchar2(50),           --地址
       u_id int,
       shname varchar2(10),              --收货人姓名
       shtel varchar2(20),                        --收货人手机号码
       postcode  int,                   --邮编
       constraint fk_place foreign key(u_id) references usersinfo(u_id)
);
create sequence seq_place start with 1 increment by 1;

--订单表
create table orderform(         --订单
        orderid int primary key,              --订单编号
        riqi   date,    --购买日期
        u_id int ,               --用户编号
        pro_number int,              --商品编号
        buy_number int,         --购买数量
        buyprice varchar2(20),         -- 购买价格
        pid int,
        orderflag int, --支付状态(0,1)
        commentsflag int,  --评论状态(0,1)
      constraint fk_p foreign key(pid) references place(pid),
     constraint fk1_id foreign key(pro_number) references  product(pro_number),
     constraint fk2_id foreign key(u_id) references  usersinfo(u_id)
);
create sequence seq_orderid start with 1 increment by 1;


--评论表
create table comments(
       comment_id int primary key,
       orderid int ,
       comment_date date,
       comment_content varchar2(200),
	   u_id int,
	   comment_image varchar2(200),
	   constraint uk_id foreign key(u_id) references usersinfo(u_id),
	   constraint uk_comm foreign key(orderid) references orderform(orderid)
);
create sequence seq_commentsid start with 1 increment by 1;
drop table comments;
drop sequence seq_commentsid 
--用户收藏表
create table collection(             
       collection_id int,            --收藏编号
       u_id int,
       pro_number int,
       constraint fk_uid foreign key(u_id) references usersinfo(u_id),
       constraint fk_pronumber foreign key(pro_number) references product(pro_number)
);
create sequence seq_collection start with 1 increment by 1;

commit;

-----需要手动插入的数据表
select * from product;    
select * from typesinfo;  
select * from manager;    
-----------------------------
------后期动态变化的数据表--|
select * from usersinfo;
select * from place;   
select * from orderform;
select  * from comments;
select * from collection;


--管理员表插入数据
insert into manager values(seq_manager.nextval,'刘赞','a');
insert into manager values(seq_manager.nextval,'段娟','a');
insert into manager values(seq_manager.nextval,'郭涛','a');
insert into manager values(seq_manager.nextval,'沈源','a');
insert into manager values(seq_manager.nextval,'sy','a');
--商品类型表插入数据
insert into typesinfo values(seq_types.nextval,'篮球鞋');
insert into typesinfo values(seq_types.nextval,'跑步鞋');
insert into typesinfo values(seq_types.nextval,'休闲鞋');
insert into typesinfo values(seq_types.nextval,'卫衣');
insert into typesinfo values(seq_types.nextval,'羽绒服');

--商品基本信息表插入数据
insert into product values(seq_pronum.nextval,'李宁征荣92 II男子经典休闲鞋',3,215,169,
'images/img_1_1.png,images/img_1_2.png,images/img_1_3.png,images/img_1_4.png,images/img_1_5.png',
'李宁征荣92 II经典休闲鞋，运用简洁的色彩搭配加上复古跑鞋风格，简单大方，经典复古。
侧面的李宁品牌标志正是“正交叉转体90度经单环起倒立落下成骑撑”这个被国际体联命名为“李宁交叉”的动作，体现了李宁先生在运动中的创新精神。
鞋子上整齐的车线，增添鞋子的品质感。鞋底采用橡胶+EVA复合底，耐磨防滑，使用寿命长。',
to_date('2016-06-04','yyyy-MM-dd'),to_date('2016-12-04','yyyy-MM-dd'),0,3,80,'男','39,40,41',
'images/color_1_1.jpg,images/color_1_2.jpg,images/color_1_3.jpg');
insert into product values(seq_pronum.nextval,'李宁男子减震跑鞋',2,256,216,
'images/img_2_1.png,images/img_2_2.png,images/img_2_3.jpg,images/img_2_4.png,images/img_2_5.png',null,
to_date('2016-06-04','yyyy-MM-dd'),to_date('2016-12-04','yyyy-MM-dd'),0,3,100,'男','39,40,41',
'images/color_2_1.jpg,images/color_2_2.jpg,images/color_2_3.jpg');
insert into product values(seq_pronum.nextval,'【2016新品】李宁男子轻质跑鞋',2,189,168,
'images/img_3_1.png,images/img_3_2.png,images/img_3_3.png,images/img_3_4.png,images/img_3_5.png',null,
to_date('2016-06-04','yyyy-MM-dd'),to_date('2016-12-04','yyyy-MM-dd'),0,3,100,'男','39,40,41',
'images/color_3_1.jpg,images/color_3_2.jpg,images/color_3_3.jpg');
insert into product values(seq_pronum.nextval,'李宁男子城市户外鞋',3,189,168,
'images/img_4_1.png,images/img_4_2.png,images/img_4_3.png,images/img_4_4.png,images/img_4_5.png',null,
to_date('2016-06-04','yyyy-MM-dd'),to_date('2016-12-04','yyyy-MM-dd'),0,3,100,'男','39,40,41',
'images/color_4_1.jpg,images/color_4_2.jpg,images/color_4_3.jpg');
insert into product values(seq_pronum.nextval,'李宁征荣92 II男子经典休闲鞋',3,365,325,
'images/img_5_1.png,images/img_5_2.png,images/img_5_3.png,images/img_5_4.png,images/img_5_5.png',null,
to_date('2016-06-04','yyyy-MM-dd'),to_date('2016-12-04','yyyy-MM-dd'),0,3,100,'男','39,40,41',
'images/color_5_1.jpg,images/color_5_2.jpg,images/color_5_3.jpg');
insert into product values(seq_pronum.nextval,'李宁溢彩女子轻质跑鞋',2,325,296,
'images/img_6_1.png,images/img_6_2.jpg,images/img_6_3.png,images/img_6_4.png,images/img_6_5.png',null,
to_date('2016-06-04','yyyy-MM-dd'),to_date('2016-12-04','yyyy-MM-dd'),0,3,100,'女','39,40,41',
'images/color_6_1.jpg,images/color_6_2.jpg,images/color_6_3.jpg');
insert into product values(seq_pronum.nextval,'李宁Air-Fluid W女子都市健步鞋',3,236,223,
'images/img_7_1.png,images/img_7_2.png,images/img_7_3.png,images/img_7_4.jpg,images/img_7_5.jpg',null,
to_date('2016-06-04','yyyy-MM-dd'),to_date('2016-12-04','yyyy-MM-dd'),0,3,100,'女','39,40,41',
'images/color_7_1.jpg,images/color_7_2.jpg,images/color_7_3.jpg');
insert into product values(seq_pronum.nextval,'李宁征荣92 II女子经典休闲鞋',3,375,296,
'images/img_8_1.jpg,images/img_8_2.jpg,images/img_8_3.jpg,images/img_8_4.jpg,images/img_8_5.jpg',null,
to_date('2016-06-04','yyyy-MM-dd'),to_date('2016-12-04','yyyy-MM-dd'),0,3,100,'女','39,40,41',
'images/color_8_1.jpg,images/color_8_2.jpg,images/color_8_3.jpg');
insert into product values(seq_pronum.nextval,'李宁女子城市户外鞋',3,375,296,
'images/img_9_1.png,images/img_9_2.png,images/img_9_3.jpg,images/img_9_4.png,images/img_9_5.png',null,
to_date('2016-06-04','yyyy-MM-dd'),to_date('2016-12-04','yyyy-MM-dd'),0,3,100,'女','39,40,41',
'images/color_9_1.jpg,images/color_9_2.jpg,images/color_9_3.jpg');
insert into product values(seq_pronum.nextval,'李宁女子轻质冬季防滑跑鞋',2,375,296,
'images/img_10_1.jpg,images/img_10_2.jpg,images/img_10_3.png,images/img_10_4.jpg,images/img_10_5.jpg',null,
to_date('2016-06-04','yyyy-MM-dd'),to_date('2016-12-04','yyyy-MM-dd'),0,3,100,'女','39,40,41',
'images/color_10_1.jpg,images/color_10_2.jpg,images/color_10_3.jpg');


--用户信息表插入数据

--收货地址表插入数据

--用户收藏表插入数据
delete from collection where pro_number>=1;
delete from orderform  where pro_number>=1;
--订单表插入数据

--评论表插入数据

commit;
delete from typesinfo where typesid=6; 

select o.orderid,o.riqi,o.pro_number,p.pro_name,p.color from orderform o,product p where commentsflag=0 and o.pro_number=p.pro_number and u_id=101
update product set pro_name=#{pro_name},typesid=#{typesid},pro_tagprice=#{pro_tagprice},pro_price=#{pro_price},pro_img=#{pro_img},detail=#{detail},
 		registertime=#{registertime},removetime=#{removetime},registerflag=#{registerflag},mid=#{mid},stock=#{stock},sex=#{sex},pro_size=#{proSize},color=#{color} 
 		where pro_number=#{pro_number}