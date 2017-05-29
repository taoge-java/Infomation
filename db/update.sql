-- 用户角色关联表
drop table if exists system_user_role_ref;
create table system_user_role_ref(
   id int not null auto_increment,
   admin_id int not null,
   role_id int not null,
   primary key(id)
)engine=innodb charset=utf8 collate=utf8_general_ci;

-- 权限操作表
drop table if exists system_function;
create table system_function(
   id int not null auto_increment,
   function_name varchar(100) not null,
   function_code varchar(100) default null,
   primary key(id)
)engine=innodb charset=utf8 collate=utf8_general_ci;

-- 角色权限关联表
drop table if exists system_role_function;
create table system_role_function(
  id int not null auto_increment,
  role_id int not null,
  function_id int not null,
  primary key(id)
)engine=innodb charset=utf8 collate=utf8_general_ci;