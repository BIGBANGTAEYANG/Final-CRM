package com.crm.bizimpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.bean.Plan;
import com.crm.biz.PlanBiz;

@Service
public class PlanBizImpl extends BaseBizImpl implements PlanBiz {

	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public List<Plan> getPlanList(Plan plan) {
		System.out.println(plan);
		return 	baseDao.findAllT(plan, "getPlanList");
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public void InsertPlan(Plan plan) {
		baseDao.save(plan, "insertPlan");
	}

	
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public void delPlan(Plan plan) {
		baseDao.del(Plan.class, plan.getId(), "deletePlan");
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public void updatePlan(Plan plan) {
		baseDao.update(plan, "updatePlan");
	}


}
