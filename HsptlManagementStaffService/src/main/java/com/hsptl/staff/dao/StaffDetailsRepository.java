package com.hsptl.staff.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsptl.staff.entity.StaffDetails;

@Repository
public interface StaffDetailsRepository extends JpaRepository<StaffDetails, Long>{

}
