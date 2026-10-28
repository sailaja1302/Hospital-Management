package com.hsptl.staff.dto;

import java.time.LocalDate;

import com.hsptl.staff.constants.Specialization;
import com.hsptl.staff.constants.StaffType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffDetailsResponseDTO {
	
	private Long staffId;
	
	private String firstname;
	
	private String lastname;
	
	private String phoneNumber;
	
	private String role;
	
	private String gender;
	
	private LocalDate dateOfJoining;
	
	private float experienceInYears;
	
	private String email;
	
	private Specialization spec;
	
	private StaffType staffType;
	
	private boolean isEmployeeActive;
	
	private boolean canLogin;
	
	private StaffAddressResponseDTO staffAddressResponse;
	
}
