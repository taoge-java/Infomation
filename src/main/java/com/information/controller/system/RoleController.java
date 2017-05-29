package com.information.controller.system;


import org.apache.commons.lang.StringUtils;

import com.information.common.BaseController;
import com.information.constant.CommonEnum.LogType;
import com.information.model.system.SystemRole;
import com.information.service.system.RoleService;
import com.information.utils.Result;
import com.information.utils.ResultCode;
import com.jfinal.aop.Duang;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Page;
/**
 * 角色管理控制器
 * @author Administrator
 *
 */
@ControllerBind(controllerKey="system/role")
public class RoleController extends BaseController{
	
	private Logger log=Logger.getLogger(AdminController.class);
	
	private RoleService roleServices=Duang.duang(RoleService.class.getName(), RoleService.class);
	
	public void index(){
		Integer pageNumber=getParaToInt("pageNumber", 1);
		String role_name=getPara("role.name");
		Page<SystemRole> page=roleServices.getRole(pageNumber,role_name);
		setAttr("pages", page);
		setAttr("login_name",role_name);
		rendView("/system/role/list.vm");
	}
	public void operRole(){
		int roleId=getParaToInt("id");
		String operList=roleServices.getOperByRoId(roleId);
		setAttr("operList", operList);
		rendView("/system/role/oper.vm");
	}
	
	 /**
     * 添加角色
     */
	public void add(){
		rendView("/system/role/add.vm");
	}
	/***
	 * 创建角色
	 */
	
	public void create(){
		String name=getPara("name");
		String flag=getPara("flag");
		String remark=getPara("remark");
		if(StringUtils.isEmpty(name)){
			renderJson(new ResultCode(ResultCode.FAIL,"请输入角色名称"));
			return;
		}
	   Result result= roleServices.save(name,flag,remark);
	   systemLog(getCurrentUser().getLoginName()+"成功创建角色"+name,LogType.MODIFY.getValue());
	   renderJson(result.getResultCode());
	}
	/**
	 * 删除角色
	 */
	public void deleteById(){
		Integer id=getParaToInt("id");
		if(id>0||id!=null){
			SystemRole.dao.deleteById(id);
			renderJson(new ResultCode(ResultCode.SUCCESS, "删除成功"));
		}
	}
	/**
	 * 批量删除角色
	 */
	public void delAll(){
		String ids=getPara("ids");
		String[] id=ids.split(",");
		Result result=roleServices.delAll(id);
		renderJson(result.getResultCode());
	}
	/**
	 * 修改角色
	 */
	public void alert(){
		Integer id=getParaToInt(0);
		try{
			if(id>0||id!=null){
				setAttr("role",roleServices.alert(id));
				rendView("system/role/update.jsp");
			}
		}catch(Exception e){
			log.error("系统异常");
			renderError(404);
		}
	}
	/**
	 * 更新角色
	 */
	public void update(){
		Integer id=getParaToInt("id");
		String name=getPara("name");
		String flag=getPara("flag");
		String remark=getPara("remark");
		Result result=roleServices.update(name, flag, remark,id);
		systemLog(getCurrentUser().getLoginName()+"成功修改角色"+name,LogType.MODIFY.getValue());
		renderJson(result.getResultCode());
	}

}
