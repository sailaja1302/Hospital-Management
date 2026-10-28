package com.hsptl.staff.builder;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hsptl.staff.dto.RegisterStaffRequestDTO;
import com.hsptl.staff.dto.StaffAddressRequestDTO;
import com.hsptl.staff.dto.StaffResponseDTO;
import com.hsptl.staff.entity.Staff;
import com.hsptl.staff.entity.StaffAddress;
import com.hsptl.staff.entity.StaffDetails;

public class StaffBuilder {
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	// ThreadLocal to store plain password temporarily for email notification
	private static final ThreadLocal<String> plainPasswordHolder = new ThreadLocal<>();

	public static Staff buildStaffFromRegisterStaffDto(RegisterStaffRequestDTO registerStaffDto){
		
		return Staff.builder()

		.firstname(registerStaffDto.getFirstname())
		.lastname(registerStaffDto.getLastname())
		.phnnum(String.valueOf(registerStaffDto.getPhnnum()))
		.gender(registerStaffDto.getGender() != null ? registerStaffDto.getGender().toUpperCase() :null)
		.dateOfJoining(registerStaffDto.getDateOfJoining())
		.staffType(registerStaffDto.getStaffType())
		.spec(registerStaffDto.getSpecialization())
		.experienceInYears(registerStaffDto.getExperienceInYears())
		.email(registerStaffDto.getEmail())
		.role(registerStaffDto.getRole() != null ? registerStaffDto.getRole().toUpperCase() : null)
		.canLogin(true)
		.isEmployeeActive(true)
		.staffAddress(buildStaffAdddressFromStaffAddressRequestDto(registerStaffDto.getStaffAddressRequestDto()))
		.staffDetails(buildStaffDetailsFromStaffDetailsDto(registerStaffDto,buildStaffAdddressFromStaffAddressRequestDto(registerStaffDto.getStaffAddressRequestDto())))
		.build();

	}
	
	public static StaffResponseDTO buildStaffResponseDTOFromStaff(Staff staff) {
		return StaffResponseDTO
				.builder()
				.staffId(staff.getStaffId())
				.firstname(staff.getFirstname())
				.lastname(staff.getLastname())
				.phnnum(staff.getPhnnum())
				.role(staff.getRole())
				.gender(staff.getGender())
				.dateOfJoining(staff.getDateOfJoining())
				.experienceInYears(staff.getExperienceInYears())
				.specialization(staff.getSpec())
				.email(staff.getEmail())
				.staffType(staff.getStaffType())
				.staffAddress(staff.getStaffAddress())
				.staffDetails(staff.getStaffDetails())
				.build();
				
	}
	
	public static StaffAddress buildStaffAdddressFromStaffAddressRequestDto(StaffAddressRequestDTO staffAddressDto) {
		

		StaffAddress staffAddress = new StaffAddress();
		
		System.out.println("Staff Address Dto" + staffAddressDto);
		
		 BeanUtils.copyProperties(staffAddressDto, staffAddress);

		return staffAddress;

	}
	
	public static StaffDetails buildStaffDetailsFromStaffDetailsDto(RegisterStaffRequestDTO registerStaffDto,StaffAddress staffAddress) {
		
		String plainPassword = generateSixDigitPassword();
		String hashedPassword = passwordEncoder.encode(plainPassword);
		
		// Store plain password temporarily for email notification
		plainPasswordHolder.set(plainPassword);
		
		return StaffDetails
				.builder()
				.email(registerStaffDto.getEmail())
				.password(hashedPassword)
				.canLogin(true)
				.isEmployeeActive(true)
				.requiredPasswordReset(true) // Force password reset for new accounts
				.staffAddress(staffAddress)
				.build();
	}
	
	public static String getPlainPassword() {
		return plainPasswordHolder.get();
	}
	
	public static void clearPlainPassword() {
		plainPasswordHolder.remove();
	}
	
	public static String generateSixDigitPassword() {
        Random random = new Random();
        int password = 100000 + random.nextInt(900000);
        return String.valueOf(password);
    }
	

}