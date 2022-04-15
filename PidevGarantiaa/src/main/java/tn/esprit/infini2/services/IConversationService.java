package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.Conversation;

public interface IConversationService {

	String addConversation(Conversation c);
	Conversation renameConversation(Long id, String name);
	void deleteConversation(Long conversationId);

	List<Conversation> retrieveConversationsparid(Long id);

	String retrievemembers(Long idconv);
	String retrieveConversation(Long idconv,Long idempl);
 
	Conversation quitterConversation(Long idconv,Long idempl);


	Conversation ajoutermembre(Long idconv, Long idempl);
	Conversation desarchiverConversation(Long conversationId);
	Conversation archiverConversation(Long conversationId);
	List<Conversation> retrieveAllconversations();
	Conversation retrieveconversation(Long id);
	
	
}
