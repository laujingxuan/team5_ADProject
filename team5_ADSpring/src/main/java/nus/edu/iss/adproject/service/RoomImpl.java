package nus.edu.iss.adproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.RoomType;
import nus.edu.iss.adproject.repository.RoomTypeRepo;

@Transactional
@Service("RoomTypeService")
public class RoomImpl implements RoomTypeService{
	@Autowired
	RoomTypeRepo roomtypeRepo;
	
	public List<RoomType> findAll(){
		return roomtypeRepo.findAll();
	}

}
