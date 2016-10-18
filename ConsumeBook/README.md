
### 这是一个基swing和jdbc开发的图形界面桌面应用，涵盖以下内容：

1. 基础内容

	面向对象，字符串操作，日期和时间

2. 中级内容
	
	异常，集合，jdbc，反射，I/O，swing，图形界面等

3. 高级内容
	
	图表动态生成，数据库备份与恢复，自定义圆形进度条

4. 软件设计思想

	单例模式，类之间的松耦合，entity层设计，DAO层设计，Service层设计

5. 业务常见处理手法

	CRUD操作，配置信息，配置信息初始化，报表生成，一对多关系，多对一关系


### 软件功能一览

1. 消费一览

2. 记一笔

3. 消费分类管理

4. 月度消费报表

5. 设置预算和数据库路径

6. 备份数据

7. 恢复数据


### 软件开发流程

1. 表结构设计

	- 数据库和表的创建

		1 数据库定名为ConsumeBill。

		2 计划创建三个表，分别为**配置表信息**config用于保存每月预算和mysql安装路径；**消费分类表**category用于保存消费分类；**消费记录表**record用于保存每一笔消费记录。

		3 配置表config：如下字段：id，key_, value

		4 消费分类表category: 如下字段： id, name

		5 消费记录表record: 如下字段： id, spend, cid, comment, date

	- 表关系

		1 分析表之间的关系：比如record table和category table之间是一对多的关系。一个消费只属于一个分类，是多表，一个分类下却有多个消费记录，是一表。

		2 确定外键：外键加在多表中，这里就是record table里的cid，指向了category table中的主键。

	- 约束

		1 主键：为三个表手动加上主键约束，alter table config add pkname primary key(id);

		2 设置id为自增长： alter table config change id id int auto_increment;

		3 增加外键约束： alter table record add constraint fk_record_category foreign key (cid) references category(id)；

2. 原型设计

3. 实体类与DAO的设计

4. 功能开发


