package com.hsptl.staff.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.hsptl.staff.builder.StaffBuilder;
import com.hsptl.staff.constants.Specialization;
import com.hsptl.staff.constants.StaffType;
import com.hsptl.staff.dao.DoctorRepository;
import com.hsptl.staff.dao.StaffRepository;
import com.hsptl.staff.dto.RegisterStaffRequestDTO;
import com.hsptl.staff.dto.StaffResponseDTO;
import com.hsptl.staff.entity.Doctor;
import com.hsptl.staff.entity.Staff;
import com.hsptl.staff.exception.StaffNotFoundException;
import com.hsptl.staff.service.StaffService;

@Service

public class StaffServiceImpl implements StaffService {
	
	@Autowired
	StaffRepository staffRepository;

	@Autowired
	DoctorRepository doctorRepository;
	
	@Override
	public StaffResponseDTO registerStaff(RegisterStaffRequestDTO registerStaffDto) {
		
		String email = registerStaffDto.getEmail();
		String phoneNum = registerStaffDto.getPhnnum();
		
		if(phoneNum != null && !phoneNum.trim().isEmpty() ) {
			boolean existsPhnnum = staffRepository.existsByPhnnum(phoneNum);
			if(existsPhnnum) {
				throw new IllegalArgumentException("Phone Number :"+phoneNum+" already exists..");
			}
		}
			else {
				throw new IllegalArgumentException("Phone number is mandatory for registration .");
			}
		
		if (email != null && !email.trim().isEmpty() ){
			boolean emailExists = staffRepository.existsByEmail(email);
			if(emailExists) {
				throw new IllegalArgumentException("Email : "+ email+"already exists");
			}
		}
			else {
				throw new IllegalArgumentException("Email is mandatory for registration .");
			}
		
		
		Staff staffFromRegisterStaffDto = StaffBuilder.buildStaffFromRegisterStaffDto(registerStaffDto);
		Staff savedStaff = staffRepository.save(staffFromRegisterStaffDto);
		
		if(StaffType.DOCTOR.equals(registerStaffDto.getStaffType())) {
			Doctor doctor = new Doctor();
			
			doctor.setStaff(savedStaff);
			doctor.setSpecialization(registerStaffDto.getSpecialization());
			doctor.setIsActive(true);
			
			doctorRepository.save(doctor);
		}
		
		StaffResponseDTO staffResponseDTOFromStaff = StaffBuilder.buildStaffResponseDTOFromStaff(savedStaff);
		
		return staffResponseDTOFromStaff;
	}

	@Override
	public StaffResponseDTO getStaffByStaffId(Long staffId) {
		Staff staffFromRepo = staffRepository.findById(staffId)
				.orElseThrow(() -> new StaffNotFoundException("Staff member not found with ID: " + staffId));
		
		StaffResponseDTO staffResponseDTOFromStaff = StaffBuilder.buildStaffResponseDTOFromStaff(staffFromRepo);
		
		return staffResponseDTOFromStaff;
	}

	@Override
	public StaffResponseDTO updateStaffById(RegisterStaffRequestDTO registerStaffRequestDto, Long staffId) {
		
		Staff staffById = staffRepository.findById(staffId)
				.orElseThrow(() ->new StaffNotFoundException("Staff member not found with ID :"+staffId));
		
		String email = registerStaffRequestDto.getEmail();
		String phoneNum = registerStaffRequestDto.getPhnnum();
		
		if (email != null && !email.trim().isEmpty() &&! email.equalsIgnoreCase(staffById.getEmail())){
			boolean emailExists = staffRepository.existsByEmail(registerStaffRequestDto.getEmail());
			if(emailExists) {
				throw new IllegalArgumentException("Email : "+ email+"already exists");
			}
			staffById.setEmail(email);
		}
		
		if(phoneNum != null && !phoneNum.trim().isEmpty() && ! phoneNum.equals(staffById.getPhnnum())) {
			boolean existsPhnnum = staffRepository.existsByPhnnum(phoneNum);
			if(existsPhnnum) {
				throw new IllegalArgumentException("Phone Number :"+phoneNum+" already exists..");
			}
			staffById.setPhnnum(phoneNum);
		}
		
		if(StaffType.DOCTOR.equals(registerStaffRequestDto.getStaffType())) {
			
			Boolean existsByStaff = doctorRepository
									.existsByStaff(staffById);
			
			if(!existsByStaff) {
				Doctor doctor = new Doctor();
				doctor.setStaff(staffById);
				doctor.setSpecialization(registerStaffRequestDto.getSpecialization());
				doctor.setIsActive(true);
				doctorRepository.save(doctor);
			}
			
		}
		
		Staff updatedStaff = StaffBuilder.buildStaffFromRegisterStaffDto(registerStaffRequestDto);
		
		Staff savedStaff = staffRepository.save(updatedStaff);
		StaffResponseDTO staffResponseDTOFromStaff = StaffBuilder.buildStaffResponseDTOFromStaff(savedStaff);
		return staffResponseDTOFromStaff;
	}
	
	
	public List<StaffResponseDTO> getStaffByStaffName(@PathVariable String searchName) {
		
		try {
		
		List<StaffResponseDTO> staffByStaffLastname = staffRepository
				.findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(searchName,searchName)
				.stream()
				.map(StaffBuilder :: buildStaffResponseDTOFromStaff)
				.toList();
	
		return staffByStaffLastname;
	}
		catch(IllegalArgumentException e) {
			throw new StaffNotFoundException("Staff member not found with ID :"+searchName);

		}
			
		}
	
	public String deleteStaffByStaffId(Long staffId) {
		Staff staff = staffRepository.findById(staffId)
				.orElseThrow(() -> new StaffNotFoundException("Staff member not found with ID :"+staffId));
		
		staff.setIsEmployeeActive(false);
		staff.setCanLogin(false);
		
		if(staff.getStaffDetails() != null) {
			staff.getStaffDetails().setCanLogin(false);
			staff.getStaffDetails().setEmployeeActive(false);
		}
		staffRepository.save(staff);
		return "Deleted staff by ID : " + staffId;
	}
	
	public List<StaffResponseDTO> getAllStaff() {
	
		List<StaffResponseDTO> staffMembers = staffRepository
									.findAll()
									.stream()
									.map(StaffBuilder::buildStaffResponseDTOFromStaff)
									.toList();
		return staffMembers;
		
	}

	@Override
	public List<StaffResponseDTO> getStaffBySpec(String spec) {
		try {
			
		Specialization specialization = Specialization.valueOf(spec.toUpperCase().trim());
		List<StaffResponseDTO> staffBySpec = staffRepository
				.findBySpec(specialization)
				.stream()
				.map(StaffBuilder::buildStaffResponseDTOFromStaff)
				.toList();
		return staffBySpec;
		
		
	} catch(IllegalArgumentException e ) {
		
		throw new StaffNotFoundException("Staff members not found with Specialization :"+spec);

	}
		
	}

}
