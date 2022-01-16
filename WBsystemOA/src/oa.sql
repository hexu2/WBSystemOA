#############################数据库njwb_oa###############################

1,创建统一数据库njwb_oa
//显示所有库
show databases;
//删除库
drop database if exists njwb_oa;

//创建库
create database if not exists njwb_oa default charset utf8;
use njwb_oa;

#############################部门表####################################

2,创建部门表t_dept

//删除部门表
drop table t_dept;

//清空表中数据
delete from t_dept;

//建表语句

create table t_dept(

t_deptno varchar(6) primary key not null,
   
t_deptname varchar(20) not null,
   
t_deptloc varchar(30) not null,
   
t_deptmanager varchar(20),
   
t_createtime date
)engine = Innodb;

//插入数据

insert into t_dept (t_deptno,t_deptname,t_deptloc,t_deptmanager,t_createtime)
values('A0001','总经办','101室','内斯塔',now());

insert into t_dept (t_deptno,t_deptname,t_deptloc,t_deptmanager,t_createtime)
values('A0002','渠道部','102室','梅西',now());

insert into t_dept (t_deptno,t_deptname,t_deptloc,t_deptmanager,t_createtime)
values('A0003','市场营销','103室','内马尔',now());

insert into t_dept (t_deptno,t_deptname,t_deptloc,t_deptmanager,t_createtime)
values('A0004','教质部','104室','恩里克',now());
insert into t_dept (t_deptno,t_deptname,t_deptloc,t_deptmanager,t_createtime)
values('A0005','教学部','105室','西蒙尼',now());
insert into t_dept (t_deptno,t_deptname,t_deptloc,t_deptmanager,t_createtime)
values('A0006','就业部','106室','阿尔维斯',now());

insert into t_dept (t_deptno,t_deptname,t_deptloc,t_deptmanager,t_createtime)
values(？,？,？,？,now());
insert into t_dept (t_deptno,t_deptname,t_deptloc,t_deptmanager,t_createtime)
values('A0008','就业部','106室','阿尔维斯',now());
//查询部门表内容
select t_deptno,t_deptname,t_deptloc,t_deptmanager,t_createtime from t_dept;
select t_deptno,t_deptname,t_deptloc,t_deptmanager,t_createtime from t_dept where t_deptno = ?

//修改部门信息
update t_dept set t_deptname='w',t_deptloc='e',t_deptmanager='r',t_createtime=now() where t_deptno='A0001';
update t_dept set t_deptname=?,t_deptloc=?,t_deptmanager=?,t_createtime=now() where t_deptno=?

//根据t_deptno删除数据
delete from t_dept where t_deptno = 'A0001';
delete from t_dept where t_deptno = ?

###############################员工表###################################

create table t_employee(
t_id int auto_increment primary key,
t_emp_no varchar(5)  not null,
t_emp_name varchar(20) not null,
t_emp_dept varchar(5) not null,
t_sex varchar(1) not null,
t_education varchar(30),
t_email varchar(30),
t_phone varchar(20) not null,
t_entry_time Date not null,
t_create_time Date not null
)engine=Innodb;

//插入员工

insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0001','李雷1','A0001','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0002','李雷2','A0002','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0003','李雷3','A0003','2','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0004','李雷4','A0004','2','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0005','李雷5','A0005','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0006','李雷6','A0006','2','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0007','李雷7','A0001','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0008','李雷8','A0001','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0009','李雷9','A0001','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0010','李雷10','A0001','0','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0007','李雷11','A0001','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0008','李雷12','A0001','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0009','李雷13','A0001','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0010','李雷14','A0001','0','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0010','李雷15','A0001','0','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0007','李雷16','A0001','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0008','李雷17','A0001','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0009','李雷18','A0001','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0010','李雷19','A0001','0','博士','221292883@163.com','15755843966','2016-10-01',now());


insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values(?,?,?,?,?,?,?,?,now())



select e1.t_id,e1.t_emp_no,e1.t_emp_name,e2.t_deptname,e1.t_sex,e1.t_education,e1.t_email,e1.t_phone,e1.t_entry_time,e1.t_create_time from t_employee e1
inner join t_dept e2
on e1.t_emp_dept = e2.t_deptno;

select e1.t_id,e1.t_emp_no,e1.t_emp_name,e2.t_deptname,e1.t_sex,e1.t_education,e1.t_email,e1.t_phone,e1.t_entry_time,e1.t_create_time from t_employee e1
inner join t_dept e2
on e1.t_emp_dept = e2.t_deptno;


//修改员工信息根据员工号
update t_employee
set t_emp_name='xx',t_emp_dept='A0002',t_sex='0',t_education='研究生',t_email='15755843966@163.com',t_phone='11111111111',t_entry_time='2016-10-01',t_create_time=now() 
where t_emp_no = 'E0001';

update t_employee
set t_emp_name=?,t_emp_dept=?,t_sex=?,t_education=?,t_email=?,t_phone=?,t_entry_time=?,t_create_time=now() 
where t_emp_no = ?

//查询员工所有信息
select e1.t_id,e1.t_emp_no,e1.t_emp_name,e2.t_deptname,e1.t_sex,e1.t_education,e1.t_email,t_phone,e1.t_entry_time,e1.t_create_time 
from t_employee e1,t_dept e2
where e1.t_emp_dept = e2.t_deptno;

select e1.t_id,e1.t_emp_no,e1.t_emp_name,e2.t_deptName as t_emp_dept, e1.t_sex , e1.t_education,e1.t_email,e1.t_phone,e1.t_entry_time,e1.t_create_time 
from t_employee e1 ,t_dept e2
where e1.t_emp_dept = e2.t_deptno

select e1.t_id,e1.t_emp_no,e1.t_emp_name,e2.t_deptName as t_emp_dept, e3.t_sex as t_sex , e1.t_education,e1.t_email,e1.t_phone,e1.t_entry_time,e1.t_create_time 
from t_employee e1 ,t_dept e2 ,t_empSex e3
where e1.t_emp_dept = e2.t_deptno and e1.t_sex = e3.t_id;

来自终端的可以用
select e1.t_id,e1.t_emp_no,e1.t_emp_name,e2.t_deptName as t_emp_dept, e3.t_sex as t_sex , e1.t_education,e1.t_email,e1.t_phone,e1.t_entry_time,e1.t_create_time 
from t_employee e1 ,t_dept e2 ,t_empSex e3
where e1.t_emp_dept = e2.t_deptno and e1.t_sex = e3.t_id;




select e2.t_id, e2.t_emp_no,e2.t_emp_name,e1.t_deptname as t_emp_dept ,e3.t_sex as t_sex,e2.t_education,e2.t_email,e2.t_phone,e2.t_entry_time,e2.t_create_time 
from t_dept e1,t_employee e2,t_empSex e3 
where e1.t_deptno = e2.t_emp_dept and e2.t_sex = e3.t_id;

select e2.t_id,e2.t_emp_no,e2.t_emp_name,e1.t_deptname,e3.t_sex,e2.t_education,e2.t_email,e2.t_phone,e2.t_entry_time,e2.t_create_time from t_dept e1,t_employee e2,t_empSex e3
where e1.t_deptno = e2.t_emp_dept and e2.t_sex = e3.t_id;

//根据用户名模糊查询员工
select t_id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time from t_employee
where t_emp_name like '%2%' limit 0,10;


//模糊查询员工

select e2.t_id,e2.t_emp_no,e2.t_emp_name,e1.t_deptname,e3.t_sex,e2.t_education,e2.t_email,e2.t_phone,e2.t_entry_time,e2.t_create_time from t_dept e1,t_employee e2,t_empSex e3
where e1.t_deptno = e2.t_emp_dept and e2.t_sex = e3.t_id  and e1.t_deptname like '%总经办%' and e2.t_emp_name like '%5%'

//分页查询员工信息
select e2.t_id,e2.t_emp_no,e2.t_emp_name,e1.t_deptname,e3.t_sex,e2.t_education,e2.t_email,e2.t_phone,e2.t_entry_time,e2.t_create_time from t_dept e1,t_employee e2,t_empSex e3
where e1.t_deptno = e2.t_emp_dept and e2.t_sex = e3.t_id limit 0,5;

//模糊分页查询
select e2.t_id,e2.t_emp_no,e2.t_emp_name,e1.t_deptname,e3.t_sex,e2.t_education,e2.t_email,e2.t_phone,e2.t_entry_time,e2.t_create_time from t_dept e1,t_employee e2,t_empSex e3
where e1.t_deptno = e2.t_emp_dept and e2.t_sex = e3.t_id  and e1.t_deptname like '%总经办%' and e2.t_emp_name like '%李%'  limit 2,3;

//模糊分页查询后员工总数
select count(1) as cnt 
from  t_dept e1,t_employee e2,t_empSex e3  
where e1.t_deptno = e2.t_emp_dept and e2.t_sex = e3.t_id  and e1.t_deptname like '%总经办%' and e2.t_emp_name like '%5%' ;


//查询员工总数
select count(1) as cnt from t_employee;

//根据员工编号查询员工
select e2.t_id,e2.t_emp_no,e2.t_emp_name,e1.t_deptname,e3.t_sex,e2.t_education,e2.t_email,e2.t_phone,e2.t_entry_time,e2.t_create_time from t_dept e1,t_employee e2,t_empSex e3
where e1.t_deptno = e2.t_emp_dept and e2.t_sex = e3.t_id and e2.t_emp_no = 'E0003';

select e2.t_id,e2.t_emp_no,e2.t_emp_name,e1.t_deptname,e3.t_sex,e2.t_education,e2.t_email,e2.t_phone,e2.t_entry_time,e2.t_create_time from t_dept e1,t_employee e2,t_empSex e3
where e1.t_deptno = e2.t_emp_dept and e2.t_sex = e3.t_id and e2.t_emp_no = ?

//删除员工
delete from t_employee where t_emp_no = 'E0002'



##################################性别表##################################
create table t_empSex(
t_id varchar(1) primary key,
t_sex varchar(10)
)engine=Innodb;

insert into t_empSex(t_id,t_sex)
values(0,'女');
insert into t_empSex(t_id,t_sex)
values(1,'男');


#############################################请假表##################################
drop table t_holiday;

create table t_holiday(
t_id int auto_increment primary key,
t_holiday_no varchar(10)  not null,
t_holiday_user varchar(20) not null,
t_holiday_type varchar(5) not null,
t_holiday_bz varchar(30) not null,
t_start_time DateTime not null,
t_end_time DateTime not null,
t_holiday_status varchar(20) not null,
t_create_time Date not null
)engine=Innodb;

//插入请假记录
insert into t_holiday(t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time)
values('QJ1001','root','1','结婚','2016-09-26 09:00:00 ','2016-09-30 18:00:00','2',now());
insert into t_holiday(t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time)
values('QJ1002','root','2','结婚','2016-09-26 09:00:00','2016-09-30 18:00:00','2',now());
insert into t_holiday(t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time)
values('QJ1003','root','2','结婚','2016-09-26 09:00:00','2016-09-30 18:00:00','1',now());
insert into t_holiday(t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time)
values('QJ1004','root','3','结婚','2016-09-26 09:00:00','2016-09-30 18:00:00','1',now());
insert into t_holiday(t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time)
values('QJ1005','root','4','结婚','2016-09-26 09:00:00','2016-09-30 18:00:00','1',now());
insert into t_holiday(t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time)
values('QJ1006','root','2','结婚','2016-09-26 09:00:00','2016-09-30 18:00:00','1',now());
insert into t_holiday(t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time)
values('QJ1007','root','5','结婚','2016-09-26 09:00:00','2016-09-30 18:00:00','1',now());
insert into t_holiday(t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time)
values('QJ1008','root','2','结婚','2016-09-26 09:00:00','2016-09-30 18:00:00','1',now());
insert into t_holiday(t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time)
values('QJ1009','root','2','结婚','2016-09-26 09:00:00','2016-09-30 18:00:00','1',now());
insert into t_holiday(t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time)
values('QJ1010','hexu','4','结婚','2016-09-26 09:00:00','2016-09-30 18:00:00','1',now());
insert into t_holiday(t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time)
values('QJ1011','hexu','2','结婚','2016-09-26 09:00:00','2016-09-30 18:00:00','1',now());


insert into t_holiday(t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time)
values(?,?,?,?,?,?,?,now());

//删除请假记录
delete from t_holiday where t_holiday_no = 'QJ1002';



//查询请假记录根据请假号(显示请假类型)------------》关联配置表
select e1.t_id,e1.t_holiday_no,e1.t_holiday_user,e2.t_holiday_name as t_holiday_type ,e1.t_holiday_bz,e1.t_start_time,e1.t_end_time,e1.t_holiday_status,e1.t_create_time from t_holiday e1
inner join t_holidayType e2
on e1.t_holiday_type = e2.t_id
where e1.t_holiday_no = 'QJ1007';
//查询所有请假记录(显示请假类型)------------》关联配置表
select e1.t_id,e1.t_holiday_no,e1.t_holiday_user,e2.t_pageValue as t_holiday_type ,e1.t_holiday_bz,e1.t_start_time,e1.t_end_time,e1.t_holiday_status,e1.t_create_time from t_holiday e1
inner join t_properties e2
on e1.t_holiday_type = e2.t_keyId
where e2.t_type = '1_holiday_type'

//查出id最大的请假记录
select  t_id,t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time from t_holiday
where t_id = (select max(t_id) from t_holiday);


//根据请假编号查询员工信息------------》关联配置表
select e1.t_id,e1.t_holiday_no,e1.t_holiday_user,e2.t_pageValue as t_holiday_type ,e1.t_holiday_bz,e1.t_start_time,e1.t_end_time,e1.t_holiday_status,e1.t_create_time from t_holiday e1
inner join t_properties e2
on e1.t_holiday_type = e2.t_keyId
where e2.t_type = '1_holiday_type' and e1.t_holiday_no = 'QJ1007';

//模糊查询根据t_holiday_user，t_holiday_type，t_holiday_status
select e1.t_id,e1.t_holiday_no,e1.t_holiday_user,e2.t_pageValue as t_holiday_type ,e1.t_holiday_bz,e1.t_start_time,e1.t_end_time,e1.t_holiday_status,e1.t_create_time from t_holiday e1
inner join t_properties e2
on e1.t_holiday_type = e2.t_keyId
where e2.t_type = '1_holiday_type' and e1.t_holiday_user like '%%' and e2.t_pageValue like '%%' and e1.t_holiday_status like '%%'
limit 0,5;

//分页查询请假总数
select count(1) as cnt 
from t_holiday e1
inner join t_properties e2
on e1.t_holiday_type = e2.t_keyId
where e2.t_type = '1_holiday_type' and e1.t_holiday_user = 'root'
//模糊分页查询后请假总数
select count(1) as cnt 
from t_holiday e1
inner join t_properties e2
on e1.t_holiday_type = e2.t_keyId
where e2.t_type = '1_holiday_type' and e1.t_holiday_user like '%2%' and e1.t_holiday_type like '%%' and e1.t_holiday_status like '%%'


//分页查询 ------------》关联配置表
select e1.t_id,e1.t_holiday_no,e1.t_holiday_user,e2.t_pageValue as t_holiday_type ,e1.t_holiday_bz,e1.t_start_time,e1.t_end_time,e1.t_holiday_status,e1.t_create_time from t_holiday e1
inner join t_properties e2
on e1.t_holiday_type = e2.t_keyId
where e2.t_type = '1_holiday_type' and e1.t_holiday_user = 'root'
limit 1,5;

select e1.t_id,e1.t_holiday_no,e1.t_holiday_user,e2.t_pageValue as t_holiday_type ,e1.t_holiday_bz,e1.t_start_time,e1.t_end_time,e1.t_holiday_status,e1.t_create_time from t_holiday e1 
inner join t_properties e2
on e1.t_holiday_type = e2.t_keyId  and e1.t_holiday_user = ?
where e2.t_type = '1_holiday_type' 
limit ?,?



########################################提交状态表##################################
drop table t_status;
create table t_Status(
t_id int auto_increment primary key,
t_value varchar(20) not null
)engine=Innodb;
insert into t_status(t_value)
values('草稿');
insert into t_status(t_value)
values('提交');
 
select t_id ,t_value from t_status;

############################################配置表##################################

create table t_properties(
t_type varchar(20) not null,
t_keyId varchar(20) not null,
t_pageValue varchar(20) not null
)engine=Innodb;

insert into t_properties(t_type,t_keyId,t_pageValue)
values('1_holiday_type','1','事假');
insert into t_properties(t_type,t_keyId,t_pageValue)
values('1_holiday_type','2','婚假');
insert into t_properties(t_type,t_keyId,t_pageValue)
values('1_holiday_type','3','年假');
insert into t_properties(t_type,t_keyId,t_pageValue)
values('1_holiday_type','4','调休');
insert into t_properties(t_type,t_keyId,t_pageValue)
values('1_holiday_type','5','病假');
insert into t_properties(t_type,t_keyId,t_pageValue)
values('1_holiday_type','6','丧假');
insert into t_properties(t_type,t_keyId,t_pageValue)
values('2_BX_type','1','差旅费');
insert into t_properties(t_type,t_keyId,t_pageValue)
values('2_BX_type','2','招待费');
insert into t_properties(t_type,t_keyId,t_pageValue)
values('2_BX_type','3','办公费');
insert into t_properties(t_type,t_keyId,t_pageValue)
values('3_user_status','1','正常');
insert into t_properties(t_type,t_keyId,t_pageValue)
values('3_user_status','2','注销');

//查询所有配置
select t_type,t_keyId,t_pageValue from t_properties;


##########################################用户表######################################


create table t_user(
	t_id int auto_increment primary key,
	t_username varchar(20) not null,
	t_pwd varchar(20) not null,
	t_emp_no  varchar(5)  not null,
	t_user_status int,
	t_role int,
	t_createtime date
)engine=Innodb;
//插入用户
insert into t_user(t_userName,t_pwd,t_emp_no,t_user_status,t_role,t_createtime)
values('root','root','E0001',1,1,now());
insert into t_user(t_userName,t_pwd,t_emp_no,t_user_status,t_role,t_createtime)
values('hexu','hexu','E0002',1,2,now());
insert into t_user(t_userName,t_pwd,t_emp_no,t_user_status,t_role,t_createtime)
values('xxxx','xxxx','E0003',2,2,now());
insert into t_user(t_userName,t_pwd,t_emp_no,t_user_status,t_role,t_createtime)
values('123456','123456','E0004',1,3,now());

//根据用户名和密码查询用户

select e1.t_id,e1.t_userName,e1.t_pwd,e1.t_emp_no,e3.t_emp_name,e1.t_user_status,e1.t_role,e1.t_createtime 
from t_user e1 ,t_employee e3 
where  e1.t_emp_no= e3.t_emp_no
and e1.t_userName = ? and e1.t_pwd =?


//查询所有用户
select e1.t_id,e1.t_userName,e1.t_pwd,e1.t_emp_no,e1.t_user_status,e2.t_role_name as t_role,e1.t_createtime from t_user e1 , t_role e2
where e1.t_role = e2.t_id and e1.t_userName = "root";
//根据账号查询用户
select t_id,t_userName,t_pwd,t_emp_no,t_user_status,t_role,t_createtime from t_user where t_userName ='hexu';

//分页查询所有用户
select e1.t_id,e1.t_userName,e1.t_pwd,e1.t_emp_no,e1.t_user_status,e2.t_role_name as t_role,e1.t_createtime from t_user e1 , t_role e2
where e1.t_role = e2.t_id
limit 0,2;
//根据用户名模糊查询用户
select e1.t_id,e1.t_userName,e1.t_pwd,e1.t_emp_no,e3.t_emp_name,e1.t_user_status,e2.t_role_name as t_role,e1.t_createtime 
from t_user e1 , t_role e2,t_employee e3 
where e1.t_role = e2.t_id and e1.t_emp_no= e3.t_emp_no and t_username like '%x%'
limit 0,10;

//分页模糊查询
select e1.t_id,e1.t_userName,e1.t_pwd,e1.t_emp_no,e3.t_emp_name,e1.t_user_status,e2.t_role_name as t_role,e1.t_createtime 
from t_user e1 , t_role e2,t_employee e3 
where e1.t_role = e2.t_id and e1.t_emp_no= e3.t_emp_no 
and e1.t_userName like ? and e1.t_user_status like ? and e2.t_role_name like 
limit ?,?
//模糊查询后的总用户数量
select count(1) as cnt
from t_user e1 , t_role e2,t_employee e3 
where e1.t_role = e2.t_id and e1.t_emp_no= e3.t_emp_no 
and e1.t_userName like '%%' and e1.t_user_status like '%%' and e2.t_role_name like '%普通用户%'

//根据用户名查询用户
select e1.t_id,e1.t_userName,e1.t_pwd,e1.t_emp_no,e3.t_emp_name,e1.t_user_status,e2.t_role_name as t_role,e1.t_createtime 
from t_user e1 , t_role e2,t_employee e3 
where e1.t_role = e2.t_id and e1.t_emp_no= e3.t_emp_no  and e1.t_userName='root';

//根据id查找用户
select e1.t_id,e1.t_userName,e1.t_pwd,e1.t_emp_no,e3.t_emp_name,e1.t_user_status,e2.t_role_name as t_role,e1.t_createtime 
from t_user e1 , t_role e2,t_employee e3 
where e1.t_role = e2.t_id and e1.t_emp_no= e3.t_emp_no and e1.t_id = '2'

//根据用户名和密码查询用户
select t_id,t_username ,t_pwd,t_role,t_createtime from t_user 
where t_username='root' and t_pwd='root';

//耕具id修改用户
update t_user set t_userName='xxxx',t_pwd='xxxx',t_emp_no='xxx',t_emp_name='xxx',t_user_status=1,t_role='2',t_createtime = now() where t_id='2';

//根据id删除用户
delete from t_user where t_id = '2';

//查询用户总数
select count(1)as t_cnt from t_user;



//查询用户，显示汉字角色替代原来的编号
select e1.t_id,e1.t_userName,e1.t_pwd,e1.t_emp_no,e3.t_emp_name,t_user_status,e2.t_role_name as t_role,e1.t_createtime from t_user e1 , t_role e2,t_employee e3
where e1.t_role = e2.t_id and e1.t_emp_no= e3.t_emp_no;




#################################角色表######################################
drop table t_role;
create table t_role(
t_id int auto_increment primary key,
t_role_name varchar(20),
t_create_time date
)engine=Innodb;
insert into t_role(t_role_name,t_create_time)
values('管理员',now());
insert into t_role(t_role_name,t_create_time)
values('普通用户',now());
insert into t_role(t_role_name,t_create_time)
values('人事专员',now());
insert into t_role(t_role_name,t_create_time)
values('安保人员',now());
insert into t_role(t_role_name,t_create_time)
values('后勤人员',now());
//添加新角色
insert into t_role(t_role_name,t_create_time)
values(?,now());

//查询所有角色
select t_id,t_role_name,t_create_time from t_role;

//根据id角色
select t_id,t_role_name,t_create_time from t_role where t_id = 3;

//分页查询角色
select t_id,t_role_name,t_create_time from t_role limit 0,5;
//查询所有角色数量
select count(1) as t_cnt from t_role;

//根据id删除角色
delete from t_role where t_id = 5;

//根据id修改角色
update t_role set t_role_name='qq',t_create_time=now()  where t_id = 11;



###########################################菜单表######################################
drop table t_menu;
create table t_menu(
t_id int auto_increment primary key,
t_menu_name varchar(50) not null,
t_href_url varchar(200),
t_parent_id int,
t_create_time date
)engine=Innodb;
//查询所有菜单
select t_id,t_menu_name,t_href_url,t_parent_id,t_create_time from t_menu;

//查询所有一级菜单表内容
select t_id,t_menu_name,t_href_url,t_parent_id,t_create_time from t_menu 
where t_parent_id is null;
//根据角色查询一级菜单
select t_id,t_menu_name,t_href_url,t_parent_id,t_create_time from t_menu 
where t_parent_id is null and t_id in (select t_menu_id from t_permissions where t_role_id=2)

//根据父亲id和角色id查询子菜单
select t_id,t_menu_name, t_href_url, t_parent_id, t_create_time 
from t_menu where t_parent_id = 1 
and t_id in (select t_menu_id from t_permissions where t_role_id=1)

insert into t_menu(t_menu_name,t_href_url,t_create_time)
values('人事管理','',now());
insert into t_menu(t_menu_name,t_href_url,t_create_time)
values('教务管理','',now());
insert into t_menu(t_menu_name,t_href_url,t_create_time)
values('财务管理','',now());
insert into t_menu(t_menu_name,t_href_url,t_create_time)
values('系统管理','',now());

insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('部门管理','queryDeptsServlet.do','1',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('员工管理','queryEmpsServlet.do','1',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('请假管理','queryHolidaysServlet.do','1',now());

insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('班级管理','','2',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('学生管理','','2',now());

insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('报销管理','queryBaoXiaosServlet.do','3',now());

insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('账户维护','queryUsersServlet.do','4',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('密码重置','','4',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('角色管理','','4',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('权限管理','','4',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('系统退出','','4',now());

################################权限表######################################
drop table t_permissions;
create table t_permissions(
t_id int auto_increment primary key,
t_role_id int not null,
t_menu_id int not null,
t_create_time date
)engine=Innodb;

//查询权限表全部数据，角色id，角色名称，菜单id，菜单名称
select e1.t_id,e1.t_role_id,e2.t_role_name,e1.t_menu_id,e3.t_menu_name,e1.t_create_time
from t_permissions e1, t_role e2, t_menu e3
where e1.t_role_id=e2.t_id and e1.t_menu_id=e3.t_id limit 0,5;
//模糊查询根据角色名 和 菜单名
select e1.t_id,e1.t_role_id,e2.t_role_name,e1.t_menu_id,e3.t_menu_name,e1.t_create_time
from t_permissions e1, t_role e2, t_menu e3
where e1.t_role_id=e2.t_id and e1.t_menu_id=e3.t_id and e2.t_role_name like '%普通用户%' and e3.t_menu_name like '%%'
limit 0,5;
//模糊分页后的总页数
select count(1) as cnt
from t_permissions e1, t_role e2, t_menu e3
where e1.t_role_id=e2.t_id and e1.t_menu_id=e3.t_id and e2.t_role_name like '%普通用户%' and e3.t_menu_name like '%%'
limit 0,5;




//
select t_menu_id from t_permissions where t_role_id=

//添加权限
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values(?,?,now());
//删除权限根据id
delete from t_permissions where t_id = 24;


insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('1','1',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('1','2',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('1','3',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('1','4',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('1','5',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('1','6',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('1','7',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('1','8',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('1','9',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('1','10',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('1','11',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('1','12',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('1','13',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('1','14',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('1','15',now());

insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('2','1',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('2','7',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('2','4',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('2','12',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('2','15',now());


#######################################报销表######################################
drop table t_bx;
create table t_bx(
t_id int auto_increment primary key,
t_bx_no varchar(10)  not null,
t_bx_user varchar(20) not null,
t_bx_type varchar(5) not null,
t_bx_money double not null,
t_bx_bz varchar(30) not null,
t_bx_status varchar(20) not null,
t_apply_time Date not null
)engine=Innodb;

insert into t_bx(t_bx_no,t_bx_user,t_bx_type,t_bx_money,t_bx_bz,t_bx_status,t_apply_time)
values('BX1001','root','1',100,'差旅费','1',now());
insert into t_bx(t_bx_no,t_bx_user,t_bx_type,t_bx_money,t_bx_bz,t_bx_status,t_apply_time)
values('BX1002','root','2',200,'招待费','2',now());
insert into t_bx(t_bx_no,t_bx_user,t_bx_type,t_bx_money,t_bx_bz,t_bx_status,t_apply_time)
values('BX1003','root','3',300,'办公费','2',now());
insert into t_bx(t_bx_no,t_bx_user,t_bx_type,t_bx_money,t_bx_bz,t_bx_status,t_apply_time)
values('BX1004','hexu','1',100,'差旅费','1',now());
insert into t_bx(t_bx_no,t_bx_user,t_bx_type,t_bx_money,t_bx_bz,t_bx_status,t_apply_time)
values('BX1005','hexu','2',200,'招待费','2',now());
insert into t_bx(t_bx_no,t_bx_user,t_bx_type,t_bx_money,t_bx_bz,t_bx_status,t_apply_time)
values('BX1006','hexu','3',300,'办公费','2',now());

//添加报销记录
insert into t_bx(t_bx_no,t_bx_user,t_bx_type,t_bx_money,t_bx_bz,t_bx_status,t_apply_time)
values('BX1007','hexu','3',300,'办公费','2',now());

//根据请假编号删除请假记录
delete from t_bx where t_bx_no = 'BX1006';
delete from t_bx where t_bx_no = ?;

//查询所有请假记录
select e1.t_id,e1.t_bx_no,e1.t_bx_user,e2.t_pageValue as t_bx_type,e1.t_bx_money,e1.t_bx_bz,e1.t_bx_status,e1.t_apply_time from t_bx e1
inner join t_properties e2
on e1.t_bx_type = e2.t_keyId
where e2.t_type = '2_BX_type';

//根据请假编号查询请假记录
select e1.t_id,e1.t_bx_no,e1.t_bx_user,e2.t_pageValue as t_bx_type,e1.t_bx_money,e1.t_bx_bz,e1.t_bx_status,e1.t_apply_time from t_bx e1
inner join t_properties e2
on e1.t_bx_type = e2.t_keyId
where e2.t_type = '2_BX_type' and e1.t_bx_no = 'BX1003';

//请假分页查询
select e1.t_id,e1.t_bx_no,e1.t_bx_user,e2.t_pageValue as t_bx_type,e1.t_bx_money,e1.t_bx_bz,e1.t_bx_status,e1.t_apply_time from t_bx e1
inner join t_properties e2
on e1.t_bx_type = e2.t_keyId
where e2.t_type = '2_BX_type' 
limit 0,3;

//根据用户名查询请假记录总数
select count(1) as t_cnt from t_bx e1
inner join t_properties e2
on e1.t_bx_type = e2.t_keyId
where e2.t_type = '2_BX_type' 
and e1.t_bx_user = 'root';

//根据请假编号修改一个请假记录
update t_bx 
set t_bx_user='reet',t_bx_type='2',t_bx_money='666',t_bx_bz='xxx',t_bx_status='1',t_apply_time= now()
where t_bx_no = 'BX1003';


//按照分页模型查询
select e1.t_id,e1.t_bx_no,e1.t_bx_user,e2.t_pageValue as t_bx_type,e1.t_bx_money,e1.t_bx_bz,e1.t_bx_status,e1.t_apply_time from t_bx e1
inner join t_properties e2
on e1.t_bx_type = e2.t_keyId
where e2.t_type = '2_BX_type' 
and e1.t_bx_user = 'root'
limit 0,2;

select e1.t_id,e1.t_bx_no,e1.t_bx_user,e2.t_pageValue as t_bx_type,e1.t_bx_money,e1.t_bx_bz,e1.t_bx_status,e1.t_apply_time from t_bx e1
inner join t_properties e2
on e1.t_bx_type = e2.t_keyId
where e2.t_type = '2_BX_type' 
and e1.t_bx_user = ?
limit ?,?

//查询id最大的那条请假记录
select t_id,t_bx_no,t_bx_user,t_bx_type,t_bx_money,t_bx_bz,t_bx_status,t_apply_time from t_bx
where t_id = (select max(t_id) from t_bx);

//按照分页模型模糊查询
select e1.t_id,e1.t_bx_no,e1.t_bx_user,e2.t_pageValue as t_bx_type,e1.t_bx_money,e1.t_bx_bz,e1.t_bx_status,e1.t_apply_time from t_bx e1
inner join t_properties e2
on e1.t_bx_type = e2.t_keyId
where e2.t_type = '2_BX_type' and e1.t_bx_user like '%root%' and e2.t_pageValue like '%招待费%' and e1.t_bx_status like '%%' 
limit 0,2;

select e1.t_id,e1.t_bx_no,e1.t_bx_user,e2.t_pageValue as t_bx_type,e1.t_bx_money,e1.t_bx_bz,e1.t_bx_status,e1.t_apply_time from t_bx e1
inner join t_properties e2
on e1.t_bx_type = e2.t_keyId
where e2.t_type = '2_BX_type' and e1.t_bx_user like ? and e2.t_pageValue like ? and e1.t_bx_status like ? 
limit ?,?;

//模糊查询分页后的请假记录总数量
select count(1) as cnt 
from t_bx e1
inner join t_properties e2
on e1.t_bx_type = e2.t_keyId
where e2.t_type = '2_BX_type' and e1.t_bx_user like ? and e2.t_pageValue like ? and e1.t_bx_status like ? 


########################################工作流表###################################

drop table t_work_flow;
create table t_work_flow(
   id int primary key not null,
   t_work_name  varchar(100) not null,
   t_table_name varchar(100) not null, 
   t_create_time  date
)engine=Innodb;

insert into t_work_flow(id,t_work_name,t_table_name,t_create_time) values(1,'请假申请流程','t_holiday',now());
insert into t_work_flow(id,t_work_name,t_table_name,t_create_time) values(2,'报销申请流程','t_bx',now());
insert into t_work_flow(id,t_work_name,t_table_name,t_create_time) values(3,'账户申请流程','t_user',now());
select id,t_work_name,t_table_name,t_create_time from t_work_flow;
 select id,t_work_name,t_table_name,t_create_time from t_work_flow where id =?;

###################################工作流节点配置表###################################
drop table t_work_node_config;
create table t_work_node_config(
   id int primary key not null,
   t_work_id  varchar(100) not null,
   t_node_id  int not null,
   t_node_name varchar(100) not null,
   t_next_node_oper varchar(100) not null,
   t_waiting_user varchar(100) not null,
   t_create_time date
)engine=Innodb;
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(1,1,101,'开始','pass:102','',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(2,1,102,'部长审核','pass:103;refuse:101','3,5',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(3,1,103,'经理审核','pass:104;refuse:101','2',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(4,1,104,'人事归档','pass:105','4',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(5,1,105,'结束','pass:end','',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(6,2,201,'开始','pass:102','',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(7,2,202,'部长审核','pass:103;refuse:101','3,5',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(8,2,203,'经理审核','pass:104;refuse:101','2',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(9,2,204,'财务归档','pass:105','4',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(10,2,205,'结束','pass:end','',now());
//根据id查询工作节点
select * from t_work_node_config where t_node_id=?

select id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time
from t_work_node_config where t_node_id=?
###################################工作流记录表###################################
drop table t_work_node_action;
create table t_work_node_action(
   id int primary key auto_increment not null,
   t_node_id  int not null,
   t_table_id  varchar(50) not null,
   t_open_time datetime not null,
   t_close_time datetime,
   t_status int not null,
   t_waiting_user varchar(100),
   t_deal_user varchar(100),
   t_deal_advices varchar(200),
   t_create_time datetime
)engine=Innodb;

//查询所有工作流记录
select * from t_work_node_action;
select id,t_node_id,t_table_id,t_open_time,t_close_time,t_status,t_waiting_user,t_deal_user,t_deal_advices,t_create_time
from t_work_node_action;

//添加新的工作流记录
insert into t_work_node_action (t_node_id,t_table_id,t_open_time,t_status,t_waiting_user,t_create_time,t_close_time,t_deal_user,t_deal_advices) 
values(101,'QJ0002',now(),2,'xxxx',now(),now(),'qqqqq','no');
insert into t_work_node_action (t_node_id,t_table_id,t_open_time,t_status,t_waiting_user,t_create_time,t_close_time,t_deal_user,t_deal_advices) 
values(102,'QJ0002',now(),2,'xxxx',now(),now(),'qqqqq','no');
insert into t_work_node_action (t_node_id,t_table_id,t_open_time,t_status,t_waiting_user,t_create_time,t_close_time,t_deal_user,t_deal_advices) 
values(103,'QJ0002',now(),2,'xxxx',now(),now(),'qqqqq','no');
insert into t_work_node_action (t_node_id,t_table_id,t_open_time,t_status,t_waiting_user,t_create_time,t_close_time,t_deal_user,t_deal_advices) 
values(104,'QJ0002',now(),2,'xxxx',now(),now(),'qqqqq','no');
insert into t_work_node_action (t_node_id,t_table_id,t_open_time,t_status,t_waiting_user,t_create_time,t_close_time,t_deal_user,t_deal_advices) 
values(105,'QJ0002',now(),2,'xxxx',now(),now(),'qqqqq','no');

insert into t_work_node_action (t_node_id,t_table_id,t_open_time,t_status,t_waiting_user,t_create_time,t_close_time,t_deal_user,t_deal_advices) 
values(?,?,?,?,?,?,?,?,?)


//查询当前的工作节点
select id,t_node_id,t_table_id,t_open_time,t_close_time,t_status,t_waiting_user,t_deal_user,t_deal_advices,t_create_time
from t_work_node_action 
where t_table_id=? and t_node_id 
in( select t_node_id from t_work_node_config where t_work_id=?) 
order by id desc limit 0,1


//关闭当前节点：修改当前节点数据：close_time,deal_user,deal_advice
update t_work_node_action 
set t_close_time=now(),t_deal_user='hhhhhh',t_deal_advices='haha',t_status=2 where id=1;

update t_work_node_action 
set t_close_time=?,t_deal_user=?,t_deal_advices=?,t_status=? where id=?

// 查询当前的工作流节点

select id,t_node_id,t_table_id,t_open_time,t_close_time,t_status,t_waiting_user,t_deal_user,t_deal_advices,t_create_time
from t_work_node_action  
where t_table_id='QJ0001' 
and t_node_id in
(select t_node_id from t_work_node_config where t_work_id=1) 
order by id desc limit 0,1

//查询工作流历史记录根据t_tabele_id 例如：QJ1003  t_work_id 例如：1，2，3。。。。
//tableId,节点ID，节点状态，打开时间，结束时间，待处理人，已处理人，处理意见
select id,t_table_id,t_node_id,t_status,t_open_time,t_close_time,t_waiting_user,t_deal_user,t_deal_advices,t_create_time
from t_work_node_action 
where t_table_id='QJ0001' and t_node_id in
(select t_node_id from t_work_node_config where t_work_id=1)

select id,t_node_id,t_table_id,t_open_time,t_close_time,t_status,t_waiting_user,t_deal_user,t_deal_advices,t_create_time 
from t_work_node_action 
where t_table_id=101 and t_node_id in 
(select t_node_id	from t_work_node_config where t_work_id=1)


select id,t_node_id,t_table_id,t_open_time,t_close_time,t_status,t_waiting_user,t_deal_user,t_deal_advices,t_create_time
from t_work_node_action 
where t_table_id=? and t_node_id in
(select t_node_id	from t_work_node_config where t_work_id=?)

select * from t_work_node_action 
where t_table_id= 'QJ0001' and t_node_id in
(select t_node_id from t_work_node_config where t_work_id='1');

//查询员工名根据IDs
select e.t_emp_name from t_user u ,t_employee e 
where u.t_emp_no=e.t_emp_no and u.t_id in ("+ ids + ")
