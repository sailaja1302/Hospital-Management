package com.hsptl.patient.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsptl.patient.entity.PatientAddress;

@Repository
public interface PatientAddressRepository extends JpaRepository<PatientAddress, Long>{

}
