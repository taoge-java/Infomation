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
	
	public static String httpRequest(String url,String charset){
		String content=null;
		HttpClientBuilder builder=HttpClientBuilder.create();
		CloseableHttpClient httpClient= builder.build();
		HttpGet get=new HttpGet(url);
		try {
			HttpResponse response=httpClient.execute(get);
			if(response.getStatusLine().getStatusCode()==HttpURLConnection.HTTP_OK){
			    HttpEntity entity=response.getEntity();//从响应中获取消息实体
			    content=EntityUtils.toString(entity);
			    EntityUtils.consume(entity);
			}
			get.abort();
			get=null;
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(httpClient!=null){
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}

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
	
	
	public static  String httpPostRequest(String url,String body){
		String content=null;
		HttpClientBuilder builder=HttpClientBuilder.create();
		CloseableHttpClient httpClient= builder.build();
		HttpPost post=new HttpPost(url);
		//设置参数
		post.setEntity(new StringEntity(body, "UTF-8"));
		try {
			HttpResponse response=httpClient.execute(post);
			if(response.getStatusLine().getStatusCode()==HttpURLConnection.HTTP_OK){
			    HttpEntity entry=response.getEntity();
			    content=EntityUtils.toString(entry,"utf-8");
			    try{
			        EntityUtils.consume(entry);//关闭流
			    }catch(IOException E){
				    E.printStackTrace();
			    }
			}
			post.abort();
			post = null;
		 } catch (IOException e) {
			e.printStackTrace();
		 }finally {
			if(httpClient!=null){
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		 }
		 return content;
	}
	
	
	public static String httpPostResuest(String url,String cotentType,String body){
		String content=null;
		//创建HttpClientBuilder
		HttpClientBuilder builder=HttpClientBuilder.create();
		//创建CloseableHttpClient
		CloseableHttpClient httpClient=builder.build();
		HttpPost post=new HttpPost(url);
		//设置参数
		post.setHeader("Content-Type", cotentType);
		post.setEntity(new StringEntity(body,"utf-8"));
		try {
			HttpResponse response=  httpClient.execute(post);
			if(response.getStatusLine().getStatusCode()==HttpURLConnection.HTTP_OK){
				HttpEntity entity=response.getEntity();
				content=EntityUtils.toString(entity, "utf-8");
				EntityUtils.consume(entity);
			}
			post.abort();
			post=null;
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(httpClient!=null){
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}

}
