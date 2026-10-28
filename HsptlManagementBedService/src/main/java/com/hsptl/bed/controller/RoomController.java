package com.hsptl.bed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsptl.bed.dto.RoomRequestDTO;
import com.hsptl.bed.dto.RoomResponseDTO;
import com.hsptl.bed.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	RoomService roomService;

	@PostMapping("/addRoom")
	public ResponseEntity<RoomResponseDTO> addRoomFromRoomRequestDTO(@RequestBody RoomRequestDTO roomRequestDto) {
		return new ResponseEntity<>(roomService.addRoomFromRoomRequestDto(roomRequestDto),HttpStatus.CREATED);
	}
	
	@PutMapping("/updateRoom/{roomId}")
	public ResponseEntity<RoomResponseDTO> updateRoomFromRoomRequestDto(@RequestBody RoomRequestDTO roomRequestDto ,@PathVariable Long roomId){
		return new ResponseEntity<>(roomService.updateRoomFromRoomRequestDto(roomRequestDto,roomId),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{roomId}")
	public ResponseEntity<Boolean> deleteRoom(@PathVariable Long roomId){
		return roomService.deleteRoom(roomId);
	}
	
	@GetMapping("/getAllRooms")
	public ResponseEntity<List<RoomResponseDTO>> getAllRooms(){
		return new ResponseEntity<>(roomService.getAllRooms(),HttpStatus.OK);
	}
}

	