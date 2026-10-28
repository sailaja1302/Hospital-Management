package com.hsptl.staff.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsptl.staff.entity.StaffAddress;

@Repository
public interface StaffAddressRepository extends JpaRepository<StaffAddress, Long>{

}
