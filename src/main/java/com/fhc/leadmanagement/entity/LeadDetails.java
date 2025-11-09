package com.fhc.leadmanagement.entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lead_details")
public class LeadDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lead_id")
	@JsonBackReference
	private Lead lead;

	@Column(name = "whatsappno")
	private String whatsappno;

	@Column(name = "address")
	private String address;

	@Column(name = "dob")
	private String dob;

	/**
	 * @param lead
	 */
	public LeadDetails(Lead lead) {
		super();
		this.lead = lead;
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
	 * @return the whatsappno
	 */
	public String getWhatsappno() {
		return whatsappno;
	}

	/**
	 * @param whatsappno the whatsappno to set
	 */
	public void setWhatsappno(String whatsappno) {
		this.whatsappno = whatsappno;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, dob, id, whatsappno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeadDetails other = (LeadDetails) obj;
		return Objects.equals(address, other.address) && Objects.equals(dob, other.dob) && Objects.equals(id, other.id)
				&& Objects.equals(whatsappno, other.whatsappno);
	}

	@Override
	public String toString() {
		return "LeadDetails [id=" + id + ", whatsappno=" + whatsappno + ", address=" + address + ", dob=" + dob + "]";
	}
}
