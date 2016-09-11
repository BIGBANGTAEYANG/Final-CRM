package com.crm.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.crm.bean.DataDirectory;
import com.crm.bean.Service;
import com.crm.bean.UserInfo;
import com.crm.biz.ServiceBiz;
import com.crm.web.model.PageJsonModel;
import com.crm.web.model.PageModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value="prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class ServiceAction extends BaseAction implements ModelDriven<PageModel<Service>>{
	private static final long serialVersionUID = 6526511683710480858L;
	private PageJsonModel pagejsonModel = new PageJsonModel();
	private PageModel<Service> pagemodel;
	private Map<String,Object> session = ActionContext.getContext().getSession();
	private HashMap<String,Object> map = new HashMap<String, Object>();
	private ServiceBiz serviceBiz;
	
	//TODO:
	@Action(value="/getServiceTypeAndCount")
	public void getServiceTypeAndCount() throws IOException{
		try {
			map.put("createtime", pagemodel.getT().getCreatetime());
			pagejsonModel.setCode(1);
			pagejsonModel.setRows(serviceBiz.getServiceTypeAndCount(map));
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("获取服务失败，原因"+e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}

	
	
	@Action(value="/getServiceDetail")
	public void getServiceDetail() throws IOException{
		
		
			if(session.get("ServiceDetail")!=null){
				List<Service> list = (List<Service>) session.get("ServiceDetail");
				for (Service service : list) {
					if(service.getId()==pagemodel.getT().getId()){
					pagejsonModel.setCode(1);
					pagejsonModel.setRows(service);
					}
				}
			}
			else{
				try {
					List<Service> list = serviceBiz.getServiceListByPageByServiceStatus(pagemodel).getList();
					for (Service service : list) {
						if(service.getId()==pagemodel.getT().getId()){
							pagejsonModel.setRows(service);
							pagejsonModel.setCode(1);
						}
					}
				} catch (Exception e) {
					pagejsonModel.setCode(0);
					pagejsonModel.setMsg("获取已分配的服务失败，原因"+e.getMessage());
				}
			}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}



	
	@Action(value="/deleteSerice")
	public void deleteSerice() throws IOException{
		try {
			serviceBiz.deleteSevice(pagemodel);
			pagejsonModel.setCode(1);
			pagejsonModel.setMsg("删除服务成功");
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("删除服务失败，原因"+e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	

	
	@Action(value="/getServiceCustomer")
	public void getServiceCustomer() throws IOException{
		try {
			pagejsonModel.setCode(1);
			pagejsonModel.setRows(serviceBiz.getCustomerService());
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("获取客户信息失败，原因"+e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}

	
	@Action(value="/getServiceType")
	public void getServiceType() throws IOException{
		 List<DataDirectory> servicetypelist = (( Map<String, List<DataDirectory>>)(ServletActionContext.getServletContext().getAttribute("dataDirectory"))).get("servicetype");
		 pagejsonModel.setCode(1); 
		 pagejsonModel.setRows(servicetypelist);
		 super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	
	@Action(value="/getServiceByCondition")
	public void getServiceByCondition() throws IOException{
		try {
			map.put("customername", pagemodel.getT().getCustomer().getCname());
			map.put("summary", pagemodel.getT().getSummary());
			map.put("servicestatus", pagemodel.getT().getServicestatus().getId());
			map.put("servicetype", pagemodel.getT().getServicetype().getId());
			map.put("servicemindateString", pagemodel.getT().getServicemindateString());
			map.put("servicemaxdateString", pagemodel.getT().getServicemaxdateString());
			pagejsonModel.setCode(1);
			pagejsonModel.setTotal(serviceBiz.getServiceListByCondition(map).getTotalCount());
			pagejsonModel.setRows(serviceBiz.getServiceListByCondition(map).getList());
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("获取新创建的服务失败，原因"+e.getMessage());
			e.printStackTrace();
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	
	
	
	@Action(value="/createNewService")
	public void  createNewService() throws IOException{
		try {
			serviceBiz.createService(pagemodel);
			pagejsonModel.setCode(1);
			pagejsonModel.setMsg("创建新服务成功");
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("创建新服务失败，原因:"+e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	
	@Action(value="/getNewCreateService")
	public void getNewCreateService() throws IOException{
		try {
			pagejsonModel.setCode(1);
			pagejsonModel.setTotal(serviceBiz.getServiceListByPageByServiceStatus(pagemodel).getTotalCount());
			pagejsonModel.setRows(serviceBiz.getServiceListByPageByServiceStatus(pagemodel).getList());
			session.put("ServiceDetail", serviceBiz.getServiceListByPageByServiceStatus(pagemodel).getList());
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("获取新创建的服务失败，原因"+e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	
	
	@Action(value="/allotService")
	public void allotService() throws IOException{
		try {
			serviceBiz.alloService(pagemodel);
			pagejsonModel.setCode(1);
			pagejsonModel.setMsg("分配服务成功");
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("分配服务失败，原因"+e.getMessage());
			e.printStackTrace();
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	
	@Action(value="/getAllotedService")
	public void getAllotedService() throws IOException{
		try {
			pagejsonModel.setCode(1);
			pagejsonModel.setTotal(serviceBiz.getServiceListByPageByServiceStatus(pagemodel).getTotalCount());
			pagejsonModel.setRows(serviceBiz.getServiceListByPageByServiceStatus(pagemodel).getList());
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("获取已分配的服务失败，原因"+e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	
	@Action(value="/DelService")
	public void DelService() throws IOException{
		try {
			serviceBiz.delService(pagemodel);
			pagejsonModel.setCode(1);
			pagejsonModel.setMsg("处理服务成功");
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("处理服务失败，原因"+e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	
	@Action(value="/getDeledService")
	public void getDeledService() throws IOException{
		try {
			pagejsonModel.setCode(1);
			pagejsonModel.setTotal(serviceBiz.getServiceListByPageByServiceStatus(pagemodel).getTotalCount());
			pagejsonModel.setRows(serviceBiz.getServiceListByPageByServiceStatus(pagemodel).getList());
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("获取已处理的服务失败，原因"+e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	
	@Action(value="/feedbackSerice")
	public void feedbackSerice() throws IOException{
		try {
			serviceBiz.feedbackSerice(pagemodel);
			pagejsonModel.setCode(1);
			pagejsonModel.setMsg("反馈服务成功");
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("反馈服务失败，原因"+e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	
	
	@Action(value="/getfeedbackedService")
	public void getfeedbackedService() throws IOException{
		try {
			pagejsonModel.setCode(1);
			pagejsonModel.setTotal(serviceBiz.getServiceListByPageByServiceStatus(pagemodel).getTotalCount());
			pagejsonModel.setRows(serviceBiz.getServiceListByPageByServiceStatus(pagemodel).getList());
			session.put("AllfeedbackedServiceList", serviceBiz.getServiceListByPageByServiceStatus(pagemodel).getList());
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("获取已反馈的服务失败，原因"+e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	
	
	
	public PageModel<Service> getModel() {
		pagemodel = new PageModel<Service>(new Service());
		return pagemodel;
	}

	@Resource(name="serviceBizImpl")
	public void setServicebiz(ServiceBiz serviceBiz) {
		this.serviceBiz = serviceBiz;
	}

}
