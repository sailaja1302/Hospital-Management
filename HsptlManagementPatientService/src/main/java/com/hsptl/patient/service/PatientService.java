package com.hsptl.patient.service;

import java.time.LocalDateTime;
import java.util.List;

import com.hsptl.patient.dto.PatientRegisterRequestDTO;
import com.hsptl.patient.dto.PatientResponseDTO;
import com.hsptl.patient.entity.Patient;

public interface PatientService {
	
	public PatientResponseDTO patientResponseFromPatientRegisterRequestDTO(PatientRegisterRequestDTO patientRequest);

	public PatientResponseDTO updatePatientFromPatientRegisterRequestDTO(PatientRegisterRequestDTO patientRequest ,Long patientId);
	
	public List<PatientResponseDTO> getAllPatients();
	
	public PatientResponseDTO getPatientById(Long patientId);
	
	public PatientResponseDTO getPatientByName(String name);
	
	public List<PatientResponseDTO> getPatientByDoctorAndDatesRange(Long staffId,LocalDateTime startDateTime,LocalDateTime endDateTime); 
}
