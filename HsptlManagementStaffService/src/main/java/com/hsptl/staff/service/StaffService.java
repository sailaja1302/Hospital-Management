package com.hsptl.staff.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.hsptl.staff.dto.RegisterStaffRequestDTO;
import com.hsptl.staff.dto.StaffResponseDTO;


public interface StaffService {

	public StaffResponseDTO registerStaff(RegisterStaffRequestDTO registerStaffDto);
	
	public StaffResponseDTO getStaffByStaffId(@PathVariable Long staffId);
	
	public StaffResponseDTO updateStaffById(@RequestBody RegisterStaffRequestDTO registerStaffRequestDTO , @PathVariable Long staffId);
	
	public List<StaffResponseDTO> getStaffByStaffName(@PathVariable String searchName);
	
	public String deleteStaffByStaffId(@PathVariable Long staffId);
	
	public List<StaffResponseDTO> getAllStaff();
	
	public List<StaffResponseDTO> getStaffBySpec(String spec);
}
