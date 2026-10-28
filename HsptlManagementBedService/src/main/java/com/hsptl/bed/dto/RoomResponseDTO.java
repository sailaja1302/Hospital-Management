package com.hsptl.bed.dto;

import java.util.List;

import com.hsptl.bed.constants.RoomType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponseDTO {
	
	private Long roomNum;
	
	private RoomType roomType;
	
	private Integer roomCapacity;
	
	private List<BedResponseDTO> bedResponseDto;

}
