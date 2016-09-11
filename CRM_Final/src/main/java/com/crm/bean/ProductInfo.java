package com.crm.bean;

import java.io.Serializable;
/**
 * 产品信息表
 * @author liwp
 *
 */
public class ProductInfo implements Serializable {

	private static final long serialVersionUID = 8410222625013724091L;

	private Integer id;
	private String pname;
	private String pversion;
	private String batch;
	private String unit;
	private Integer price;
	private String remark;

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPversion() {
		return pversion;
	}

	public void setPversion(String pversion) {
		this.pversion = pversion;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "ProductInfo [id=" + id + ", pname=" + pname + ", pversion=" + pversion + ", batch=" + batch + ", unit="
				+ unit + ", price=" + price + ", remark=" + remark + "]";
	}
}
