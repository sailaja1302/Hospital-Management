package com.hsptl.staff.dto;

import java.time.LocalDate;

import com.hsptl.staff.constants.Specialization;
import com.hsptl.staff.constants.StaffType;
import com.hsptl.staff.entity.StaffAddress;
import com.hsptl.staff.entity.StaffDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffResponseDTO {
	
	private Long staffId;
	
	private String firstname;
	
	private String lastname;
	
	private String phnnum;
	
	private String role;
	
	private String gender;
	
	private LocalDate dateOfJoining;
	
	private float experienceInYears;
	
	private Specialization specialization;
	
	private String email;
	
	private StaffType staffType;
	
	private StaffAddress staffAddress;
	
	private StaffDetails staffDetails;

}
