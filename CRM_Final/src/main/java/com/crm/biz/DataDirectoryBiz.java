package com.crm.biz;

import java.util.List;
import java.util.Map;

import com.crm.bean.DataDirectory;
import com.crm.web.model.PageModel;

public interface DataDirectoryBiz {
	
	public List<DataDirectory> getDataDirectoryAndCustomerCount(Map<String,Object> map);

	
	public List<DataDirectory> getDataDirectoryByType(String dtype);
	/**
	 * 得到所有的数据字典  作为初始化数据
	 * @return
	 */
	public Map<String, List<DataDirectory>> getAllDataDirectory();
	
	/**
	 * 条件查询数据字典
	 */
	public PageModel<DataDirectory> searchDataDirectory(Map<String, Object> map);

	/**
	 * 更新和新增
	 * @param dataDirectory
	 */
	public void updateDataDirectory(DataDirectory dataDirectory);
	public void insertDataDirectory(DataDirectory dataDirectory);

}
