package com.information.model.slave;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

public class SlaveBaseModel extends Model<SlaveBaseModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public void getParamters(Map<String,String[]> map){
		String modelName=getClass().getSimpleName();
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
