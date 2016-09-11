package com.crm.bean;

import java.io.Serializable;
/**
 * 用户表
 * @author liwp
 *
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 5557741825437928405L;

	private Integer id;
	private String uname; // 昵称
	private String pwd; // 密码
	private String name; // 真实姓名
	private DataDirectory sex; // 性别 关联数据字典
	private Integer userage; // 年龄
	private DataDirectory role; // 角色 关联数据字典
	private DataDirectory isassign; // 是否被指派 关联数据字典

	public DataDirectory getIsassign() {
		return isassign;
	}

	public void setIsassign(DataDirectory isassign) {
		this.isassign = isassign;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DataDirectory getSex() {
		return sex;
	}

	public void setSex(DataDirectory sex) {
		this.sex = sex;
	}

	public Integer getUserage() {
		return userage;
	}

	public void setUserage(Integer userage) {
		this.userage = userage;
	}

	public DataDirectory getRole() {
		return role;
	}

	public void setRole(DataDirectory role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", uname=" + uname + ", pwd=" + pwd + ", name=" + name + ", sex=" + sex
				+ ", userage=" + userage + ", role=" + role + ", isassign=" + isassign + "]";
	}

}
