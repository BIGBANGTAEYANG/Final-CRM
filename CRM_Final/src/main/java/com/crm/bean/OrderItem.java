package com.crm.bean;

import java.io.Serializable;
/**
 * 订单详情表
 * @author liwp
 *
 */
public class OrderItem implements Serializable {

	private static final long serialVersionUID = -7075257919104354637L;

	private Integer id;
	private OrderInfo order;
	private String pname;
	private Integer ordernumber;
	private String unit;
	private Integer price;
	private Integer countprice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrderInfo getOrder() {
		return order;
	}

	public void setOrder(OrderInfo order) {
		this.order = order;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(Integer ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getCountprice() {
		return countprice;
	}

	public void setCountprice(Integer countprice) {
		this.countprice = countprice;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", order=" + order + ", pname=" + pname + ", ordernumber=" + ordernumber
				+ ", unit=" + unit + ", price=" + price + ", countprice=" + countprice + "]";
	}
}
