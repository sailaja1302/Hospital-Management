package com.hsptl.appointment.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.text.DateFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hsptl.appointment.dao.AppointmentRepository;

@Component
public class AppointmentIdGenerator {
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	public String AppointmentIdGenerateNext() {
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String formattedCurrentDateTime = LocalDateTime.now().format(dateTimeFormatter);
		
		String formattedCurrentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		String lastAppointmentId = appointmentRepository.findLastApplicationId(formattedCurrentDate);
		int nextNumber = 1;
		String appointmentIdSuffix = "";
		String appointmentId ="";
		
		
		if(lastAppointmentId != null && lastAppointmentId.startsWith(formattedCurrentDate)) {
			
			String idSuffix = lastAppointmentId.substring(14);
			nextNumber = Integer.parseInt(idSuffix)+1;
			
			appointmentIdSuffix = String.format("%04d",nextNumber);
			appointmentId = formattedCurrentDateTime + appointmentIdSuffix;
			
			return appointmentId;
		}
		
		else {
			nextNumber = 1;
			appointmentIdSuffix = String.format("%04d",nextNumber);
			appointmentId = formattedCurrentDateTime+appointmentIdSuffix;
			return appointmentId;
		}
		
	}

}
