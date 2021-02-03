package nus.edu.iss.simulated.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.edu.iss.simulated.model.DailyAttractionDetail;
import nus.edu.iss.simulated.model.DailyRoomTypeDetail;


public interface DailyAttractionDetailRepo extends JpaRepository<DailyAttractionDetail, Long> {

	public List<DailyAttractionDetail> findDailyAttractionDetailByName(String name);
	
	@Query("SELECT r FROM DailyAttractionDetail r WHERE MONTH(r.date) = :monthNum" )
	public List<DailyAttractionDetail> findByMonthandAttractionName
	(@Param("monthNum") int monthNum );
	

	@Query("SELECT r FROM DailyAttractionDetail r WHERE r.date = :date AND r.name = :attractionName " )
	public DailyAttractionDetail findbyDateAndAttractionName(@Param("date")LocalDate date ,String attractionName);

	@Query("SELECT r FROM DailyAttractionDetail r WHERE r.date = :date ")
	public DailyAttractionDetail findbyDate(@Param("date") LocalDate date);

	
}