package com.hsptl.bed.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.hsptl.bed.dto.BedResponseDTO;
import com.hsptl.bed.dto.RoomRequestDTO;
import com.hsptl.bed.dto.RoomResponseDTO;
import com.hsptl.bed.entity.Bed;
import com.hsptl.bed.entity.Room;

public class RoomBuilder {
	
	public static Room buildRoomFromRoomRequestDTO(RoomRequestDTO roomRequestDto) {
		Room room = new Room();
		
		room.setRoomNum(roomRequestDto.getRoomNum());
		room.setRoomType(roomRequestDto.getRoomType());
		room.setRoomCapacity(roomRequestDto.getRoomCapacity());
	
		if(roomRequestDto.getBedRequestDto() != null) {
			
		List<Bed> beds = roomRequestDto
				.getBedRequestDto()
				.stream()
				.map(bedRequestDTO -> {
					Bed bed = BedBuilder.buildBedFromBedRequestDto(bedRequestDTO,room);
					return bed;
					})
				.toList();
		room.setBeds(beds);

		}
		else {
			room.setBeds(new ArrayList<>());
		}
		
		return room;		
		
	}
	
	public static RoomResponseDTO buildRoomResponseDtoFromRoom(Room room) {
		
		List<BedResponseDTO> beds = room
				.getBeds()
				.stream()
				.map(bed -> {
					BedResponseDTO bedDto = new BedResponseDTO();
					bedDto.setBedId(bed.getBedId());
					bedDto.setIsOccupied(bed.getIsOccupied());
					return bedDto;
					
				})
				.collect(Collectors.toList());
		
		
		return RoomResponseDTO
				.builder()
				.roomNum(room.getRoomNum())
				.roomType(room.getRoomType())
				.roomCapacity(room.getRoomCapacity())
				.bedResponseDto(beds)
				.build();
		
	}

}
