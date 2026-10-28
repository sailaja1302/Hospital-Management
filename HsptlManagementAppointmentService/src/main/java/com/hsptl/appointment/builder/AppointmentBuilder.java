package com.hsptl.appointment.builder;

import com.hsptl.appointment.dto.AppointmentDetailsDTO;
import com.hsptl.appointment.dto.AppointmentRequestDTO;
import com.hsptl.appointment.dto.AppointmentResponseDTO;
import com.hsptl.appointment.entity.Appointment;

public class AppointmentBuilder {
	
	public static Appointment buildAppointmentFromAppointmentRequestDTO(AppointmentRequestDTO appointmentRequestDto) {
		return Appointment
				.builder()
				.patientId(appointmentRequestDto.getPatientId())
				.doctorId(appointmentRequestDto.getDoctorId())
				.appointmentDate(appointmentRequestDto.getAppointmentDate())
				.startDate(appointmentRequestDto.getStartDate())
				.endDate(appointmentRequestDto.getEndDate())
				.status(appointmentRequestDto.getStatus())
				.notes(appointmentRequestDto.getNotes())
				.build();
	}
	
	public static AppointmentResponseDTO buildAppointmentResponseFromAppointment(Appointment appointment) {
		return AppointmentResponseDTO
				.builder()
				.appointmentId(appointment.getAppointmentId())
				.patientId(appointment.getPatientId())
				.doctorId(appointment.getDoctorId())
				.appointmentDate(appointment.getAppointmentDate())
				.startDate(appointment.getStartDate())
				.endDate(appointment.getEndDate())
				.status(appointment.getStatus())
				.notes("Please be ready for the doctor checkUp before the stipulated time")
				.reasonForVisit(null)
				.diagnosisSummary(null)
				.prescription(null)
				.medicines("Check the prescription to get detailed instructions for each medicine timings")
				.notesForReceptionist("Kindly make an appointment for your follow up checkUp")
				.dietPlan(null)
				.build();
				
	}
	


	
}
