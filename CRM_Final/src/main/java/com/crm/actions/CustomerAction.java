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
import com.crm.bean.Customer;
import com.crm.bean.DataDirectory;
import com.crm.bean.UserInfo;
import com.crm.biz.CustomerBiz;
import com.crm.biz.DataDirectoryBiz;
import com.crm.biz.UserInfoBiz;
import com.crm.web.model.JsonModel;
import com.crm.web.model.PageJsonModel;
import com.crm.web.model.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value = "prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class CustomerAction extends BaseAction implements ModelDriven<PageModel<Customer>> {

	private static final long serialVersionUID = 5424400354643222777L;
	private PageModel<Customer> pageModel;
	private PageJsonModel pagejsonModel;
	private JsonModel jsonModel;
	private CustomerBiz customerBiz;
	private DataDirectoryBiz dataDirectoryBiz;
	private UserInfoBiz userinfobiz;
	
	

	@Action(value = "/list_customer")
	public void list() throws IOException {
		pagejsonModel = new PageJsonModel();
		List<Customer> chancelist = customerBiz.getCustomerBean(pageModel).getList();
		Integer total = customerBiz.getCustomerBean(pageModel).getTotalCount();
		pagejsonModel.setTotal(total);
		pagejsonModel.setRows(chancelist);
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}

	@Action(value = "/customer_search")
	public void search() throws IOException {
		jsonModel = new JsonModel();
		pagejsonModel = new PageJsonModel();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", pageModel.getPage());
		map.put("rows", pageModel.getRows());

		map.put("id", pageModel.getT().getId());
		map.put("cname", pageModel.getT().getCname());
		// TODO ：解决属性必须new的问题
		map.put("district", pageModel.getT().getDistrict().getId());
		map.put("customermanagername", pageModel.getT().getCustomermanager().getName());
		map.put("grade", pageModel.getT().getGrade().getId());

		pageModel = customerBiz.searchCustomer(map);
		pagejsonModel.setTotal(pageModel.getTotalCount());
		pagejsonModel.setRows(pageModel.getList());

		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}

	@Action(value = "/list_select")
	public void customerGrade() throws IOException {
		jsonModel = new JsonModel();
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserInfo> list = userinfobiz.getUserInfoXiaoShou();
		// 组装列表数据
		List<DataDirectory> grade = dataDirectoryBiz.getDataDirectoryByType("grade");
		List<DataDirectory> district = dataDirectoryBiz.getDataDirectoryByType("district");
		List<DataDirectory> satisfaction = dataDirectoryBiz.getDataDirectoryByType("satisfaction");
		List<DataDirectory> credit = dataDirectoryBiz.getDataDirectoryByType("credit");
		List<DataDirectory> post = dataDirectoryBiz.getDataDirectoryByType("post");

		map.put("customerman", list);

		map.put("grade", grade);
		map.put("district", district);
		map.put("satisfaction", satisfaction);
		map.put("credit", credit);
		map.put("post", post);
		if (map != null && !map.isEmpty()) {
			jsonModel.setCode(1);
			jsonModel.setObj(map);
		} else {
			jsonModel.setCode(0);
			jsonModel.setMsg("select is empty");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}

	@Action(value = "/list_customerbyid")
	public void listData() throws IOException {
		jsonModel = new JsonModel();
		if (pageModel.getT().getId() != null) {
			Customer c = customerBiz.getCustomerById(pageModel.getT());
			jsonModel.setCode(1);
			jsonModel.setObj(c);
		} else {
			jsonModel.setCode(0);
			jsonModel.setMsg("id is null");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	@Action(value="/save_customerbyid")
	public void updateCustomer() throws IOException {
		jsonModel = new JsonModel();
		Customer c=pageModel.getT();
		if (c != null) {
			customerBiz.updateCustomer(c);
			jsonModel.setCode(1);
		}else{
			jsonModel.setCode(0);
			jsonModel.setMsg("update customer failed");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	@Action(value="/delete_customerbyid")
	public void deleteCustomer() throws IOException{
		jsonModel=new JsonModel();
		Customer c=pageModel.getT();
		if (c!=null && c.getId()!=null) {
			customerBiz.deleteCustomer(c);
			jsonModel.setCode(1);
		}else{
			jsonModel.setCode(0);
			jsonModel.setMsg("injert failed");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	public PageModel<Customer> getModel() {
		pageModel = new PageModel<Customer>(new Customer());
		return pageModel;
	}

	@Resource(name = "customerBizImpl")
	public void setCustomerBiz(CustomerBiz customerBiz) {
		this.customerBiz = customerBiz;
	}

	@Resource(name = "dataDirectoryBizImpl")
	public void setDataDirectoryBiz(DataDirectoryBiz dataDirectoryBiz) {
		this.dataDirectoryBiz = dataDirectoryBiz;
	}

	@Resource(name = "userInfoBizImpl")
	public void setUserinfobiz(UserInfoBiz userinfobiz) {
		this.userinfobiz = userinfobiz;
	}
}
