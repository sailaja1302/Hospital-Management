package com.hsptl.staff.dto;

import com.hsptl.staff.constants.Specialization;
import com.hsptl.staff.entity.Staff;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponseDTO {
	
	private Long doctorId;
	
	private Staff staff;
	
	private Specialization specialization;
	
	private Boolean isActive;


}
