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

import com.crm.bean.UserInfo;
import com.crm.biz.UserInfoBiz;
import com.crm.web.model.JsonModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;


@Controller
@Scope(value="prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class UserInfoAction extends BaseAction implements ModelDriven<UserInfo>{

	private static final long serialVersionUID = -589637334129784556L;
	private JsonModel jsonModel = new JsonModel();
	private UserInfoBiz userinfobiz;
	private UserInfo userinfo;
	
	
	
	@Action(value="/findUserInfoByName")
	public void findUserInfoByName() throws IOException{
		userinfo= userinfobiz.findUserInfo(userinfo);
		if(userinfo!=null){
			ServletActionContext.getRequest().getSession().setAttribute("loginuser", userinfo);
			jsonModel.setCode(1);
			jsonModel.setObj(userinfo);
		}else{
			jsonModel.setCode(0);
			jsonModel.setMsg("user is not exist");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	
	@Action(value="/findXiaoShouUserInfo")
	public void findXiaoShouUserInfo() throws IOException{
		List<UserInfo> list = userinfobiz.getUserInfoXiaoShou();
		if(list!=null){
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("xiaoshouzhuguan", list);
			jsonModel.setCode(1);
			jsonModel.setObj(list);
		}else{
			jsonModel.setCode(0);
			jsonModel.setMsg("user is not exist");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	
	
	@Action(value="/checkLogin")
	public void checkLogin() throws IOException{
		Map<String,Object> session = ActionContext.getContext().getSession();
		UserInfo currUser = (UserInfo) session.get("loginuser");
		if(currUser!=null){
			jsonModel.setCode(1);
			currUser.setPwd(null);
			jsonModel.setObj(currUser);
			
		}else {
			
			jsonModel.setCode(0);
			
			jsonModel.setMsg("you have not login,please login first...");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	
	@Action(value="/logout")
	public void logout() throws IOException{
		
		Map<String,Object> session = ActionContext.getContext().getSession();
		if(null!=userinfo){
			session.remove("loginuser");
			jsonModel.setCode(1);
			
		}else{
			jsonModel.setCode(0);
			
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	
	
	
	@Resource(name="userInfoBizImpl")
	public void setUserinfobiz(UserInfoBiz userinfobiz) {
		this.userinfobiz = userinfobiz;
	}



	public UserInfo getModel() {
		userinfo = new UserInfo();
		return userinfo;
	}
	
	
	

}
