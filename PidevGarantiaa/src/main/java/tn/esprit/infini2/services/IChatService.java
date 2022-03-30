package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.Chat;

public interface IChatService {
	List<Chat> retrieveAllChats();

	Chat addChat(Chat c);

	void deleteChat(Long id);

	Chat updateChat(Chat u);

	Chat retrieveChat(Long id);

	List<Chat> retrieveConversationsdistinct(Long id1, Long id2);

	List<Chat> retrieveConversation(Long id1, Long id2);
}
