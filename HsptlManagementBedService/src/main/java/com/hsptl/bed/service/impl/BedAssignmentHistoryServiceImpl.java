package com.hsptl.bed.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsptl.bed.client.PatientClient;
import com.hsptl.bed.dao.BedAssignmentRepository;
import com.hsptl.bed.dao.BedRepository;
import com.hsptl.bed.dao.RoomRepository;
import com.hsptl.bed.dto.BedAssignmentHistoryResponseDTO;
import com.hsptl.bed.dto.PatientResponseDTO;
import com.hsptl.bed.entity.Bed;
import com.hsptl.bed.entity.BedAssignmentHistory;
import com.hsptl.bed.entity.Room;
import com.hsptl.bed.exception.BedNotFoundException;
import com.hsptl.bed.exception.BedUnavailableException;
import com.hsptl.bed.exception.PatientNotFoundException;
import com.hsptl.bed.exception.RoomNotFoundException;
import com.hsptl.bed.service.BedAssignmentService;

@Service
public class BedAssignmentHistoryServiceImpl implements BedAssignmentService{

	@Autowired
	BedAssignmentRepository bedAssignmentRepository;
	
	@Autowired
	BedRepository bedRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	PatientClient patientClient;
	
	@Override
	public String assignBedToPatient( Long bedNum, Long patientId) {
		
		try {
		PatientResponseDTO patientByPatientId = patientClient.getPatientByPatientId(patientId);
		}
		catch(feign.FeignException.NotFound e) {
			throw new PatientNotFoundException("Patient not found with ID :"+patientId);
		}
		Bed bed = bedRepository
				.findById(bedNum)
				.orElseThrow(()->new BedNotFoundException ("Bed Not Found with ID : "+bedNum ));
				
		
		if (bed.getIsOccupied() == true) {
			throw new BedUnavailableException("Bed "+bedNum+" is already occupied.");
		}

			bed.setIsOccupied(true);
			bed.setPatientId(patientId);
			bedRepository.save(bed);
			
			BedAssignmentHistory bedAssignmentHistory = new BedAssignmentHistory();
			
			bedAssignmentHistory.setBed(bed);
			bedAssignmentHistory.setPatientId(patientId);
			bedAssignmentHistory.setBedNum(bedNum);
			bedAssignmentHistory.setAssignedAt(LocalDateTime.now());
			
			if(bed.getBedAssignmentHistory() == null) {
				bed.setBedAssignmentHistory(new ArrayList<>());
			}
			bed.getBedAssignmentHistory().add(bedAssignmentHistory);
			
			bedAssignmentRepository.save(bedAssignmentHistory);
			BedAssignmentHistoryResponseDTO bedAssignmentResponse = new BedAssignmentHistoryResponseDTO();
			BeanUtils.copyProperties(bedAssignmentHistory, bedAssignmentResponse);
		
		return "Successfully assigned Bed with ID : "+bedNum+" to the patient with ID : "+patientId;
	}

	@Override
	public String vacateBedInRoom(Long bedNumber, Long roomNum) {
		Bed existingBed = bedRepository.findById(bedNumber)
							.orElseThrow(()-> new BedNotFoundException("No Bed found with ID : "+bedNumber));
		
		 Room existingRoom = roomRepository.findById(roomNum)
								.orElseThrow(()-> new RoomNotFoundException("No Room found with ID : "+roomNum));
		 
		 if(existingBed.getIsOccupied() == false) {
			 throw new IllegalArgumentException("No active assignments found for Bed with ID : "+bedNumber);
		 }
		 
		 existingBed.setIsOccupied(false);
		 existingBed.setRoomNum(existingRoom);
		 existingBed.setPatientId(null);
		 bedRepository.save(existingBed);
		 
		 BedAssignmentHistory bedAssignmentHistory = new BedAssignmentHistory();
		 
		 bedAssignmentHistory.setBed(existingBed);
		 bedAssignmentHistory.setBedNum(bedNumber);
		 bedAssignmentHistory.setPatientId(null);
		 bedAssignmentHistory.setVacatedAt(LocalDateTime.now());
		 
		 BedAssignmentHistoryResponseDTO bedAssignmentResponse = new BedAssignmentHistoryResponseDTO();
		 
		 BeanUtils.copyProperties(bedAssignmentHistory, bedAssignmentResponse);
		return "Successfully vacated Bed with ID : "+bedNumber +" in the room "+roomNum;
	}

}
