package com.hsptl.appointment.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.hsptl.appointment.builder.AppointmentBuilder;
import com.hsptl.appointment.client.DoctorClient;
import com.hsptl.appointment.client.PatientClient;
import com.hsptl.appointment.constants.Status;
import com.hsptl.appointment.dao.AppointmentRepository;
import com.hsptl.appointment.dto.AppointmentRequestDTO;
import com.hsptl.appointment.dto.AppointmentResponseDTO;
import com.hsptl.appointment.dto.RescheduleAppointmentRequestDTO;
import com.hsptl.appointment.entity.Appointment;
import com.hsptl.appointment.exception.AppointmentAlreadyExistsException;
import com.hsptl.appointment.exception.AppointmentNotFoundException;
import com.hsptl.appointment.exception.DoctorNotFoundException;
import com.hsptl.appointment.exception.InvalidTimeException;
import com.hsptl.appointment.service.AppointmentService;
import com.hsptl.appointment.utils.AppointmentIdGenerator;
import com.hsptl.staff.dto.DoctorResponseDTO;

@Service
public class AppointmentServiceImpl implements AppointmentService{

	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	AppointmentIdGenerator appointmentIdGenerator;
	
	@Autowired
	PatientClient patientClient;
	
	@Autowired
	DoctorClient doctorClient;
	
	@Override
	public AppointmentResponseDTO bookAppointmentFromAppointmentRequestDto(
			AppointmentRequestDTO appointmentRequestDto) {
		
		String generateNextAppointmentId = appointmentIdGenerator.AppointmentIdGenerateNext();
		
		LocalTime workingStart = LocalTime.of(9, 00);
		LocalTime workingEnd = LocalTime.of(16, 00);
		
		LocalTime lunchStart = LocalTime.of(12, 00);
		LocalTime lunchEnd = LocalTime.of(13, 00);
		
		LocalTime startTime = appointmentRequestDto.getStartDate().toLocalTime();
		LocalTime endTime = appointmentRequestDto.getEndDate().toLocalTime();
		
		if (appointmentRequestDto.getStartDate().isAfter(appointmentRequestDto.getEndDate()) ) {
			throw new InvalidTimeException("StartTime must be before EndTime ");	
		}
		
		if(appointmentRequestDto.getStartDate().isBefore(LocalDateTime.now()) || appointmentRequestDto.getEndDate().isBefore(LocalDateTime.now())) {
			throw new InvalidTimeException("Appointment dates cannot be in past ..");
		}
		
		if(appointmentRequestDto.getStartDate().getMinute() != 0 || appointmentRequestDto.getStartDate().getSecond() != 0 || appointmentRequestDto.getEndDate().getMinute() != 0 || appointmentRequestDto.getEndDate().getSecond() != 0) {
			throw new IllegalArgumentException("Slots must be in uniform time increment (Ex : 10:00 - 11:00). Invalid Slot : "+appointmentRequestDto.getStartDate());
		}
		
		if(startTime.isBefore(workingStart) || startTime.isAfter(workingEnd) || endTime.isAfter(workingEnd) || endTime.isBefore(workingStart)) {
			throw new InvalidTimeException("Appointment should be booked between the Out-Patient hours (09:00am - 04:00pm)");			
		}
		
		if(startTime.isBefore(lunchEnd) && endTime.isAfter(lunchStart)) {
			throw new InvalidTimeException("Appointment schedules are halted for Lunch will resume after "+lunchEnd);
		}
		
		long slotDuration = Duration.between(startTime, endTime).toMinutes();
		
		if(slotDuration != 60) {
			throw new InvalidTimeException("Appointment slot duration must be scheduled to 1 hour. Provided duration : "+slotDuration+" minutes.");
		}
		Long existsByDoc = appointmentRepository.existsByDoctorIdAndDate(
									appointmentRequestDto.getDoctorId(),
									appointmentRequestDto.getStartDate(),
									appointmentRequestDto.getEndDate());
		if(existsByDoc > 0) {
			throw new AppointmentAlreadyExistsException("Doctor already has an appointment for specific slot. Please select another slot");
		}
		
		Long existsByPatient = appointmentRepository.existsByPatientIdAndDate(
				appointmentRequestDto.getPatientId(),
				appointmentRequestDto.getStartDate(),
				appointmentRequestDto.getEndDate());
	
		if(existsByPatient > 0) {
			throw new AppointmentAlreadyExistsException("Patient already has appoinment booked for specific slot. Requested to be there in the reception ahead 10 mins the scheduled slot. ");
		}		
		
		
	
		Appointment appointmentFromAppointmentRequestDTO = AppointmentBuilder.buildAppointmentFromAppointmentRequestDTO(appointmentRequestDto);
		appointmentFromAppointmentRequestDTO.setAppointmentId(generateNextAppointmentId);
		
		Appointment savedAppointment = appointmentRepository.save(appointmentFromAppointmentRequestDTO);
		
		AppointmentResponseDTO appointmentResponse = AppointmentBuilder.buildAppointmentResponseFromAppointment(savedAppointment);
		
		return appointmentResponse;
	}

	@Override
	public AppointmentResponseDTO rescheduleAppointmentFromRescheduleAppointmentRequestDTO(
			RescheduleAppointmentRequestDTO rescheduleAppointmentRequestDto, String appointmentId) {
		
		Appointment existingAppointment = appointmentRepository
										.findByAppointmentId(appointmentId)
										.orElseThrow(() -> new  AppointmentNotFoundException("Appointment not found with ID : "+appointmentId));
		
		LocalTime workingStart = LocalTime.of(9, 00);
		LocalTime workingEnd = LocalTime.of(16, 00);
		
		LocalTime lunchStart = LocalTime.of(12, 00);
		LocalTime lunchEnd = LocalTime.of(13, 00);
		
		LocalTime startTime = rescheduleAppointmentRequestDto.getStartDate().toLocalTime();
		LocalTime endTime = rescheduleAppointmentRequestDto.getEndDate().toLocalTime();
		
		
		if (rescheduleAppointmentRequestDto.getStartDate().isAfter(rescheduleAppointmentRequestDto.getEndDate()) ) {
			throw new InvalidTimeException("StartTime must be before EndTime ");	
		}
		
		if(rescheduleAppointmentRequestDto.getStartDate().isBefore(LocalDateTime.now()) || rescheduleAppointmentRequestDto.getEndDate().isBefore(LocalDateTime.now())) {
			throw new InvalidTimeException("Appointment dates cannot be in past ..");
		}
		
		if(rescheduleAppointmentRequestDto.getStartDate().getMinute() != 0 || rescheduleAppointmentRequestDto.getStartDate().getSecond() != 0 || rescheduleAppointmentRequestDto.getEndDate().getMinute() != 0 || rescheduleAppointmentRequestDto.getEndDate().getSecond() != 0) {
			throw new IllegalArgumentException("Slots must be in uniform time increment (Ex : 10:00 - 11:00). Invalid Slot : "+rescheduleAppointmentRequestDto.getStartDate());
		}
		
		if(startTime.isBefore(workingStart) || startTime.isAfter(workingEnd) || endTime.isAfter(workingEnd) || endTime.isBefore(workingStart)) {
			throw new InvalidTimeException("Appointment should be booked between the Out-Patient hours (09:00am - 04:00pm)");			
		}
		
		if(startTime.isBefore(lunchEnd) && endTime.isAfter(lunchStart)) {
			throw new InvalidTimeException("Appointment schedules are halted for Lunch. Will resume after "+lunchEnd);
		}
		
		long slotDuration = Duration.between(startTime, endTime).toMinutes();
		
		if(slotDuration != 60) {
			throw new InvalidTimeException("Appointment slot duration is not more than 1 hour. Provided duration : "+slotDuration+" minutes.");
		}
		
		Long existsByDoc = appointmentRepository.existsByDoctorIdAndDate(
				existingAppointment.getDoctorId(),
				rescheduleAppointmentRequestDto.getStartDate(),
				rescheduleAppointmentRequestDto.getEndDate());
		if(existsByDoc > 0) {
			throw new AppointmentAlreadyExistsException("Doctor already has an appointment for specific slot. Please select another slot");
		}
		
		Long existsByPatient = appointmentRepository.existsByPatientIdAndDate(
				existingAppointment.getPatientId(),
				rescheduleAppointmentRequestDto.getStartDate(),
				rescheduleAppointmentRequestDto.getEndDate());
	
		if(existsByPatient > 0) {
			throw new AppointmentAlreadyExistsException("Patient already has appoinment booked for specific slot. Requested to be there in the reception ahead 10 mins the scheduled slot. ");
		}		
		
		existingAppointment.setAppointmentDate(rescheduleAppointmentRequestDto.getAppointmentDate());
		existingAppointment.setStartDate(rescheduleAppointmentRequestDto.getStartDate());
		existingAppointment.setEndDate(rescheduleAppointmentRequestDto.getEndDate());
		existingAppointment.setNotes(rescheduleAppointmentRequestDto.getNotes());
		existingAppointment.setStatus(Status.RESCHEDULED);
		
		Appointment savedAppointment = appointmentRepository.save(existingAppointment);
		
		AppointmentResponseDTO appointmentResponse = AppointmentBuilder.buildAppointmentResponseFromAppointment(savedAppointment);
		return appointmentResponse;
	}

	@Override
	public List<AppointmentResponseDTO> getAllFutureAppointmentsByDoctorId(Long doctorId) {
		
		try {
			 DoctorResponseDTO doctorById = doctorClient.getDoctorByDoctorId(doctorId);
			}
			catch(feign.FeignException.NotFound e) {
				throw new DoctorNotFoundException("Doctor not Found with Doctor ID : "+ doctorId);
			}
		
		LocalDate startDate = LocalDate.now();
		
		List<Appointment> existingAppointments = appointmentRepository.findAllAppointmentsByDoctorIdAndAppointmentDate(doctorId,startDate);

		if(existingAppointments.isEmpty()) {
			throw new AppointmentNotFoundException("Appointments not found for Doctor ID :"+ doctorId);
		}
	
	return existingAppointments
		.stream()
		.map(AppointmentBuilder :: buildAppointmentResponseFromAppointment)
		.toList();
													
	}
	
	public List<AppointmentResponseDTO> getAllAppointmentsByDate(@PathVariable("date") LocalDate date ){
		
		List<Appointment> allAppointmentsByAppointmentDate = appointmentRepository
													.findAllByAppointmentDate(date);
		if(allAppointmentsByAppointmentDate.isEmpty()) {
			throw new AppointmentNotFoundException ("Appointments not found in requested date : "+date);
		}
		
		return allAppointmentsByAppointmentDate
				.stream()
				.map(AppointmentBuilder :: buildAppointmentResponseFromAppointment)
				.toList();
	}
	
	public String deleteAppointmentByAppointmentId(@PathVariable String appointmentId){
		Appointment existedAppointment = appointmentRepository
									.findByAppointmentId(appointmentId)
									.orElseThrow(() -> new AppointmentNotFoundException("Apointment not found with ID : "+appointmentId));
		appointmentRepository.delete(existedAppointment);
		
		return "Successfuuly deleted appointment scheduled for "+appointmentId;
		
	}

	@Override
	public AppointmentResponseDTO getAppointmentDetailsByAppointmentId(String appointmentId) {
		
		Appointment existingAppointment = appointmentRepository.findByAppointmentId(appointmentId)
										.orElseThrow(()-> new AppointmentNotFoundException("Appointment not found with ID : "+appointmentId));
										
		AppointmentResponseDTO appointmentResponseFromAppointment = AppointmentBuilder.buildAppointmentResponseFromAppointment(existingAppointment);
		
		return appointmentResponseFromAppointment;
	}

	@Override
	public List<Long> getPatientIdsByDoctorAndDatesRange(Long doctorId, LocalDateTime startDateTime,
			LocalDateTime endDateTime) {
		try {
			 DoctorResponseDTO doctorById = doctorClient.getDoctorByDoctorId(doctorId);
			}
			catch(feign.FeignException.NotFound e) {
				throw new DoctorNotFoundException("Doctor not Found with Doctor ID : "+ doctorId);
			}
		
		 List<Long> patientIdsByDoctorAndDateRange = appointmentRepository.findPatientIdsByDoctorAndDateRange(doctorId,startDateTime,endDateTime);
		 
		 return patientIdsByDoctorAndDateRange;
		 
	}

	@Override
	public List<AppointmentResponseDTO> getAllAppointmentsByDoctorAndDate(Long doctorId, LocalDate date) {
		
		try {
		 DoctorResponseDTO doctorById = doctorClient.getDoctorByDoctorId(doctorId);
		}
		catch(feign.FeignException.NotFound e) {
			throw new DoctorNotFoundException("Doctor not Found with Doctor ID : "+ doctorId);
		}
		List<AppointmentResponseDTO> allAppointmentResponses = appointmentRepository
											.findAllAppointmentsByDoctorIdAndAppointmentDate(doctorId, date)
											.stream()
											.map(AppointmentBuilder :: buildAppointmentResponseFromAppointment)
											.toList();
		
		return allAppointmentResponses;
	}



}
