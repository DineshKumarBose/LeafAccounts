package com.leafsoft.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the UserRoles database table.
 * 
 */
@Entity
@Table(name="UserRoles")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_role_id")
	private int userRoleId;

	private String rolename;

	//bi-directional many-to-one association to LeafUser
	@ManyToOne
	@JoinColumn(name="lid")
	private LeafUser leafUser;

	public UserRole() {
	}

	public int getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public LeafUser getLeafUser() {
		return this.leafUser;
	}

	public void setLeafUser(LeafUser leafUser) {
		this.leafUser = leafUser;
	}

}