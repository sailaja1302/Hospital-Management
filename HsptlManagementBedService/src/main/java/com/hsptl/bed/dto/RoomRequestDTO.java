package com.hsptl.bed.dto;

import java.util.List;

import com.hsptl.bed.constants.RoomType;
import com.hsptl.bed.entity.Bed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomRequestDTO {
	
	private Long roomNum;
	
	private RoomType roomType;
	
	private Integer roomCapacity;
	
	private List<BedRequestDTO> bedRequestDto;
	

}
