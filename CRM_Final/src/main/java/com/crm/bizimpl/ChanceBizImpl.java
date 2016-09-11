package com.crm.bizimpl;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.bean.Chance;
import com.crm.bean.Contacter;
import com.crm.bean.Customer;
import com.crm.bean.UserInfo;
import com.crm.biz.ChanceBiz;
import com.crm.web.model.PageModel;

@Service
public class ChanceBizImpl extends BaseBizImpl implements ChanceBiz {
	
	public void deleteChanceById(PageModel<Chance> pagemodel) {
		baseDao.del(pagemodel.getT().getClass(), pagemodel.getT().getId(), "deleteChanceById");
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public void updateChanceStatusAndInsertCustomer(PageModel<Chance> pagemodel) {
		baseDao.update(pagemodel.getT(),"updateChance");
		Customer customer = new Customer();
		customer.setCname(pagemodel.getT().getCustomername());
		UserInfo userinfo = (UserInfo) ServletActionContext.getRequest().getSession().getAttribute("loginuser");
		customer.setCustomermanager(userinfo);
		baseDao.save(customer, "insertCustomer");
	}

	
	//更新机会
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public boolean updateChanceUserInfo(PageModel<Chance> pagemodel) {
		if(pagemodel.getT().getContacter()==null&&"".equals(pagemodel.getT().getContacter())){
			baseDao.update(pagemodel.getT(),"updateChance");
			baseDao.save(pagemodel.getT().getContacter(),"saveContacter");
			return true;
		}else{
			
			if(pagemodel.getT().getUser().getId()==null){
				Contacter contacter=(Contacter) baseDao.find(pagemodel.getT().getContacter(), "getContacterByName");
				if (contacter!=null) {
					return false;
				}else{
					baseDao.update(pagemodel.getT(),"updateChance");
					baseDao.save(pagemodel.getT().getContacter(),"saveContacter");
					return true;
				}
			
			}else{
				baseDao.update(pagemodel.getT(),"updateChance");
				return true;
			}
		}
	}


	//条件查询
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public PageModel<Chance> FindChanceByCondition(Map<String,Object> map) {
		PageModel<Chance> pagemodel = new PageModel<Chance>();
		int count = baseDao.getCount(Chance.class, map, "findChanceByConditionCount");
		pagemodel.setTotalCount(count);
		int total =count%pagemodel.getRows()==0?count/pagemodel.getRows():count/pagemodel.getRows()+1;
		pagemodel.setTotal(total);
		int offset =(pagemodel.getPage()-1)*pagemodel.getRows();
		List<Chance> list = baseDao.findList(Chance.class, map, "findChanceByCondition", offset, pagemodel.getRows());
		pagemodel.setList(list);
		return pagemodel;
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public boolean InsertChance(PageModel<Chance> pagemodel) {
		if(pagemodel.getT().getContacter().getCname()==null||"".equals(pagemodel.getT().getContacter().getCname())){
			baseDao.save(pagemodel.getT(), "savechance");
			return true;
		}else{
			
		Contacter contacter=(Contacter) baseDao.find(pagemodel.getT().getContacter(), "getContacterByName");
		if (contacter!=null) {
			return false;
		}else{
			baseDao.save(pagemodel.getT(), "savechance");
			baseDao.save(pagemodel.getT().getContacter(),"saveContacter");
			return true;
		}
		}
	}

	
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public List<Chance> FindChanceList(PageModel<Chance> pagemodel) {
		System.out.println(pagemodel.getT());
		List<Chance> list = baseDao.findAllT(pagemodel.getT(), "findChanceByChancechancestatus");
		return list;
	}
	
	
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public PageModel<Chance> FindChanceListPage(PageModel<Chance> pagemodel){
		int count = baseDao.getCountT(pagemodel.getT(),"getChanceCount");
		pagemodel.setTotalCount(count);
		int total = count%pagemodel.getRows()==0?count/pagemodel.getRows():count/pagemodel.getRows()+1;
		pagemodel.setTotal(total);
		int offset = (pagemodel.getPage()-1)*pagemodel.getRows();
		List<Chance> list = baseDao.findListT(pagemodel.getT(), "findChanceByChancechancestatus", offset, pagemodel.getRows());
		pagemodel.setList(list);
		
		return pagemodel;
	}

	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public Chance FindDetailChance(PageModel<Chance> pagemodel) {
		return (Chance) baseDao.find(pagemodel.getT(), "getChanceDetail");
	}


	public List<Chance> getAllChance() {
		return baseDao.findAll(Chance.class,"findAllChance");
	}


}
