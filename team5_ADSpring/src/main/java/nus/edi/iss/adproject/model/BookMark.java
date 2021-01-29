package nus.edi.iss.adproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookMark {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	private long UserId;
	private long ProductId;
	
	public BookMark() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookMark(long userId, long productId) {
		super();
		UserId = userId;
		ProductId = productId;
	}
	public long getUserId() {
		return UserId;
	}
	public void setUserId(long userId) {
		UserId = userId;
	}
	public long getProductId() {
		return ProductId;
	}
	public void setProductId(long productId) {
		ProductId = productId;
	}
	@Override
	public String toString() {
		return "BookMark [Id=" + Id + ", UserId=" + UserId + ", ProductId=" + ProductId + "]";
	}
	
	
}
