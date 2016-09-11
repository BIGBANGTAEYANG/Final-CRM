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

import com.crm.bean.Chance;
import com.crm.bean.UserInfo;
import com.crm.biz.ChanceBiz;
import com.crm.web.model.PageJsonModel;
import com.crm.web.model.PageModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value="prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class ChanceAction extends BaseAction implements ModelDriven<PageModel<Chance>>{
	private static final long serialVersionUID = 7210989892510374954L;
	private PageJsonModel pagejsonModel = new PageJsonModel();
	private ChanceBiz chanceBiz;
	private PageModel<Chance> pagemodel;
	private HashMap<String,Object> map = new HashMap<String, Object>();
	private Integer total;
	private Map<String,Object> session = ActionContext.getContext().getSession();
	
	
	@Action(value="/updateChanceStatusAndInsertCustomer")
	public void updateChanceStatusAndInsertCustomer() throws IOException{
			try {
				chanceBiz.updateChanceStatusAndInsertCustomer(pagemodel);
				pagejsonModel.setCode(1);
				pagejsonModel.setMsg("操作成功");
			} catch (Exception e) {
				pagejsonModel.setCode(0);
				pagejsonModel.setMsg(e.getMessage());
			}
		
			super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}

	
	
	@Action(value="/deleteChanceById")
	public void DeleteChanceById() throws IOException{
			try {
				if(session.get("loginuser")!=null&&!"".equals(session.get("loginuser"))){
					UserInfo userinfo = (UserInfo) session.get("loginuser");
					List<Chance> chancelist  = (List<Chance>) session.get("chancelist");
					for (Chance chance : chancelist) {
						if(chance.getId()==pagemodel.getT().getId()){
							if(userinfo.getUname().equals(chance.getCreater())){
								chanceBiz.deleteChanceById(pagemodel);
								pagejsonModel.setCode(1);
								pagejsonModel.setMsg("删除成功");
							}
						}
					}
				}
			} catch (Exception e) {
				pagejsonModel.setCode(0);
				pagejsonModel.setMsg("删除失败，原因"+e.getMessage());
			}
			super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}


	
	@Action(value="/updateChanceUserInfo")
	public void updateChanceUserInfo() throws IOException{
			try {
				
				boolean flag=chanceBiz.updateChanceUserInfo(pagemodel);
				if(flag){
					pagejsonModel.setCode(1);
					pagejsonModel.setMsg("更新成功");
				}else{
					pagejsonModel.setCode(0);
					pagejsonModel.setMsg("更新失败");
				}
				
			} catch (Exception e) {
				pagejsonModel.setCode(0);
				pagejsonModel.setMsg(e.getMessage());
			}
		
			super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}

	
	
	@Action(value="/getChanceDetail")
	public void getChanceDetail() throws IOException{
		
		if(session.get("chancelist")!=null&&!"".equals(session.get("chancelist"))){
			List<Chance> chancelist  = (List<Chance>) session.get("chancelist");
			for (Chance chance : chancelist) {
				if(pagemodel.getT().getId()==chance.getId()){
					pagejsonModel.setCode(1);
					pagejsonModel.setRows(chance);
				}
			}
		}else{
			Chance chance =chanceBiz.FindDetailChance(pagemodel);
			if(chance!=null&&!"".equals(chance)){
				pagejsonModel.setCode(1);
				pagejsonModel.setRows(chance);
			}else{
				pagejsonModel.setCode(0);
				pagejsonModel.setMsg("error in action");
			}
		}
			super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}

	
	@Action(value="/findChanceByCondition")
	public void FindChanceByCondition() throws IOException{
		map.put("contactername", pagemodel.getT().getContacter().getCname());
		map.put("customername", pagemodel.getT().getCustomername());
		map.put("chancesummary", pagemodel.getT().getChancesummary());
		
		List<Chance> chancelist = chanceBiz.FindChanceByCondition(map).getList();
		
		total=chanceBiz.FindChanceByCondition(map).getTotalCount();
		
		pagejsonModel.setTotal(total);
		pagejsonModel.setRows(chancelist);
		
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}

	
	@Action(value="/SaveChance")
	public void SaveChance() throws IOException{
		
			boolean flag=chanceBiz.InsertChance(pagemodel);
			if (flag) {
				pagejsonModel.setCode(1);
				pagejsonModel.setMsg("添加机会成功");
			}else{
				pagejsonModel.setCode(0);
				pagejsonModel.setMsg("联系人重名");
			}
			super.outJson(pagejsonModel, ServletActionContext.getResponse());
		
	}


	
	@Action(value="/findChanceListPage")
	public void FindChanceListPage() throws IOException{
		
		List<Chance> chancelist = chanceBiz.FindChanceListPage(pagemodel).getList();
		map.put("rows", chancelist);
		total=chanceBiz.FindChanceListPage(pagemodel).getTotalCount();
		map.put("total", total);
		
		session.put("chancelist", chancelist);
		
		if(map!=null){
			pagejsonModel.setTotal(total);;
			pagejsonModel.setRows(chancelist);
		}else{
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("action error");
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}

	
	
	
	
	public PageModel<Chance> getModel() {
		pagemodel = new PageModel<Chance>(new Chance());
		return pagemodel;
	}


	@Resource(name="chanceBizImpl")
	public void setChanceBiz(ChanceBiz chanceBiz) {
		this.chanceBiz = chanceBiz;
	}
	
	
	
	
}
