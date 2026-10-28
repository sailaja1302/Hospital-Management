package com.hsptl.appointment.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.hsptl.appointment.constants.Status;
import com.hsptl.appointment.utils.AppointmentEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Table(name="appointments")
@NoArgsConstructor
@Builder
@EntityListeners(value=AppointmentEntityListener.class)
public class Appointment {

	@Id
	private String appointmentId;
	
	@Column(name="patient_id",nullable=false)
	private Long patientId;
	
	@Column(name="doctor_id",nullable=false)
	private Long doctorId;
	
	private LocalDate appointmentDate;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status",length = 30)
	private Status status;
	
	private String notes;
	

	public Appointment(long patientId, long doctorId, LocalDate appointmentDate, LocalDateTime startDate,
			LocalDateTime endDate, Status staus, String notes) {
		super();
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.appointmentDate = appointmentDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = staus;
		this.notes = notes;
	}
	
	
	
}
