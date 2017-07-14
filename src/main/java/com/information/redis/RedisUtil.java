package com.information.redis;

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
}
