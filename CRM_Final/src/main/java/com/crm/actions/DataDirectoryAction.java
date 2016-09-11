package com.crm.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.crm.bean.DataDirectory;
import com.crm.biz.DataDirectoryBiz;
import com.crm.util.LuceneUtil;
import com.crm.web.model.JsonModel;
import com.crm.web.model.PageJsonModel;
import com.crm.web.model.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value = "prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class DataDirectoryAction extends BaseAction implements ModelDriven<PageModel<DataDirectory>> {

	private static final long serialVersionUID = -4348815688856956230L;
	private PageModel<DataDirectory> pageModel;
	private DataDirectoryBiz dataDirectoryBiz;
	private PageJsonModel pagejsonModel;
	private JsonModel jsonModel;
	
	
	@Action(value = "/getDataDirectoryAndCustomerCount")
	public void getDataDirectoryAndCustomerCount() throws IOException {
		pagejsonModel = new PageJsonModel();
		Map<String, Object> map = new HashMap<String, Object>();
		if(("grade").equals(pageModel.getT().getDtype())){
			map.put("grade", pageModel.getT().getDtype());
		}else if(("satisfaction").equals(pageModel.getT().getDtype())){
			map.put("satisfaction", pageModel.getT().getDtype());
		}else if(("credit").equals(pageModel.getT().getDtype())){
			map.put("credit", pageModel.getT().getDtype());
		}
		try {
			pagejsonModel.setCode(1);
			pagejsonModel.setRows(dataDirectoryBiz.getDataDirectoryAndCustomerCount(map));
			
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("获取失败，原因"+e.getMessage());	
			}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}


	@Action(value = "/search_datadirectory")
	public void searchDataDirectory() throws IOException {
		pagejsonModel = new PageJsonModel();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", pageModel.getPage());
		map.put("rows", pageModel.getRows());
		if (pageModel.getT() != null) {
			map.put("dname", pageModel.getT().getDname());
			map.put("tiaomu", pageModel.getT().getTiaomu());
			map.put("dvalue", pageModel.getT().getDvalue());
		}
		pageModel = dataDirectoryBiz.searchDataDirectory(map);
		pagejsonModel.setTotal(pageModel.getTotalCount());
		pagejsonModel.setRows(pageModel.getList());

		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	@Action(value="/list_dtypedynamic")
	public void getDataDirectoryDynamic() throws InvalidTokenOffsetsException, IOException {
		jsonModel=new JsonModel();
		LuceneUtil lucene=new LuceneUtil();
		if (pageModel.getT()!=null) {
			String dname=pageModel.getT().getDname();
			if (dname!=null && !dname.trim().equals("")) {
				List<DataDirectory> dataDirectories=lucene.search(dname, 10);
				jsonModel.setCode(1);
				jsonModel.setObj(dataDirectories);
			}else{
				jsonModel.setCode(0);
				jsonModel.setMsg("为空");
			}
		}else{
			jsonModel.setCode(0);
			jsonModel.setMsg("injert failed");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	@Action(value="/list_datatype")
	public void listDataType() throws IOException{
		jsonModel=new JsonModel();
		List<DataDirectory> list=(List<DataDirectory>) ServletActionContext.getRequest().getSession().getServletContext().getAttribute("dataDirectoryType");
		if (list!=null) {
			jsonModel.setCode(1);
			jsonModel.setObj(list);
		}else{
			jsonModel.setCode(0);
			jsonModel.setMsg("could not find");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	@Action(value="/insert_datadirectory")
	public void insertDataDirectory() throws IOException{
		jsonModel=new JsonModel();
		if (pageModel.getT()!=null) {
			dataDirectoryBiz.insertDataDirectory(pageModel.getT());
			jsonModel.setCode(1);
		}else{
			jsonModel.setCode(0);
			jsonModel.setMsg("injert failed");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	@Action(value="/update_datadirectory")
	public void updateDataDirectory() throws IOException{
		jsonModel=new JsonModel();
		if (pageModel.getT()!=null) {
			dataDirectoryBiz.updateDataDirectory(pageModel.getT());
			jsonModel.setCode(1);
		}else{
			jsonModel.setCode(0);
			jsonModel.setMsg("injert failed");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}

	public PageModel<DataDirectory> getModel() {
		pageModel = new PageModel<DataDirectory>(new DataDirectory());
		return pageModel;
	}

	@Resource(name = "dataDirectoryBizImpl")
	public void setDataDirectoryBiz(DataDirectoryBiz dataDirectoryBiz) {
		this.dataDirectoryBiz = dataDirectoryBiz;
	}

}
