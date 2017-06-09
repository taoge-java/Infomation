insert into system_admin(login_name,password,encrypt) values("admin","9b05732f4b50b86a18e48b982c83bba3","d6f6ccb41d6403f2adeb98d6db97814a4b07594c");
insert into system_role(id,role_name) values(1,"班主任");

insert into system_menu(id,menu_name,menu_code,menu_url,parent_id) values(1,'学生信息管理',"1_1","/student/info",null);
insert into system_menu(id,menu_name,menu_code,menu_url,parent_id) values(2,'录入学生信息',"1_1_1","/student/info",1);
insert into system_menu(id,menu_name,menu_code,menu_url,parent_id) values(3,'学生信息管理',"1_1_2","/student/info",1);

insert into system_menu(id,menu_name,menu_code,menu_url,parent_id) values(4,'考试管理',"1_2","/student/info",null);
insert into system_menu(id,menu_name,menu_code,menu_url,parent_id) values(5,'考试信息管理',"1_2_1","/student/info",4);
insert into system_menu(id,menu_name,menu_code,menu_url,parent_id) values(6,'学生成绩管理',"1_2_2","/student/info",4);
insert into system_menu(id,menu_name,menu_code,menu_url,parent_id) values(7,'学科管理',"1_2_3","/student/info",4);

insert into system_menu(id,menu_name,menu_code,menu_url,parent_id) values(8,'系统设置',"1_3","/student/info",null);
insert into system_menu(id,menu_name,menu_code,menu_url,parent_id) values(9,'管理员管理',"1_3_1","/student/info",8);
insert into system_menu(id,menu_name,menu_code,menu_url,parent_id) values(10,'角色管理',"1_3_2","/student/info",8);
insert into system_menu(id,menu_name,menu_code,menu_url,parent_id) values(11,'操作日志管理',"1_3_3","/student/info",8);

insert into system_oper(id,oper_name,menu_id,oper_code,remark) values(1,"新增",2,"1_1_1_1",null);
insert into system_oper(id,oper_name,menu_id,oper_code,remark) values(2,"删除",2,"1_1_1_2",null);

insert into system_oper(id,oper_name,menu_id,oper_code,remark) values(3,"列表",3,"1_1_2_1",null);
insert into system_oper(id,oper_name,menu_id,oper_code,remark) values(4,"删除",3,"1_1_2_2",null);

insert into system_oper(id,oper_name,menu_id,oper_code,remark) values(5,"列表",5,"1_2_1_1",null);
insert into system_oper(id,oper_name,menu_id,oper_code,remark) values(6,"删除",5,"1_2_1_2",null);

insert into system_oper(id,oper_name,menu_id,oper_code,remark) values(7,"列表",6,"1_2_2_1",null);
insert into system_oper(id,oper_name,menu_id,oper_code,remark) values(8,"删除",6,"1_2_2_2",null);

insert into system_oper(id,oper_name,menu_id,oper_code,remark) values(9,"列表",7,"1_2_3_1",null);
insert into system_oper(id,oper_name,menu_id,oper_code,remark) values(10,"删除",7,"1_2_3_2",null);

insert into system_oper(id,oper_name,menu_id,oper_code,remark) values(11,"列表",9,"1_3_1_1",null);
insert into system_oper(id,oper_name,menu_id,oper_code,remark) values(12,"删除",9,"1_3_1_2",null);

insert into system_oper(id,oper_name,menu_id,oper_code,remark) values(13,"列表",10,"1_3_2_1",null);
insert into system_oper(id,oper_name,menu_id,oper_code,remark) values(14,"删除",10,"1_3_2_2",null);

insert into system_oper(id,oper_name,menu_id,oper_code,remark) values(15,"列表",11,"1_3_3_1",null);
insert into system_oper(id,oper_name,menu_id,oper_code,remark) values(16,"删除",11,"1_3_3_2",null);