package com.hsptl.bed.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.hsptl.bed.dto.BedRequestDTO;
import com.hsptl.bed.dto.BedResponseDTO;

public interface BedService {
	
	public BedResponseDTO addBedInRoom(@RequestBody BedRequestDTO bedRequestDto);
	
	public BedResponseDTO updateBedInRoom(@RequestBody BedRequestDTO bedRequestDto, @PathVariable Long bedId, @PathVariable Long roomNum);
	
	public String deleteBedInRoom(@PathVariable Long roomNum, @PathVariable Long bedId);

	public List<BedResponseDTO> getAllBeds();
	
	public List<BedResponseDTO> getAllVacantBeds(@PathVariable Long roomNum);
}
