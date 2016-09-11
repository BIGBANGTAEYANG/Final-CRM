package com.crm.bizimpl;

import javax.annotation.Resource;

import com.crm.dao.BaseDao;

public abstract class BaseBizImpl {
	protected BaseDao baseDao;

	@Resource(name="baseDaoImpl")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
}
