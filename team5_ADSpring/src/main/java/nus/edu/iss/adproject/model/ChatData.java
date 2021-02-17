package nus.edu.iss.adproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatData {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String Sender;
	private String Message;
	public String getSender() {
		return Sender;
	}
	public void setSender(String sender) {
		Sender = sender;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public ChatData(String sender, String message) {
		super();
		Sender = sender;
		Message = message;
	}
	public ChatData() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ChatData [Sender=" + Sender + ", Message=" + Message + "]";
	}
	

}
