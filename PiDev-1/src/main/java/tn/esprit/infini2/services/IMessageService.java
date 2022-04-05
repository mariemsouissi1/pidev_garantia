package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.Message;

public interface IMessageService {
	Message addMessage(Message c,long id);

	Message updateMessage(Message Message);

	void deleteMessage(Long MessageId);

	List<Message> retrieveAllMessages();

	List<Message> retrieveMessagesdistinct(Long id1, Long id2);

	List<Message> retrieveMessage(Long id1, Long id2);

	Message retrieveMessage(Long idMessage);
	Message Unlike_message(Long id);
	Message Like_message(Long id);
}
