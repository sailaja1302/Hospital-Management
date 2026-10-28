package com.hsptl.patient.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hsptl.patient.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	@Query(value = "select patient_id from patient where patient_id like concat(?1,'%') order by patient_id desc limit 1",nativeQuery = true)
	String findLastPatientId(String formattedCurrentDateTime);

	@Query
	Boolean existsByPatientEmail(String email);
	
	@Query
	Boolean existsByPatientPhoneNum(String patientPhoneNum);
	
	@Query
	Optional<Patient> findByIgnoreCasePatientName(String name);
	
//	@Query
//	List<Patient> findByPatientId(List<Long> patientId);
}
