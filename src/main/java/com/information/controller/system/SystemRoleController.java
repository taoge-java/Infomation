package com.information.controller.system;

import com.information.annotation.AopBean;
import com.information.annotation.ControllerRoute;
import com.information.constant.CommonEnum.LogType;
import com.information.controller.base.BaseController;
import com.information.model.primary.system.SystemRole;
import com.information.service.base.Result;
import com.information.service.system.SystemRoleService;
import com.information.utils.ResultCode;
import com.information.utils.StrKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Page;
/**
 * 角色管理
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月8日 下午9:29:32
 */
@ControllerRoute(url="/system/role")
public class SystemRoleController extends BaseController{
	
	private Log log=Log.getLog(SystemAdminController.class);
	
	@AopBean
	private SystemRoleService systemRoleService;
	
	public void index(){
		Integer pageNumber=getParaToInt("pageNumber", 1);
		String role_name=getPara("role.name");
		Page<SystemRole> page=systemRoleService.getRole(pageNumber,role_name);
		setAttr("pages", page);
		setAttr("login_name",role_name);
		rendView("/system/role/list.vm");
	}
	
	public void operRole(){
		int roleId=getParaToInt("id");
		String operList=systemRoleService.getOperByRoId(roleId);
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
		if(StrKit.isEmpoty(name)){
			renderJson(new ResultCode(ResultCode.FAIL,"请输入角色名称"));
			return;
		}
       Result result=systemRoleService.save(name,flag,remark);
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
		Result result=systemRoleService.delAll(id);
		renderJson(result.getResultCode());
	}
	
	/**
	 * 修改角色
	 */
	public void alert(){
		Integer id=getParaToInt(0);
		try{
			if(id>0||id!=null){
				setAttr("role",systemRoleService.alert(id));
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
		Result result=systemRoleService.update(name, flag, remark,id);
		systemLog(getCurrentUser().getLoginName()+"成功修改角色"+name,LogType.MODIFY.getValue());
		renderJson(result.getResultCode());
	}

	/**
	 * 保存用户操作权限集合
	 */
	public void saveOper(){
		int roleId = getParaToInt("roleId");
		String operIds = getPara("operIds");
		Result result=systemRoleService.saveOper(roleId, operIds);
		if(result.isSuccess()){
			renderJson(new ResultCode(ResultCode.SUCCESS, "操作成功"));
		}else{
			renderJson(new ResultCode(ResultCode.FAIL, "操作失败"));
		}
	}
}
