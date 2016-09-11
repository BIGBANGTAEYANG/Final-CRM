package com.crm.biz;


import java.util.Map;

import com.crm.bean.Lost;
import com.crm.web.model.PageModel;

public interface LostBiz {
	public PageModel<Lost> getLostBean(PageModel<Lost> pageModel);
	public PageModel<Lost> searchLost(Map<String, Object> map);
	/**
	 * 按limitday为间隔查找所有的lost
	 * @param limiteday
	 */
	public void vilateLost(Integer limitday);
	public void updateLost(Lost lost);
	
	public PageModel<Lost> customerLostAnalysis(Map<String, Object> map);

}
