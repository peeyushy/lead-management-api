package com.fhc.leadmanagement.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fhc.leadmanagement.entity.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "user_subscription_map", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "subscription_id"))
	@JsonManagedReference
	private Set<Subscriptions> subscriptions = new HashSet<>();

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

	/**
	 * @return the subscriptions
	 */
	public Set<Subscriptions> getSubscriptions() {
		return subscriptions;
	}

	/**
	 * @param subscriptions the subscriptions to set
	 */
	public void setSubscriptions(Set<Subscriptions> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(assignedLeads, createdat, email, fullname, id, password, role, subscriptions, teamleaderid,
				updatedat, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(assignedLeads, other.assignedLeads) && Objects.equals(createdat, other.createdat)
				&& Objects.equals(email, other.email) && Objects.equals(fullname, other.fullname)
				&& Objects.equals(id, other.id) && Objects.equals(password, other.password) && role == other.role
				&& Objects.equals(subscriptions, other.subscriptions)
				&& Objects.equals(teamleaderid, other.teamleaderid) && Objects.equals(updatedat, other.updatedat)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", fullname="
				+ fullname + ", email=" + email + ", createdat=" + createdat + ", updatedat=" + updatedat
				+ ", teamleaderid=" + teamleaderid + ", assignedLeads=" + assignedLeads + ", subscriptions="
				+ subscriptions + "]";
	}
}