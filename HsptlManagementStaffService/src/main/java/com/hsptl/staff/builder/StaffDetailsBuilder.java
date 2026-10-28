package com.hsptl.staff.builder;

import org.springframework.beans.BeanUtils;

import com.hsptl.staff.dto.StaffAddressResponseDTO;
import com.hsptl.staff.dto.StaffDetailsResponseDTO;
import com.hsptl.staff.entity.Staff;
import com.hsptl.staff.entity.StaffAddress;

public class StaffDetailsBuilder {
	
	public static StaffDetailsResponseDTO buildStaffDetailsResponseFromStaff(Staff staff) {
		return StaffDetailsResponseDTO
				.builder()
				.staffId(staff.getStaffId())
				.firstname(staff.getFirstname())
				.lastname(staff.getLastname())
				.phoneNumber(staff.getPhnnum())
				.role(staff.getRole())
				.gender(staff.getGender())
				.experienceInYears(staff.getExperienceInYears())
				.dateOfJoining(staff.getDateOfJoining())
				.email(staff.getStaffDetails().getEmail())
				.spec(staff.getSpec())
				.staffType(staff.getStaffType())
				.isEmployeeActive(staff.getIsEmployeeActive())
				.canLogin(staff.getCanLogin())
				.staffAddressResponse(buildAddressResponseFromStaffAddress(staff.getStaffAddress()))
				.build();
				
	}

	public static StaffAddressResponseDTO buildAddressResponseFromStaffAddress(StaffAddress staffAddress) {

		StaffAddressResponseDTO staffAddressDto = new StaffAddressResponseDTO();

		BeanUtils.copyProperties(staffAddress, staffAddressDto);

		return staffAddressDto;
	}
	
	

}
