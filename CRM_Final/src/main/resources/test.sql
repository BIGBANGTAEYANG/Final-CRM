select uname,userage,tiaomu from userinfo
inner join datadirectorys on
userinfo.role=datadirectorys.id
where uname='a';
select * from service where servicestatus=40;
select * from chance;
delete  from customer where id=6;
update chance set chancestatus=6 where id= 4;
delete from chance where id=2;

select * from contacter

delete from contacter where id in (2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,20,21,22,23,24,25,26,27,28,29,30) 

select * from datadirectorys where dtype='district';

delete  from lost;
select * from lost;
select * from orderinfo;
select id from orderinfo group by customerid having (datediff(now(),max(ordertime))>50)

update lost set measure=' ' ,addmeasure=' '

select * from service where  servicestatus=40  and createdate between '2016-8-30' and '2016-8-30'