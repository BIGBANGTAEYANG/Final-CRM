package com.crm.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.crm.bean.DataDirectory;
import com.crm.bean.Lost;
import com.crm.biz.LostBiz;
import com.crm.util.HtmlEncodeUtil;
import com.crm.web.model.JsonModel;
import com.crm.web.model.PageJsonModel;
import com.crm.web.model.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value = "prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class LostAction extends BaseAction implements ModelDriven<PageModel<Lost>>{

	private static final long serialVersionUID = 2491652381848632358L;
	private PageModel<Lost> pageModel;
	private PageJsonModel pagejsonModel;
	private JsonModel jsonModel;
	private LostBiz lostBiz;
	
	@Action(value="/CustomerLostAnalysis")
	public void  CustomerLostAnalysis() throws IOException{
		pagejsonModel = new PageJsonModel();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", pageModel.getPage());
		map.put("rows", pageModel.getRows());
		map.put("customername", pageModel.getT().getCustomer().getCname());
		map.put("name", pageModel.getT().getUserinfo().getName());
		try {
			pageModel =lostBiz.customerLostAnalysis(map);
			pagejsonModel.setTotal(pageModel.getTotalCount());
			
			//HtmlEncodeUtil
			
			List<Lost> list = pageModel.getList();
			
			for (Lost lost : list) {
				lost.setLostreason(HtmlEncodeUtil.htmlEncode(lost.getLostreason()));
			}
			
			
			pagejsonModel.setRows(list);
			pagejsonModel.setCode(1);
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("失败，原因"+e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}

	
	@Action(value="/list_lost")
	public void listLost() throws IOException{
		pagejsonModel = new PageJsonModel();
		List<Lost> list = lostBiz.getLostBean(pageModel).getList();
		Integer total = lostBiz.getLostBean(pageModel).getTotalCount();
		pagejsonModel.setTotal(total);
		pagejsonModel.setRows(list);
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	@Action(value="/list_selectstatus")
	public void listList() throws IOException{
		jsonModel=new JsonModel();
		List<DataDirectory> dataDirectories=((Map<String, List<DataDirectory>>)(ServletActionContext.getServletContext().getAttribute("dataDirectory"))).get("customerstatus");
//		dataDirectories.remove(0);
//		dataDirectories.remove(dataDirectories.size()-1);
		jsonModel.setCode(1);
		jsonModel.setObj(dataDirectories);
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	@Action(value="/search_lost")
	public void searchLost() throws IOException{
		pagejsonModel = new PageJsonModel();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", pageModel.getPage());
		map.put("rows", pageModel.getRows());
		
		map.put("cname", pageModel.getT().getOrderInfo().getCustomer().getCname());
		map.put("manname", pageModel.getT().getOrderInfo().getCustomer().getCustomermanager().getName());
		map.put("status", pageModel.getT().getOrderInfo().getCustomer().getCustomerstatus().getId());
		
		pageModel=lostBiz.searchLost(map);
		pagejsonModel.setTotal(pageModel.getTotalCount());
		pagejsonModel.setRows(pageModel.getList());
		
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	@Action(value="/update_lost")
	public void updateLost() throws IOException{
		jsonModel=new JsonModel();
		if (pageModel.getT().getId()!=null && pageModel.getT().getOrderInfo().getCustomer().getId() !=null) {
			pageModel.getT().setMeasure(HtmlEncodeUtil.htmlEncode(pageModel.getT().getMeasure()));
			pageModel.getT().setAddmeasure(HtmlEncodeUtil.htmlEncode(pageModel.getT().getAddmeasure()));
			lostBiz.updateLost(pageModel.getT());
			jsonModel.setCode(1);
		}else{
			jsonModel.setCode(0);
			jsonModel.setMsg("injert failed");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	@Resource(name="lostBizImpl")
	public void setLostBiz(LostBiz lostBiz) {
		this.lostBiz = lostBiz;
	}
	public PageModel<Lost> getModel() {
		pageModel=new PageModel<Lost>(new Lost());
		return pageModel;
	}
}
