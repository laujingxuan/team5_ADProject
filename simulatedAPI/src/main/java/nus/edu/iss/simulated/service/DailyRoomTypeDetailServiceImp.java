package nus.edu.iss.simulated.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.simulated.model.DailyRoomTypeDetail;
import nus.edu.iss.simulated.model.RoomType;
import nus.edu.iss.simulated.repository.DailyRoomTypeDetailRepo;

@Service
@Transactional
public class DailyRoomTypeDetailServiceImp implements DailyRoomTypeDetailService {

	@Autowired
	private DailyRoomTypeDetailRepo roomRepo;
	
	@Override
	public DailyRoomTypeDetail findRoomDetailByDateAndType(LocalDate date, RoomType roomType) {
		return roomRepo.findByDateAndRoomType(date, roomType);
	}

	@Override
	public List<DailyRoomTypeDetail> findRoomDetailsByMonthAndType(int monthNum, RoomType roomType) {
		return roomRepo.findByMonthAndRoomType(monthNum, roomType);
	}

}
