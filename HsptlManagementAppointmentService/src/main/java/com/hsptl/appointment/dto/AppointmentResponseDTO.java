package com.hsptl.appointment.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.hsptl.appointment.constants.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AppointmentResponseDTO {
	
	private String appointmentId;

	private Long patientId;
	
	private Long doctorId;
	
	private LocalDate appointmentDate;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	private Status status;
	
	private String notes;
	
	private String reasonForVisit;
	
	private String diagnosisSummary;
	
	private String prescription;
	
	private String medicines;
	
	private String notesForReceptionist;
	
	private String followUpSuggestion;
	
	private String dietPlan;

}
