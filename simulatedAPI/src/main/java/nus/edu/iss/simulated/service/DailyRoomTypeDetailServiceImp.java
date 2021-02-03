package nus.edu.iss.simulated.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.simulated.model.DailyRoomTypeDetail;
import nus.edu.iss.simulated.nonEntityModel.DailyRoomDetailWrapper;
import nus.edu.iss.simulated.repository.DailyRoomTypeDetailRepo;

@Service
@Transactional
public class DailyRoomTypeDetailServiceImp implements DailyRoomTypeDetailService {

	@Autowired
	private DailyRoomTypeDetailRepo roomRepo;
	
	@Override
	public DailyRoomTypeDetail findRoomDetailByDateAndType(LocalDate date, String roomType) {
		return roomRepo.findByDateAndRoomType(date, roomType);
	}

	@Override
	public List<DailyRoomTypeDetail> findRoomDetailsByMonthAndType(int monthNum, String roomType) {
		return roomRepo.findByMonthAndRoomType(monthNum, roomType);
	}
	
	@Override
	public List<DailyRoomTypeDetail> findRoomDetailsByPeriodAndType(LocalDate startD, LocalDate endD, String roomType) {
		
		List<DailyRoomTypeDetail> output = new ArrayList<>();
		//15th to 16th only one day and only needs to know 15th price
		while (endD.minusDays(1).isEqual(startD) || endD.minusDays(1).isAfter(startD)) {
			output.add(findRoomDetailByDateAndType(startD, roomType));
			System.out.println("startD" + startD);
			System.out.println("endD" + endD);
			startD = startD.plusDays(1);
		}
		return output;
	}

	@Override
	public Boolean UpdateVacanciesQuantity(DailyRoomDetailWrapper updated) {
		for (DailyRoomTypeDetail d : updated.getDailyList()) {
			roomRepo.save(d);
		}
		return true;
	}

}
