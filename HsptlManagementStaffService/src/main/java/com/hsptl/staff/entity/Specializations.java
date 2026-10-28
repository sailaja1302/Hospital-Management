package com.hsptl.staff.entity;

import com.hsptl.staff.constants.Specialization;
import com.hsptl.staff.constants.StaffType;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

@NoArgsConstructor
public class Specializations {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long SpecializationId;
	
	@Enumerated(EnumType.STRING)
	@OneToOne(fetch=FetchType.LAZY)
	private Specialization specialization;

	private String description;

	public Specializations(Specialization specialization, String description) {
		super();
		this.specialization = specialization;
		this.description = description;
	}
	
	
}
