import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.simulated.model.DailyAttractionDetail;
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
	public List<DailyAttractionDetail> findAttractionDetailByMonth(int monthNum) {
		// TODO Auto-generated method stub
		return dapRepo.findByMonth(monthNum);
	}


	@Override
	public List<DailyAttractionDetail> findAttractionDetailByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return dapRepo.findbyDate(date);
	}
	
	
	@Override
	public List<DailyAttractionDetail> findAttractionDetailByName(String name) {
		// TODO Auto-generated method stub
		return dapRepo.findDailyAttractionDetailByName(name);
	}



}