package com.crm.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 交往记录
 * 
 * @author liwp
 *
 */
public class ContactHistory implements Serializable {

	private static final long serialVersionUID = 5530442943012694232L;

	private Integer id;
	private Customer customer;
	private Date contacttime;
	private String place;
	private String summary;
	private String item;
	private String remark;
	//前台需求的格式
	private String contacttimestr;

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

	public Date getContacttime() throws ParseException {
		return contacttime;
	}

	public void setContacttime(Date contacttime) {
		this.contacttime=contacttime;
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		this.contacttimestr=df.format(new java.util.Date(this.contacttime.getTime()));
	}

	public String getContacttimestr() {
		return contacttimestr;
	}

	public void setContacttimestr(String contacttimestr) throws ParseException {
		this.contacttimestr = contacttimestr;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "ContactHistory [id=" + id + ", customer=" + customer + ", contacttime=" + contacttime + ", place="
				+ place + ", summary=" + summary + ", item=" + item + ", remark=" + remark + ", contacttimestr="
				+ contacttimestr + "]";
	}

}
