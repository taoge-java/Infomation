/**
 * 
 */
package com.event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月24日下午10:22:32
 */
public class Test {

	@SuppressWarnings({ "static-access", "deprecation" })
	public static void main(String[] args) throws InterruptedException {  
//        Queue<String> queue = new LinkedList<String>();  
//        for (int i = 0; i < 65; i++) {  
//            queue.offer("queue emement:" + i);  
//        }  
//        System.out.println(queue.size());  
//        String str;  
//        int j = 1;  
//        while (!queue.isEmpty()) {  
//            int k = 10;  
//            while ((str = queue.poll()) != null) {  
//                System.out.println(str);  
//                k--;  
//                if (k == 0) {  
//                    break;  
//                }  
//            }  
//            System.out.println("第" + j + "次提交！");  
//            j++;  
//            Thread.sleep(5000);  
//        }  
//  
//        System.out.println("===================================");  
//        System.out.println(queue.size());  
		
		
		final Queue queue = new com.event.Queue();
		OrderPOJO orderPOJO1 = new OrderPOJO();
		Date time = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(Calendar.SECOND, 10);
		orderPOJO1.setOrderNo("1");
		orderPOJO1.setCreateTime(calendar.getTime());
		
		OrderPOJO orderPOJO2 = new OrderPOJO();
		orderPOJO2.setOrderNo("2");
		calendar.add(Calendar.SECOND, 20);
		orderPOJO2.setCreateTime(calendar.getTime());
		
		queue.addQueue(orderPOJO1);
		queue.addQueue(orderPOJO2);
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					OrderPOJO orderPOJO =  queue.getQueueLastObject();
					if(orderPOJO == null){
						System.out.println("没得，数据了，等待加入数据");
						Thread.currentThread().suspend();
						continue;
					}
					long time = orderPOJO.getCreateTime().getTime() - System.currentTimeMillis();
					System.out.println("订单编号：" + orderPOJO.getOrderNo() + ",到期时间：" + 
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderPOJO.getCreateTime().getTime())
							+ ",当前线程需要睡眠" + time + "毫秒");
					if(time < 1){
						System.out.println("当前可以执行的数据订单编号：" + orderPOJO.getOrderNo());
						queue.removeObjectByOrderNo(orderPOJO.getOrderNo());
					}else{
						try {
							Thread.sleep(time);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
		});
		t.setName("执行队列的后台线程");
		t.start();
		long l = 40*1000;
		try {
			Thread.currentThread().sleep(l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		OrderPOJO orderPOJO3 = new OrderPOJO();
		orderPOJO3.setOrderNo("3");
		calendar.add(Calendar.SECOND, 30);
		orderPOJO3.setCreateTime(calendar.getTime());
		System.out.println("加入数据，开始执行");
		queue.addQueue(orderPOJO3);
		t.resume();
    }  
}
