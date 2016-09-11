package com.crm.bean;

import java.io.Serializable;
/**
 * 库存表
 * @author liwp
 *
 */
public class KuCun implements Serializable {

	private static final long serialVersionUID = -1645393981966712955L;

	private Integer id;
	private String product;
	private String storehouse;
	private String storeplace;
	private Integer numbers;
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getStorehouse() {
		return storehouse;
	}

	public void setStorehouse(String storehouse) {
		this.storehouse = storehouse;
	}

	public String getStoreplace() {
		return storeplace;
	}

	public void setStoreplace(String storeplace) {
		this.storeplace = storeplace;
	}

	public Integer getNumbers() {
		return numbers;
	}

	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "KuCun [id=" + id + ", product=" + product + ", storehouse=" + storehouse + ", storeplace=" + storeplace
				+ ", numbers=" + numbers + ", remark=" + remark + "]";
	}
}
