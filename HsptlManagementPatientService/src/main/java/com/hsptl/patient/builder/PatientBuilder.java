package com.hsptl.patient.builder;

import org.springframework.beans.BeanUtils;

import com.hsptl.patient.dto.PatientAddressRequestDTO;
import com.hsptl.patient.dto.PatientAddressResponseDTO;
import com.hsptl.patient.dto.PatientRegisterRequestDTO;
import com.hsptl.patient.dto.PatientResponseDTO;
import com.hsptl.patient.entity.Patient;
import com.hsptl.patient.entity.PatientAddress;

public class PatientBuilder {
	
	public static Patient buildPatientFromPatientRegisterRequestDto(PatientRegisterRequestDTO patientRegisterRequestDto) {
		
		return Patient
				.builder()
				.patientName(patientRegisterRequestDto.getPatientName())
				.gender(patientRegisterRequestDto.getGender())
				.patientEmail(patientRegisterRequestDto.getPatientEmail())
				.patientPhoneNum(patientRegisterRequestDto.getPatientPhoneNum())
				.dateOfBirth(patientRegisterRequestDto.getDateOfBirth())
				.patientAddress(buildPatientAddressFromPatientAddressRequestDTO(patientRegisterRequestDto.getPatientAddressRequestDto()))
				.build();
	}
	
	public static PatientResponseDTO buildPatientResponseFromPatient(Patient patient) {
		return PatientResponseDTO
				.builder()
				.patientId(patient.getPatientId())
				.patientName(patient.getPatientName())
				.gender(patient.getGender())
				.patientEmail(patient.getPatientEmail())
				.patientPhoneNum(patient.getPatientPhoneNum())
				.dateOfBirth(patient.getDateOfBirth())
				.patientAddressResponseDto(buildPatientAddressResponseFromPatientAddress(patient.getPatientAddress()))
				.build();
	}

	public static PatientAddressResponseDTO buildPatientAddressResponseFromPatientAddress(PatientAddress patientAddress) {
		return PatientAddressResponseDTO
				.builder()
				.patientAddressId(patientAddress.getPatientAddressId())
				.doorNum(patientAddress.getDoorNum())
				.landmark(patientAddress.getLandmark())
				.city(patientAddress.getCity())
				.state(patientAddress.getState())
				.country(patientAddress.getCountry())
				.pincode(patientAddress.getPincode())
				.build();
	}
	
	public static PatientAddress buildPatientAddressFromPatientAddressRequestDTO(PatientAddressRequestDTO patientAddressRequestDto) {
		return PatientAddress
				.builder()
				.doorNum(patientAddressRequestDto.getDoorNum())
				.landmark(patientAddressRequestDto.getLandmark())
				.city(patientAddressRequestDto.getCity())
				.state(patientAddressRequestDto.getState())
				.country(patientAddressRequestDto.getCountry())
				.pincode(patientAddressRequestDto.getPincode())
				.build();
	}
	
	public static Patient updatePatientFromRegisterPatientRegisterRequestDTO(PatientRegisterRequestDTO patientRegisterRequestDto, Patient existingPatient) {
		
		existingPatient.setPatientName(patientRegisterRequestDto.getPatientName());
		existingPatient.setGender(patientRegisterRequestDto.getGender());
		existingPatient.setPatientEmail(patientRegisterRequestDto.getPatientEmail());
		existingPatient.setPatientPhoneNum(patientRegisterRequestDto.getPatientPhoneNum());
		existingPatient.setDateOfBirth(patientRegisterRequestDto.getDateOfBirth());
		BeanUtils.copyProperties(patientRegisterRequestDto.getPatientAddressRequestDto(), existingPatient.getPatientAddress());
		
		return existingPatient;
		
	}
}
