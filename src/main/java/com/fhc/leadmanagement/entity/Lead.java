package com.fhc.leadmanagement.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fhc.leadmanagement.entity.enums.LeadSource;
import com.fhc.leadmanagement.entity.enums.LeadStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "leads")
public class Lead {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "contactno")
	private String contactno;

	@Column(name = "budget")
	private String budget;

	@Column(name = "project")
	private String project;

	@Column(name = "requirement")
	private String requirement;

	@Enumerated(EnumType.STRING)
	private LeadStatus status;

	@Enumerated(EnumType.STRING)
	private LeadSource source;

	@Column(name = "assigned_user_id")
	private Long assignedUserId;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "createdByUserId")
	private Long createdByUserId;

	@Column(name = "updatedByUserId")
	private Long updatedByUserId;

	// One-to-One relationship to LeadDetails
	@OneToOne(mappedBy = "lead", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private LeadDetails leadDetails;

	@OneToMany(mappedBy = "lead", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<LeadNote> notes = new ArrayList<>();

	// Helper method to add note
	public void addNote(LeadNote note) {
		notes.add(note);
		note.setLead(this);
	}

	// Helper method to remove note
	public void removeNote(LeadNote note) {
		notes.remove(note);
		note.setLead(null);
	}

	public Lead() {
		this.createdAt = LocalDateTime.now();
		this.status = LeadStatus.HOT;
	}

	public void addLeadDetails(LeadDetails details) {
		this.leadDetails = details;
		details.setLead(this);
	}

	public void removeLeadDetails() {
		if (this.leadDetails != null) {
			this.leadDetails.setLead(null);
			this.leadDetails = null;
		}
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return the contactno
	 */
	public String getContactno() {
		return contactno;
	}

	/**
	 * @param contactno the contactno to set
	 */
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	/**
	 * @return the budget
	 */
	public String getBudget() {
		return budget;
	}

	/**
	 * @param budget the budget to set
	 */
	public void setBudget(String budget) {
		this.budget = budget;
	}

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the requirement
	 */
	public String getRequirement() {
		return requirement;
	}

	/**
	 * @param requirement the requirement to set
	 */
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	/**
	 * @return the status
	 */
	public LeadStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(LeadStatus status) {
		this.status = status;
	}

	/**
	 * @return the source
	 */
	public LeadSource getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(LeadSource source) {
		this.source = source;
	}

	/**
	 * @return the assignedUserId
	 */
	public Long getAssignedUserId() {
		return assignedUserId;
	}

	/**
	 * @param assignedUserId the assignedUserId to set
	 */
	public void setAssignedUserId(Long assignedUserId) {
		this.assignedUserId = assignedUserId;
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
	 * @return the leadDetails
	 */
	public LeadDetails getLeadDetails() {
		return leadDetails;
	}

	/**
	 * @param leadDetails the leadDetails to set
	 */
	public void setLeadDetails(LeadDetails leadDetails) {
		this.leadDetails = leadDetails;
	}

	/**
	 * @return the notes
	 */
	public List<LeadNote> getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(List<LeadNote> notes) {
		this.notes = notes;
	}

	// equals and hashCode based ONLY on id:
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Lead))
			return false;
		Lead lead = (Lead) o;
		return id != null && id.equals(lead.id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	// toString with only id and simple fields, no notes:
	@Override
	public String toString() {
		return "Lead{" + "id=" + id +
		/* other simple fields here */
				'}';
	}
}
