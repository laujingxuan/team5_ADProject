package nus.edu.iss.adproject.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ChatHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String chatSessionId;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private User sender;
	
	private String message;
	private Date dateTime;
	
	public ChatHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChatHistory(long id, String chatSessionId, User user, User sender, String message, Date dateTime) {
		super();
		this.id = id;
		this.chatSessionId = chatSessionId;
		this.user = user;
		this.sender = sender;
		this.message = message;
		this.dateTime = dateTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChatSessionId() {
		return chatSessionId;
	}

	public void setChatSessionId(String chatSessionId) {
		this.chatSessionId = chatSessionId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	

}
