drop table orderform;--订单表
drop table collection;--收藏表
drop table place;--地址表
drop table usersinfo;--个人信息表
drop table product;--商品表
drop table typesinfo;--类型表
drop table manager;--管理员表
drop table comments;--评论表

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
create sequence seq_manager start with 001 increment by 1;

--商品类型表
create table typesinfo(
   typesid int primary key,
   typename varchar2(20)
);
create sequence seq_types start with 1 increment by 1;

create  table  product(                --商品基本信息表
    pro_number   int primary key,                 --商品编号1
    pro_name     varchar2(100),                            --商品名2
    typesid  int,                                         --商品类别编号3
    pro_tagprice    int,                                --吊牌价4
	pro_price    int,							--销售价(2016-1-10增加)
    pro_img   varchar2(200),                            --商品介绍图片（路径）5
    detail varchar2(500),                                --商品的细节信息（介绍文字）6
    registertime varchar2(20),                                    --上架日期7
    removetime varchar2(20),                                     --下架日期8
    registerflag int,                              --是否已下架9
    mid int ,                                             --操作员10
    stock     int,                                       --库存11
    sex varchar2(10),                                     --性别12
    pro_size  varchar2(100),                            --尺码（拼接）13
    color varchar(200),                                 --颜色（图片）14
   constraint mid_ck foreign key(mid) references manager(mid),	--15
   constraint fk_type foreign key(typesid) references typesinfo(typesid)	--16
);
create sequence seq_pronum start with 1 increment by 1;

create table usersinfo(                     --用户信息表
    u_id     int primary key,              --用户编号
    u_name varchar(20) ,                    --登录名
    pwd    varchar(20),                    --密码
    realname varchar(10),                  --真实姓名
    sex   varchar2(8) constraint sex1_check  check(sex in('男','女','保密') ) , --性别
    birthday date,               --生日
    email    varchar2(20),       --邮箱
    tel_number varchar2(20),              --手机号码     
    balance   varchar2(10),         --余额
    score int,                   --积分
    expenditure  varchar2(10)        --消费总额   
);
create sequence seq_uid start with 101 increment by 1;


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

create table collection(             --用户收藏表
       collection_id int,            --收藏编号
        u_id int,
       pro_number int,
       constraint fk_uid foreign key(u_id) references usersinfo(u_id),
       constraint fk_pronumber foreign key(pro_number) references product(pro_number)
);
create sequence seq_collection start with 1 increment by 1;


create table orderform(         --订单
       orderid int primary key,              --订单编号
        riqi   date,    --购买日期
       u_id int ,               --用户编号
       pro_number int,              --商品编号
        buy_number int,         --购买数量
        buyprice int,         -- 购买价格
        pid int,
        orderflas int,
      constraint fk_p foreign key(pid) references place(pid),
     constraint fk1_id foreign key(pro_number) references  product(pro_number),
     constraint fk2_id foreign key(u_id) references  usersinfo(u_id)
);
create sequence seq_orderid start with 1 increment by 1;

create table comments(      --评论表
	   comments_id primary key,
       orderid int,
       coment varchar2(100)，
	   u_id int,
	   pro_number int
	 
);





--管理员表插入数据
insert into manager values(seq_manager.nextval,'刘赞','a');
insert into manager values(seq_manager.nextval,'段娟','a');
insert into manager values(seq_manager.nextval,'郭涛','a');
insert into manager values(seq_manager.nextval,'沈源','an');
insert into manager values(seq_manager.nextval,'sy','a');
--商品类型表插入数据
insert into typesinfo values(seq_types.nextval,'篮球鞋');
insert into typesinfo values(seq_types.nextval,'跑步鞋');
insert into typesinfo values(seq_types.nextval,'休闲鞋');
insert into typesinfo values(seq_types.nextval,'T恤');
insert into typesinfo values(seq_types.nextval,'卫衣');
insert into typesinfo values(seq_types.nextval,'裤装');
insert into typesinfo values(seq_types.nextval,'羽绒服');

--商品基本信息表插入数据
insert into product values(seq_pronum.nextval,'李宁征荣92 II男子经典休闲鞋',1,215,169,'images/img_1_1.png,images/img_1_2.png,images/img_1_3.png,images/img_1_4.png,images/img_1_5.png',
'李宁征荣92 II经典休闲鞋，运用简洁的色彩搭配加上复古跑鞋风格，简单大方，经典复古。侧面的李宁品牌标志正是“正交叉转体90度经单环起倒立落下成骑撑”这个被国际体联命名为“李宁交叉”的动作，体现了李宁先生在运动中的创新精神。鞋子上整齐的车线，增添鞋子的品质感。鞋底采用橡胶+EVA复合底，耐磨防滑，使用寿命长。',
to_date('2015-5-1','yyyy-MM-dd'),to_date('2016-5-30','yyyy-MM-dd'),0,001,100,'男','39,40,41','images/color_1_1.jpg,images/color_1_2.jpg,images/color_1_3.jpg');
insert into product values(seq_pronum.nextval,'李宁男子减震跑鞋',1,256,216,'images/img_2_1.png,images/img_2_2.png,images/img_2_3.jpg,images/img_2_4.png,images/img_2_5.png',null,
to_date('2015-5-1','yyyy-MM-dd'),to_date('2016-5-30','yyyy-MM-dd'),0,001,100,'男','39,40,41','images/color_2_1.jpg,images/color_2_2.jpg,images/color_2_3.jpg');

insert into product values(seq_pronum.nextval,'【2016新品】李宁男子轻质跑鞋',1,189,168,'images/img_3_1.png,images/img_3_2.png,images/img_3_3.png,images/img_3_4.png,images/img_3_5.png',null,
to_date('2015-5-1','yyyy-MM-dd'),to_date('2016-5-30','yyyy-MM-dd'),0,001,100,'男','39,40,41','images/color_3_1.jpg,images/color_3_2.jpg,images/color_3_3.jpg');
insert into product values(seq_pronum.nextval,'李宁男子城市户外鞋',1,189,168,'images/img_4_1.png,images/img_4_2.png,images/img_4_3.png,images/img_4_4.png,images/img_4_5.png',null,
to_date('2015-5-1','yyyy-MM-dd'),to_date('2016-5-30','yyyy-MM-dd'),0,001,100,'男','39,40,41','images/color_4_1.jpg,images/color_4_2.jpg,images/color_4_3.jpg');
	
insert into product values(seq_pronum.nextval,'李宁征荣92 II男子经典休闲鞋',1,365,325,'images/img_5_1.png,images/img_5_2.png,images/img_5_3.png,images/img_5_4.png,images/img_5_5.png',null,
to_date('2015-5-1','yyyy-MM-dd'),to_date('2016-5-30','yyyy-MM-dd'),0,001,100,'男','39,40,41','images/color_5_1.jpg,images/color_5_2.jpg,images/color_5_3.jpg');
insert into product values(seq_pronum.nextval,'李宁溢彩女子轻质跑鞋',1,325,296,'images/img_6_1.png,images/img_6_2.jpg,images/img_6_3.png,images/img_6_4.png,images/img_6_5.png',null,
to_date('2015-5-1','yyyy-MM-dd'),to_date('2016-5-30','yyyy-MM-dd'),0,001,100,'女','39,40,41','images/color_6_1.jpg,images/color_6_2.jpg,images/color_6_3.jpg');
insert into product values(seq_pronum.nextval,'李宁Air-Fluid W女子都市健步鞋',1,236,223,'images/img_7_1.png,images/img_7_2.png,images/img_7_3.png,images/img_7_4.jpg,images/img_7_5.jpg',null,
to_date('2015-5-1','yyyy-MM-dd'),to_date('2016-5-30','yyyy-MM-dd'),0,001,100,'女','39,40,41','images/color_7_1.jpg,images/color_7_2.jpg,images/color_7_3.jpg');
insert into product values(seq_pronum.nextval,'李宁征荣92 II女子经典休闲鞋',1,375,296,'images/img_8_1.jpg,images/img_8_2.jpg,images/img_8_3.jpg,images/img_8_4.jpg,images/img_8_5.jpg',null,
to_date('2015-5-1','yyyy-MM-dd'),to_date('2016-5-30','yyyy-MM-dd'),0,001,100,'女','39,40,41','images/color_8_1.jpg,images/color_8_2.jpg,images/color_8_3.jpg');
insert into product values(seq_pronum.nextval,'李宁女子城市户外鞋',1,375,296,'images/img_9_1.png,images/img_9_2.png,images/img_9_3.jpg,images/img_9_4.png,images/img_9_5.png',null,
to_date('2015-5-1','yyyy-MM-dd'),to_date('2016-5-30','yyyy-MM-dd'),0,001,100,'女','39,40,41','images/color_9_1.jpg,images/color_9_2.jpg,images/color_9_3.jpg');
insert into product values(seq_pronum.nextval,'李宁女子轻质冬季防滑跑鞋',1,375,296,'images/img_10_1.jpg,images/img_10_2.jpg,images/img_10_3.png,images/img_10_4.jpg,images/img_10_5.jpg',null,
to_date('2015-5-1','yyyy-MM-dd'),to_date('2016-5-30','yyyy-MM-dd'),0,001,100,'女','39,40,41','images/color_10_1.jpg,images/color_10_2.jpg,images/color_10_3.jpg');

--用户信息表插入数据
insert into usersinfo values(seq_uid.nextval,'老王','aaa','汪汪汪','男',to_date('1995-12-12','yyyy-MM-dd'),'1099176690@qq.com','15073485093',300.5,500,1000.0);
insert into usersinfo values(seq_uid.nextval,'老李','aaa','李克强','男',to_date('1995-11-12','yyyy-MM-dd'),'2099176690@qq.com','15173485093',0,300,2000.0);
insert into usersinfo values(seq_uid.nextval,'老金','aaa','金三胖','男',to_date('1995-10-12','yyyy-MM-dd'),'3099176690@qq.com','15273485093',0,500,800.0);



--收货地址表插入数据
insert into place values(seq_place.nextval,'湖南省衡阳市湖南工学院',101,'杨少侠','12961897789',411210);
insert into place values(seq_place.nextval,'湖南省长沙市芙蓉区',101,'周杰翔','13961897789',417701);
insert into place values(seq_place.nextval,'广东省广州市天河区',102,'周树强','14961897789',31212);
insert into place values(seq_place.nextval,'北京市西路四环',103,'习近平','15961897789',621211);

--用户收藏表插入数据
insert into collection values(seq_collection.nextval,101,1);
insert into collection values(seq_collection.nextval,102,1);
insert into collection values(seq_collection.nextval,103,2);

--订单表插入数据
insert into orderform values(seq_orderid.nextval,Sysdate,101,1,1,'99.00',1,1);
insert into orderform values(seq_orderid.nextval,to_date('2016-1-1','yyyy-MM-dd'),101,1,1,'199.00',2,1);
insert into orderform values(seq_orderid.nextval,to_date('2016-1-2','yyyy-MM-dd'),102,2,2,'399.00',3,1);
insert into orderform values(seq_orderid.nextval,to_date('2016-1-3','yyyy-MM-dd'),103,3,3,'269.00',4,1);

--评论表插入数据
insert into comments values(1,'好！');
insert into comments values(2,'差评！');


-----需要手动插入的数据表
select * from product;
select * from typesinfo;
select * from manager;
-----------------------------
------后期动态变化的数据表--		|
select * from usersinfo;  --|
select * from orderform;  --|
select * from place;	  --|
select * from collection; --|
select  * from comments;  --|
-----------------------------
commit;

delete sequence seq_place;
delete manager;
update orderform set orderflas=1 where u_id=101
select * from place where u_id=101; 
insert into orderform values(seq_orderid.nextval,getdate(),?,?,?,?,1,1);
insert into orderform values(seq_orderid.nextval,getdate(),?,?,?,?,1,1)
select o.orderid,o.buy_number,o.buyprice,p.shname,p.placename,p.postcode,p.shtel 
		from orderform o  inner join place  p on o.pid=p.pid where o.u_id=101			
select o.orderid,o.buy_number,o.buyprice,p.shname,p.placename,
				p.postcode,p.shtel, pro.pro_name,pro.pro_img,pro.pro_tagprice,pro.stock  from orderform o  inner join place  
				p on o.pid=p.pid     inner join product pro on  o.pro_number=pro.pro_number
				where o.u_id=101;			
select o.orderid,o.buy_number,o.buyprice,p.shname,p.placename,
				p.postcode,p.shtel,pro.pro_name,pro.pro_img,pro.pro_tagprice,pro.stock
				from orderform o  inner join place p on o.pid=p.pid 
				inner join product pro on  o.pro_number=pro.pro_number where o.u_id=101;			


select o.orderid,o.buy_number,o.buyprice,p.shname,p.placename,
				p.postcode,p.shtel from orderform o  inner join place 
				p on o.pid=p.pid where o.u_id=101
select * from product where pro_number=10;	
select pro_img,pro_price,pro_tagprice,pro_name from product where pro_number=10	
select pro_img,pro_price,pro_tagprice,pro_name from product where pro_number=1



----------------------2-28增加操作--------------------
	
	
	
	select c.u_id,p.pro_number,p.pro_img,p.pro_tagprice from  product p inner join collection c on p.pro_number=c.pro_number where u_id=101
	
insert into orderform values(seq_orderid.nextval,to_date('2015-7-1','yyyy-MM-dd'),101,18,4,375,1,1)	
select * from product
select * from orderform

select * from place
select * from(select a .*,rownum rn from (select  orderid,to_char(riqi,'yyyy-MM-dd'),u_id,pro_number,buy_number,buyprice,pid,orderflas from orderform order by orderid asc)a)




--分页查询测试语句
select * from(select a .*,rownum rn from 
	(select color,pro_name,pro_tagprice,pro_number from product  order by  
	pro_number desc) a
		where rownum &lt;=#{pageSize*pageNo} ) b where rn>#{pageSize*(pageNo-1)}



select * from(select a .*,rownum rn from 
	(select color,pro_name,pro_tagprice,pro_number from product order by pro_tagprice desc) a
		where rownum <=8 ) b where rn>0



select * from(select a .*,rownum rn from 
		(select color,pro_name,pro_tagprice,pro_number from product where pro_tagprice between 100 and 200) a
		where rownum <=8 ) b where rn>0



