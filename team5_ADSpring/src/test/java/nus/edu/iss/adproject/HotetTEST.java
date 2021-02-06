//
//package nus.edu.iss.adproject;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import nus.edu.iss.adproject.model.Hotel;
//import nus.edu.iss.adproject.model.RoomType;
//import nus.edu.iss.adproject.repository.HotelRepository;
//import nus.edu.iss.adproject.repository.RoomTypeRepo;
//
//
//@SpringBootTest
//public class HotetTEST {
//
//	@Autowired
//	private HotelRepository htRepo;
//	@Autowired
//	private RoomTypeRepo RoomRepo;
//	//String Htel,  String roomType, String description, String imageURL, double price
//	@Test
//	void RoomType() {
//		RoomRepo.save(new RoomType("Marina Bay Sands Hotel ","Single","Best to rest","https://a36c2e13a78ae1256a2f-1dc878dead8ec78a84e429cdf4c9df00.ssl.cf1.rackcdn.com/responsive/980/a36c2e13a78ae1256a2f-1dc878dead8ec78a84e429cdf4c9df00.ssl.cf1.rackcdn.com/u/park-hotel-hong-kong/room/Superior-Single-Room_mid.jpg",30.00));
//		RoomRepo.save(new RoomType("Marina Bay Sands Hotel ","Single","Best to rest","https://www.parkinternationalhotel.com/d/fraserparkinternational/media/Images/__thumbs_1050_567_crop/SingleRoom_Park_International.jpg",35.00));
//		RoomRepo.save(new RoomType("Swissôtel The Stamford ","classic single","Best to U","https://media-cdn.tripadvisor.com/media/photo-s/0e/e5/f5/d2/small-single-room-at.jpg",25.00));
//		RoomRepo.save(new RoomType("Swissôtel The Stamford ","Single","Best to U","https://www.oyster.com/wp-content/uploads/sites/35/2019/05/single-room-v12936474-1440-1024x683.jpg",27.00));
//		RoomRepo.save(new RoomType("Swissôtel The Stamford ","Single","Best to U","https://setupmyhotel.com/images/Room-Type-Single-Room.jpg",29.00));
//		RoomRepo.save(new RoomType("Swissôtel The Stamford ","super  Single","Best all","http://arcadiahotel.com.sg/wp-content/uploads/2015/08/1-Superior-Single1.jpg",37.00));
//		RoomRepo.save(new RoomType("Hotel Miramar ","Single","Best all","https://cf.bstatic.com/images/hotel/max1024x768/586/58653023.jpg",40.00));
//		
//		
//		RoomRepo.save(new RoomType("Marina Bay Sands Hotel ","Double","Best to rest","double.png",50.00));
//		RoomRepo.save(new RoomType("Marina Bay Sands Hotel ","Double","Best to rest","double1.png",55.00));
//		RoomRepo.save(new RoomType("Swissôtel The Stamford ","Double","Best to U","double2.png",45.00));
//		RoomRepo.save(new RoomType("Swissôtel The Stamford ","Double","Best to U","double3.png",47.00));
//		RoomRepo.save(new RoomType("Swissôtel The Stamford ","Double","Best to U","double4.png",49.00));
//		RoomRepo.save(new RoomType("Hotel Miramar ","Double","Best all","double5.png",67.00));
//		RoomRepo.save(new RoomType("Hotel Miramar ","Double","Best all","double6.png",60.00));
//	}
////	@Test
////	void htcreate()
////	{
////		
////		htRepo.save(new Hotel("Marina Bay Sands Hotel ", 1.282302,103.858528 , 400.0, 8, "SG","aa", "best", "we try to be home"));
////		htRepo.save(new Hotel("Swissôtel The Stamford ", 1.293354,103.853561 , 260.0, 8, "SG","aa", "best", "we try to be home"));
////		htRepo.save(new Hotel("Hotel Miramar ", 1.288710,103.837372 , 260.0, 8, "SG","aa", "best", "we try to be home"));
//////		ArrayList<Hotel> plist = new ArrayList<Hotel>();		
//////		plist = (ArrayList<Hotel>) htRepo.findAll();
//////		
//////		System.out.println("Print all products");
//////		for (Iterator<Hotel> iterator = plist.iterator(); iterator.hasNext();) {
//////			Hotel product =  iterator.next();
//////			System.out.println(product.toString());
//////		}
//	}
//	
//
//
//
