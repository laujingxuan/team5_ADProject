package nus.edu.iss.simulated.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import nus.edu.iss.simulated.model.DailyAttractionDetail;
import nus.edu.iss.simulated.repository.DailyAttractionDetailRepo;


public interface DailyAttractionDetailService {
	
	public List<DailyAttractionDetail> findAttractionDetailByName(String Name);
	
	public DailyAttractionDetail findAttractionDetailById(long id);
	
	public List<DailyAttractionDetail> findAttractionDetailByMonth(int monthNum);
	

	public List<DailyAttractionDetail> findAttractionDetailByDate(LocalDate date);
	

}
