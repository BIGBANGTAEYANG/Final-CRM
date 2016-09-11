package com.crm.actions;

import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.crm.bean.DataDirectory;
import com.crm.bean.UserInfo;
import com.crm.biz.UserInfoBiz;
import com.crm.util.Encrypt;
import com.crm.web.model.PageJsonModel;
import com.crm.web.model.PageModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;


@Controller
@Scope(value="prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class UserInfoPageModelAction extends BaseAction implements ModelDriven<PageModel<UserInfo>>{

	private static final long serialVersionUID = -589637334129784556L;
	private PageJsonModel pagejsonModel = new PageJsonModel();
	private UserInfoBiz userinfobiz;
	private PageModel<UserInfo> pagemodel;
	private Map<String,Object> session = ActionContext.getContext().getSession();
	
	@Action(value="/getUserInfoDetail")
	public void getUserInfoDetail() throws IOException{
		if(session.get("userinfoDetail")!=null){
			List<UserInfo> list = (List<UserInfo>) session.get("userinfoDetail");
			
			for (UserInfo userInfo : list) {
				if(pagemodel.getT().getId()==userInfo.getId()){
					pagejsonModel.setCode(1);
					pagejsonModel.setRows(userInfo);
				}
			}
			
			
		}else{
			try {
				pagejsonModel.setRows(userinfobiz.getUserInfoDetailById(pagemodel.getT()));
				pagejsonModel.setCode(1);
			} catch (Exception e) {
				pagejsonModel.setCode(0);
				pagejsonModel.setMsg(e.getMessage());
			}
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	

	
	@Action(value="/getSelectList")
	public void getSelectList() throws IOException{
		 pagejsonModel.setCode(1); 
		 pagejsonModel.setRows((( Map<String, List<DataDirectory>>)(ServletActionContext.getServletContext().getAttribute("dataDirectory"))).get("role"));
		 super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	
	@Action(value="/deleteUserInfo")
	public void deleteUserInfo() throws IOException{
		try {
			userinfobiz.delteUserInfo(pagemodel);
			pagejsonModel.setCode(1);
			pagejsonModel.setMsg("删除成功");
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg(e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	
	@Action(value="/updateUserInfo")
	public void updateUserInfo() throws IOException{
		try {
			userinfobiz.updateUserInfo(pagemodel);
			pagejsonModel.setCode(1);
			pagejsonModel.setMsg("更新成功");
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg(e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}

	
	

	
	@Action(value="/findAllUserInfo")
	public void findAllUserInfo() throws IOException{
		try {
			pagejsonModel.setCode(1);
			pagejsonModel.setTotal(userinfobiz.findAllUserInfo(pagemodel).getTotalCount());
			pagejsonModel.setRows(userinfobiz.findAllUserInfo(pagemodel).getList());
			session.put("userinfoDetail", userinfobiz.findAllUserInfo(pagemodel).getList());
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("action error");
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	
	
	
	@Action(value="/createUserinfo")
	public void createUserinfo() throws IOException{
		try {
			pagemodel.getT().setPwd(Encrypt.md5AndSha(pagemodel.getT().getPwd()));
			userinfobiz.createUserInfo(pagemodel);
			pagejsonModel.setCode(1);
			pagejsonModel.setMsg("创建成功");
		} catch (Exception e) {
			pagejsonModel.setCode(0);
			pagejsonModel.setMsg("创建失败,原因"+e.getMessage());
		}
		super.outJson(pagejsonModel, ServletActionContext.getResponse());
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Resource(name="userInfoBizImpl")
	public void setUserinfobiz(UserInfoBiz userinfobiz) {
		this.userinfobiz = userinfobiz;
	}

	public PageModel<UserInfo> getModel() {
		pagemodel=new PageModel<UserInfo>(new UserInfo());
		return pagemodel;
	}

}
