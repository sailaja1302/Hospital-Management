package com.hsptl.bed.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hsptl.bed.builder.RoomBuilder;
import com.hsptl.bed.dao.BedRepository;
import com.hsptl.bed.dao.RoomRepository;
import com.hsptl.bed.dto.RoomRequestDTO;
import com.hsptl.bed.dto.RoomResponseDTO;
import com.hsptl.bed.entity.Room;
import com.hsptl.bed.exception.RoomNotFoundException;
import com.hsptl.bed.service.RoomService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoomServiceImpl implements RoomService{

	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	BedRepository bedRepository;

	@Override
	public RoomResponseDTO addRoomFromRoomRequestDto(RoomRequestDTO roomRequestDto) {
		Room roomFromRoomRequestDTO = RoomBuilder.buildRoomFromRoomRequestDTO(roomRequestDto);
		
		if(roomRequestDto.getRoomCapacity() < roomRequestDto.getBedRequestDto().size()) {
			throw new IllegalArgumentException("Cannot reduce room capacity to "+ roomRequestDto.getRoomCapacity() +" as room has current bed Count "+ roomRequestDto.getBedRequestDto().size());
		}
		
		Room savedRoom = roomRepository.save(roomFromRoomRequestDTO);
	
		RoomResponseDTO roomResponseDTO = RoomBuilder.buildRoomResponseDtoFromRoom(savedRoom);
		return roomResponseDTO;
	}

	@Override
	public RoomResponseDTO updateRoomFromRoomRequestDto(RoomRequestDTO roomRequestDto, Long roomId) {
		Room room = roomRepository
							.findById(roomId)
							.orElseThrow(()-> new RoomNotFoundException("Your requested room not found : "+roomId));
		
		int bedCount = room.getBeds().size();
		
		if(roomRequestDto.getRoomCapacity() < bedCount) {
			throw new IllegalArgumentException("Cannot reduce room capacity to "+ roomRequestDto.getRoomCapacity() +" as room has current bed Count "+ bedCount);
		}
		
		Room roomFromRoomRequestDTO = RoomBuilder.buildRoomFromRoomRequestDTO(roomRequestDto);
		
		Room updatedRoom = roomRepository.save(roomFromRoomRequestDTO);
		
		 RoomResponseDTO roomResponseDtoFromRoom = RoomBuilder.buildRoomResponseDtoFromRoom(updatedRoom);
		return roomResponseDtoFromRoom;
	}

	@Override
	public ResponseEntity<Boolean> deleteRoom(Long roomId) {
		Room room = roomRepository
								.findById(roomId)
								.orElseThrow(() -> new RoomNotFoundException("Your requested room not found by Id : "+ roomId));
		
			roomRepository.delete(room);
			bedRepository.deleteAll(room.getBeds());
		
		return new ResponseEntity<>(true,HttpStatus.OK);
	}

	@Override
	public List<RoomResponseDTO> getAllRooms() {
		List<RoomResponseDTO> allRooms = roomRepository
								.findAll()
								.stream()
								.map(RoomBuilder :: buildRoomResponseDtoFromRoom)
								.toList();
		
		return allRooms;
	}
	
	
}
