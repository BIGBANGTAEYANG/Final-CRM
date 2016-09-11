package com.crm.bizimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.bean.Customer;
import com.crm.bean.DataDirectory;
import com.crm.bean.Service;
import com.crm.biz.ServiceBiz;
import com.crm.web.model.PageModel;

@org.springframework.stereotype.Service
public class ServiceBizImpl extends BaseBizImpl implements ServiceBiz {
	
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public List<Service> getServiceTypeAndCount(Map<String,Object> map) {
		
		List<Service> list =baseDao.findList(Service.class, map, "getServiceTypeAndCount");
		
		return list;
	}

	
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public void deleteSevice(PageModel<Service> pagemodel) {
		baseDao.del(pagemodel.getT().getClass(), pagemodel.getT().getId(), "deleteService");
	}

	
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public List<Customer> getCustomerService() {
		List<Customer> list = baseDao.findAll(Customer.class, "getCustomer");
		return list;
	}


	//创建新服务
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public void createService(PageModel<Service> pagemodel) {
		baseDao.save(pagemodel.getT(),"insertNewService");
	}
	//分配服务
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public void alloService(PageModel<Service> pagemodel) {
		baseDao.update(pagemodel.getT(), "updateService");
	}
	//处理服务
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public void delService(PageModel<Service> pagemodel) {
		baseDao.update(pagemodel.getT(), "updateService");
	}
	//反馈服务
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public void feedbackSerice(PageModel<Service> pagemodel) {
		DataDirectory servicestatus = new DataDirectory();
		if(pagemodel.getT().getSatisify().getId()<43){
			servicestatus.setId(37);
			pagemodel.getT().setServicestatus(servicestatus);
			baseDao.update(pagemodel.getT(), "updateService");
		}else{
			servicestatus.setId(40);
			pagemodel.getT().setServicestatus(servicestatus);
			baseDao.update(pagemodel.getT(), "updateService");
		}
		
	}

	//归档服务
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public PageModel<Service> doneService(PageModel<Service> pagemodel) {
		int count = baseDao.getCountT(pagemodel.getT(),"getAllServiceCountByServiceStatus");
		pagemodel.setTotalCount(count);
		int total = count%pagemodel.getRows()==0?count/pagemodel.getRows():count/pagemodel.getRows()+1;
		pagemodel.setTotal(total);
		int offset = (pagemodel.getPage()-1)*pagemodel.getRows();
		List<Service> list = baseDao.findListT(pagemodel.getT(), "findAllServiceByServiceStatus", offset, pagemodel.getRows());
		pagemodel.setList(list);
		return pagemodel;
	}
	
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	//根据服务状态id得到所有的服务(新创建，已分配，已反馈，已归档)
	public PageModel<Service> getServiceListByPageByServiceStatus(PageModel<Service> pagemodel) {
		int count = baseDao.getCountT(pagemodel.getT(),"getAllServiceCountByServiceStatus");
		pagemodel.setTotalCount(count);
		int total = count%pagemodel.getRows()==0?count/pagemodel.getRows():count/pagemodel.getRows()+1;
		pagemodel.setTotal(total);
		int offset = (pagemodel.getPage()-1)*pagemodel.getRows();
		List<Service> list = baseDao.findListT(pagemodel.getT(), "findAllServiceByServiceStatus", offset, pagemodel.getRows());
		pagemodel.setList(list);
		return pagemodel;
	}
	
	//通过条件查询得到服务列表(条件查询)
	//TODO:页面得到的是客户的名字，但是数据库中是客户的id
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public PageModel<Service> getServiceListByCondition(Map<String,Object> map) {
		
		PageModel<Service> pagemodel = new PageModel<Service>();
		
		if(map.get("customername")!=null){
			Customer customer = new Customer();
			String cname = map.get("customername").toString();
			customer.setCname(cname);
			List<Customer> customerlist = baseDao.findAllT(customer, "getCustomerByName");
			List idlist = new ArrayList();
			for (int i=0;i<customerlist.size();i++) {
				int id = customerlist.get(i).getId();
				idlist.add(id);
			}
			map.put("idlist", idlist);
		}
		
		int count = baseDao.getCount(Service.class, map, "findServiceCountByCondition");
		pagemodel.setTotalCount(count);
		int total =count%pagemodel.getRows()==0?count/pagemodel.getRows():count/pagemodel.getRows()+1;
		pagemodel.setTotal(total);
		int offset =(pagemodel.getPage()-1)*pagemodel.getRows();
		List<Service> list = baseDao.findList(Service.class, map, "findServiceByCondition", offset, pagemodel.getRows());
		pagemodel.setList(list);
		return pagemodel;
	}


}
