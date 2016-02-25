package com.prolificidea.templates.tsw.domain;

import javax.persistence.*;

@Entity
@Table(name = "AppUserDetails")
public class AppUserDetails implements java.io.Serializable {

	private int appUserDetailsId;
	private String firstName;
	private String lastName;
	private String emailAddress;

	public AppUserDetails() {
	}

	public AppUserDetails(int appUserDetailsId, String firstName,
			String lastName, String emailAddress) {
		this.appUserDetailsId = appUserDetailsId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}

	@Id
    @GeneratedValue
	@Column(name = "AppUserDetailsId", unique = true, nullable = false)
	public int getAppUserDetailsId() {
		return this.appUserDetailsId;
	}

	public void setAppUserDetailsId(int appUserDetailsId) {
		this.appUserDetailsId = appUserDetailsId;
	}

	@Column(name = "FirstName", nullable = false, length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LastName", nullable = false, length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "EmailAddress", nullable = false, length = 100)
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
