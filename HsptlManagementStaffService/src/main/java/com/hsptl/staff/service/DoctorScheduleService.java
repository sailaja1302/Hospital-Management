package com.hsptl.staff.service;

import com.hsptl.staff.dto.DoctorScheduleAvailableDatesDTO;
import com.hsptl.staff.dto.DoctorScheduleResponseDTO;
import com.hsptl.staff.dto.DoctorScheduleUnavailableDatesDTO;

public interface DoctorScheduleService {
	
	public String markDoctorUnavailable(DoctorScheduleUnavailableDatesDTO doctorSchedule);

	public String markDoctorAvailable(DoctorScheduleAvailableDatesDTO doctorAvailable);

}
