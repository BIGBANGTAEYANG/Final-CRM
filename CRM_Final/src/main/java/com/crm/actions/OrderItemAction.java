package com.crm.actions;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.crm.bean.OrderItem;
import com.crm.biz.OrderItemBiz;
import com.crm.web.model.JsonModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value = "prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class OrderItemAction extends BaseAction implements ModelDriven<OrderItem>{
	private static final long serialVersionUID = -542837516582508820L;
	
	private OrderItem orderItem;
	private OrderItemBiz orderItemBiz;
	
	private JsonModel jsonModel;
	@Action(value="/list_orderitem")
	public void listOrderInfo() throws IOException{
		jsonModel=new JsonModel();
		if (orderItem.getOrder()!=null && orderItem.getOrder().getId()!=null) {
			List<OrderItem> list=orderItemBiz.getOrderItemByInfoId(orderItem);
			jsonModel.setCode(1);
			jsonModel.setObj(list);
		} else {
			jsonModel.setCode(0);
			jsonModel.setObj("injert failed");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	public OrderItem getModel() {
		orderItem=new OrderItem();
		return orderItem;
	}
	@Resource(name="orderItemBizImpl")
	public void setOrderItemBiz(OrderItemBiz orderItemBiz) {
		this.orderItemBiz = orderItemBiz;
	}
	
}
