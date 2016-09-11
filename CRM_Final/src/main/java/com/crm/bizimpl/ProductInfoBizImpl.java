package com.crm.bizimpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.bean.ProductInfo;
import com.crm.biz.ProductInfoBiz;
import com.crm.web.model.PageModel;

@Service
public class ProductInfoBizImpl extends BaseBizImpl implements ProductInfoBiz {
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT, rollbackForClassName = ("java.lang.RuntimeException"), propagation = Propagation.NOT_SUPPORTED)
	public PageModel<ProductInfo> searchProductInfo(Map<String, Object> map) {
		PageModel<ProductInfo> productModel = new PageModel<ProductInfo>();

		int currPage = Integer.parseInt(map.get("page").toString());
		int sizePage = Integer.parseInt(map.get("rows").toString());

		int count = baseDao.getCount(ProductInfo.class, map, "getProductInfoConditionCount");
		productModel.setTotalCount(count);

		int total = count % sizePage == 0 ? count / sizePage : count / sizePage + 1;
		productModel.setTotal(total);
		int offset = (currPage - 1) * sizePage;
		List<ProductInfo> cs=baseDao.findList(ProductInfo.class, map, "getProductInfoCondition", offset, sizePage);
		productModel.setList(cs);
		productModel.setPage(currPage);
		return productModel;
	}

}
