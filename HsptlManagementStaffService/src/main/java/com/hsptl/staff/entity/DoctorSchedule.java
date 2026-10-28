package com.hsptl.staff.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="doctor_schedule")
public class DoctorSchedule {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="doctor_schedule_id")
	private Long doctorScheduleId;
	
	@JoinColumn(name="doctor_id")
	private Long doctorId;
	
	@ManyToOne
	@JoinColumn(name="staff_id")
	private Staff staff;
	
	@Singular
	@JdbcTypeCode(SqlTypes.JSON)
	private List<LocalDateTime> unavailableDates;
	
	@Singular
	@JdbcTypeCode(SqlTypes.JSON)
	private List<LocalDateTime> availableDates;

	public DoctorSchedule(Staff staff, List<LocalDateTime> unavailableDates) {
		this.staff = staff;
		this.unavailableDates = unavailableDates;
	}
		
}

