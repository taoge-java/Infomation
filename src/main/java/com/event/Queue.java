/**
 * 
 */
package com.event;

import java.util.LinkedList;

/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月24日下午10:32:34
 */
public class Queue {

	/**
	 * 订单数据队列
	 */
	private LinkedList<OrderPOJO> orderQueue = new LinkedList<OrderPOJO>();
	
	
	/**
	 * 添加一个数据到队列
	 * @param orderPOJO
	 */
	public synchronized void addQueue(OrderPOJO orderPOJO){
		orderQueue.add(orderPOJO);
	}
	
	/**
	 * 获取队列中最后一个对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T getQueueLastObject(){
		return (T)orderQueue.peekFirst();
	}
	
	/**
	 * 根据订单编号移除一个已存在队列中的数据
	 * @param orderNo
	 */
	public synchronized void removeObjectByOrderNo(String orderNo){
		OrderPOJO orderPOJO = new OrderPOJO();
		orderPOJO.setOrderNo(orderNo);
		int index = orderQueue.indexOf(orderPOJO);
		orderQueue.remove(index);
	}
}
