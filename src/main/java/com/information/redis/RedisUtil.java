package com.information.redis;

import com.information.constant.CommonConstant;
import com.information.listener.RedisListener;
import com.jfinal.plugin.redis.Redis;

public class RedisUtil {
	
	public static void pub(String channel,String message){
	    Redis.use().getJedis().publish(channel, message);
	}
	
	public static void sub(String channel){
	    Redis.use().getJedis().subscribe(new RedisListener(), channel);
	}
	
	public static void close(){
		Redis.use().getJedis().close();
	}
	
	public static String set(String key,Object value){
		return Redis.use(CommonConstant.HOSTCAHENAME).set(key, value);
	}
	
	public static String set(String key,Object value,int seconds){
		return Redis.use(CommonConstant.HOSTCAHENAME).setex(key, seconds, value);
	}
	
	public static <T> T get(String key){
		return Redis.use(CommonConstant.HOSTCAHENAME).get(key);
	}
	
	public static void delete(String key){
		 Redis.use(CommonConstant.HOSTCAHENAME).del(key);
	}
}
