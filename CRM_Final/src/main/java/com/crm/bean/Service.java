package com.crm.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * 服务表
 * @author liwp
 *
 */
import java.util.Date;
/**
 * 服务表
 * @author liwp
 *
 */
public class Service implements Serializable {

	private static final long serialVersionUID = 1131039083661188359L;

	private Integer id;
	private DataDirectory servicetype;
	private String summary;
	private Customer customer;
	private DataDirectory servicestatus;
	private String servicerequest;
	private String createperson;
	private Date createdate;
	private UserInfo allotperson;
	private Date allotdate;
	private String servicedeal;
	private UserInfo dealperson;
	private Date dealtime;
	private String dealresult;
	private DataDirectory satisify;
	private Date servicemindate;
	private Date servicemaxdate;
	private String servicemindateString;
	private String servicemaxdateString;
	
	private String createdataString;
	private String allotdateString;
	private String dealtimeString;
	
	private String count;
	private Integer createtime;
	

	

	public Integer getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Integer createtime) {
		this.createtime = createtime;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getServicemindateString() {
		return servicemindateString;
	}

	public void setServicemindateString(String servicemindateString) {
		this.servicemindateString = servicemindateString;
	}

	public String getServicemaxdateString() {
		return servicemaxdateString;
	}

	public void setServicemaxdateString(String servicemaxdateString) {
		this.servicemaxdateString = servicemaxdateString;
	}

	public String getCreatedataString() {
		return createdataString;
	}

	public void setCreatedataString(String createdataString) {
		this.createdataString = createdataString;
	}

	public String getAllotdateString() {
		return allotdateString;
	}

	public void setAllotdateString(String allotdateString) {
		this.allotdateString = allotdateString;
	}

	public String getDealtimeString() {
		return dealtimeString;
	}

	public void setDealtimeString(String dealtimeString) {
		this.dealtimeString = dealtimeString;
	}

	public Date getServicemindate() throws ParseException {
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		return df.parse(this.servicemindateString);
	}

	public void setServicemindate(Date servicemindate) {
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		this.servicemindate = servicemindate;
		this.servicemindateString = df.format(servicemindate);
	}

	public Date getServicemaxdate() throws ParseException {
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		return df.parse(this.servicemaxdateString);
	}

	public void setServicemaxdate(Date servicemaxdate) {
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		this.servicemaxdate = servicemaxdate;
		this.servicemaxdateString = df.format(servicemaxdate);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DataDirectory getServicetype() {
		return servicetype;
	}

	public void setServicetype(DataDirectory servicetype) {
		this.servicetype = servicetype;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public DataDirectory getServicestatus() {
		return servicestatus;
	}

	public void setServicestatus(DataDirectory servicestatus) {
		this.servicestatus = servicestatus;
	}

	public String getServicerequest() {
		return servicerequest;
	}

	public void setServicerequest(String servicerequest) {
		this.servicerequest = servicerequest;
	}

	public String getCreateperson() {
		return createperson;
	}

	public void setCreateperson(String createperson) {
		this.createperson = createperson;
	}

	public Date getCreatedate() throws ParseException {
		//DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		//this.createdate = new Date(df.parse(this.createdataString).getTime());
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		this.createdate = createdate;
		this.createdataString=df.format(new Date(this.createdate.getTime()));
	}


	public UserInfo getAllotperson() {
		return allotperson;
	}

	public void setAllotperson(UserInfo allotperson) {
		this.allotperson = allotperson;
	}

	public Date getAllotdate() throws ParseException {
		//DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		//this.allotdate = new Date(df.parse(this.allotdateString).getTime());
		return this.allotdate;
	}

	public void setAllotdate(Date allotdate) {
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		this.allotdate = allotdate;
		this.allotdateString = df.format(new Date(this.allotdate.getTime()));
	}


	public String getServicedeal() {
		return servicedeal;
	}

	public void setServicedeal(String servicedeal) {
		this.servicedeal = servicedeal;
	}

	public UserInfo getDealperson() {
		return dealperson;
	}

	public void setDealperson(UserInfo dealperson) {
		this.dealperson = dealperson;
	}


	public Date getDealtime() throws ParseException {
		//DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		//this.dealtime = new Date(df.parse(this.dealtimeString).getTime());
		return this.dealtime;
	}

	public void setDealtime(Date dealtime) {
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		this.dealtime = dealtime;
		this.dealtimeString = df.format(new Date(this.dealtime.getTime()));
	}


	public String getDealresult() {
		return dealresult;
	}

	public void setDealresult(String dealresult) {
		this.dealresult = dealresult;
	}

	public DataDirectory getSatisify() {
		return satisify;
	}

	public void setSatisify(DataDirectory satisify) {
		this.satisify = satisify;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", servicetype=" + servicetype
				+ ", summary=" + summary + ", customer=" + customer
				+ ", servicestatus=" + servicestatus + ", servicerequest="
				+ servicerequest + ", createperson=" + createperson
				+ ", createdate=" + createdate + ", allotperson=" + allotperson
				+ ", allotdate=" + allotdate + ", servicedeal=" + servicedeal
				+ ", dealperson=" + dealperson + ", dealtime=" + dealtime
				+ ", dealresult=" + dealresult + ", satisify=" + satisify
				+ ", servicemindate=" + servicemindate + ", servicemaxdate="
				+ servicemaxdate + ", servicemindateString="
				+ servicemindateString + ", servicemaxdateString="
				+ servicemaxdateString + ", createdataString="
				+ createdataString + ", allotdateString=" + allotdateString
				+ ", dealtimeString=" + dealtimeString + ", count=" + count
				+ ", createtime=" + createtime + "]";
	}


}
