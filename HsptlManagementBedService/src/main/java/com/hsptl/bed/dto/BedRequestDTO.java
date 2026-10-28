package com.hsptl.bed.dto;

import com.hsptl.bed.entity.Room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BedRequestDTO {
	
	private Long bedId;
	
	private Boolean isOccupied;
	
	private Room room;
}
