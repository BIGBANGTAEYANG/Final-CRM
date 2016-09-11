package com.crm.biz;

import java.util.List;
import java.util.Map;

import com.crm.bean.Chance;
import com.crm.web.model.PageModel;

public interface ChanceBiz {
	
	public void updateChanceStatusAndInsertCustomer(PageModel<Chance> pagemodel);

	
	public List<Chance> getAllChance();
	
	public  void deleteChanceById(PageModel<Chance> pagemodel);

	public List<Chance> FindChanceList(PageModel<Chance> pagemodel);
	
	public PageModel<Chance> FindChanceListPage(PageModel<Chance> pagemodel);
	
	public Chance FindDetailChance(PageModel<Chance> pagemodel);

	public  boolean updateChanceUserInfo(PageModel<Chance> pagemodel);

	public boolean InsertChance(PageModel<Chance> pagemodel);

	public PageModel<Chance> FindChanceByCondition(Map<String,Object> map);
	
	

	
}
