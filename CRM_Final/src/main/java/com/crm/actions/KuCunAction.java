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
import com.crm.bean.KuCun;
import com.crm.biz.KuCunBiz;
import com.crm.web.model.PageJsonModel;
import com.crm.web.model.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value = "prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class KuCunAction extends BaseAction implements ModelDriven<PageModel<KuCun>> {

	private static final long serialVersionUID = -2580674929212068982L;
	private PageModel<KuCun> pageModel;
	private KuCunBiz kuCunBiz;
	private PageJsonModel pageJsonModel;
	
	@Action(value="/search_kucuninfo")
	public void search() throws IOException{
		pageJsonModel = new PageJsonModel();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", pageModel.getPage());
		map.put("rows", pageModel.getRows());
		
		map.put("product", pageModel.getT().getProduct());
		map.put("storehouse", pageModel.getT().getStorehouse());
		
		pageModel=kuCunBiz.searchKuCun(map);
		pageJsonModel.setTotal(pageModel.getTotalCount());
		pageJsonModel.setRows(pageModel.getList());
		
		super.outJson(pageJsonModel, ServletActionContext.getResponse());
	}
	
	public PageModel<KuCun> getModel() {
		pageModel=new PageModel<KuCun>(new KuCun());
		return pageModel;
	}
	@Resource(name = "kuCunBizImpl")
	public void setKuCunBiz(KuCunBiz kuCunBiz) {
		this.kuCunBiz = kuCunBiz;
	}
}
