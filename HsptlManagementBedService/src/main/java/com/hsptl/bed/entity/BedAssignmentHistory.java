package com.hsptl.bed.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BedAssignmentHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bed_assign_hist")
	private Long bedHisId; 
	
	private Long bedNum;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bed_id",nullable=false)
	private Bed bed;
	
	@Column(name="patient_id")
	private Long patientId;
	
	private LocalDateTime assignedAt;
	
	private LocalDateTime vacatedAt;
	
}
