package com.hsptl.bed.dto;

import com.hsptl.bed.entity.Bed.BedBuilder;
import com.hsptl.bed.entity.Room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BedResponseDTO {
	
	private Long bedId;
	
	private Boolean isOccupied;

	private Long roomNum;
}
