package com.information.utils;

import java.util.List;

/***
 * 返回结果工具类
 */
public class Result{
	
	private ResultCode resultCode;
	
	private List<Object> list;
	
	private Object object;
	
	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> subject) {
		this.list = subject;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public ResultCode getResultCode() {
		return resultCode;
	}

	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
	}


}