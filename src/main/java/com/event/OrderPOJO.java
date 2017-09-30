/**
 * 
 */
package com.event;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月24日下午10:30:40
 */
public class OrderPOJO implements Serializable{

	private static final long serialVersionUID = 8126155000510758759L;

	private String orderNo;  //订单编号
	
	private Date createTime;  //下单时间
	
	public OrderPOJO() {
		
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public boolean equals(Object obj) {
		OrderPOJO orderPOJO = (OrderPOJO)obj;
		return this.orderNo.equals(orderPOJO.getOrderNo());
	}
}
