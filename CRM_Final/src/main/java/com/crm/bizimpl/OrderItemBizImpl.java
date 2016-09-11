package com.crm.bizimpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.bean.Contacter;
import com.crm.bean.OrderInfo;
import com.crm.bean.OrderItem;
import com.crm.biz.ContacterBiz;
import com.crm.biz.OrderInfoBiz;
import com.crm.biz.OrderItemBiz;

@Service
public class OrderItemBizImpl extends BaseBizImpl implements OrderItemBiz {
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public List<OrderItem> getOrderItemByInfoId(OrderItem orderItem) {
		return baseDao.findAllT(orderItem, "getOrderItemByInfoId");
	}

}
