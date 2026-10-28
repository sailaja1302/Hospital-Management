package com.hsptl.patient.dto;

import java.time.LocalDate;

import com.hsptl.patient.constants.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientRegisterRequestDTO {
	
	private String patientName;
	
	private Gender gender;
	
	private String patientEmail;
	
	private String patientPhoneNum;
	
	private LocalDate dateOfBirth;
	
	private PatientAddressRequestDTO patientAddressRequestDto;

}

