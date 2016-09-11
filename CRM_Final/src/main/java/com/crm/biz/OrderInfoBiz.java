package com.crm.biz;

import java.util.List;
import java.util.Map;

import com.crm.bean.OrderInfo;

public interface OrderInfoBiz {
	public List<OrderInfo> getOrderInfoByCustomerId(OrderInfo orderInfo);
	//TODO:
	public List<OrderInfo> getOrderInfoAndSumprice(Map<String,Object> map);

}
