package com.crm.biz;

import java.util.List;
import java.util.Map;

import com.crm.bean.Contacter;

public interface ContacterBiz {
	public void saveContacter(Contacter contacter);
	
	/**
	 * 通过Contactr中customerid查询联系人
	 * @param contacter
	 * @return
	 */
	public List<Contacter> getContacterByCustomerId(Map<String, Object> map);

	/**
	 * 保存联系人
	 * @param contacter
	 */
	public boolean saveContacterDetail(Contacter contacter);
	/**
	 * 更新联系人
	 * @param contacter
	 * @return
	 */
	public void updateContacterDetail(Contacter contacter);
	/**
	 * 删除联系人
	 * @param contacter
	 * @return
	 */
	public void deleteContacterDetail(Contacter contacter);
}
