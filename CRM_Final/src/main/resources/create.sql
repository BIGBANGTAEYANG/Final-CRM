select * from userinfo;
create table userinfo( 							
	id int primary key auto_increment ,							
	uname varchar(100) unique not null,			
	pwd varchar(100) unique not null,				
	name varchar(50) not null,						
	sex int not null,								
	userage int not null,							
	role int,									
	isassign  int  									
);

create table customer( 						 
	id int primary key auto_increment,								 
	cname varchar(100) not null,					 
	district  int not null,							 
	grade int not null,								 
	satisfaction int not null,						 
	credit   int not null,							 
	location  varchar(50) not null,				 
    postcode  int not null,							 
	telphone  varchar(50) not null,				 
	fax	varchar(50) not null,						 
	website varchar(100) not null,					 
	license varchar(100),							 
	legal  varchar(100),							 
	registermoney int ,								 
	yearincome	int,								 
	bank varchar(100) not null,					 
	bankaccount varchar(100) not null,				 
	rentnumber varchar(50),						 
	taxnumber varchar(50),							 					
	customerstatus int 	,							 
	customermanagerid int							 
);
alter table customer modify district int;
alter table customer modify grade int;
alter table customer modify satisfaction int;
alter table customer modify credit int;
alter table customer modify location  varchar(50);
alter table customer modify telphone  varchar(50);
alter table customer modify postcode  int;
alter table customer modify fax  varchar(50);
alter table customer modify website  varchar(50);
alter table customer modify bank  varchar(50);
alter table customer modify bankaccount  varchar(50);


select * from customer;
delete from customer where id=7;
 
create table contacter(						 
	id int primary key auto_increment,								 
	customerid int ,										 
	cname varchar(50) not null,					 
	sex	int not null ,								 
	post int not null,								 
	telphone  varchar(50) not null,				 
	cellphone  varchar(50) not null,				 
	remark   varchar(200) not null					 
);
select * from contacter;
delete from contacter where id=50;

alter table contacter add constraint fk_customerid foreign key(customerid) references  customer(id);

create table chance(						 
	id int primary key auto_increment,								 
	chancesource  varchar(50),						 
	customername   varchar(50) not null,			 
	success varchar(50) not null,					 
	chancesummary   varchar(100),					 
	contacename varchar(50),						 
	chancedescribe  varchar(200) ,						 
	creater  varchar(50) ,							 
	createtime date not null,						 
	userid int ,									 
	assigntime date not null,					 
	chancestatus int								 
);
alter table chance add constraint fk_userid foreign key(userid) references  userinfo(id);

create table plan (							 
	id int primary key auto_increment,								 
	chanceid  int ,									 
	plantime date not null,							 
	planitem varchar(50) not null,					 
	result varchar(50) not null					 
);

insert into plan values(1,3,'2016-8-27','plan','未执行')

alter table plan add constraint fk_chanceid foreign key(chanceid) references  chance(id);

create table contacthistory(						 
	id int primary key auto_increment,						 
	customerid int ,										 
	contacttime date  not null,						 
	place varchar(50) not null,					 
	summary varchar(50) not null,					 
	item   varchar(200),							 
	remark varchar(50)								 	
);
alter table contacthistory add constraint fk_cid foreign key(customerid) references  customer(id);

create table orderinfo(							  
	id int primary key auto_increment,								 
	customerid  int ,								 
	customername varchar(50),						 
	ordertime  date ,								 
	sendlocation varchar(50),						 
	orderstatus int 								 
);
alter table orderinfo add constraint fk_orderinfo_customerid foreign key(customerid) references  customer(id);
create table orderitem(							 
	id int primary key auto_increment,								 
	orderid int ,									 
	pname varchar(50),								 
	ordernumber int ,								 
	unit  varchar(20),								 
	price int ,										 
	countprice int 									 
);
alter table orderitem add constraint fk_orderid foreign key(orderid) references  orderinfo(id);
create table lost(							 
	id int primary key auto_increment,								 
	orderinfoid int ,		
	confirmlostdate date,
	addmeasure varchar(200),
	measure varchar(100) default '打电话给客户了解不再购买本公司产品的原因。发送新产品目录给客户。',  
	addmeasure varchar(200) not null,				 
	lostreason varchar(200)						 
);
alter table lost modify addmeasure varchar(200);
alter table lost add confirmlostdate date;

alter table lost add constraint fk_orderinfoid foreign key(orderinfoid) references  orderinfo(id);
create table service(						 
	id int primary key auto_increment,							 
	servicetype int not null,						 
	summary varchar(50) not null,					 
	customerid int not null,						 
	servicestatus int,								 
	servicerequest varchar(200),					 
	createperson varchar(50) not null,				 
	createdate date not null,						 
	allotpersonid int ,								 
	allotdate date ,								 
	servicedeal varchar(200),						 
	dealpersonid int,								 
	dealtime date ,									 
	dealresult varchar(50),						 
	satisify int 									 
	
);

alter table service add constraint fk_service_customerid foreign key(customerid) references  customer(id);
alter table service add constraint fk_allotpersonid foreign key(allotpersonid) references  userinfo(id);
alter table service add constraint fk_dealpersonid foreign key(dealpersonid) references  userinfo(id);



create table kucun(							 
	id int primary key auto_increment,								 
	product varchar(100),							 
	storehouse varchar(50),						 
	storeplace  varchar(50),						 
	numbers  int ,									 
	remark   varchar(50)							 
);

create table productinfo(					 
	id int primary key auto_increment,							 
	pname varchar(50),								 
	pversion varchar(50),							 
	batch  varchar(50),							 
	unit  varchar(20),		
	price int,
	remark varchar(50)								 
);

alter table productinfo add price int;


create table datadirectorys(					 
	id int primary key auto_increment,								 
	dtype varchar(20),		
	dname varchar(20),
	tiaomu varchar(20),							 
	dvalue varchar(20),							 
	isedit varchar(20)  									 
);

alter table datadirectorys add dname varchar(20);



