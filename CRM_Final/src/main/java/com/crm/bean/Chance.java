package com.crm.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 销售机会表
 * 
 * @author liwp
 *
 */
public class Chance implements Serializable {

	private static final long serialVersionUID = 9219454948178572527L;
	
	private Integer id;
	private String chancesource; // 机会来源
	private String customername; // 客户名称
	private String success; // 成功几率
	private String chancesummary; // 机会描述
	private Contacter contacter; // 联系人
	private String chancedescribe; // 机会描述
	private String creater; // 创建人
	private Date createtime; // 创建时间
	private UserInfo user; // 被指派的人
	private Date assigntime; // 指派时间
	private DataDirectory chancestatus; // 机会状态 关联数据字典
	//转换后的时间
	private String changecreatetime;
	private String changeassigntime;
	
	
	
	

	public String getChangecreatetime() {
		return this.changecreatetime;
	}

	public void setChangecreatetime(String changecreatetime) {
		this.changecreatetime = changecreatetime;
	}

	public String getChangeassigntime() {
		return this.changeassigntime;
	}

	public void setChangeassigntime(String changeassigntime) {
		this.changeassigntime = changeassigntime;
	}
	
	
	public Date getCreatetime() throws ParseException {
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		this.createtime=new Date(df.parse(this.changecreatetime).getTime());
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		this.createtime = createtime;
		this.changecreatetime=df.format(new java.util.Date(this.createtime.getTime()));
	}

	public Date getAssigntime() throws ParseException {
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		this.assigntime=new Date(df.parse(this.changeassigntime).getTime());
		return this.assigntime;
	}

	public void setAssigntime(Date assigntime) {
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		this.assigntime = assigntime;
		this.changeassigntime=df.format(new  java.util.Date(this.assigntime.getTime()));
	}
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChancesource() {
		return chancesource;
	}

	public void setChancesource(String chancesource) {
		this.chancesource = chancesource;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getChancesummary() {
		return chancesummary;
	}

	public void setChancesummary(String chancesummary) {
		this.chancesummary = chancesummary;
	}

	public Contacter getContacter() {
		return contacter;
	}

	public void setContacter(Contacter contacter) {
		this.contacter = contacter;
	}

	public String getChancedescribe() {
		return chancedescribe;
	}

	public void setChancedescribe(String chancedescribe) {
		this.chancedescribe = chancedescribe;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}




	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}


	

	public DataDirectory getChancestatus() {
		return chancestatus;
	}

	public void setChancestatus(DataDirectory chancestatus) {
		this.chancestatus = chancestatus;
	}

	@Override
	public String toString() {
		return "Chance [id=" + id + ", chancesource=" + chancesource
				+ ", customername=" + customername + ", success=" + success
				+ ", chancesummary=" + chancesummary + ", contacter="
				+ contacter + ", chancedescribe=" + chancedescribe
				+ ", creater=" + creater + ", createtime=" + createtime
				+ ", user=" + user + ", assigntime=" + assigntime
				+ ", chancestatus=" + chancestatus + ", changecreatetime="
				+ changecreatetime + ", changeassigntime=" + changeassigntime
				+ "]";
	}

	
}
