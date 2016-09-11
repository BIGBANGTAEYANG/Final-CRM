package com.crm.biz;

import java.util.Map;

import com.crm.bean.ProductInfo;
import com.crm.web.model.PageModel;

public interface ProductInfoBiz {
	public PageModel<ProductInfo> searchProductInfo(Map<String, Object> map);
}
