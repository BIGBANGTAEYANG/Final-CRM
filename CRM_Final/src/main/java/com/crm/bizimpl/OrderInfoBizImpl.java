package com.crm.bizimpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.bean.Contacter;
import com.crm.bean.OrderInfo;
import com.crm.biz.ContacterBiz;
import com.crm.biz.OrderInfoBiz;

@Service
public class OrderInfoBizImpl extends BaseBizImpl implements OrderInfoBiz {
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public List<OrderInfo> getOrderInfoByCustomerId(OrderInfo orderInfo) {
		return baseDao.findAllT(orderInfo, "getOrderInfoByCustomerId");
	}
	
	//TODO:
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public List<OrderInfo> getOrderInfoAndSumprice(Map<String, Object> map) {
		List<OrderInfo> list  = baseDao.findList(OrderInfo.class, map, "getOrderInfoAndSumPrice");
		return list;
	}

}
