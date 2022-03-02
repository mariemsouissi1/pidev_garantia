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
	private Long seder_id;
	private Long receive_id;
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




	public Long getSeder_id() {
		return seder_id;
	}




	public void setSeder_id(Long seder_id) {
		this.seder_id = seder_id;
	}




	public Long getReceive_id() {
		return receive_id;
	}




	public void setReceive_id(Long receive_id) {
		this.receive_id = receive_id;
	}




	public Date getChat_date() {
		return chat_date;
	}




	public void setChat_date(Date chat_date) {
		this.chat_date = chat_date;
	}

}