insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'role','系统管理员','1','1' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'role','销售主管','2','1' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'role','客户经理','3','1' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'role','高管','4','1' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'chancestatus','未分配','1','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'chancestatus','开发中','2','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'chancestatus','开发成功','3','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'chancestatus','已归档','4','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'isassign','未指派','2','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'isassign','指派','1','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'district','北京','1','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'district','华北','2','0' );      
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'district','中南','3','0' );   
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'district','西部','4','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'district','东北','5','0' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'grade','战略合作伙伴','1','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'grade','合作伙伴','2','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'grade','大客户','3','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'grade','重点开发客户','4','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'grade','普通客户','5','0' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'customerstatus','客户','1','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'customerstatus','预警','2','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'customerstatus','暂缓流失','3','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'customerstatus','确认流失','4','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'customerstatus','归档','5','0' ); 
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'sex','男','1','0' ); 
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'sex','女','2','0' ); 
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'post','总经理','1','0' ); 
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'post','总经理助理','2','0' ); 
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'post','供销部经理','3','0' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'orderstatus','未回款','1','0' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'orderstatus','已回款','2','0' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'servicetype','咨询','1','0' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'servicetype','投诉','2','0' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'servicetype','建议','3','0' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'servicestatus','新创建','1','0' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'servicestatus','已分配','2','0' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'servicestatus','已处理','3','0' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'servicestatus','已反馈','4','0' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'servicestatus','已归档','5','0' );
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'satisfaction','一星','1','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'satisfaction','二星','2','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'satisfaction','三星','3','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'satisfaction','四星','4','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'satisfaction','五星','5','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'credit','一星','1','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'credit','二星','2','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'credit','三星','3','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'credit','四星','4','0' );  
insert into datadirectorys( dtype,tiaomu,dvalue,isedit  ) values( 'credit','五星','5','0' );  
update datadirectorys set dname='用户角色' where dtype='role';
update datadirectorys set dname='机会状态' where dtype='chancestatus';
update datadirectorys set dname='指派状态' where dtype='isassign';
update datadirectorys set dname='地区' where dtype='district';
update datadirectorys set dname='企业客户等级' where dtype='grade';
update datadirectorys set dname='客户状态' where dtype='customerstatus';
update datadirectorys set dname='性别' where dtype='sex';
update datadirectorys set dname='职务' where dtype='post';
update datadirectorys set dname='订单状态' where dtype='orderstatus';
update datadirectorys set dname='服务类型' where dtype='servicetype';
update datadirectorys set dname='服务状态' where dtype='servicestatus';
update datadirectorys set dname='客户满意度' where dtype='satisfaction';
update datadirectorys set dname='信用度' where dtype='credit';

select * from datadirectorys where id=26;



insert into userinfo( uname,pwd,name,sex,userage,role,isassign )
 values( 'a','a','战三',26,2,1,'9' );
insert into userinfo( uname,pwd,name,sex,userage,role,isassign )
 values( 'b','b','李四',26,2,2,'9' );
 insert into userinfo( uname,pwd,name,sex,userage,role,isassign )
 values( 'c','c','王五',26,2,3,'9' );
 insert into userinfo( uname,pwd,name,sex,userage,role,isassign )
 values( 'd','d','小二',26,2,4,'9' );
 
 --md5和sha两次加密
 update userinfo set pwd='6f9b0a55df8ac28564cb9f63a10be8af6ab3f7c2' where id=1;
 update userinfo set pwd='ef7ad6c1faf1e46a706cd0615ada442bbc570b0e' where id=2;
 update userinfo set pwd='fbc1b6c1376532db725be86c00e0cdf2025f42aa' where id=3;
 update userinfo set pwd='3c3edb7d9a5fef73fab35b77f2c50767d7bd0a87' where id=4;

 select * from userinfo;



insert into customer( cname,district,grade,satisfaction,credit,
location,postcode,registermoney,yearincome,telphone,fax,website,license,legal,bank,
bankaccount,rentnumber,taxnumber,customerstatus,customermanagerid
)
 values( 'yc公司',11,18,45,50,'北京市海淀区成府路702号',
152632,10000,1000000,'100','010-62283396','www.conghai.com',' ','张飞','xxxx银行','1546513215645',' ',' ',21,1 
);
insert into customer( cname,district,grade,satisfaction,credit,
location,postcode,registermoney,yearincome,telphone,fax,website,license,legal,bank,
bankaccount,rentnumber,taxnumber,customerstatus,customermanagerid
)
 values( '聪海信息科技有限公司',11,18,45,50,'北京市海淀区成府路702号',
152632,10000,1000000,'100','010-62283396','www.conghai.com',' ','张飞','xxxx银行','1546513215645',' ',' ',21,1 
);
insert into customer( cname,district,grade,satisfaction,credit,
location,postcode,registermoney,yearincome,telphone,fax,website,license,legal,bank,
bankaccount,rentnumber,taxnumber,customerstatus,customermanagerid
)
 values( '要走有限公司',11,18,45,50,'北京市海淀区成府路702号',
152632,10000,1000000,'100','010-62283396','www.conghai.com',' ','张飞','xxxx银行','1546513215645',' ',' ',21,1 
);
insert into customer( cname,district,grade,satisfaction,credit,
location,postcode,registermoney,yearincome,telphone,fax,website,license,legal,bank,
bankaccount,rentnumber,taxnumber,customerstatus,customermanagerid
)
 values( '要走信息科技有限公司',11,18,45,50,'北京市海淀区成府路702号',
152632,10000,1000000,'100','010-62283396','www.conghai.com',' ','张飞','xxxx银行','1546513215645',' ',' ',21,1 
);
insert into customer( cname,district,grade,satisfaction,credit,
location,postcode,registermoney,yearincome,telphone,fax,website,license,legal,bank,
bankaccount,rentnumber,taxnumber,customerstatus,customermanagerid
)
 values( '流失信息科技有限公司',11,18,45,50,'北京市海淀区成府路702号',
152632,10000,1000000,'100','010-62283396','www.conghai.com',' ','张飞','xxxx银行','1546513215645',' ',' ',21,1 
);

select * from customer;



insert into contacter(customerid,cname,sex,post,telphone,cellphone,remark) values(1,'杨影',26,28,'010-68348438-326','13728838283',' ');
insert into contacter(customerid,cname,sex,post,telphone,cellphone,remark) values(2,'王琪',26,28,'010-68348438-326','13728838283',' ');
select * from contacter;



insert into contacthistory( contacterid,contacttime,place,summary,item,remark ) values(1,now(),'大酒店',' ',' ',' ');
select * from contacthistory;


insert into orderinfo( customerid,customername,ordertime,sendlocation,orderstatus ) values( 1,'yc公司','2011-01-08','哈哈家',31 );
insert into orderinfo( customerid,customername,ordertime,sendlocation,orderstatus ) values( 2,'聪海信息科技有限公司','2011-01-10','哈哈家',31 );
insert into orderinfo( customerid,customername,ordertime,sendlocation,orderstatus ) values( 2,'聪海信息科技有限公司','2011-01-08','哈哈家',31 );
insert into orderinfo( customerid,customername,ordertime,sendlocation,orderstatus ) values( 1,'yc公司','2011-01-15','哈哈家',31 );
insert into orderinfo( customerid,customername,ordertime,sendlocation,orderstatus ) values( 3,'要走有限公司','2011-01-15','哈哈家',31 );
insert into orderinfo( customerid,customername,ordertime,sendlocation,orderstatus ) values( 4,'要走信息科技有限公司','2011-01-15','哈哈家',31 );
insert into orderinfo( customerid,customername,ordertime,sendlocation,orderstatus ) values( 5,'流失信息科技有限公司','2011-01-15','哈哈家',31 );

select * from orderinfo;



insert into orderitem(orderid  ,pname ,ordernumber ,unit,price,countprice) values(1,'飞机','10','个',1000,10000);
insert into orderitem(orderid  ,pname ,ordernumber ,unit,price,countprice) values(1,'火箭','10','个',10000,100000);
insert into orderitem(orderid  ,pname ,ordernumber ,unit,price,countprice) values(2,'火箭','10','个',10000,100000);
insert into orderitem(orderid  ,pname ,ordernumber ,unit,price,countprice) values(3,'火箭','10','个',10000,100000);
insert into orderitem(orderid  ,pname ,ordernumber ,unit,price,countprice) values(4,'火箭','10','个',10000,100000);
insert into orderitem(orderid  ,pname ,ordernumber ,unit,price,countprice) values(5,'火箭','10','个',10000,100000);
insert into orderitem(orderid  ,pname ,ordernumber ,unit,price,countprice) values(5,'火箭','10','个',10000,100000);
insert into orderitem(orderid  ,pname ,ordernumber ,unit,price,countprice) values(5,'火箭','10','个',10000,100000);
insert into orderitem(orderid  ,pname ,ordernumber ,unit,price,countprice) values(6,'火箭','10','个',10000,100000);
insert into orderitem(orderid  ,pname ,ordernumber ,unit,price,countprice) values(7,'火箭','10','个',10000,100000);

select * from orderitem;

delete from chance where id=8;

insert into service(servicetype,summary,customerid,servicestatus,createperson,createdate) values(33,'询问收音机价格',1,36,'王五',now());
insert into service(servicetype,summary,customerid,servicestatus,createperson,createdate) values(33,'询问收音机价格',1,36,'王五',now());
insert into service(servicetype,summary,customerid,servicestatus,createperson,createdate) values(33,'询问收音机价格',1,36,'王五',now());
insert into service(servicetype,summary,customerid,servicestatus,createperson,createdate) values(34,'询问收音机价格',1,36,'王五',now());
insert into service(servicetype,summary,customerid,servicestatus,createperson,createdate) values(34,'询问收音机价格',1,36,'王五',now());
insert into service(servicetype,summary,customerid,servicestatus,createperson,createdate) values(34,'询问收音机价格',1,36,'王五',now());
insert into service(servicetype,summary,customerid,servicestatus,createperson,createdate) values(35,'询问收音机价格',1,36,'王五',now());
insert into service(servicetype,summary,customerid,servicestatus,createperson,createdate) values(35,'询问收音机价格',1,36,'王五',now());
insert into service(servicetype,summary,customerid,servicestatus,createperson,createdate) values(35,'询问收音机价格',1,36,'王五',now());

insert into chance values(1,'获取客户电话获取机会来源','yc公司','70','有意向采购公司的飞机','杨影','销售机会','李四','2016-8-19',3,'2016-8-20',6);
insert into chance values(2,'获取客户电话','聪海信息科技有限公司','70','有意向采购公司的火箭','王琪','销售机会','李四','2016-8-19',3,'2016-8-20',5);

select * from chance;

insert into productinfo(pname ,pversion,batch ,unit ,price ,remark) 
values('幸福牌电视机','818 TFT','2388 EA03','台',7500,'代生产');
insert into productinfo(pname ,pversion,batch ,unit ,price ,remark) 
values('幸福牌收音机','天语 007','7878 006A','台',36,'代生产');
insert into productinfo(pname ,pversion,batch ,unit ,price ,remark) 
values('海龙笔记本电脑','i60','2688 9966','台',9800,'');
insert into productinfo(pname ,pversion,batch ,unit ,price ,remark) 
values('海龙笔记本电脑','i61','2689 6688','台',16800,'');
insert into productinfo(pname ,pversion,batch ,unit ,price ,remark) 
values('海龙笔记本电脑','i61','2689 6689','台',15800,'');


insert into lost(orderinfoid,addmeasure) values(1,'请喝茶');

update lost set measure='请喝茶' where id=35;


insert into kucun(product,storehouse,storeplace,numbers ,remark)
values('幸福牌电视机-818 FFT-2388 EA03','北京-西直门库','EC-D2',16,'');
insert into kucun(product,storehouse,storeplace,numbers ,remark)
values('海龙笔记本电脑-i60-2688 9966','北京-大钟寺库','EA-B8',12,'');
insert into kucun(product,storehouse,storeplace,numbers ,remark)
values('海龙笔记本电脑-i60-2688 9966','北京-马甸库','EA-A6',8,'');
insert into kucun(product,storehouse,storeplace,numbers ,remark)
values('海龙笔记本电脑-i61-2689 6688','天津-恒远电子C库','北26位',36,'');
insert into kucun(product,storehouse,storeplace,numbers ,remark)
values('海龙笔记本电脑-i61-2689 6688','天津-恒远电子C库','北27位',36,'');
insert into kucun(product,storehouse,storeplace,numbers ,remark)
values('海龙笔记本电脑-i61-2689 6688','天津-恒远电子C库','北28位',30,'');
select * from lost;
delete from lost where id=34;
