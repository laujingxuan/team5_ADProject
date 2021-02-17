package nus.edu.iss.adproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.ChatData;
import nus.edu.iss.adproject.repository.ChatDataRepository;
@Service
@Transactional
public class ChatDataServiceImpl implements ChatDataService{
	@Autowired
	private ChatDataRepository CD_repo;
	@Override
	public List<ChatData> findAll()
	{
		return CD_repo.findAll();
	}
	@Override
	public void save(ChatData x) {
		CD_repo.save(x);
		
	}
	@Override
	public ChatData findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(ChatData x) {
		// TODO Auto-generated method stub
		
	}

}
