package com.crm.bizimpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.bean.UserInfo;
import com.crm.biz.UserInfoBiz;
import com.crm.util.Encrypt;
import com.crm.web.model.PageModel;

@Service
public class UserInfoBizImpl extends BaseBizImpl implements UserInfoBiz{
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public UserInfo getUserInfoDetailById(UserInfo userinfo) {
		return (UserInfo) baseDao.find(userinfo, "getUserInfo");
	}

	
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public void delteUserInfo(PageModel<UserInfo> pagemodel) {
		baseDao.del(pagemodel.getT().getClass(), pagemodel.getT().getId(), "deleteUserInfo");
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public void updateUserInfo(PageModel<UserInfo> pagemodel) {
		baseDao.update(pagemodel.getT(), "updateUserInfo");
	}

	
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public PageModel<UserInfo> findAllUserInfo( PageModel<UserInfo> pagemodel) {
		int count = baseDao.getCountT(pagemodel.getT(),"findAllUserInfoCount");
		pagemodel.setTotalCount(count);
		int total = count%pagemodel.getRows()==0?count/pagemodel.getRows():count/pagemodel.getRows()+1;
		pagemodel.setTotal(total);
		int offset =(pagemodel.getPage()-1)*pagemodel.getRows();
		List<UserInfo> list = baseDao.findListT(pagemodel.getT(), "findAllUserInfoList",  offset, pagemodel.getRows());
		pagemodel.setList(list);
		return pagemodel;
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.REQUIRED)
	public void createUserInfo(PageModel<UserInfo> pagemodel) {
		baseDao.save(pagemodel.getT(),"createUserInfo");
	}


	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public UserInfo findUserInfo(UserInfo userinfo) {
		userinfo.setPwd(Encrypt.md5AndSha(userinfo.getPwd()));
		return (UserInfo) baseDao.find(userinfo, "findUserInfo");
	}
	
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName=("java.lang.RuntimeException"),propagation=Propagation.NOT_SUPPORTED)
	public List<UserInfo> getAllUserInfo(UserInfo userinfo){
		return  baseDao.findAll(UserInfo.class, "getAllUserInfo");
	}

	public List<UserInfo> getUserInfoXiaoShou() {
		return baseDao.findAll(UserInfo.class, "getUserInfoXiaoShou");
	}

}
