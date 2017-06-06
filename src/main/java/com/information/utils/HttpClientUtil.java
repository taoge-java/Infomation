package com.information.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * http请求工具类
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月22日 下午4:54:51
 */
public class HttpClientUtil {
	
	@SuppressWarnings("unused")
	public static  String httpPostRequest(String url,Map<String,String> map){
		String content=null;
		HttpClientBuilder builder=HttpClientBuilder.create();
		CloseableHttpClient httpClient= builder.build();
		HttpPost post=new HttpPost(url);
		//NameValuePair:代表数据类型
		List<NameValuePair> valuePair = new ArrayList<NameValuePair>();
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext()){
			String key=iterator.next();
			//valuePair.add(new BasicNameValuePair(key,map.get(key)));
		}
		return content;
	}
	
	public static String httpGet(String url){
		return httpGet(url,"UTF-8");
	}

	/**
	 * httpGet请求
	 * @param url 
	 * @param code  编码
	 * @return
	 */
	public static String httpGet(String url, String code) {
		String content=null;
		HttpClientBuilder builder=HttpClientBuilder.create();
	    CloseableHttpClient httpClient=builder.build();
	    HttpGet httpget=new HttpGet(url);
	    try {
			HttpResponse response=httpClient.execute(httpget);
			if(response.getStatusLine().getStatusCode()==HttpURLConnection.HTTP_OK){
			    HttpEntity entity=response.getEntity();
			    content=EntityUtils.toString(entity, code);
			    EntityUtils.consume(entity);
			}
			httpget.abort();
			httpget=null;
			return content;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * httpPost请求
	 * @param url
	 * @param body
	 * @param charset
	 * @return
	 */
	public static String httpPost(String url,String params,String charset){
		String content=null;
		HttpClientBuilder builder=HttpClientBuilder.create();
	    CloseableHttpClient httpClient=builder.build();
	    HttpPost post=new HttpPost(url);
	    try {
			post.setEntity(new StringEntity(params, charset));//设置参数
	        HttpResponse response=httpClient.execute(post);
	        if(response.getStatusLine().getStatusCode()==HttpURLConnection.HTTP_OK){
	        	HttpEntity entity=response.getEntity();//获取响应信息
	        	content=EntityUtils.toString(entity,charset);
	        	EntityUtils.consume(entity);//关闭流
	        }
	        post.abort();
	        httpClient=null;
	        return content;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String httpPost(String url, String params) {
		return httpPost(url,params,"UTF-8");
	}

}
