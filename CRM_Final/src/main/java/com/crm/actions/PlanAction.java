package com.crm.actions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.crm.bean.Plan;
import com.crm.biz.PlanBiz;
import com.crm.web.model.PageJsonModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value="prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class PlanAction extends BaseAction implements ModelDriven<Plan>{

	private static final long serialVersionUID = 8980958399058395833L;
	private PageJsonModel pagejsonModel = new PageJsonModel();
	Map<String,Object> session = ActionContext.getContext().getSession();
	private Plan plan;
	private PlanBiz planbiz;


	@Action(value="/findAllPlanByChanceId")
	public void findAllPlan() throws IOException{
		List<Plan> planlist = planbiz.getPlanList(plan);
		if(planlist!=null){
			pagejsonModel.setCode(1);
			pagejsonModel.setRows(planlist);
			session.put("planlist", planlist);
		}else{
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("plan is null");
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}

	@Action(value="/insertPlan")
	public void insertPlan() throws IOException{
		try {
			planbiz.InsertPlan(plan);
			pagejsonModel.setCode(1);
			pagejsonModel.setMsg("添加成功");
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg(e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	

	@Action(value="/updatePlan")
	public void updatePlan() throws IOException{
		try {
			planbiz.updatePlan(plan);
			pagejsonModel.setCode(1);
			pagejsonModel.setMsg("添加成功");
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg(e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	
	@Action(value="/deletePlan")
	public void deletePlan() throws IOException{
		try {
			planbiz.delPlan(plan);
			pagejsonModel.setCode(1);
			pagejsonModel.setMsg("删除成功");
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg(e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	
	
	public Plan getModel() {
		plan = new Plan();
		return plan;
	}


	@Resource(name="planBizImpl")
	public void setPlanbiz(PlanBiz planbiz) {
		this.planbiz = planbiz;
	}
	
}
