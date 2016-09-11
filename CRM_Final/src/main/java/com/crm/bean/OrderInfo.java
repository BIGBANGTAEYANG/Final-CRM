package com.crm.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * 订单表
 * @author liwp
 *
 */
import java.util.Date;
/**
 * 订单表
 * @author liwp
 *
 */
public class OrderInfo implements Serializable {

	private static final long serialVersionUID = -2932755312847281835L;

	private Integer id;
	private Customer customer;
	private String customername;
	private Date ordertime;
	private String sendlocation;
	private DataDirectory orderstatus;// 状态 关联数据字典
	private Double sumprice;
	private Integer createtime;

	//前台需求的格式
	private String ordertimestr;

	
	public Double getSumprice() {
		return sumprice;
	}

	public void setSumprice(Double sumprice) {
		this.sumprice = sumprice;
	}

	public Integer getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Integer createtime) {
		this.createtime = createtime;
	}

	public String getOrdertimestr() {
		return ordertimestr;
	}

	public void setOrdertimestr(String ordertimestr) {
		this.ordertimestr = ordertimestr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
		DateFormat df =new SimpleDateFormat("yyyy年MM月dd日");
		this.ordertimestr=df.format(new java.util.Date(this.ordertime.getTime()));
	}

	public String getSendlocation() {
		return sendlocation;
	}

	public void setSendlocation(String sendlocation) {
		this.sendlocation = sendlocation;
	}

	public DataDirectory getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(DataDirectory orderstatus) {
		this.orderstatus = orderstatus;
	}

	@Override
	public String toString() {
		return "OrderInfo [id=" + id + ", customer=" + customer
				+ ", customername=" + customername + ", ordertime=" + ordertime
				+ ", sendlocation=" + sendlocation + ", orderstatus="
				+ orderstatus + ", sumprice=" + sumprice + ", createtime="
				+ createtime + ", ordertimestr=" + ordertimestr + "]";
	}

	
}
