package com.hsptl.staff.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorScheduleResponseDTO {
	
	private Long doctorId;
	
	private List<LocalDate> availabeDates;
	
	private List<LocalDate> unavailableDates;

}
