package com.hsptl.staff.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.hsptl.staff.entity.DoctorSchedule;
import com.hsptl.staff.entity.Staff;

@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long>{
	
	@Query
	List<DoctorSchedule> findByStaff(Staff staff);
	
	@Query(value= "SELECT * FROM doctor_schedule WHERE JSON_CONTAINS(unavailable_dates, CAST(CONCAT('\"', :date, '\"') AS JSON))", nativeQuery = true)
	Optional<DoctorSchedule> existsByStaffAndUnavailableDates(Staff staff, @Param("date") LocalDateTime date);
	
	@Query(value ="select * from doctor_schedule d where d.doctor_id=:doctorId",nativeQuery=true)
	List<DoctorSchedule> findDoctorScheduleByDoctorId(Long doctorId);
	
}
