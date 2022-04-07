package tn.esprit.infini2.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="conversation")
public class Conversation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="conversation_id")
	private Long conversation_id;//cle primaire
	@ElementCollection

	private List <Long>  conversation_members;
	@ElementCollection

	private List <Long>  conversation_nonmembers;
	private boolean conversation_visibility;
	@Enumerated(EnumType.STRING)
	private conversation_type conversation_type;
	private String  conversation_name;

	public conversation_type getConversation_type() {
		return conversation_type;
	}
	public void setConversation_type(conversation_type conversation_type) {
		this.conversation_type = conversation_type;
	}
	
	public List<Long> getConversation_nonmembers() {
		return conversation_nonmembers;
	}
	public void setConversation_nonmembers(List<Long> conversation_nonmembers) {
		this.conversation_nonmembers = conversation_nonmembers;
	}
	public String getConversation_name() {
		return conversation_name;
	}
	public void setConversation_name(String conversation_name) {
		this.conversation_name = conversation_name;
	}
	
	
	@Override
	public String toString() {
		return "Conversation [conversation_id=" + conversation_id + ", conversation_members=" + conversation_members
				+ ", conversation_nonmembers=" + conversation_nonmembers + ", conversation_visibility="
				+ conversation_visibility + ", conversation_type=" + conversation_type + ", conversation_name="
				+ conversation_name + "]";
	}
	public Long getConversation_id() {
		return conversation_id;
	}
	public void setConversation_id(Long conversation_id) {
		this.conversation_id = conversation_id;
	}
	public List<Long> getConversation_members() {
		return conversation_members;
	}
	public void setConversation_members(List<Long> conversation_members) {
		this.conversation_members = conversation_members;
	}
	public boolean isConversation_visibility() {
		return conversation_visibility;
	}
	public void setConversation_visibility(boolean conversation_visibility) {
		this.conversation_visibility = conversation_visibility;
	}
	
	
	
}
