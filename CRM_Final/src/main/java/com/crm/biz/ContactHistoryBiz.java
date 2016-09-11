package com.crm.biz;

import java.util.List;
import java.util.Map;

import com.crm.bean.ContactHistory;

public interface ContactHistoryBiz {
	/**
	 * 根据客户id查询联系历史
	 * @param contactHistory
	 * @return
	 */
	public List<ContactHistory> getContacterByCustomerId(Map<String, Object> map);
	/**
	 * 新建联系历史
	 * @param contactHistory
	 */
	public void insertContactHistory(ContactHistory contactHistory);
	/**
	 * 更新联系历史
	 * @param contactHistory
	 */
	public void updateContactHistory(ContactHistory contactHistory); 
	/**
	 * 删除联系历史
	 * @param contactHistory
	 */
	public void deleteContactHistory(ContactHistory contactHistory); 
}
