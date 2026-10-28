package com.hsptl.staff.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsptl.staff.dao.DoctorRepository;
import com.hsptl.staff.dto.DoctorResponseDTO;
import com.hsptl.staff.entity.Doctor;
import com.hsptl.staff.exception.DoctorNotFoundException;
import com.hsptl.staff.service.DoctorService;

@Service
public class DoctorServiceimpl implements DoctorService {
	
	@Autowired
	DoctorRepository doctorRepository;
	@Override
	public DoctorResponseDTO getDoctorByDoctorId(Long doctorId) {
		Doctor doctor = doctorRepository
									.findById(doctorId)
									.orElseThrow(()-> new DoctorNotFoundException("Doctor not found with ID :"+doctorId));
		
		DoctorResponseDTO doctoeScheduleResponse = new DoctorResponseDTO();
		
		BeanUtils.copyProperties(doctor, doctoeScheduleResponse);
		
		
		return doctoeScheduleResponse;
	}
	@Override
	public Boolean isDoctorAvailableByDoctorId(Long doctorId) {
		Doctor doctor = doctorRepository.findById(doctorId)
						.orElseThrow(()-> new DoctorNotFoundException("Doctor not found with ID :"+doctorId));
		
		
		if(doctor.getIsActive() == true) {
			return true;
		}
		
		else {
			return false;
		}
	}


}
