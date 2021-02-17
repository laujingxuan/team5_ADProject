package nus.edu.iss.simulated.service;

import java.time.LocalDate;
import java.util.List;

import nus.edu.iss.simulated.model.DailyAttractionDetail;


public interface DailyAttractionDetailService {
	
//	public List<DailyAttractionDetail> findAttractionDetailByName(AttractionName attractionName);
	
	public DailyAttractionDetail findAttractionDetailById(long id);
	
	public List<DailyAttractionDetail> findAttractionDetailByMonthAndAttractionName
	(int monthNum);

	public DailyAttractionDetail findAttractionDetailByDate(LocalDate date);

	
	public Boolean UpdateTicketQuantity(DailyAttractionDetail updated);

	List<DailyAttractionDetail> findAttractionByPeriod(LocalDate startD, LocalDate endD);
}
