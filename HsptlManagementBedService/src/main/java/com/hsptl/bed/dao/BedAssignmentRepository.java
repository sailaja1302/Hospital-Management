package com.hsptl.bed.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsptl.bed.entity.BedAssignmentHistory;

@Repository
public interface BedAssignmentRepository extends JpaRepository<BedAssignmentHistory, Long>{

}
