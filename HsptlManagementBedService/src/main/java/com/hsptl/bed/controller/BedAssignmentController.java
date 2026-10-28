package com.hsptl.bed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsptl.bed.dto.BedAssignmentHistoryResponseDTO;
import com.hsptl.bed.dto.BedRequestDTO;
import com.hsptl.bed.service.BedAssignmentService;

@RestController
@RequestMapping("/bedAssign")
public class BedAssignmentController {
	
	@Autowired
	BedAssignmentService bedAssignmentService;
	
	@PostMapping("/assignBed/{bedNumber}/{patientId}")
	public ResponseEntity<String> assignBedToPatient(@PathVariable Long bedNumber, @PathVariable Long patientId ){
		return new ResponseEntity<>(bedAssignmentService.assignBedToPatient(bedNumber,patientId),HttpStatus.OK);
	}

	@PostMapping("vacateBed/{roomNum}/{bedNumber}")
	public ResponseEntity<String> vacateBedInRoom(@PathVariable Long bedNumber, @PathVariable Long roomNum ){
		return new ResponseEntity<>(bedAssignmentService.vacateBedInRoom(bedNumber,roomNum),HttpStatus.OK);
	}

}
