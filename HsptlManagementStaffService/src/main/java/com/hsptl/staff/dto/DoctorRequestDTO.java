package com.hsptl.staff.dto;

import com.hsptl.staff.constants.Specialization;
import com.hsptl.staff.entity.Staff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorRequestDTO {
	
	private Staff staff;
	
	private Specialization specialization;
	
	private Boolean isActive;


}
