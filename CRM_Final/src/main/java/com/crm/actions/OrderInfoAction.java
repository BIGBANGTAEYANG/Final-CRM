package com.crm.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.crm.bean.OrderInfo;
import com.crm.biz.OrderInfoBiz;
import com.crm.web.model.JsonModel;
import com.crm.web.model.PageJsonModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value = "prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class OrderInfoAction extends BaseAction implements ModelDriven<OrderInfo>{
	private static final long serialVersionUID = -542837516582508820L;
	
	private OrderInfo orderInfo;
	private OrderInfoBiz orderInfoBiz;
	private HashMap<String,Object> map = new HashMap<String, Object>();
	private PageJsonModel pagejsonModel = new PageJsonModel();
	private JsonModel jsonModel;
	
	@Action(value="/getOrderInfoAndSumPrice")
	public void getOrderInfoAndSumPrice() throws IOException{
		try {
			map.put("createtime", orderInfo.getCreatetime());
			map.put("customername", orderInfo.getCustomername());
			pagejsonModel.setCode(1);
			pagejsonModel.setRows(orderInfoBiz.getOrderInfoAndSumprice(map));
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("获取失败，原因"+e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}

	
	@Action(value="/list_orderinfo")
	public void listOrderInfo() throws IOException{
		jsonModel=new JsonModel();
		if (orderInfo.getCustomer()!=null && orderInfo.getCustomer().getId()!=null) {
			List<OrderInfo> list=orderInfoBiz.getOrderInfoByCustomerId(orderInfo);
			jsonModel.setCode(1);
			jsonModel.setObj(list);
		} else {
			jsonModel.setCode(0);
			jsonModel.setObj("injert failed");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	public OrderInfo getModel() {
		orderInfo=new OrderInfo();
		return orderInfo;
	}
	@Resource(name="orderInfoBizImpl")
	public void setOrderInfoBiz(OrderInfoBiz orderInfoBiz) {
		this.orderInfoBiz = orderInfoBiz;
	}
}
