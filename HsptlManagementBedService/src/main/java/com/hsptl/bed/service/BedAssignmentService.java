package com.hsptl.bed.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.hsptl.bed.dto.BedAssignmentHistoryResponseDTO;
import com.hsptl.bed.dto.BedRequestDTO;

public interface BedAssignmentService {
	
	public String assignBedToPatient(@PathVariable Long bedNum, @PathVariable Long patientId );
	
	public String vacateBedInRoom(@PathVariable Long bedNumber, @PathVariable Long roomNum );

}
