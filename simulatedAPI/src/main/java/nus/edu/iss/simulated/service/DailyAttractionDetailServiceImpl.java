package nus.edu.iss.simulated.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import nus.edu.iss.simulated.model.DailyAttractionDetail;
import nus.edu.iss.simulated.model.DailyRoomTypeDetail;
import nus.edu.iss.simulated.repository.DailyAttractionDetailRepo;

@Service
@Transactional
public class  DailyAttractionDetailServiceImpl implements DailyAttractionDetailService{
	

	@Autowired
	private DailyAttractionDetailRepo dapRepo;


	@Override
	public DailyAttractionDetail findAttractionDetailById(long id) {
		// TODO Auto-generated method stub
		return dapRepo.findById(id).get();
	}

	@Override
	public List<DailyAttractionDetail> findAttractionDetailByMonthAndAttractionName
	(int monthNum) {
		// TODO Auto-generated method stub
		return dapRepo.findByMonthandAttractionName(monthNum);
	}


	@Override
	public DailyAttractionDetail findAttractionDetailByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return dapRepo.findbyDate(date);
	}

	@Override
	public Boolean UpdateTicketQuantity(DailyAttractionDetail updated) {
		dapRepo.save(updated);
		return true;
	}
	
	@Override
	public List<DailyAttractionDetail> findAttractionByPeriod(LocalDate startD, LocalDate endD) {
		
		List<DailyAttractionDetail> output = new ArrayList<>();
		//15th to 16th only one day and only needs to know 15th price
		while (endD.minusDays(1).isEqual(startD) || endD.minusDays(1).isAfter(startD)) {
			output.add(findAttractionDetailByDate(startD));
			startD = startD.plusDays(1);
		}
		return output;
	}
}