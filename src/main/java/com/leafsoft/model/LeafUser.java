package com.leafsoft.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LeafUsers database table.
 * 
 */
@Entity
@Table(name="LeafUsers")
@NamedQuery(name="LeafUser.findAll", query="SELECT l FROM LeafUser l")
public class LeafUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int lid;

	@Temporal(TemporalType.DATE)
	private Date dob;

	private String email;

	private int enabled;

	private String password;

	private String username;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="leafUser")
	private List<UserRole> userRoles;

	public LeafUser() {
	}

	public int getLid() {
		return this.lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEnabled() {
		return this.enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setLeafUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setLeafUser(null);

		return userRole;
	}

}