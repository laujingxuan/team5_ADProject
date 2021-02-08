package nus.edu.iss.adproject.nonEntityModel;

public class DashboardQuery {
	private long hotel_id;
	private long attraction_id;
	private String hotel_name;
	private String attraction_name;
	private double monthly_revenue;
	private double monthly_guest;
	
	
	public DashboardQuery() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public DashboardQuery(long hotel_id, long attraction_id, String hotel_name, String attraction_name,
			double monthly_revenue, double monthly_guest) {
		super();
		this.hotel_id = hotel_id;
		this.attraction_id = attraction_id;
		this.hotel_name = hotel_name;
		this.attraction_name = attraction_name;
		this.monthly_revenue = monthly_revenue;
		this.monthly_guest = monthly_guest;
	}



	public long getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(long hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public double getMonthly_revenue() {
		return monthly_revenue;
	}
	public void setMonthly_revenue(double monthly_revenue) {
		this.monthly_revenue = monthly_revenue;
	}
	public double getMonthly_guest() {
		return monthly_guest;
	}
	public void setMonthly_guest(double monthly_guest) {
		this.monthly_guest = monthly_guest;
	}

	public String getAttraction_name() {
		return attraction_name;
	}

	public void setAttraction_name(String attraction_name) {
		this.attraction_name = attraction_name;
	}



	public long getAttraction_id() {
		return attraction_id;
	}

	public void setAttraction_id(long attraction_id) {
		this.attraction_id = attraction_id;
	}
	
	
}
