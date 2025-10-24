package com.fhc.leadmanagement.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lead_notes")
public class LeadNote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Each note is associated with a single Lead
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lead_id", nullable = false)
	@JsonBackReference
	private Lead lead;

	@Column(name = "note", columnDefinition = "TEXT", nullable = false)
	private String note;

	// User who created the note
	private Long createdByUserId;

	// User who last updated the note
	private Long updatedByUserId;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	// Constructors, getters, and setters
	public LeadNote() {
		this.createdAt = LocalDateTime.now();
	}

	// Convenience constructor to initialize important fields
	public LeadNote(Lead lead, Long createdBy, String note) {
		this.lead = lead;
		this.createdByUserId = createdBy;
		this.updatedByUserId = createdBy;
		this.note = note;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
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
	 * @return the lead
	 */
	public Lead getLead() {
		return lead;
	}

	/**
	 * @param lead the lead to set
	 */
	public void setLead(Lead lead) {
		this.lead = lead;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the createdByUserId
	 */
	public Long getCreatedByUserId() {
		return createdByUserId;
	}

	/**
	 * @param createdByUserId the createdByUserId to set
	 */
	public void setCreatedByUserId(Long createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	/**
	 * @return the updatedByUserId
	 */
	public Long getUpdatedByUserId() {
		return updatedByUserId;
	}

	/**
	 * @param updatedByUserId the updatedByUserId to set
	 */
	public void setUpdatedByUserId(Long updatedByUserId) {
		this.updatedByUserId = updatedByUserId;
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
	 * @return the updatedAt
	 */
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((createdByUserId == null) ? 0 : createdByUserId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lead == null) ? 0 : lead.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
		result = prime * result + ((updatedByUserId == null) ? 0 : updatedByUserId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		LeadNote other = (LeadNote) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (createdByUserId == null) {
			if (other.createdByUserId != null)
				return false;
		} else if (!createdByUserId.equals(other.createdByUserId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lead == null) {
			if (other.lead != null)
				return false;
		} else if (!lead.equals(other.lead))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		if (updatedByUserId == null) {
			if (other.updatedByUserId != null)
				return false;
		} else if (!updatedByUserId.equals(other.updatedByUserId))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LeadNote [id=" + id + ", lead=" + lead + ", note=" + note + ", createdByUserId=" + createdByUserId
				+ ", updatedByUserId=" + updatedByUserId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ "]";
	}
}
