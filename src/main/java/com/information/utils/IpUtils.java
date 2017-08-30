package com.information.utils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.log.Log;

import net.sf.json.JSONObject;

public class IpUtils {
	
	private static final Log LOG = Log.getLog(IpUtils.class);
	
	/**
	 * 获取客户端ip地址
	 * @return
	 */
   public static String getAddressIp(HttpServletRequest request){
	   String ip = request.getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
    } 
   
    /**
     * 获取ip地址
     * @param ip
     * @return
     */
    public static String getIpAddress(String ip){
		String address = "";
		try {
	//		{"code":0,"data":{"country":"中国","country_id":"CN","area":"华北","area_id":"100000","region":"北京市","region_id":"110000","city":"北京市","city_id":"110100","county":"","county_id":"-1","isp":"阿里巴巴","isp_id":"100098","ip":"47.94.12.108"}}
			URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
			URLConnection con = url.openConnection();
			InputStream is = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer buffer = new StringBuffer();
			String line =null;
			while(null != (line = br.readLine())){
				buffer.append(line);
			}
			br.close();
			isr.close();
			is.close();
			JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
			JSONObject result = JSONObject.fromObject(jsonObject.get("data"));
			if(jsonObject.getInt("code") == 0){
				address = result.get("country").toString()+result.get("region")+result.get("city").toString();
			}
			return address;
		} catch (Exception e) {
			LOG.error("获取ip地址异常",e);
		}
		return null;
	}
    public static void main(String[] args) {
		System.err.println(getIpAddress("106.8.99.77"));
	}
} 
