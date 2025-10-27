package com.fhc.leadmanagement.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fhc.leadmanagement.entity.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	private String fullname;

	private String email;

	private LocalDateTime createdat;

	private LocalDateTime updatedat;

	// Link to team leader for EXECUTIVE users; null for others
	@Column(name = "team_leader_id")
	private Long teamleaderid;

	// One-to-Many relationship with Lead (read-only)
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "assigned_user_id", referencedColumnName = "id", insertable = false, updatable = false)
	private List<Lead> assignedLeads = new ArrayList<>();

	public User() {
		this.createdat = LocalDateTime.now();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the createdat
	 */
	public LocalDateTime getCreatedat() {
		return createdat;
	}

	/**
	 * @param createdat the createdat to set
	 */
	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}

	/**
	 * @return the updatedat
	 */
	public LocalDateTime getUpdatedat() {
		return updatedat;
	}

	/**
	 * @param updatedat the updatedat to set
	 */
	public void setUpdatedat(LocalDateTime updatedat) {
		this.updatedat = updatedat;
	}

	/**
	 * @return the teamleaderid
	 */
	public Long getTeamleaderid() {
		return teamleaderid;
	}

	/**
	 * @param teamleaderid the teamleaderid to set
	 */
	public void setTeamleaderid(Long teamleaderid) {
		this.teamleaderid = teamleaderid;
	}

	/**
	 * @return the assignedLeads
	 */
	public List<Lead> getAssignedLeads() {
		return assignedLeads;
	}

	/**
	 * @param assignedLeads the assignedLeads to set
	 */
	public void setAssignedLeads(List<Lead> assignedLeads) {
		this.assignedLeads = assignedLeads;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assignedLeads == null) ? 0 : assignedLeads.hashCode());
		result = prime * result + ((createdat == null) ? 0 : createdat.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((teamleaderid == null) ? 0 : teamleaderid.hashCode());
		result = prime * result + ((updatedat == null) ? 0 : updatedat.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (assignedLeads == null) {
			if (other.assignedLeads != null)
				return false;
		} else if (!assignedLeads.equals(other.assignedLeads))
			return false;
		if (createdat == null) {
			if (other.createdat != null)
				return false;
		} else if (!createdat.equals(other.createdat))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		if (teamleaderid == null) {
			if (other.teamleaderid != null)
				return false;
		} else if (!teamleaderid.equals(other.teamleaderid))
			return false;
		if (updatedat == null) {
			if (other.updatedat != null)
				return false;
		} else if (!updatedat.equals(other.updatedat))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", fullname="
				+ fullname + ", email=" + email + ", createdat=" + createdat + ", updatedat=" + updatedat
				+ ", teamleaderid=" + teamleaderid + ", assignedLeads=" + assignedLeads + "]";
	}
}