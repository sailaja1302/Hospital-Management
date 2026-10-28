package com.hsptl.staff.dto;

import java.time.LocalDate;

import com.hsptl.staff.constants.Specialization;
import com.hsptl.staff.constants.StaffType;
import com.hsptl.staff.entity.StaffDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterStaffRequestDTO {
	
	private String firstname;
	
	private String lastname;
	
	private String phnnum;
	
	private String role;
	
	private String email;
	
	private String gender;
	
	private StaffType staffType;
	
	private Specialization specialization;
	
	private LocalDate dateOfJoining;
	
	private float experienceInYears;
	
	private StaffAddressRequestDTO staffAddressRequestDto;
	
	private StaffDetails staffDetails;
	
	

	
}
