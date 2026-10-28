package com.hsptl.bed.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.hsptl.bed.entity.Bed;

@Repository
public interface BedRepository extends JpaRepository<Bed, Long>{
	@Query

	List<Bed> findByRoomNumRoomNumAndIsOccupiedFalse(Long roomNum);
	
	@Query(value = "select b from bed b where b.patient_id = : patientId and b.bed_id =: bedId",nativeQuery = true)
	Optional<Bed> findByPatientIdAndBedId(@PathVariable Long patientId , @PathVariable Long bedId );

}
