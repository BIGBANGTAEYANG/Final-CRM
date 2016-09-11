package com.crm.biz;

import java.util.Map;

import com.crm.bean.Customer;
import com.crm.web.model.PageModel;

public interface CustomerBiz {
	
	
	
	/**
	 * 分页查询customer
	 * 
	 * @param customerModel
	 * @return
	 */
	public PageModel<Customer> getCustomerBean(PageModel<Customer> customerModel);

	/**
	 * 动态sql查询
	 * 
	 * @param map
	 * @return
	 */
	public PageModel<Customer> searchCustomer(Map<String, Object> map);/**
	 * 根据id查询顾客
	 * @param id
	 * @return
	 */
	public Customer getCustomerById(Customer customer);
	/**
	 * 修改customer
	 * @param customer
	 */
	public void updateCustomer(Customer customer);
	/**
	 * 根据id或名称删除顾客
	 * @param customer
	 */
	public void deleteCustomer(Customer customer);
}
