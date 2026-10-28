package com.hsptl.staff.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hsptl.staff.entity.Doctor;
import com.hsptl.staff.entity.Staff;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long>{

	@Query
	Boolean existsByStaff(Staff staff);
}
