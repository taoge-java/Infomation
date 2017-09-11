package com.information.redis;


import com.jfinal.log.Log;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 消息发布端
 * @author zengjintao
 * @version 1.0
 * create_at 2017年6月25日 上午10:11:50
 */
public class PubClient {

	private Jedis jedis;
	
	private JedisPool jedisPool;
	
	private static final Log LOG = Log.getLog(PubClient.class);
	
	public PubClient(String host, int port){
		jedis = new Jedis(host, port);
	}
	
	/**连接池的jedis**/
	public PubClient(String host, int port, int timeout, String passwd){
		try {
			if (jedisPool == null) {
				JedisPoolConfig config = new JedisPoolConfig();
				config.setMaxIdle(5);
				config.setMinIdle(1);
				config.setMaxWaitMillis(2000);
				config.setMaxTotal(20);
				jedisPool = new JedisPool(config, host, port, timeout, passwd);
			}
			if(jedis ==null){
				jedis = jedisPool.getResource();
			}
		} catch (Exception e) {
			LOG.error("redis发布异常。。。");
		}
	}
	
	public void pub(String channel,String message){
		jedis.publish(channel, message);
	}
	
	public void close(String channel,String message){
		jedis.publish(channel,"关闭jedis消息发布");
		jedis.del(channel);
		if(jedis != null){
			jedis.close();
		}
	}
	
}
