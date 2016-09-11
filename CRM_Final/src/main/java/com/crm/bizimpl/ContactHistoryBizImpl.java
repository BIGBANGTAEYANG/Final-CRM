package com.crm.bizimpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.bean.ContactHistory;
import com.crm.biz.ContactHistoryBiz;

@Service
public class ContactHistoryBizImpl extends BaseBizImpl implements ContactHistoryBiz {

	@Transactional(readOnly = true, isolation = Isolation.DEFAULT, rollbackForClassName = ("java.lang.RuntimeException"), propagation = Propagation.NOT_SUPPORTED)
	public List<ContactHistory> getContacterByCustomerId(Map<String, Object> map) {
		return baseDao.findList(ContactHistory.class, map, "getContactHistoryByCustomerId");
	}
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, rollbackForClassName = ("java.lang.RuntimeException"), propagation = Propagation.NOT_SUPPORTED)
	public void insertContactHistory(ContactHistory contactHistory) {
		baseDao.save(contactHistory, "saveContactHistory");
	}
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, rollbackForClassName = ("java.lang.RuntimeException"), propagation = Propagation.NOT_SUPPORTED)
	public void updateContactHistory(ContactHistory contactHistory) {
		baseDao.update(contactHistory, "updateContactHistory");
	}
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, rollbackForClassName = ("java.lang.RuntimeException"), propagation = Propagation.NOT_SUPPORTED)
	public void deleteContactHistory(ContactHistory contactHistory) {
		baseDao.del(ContactHistory.class, contactHistory.getId(), "deleteContactHistory");
	}
}
