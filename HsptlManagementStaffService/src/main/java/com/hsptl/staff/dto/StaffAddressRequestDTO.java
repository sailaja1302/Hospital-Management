package com.hsptl.staff.dto;

import com.hsptl.staff.constants.StaffType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffAddressRequestDTO {
	
	private String landmark;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String pincode;

}
