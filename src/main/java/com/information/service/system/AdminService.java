package com.information.service.system;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.information.constant.CommonConstant;
import com.information.model.system.SystemAdmin;
import com.information.service.base.DefaultResult;
import com.information.service.base.Result;
import com.information.utils.Md5Utils;
import com.information.utils.ResultCode;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Page;

@Service
public class AdminService {
	
	private static final Log log=Log.getLog(AdminService.class);
	
	/**
	 * 管理员列表
	 */
	public Result getAdmin(String  login_name,int pageNumber){
		Result result=new DefaultResult();
		StringBuilder context=new StringBuilder("from system_admin where 1=1");
		List<Object> param=new ArrayList<Object>();
		if(StringUtils.isNotEmpty(login_name)){
			context.append(" and login_name=?");
			param.add(login_name);
		}
		Page<SystemAdmin> page= SystemAdmin.dao.paginate(pageNumber, CommonConstant.pageSize, "select *", context.toString(),param.toArray());
		result.setDefaultModel(page);
		return result;
	}
	/**
	 * 保存管理员
	 * @param systemAdmin
	 * @param password
	 * @return
	 */
	@SuppressWarnings("static-access")
	public Result save(SystemAdmin systemAdmin,String password){
		Result result=new DefaultResult();
		ResultCode resultCode=new ResultCode(ResultCode.SUCCESS, "管理员创建成功!");
		try{
		    SystemAdmin admin=systemAdmin.dao.findFirst("select * from sys_admin where login_name=?",systemAdmin.getStr("login_name"));
		    if(admin!=null){
		    	resultCode=new ResultCode(ResultCode.FAIL, "该管理员已存在,请勿重复创建!");
		    	result.setResultCode(resultCode);
		    	return result;
		    }else{
			    systemAdmin.set("sys_password",Md5Utils.getMd5(password));
			    systemAdmin.save();
		    }
		}catch(Exception e){
			resultCode=new ResultCode(ResultCode.FAIL, "数据创建异常!");
			log.error("数据创建异常");
		}
		result.setResultCode(resultCode);
		return result;
	}
	/**
	 * 删除管理员
	 * @param id
	 * @return
	 */
	public Result delById(int id){
		Result result=new DefaultResult();
		ResultCode rseultCode=new ResultCode(ResultCode.SUCCESS,"删除成功");
        try{
			SystemAdmin.dao.deleteById(id);
		}catch(Exception e){
			e.printStackTrace();
			log.error("删除数据异常");
			rseultCode=new ResultCode(ResultCode.FAIL,"删除数据异常");
		}
        result.setResultCode(rseultCode);
        return result;
	}
	/**
	 * 修改管理员
	 */
	public SystemAdmin alert(int id){
		return SystemAdmin.dao.findById(id);
	}
	public Result update(SystemAdmin systemAdmin,String password){
		Result result=new DefaultResult();
		ResultCode resultCode=new ResultCode(ResultCode.SUCCESS,"数据更新成功");
		try{
			systemAdmin.set("sys_password",Md5Utils.getMd5(password));
	        systemAdmin.update();
		}catch(Exception e){
			e.printStackTrace();
			log.error("删除数据异常");
			resultCode=new ResultCode(ResultCode.FAIL,"更新数据异常");
		}
		result.setResultCode(resultCode);
		return result;
		
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public Result delAll(String ids[]){
		Result result=new DefaultResult();
		ResultCode resultCode=new ResultCode(ResultCode.SUCCESS, "删除数据成功");
		try{
			for(String id:ids){
				SystemAdmin.dao.deleteById(id);
			}
		}catch(Exception e){
			log.error("删除数据异常....");
			resultCode=new ResultCode(ResultCode.FAIL, "删除数据异常");
		}
		result.setResultCode(resultCode);
		return result;
	}
	

}
