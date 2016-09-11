package com.crm.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 客户流失表
 * @author liwp
 *
 */
public class Lost implements Serializable {

	private static final long serialVersionUID = 7456730714022832697L;

	private Integer id;
	private OrderInfo orderInfo;
	private Date confirmlostdate;
	private String measure;
	private String addmeasure;
	private String lostreason;
	
	private String lostdatestr;
	private Customer customer = new Customer();
	private UserInfo userinfo = new UserInfo();

	
	
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public String getLostdatestr() {
		return lostdatestr;
	}

	public void setLostdatestr(String lostdatestr) {
		this.lostdatestr = lostdatestr;
	}

	public Date getConfirmlostdate() {
		return confirmlostdate;
	}

	public void setConfirmlostdate(Date confirmlostdate) {
		this.confirmlostdate = confirmlostdate;
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		this.lostdatestr=df.format(new java.util.Date(this.confirmlostdate.getTime()));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getAddmeasure() {
		return addmeasure;
	}

	public void setAddmeasure(String addmeasure) {
		this.addmeasure = addmeasure;
	}

	public String getLostreason() {
		return lostreason;
	}

	public void setLostreason(String lostreason) {
		this.lostreason = lostreason;
	}

	@Override
	public String toString() {
		return "Lost [id=" + id + ", orderInfo=" + orderInfo
				+ ", confirmlostdate=" + confirmlostdate + ", measure="
				+ measure + ", addmeasure=" + addmeasure + ", lostreason="
				+ lostreason + ", lostdatestr=" + lostdatestr + ", customer="
				+ customer + ", userinfo=" + userinfo + "]";
	}

	
}
