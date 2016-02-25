package com.prolificidea.templates.tsw.domain;

import javax.persistence.*;

@Entity
@Table(name = "AppUserRole")
public class AppUserRole implements java.io.Serializable {

	private int appUserRoleId;
	private AppUser appUser;
	private Role role;

	public AppUserRole() {
	}

	public AppUserRole(int appUserRoleId, AppUser appUser, Role role) {
		this.appUserRoleId = appUserRoleId;
		this.appUser = appUser;
		this.role = role;
	}

	@Id
    @GeneratedValue
	@Column(name = "AppUserRoleId", unique = true, nullable = false)
	public int getAppUserRoleId() {
		return this.appUserRoleId;
	}

	public void setAppUserRoleId(int appUserRoleId) {
		this.appUserRoleId = appUserRoleId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AppUser", nullable = false)
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Role", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
