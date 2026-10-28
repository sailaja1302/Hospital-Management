package com.hsptl.staff.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsptl.staff.dao.DoctorRepository;
import com.hsptl.staff.dao.DoctorScheduleRepository;
import com.hsptl.staff.dao.StaffRepository;
import com.hsptl.staff.dto.DoctorScheduleAvailableDatesDTO;
import com.hsptl.staff.dto.DoctorScheduleUnavailableDatesDTO;
import com.hsptl.staff.entity.Doctor;
import com.hsptl.staff.entity.DoctorSchedule;
import com.hsptl.staff.entity.Staff;
import com.hsptl.staff.exception.DoctorNotFoundException;
import com.hsptl.staff.exception.DoctorUnavailableException;
import com.hsptl.staff.exception.StaffNotFoundException;
import com.hsptl.staff.service.DoctorScheduleService;

@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService{
	
	@Autowired
	DoctorScheduleRepository doctorScheduleRepository;
	
	@Autowired
	StaffRepository staffRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Override
	public String markDoctorUnavailable(DoctorScheduleUnavailableDatesDTO doctorSchedule) {
		
		int docAppointments = 0;

		Long staffId = doctorSchedule.getStaffId();
		Long doctorId = doctorSchedule.getDoctorId();
	
		Doctor doctor = doctorRepository.findById(doctorId)
		.orElseThrow(() -> new DoctorNotFoundException("No Doctor found with ID :"+doctorId));
		
		List<DoctorSchedule> docSchedule = doctorScheduleRepository.findDoctorScheduleByDoctorId(doctorId);
		
		Staff staff = staffRepository.findById(doctorSchedule.getStaffId())
				.orElseThrow(()-> new StaffNotFoundException("Staff not found with Staff ID :"+staffId));
		
		if(doctor.getIsActive()==false) {
			throw new DoctorUnavailableException("Doctor unavailable. ");
		}
		LocalDateTime now = LocalDateTime.now(); 
		
		DoctorSchedule targetSchedule = docSchedule.isEmpty() ? 
										DoctorSchedule
										.builder()
										.doctorId(doctorId)
										.staff(staff)
										.unavailableDates(new java.util.ArrayList<>())
										.build() : 
										docSchedule.get(0);
		targetSchedule.setStaff(staff);
		
		List<LocalDateTime> unavailableList = targetSchedule.getUnavailableDates() != null 
							? new java.util.ArrayList<>(targetSchedule.getUnavailableDates()) 
							: new java.util.ArrayList<>();
		
				
		for(LocalDateTime slot : doctorSchedule.getUnavailableDates()){
			
			if(slot.isBefore(now)) {
				throw new IllegalArgumentException("availability date cannot be in past"+slot);
			}
			
			if(slot.getMinute() != 0 || slot.getSecond() != 0) {
				throw new IllegalArgumentException("Slots must be in uniform time increment (eg : 2026-10-29T10:00). Invalid Slot : "+slot);
			}
		
			boolean anyMatch = unavailableList
									.stream()
									.map(existingSlot -> existingSlot.withNano(0))
									.anyMatch(existingSlot -> existingSlot.equals(slot));
			
			if (anyMatch) {
				throw new DoctorUnavailableException ("Doctor slot already occupied . Please choose another slot.");
			}		
			
			unavailableList.add(slot);
			docAppointments++;
			targetSchedule.setUnavailableDates(unavailableList);
			doctorScheduleRepository.save(targetSchedule);
		}
		
		
		
		return "Successfully marked unavailable for doctor with "+docAppointments +" slots";
	}



	@Override
	public String markDoctorAvailable(DoctorScheduleAvailableDatesDTO doctorAvailable) {
		
		Long doctorId = doctorAvailable.getDoctorId();
		Long staffId = doctorAvailable.getStaffId();
		
		List<DoctorSchedule> doctorSchedule = doctorScheduleRepository
											.findDoctorScheduleByDoctorId(doctorId);
		
		int removedCount = 0;
		LocalDateTime now = LocalDateTime.now();
		
		 Staff staff = staffRepository.findById(staffId)
				 		.orElseThrow(() -> new StaffNotFoundException("Staff not found with ID :"+staffId));
		
		DoctorSchedule targetSchedule = doctorSchedule.isEmpty() ? 
										DoctorSchedule
										.builder()
										.doctorId(doctorId)
										.staff(staff)
										.unavailableDates(new java.util.ArrayList<>())
										.build():doctorSchedule.get(0);
		
		List<LocalDateTime> unavailable = targetSchedule.getUnavailableDates() != null 
													? new java.util.ArrayList<>(targetSchedule.getUnavailableDates()) 
													: new java.util.ArrayList<>();
		
		List<LocalDateTime> available = targetSchedule.getAvailableDates() != null 
												? new java.util.ArrayList<>(targetSchedule.getAvailableDates()) 
												: new java.util.ArrayList<>();
		
		for (LocalDateTime slot : doctorAvailable.getAvailableDates()) {
			
			
			if(slot.isBefore(now)) {
				throw new IllegalArgumentException("Availability date cannot be in past"+slot);
			}
				
			
			if(slot.getMinute() != 00 || slot.getSecond() != 00) {
				throw new IllegalArgumentException("Slots must be in uniform time increment (eg : 2026-10-29T10:00 ). Invalid Slot : "+slot);
			}
			
			if(!unavailable.contains(slot)) {
				throw new DoctorUnavailableException("Doctor schedule is already available for selected date and Time "+slot +". Nothing to mark available");
			}
			
			if(unavailable != null && unavailable.contains(slot)) {
				available.add(slot);
				removedCount++;
			}
			
			targetSchedule.setAvailableDates(available);
			unavailable.remove(slot);
			doctorScheduleRepository.save(targetSchedule);
		}
	
		return "Successfully marked Doctor schedule to available for "+ doctorAvailable.getAvailableDates() +" for "+ removedCount+" dates.";
	}

}




