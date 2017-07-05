package com.information.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;
/**
 * 消息订阅端
 * @author taoge
 * @version 1.0
 * @create_at 2017年6月26日下午3:08:38
 */
public class SubClient {

	private JedisPool jedisPool;

	private Jedis jedis;
	
	/**简单的jedis**/
	public SubClient(String host, int port){
		jedis = new Jedis(host, port);
	}
	
	/**连接池的jedis**/
	public SubClient(String host, int port, int timeout, String passwd){
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
	}
	
	public void sub(JedisPubSub listener, String channel){
		jedis.subscribe(listener, channel);
		//此处将会阻塞，在client代码级别为JedisPubSub在处理消息时，将会“独占”链接
		//并且采取了while循环的方式，侦听订阅的消息
	}
}
