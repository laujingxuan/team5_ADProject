package nus.edu.iss.simulated.service;

import java.time.LocalDate;
import java.util.List;

import nus.edu.iss.simulated.model.DailyRoomTypeDetail;

public interface DailyRoomTypeDetailService {

	public DailyRoomTypeDetail findRoomDetailByDateAndType(LocalDate date, String roomType);
	
	public List<DailyRoomTypeDetail> findRoomDetailsByMonthAndType(int monthNum, String roomType);
	
	public List<DailyRoomTypeDetail> findRoomDetailsByPeriodAndType(LocalDate startD, LocalDate endD, String roomType);
}
