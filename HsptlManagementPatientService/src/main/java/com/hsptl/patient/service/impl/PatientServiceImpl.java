package com.hsptl.patient.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.hsptl.patient.builder.PatientBuilder;
import com.hsptl.patient.client.AppointmentClient;
import com.hsptl.patient.dao.PatientRepository;
import com.hsptl.patient.dto.PatientRegisterRequestDTO;
import com.hsptl.patient.dto.PatientResponseDTO;
import com.hsptl.patient.entity.Patient;
import com.hsptl.patient.exception.PatientNotFoundException;
import com.hsptl.patient.service.PatientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{
	
	private final PatientRepository patientRepository;
	@Autowired
	AppointmentClient appointmentClient;
	
	public PatientResponseDTO patientResponseFromPatientRegisterRequestDTO(PatientRegisterRequestDTO patientRequest) {
		
		String patientEmail = patientRequest.getPatientEmail();
		String patientPhoneNumb = patientRequest.getPatientPhoneNum();
		
		if(patientEmail != null && ! patientEmail.trim().isEmpty() && !patientEmail.equalsIgnoreCase(patientEmail)) {
			boolean emailExists = patientRepository.existsByPatientEmail(patientEmail);
			if(emailExists) {
				throw new IllegalArgumentException("Email already registered with ID : "+patientEmail);
			}
		}
		else {
			throw new IllegalArgumentException("Email is mandatory for registration");
		}
		
		if(patientPhoneNumb != null && !patientPhoneNumb.trim().isEmpty() && !patientPhoneNumb.equals(patientPhoneNumb)) {
			boolean phoneNumExists = patientRepository.existsByPatientPhoneNum(patientPhoneNumb);
			if(phoneNumExists) {
				throw new IllegalArgumentException("Phone number already registered : "+patientPhoneNumb);
			}
		}
		else {
			throw new IllegalArgumentException("Phone number is mandatory for registration");
		}
		
		Patient patientFromPatientRegisterRequestDto = PatientBuilder.buildPatientFromPatientRegisterRequestDto(patientRequest);
		
		Patient savedPatient = patientRepository.save(patientFromPatientRegisterRequestDto);
		
		PatientResponseDTO patientResponseFromPatient = PatientBuilder.buildPatientResponseFromPatient(savedPatient);
		return patientResponseFromPatient;
		
	}

	public PatientResponseDTO updatePatientFromPatientRegisterRequestDTO(PatientRegisterRequestDTO patientRequest, Long patientId) {
		
		Patient patient = patientRepository
							.findById(patientId)
							.orElseThrow(() -> new PatientNotFoundException("patient not found with ID : "+ patientId ) );
		
		String patientEmail = patientRequest.getPatientEmail();
		String patientPhoneNumb = patientRequest.getPatientPhoneNum();
		
		if(patientEmail != null && !patientEmail.trim().isEmpty() && ! patientEmail.equalsIgnoreCase(patient.getPatientEmail()) ) {
			Boolean emailExists = patientRepository.existsByPatientEmail(patientEmail);
			if(emailExists) {
				throw new IllegalArgumentException("Email already exists : "+patientEmail);
			}
		}
		
		if(patientPhoneNumb != null) {
			Boolean phoneNumExists = patientRepository.existsByPatientPhoneNum(patientPhoneNumb);
			if(phoneNumExists) {
				throw new IllegalArgumentException("Phone number already registered : "+patientPhoneNumb);
			}
		}
		Patient updatedPatient = PatientBuilder.updatePatientFromRegisterPatientRegisterRequestDTO(patientRequest,patient);
		
		Patient savedPatient = patientRepository.save(updatedPatient);
		PatientResponseDTO patientResponseFromPatient = PatientBuilder.buildPatientResponseFromPatient(savedPatient);
		return patientResponseFromPatient;
	}

	@Override
	public List<PatientResponseDTO> getAllPatients() {
		List<PatientResponseDTO> patients = patientRepository
												.findAll()
												.stream()
												.map(PatientBuilder :: buildPatientResponseFromPatient)
												.toList();
		return patients;
	}

	@Override
	public PatientResponseDTO getPatientById(Long patientId) {
		Patient patient = patientRepository.findById(patientId)
							.orElseThrow(() -> new PatientNotFoundException("Patient not found with ID : "+ patientId));
				
				PatientResponseDTO patientResponseFromPatient = PatientBuilder.buildPatientResponseFromPatient(patient);
		return patientResponseFromPatient;
	}

	@Override
	public PatientResponseDTO getPatientByName(String name) {
		Patient patientByName = patientRepository
							.findByIgnoreCasePatientName(name)
							.orElseThrow(() -> new PatientNotFoundException("Patient not found by name : "+name));
		PatientResponseDTO patient = PatientBuilder.buildPatientResponseFromPatient(patientByName);
		return patient;
	}

	@Override
	public List<PatientResponseDTO> getPatientByDoctorAndDatesRange(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
//		List<Patient> patientsList = patientRepository.findByIdIn(staffId);
		List<Long> patientIdsByDoctorAndDatesRange = appointmentClient.getPatientIdsByDoctorAndDatesRange(doctorId, startDateTime, endDateTime);
		
		if(patientIdsByDoctorAndDatesRange == null || patientIdsByDoctorAndDatesRange.isEmpty()) {
			return Collections.emptyList();
		}
		 List<PatientResponseDTO> patients = patientRepository
				 					.findAllById(patientIdsByDoctorAndDatesRange)
				 					.stream()
				 					.map(PatientBuilder::buildPatientResponseFromPatient)
				 					.toList();
		
		 
		 return patients;
	}
	
}
