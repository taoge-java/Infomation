package com.information.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * json工具类
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年5月24日 下午2:10:03
 */
public class JsonUtil {
	/**
	 * 将map转换为json
	 * @param map
	 * @return
	 */
	public static String toJson(Map<String,String> map){
		if(null==map)
			throw new NullPointerException("map can not be null");
		Set<String> keySet=map.keySet();
		String lastKey=getSetLastKey(keySet.iterator());
		StringBuilder sb=new StringBuilder("{");
		for(String s:keySet){
			sb.append("\"");
			sb.append(s+"\":").append("\""+map.get(s)+"\"");
			if(s==lastKey)
			   sb.append("");
			else
			   sb.append(",");
		}
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * 获取set集合中的最后一个key
	 * @param iterator
	 * @return
	 */
	public static String getSetLastKey(Iterator<String> iterator){
		List<String> list=new ArrayList<String>();
		while(iterator.hasNext()){
			list.add(iterator.next());
		}
		return list.get(list.size()-1);
	}
}
