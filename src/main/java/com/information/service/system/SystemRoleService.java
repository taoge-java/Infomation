package com.information.service.system;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.information.constant.CommonConstant;
import com.information.controller.system.SystemAdminController;
import com.information.model.primary.system.SystemMenu;
import com.information.model.primary.system.SystemOper;
import com.information.model.primary.system.SystemRole;
import com.information.model.primary.system.SystemRoleOperRef;
import com.information.service.base.BaseService;
import com.information.service.base.DefaultResult;
import com.information.service.base.Result;
import com.information.utils.ResultCode;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
/**
 * 角色管理services
 * @author zjt
 *
 */
@Service
public class SystemRoleService extends BaseService{
	
	private static final Logger LOG=Logger.getLogger(SystemAdminController.class);
	
	/**
	 * 根据角色获取操作权限列表
	 * @param roleId 角色id
	 * @return
	 */
	public Set<String> findRoleById(int roleId){
		SystemRole systemRole=SystemRole.dao.findById(roleId);
		List<SystemOper> oper=null;
		if(systemRole.getBoolean("super_flag")){
			oper=SystemOper.dao.find("select * from system_oper");
		}else{
			//查询角色所有操作权限
			oper=SystemOper.dao.find("select * from system_oper where id in(select oper_id from system_role_oper_ref where role_id="+roleId+")");
		}
		Set<String> operCode=new LinkedHashSet<String>();
		for(SystemOper code:oper){
			operCode.add(code.getStr("oper_code"));
		}
		return operCode;
	}
	
	
	
	/**
	 * 角色列表
	 * @return
	 */
	public Page<SystemRole> getRole(int pageNumber,String login_name){
		StringBuilder context=new StringBuilder("from system_role where 1=1");
		List<Object> param=new  ArrayList<Object>();
		if(StringUtils.isNotEmpty(login_name)){
			context.append(" and role_name=?");
			param.add(login_name);
		}
		String order=" order by id desc";
		context.append(order);
		return SystemRole.dao.paginate(pageNumber,CommonConstant.pageSize, "select *",context.toString(),param.toArray());
	}
	
    /**
     * 创建角色
     * @param name
     * @param flag
     * @param remark
     * @return
     */
	public Result save(String name, String flag, String remark) {
		Result result=new DefaultResult();
		ResultCode resultCode =new ResultCode(ResultCode.SUCCESS, "角色创建成功!");
        try{
        	SystemRole role1=SystemRole.dao.findFirst("select  *from system_role where role_name=?",name);
        	if(role1!=null){
        		resultCode =new ResultCode(ResultCode.FAIL, "该角色已经存在!");
	        	result.setResultCode(resultCode);
	        	return result;
        	}else{
        		SystemRole role=new SystemRole();
        		role.set("role_name", name);
        		role.set("flag", flag);
        		role.set("remark", remark);
        		role.save();
        	}
        }catch(Exception e){
            resultCode =new ResultCode(ResultCode.FAIL, "创建数据异常!");
        	LOG.error("创建数据异常!",e);
        }
		result.setResultCode(resultCode);
		return result;
	}
	/**
	 * 更新角色
	 * @param name
	 * @param flag
	 * @param remark
	 * @return
	 */
	public Result update(String name, String flag, String remark,int id){
		Result result=new DefaultResult();
		ResultCode resultCode=new ResultCode(ResultCode.SUCCESS,"角色修改成功");
		SystemRole role=SystemRole.dao.findById(id);
		try{
			if(role!=null){
				role.set("role_name", name);
				role.set("flag", flag);
        		role.set("remark", remark);
        		role.update();
			}
		}catch(Exception e){
			resultCode=new ResultCode(ResultCode.FAIL,"更新数据异常");
			LOG.error("更新数据异常",e);
		}
		result.setResultCode(resultCode);
		return result;
	}
	/**
	 * 修改角色
	 * @param id
	 * @return
	 */
	public SystemRole alert(int id){
		return SystemRole.dao.findById(id);
	}
	/**
	 * 批量删除角色
	 * @param ids
	 * @return
	 */
	public Result delAll(String ids[]){
		Result result=new DefaultResult();
		ResultCode resultCode=new ResultCode(ResultCode.SUCCESS, "删除数据成功");
		try{
			for(String id:ids){
				SystemRole.dao.deleteById(id);
			}
		}catch(Exception e){
			LOG.error("删除数据异常....",e);
			resultCode=new ResultCode(ResultCode.FAIL, "删除数据异常");
		}
		result.setResultCode(resultCode);
		return result;
	}
	
	/**
	 * 获取权限列表
	 * @param role_id
	 * @return
	 */
	public String getOperByRoId(int role_id){
		HashSet<String> hasOper=new HashSet<String>();
		List<SystemOper> operList=SystemOper.dao.find("select * from system_oper a,system_role_oper_ref b where a.id=b.oper_id and b.role_id=?",role_id);
		if(operList != null){
			for(SystemOper oper: operList){
				hasOper.add(oper.getStr("oper_code"));
			}
		}
		//所有菜单
		List<SystemMenu> menuList=SystemMenu.dao.find("select * from system_menu");
		JSONArray jsonarray = new JSONArray();
		if(menuList.size()>0&&menuList!=null){
			JSONObject jsonObject=null;
			for(SystemMenu menu:menuList){
				jsonObject=new JSONObject();
				jsonObject.put("id", "menu_"+menu.getInt("id"));
				jsonObject.put("pId", menu.get("parent_id")==null?"menu_"+menu.getInt("id"):"menu_"+menu.getInt("parent_id"));
				jsonObject.put("name", menu.getStr("menu_name"));
				jsonObject.put("open", false);
				jsonarray.add(jsonObject);
			}
		}
		//所有操作
		List<SystemOper> allOperList=SystemOper.dao.find("select * from system_oper");
		if(allOperList.size()>0&&allOperList!=null){
			JSONObject jsonObject=null;
			for(SystemOper oper:allOperList){
				jsonObject=new JSONObject();
				jsonObject.put("id", oper.getInt("id"));
				jsonObject.put("name", oper.getStr("oper_name"));
				jsonObject.put("pId", "menu_"+oper.getInt("menu_id"));
				jsonObject.put("code",oper.getStr("oper_code"));
				if(hasOper.contains(oper.getStr("oper_code"))){
					jsonObject.put("checked", true);
				}
				jsonObject.put("open", false);
				jsonarray.add(jsonObject);
			}
		}
		return jsonarray.toString();
	}
	
	/**
	 * 保存权限
	 * @param roleId
	 * @param session
	 * @param operIds
	 */
	public Result saveOper(int roleId,String operIds){
		Result result=new DefaultResult();
		ResultCode resultCode=new ResultCode(ResultCode.SUCCESS);
		try{
			Db.update("delete from system_role_oper_ref where role_id="+ roleId);
			if (StringUtils.isEmpty(operIds)) {
				result.setResultCode(resultCode);
				return result;
			}
			String[] opers = operIds.split(",");
			SystemRoleOperRef roleOperRef = null;
			for (String oper_id: opers) {
				roleOperRef =  new SystemRoleOperRef();
				roleOperRef.set("role_id", roleId);
				roleOperRef.set("oper_id", Integer.parseInt(oper_id) );
				roleOperRef.save();
			}
			result.setResultCode(resultCode);
		}catch(Exception e){
			resultCode=new ResultCode(ResultCode.FAIL);
			LOG.error("保存权限异常", e);
		}
		return result;
	}
}
