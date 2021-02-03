package nus.edu.iss.simulated.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import nus.edu.iss.simulated.model.DailyAttractionDetail;
import nus.edu.iss.simulated.repository.DailyAttractionDetailRepo;


public interface DailyAttractionDetailService {
	
//	public List<DailyAttractionDetail> findAttractionDetailByName(AttractionName attractionName);
	
	public DailyAttractionDetail findAttractionDetailById(long id);
	
	public List<DailyAttractionDetail> findAttractionDetailByMonthAndAttractionName
	(int monthNum);
	

	public DailyAttractionDetail findAttractionDetailByDateAndAttractionName
	(LocalDate date,String attractionName);
	

}
