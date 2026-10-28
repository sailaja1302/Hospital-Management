package com.hsptl.appointment.dto;

import java.time.LocalDate;

import com.hsptl.appointment.constants.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientResponseDTO {
	
	private Long patientId;
	private String patientName;
	private Gender gender;
	private String patientEmail;
	private String patientPhoneNum;
	private LocalDate dateOfBirth;
	private PatientAddressResponseDTO patientAddressResponseDto;
	
}
