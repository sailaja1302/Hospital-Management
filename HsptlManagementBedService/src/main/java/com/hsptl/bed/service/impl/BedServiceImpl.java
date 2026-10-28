package com.hsptl.bed.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsptl.bed.builder.BedBuilder;
import com.hsptl.bed.dao.BedRepository;
import com.hsptl.bed.dao.RoomRepository;
import com.hsptl.bed.dto.BedRequestDTO;
import com.hsptl.bed.dto.BedResponseDTO;
import com.hsptl.bed.entity.Bed;
import com.hsptl.bed.entity.Room;
import com.hsptl.bed.exception.BedNotFoundException;
import com.hsptl.bed.exception.RoomNotFoundException;
import com.hsptl.bed.service.BedService;

@Service
public class BedServiceImpl implements BedService{
	
	@Autowired
	BedRepository bedRepository;
	
	@Autowired
	RoomRepository roomRepository;

	@Override
	public BedResponseDTO addBedInRoom(BedRequestDTO bedRequestDto) {
		BedResponseDTO bedResponseDTOFromBed = null;
		if (bedRequestDto.getRoom() == null || bedRequestDto.getRoom().getRoomNum() == null) {
		        throw new IllegalArgumentException("Room Number must be provided in the request.");
		    }
		
		Long roomNum = bedRequestDto.getRoom().getRoomNum();
		
		Room room = roomRepository
					.findById(roomNum)
					.orElseThrow(()->new RoomNotFoundException("Room not found with Id : "+roomNum));

		Long bedId = bedRequestDto.getBedId();
		Boolean ifExists = bedRepository.existsById(bedId);
		
		if(ifExists) {
			throw new IllegalArgumentException("Bed already exists with ID : "+ bedId);
		}
		
		if(room.getBeds().size()  >= room.getRoomCapacity()) {
			throw new IllegalArgumentException("Cannot add Bed into Room Number : "+room.getRoomNum() + " as room is full.");
		}
		
		Bed bedFromBedRequestDto = BedBuilder.buildBedFromBedRequestDto(bedRequestDto, room);
		Bed savedBed = bedRepository.save(bedFromBedRequestDto);
		
		room.getBeds().add(savedBed);
		bedResponseDTOFromBed = BedBuilder.buildBedResponseDTOFromBed(savedBed);

		return bedResponseDTOFromBed;
	}

	@Override
	public BedResponseDTO updateBedInRoom(BedRequestDTO bedRequestDto, Long bedId, Long roomNum) {
		Room existingRoom = roomRepository.findById(roomNum)
					.orElseThrow(()-> new RoomNotFoundException("Room not found with ID : "+roomNum));
		
		Bed existingBed = bedRepository.findById(bedId)
		.orElseThrow(() -> new BedNotFoundException("Bed not found with ID : "+bedId));
		
		Bed bedFromBedRequestDto = BedBuilder.buildBedFromBedRequestDto(bedRequestDto, existingRoom);
		
		Bed savedBed = bedRepository.save(bedFromBedRequestDto);
		
		BedResponseDTO bedResponseDTOFromBed = BedBuilder.buildBedResponseDTOFromBed(savedBed);
		return bedResponseDTOFromBed;
	}

	@Override
	public String deleteBedInRoom(Long roomNum, Long bedId) {
		
		Room existingRoom = roomRepository.findById(roomNum)
				.orElseThrow(()-> new RoomNotFoundException("Room not found with ID : "+roomNum));
		
		Bed existingBed = bedRepository.findById(bedId)
		.orElseThrow(() -> new BedNotFoundException("Bed not found with ID : "+ bedId));
		
		
		if( roomRepository.existsById(roomNum)) {
			existingRoom.getBeds().remove(existingBed);
		}
		bedRepository.deleteById(bedId);
		return "Successfully deleted the Bed with ID : "+bedId;
	}

	@Override
	public List<BedResponseDTO> getAllBeds() {
		List<BedResponseDTO> allBeds = bedRepository
										.findAll()
										.stream()
										.map(BedBuilder :: buildBedResponseDTOFromBed)
										.toList();
		return allBeds;
	}

	@Override
	public List<BedResponseDTO> getAllVacantBeds(Long roomNum) {
		
		Room existingRoom = roomRepository.findById(roomNum)
				.orElseThrow(() -> new RoomNotFoundException("Room not found with ID : "+roomNum));
		
		List<BedResponseDTO> vacantBeds = bedRepository
										.findByRoomNumRoomNumAndIsOccupiedFalse(roomNum)
										.stream()
										.map(BedBuilder :: buildBedResponseDTOFromBed)
										.toList();
		
		return vacantBeds;
	}

}
