package com.hsptl.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientAddressResponseDTO {
	
	private Long patientAddressId;
	
	private  String doorNum;
	
	private String landmark;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String pincode;

}
