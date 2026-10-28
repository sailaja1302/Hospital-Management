package com.hsptl.bed.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.hsptl.bed.dto.RoomRequestDTO;
import com.hsptl.bed.dto.RoomResponseDTO;

public interface RoomService {

	public RoomResponseDTO addRoomFromRoomRequestDto(@RequestBody RoomRequestDTO roomRequestDto);
	
	public RoomResponseDTO updateRoomFromRoomRequestDto(@RequestBody RoomRequestDTO roomRequestDto , @PathVariable Long roomId);
	
	public ResponseEntity<Boolean> deleteRoom(@PathVariable Long roomId);
	
	public List<RoomResponseDTO> getAllRooms();
}
