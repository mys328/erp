package cn.itcast.erp.auth.res.vo;

import java.util.HashSet;
import java.util.Set;

import cn.itcast.erp.auth.role.vo.RoleModel;

public class ResModel {
	private Long uuid;
	private String name;
	private String text;
	private Set<RoleModel> roles = new HashSet<RoleModel>();

	public Set<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
