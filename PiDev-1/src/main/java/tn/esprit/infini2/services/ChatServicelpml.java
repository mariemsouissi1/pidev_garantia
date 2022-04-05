package tn.esprit.infini2.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.infini2.entities.Chat;
import tn.esprit.infini2.repositories.ChatRepository;

@Service
public class ChatServicelpml implements IChatService {
	@Autowired
	ChatRepository chatRepository;
	@Override
	public List<Chat> retrieveAllChats() {
		return (List<Chat>) chatRepository.findAll();
	}

	@Override
	public Chat addChat(Chat c) {
		//Calendar calendar = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date d = cal.getTime();
		//Date d =new Date();
		c.setChat_date(d);
		chatRepository.save(c);
		return c;
	}

	@Override
	public void deleteChat(Long id) {
		chatRepository.deleteById(id);		
	}

	@Override
	public Chat updateChat(Chat u) {
		chatRepository.save(u);
		return u;
	}

	@Override
	public Chat retrieveChat(Long id) {
		return chatRepository.findById(id).orElse(null);
	}
	@Override
	public List<Chat> retrieveConversationsdistinct(Long id1,Long id2) {
		return (List<Chat>) chatRepository.RetrieveConversations(id1,id2);
	}

	
	@Override
	public List<Chat> retrieveConversation(Long id1,Long id2) {
		return (List<Chat>) chatRepository.RetrieveConversation(id1,id2);
	}
}
