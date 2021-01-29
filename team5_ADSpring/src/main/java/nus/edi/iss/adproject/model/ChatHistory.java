package nus.edi.iss.adproject.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	private String Chat_sessin_id;
	private String Sender_id;
	private String Message;
	private Date DateTime;
	
	public ChatHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChatHistory(String chat_sessin_id, String sender_id, String message, Date dateTime) {
		super();
		Chat_sessin_id = chat_sessin_id;
		Sender_id = sender_id;
		Message = message;
		DateTime = dateTime;
	}
	public String getChat_sessin_id() {
		return Chat_sessin_id;
	}
	public void setChat_sessin_id(String chat_sessin_id) {
		Chat_sessin_id = chat_sessin_id;
	}
	public String getSender_id() {
		return Sender_id;
	}
	public void setSender_id(String sender_id) {
		Sender_id = sender_id;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public Date getDateTime() {
		return DateTime;
	}
	public void setDateTime(Date dateTime) {
		DateTime = dateTime;
	}
	@Override
	public String toString() {
		return "ChatHistory [Chat_sessin_id=" + Chat_sessin_id + ", Sender_id=" + Sender_id + ", Message=" + Message
				+ ", DateTime=" + DateTime + "]";
	}
	
}
