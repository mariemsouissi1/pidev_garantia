package tn.esprit.infini2.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Chat")
public class Chat implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="chat_id")
	private Long chat_id;//cle primaire
	private String chat_message;
	private Long sender_id;
	private Long receiver_id;
	private String sender_Type;
	private String receiver_Type;
	@Temporal(TemporalType.TIME)
	private Date chat_date;
	public Long getChat_id() {
		return chat_id;
	}
	public void setChat_id(Long chat_id) {
		this.chat_id = chat_id;
	}
	public String getChat_message() {
		return chat_message;
	}
	public void setChat_message(String chat_message) {
		this.chat_message = chat_message;
	}
	public Long getSender_id() {
		return sender_id;
	}
	public void setSender_id(Long sender_id) {
		this.sender_id = sender_id;
	}
	public Long getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(Long receiver_id) {
		this.receiver_id = receiver_id;
	}
	public String getSender_Type() {
		return sender_Type;
	}
	public void setSender_Type(String sender_Type) {
		this.sender_Type = sender_Type;
	}
	public String getReceiver_Type() {
		return receiver_Type;
	}
	public void setReceiver_Type(String receiver_Type) {
		this.receiver_Type = receiver_Type;
	}
	public Date getChat_date() {
		return chat_date;
	}
	public void setChat_date(Date chat_date) {
		this.chat_date = chat_date;
	}
	
}
