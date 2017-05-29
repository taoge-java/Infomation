package com.information.model;

import java.util.Map;

import com.jfinal.plugin.activerecord.Model;

import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

/**
 * model基类
 * @author zengjintao
 * 2016年10月30日下午 18:32:28
 * @param <M>
 */

@SuppressWarnings("serial")
public class BaseModel<M extends Model<M>> extends Model<M>{

	public void getParamters(Map<String,String[]> map){
		
		String modelName=getClass().getSimpleName();
		/**
		 * 遍历集合
		 */
	    for(Entry<String, String[]> e: map.entrySet()){
	    	//获得map的key值
			String paraKey=e.getKey();
			if(paraKey.toLowerCase().startsWith(modelName.toLowerCase()+".")){
				if(e.getValue().length == 1) {
					set(paraKey.substring(paraKey.indexOf(".")+1), e.getValue()[0]);
				}else if(e.getValue().length > 1) {
					set(paraKey.substring(paraKey.indexOf(".")+1), StringUtils.join(e.getValue(), ","));
				}
			}
		}
		
	}
}
