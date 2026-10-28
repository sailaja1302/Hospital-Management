package com.hsptl.staff.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="staff_address")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffAddress {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long staffAddressId;
	
	private String landmark;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String pincode;
}
