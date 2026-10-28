package com.hsptl.bed.dto;

import java.time.LocalDateTime;

import com.hsptl.bed.entity.Bed;

public class BedAssignmentHistoryResponseDTO {
	
	private Long bedId;
	
	private Long bedNum;
	
	private Bed bed;
	
	private Long patientId;
	
	private LocalDateTime assignedAt;
	
	private LocalDateTime vacatedAt;

}
