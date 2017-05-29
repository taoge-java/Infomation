-- 系统管理员表
drop table if exists system_admin;
create table system_admin(
   id int not null auto_increment,
   login_name varchar(100) not null,
   real_name varchar(100) default null,
   password varchar(100) not null,
   encrypt varchar(100) not null,
   last_login_ip varchar(100) default null,
   mobile varchar(100) default null,
   nack_name varchar(100) default null,
   create_at datetime default null,
   update_at datetime default null,
   admin_type tinyint(2) default 0,
   disabled_flag tinyint(1) default 0,
   mail varchar(100) default null,
   last_login_time datetime default null,
   super_flag tinyint(1) default 1,
   role_id int default 0,
   login_count int default 0,
   login_error int default 0,
   primary key(id)
)engine=innodb charset=utf8 collate=utf8_general_ci;

-- 角色表
drop table if exists system_role;
create table system_role(
   id int not null auto_increment,
   role_name varchar(100) not null,
   remark varchar(100) default null,
   super_flag tinyint(1) default 0,
   primary key(id)
)engine=innodb charset=utf8 collate=utf8_general_ci;

-- 操作表
drop table if exists system_oper;
create table system_oper(
   id int not null auto_increment,
   oper_name varchar(100) not null,
   menu_id int not null,
   oper_code varchar(100) not null,
   remark varchar(100) default null,
   primary key(id)
)engine=innodb charset=utf8 collate=utf8_general_ci;

-- 系统菜单表
drop table if exists system_menu;
create table system_menu(
   id int not null auto_increment,
   menu_name varchar(100) not null,
   menu_code varchar(100) not null,
   menu_url varchar(100) not null,
   parent_id int ,
   primary key(id)
)engine=innodb charset=utf8 collate=utf8_general_ci;

-- 角色操作关联表
drop table if exists system_role_oper_ref;
create table system_role_oper_ref(
   id int not null auto_increment,
   role_id int not null,
   oper_id int not null,
   primary key(id)
)engine=innodb charset=utf8 collate=utf8_general_ci;

drop table if exists system_log;
create table system_log(
   id int not null auto_increment,
   admin_id int not null,
   oper_name varchar(100) not null,
   oper_ip varchar(100) default null,
   oper_desc varchar(1024) default null,
   oper_time datetime default null,
   login_type tinyint(2) default 1,
   primary key(id)
)engine=innodb charset=utf8 collate=utf8_general_ci;


