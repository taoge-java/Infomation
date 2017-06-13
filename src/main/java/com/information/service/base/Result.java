package com.information.service.base;

import java.util.Map;

import com.information.utils.ResultCode;

/**
 * 业务返回结果接口
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月13日 下午8:22:38
 */
public interface Result {

	String DEFAULT_MODEL_KEY="default_model";
	
	public boolean isSuccess();
	
	public void setModel(String key,Object value);
	
	public void setDefaultModel(Object model);
	
	public void setResultCode(ResultCode resultCode);
	
	public ResultCode getResultCode();
	
	public Map<String,Object>  getModels();
	
	public Object getDefaultModel();
}
