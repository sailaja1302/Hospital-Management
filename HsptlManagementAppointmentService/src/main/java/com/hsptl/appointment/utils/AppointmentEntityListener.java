package com.hsptl.appointment.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hsptl.appointment.constants.Status;
import com.hsptl.appointment.entity.Appointment;

import jakarta.persistence.PrePersist;

@Component
public class AppointmentEntityListener {
	
	public static AppointmentIdGenerator appointmentIdGenerate;
	
	@Autowired
	public void init(AppointmentIdGenerator appointmentIdGenerator) {
		appointmentIdGenerate = appointmentIdGenerator;
	}
	
	@PrePersist
	public void generateAppointmentId(Appointment appointment) {
		
		if(appointment.getAppointmentId()==null) {
			appointment.setAppointmentId(appointmentIdGenerate.AppointmentIdGenerateNext());
		
			if(appointment.getStatus()==null) {
				appointment.setStatus(Status.SCHEDULED);
			}
		}
		
	}

}
