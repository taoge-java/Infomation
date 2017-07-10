package com.information.utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.information.dao.weixin.base.BaseMessae;
import com.information.dao.weixin.message.Articles;
import com.information.dao.weixin.message.response.ResponseNewsMessage;
import com.thoughtworks.xstream.XStream;

/**
 * @author zengjintao
 * @version 1.0
 * 2017年4月七日上午8:05
 */
public class XMLUtil {

	/**
	 * xml转成map
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> xmlTomap(HttpServletRequest request){
		Map<String,String> map=new HashMap<String, String>();
		try {
		    InputStream in=request.getInputStream();
		    SAXReader reader=new SAXReader();
		    Document document= reader.read(in);
		    Element element= document.getRootElement();
		    List<Element> list=element.elements();
		    for(Element e:list){
		    	map.put(e.getName(), e.getTextTrim());
		    }
		    return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将数据转换成xml
	 */
	public static String messageToXml(Object obj){
		XStream xstream=new XStream();
		if(obj instanceof ResponseNewsMessage){
			xstream.alias("item",new Articles().getClass());
		}
		xstream.alias("xml", obj.getClass());
		return xstream.toXML(obj);
	}
	
	/**
	 * 将xml转换成消息对象
	 * @return
	 */
	@SuppressWarnings("unused")
	public static BaseMessae xmlToBaseMessae(HttpServletRequest request){
		Map<String,String> xm=xmlTomap(request);
		return null;
	}
	
	public static Object mapToBean(HttpServletRequest request){
		 try {
			InputStream in=request.getInputStream();
			InputStreamReader read=new InputStreamReader(in);
			char[] bytes=new char[10];
			int flag=0;
			StringBuffer buffer=new StringBuffer();
			while((flag=read.read(bytes, 0, 10))!=-1){
				buffer.append(new String(bytes,0,flag));
			}
			System.err.println(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
