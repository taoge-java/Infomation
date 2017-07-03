package com.information.controller.system;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.information.annotation.Permission;
import com.information.annotation.ControllerRoute;
import com.information.constant.CommonEnum.LogType;
import com.information.constant.OperCode;
import com.information.controller.base.BaseController;
import com.information.model.primary.system.SystemAdmin;
import com.information.model.primary.system.SystemRole;
import com.information.service.base.Result;
import com.information.service.system.SystemAdminService;
import com.information.utils.ResultCode;
import com.information.utils.StrUtils;

/***
 * 管理员设置
 */
@ControllerRoute(url="/system/admin")
public class SystemAdminController extends BaseController{
	
	@Autowired
	private SystemAdminService systemAdminService;
	
	/**
	 * 管理员列表
	 */
	@Permission(points=OperCode.OPER_CODE_1_3_1_1)
	public void index(){
		int pageNmuber=getParaToInt("pageNmuber",1);
		Result result=systemAdminService.getAdminList("", pageNmuber);
		setAttr("page", result.getDefaultModel());
		rendView("/system/admin/index.vm");
	}
	
	public void list(){
		int pageNmuber=getParaToInt("pageNmuber",1);
		String name=getPara("name");
		Result result=systemAdminService.getAdminList(name, pageNmuber);
		setAttr("page",result.getDefaultModel());
		rendView("/system/admin/list.vm");
	}
    /**
     * 添加管理员渲染视图
     */
	public void add(){
		List<SystemRole> list=systemAdminService.findAllSystemRole();
		setAttr("list", list);
		rendView("system/admin/add.jsp");
	}
	/**
	 * 保存管理员
	 */
	public void save(){
		SystemAdmin systemAdmin=new SystemAdmin();
		systemAdmin.getParamters(getParaMap());
		String password=getPara("password");
		Result result=systemAdminService.save(systemAdmin,password);
		if(result.isSuccess()){
			systemLog(getCurrentUser().getLoginName()+"成功创建管理员"+systemAdmin.getStr("login_name"),LogType.MODIFY.getValue());
		}
		renderJson(result.getResultCode());
	}
	/**
	 * 删除管理员
	 */
	public void deleteById(){
		Integer id=getParaToInt("id");
		Result result= systemAdminService.delById(id);
		renderJson(result.getResultCode());
	}
	/**
	 * 修改管理员
	 */
	public void edit(){
		Integer id=getParaToInt(0);
	    List<SystemRole> list=systemAdminService.findAllSystemRole();
	    setAttr("list", list);
	    setAttr("admin",systemAdminService.getSystemAdmin(id));
	    rendView("system/admin/update.jsp");
	}
	
	/**
	 * 更新管理员
	 */
	public void update(){
	    int id=	getParaToInt(0);
		SystemAdmin systemAdmin=SystemAdmin.dao.findById(id);
		systemAdmin.getParamters(getParaMap());
		String password=getPara("password");
		if(StringUtils.isEmpty(password)){
			renderJson(new ResultCode(ResultCode.FAIL,"密码不能为空"));
			return;
		}
		Result result=systemAdminService.update(systemAdmin,password);
		renderJson(result.getResultCode());
	}
	/**
	 * 批量删除管理员
	 */
	public void delAll(){
		String ids=getPara("ids");
		String[] id=StrUtils.spilt(ids);
		Result result=systemAdminService.delAll(id);
		if(result.isSuccess()){
			
		}
		renderJson(result.getResultCode());
	}
}
