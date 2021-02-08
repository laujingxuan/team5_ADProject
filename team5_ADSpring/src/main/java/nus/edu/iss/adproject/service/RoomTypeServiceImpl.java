package nus.edu.iss.adproject.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.RoomType;
import nus.edu.iss.adproject.repository.RoomTypeRepo;

@Service
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService{
	
	@Autowired
	private RoomTypeRepo rrepo;
	
	@Override
	public void save(RoomType x) {
		rrepo.save(x);
	}
	
	@Override
	public void delete(RoomType x)
	{
		rrepo.delete(x);
	}
	@Override
	public List<RoomType> findRoomTypesByHotelId(Long id) {
		return rrepo.findRoomTypesByHotelId(id);
	}
	
	@Override
	public RoomType findById(Long id) {
		return rrepo.findById(id).get();
	}
	
	public List<RoomType> findAll(){
		return rrepo.findAll();
	}
//	public List<RoomType>  findbyHotelId(int hid){
//		return rrepo.findbyHotelId(hid);
//	}

	@Override
	public ArrayList<Object> findDistinctRoomTypes() {
		return rrepo.findDistinctRoomTypes();
	}
}
