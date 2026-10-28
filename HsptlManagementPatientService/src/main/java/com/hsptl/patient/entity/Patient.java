package com.hsptl.patient.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsptl.patient.constants.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="patient")
@Builder
public class Patient {

	@Id
	@Column(name="patient_id")
	@JsonProperty
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long patientId;
	
	private String patientName;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private String patientEmail;
	
	private String patientPhoneNum;
	
	@OneToOne(cascade=CascadeType.ALL)
	private PatientAddress patientAddress;
	
	@JsonFormat(pattern = "dd-mm-yyyy")
	private LocalDate dateOfBirth;

	public Patient(String name, Gender gender, String email, String patientPhoneNum, PatientAddress patientAddress,
			LocalDate dateOfBirth) {
		super();
		this.patientName = name;
		this.gender = gender;
		this.patientEmail = email;
		this.patientPhoneNum = patientPhoneNum;
		this.patientAddress = patientAddress;
		this.dateOfBirth = dateOfBirth;
	}
	

}
