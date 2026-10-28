package com.hsptl.appointment.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hsptl.appointment.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String>{

	@Query(value = "select appointment_id from appointments where appointment_id like concat(?1,'%') order by appointment_id desc limit 1",nativeQuery = true)
	String findLastApplicationId(String formattedCurrentDate);

	@Query(value = "select count(*)>0 from appointments a where a.doctor_id = :doctorId and :startDate < a.end_date and :endDate > a.start_date",nativeQuery = true)
	Long existsByDoctorIdAndDate(
			@Param("doctorId") Long doctorId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate
			);

	@Query(value = "select count(*)>0 from appointments a where a.patient_id = :patientId and :startDate < a.end_date and :endDate > a.start_date",nativeQuery = true)
	Long existsByPatientIdAndDate(
			Long patientId , LocalDateTime startDate, LocalDateTime endDate
			);
	
	@Query
	Optional<Appointment> findByAppointmentId(String appointmentId);
	
	@Query(value = "select * from appointments a where a.doctor_id = :doctorId and a.appointment_date >= :appointmentDate",nativeQuery = true)
	List<Appointment> findAllAppointmentsByDoctorIdAndAppointmentDate(@Param("doctorId") Long doctorId, @Param("appointmentDate") LocalDate appointmentDate);

	@Query
	List<Appointment> findAllByAppointmentDate(LocalDate appointmentDate);
	
	@Query(value = "SELECT DISTINCT a.patient_id FROM appointments a WHERE a.doctor_id = :doctorId AND a.start_date >= :startDateTime AND a.end_date <= :endDateTime",nativeQuery=true)
	List<Long> findPatientIdsByDoctorAndDateRange(
										            @Param("doctorId") Long doctorId,
										            @Param("startDateTime") LocalDateTime startDateTime,
										            @Param("endDateTime") LocalDateTime endDateTime);
}
