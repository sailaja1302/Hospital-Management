package com.hsptl.staff.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsptl.staff.constants.Specialization;
import com.hsptl.staff.constants.StaffType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Staff {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long staffId;
	
	private String firstname;
	
	private String lastname;
	
	private String phnnum;
	
	@Enumerated(EnumType.STRING)
	private StaffType staffType;
	
	private String role;
	
	private String gender;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name="spec",length=50)
	private Specialization spec;
	
	private LocalDate dateOfJoining;
	
	private float experienceInYears;
	
	@Column(name = "can_login")
	private Boolean canLogin;
	
	@Column(name = "isEmployeeActive")
	private Boolean isEmployeeActive;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="staff_details_id")
	private StaffDetails staffDetails;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="staff_address_id")
	private StaffAddress staffAddress;

	public Staff(String firstname, String lastname, String phnnum, StaffType stafftype, String role, String gender,
			Specialization spec, LocalDate dateOfJoining, float experienceInYears, boolean canLogin, boolean isEmployeeActive,
			StaffDetails staffDetails, StaffAddress staffAdd) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.phnnum = phnnum;
		this.staffType = stafftype;
		this.role = role;
		this.gender = gender;
		this.spec = spec;
		this.dateOfJoining = dateOfJoining;
		this.experienceInYears = experienceInYears;
		this.canLogin = canLogin;
		this.isEmployeeActive = isEmployeeActive;
		this.staffDetails = staffDetails;
		this.staffAddress = staffAdd;
	}
		
		
}
