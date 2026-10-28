package com.hsptl.patient.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hsptl.patient.dao.PatientRepository;
@Component
public class PatientIdGenerator {
	
	@Autowired
	public final PatientRepository patientRepository;

	public PatientIdGenerator (PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	public Long PatientIdGenerateNext() {
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("YYYYmmdd");
		
		String formattedCurrentDateTime = currentDateTime.format(formatDateTime);
		String lastPatientId = patientRepository.findLastPatientId(formattedCurrentDateTime);
		int nextNumber = 1;
		String patientId = "";
		String patientIdSuffix = "";
		
		if(lastPatientId != null && lastPatientId.startsWith(formattedCurrentDateTime)) {
			String idSuffix = patientId.substring(7);
			nextNumber = Integer.parseInt(idSuffix)+1;
			patientIdSuffix = String.format("%06",nextNumber);
			return Long.parseLong(patientId);
			
		}
		else {
			patientIdSuffix = String.format("%06",nextNumber);
			patientId = formattedCurrentDateTime + patientIdSuffix;
			return Long.parseLong(patientId);
		}
		
		
	}
}
