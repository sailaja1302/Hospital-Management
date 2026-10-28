package com.hsptl.staff.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorScheduleAvailableDatesDTO {
	
	private Long staffId;
	
	private Long doctorId;
	
	private List<LocalDateTime> availableDates;

}
