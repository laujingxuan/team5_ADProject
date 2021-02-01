package nus.edu.iss.simulated.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.edu.iss.simulated.model.DailyRoomTypeDetail;
import nus.edu.iss.simulated.model.RoomType;

public interface DailyRoomTypeDetailRepo extends JpaRepository<DailyRoomTypeDetail,Long> {

	public DailyRoomTypeDetail findByDateAndRoomType(LocalDate date, RoomType roomType);
	
	@Query("SELECT r FROM DailyRoomTypeDetail r WHERE r.roomType = :roomType AND MONTH(r.date) = :monthNum ")
	public List<DailyRoomTypeDetail> findByMonthAndRoomType(@Param("monthNum") int monthNum, @Param("roomType") RoomType roomType);
}
