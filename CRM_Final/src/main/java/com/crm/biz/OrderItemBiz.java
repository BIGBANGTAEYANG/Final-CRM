package com.crm.biz;

import java.util.List;

import com.crm.bean.OrderItem;

public interface OrderItemBiz {
	public List<OrderItem> getOrderItemByInfoId(OrderItem orderItem);
}
