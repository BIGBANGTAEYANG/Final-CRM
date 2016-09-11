package com.crm.biz;

import java.util.List;
import java.util.Map;

import com.crm.bean.Customer;
import com.crm.bean.Service;
import com.crm.web.model.PageModel;

public interface ServiceBiz {
	
	public List<Service> getServiceTypeAndCount(Map<String,Object> map);

	
	public List<Customer> getCustomerService();

	public void  deleteSevice(PageModel<Service> pagemodel);
	
	public PageModel<Service> getServiceListByCondition(Map<String,Object> map);
	
	public PageModel<Service> getServiceListByPageByServiceStatus(PageModel<Service >pagemodel);
	
	public void createService(PageModel<Service >pagemodel);
	
	public void alloService(PageModel<Service >pagemodel);
	
	public void delService(PageModel<Service >pagemodel);
	
	public void feedbackSerice(PageModel<Service >pagemodel);
	
	public PageModel<Service> doneService(PageModel<Service >pagemodel);
}
