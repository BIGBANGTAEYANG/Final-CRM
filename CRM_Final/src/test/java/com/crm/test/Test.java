package com.crm.test;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.crm.bean.Chance;
import com.crm.bean.Service;
import com.crm.bean.UserInfo;
import com.crm.biz.ChanceBiz;
import com.crm.biz.LostBiz;
import com.crm.biz.ServiceBiz;
import com.crm.biz.UserInfoBiz;
import com.crm.web.model.PageModel;

import junit.framework.TestCase;

public class Test extends TestCase {

	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
		UserInfoBiz biz=(UserInfoBiz) context.getBean("userInfoBizImpl");
		UserInfo user=new UserInfo();
		user.setUname("a");
		user.setPwd("a");
		
		user= biz.findUserInfo(user);
		System.out.println(user);
	}
	public void tes2() {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
		LostBiz biz=(LostBiz) context.getBean("lostBizImpl");
		biz.vilateLost(100);
		//System.out.println(biz.FindChanceList());
	}
	
	public void tes4() {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
		ServiceBiz biz=(ServiceBiz) context.getBean("serviceBizImpl");
		HashMap<String,Object> map = new HashMap<String, Object>();
		PageModel<Service> pagemodel = new PageModel<Service>();
		
		//map.put("customername", "yc");
		map.put("servicestatus", 40);
		
		List<Service> list = biz.getServiceListByCondition(map).getList();
		
		for (Service service : list) {
			System.out.println(service);
		}
		
	}
	
}
