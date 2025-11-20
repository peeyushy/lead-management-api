package com.fhc.leadmanagement.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "subscriptions")
public class Subscriptions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(mappedBy = "subscriptions")
	@JsonBackReference
	private Set<User> users = new HashSet<>();

	@Column(name = "endpoint", nullable = false, length = 2048)
	private String endpoint;

	@Column(name = "p256dh", nullable = false, length = 255)
	private String p256dh;

	@Column(name = "auth", nullable = false, length = 255)
	private String auth;

	@Column(name = "device_type", length = 255)
	private String deviceType; // e.g., "Chrome Desktop", "Firefox Mobile", "iOS Safari"

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	public Subscriptions() {
		this.createdAt = LocalDateTime.now();
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
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
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * @param endpoint the endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * @return the p256dh
	 */
	public String getP256dh() {
		return p256dh;
	}

	/**
	 * @param p256dh the p256dh to set
	 */
	public void setP256dh(String p256dh) {
		this.p256dh = p256dh;
	}

	/**
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * @param auth the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}

	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Subscriptions))
			return false;
		Subscriptions that = (Subscriptions) o;
		return Objects.equals(endpoint, that.endpoint);
	}

	@Override
	public int hashCode() {
		return Objects.hash(endpoint);
	}
}