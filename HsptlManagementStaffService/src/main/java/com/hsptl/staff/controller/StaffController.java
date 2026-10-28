package com.hsptl.staff.controller;

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

import com.hsptl.staff.dto.RegisterStaffRequestDTO;
import com.hsptl.staff.dto.StaffResponseDTO;
import com.hsptl.staff.service.StaffService;

@RestController
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	StaffService staffService;
	
	@PostMapping("/addStaff")
	public ResponseEntity<StaffResponseDTO> registerStaff(@RequestBody RegisterStaffRequestDTO registerStaffDto) {
		return new ResponseEntity(staffService.registerStaff(registerStaffDto),HttpStatus.CREATED); 
	}

	@GetMapping("/get/{staffId}")
	public ResponseEntity<StaffResponseDTO> getStaffByStaffId(@PathVariable Long staffId) {
		return new ResponseEntity<>(staffService.getStaffByStaffId(staffId),HttpStatus.OK);
	}
	
	@PutMapping("/update/{staffId}")
	public ResponseEntity<StaffResponseDTO> updateStaffById(@RequestBody RegisterStaffRequestDTO registerStaffRequestDTO , @PathVariable Long staffId) {
		return new ResponseEntity<>(staffService.updateStaffById(registerStaffRequestDTO,staffId),HttpStatus.OK);
	} 
	
	@GetMapping("get/name/{searchName}")
	public ResponseEntity<List<StaffResponseDTO>> getStaffByName(@PathVariable String searchName) {
		return  new ResponseEntity<>(staffService.getStaffByStaffName(searchName),HttpStatus.OK);
	}
	
	@DeleteMapping("del/{staffId}")
	public ResponseEntity<String> deleteStaffByStaffId(@PathVariable Long staffId) {
		return new ResponseEntity<>(staffService.deleteStaffByStaffId(staffId),HttpStatus.OK);
	}
	
	@GetMapping("/allStaff")
	public List<StaffResponseDTO> getAllStaff(){
		return staffService.getAllStaff();
		
	}
	
	@GetMapping("/get/specialization/{spec}")
	public ResponseEntity<List<StaffResponseDTO>> getStaffBySpec(@PathVariable String spec){
		return new ResponseEntity<>(staffService.getStaffBySpec(spec),HttpStatus.OK);
	}
	
	@GetMapping("/get/doc/nonDoc/{staffType}")
	public ResponseEntity<List<StaffResponseDTO>> getStaffByStaffType(@PathVariable String staffType){
		return new ResponseEntity<>(staffService.getStaffByStaffType(staffType),HttpStatus.OK);
	}
	
}
