package com.prolificidea.templates.tsw.domain;

import javax.persistence.*;

@Entity
@Table(name = "Role")
public class Role implements java.io.Serializable {

	private int roleId;
	private String description;

	public Role() {
	}

	public Role(int roleId, String description) {
		this.roleId = roleId;
		this.description = description;
	}

	@Id
    @GeneratedValue
	@Column(name = "RoleId", unique = true, nullable = false)
	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Column(name = "Description", nullable = false, length = 50)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
