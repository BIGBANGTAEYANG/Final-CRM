package com.crm.bean;

import java.io.Serializable;

/**
 * 客户表
 * 
 * @author liwp
 *
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = -737982344888013609L;

	private Integer id;
	private String cname; // 编号
	private DataDirectory district; // 地区 关联数据字典
	private DataDirectory grade; // 客户星级 关联数据字典
	private DataDirectory satisfaction; // 客户满意度 关联数据字典
	private DataDirectory credit; // 信用度 关联数据字典
	private String location; // 地址
	private Integer postcode; // 邮政编码
	private String telphone; // 联系电话
	private String fax; // 传真
	private String website; // 网址
	private String license; // 营业执照注册号
	private String legal; // 法人
	private Integer registermoney; // 注册资金（万元）
	private Integer yearincome; // 年营业额
	private String bank; // 用户银行
	private String bankaccount; // 银行账户
	private String rentnumber; // 地税登记号
	private String taxnumber; // 国税登记号
	private DataDirectory customerstatus; // 客户状态
	private UserInfo customermanager; // 客户经理 对应数据库中customermanagerid
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public DataDirectory getDistrict() {
		return district;
	}

	public void setDistrict(DataDirectory district) {
		this.district = district;
	}

	public DataDirectory getGrade() {
		return grade;
	}

	public void setGrade(DataDirectory grade) {
		this.grade = grade;
	}

	

	public DataDirectory getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(DataDirectory satisfaction) {
		this.satisfaction = satisfaction;
	}

	public DataDirectory getCredit() {
		return credit;
	}

	public void setCredit(DataDirectory credit) {
		this.credit = credit;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getPostcode() {
		return postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public Integer getRegistermoney() {
		return registermoney;
	}

	public void setRegistermoney(Integer registermoney) {
		this.registermoney = registermoney;
	}

	public Integer getYearincome() {
		return yearincome;
	}

	public void setYearincome(Integer yearincome) {
		this.yearincome = yearincome;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public String getRentnumber() {
		return rentnumber;
	}

	public void setRentnumber(String rentnumber) {
		this.rentnumber = rentnumber;
	}

	public String getTaxnumber() {
		return taxnumber;
	}

	public void setTaxnumber(String taxnumber) {
		this.taxnumber = taxnumber;
	}

	public DataDirectory getCustomerstatus() {
		return customerstatus;
	}

	public void setCustomerstatus(DataDirectory customerstatus) {
		this.customerstatus = customerstatus;
	}

	public UserInfo getCustomermanager() {
		return customermanager;
	}

	public void setCustomermanager(UserInfo customermanager) {
		this.customermanager = customermanager;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", cname=" + cname + ", district=" + district + ", grade=" + grade
				+ ", satisfaction=" + satisfaction + ", credit=" + credit + ", location=" + location + ", postcode="
				+ postcode + ", telphone=" + telphone + ", fax=" + fax + ", website=" + website + ", license=" + license
				+ ", legal=" + legal + ", registermoney=" + registermoney + ", yearincome=" + yearincome + ", bank="
				+ bank + ", bankaccount=" + bankaccount + ", rentnumber=" + rentnumber + ", taxnumber=" + taxnumber
				+ ", customerstatus=" + customerstatus + ", customermanager=" + customermanager + "]";
	}
}
