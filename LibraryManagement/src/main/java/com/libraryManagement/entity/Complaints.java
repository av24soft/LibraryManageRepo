package com.libraryManagement.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Complaints {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int complaintsId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserDetails user;

	private String complaintDescription;

	private String complaintType;

	private String complaintStatus;

	private LocalDate complaintRaisedDate;
	private LocalDate complaintResolvedDate;
	public int getComplaintsId() {
		return complaintsId;
	}
	public void setComplaintsId(int complaintsId) {
		this.complaintsId = complaintsId;
	}
	public UserDetails getUser() {
		return user;
	}
	public void setUser(UserDetails user) {
		this.user = user;
	}
	public String getComplaintDescription() {
		return complaintDescription;
	}
	public void setComplaintDescription(String complaintDescription) {
		this.complaintDescription = complaintDescription;
	}
	public String getComplaintType() {
		return complaintType;
	}
	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}
	public String getComplaintStatus() {
		return complaintStatus;
	}
	public void setComplaintStatus(String complaintStatus) {
		this.complaintStatus = complaintStatus;
	}
	public LocalDate getComplaintRaisedDate() {
		return complaintRaisedDate;
	}
	public void setComplaintRaisedDate(LocalDate complaintRaisedDate) {
		this.complaintRaisedDate = complaintRaisedDate;
	}
	public LocalDate getComplaintResolvedDate() {
		return complaintResolvedDate;
	}
	public void setComplaintResolvedDate(LocalDate complaintResolvedDate) {
		this.complaintResolvedDate = complaintResolvedDate;
	}
	public Complaints(int complaintsId, UserDetails user, String complaintDescription, String complaintType,
			String complaintStatus, LocalDate complaintRaisedDate, LocalDate complaintResolvedDate) {
		super();
		this.complaintsId = complaintsId;
		this.user = user;
		this.complaintDescription = complaintDescription;
		this.complaintType = complaintType;
		this.complaintStatus = complaintStatus;
		this.complaintRaisedDate = complaintRaisedDate;
		this.complaintResolvedDate = complaintResolvedDate;
	}
	public Complaints() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
