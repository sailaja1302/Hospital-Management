package com.hsptl.staff.entity;

import com.hsptl.staff.constants.Specialization;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Doctor {
	
	@Id
	@Column(name="doctor_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long doctorId;
	
	private String doctorName;
	
	@JoinColumn(name="doctor_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Staff staff;
	
	@Enumerated(EnumType.STRING)
	@Column(name="specialization", length = 50)
	private Specialization specialization;
	
	private Boolean isActive;

	public Doctor(Staff staff, Specialization specialization, boolean isActive) {
		super();
		this.staff = staff;
		this.specialization = specialization;
		this.isActive = isActive;
	}
	
	
}
