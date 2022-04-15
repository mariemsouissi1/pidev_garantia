package tn.esprit.infini2.entities;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="message")
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="message_id")
	private Long message_id;//cle primaire
	private String contenu_message;
	private boolean message_visibility;
	private Long nbre_like_message;
	private Long id_sender;

	private Long nbre_unlike_message;
	@Temporal(TemporalType.TIME)
	
	private Date message_date;
	private Long conv_id;

	@ManyToOne(cascade = CascadeType.ALL)
	Conversation conversation;
	
	
	public Long getConv_id() {
		return conv_id;
	}
	public void setConv_id(Long conv_id) {
		this.conv_id = conv_id;
	}
	public Conversation getConversation() {
		return conversation;
	}
	public Long getId_sender() {
		return id_sender;
	}
	public void setId_sender(Long id_sender) {
		this.id_sender = id_sender;
	}
	public Date getMessage_date() {
		return message_date;
	}
	public void setMessage_date(Date message_date) {
		this.message_date = message_date;
	}
	public long getConversationid() {
		return conversation.getConversation_id();
	}
	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	public Long getMessage_id() {
		return message_id;
	}
	public void setMessage_id(Long message_id) {
		this.message_id = message_id;
	}
	public String getContenu_message() {
		return contenu_message;
	}
	public void setContenu_message(String contenu_message) {
		this.contenu_message = contenu_message;
	}
	public boolean isMessage_visibility() {
		return message_visibility;
	}
	public void setMessage_visibility(boolean message_visibility) {
		this.message_visibility = message_visibility;
	}
	public Long getNbre_like_message() {
		return nbre_like_message;
	}
	public void setNbre_like_message(Long nbre_like_message) {
		this.nbre_like_message = nbre_like_message;
	}
	public Long getNbre_unlike_message() {
		return nbre_unlike_message;
	}
	public void setNbre_unlike_message(Long nbre_unlike_message) {
		this.nbre_unlike_message = nbre_unlike_message;
	}
	
}
