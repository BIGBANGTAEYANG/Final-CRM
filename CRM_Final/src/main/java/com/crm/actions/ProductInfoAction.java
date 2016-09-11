package com.crm.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.crm.bean.ProductInfo;
import com.crm.biz.ProductInfoBiz;
import com.crm.web.model.PageJsonModel;
import com.crm.web.model.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value = "prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class ProductInfoAction extends BaseAction implements ModelDriven<PageModel<ProductInfo>> {

	private static final long serialVersionUID = -2580674929212068982L;
	private PageModel<ProductInfo> pageModel;
	private ProductInfoBiz productInfoBiz;
	private PageJsonModel pageJsonModel;
	
	@Action(value="/search_productinfo")
	public void search() throws IOException{
		pageJsonModel = new PageJsonModel();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", pageModel.getPage());
		map.put("rows", pageModel.getRows());
		
		map.put("pname", pageModel.getT().getPname());
		map.put("pversion", pageModel.getT().getPversion());
		map.put("batch", pageModel.getT().getBatch());
		
		pageModel=productInfoBiz.searchProductInfo(map);
		pageJsonModel.setTotal(pageModel.getTotalCount());
		pageJsonModel.setRows(pageModel.getList());
		
		super.outJson(pageJsonModel, ServletActionContext.getResponse());
	}
	
	public PageModel<ProductInfo> getModel() {
		pageModel=new PageModel<ProductInfo>(new ProductInfo());
		return pageModel;
	}
	@Resource(name = "productInfoBizImpl")
	public void setProductInfoBIz(ProductInfoBiz productInfoBiz) {
		this.productInfoBiz = productInfoBiz;
	}
	
}
