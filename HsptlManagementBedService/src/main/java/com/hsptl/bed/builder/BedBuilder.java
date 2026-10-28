package com.hsptl.bed.builder;

import com.hsptl.bed.dto.BedRequestDTO;
import com.hsptl.bed.dto.BedResponseDTO;
import com.hsptl.bed.entity.Bed;
import com.hsptl.bed.entity.Room;

public class BedBuilder {
	
	public static Bed buildBedFromBedRequestDto(BedRequestDTO bedRequestDto,Room room) {
		return Bed
				.builder()
				.bedId(bedRequestDto.getBedId())
				.isOccupied(bedRequestDto.getIsOccupied())
				.roomNum(room)
				.patientId(null)			
				.build();
	}
	
	public static BedResponseDTO buildBedResponseDTOFromBed(Bed bed) {
		return BedResponseDTO
				.builder()
				.bedId(bed.getBedId())
				.isOccupied(bed.getIsOccupied())
				.build();
		
	}
	
	

}
