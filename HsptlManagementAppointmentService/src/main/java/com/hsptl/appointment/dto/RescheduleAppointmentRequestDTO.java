package com.hsptl.appointment.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RescheduleAppointmentRequestDTO {
	
	private LocalDate appointmentDate;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	private String notes;
	
}
