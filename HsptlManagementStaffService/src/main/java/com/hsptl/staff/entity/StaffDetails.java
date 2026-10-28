package com.hsptl.staff.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsptl.staff.dto.StaffAddressRequestDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="staff_details")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StaffDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long staffDetailsId;
	
	private String email;
	
	private String password;
	
	private String resetOTP;
	
	@Column(name = "can_login")
	private boolean canLogin;
	
	@Column(name ="is_employee_active")
	private boolean isEmployeeActive;
	
	private LocalDateTime otpExpiryTime;
	
	private boolean requiredPasswordReset = false;
	
	@OneToOne(cascade=CascadeType.ALL)
	private StaffAddress staffAddress;

	public StaffDetails(String email, String password, String resetOTP, LocalDateTime otpExpiryTime,
			boolean requiredPasswordReset, StaffAddress staffAddress) {
		super();
		this.email = email;
		this.password = password;
		this.resetOTP = resetOTP;
		this.otpExpiryTime = otpExpiryTime;
		this.requiredPasswordReset = requiredPasswordReset;
		this.staffAddress = staffAddress;
	}

	public StaffDetails(String email, String password, String resetOTP, boolean canLogin, boolean isEmployeeActive,
			LocalDateTime otpExpiryTime, boolean requiredPasswordReset, StaffAddress staffAddress) {
		super();
		this.email = email;
		this.password = password;
		this.resetOTP = resetOTP;
		this.canLogin = canLogin;
		this.isEmployeeActive = isEmployeeActive;
		this.otpExpiryTime = otpExpiryTime;
		this.requiredPasswordReset = requiredPasswordReset;
		this.staffAddress = staffAddress;
	}
	
	
	
}
