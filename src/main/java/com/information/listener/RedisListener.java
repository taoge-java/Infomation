package com.information.listener;

import com.jfinal.log.Log;

import redis.clients.jedis.JedisPubSub;
/**
 * redis订阅消息监听器
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月11日 下午5:53:09
 */
public class RedisListener extends JedisPubSub{

	private static final Log logger=Log.getLog(RedisListener.class);
	
    /**
     *  取得订阅的消息后的处理    (non-Javadoc)
     * @see redis.clients.jedis.JedisPubSub#onMessage(java.lang.String, java.lang.String)
     */
    @Override
    public void onMessage(String channel, String message) {
    	logger.info("开始接收信息");
        System.out.println("onMessage: channel["+channel+"], message["+message+"]");
    }

    /**
     *  取得按表达式的方式订阅的消息后的处理  (non-Javadoc)
     * @see redis.clients.jedis.JedisPubSub#onPMessage(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("onPMessage: channel["+channel+"], message["+message+"]");
    }

    /**
     *初始化订阅时候的处理    (non-Javadoc)
     * @see redis.clients.jedis.JedisPubSub#onSubscribe(java.lang.String, int)
     */
    @Override
    public void onSubscribe(String channel,int subscribedChannels) {
    	logger.info("初始化订阅消息");
        System.out.println("onSubscribe: channel["+channel+"],"+"subscribedChannels["+subscribedChannels+"]");
    }

    /**
     *  取消订阅时候的处理  
     */
    @Override
    public void onUnsubscribe(String channel,int subscribedChannels) {
    	logger.info("取消订阅消息");
        System.out.println("onUnsubscribe: channel["+channel+"], "+ "subscribedChannels["+subscribedChannels+"]");
    }
 
    /**
    * 取消按表达式的方式订阅时候的处理 
    */
    @Override
    public void onPUnsubscribe(String pattern,int subscribedChannels) {
        System.out.println("onPUnsubscribe: pattern["+pattern+"],"+ "subscribedChannels["+subscribedChannels+"]");
    }

    /**
     *  初始化按表达式的方式订阅时候的处理    (non-Javadoc)
     * @see redis.clients.jedis.JedisPubSub#onPSubscribe(java.lang.String, int)
     */
    @Override
    public void onPSubscribe(String pattern,int subscribedChannels) {
        System.out.println("onPSubscribe: pattern["+pattern+"], "+"subscribedChannels["+subscribedChannels+"]");
    }
}
