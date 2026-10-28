package com.hsptl.staff.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.hsptl.staff.dto.DoctorResponseDTO;

public interface DoctorService {
	
	public DoctorResponseDTO getDoctorByDoctorId(@PathVariable Long doctorId);

	public Boolean isDoctorAvailableByDoctorId(@PathVariable Long doctorId);
	
}
