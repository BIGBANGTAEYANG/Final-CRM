package com.crm.bizimpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.bean.Contacter;
import com.crm.biz.ContacterBiz;

@Service
public class ContacterBizImpl extends BaseBizImpl implements ContacterBiz {

	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public void saveContacter(Contacter contacter) {
		baseDao.save(contacter, "saveContacter");
	}
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public List<Contacter> getContacterByCustomerId(Map<String, Object> map) {
		return baseDao.findList(Contacter.class, map, "getContacterByCustomerId");
	}
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, rollbackForClassName = ("java.lang.RuntimeException"), propagation = Propagation.REQUIRED)
	public boolean saveContacterDetail(Contacter contacter) {
		Contacter c = (Contacter) baseDao.find(contacter, "getContacterByName");
		if (c != null) {
			return false;
		} else {
			baseDao.save(contacter, "saveContacterDetail");
			return true;
		}
	}
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, rollbackForClassName = ("java.lang.RuntimeException"), propagation = Propagation.REQUIRED)
	public void updateContacterDetail(Contacter contacter) {
		baseDao.update(contacter, "updateContacterDetail");
	}
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, rollbackForClassName = ("java.lang.RuntimeException"), propagation = Propagation.REQUIRED)
	public void deleteContacterDetail(Contacter contacter) {
		baseDao.del(Contacter.class, contacter.getId(), "deleteContacterDetail");
	}
}
