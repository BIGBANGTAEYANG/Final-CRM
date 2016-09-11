package com.crm.biz;

import java.util.List;

import com.crm.bean.Plan;

public interface PlanBiz {
	public List<Plan> getPlanList(Plan plan);
	
	public void InsertPlan(Plan plan);
	
	public void delPlan(Plan plan);
	
	public void updatePlan(Plan plan);
}
