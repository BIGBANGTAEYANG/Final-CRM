package com.crm.bean;

import java.io.Serializable;

/**
 * 客户联系人表
 * 
 * @author liwp
 *
 */
public class Contacter implements Serializable {

	private static final long serialVersionUID = -8640470517069581185L;

	private Integer id;
	private Customer customer; // 客户 对应于数据库 customerid
	private String cname; // 联系人名
	private DataDirectory sex; // 性别 关联数据字典
	private DataDirectory post; // 职务 关联数据字典
	private String telphone; // 办公电话
	private String cellphone; // 手机
	private String remark; // 备注

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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public DataDirectory getSex() {
		return sex;
	}

	public void setSex(DataDirectory sex) {
		this.sex = sex;
	}

	public DataDirectory getPost() {
		return post;
	}

	public void setPost(DataDirectory post) {
		this.post = post;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Contacter [id=" + id + ", customer=" + customer + ", cname=" + cname + ", sex=" + sex + ", post=" + post
				+ ", telphone=" + telphone + ", cellphone=" + cellphone + ", remark=" + remark + "]";
	}

}
