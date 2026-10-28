package com.hsptl.staff.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hsptl.staff.constants.Specialization;
import com.hsptl.staff.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

	public boolean existsByEmail(String email);
	
	public boolean existsByPhnnum(String phnnum);
	
	@Query("select s from Staff s where s.spec = :spec")
	List<Staff> findBySpec( @Param("spec") Specialization  specialization);
	
	List<Staff> findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String firstname, String lastname);

}

