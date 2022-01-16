# WBSystemOA


方便公司对学生、班级、员工的管理，并提供请假和报销流程。


工作流类型
1、请假审批流程（请假编码--》ID）
2、报销审批流程（报销编码--》ID）
3、账户添加审批流程 

1)t_work_type
id,work_name

工作流节点：
审核操作：关闭当前节点，打开下一个节点。

请假流程：用户提交（开始101）--》部长审批（102）--》经理审批(105)--》人事归档（103）--》结束(104)
报销流程：用户提交--》部长审批--》总经理审批--》财务---》结束

2)t_work_node
work_id,--属于哪个工作流类型
node_id,--节点ID
node_name,--节点名称
waiting_user --本节点待处理人
next_node_oper--下一个节点
work_id node_id node_name waiting_user next_node_pass  next_node_refuse  (next_node_oper)
1        101 	 开始			  102                                pass:102
1	 102     部长审批   8888,7777     103               101		  (pass:103;refuse:101)

2	 201     开始                                                        pass:202
2        202     部长审批  



针对单条请假数据有工作流流程

顾康伟申请请假1天 --用户提交 13：45：00
叶莹（张太伟）审批          --部长审批 16：45：00
袁老师（userID:8888）审批       --总经理审批
季老师统计       --人事归档 
结束  

瞿安琰 请假 

工作流动作表
3）t_work_node_action

打开：insert新数据（table_id,node_id,open_time,waiting_user，Status）
关闭：update原有数据（close_time,deal_user,deal_advice，Status）
table_id,---需要添加工作流的数据ID（不唯一）（多张表，请假表，报销表）
node_id,---当前节点 
open_time,---当前节点打开时间
close_time, ---当前节点关闭时间
waiting_user --当前节点处理人
deal_user ---当前节点已处理人
deal_advice --处理意见
table_id	node_id		open_time	 	close_time	waiting_user	deal_user	deal_advice
QJ1002		101		20161025 11:56:56    20161025 11:56:56 
QJ1002		102		20161025 11:56:56    20161025 14:56:56  8888,7777	7777		通过 
QJ1002          103 		20161025 14:56:56                        6666

QJ1002		101		20161025 11:56:56    20161025 11:56:56 
QJ1002		102		20161025 11:56:56    20161025 14:56:56  8888,7777	8888		拒绝，还没答辩 
QJ1002          101 
1               101

关闭--QJ1003		101		20161025 11:56:56    now()           --           ?               ?
  QJ1003           !               now()                                     !                     
   
---报销
BX1001          201              20161025 11:56:56
1               201

---查当前节点
mysql> select * from t_work_node_action where t_table_id='QJ1003' and t_node_id in( select t_node_id
 from t_work_node_config where t_work_id=1) order by id desc limit 0,1;


-----------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------

--工作流表
t_table_name:--该工作流处理的表，一般作为查看使用

create table t_work_flow(
   id int primary key not null,
   t_work_name  varchar(100) not null,
   t_table_name varchar(100) not null, 
   t_create_time  date

)engine=InnoDB;

insert into t_work_flow(id,t_work_name,t_table_name,t_create_time) values(1,'请假申请流程','t_holiday',now());
insert into t_work_flow(id,t_work_name,t_table_name,t_create_time) values(2,'报销申请流程','t_bx',now());
--insert into t_work_flow(id,t_work_name,t_table_name,t_create_time) values(3,'账户申请流程','t_bx',now());
--工作流节点配置表
create table t_work_node_config(
   id int primary key not null,
   t_work_id  varchar(100) not null,
   t_node_id  int not null,
   t_node_name varchar(100) not null,
   t_next_node_oper varchar(100) not null,
   t_waiting_user varchar(100) not null,
   t_create_time date
)engine=InnoDB;
---请假
insert into t_work_node_config values(1,1,101,'开始','pass:102','',now());
insert into t_work_node_config values(2,1,102,'部长审核','pass:103;refuse:101','3,5',now());
insert into t_work_node_config values(3,1,103,'经理审核','pass:104;refuse:101','2',now());
insert into t_work_node_config values(4,1,104,'人事归档','pass:105','4',now());
insert into t_work_node_config values(5,1,105,'结束','pass:end','',now());

---报销
insert into t_work_node_config values(1,2,201,'开始','pass:102','',now());
insert into t_work_node_config values(2,2,202,'部长审核','pass:103;refuse:101','3,5',now());
insert into t_work_node_config values(3,2,203,'经理审核','pass:104;refuse:101','2',now());
insert into t_work_node_config values(4,2,204,'财务归档','pass:105','4',now());
insert into t_work_node_config values(5,2,205,'结束','pass:end','',now());
--工作流动作表
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

)engine=InnoDB; 



---------------------------------
工作流查看：
1、入口,action的name定义好
2、mvc.xml配置
3、action类定义，方法定义(workFlowAction)
4、页面定义（auditHistory.jsp）
5、方法填充。


--请假流程添加工作流（添加申请、修改页面点提交）
--请假流程历史审批记录

10-26
--审批
--当审批流程不通过，回退到开始节点：当前的数据需要允许修改
请假修改权限：如果是“已提交”，如果当前流程处于开始节点，则允许本人修改

请假修改：点“提交”，如果提交之前的状态是草稿：加流程，下一个节点
点提交之前，是仍然是提交状态：流程到下一个节点

--另一种处理方案：如果审批流程不通过，则流程直接结束，该条数据不允许任何操作~！
需要在历史记录中显示：正常流程结束，或者拒绝结束



select
t_id,t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time from t_holiday where t_id = (select max(t_id) from t_holiday)

