package nus.edu.iss.simulated.service;

import java.time.LocalDate;
import java.util.List;

import nus.edu.iss.simulated.model.DailyRoomTypeDetail;
import nus.edu.iss.simulated.model.HotelBooking;
import nus.edu.iss.simulated.nonEntityModel.DailyRoomDetailWrapper;


public interface DailyRoomTypeDetailService {

	public DailyRoomTypeDetail findRoomDetailByDateAndType(LocalDate date, String roomType);
	
	public List<DailyRoomTypeDetail> findRoomDetailsByMonthAndType(int monthNum, String roomType);
	
	public List<DailyRoomTypeDetail> findRoomDetailsByPeriodAndType(LocalDate startD, LocalDate endD, String roomType);
	
	public Boolean updateVacanciesQuantity(DailyRoomDetailWrapper updated);
	
	public DailyRoomTypeDetail updateDailyRoomTypeDetail(DailyRoomTypeDetail dailyRoom);

}
