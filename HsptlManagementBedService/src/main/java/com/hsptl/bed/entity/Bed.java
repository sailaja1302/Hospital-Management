package com.hsptl.bed.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bed {
	
	@Id
	private Long bedId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="room_num")
	private Room roomNum;
	
	private Boolean isOccupied;
	
	@Column(name="patient_id")
	private Long patientId;
	
	@OneToMany(mappedBy="bed",cascade=CascadeType.ALL)
	private List<BedAssignmentHistory> bedAssignmentHistory;

	public Bed(Room roomNum, Boolean isOccupied, Long patientId, List<BedAssignmentHistory> bedAssignmentHistory) {
		super();
		this.roomNum = roomNum;
		this.isOccupied = isOccupied;
		this.patientId = patientId;
		this.bedAssignmentHistory = bedAssignmentHistory;
	}
	
	

	
}
