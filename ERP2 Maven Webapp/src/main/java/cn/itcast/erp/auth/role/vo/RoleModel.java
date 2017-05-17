package cn.itcast.erp.auth.role.vo;

import java.util.HashSet;
import java.util.Set;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.res.vo.ResModel;

public class RoleModel {
	private Long uuid;
	private String name;
	private String code;
	private Set<ResModel> reses = new HashSet<ResModel>();
	private Set<EmpModel> emps;
	private Set<MenuModel> menus;
	
	public Set<MenuModel> getMenus() {
		return menus;
	}

	public void setMenus(Set<MenuModel> menus) {
		this.menus = menus;
	}

	public Set<EmpModel> getEmps() {
		return emps;
	}

	public void setEmps(Set<EmpModel> emps) {
		this.emps = emps;
	}

	public Set<ResModel> getReses() {
		return reses;
	}

	public void setReses(Set<ResModel> reses) {
		this.reses = reses;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
