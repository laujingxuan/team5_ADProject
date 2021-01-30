package nus.edu.iss.simulated.service;

import java.time.LocalDate;
import java.util.List;

import nus.edu.iss.simulated.model.DailyRoomTypeDetail;
import nus.edu.iss.simulated.model.RoomType;

public interface DailyRoomTypeDetailService {

	public DailyRoomTypeDetail findRoomDetailByDateAndType(LocalDate date, RoomType roomType);
	
	public List<DailyRoomTypeDetail> findRoomDetailsByMonthAndType(int monthNum, RoomType roomType);
}
