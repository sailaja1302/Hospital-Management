package com.hsptl.staff.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DoctorScheduleUnavailableDatesDTO {
	
	private Long staffId;
	
	private Long doctorId;
	
	private List<LocalDateTime> unavailableDates;
	
	

}
