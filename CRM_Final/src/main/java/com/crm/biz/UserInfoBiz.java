package com.crm.biz;

import java.util.List;

import com.crm.bean.UserInfo;
import com.crm.web.model.PageModel;

public interface UserInfoBiz {
	
	
	public UserInfo getUserInfoDetailById(UserInfo userinfo);
	
	public void delteUserInfo(PageModel<UserInfo> pagemodel);

	public void updateUserInfo(PageModel<UserInfo> pagemodel);
	
	public PageModel<UserInfo> findAllUserInfo(PageModel<UserInfo> pagemodel);
	
	public void createUserInfo(PageModel<UserInfo> pagemodel);

	
	public UserInfo findUserInfo(UserInfo userinfo);
	
	public  List<UserInfo> getAllUserInfo(UserInfo userinfo);
	
	public List<UserInfo> getUserInfoXiaoShou();
}
