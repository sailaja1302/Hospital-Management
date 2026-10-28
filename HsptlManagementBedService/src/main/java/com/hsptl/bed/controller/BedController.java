package com.hsptl.bed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsptl.bed.dto.BedRequestDTO;
import com.hsptl.bed.dto.BedResponseDTO;
import com.hsptl.bed.service.BedService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bed")
public class BedController {
	
	@Autowired
	BedService bedService;
	
	@PostMapping("/addBed")
	public ResponseEntity<BedResponseDTO> addBedInRoom(@RequestBody BedRequestDTO bedRequestDto){
		return new ResponseEntity<>(bedService.addBedInRoom(bedRequestDto),HttpStatus.CREATED);
	}
	
	@PutMapping("/updateBed/{roomNum}/{bedId}")
	public ResponseEntity<BedResponseDTO> updateBedInRoom(@RequestBody BedRequestDTO bedRequestDto, @PathVariable Long bedId, @PathVariable Long roomNum){
		return new ResponseEntity<>(bedService.updateBedInRoom(bedRequestDto,bedId,roomNum),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteBed/{roomNum}/{bedId}")
	public ResponseEntity<String> deleteBedInRoom(@PathVariable Long roomNum, @PathVariable Long bedId){
		return new ResponseEntity<>(bedService.deleteBedInRoom(roomNum,bedId),HttpStatus.OK);
	}
	
	@GetMapping("/getAllBeds")
	public ResponseEntity<List<BedResponseDTO>> getAllBeds(){
		return new ResponseEntity<>(bedService.getAllBeds(),HttpStatus.OK);
	}
	
	@GetMapping("/getVacantBeds/{roomNum}")
	public ResponseEntity<List<BedResponseDTO>> getAllVacantBeds(@PathVariable Long roomNum){
		return new ResponseEntity<>(bedService.getAllVacantBeds(roomNum),HttpStatus.OK);
	}
	

}
