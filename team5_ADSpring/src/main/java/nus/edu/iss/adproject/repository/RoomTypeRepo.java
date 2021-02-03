package nus.edu.iss.adproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.edu.iss.adproject.model.RoomType;

public interface RoomTypeRepo extends JpaRepository<RoomType, Long> {
	

	

	public List<RoomType> findAll();

}
