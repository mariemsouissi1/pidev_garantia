package tn.esprit.infini2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.infini2.entities.Chat;
import tn.esprit.infini2.repositories.ChatRepository;

@Service
public class PdfGeneratorService implements IChatService {
	@Autowired
	ChatRepository chatRepository;
	@Override
	public List<Chat> retrieveAllChats() {
		return (List<Chat>) chatRepository.findAll();
	}

	@Override
	public Chat addChat(Chat c) {
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

}
