package com.crm.web.model;

import java.io.Serializable;
import java.util.List;


public class PageModel<T> implements Serializable{

	private static final long serialVersionUID = -8320793646532565741L;
	private Integer total=1;	//总页数
	private Integer totalCount=0; //总记录数
	private Integer page;  //当前第几页
	private Integer rows=5;	//每页条数
	private List<T> list;
	private T t;
	
	
	
	
	
	
	public PageModel() {
	}

	public PageModel(T t) {
		this.t = t;
	}
	
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
	
	public Integer getPage() {
		if(page==null||page<=0){
			page=1;
		}
//		if(page>total){
//			page=total;
//		}
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	
	
	public Integer getRows() {
		if(rows==null||rows==0){
			rows=5;
		}
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	@Override
	public String toString() {
		return "PageModel [total=" + total + ", totalCount=" + totalCount
				+ ", page=" + page + ", rows=" + rows + ", list=" + list
				+ ", t=" + t + "]";
	}


	
}
