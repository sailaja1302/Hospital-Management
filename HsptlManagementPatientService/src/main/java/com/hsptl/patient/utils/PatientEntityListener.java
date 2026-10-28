package com.hsptl.patient.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hsptl.patient.entity.Patient;

import jakarta.persistence.PrePersist;

@Component
public class PatientEntityListener {
	
	public PatientIdGenerator patientIdGenerator;
	
	@Autowired
	public void init(PatientIdGenerator patientIdGenerator) {
		this.patientIdGenerator = patientIdGenerator;
	}
	
	@PrePersist
	public void generatePatientId(Patient patient) {
		if(patient.getPatientId()==null) {
			patient.setPatientId(patientIdGenerator.PatientIdGenerateNext());
		}
	}

}
