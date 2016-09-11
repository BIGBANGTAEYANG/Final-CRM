package com.crm.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.crm.bean.ContactHistory;
import com.crm.biz.ContactHistoryBiz;
import com.crm.web.model.JsonModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value="prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class ContactHistoryAction extends BaseAction implements ModelDriven<ContactHistory>{
	
	private static final long serialVersionUID = 3847077454786371504L;
	
	private ContactHistory contactHistory;
	private JsonModel jsonModel;
	private ContactHistoryBiz contactHistoryBiz;
	
	@Action(value = "/list_contacthistory")
	public void listContactHistory() throws IOException{
		jsonModel=new JsonModel();
		if (contactHistory.getCustomer()!=null && contactHistory.getCustomer().getId()!=null) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("customerid", contactHistory.getCustomer().getId());
			List<ContactHistory> list=contactHistoryBiz.getContacterByCustomerId(map);
			jsonModel.setCode(1);
			jsonModel.setObj(list);
		} else {
			jsonModel.setCode(0);
			jsonModel.setObj("injert fail");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	@Action(value="/insert_contacthistory")
	public void insertContactHistory() throws IOException{
		jsonModel=new JsonModel();
		if (contactHistory.getCustomer()!=null && contactHistory.getCustomer().getId()!=null) {
			contactHistoryBiz.insertContactHistory(contactHistory);
			jsonModel.setCode(1);
		} else {
			jsonModel.setCode(0);
			jsonModel.setObj("injert fail");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	@Action(value="/update_contacthistory")
	public void updateContactHistory() throws IOException{
		jsonModel=new JsonModel();
		if (contactHistory.getCustomer()!=null && contactHistory.getCustomer().getId()!=null) {
			contactHistoryBiz.updateContactHistory(contactHistory);
			jsonModel.setCode(1);
		} else {
			jsonModel.setCode(0);
			jsonModel.setObj("injert fail");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	@Action(value="/delete_contacthistory")
	public void deleteContactHistory() throws IOException{
		jsonModel=new JsonModel();
		if (contactHistory.getId()!=null) {
			contactHistoryBiz.deleteContactHistory(contactHistory);
			jsonModel.setCode(1);
		} else {
			jsonModel.setCode(0);
			jsonModel.setObj("injert fail");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	public ContactHistory getModel() {
		contactHistory=new ContactHistory();
		return contactHistory;
	}
	@Resource(name="contactHistoryBizImpl")
	public void setContactHistoryBiz(ContactHistoryBiz contactHistoryBiz) {
		this.contactHistoryBiz = contactHistoryBiz;
	}
	
}
