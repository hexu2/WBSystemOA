drop database if exists njwb_oa;
create database if not exists njwb_oa default charset utf8;
use njwb_oa;

-------------------------------------部门表------------------------------------
drop table t_dept;
create table t_dept(
t_deptno varchar(6) primary key not null,
t_deptname varchar(20) not null,
t_deptloc varchar(30) not null,
t_deptmanager varchar(20),
t_createtime date
)engine = Innodb;
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

---------------------------------------员工表-----------------------------------------------------
drop table t_employee;
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
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0001','李雷1','A0001','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0002','李雷2','A0002','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0003','李雷3','A0003','0','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0004','何旭','A0004','0','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0005','李雷5','A0005','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0006','李雷6','A0006','0','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0007','李雷7','A0001','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0008','李雷8','A0001','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0009','李雷9','A0001','1','博士','221292883@163.com','15755843966','2016-10-01',now());
insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time)
values('E0010','李雷10','A0001','0','博士','221292883@163.com','15755843966','2016-10-01',now());


----------------------------------------性别---------------------------------------------
drop table t_empSex;
create table t_empSex(
t_id varchar(1) primary key,
t_sex varchar(10)
)engine=Innodb;
insert into t_empSex(t_id,t_sex)
values(0,'女');
insert into t_empSex(t_id,t_sex)
values(1,'男');

----------------------------------------请假表-----------------------------------
drop table t_holiday;
create table t_holiday(
t_id int auto_increment primary key,
t_holiday_no varchar(10)  not null,
t_holiday_user varchar(20) not null,
t_holiday_type varchar(5) not null,
t_holiday_bz varchar(30) not null,
t_start_time varchar(30) not null,
t_end_time varchar(30) not null,
t_holiday_status varchar(20) not null,
t_create_time Date not null
)engine=Innodb;

-----------------------------------配置表-------------------------------------------------
drop table t_properties;
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

------------------------------------用户表---------------------------------
drop table t_user;
create table t_user(
	t_id int auto_increment primary key,
	t_username varchar(20) not null,
	t_pwd varchar(20) not null,
	t_emp_no  varchar(5)  not null,
	t_user_status int,
	t_role int,
	t_createtime date
)engine=Innodb;
insert into t_user(t_userName,t_pwd,t_emp_no,t_user_status,t_role,t_createtime)
values('root','root','E0001',1,2,now());
insert into t_user(t_userName,t_pwd,t_emp_no,t_user_status,t_role,t_createtime)
values('minister','minister','E0002',1,1,now());
insert into t_user(t_userName,t_pwd,t_emp_no,t_user_status,t_role,t_createtime)
values('minister2','minister2','E0003',1,1,now());
insert into t_user(t_userName,t_pwd,t_emp_no,t_user_status,t_role,t_createtime)
values('hexu','hexu','E0004',1,1,now());
insert into t_user(t_userName,t_pwd,t_emp_no,t_user_status,t_role,t_createtime)
values('personnel','personnel','E0005',1,3,now());
insert into t_user(t_userName,t_pwd,t_emp_no,t_user_status,t_role,t_createtime)
values('money','money','E0005',1,4,now());
insert into t_user(t_userName,t_pwd,t_emp_no,t_user_status,t_role,t_createtime)
values('888888','888888','E0006',2,1,now());
-----------------------------------角色表----------------------------------
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
values('财务专员',now());
insert into t_role(t_role_name,t_create_time)
values('安保人员',now());
insert into t_role(t_role_name,t_create_time)
values('后勤人员',now());

--------------------------------------提交状态表----------------------------------------
drop table t_status;
create table t_Status(
t_id int auto_increment primary key,
t_value varchar(20) not null
)engine=Innodb;
insert into t_status(t_value)
values('草稿');
insert into t_status(t_value)
values('提交');

----------------------------------------菜单表-------------------------------------------
drop table t_menu;
create table t_menu(
t_id int auto_increment primary key,
t_menu_name varchar(50) not null,
t_href_url varchar(200),
t_parent_id int,
t_create_time date
)engine=Innodb;
insert into t_menu(t_menu_name,t_href_url,t_create_time)
values('人事管理','',now());
insert into t_menu(t_menu_name,t_href_url,t_create_time)
values('教务管理','',now());
insert into t_menu(t_menu_name,t_href_url,t_create_time)
values('财务管理','',now());
insert into t_menu(t_menu_name,t_href_url,t_create_time)
values('系统管理','',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('部门管理','queryAllDepts.do','1',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('员工管理','queryAllEmps.do','1',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('请假管理','queryAllHolidays.do','1',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('班级管理','','2',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('学生管理','','2',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('报销管理','queryBaoXiaos.do','3',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('账户管理','queryUsers.do','4',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('密码重置','njwb/resetpwd/resetPwd.jsp','4',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('角色管理','queryRoles.do','4',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('权限管理','queryPermissions.do','4',now());
insert into t_menu(t_menu_name,t_href_url,t_parent_id,t_create_time)
values('系统退出','','4',now());

--------------------------------------报销表----------------------------------------
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

------------------------------------权限表-------------------------------------------
drop table t_permissions;
create table t_permissions(
t_id int auto_increment primary key,
t_role_id int not null,
t_menu_id int not null,
t_create_time date
)engine=Innodb;
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
values('2','3',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('2','10',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('2','4',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('2','12',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('2','15',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('3','1',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('3','4',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('3','7',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('3','12',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('3','15',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('4','3',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('4','4',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('4','10',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('4','12',now());
insert into t_permissions(t_role_id,t_menu_id,t_create_time)
values('4','15',now());
--------------------------------工作流表---------------------------
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

------------------------------工作流节点配置表-------------------------
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
values(2,1,102,'部长审核','pass:103;refuse:105','2,3',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(3,1,103,'经理审核','pass:104;refuse:105','4',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(4,1,104,'人事归档','pass:105','5',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(5,1,105,'结束','pass:end','',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(6,2,201,'开始','pass:202','',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(7,2,202,'部长审核','pass:203;refuse:205','2,3',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(8,2,203,'经理审核','pass:204;refuse:205','4',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(9,2,204,'财务归档','pass:205','6',now());
insert into t_work_node_config(id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time)
values(10,2,205,'结束','pass:end','',now());

------------------------------工作流节点配置表------------------------------
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

