package com.hsptl.bed.dto;

import java.time.LocalDateTime;

import com.hsptl.bed.entity.Bed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BedAssignmentHistoryResponseDTO {
	
	private Long bedId;
	
	private Long bedNum;
	
	private Bed bed;
	
	private Long patientId;
	
	private LocalDateTime assignedAt;
	
	private LocalDateTime vacatedAt;

}
