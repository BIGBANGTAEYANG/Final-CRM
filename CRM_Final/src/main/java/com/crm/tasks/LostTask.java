package com.crm.tasks;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.crm.biz.LostBiz;

@Component
public class LostTask {
	private LostBiz lostBiz;
	public void getAllLost(){
		//设置天数
		System.out.println("执行lost任务");
		lostBiz.vilateLost(180);
	}
	@Resource(name="lostBizImpl")
	public void setLostBiz(LostBiz lostBiz) {
		this.lostBiz = lostBiz;
	}
	
}
