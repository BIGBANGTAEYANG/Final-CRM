package com.crm.bizimpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.bean.Chance;
import com.crm.bean.ContactHistory;
import com.crm.bean.Contacter;
import com.crm.bean.Customer;
import com.crm.bean.Lost;
import com.crm.bean.OrderInfo;
import com.crm.bean.OrderItem;
import com.crm.bean.Plan;
import com.crm.biz.CustomerBiz;
import com.crm.web.model.PageModel;

@Service
public class CustomerBizImpl extends BaseBizImpl implements CustomerBiz {
	
	
	
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT, rollbackForClassName = ("java.lang.RuntimeException"), propagation = Propagation.NOT_SUPPORTED)
	public PageModel<Customer> getCustomerBean(PageModel<Customer> customerModel) {
		// 查询总记录数
		int count = baseDao.getCount(Customer.class, "getCustomerCount");

		customerModel.setTotalCount(count);
		// 计算总页数
		int total = count % customerModel.getRows() == 0 ? count / customerModel.getRows()
				: count / customerModel.getRows() + 1;
		customerModel.setTotal(total);
		// 偏移量：第几条记录开始查询
		int offset = (customerModel.getPage() - 1) * customerModel.getRows();
		List<Customer> list = baseDao.findList(Customer.class, null, "getCustomer", offset, customerModel.getRows());
		customerModel.setList(list);
		return customerModel;
	}
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT, rollbackForClassName = ("java.lang.RuntimeException"), propagation = Propagation.NOT_SUPPORTED)
	public PageModel<Customer> searchCustomer(Map<String, Object> map) {
		PageModel<Customer> customerModel = new PageModel<Customer>();

		int currPage = Integer.parseInt(map.get("page").toString());
		int sizePage = Integer.parseInt(map.get("rows").toString());

		int count = baseDao.getCount(Customer.class, map, "findCustomerConditionCount");
		customerModel.setTotalCount(count);

		int total = count % sizePage == 0 ? count / sizePage : count / sizePage + 1;
		customerModel.setTotal(total);
		int offset = (currPage - 1) * sizePage;
		List<Customer> cs=baseDao.findList(Customer.class, map, "findCustomerCondition", offset, sizePage);
		customerModel.setList(cs);
		customerModel.setPage(currPage);
		return customerModel;
	}
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT, rollbackForClassName = ("java.lang.RuntimeException"), propagation = Propagation.NOT_SUPPORTED)
	public Customer getCustomerById(Customer customer) {
		Customer c=(Customer) baseDao.find(customer, "getCustomerById");
		return c;
	}
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, rollbackForClassName = ("java.lang.RuntimeException"), propagation = Propagation.NOT_SUPPORTED)
	public void updateCustomer(Customer customer) {
		baseDao.update(customer, "updateCustomerById");
	}
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, rollbackForClassName = ("java.lang.RuntimeException"), propagation = Propagation.NOT_SUPPORTED)
	public void deleteCustomer(Customer customer) {
		// 根据id查询出来客户名 用来根据客户名删除customer
		customer = (Customer) baseDao.find(customer, "getCustomerByIdWithoutMap");
		int id = customer.getId();
		String cname = customer.getCname();
		// 根据customerid删除service
		baseDao.del(com.crm.bean.Service.class, id, "deleteServiceByCustomerid");
		// 根据customerid删除ContactHistory
		baseDao.del(ContactHistory.class, id, "deleteContactHistoryByContactHistory");
		// 删除lost
		// 1.根据customerid找到所有的orderinfo
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCustomer(customer);
		List<OrderInfo> infos = baseDao.findAllT(orderInfo, "getOrderInfoByCustomerId");
		// 2.根据orderinfo的id找到所有的lost和orderitem并删除
		if (infos != null && !infos.isEmpty()) {
			// 迭代infos删除
			for (OrderInfo info : infos) {
				// 根据info的id找到所有的lost和orderitem
				Lost losttmp = new Lost();
				losttmp.setOrderInfo(info);
				List<Lost> losts = baseDao.findAllT(losttmp, "getLostByOrderInfoId");
				// 迭代losts并删除
				if (losts != null && !losts.isEmpty()) {
					for (Lost lost : losts) {
						baseDao.del(Lost.class, lost.getId(), "deleteLostById");
					}
				}
				OrderItem itemtmp = new OrderItem();
				itemtmp.setOrder(info);
				List<OrderItem> items = baseDao.findAllT(itemtmp, "getOrderItemByInfoId");
				// 迭代items并删除
				if (items != null && !items.isEmpty()) {
					for (OrderItem item : items) {
						baseDao.del(OrderItem.class, item.getId(), "deleteOrderItemById");
					}
				}
			}
		}
		// 最后删除orderInfo
		baseDao.del(OrderInfo.class, id, "deleteOrderInfoByCustomerId");

		Chance chance = new Chance();
		chance.setCustomername(cname);
		//根据名称查询到所有的Chance
		List<Chance> chances=baseDao.findAllT(chance, "getChanceByCustomerName");
		//根据chance的id找到所有plan
		if (chances!=null && !chances.isEmpty()) {
			//迭代chances删除
			for (Chance chan : chances) {
				//根据chan的id找到所有的plan
				Plan plantmp=new Plan();
				plantmp.setChance(chan);
				List<Plan> plans=baseDao.findAllT(plantmp, "getPlanByChanceId");
				//迭代plans并删除
				if (plans!=null && !plans.isEmpty()) {
					for (Plan plan : plans) {
						baseDao.del(Plan.class, plan.getId(), "deletePlan");
					}
				}
			}
		}
		//最后删除chance
		baseDao.del(Chance.class, cname, "deleteChanceByCustomerName");
		//根据id删除contacter
		baseDao.del(Contacter.class, id, "deleteContacterByCustomerId");
		//根据id删除customer
		baseDao.del(Customer.class, id, "deleteCustomerById");
	}


}
