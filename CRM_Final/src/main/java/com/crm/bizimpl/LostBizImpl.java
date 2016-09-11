package com.crm.bizimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.bean.Customer;
import com.crm.bean.Lost;
import com.crm.bean.OrderInfo;
import com.crm.biz.LostBiz;
import com.crm.web.model.PageModel;
@Service
public class LostBizImpl extends BaseBizImpl implements LostBiz{
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public PageModel<Lost> customerLostAnalysis(Map<String, Object> map) {
		// TODO Auto-generated method stub
		PageModel<Lost> lostModel=new PageModel<Lost>();
		
		int currPage = Integer.parseInt(map.get("page").toString());
		int sizePage = Integer.parseInt(map.get("rows").toString());
		
		int count=baseDao.getCount(Lost.class,map, "getLostCount");
		lostModel.setTotalCount(count);
		
		int total=count % lostModel.getRows() == 0 ? count / lostModel.getRows() : count / lostModel.getRows() + 1;
		lostModel.setTotal(total);
		
		int offset = (currPage - 1) * sizePage;
		List<Lost> list=baseDao.findList(Lost.class, map, "customerLostAnalysis", offset, sizePage);
		lostModel.setList(list);
		lostModel.setPage(currPage);
		return lostModel;
	}

	
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public PageModel<Lost> getLostBean(PageModel<Lost> lostModel) {
		int count=baseDao.getCount(Lost.class, "getLostCount");
		lostModel.setTotalCount(count);
		
		int total=count % lostModel.getRows() == 0 ? count / lostModel.getRows() : count / lostModel.getRows() + 1;
		lostModel.setTotal(total);
		
		int offset = (lostModel.getPage() - 1) * lostModel.getRows();
		List<Lost> list=baseDao.findAll(Lost.class, "getAllLost");
		lostModel.setList(list);
		
		return lostModel;
	}
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public PageModel<Lost> searchLost(Map<String, Object> map) {
		PageModel<Lost> lostModel=new PageModel<Lost>();
		
		int currPage = Integer.parseInt(map.get("page").toString());
		int sizePage = Integer.parseInt(map.get("rows").toString());
		
		int count=baseDao.getCount(Lost.class,map, "getLostCountCondition");
		lostModel.setTotalCount(count);
		
		int total=count % lostModel.getRows() == 0 ? count / lostModel.getRows() : count / lostModel.getRows() + 1;
		lostModel.setTotal(total);
		
		int offset = (currPage - 1) * sizePage;
		List<Lost> list=baseDao.findList(Lost.class, map, "getLostCondition", offset, sizePage);
		lostModel.setList(list);
		lostModel.setPage(currPage);
		return lostModel;
	}
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public void vilateLost(Integer limitday) {
		//查找到所有过期的orderinfo
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("limitday", limitday);
		List<Integer> infoid = baseDao.findList(OrderInfo.class, map, "getOrderInfoOverDate");
		System.out.println(infoid);
		//客户状态设置为预警
		List<Integer> lostInfoId=baseDao.findAll(Lost.class, "getLostOrderInfoId");
		System.out.println(lostInfoId);
		//两个list取并集并去重
		infoid.removeAll(lostInfoId);
		if (!infoid.isEmpty()) {
			//对customer更新设置参数
			map.put("customerstatus", 22);
			map.put("ids", infoid);
			baseDao.update(Customer.class, "updateCustomerStatus", map);
			System.out.println(infoid);
			for (Integer id : infoid) {
				baseDao.save(Lost.class, "insertLostByOrderInfo",id);
			}
		} else {
			System.out.println("没有新客户流失");
		}
		infoid.clear();
		lostInfoId.clear();
	}
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public void updateLost(Lost lost){
		//用户输入了流失原因
		Map<String, Object> map=new HashMap<String, Object>();
		List<Integer> ids=new ArrayList<Integer>();
		ids.add(lost.getOrderInfo().getCustomer().getId());
		map.put("id", lost.getId());
		map.put("ids", ids);
		if (lost.getLostreason()!=null && !"".equals(lost.getLostreason())) {
			map.put("lostreason", lost.getLostreason());
			baseDao.update(Lost.class, "updateLost", map);
			map.put("customerstatus", 24);
		}else{
			map.put("measure", lost.getMeasure());
			map.put("addmeasure", lost.getAddmeasure());
			baseDao.update(Lost.class, "updateLost", map);
			map.put("customerstatus", 23);
		}
		baseDao.update(Customer.class, "updateCustomerStatus", map);
	}
}
