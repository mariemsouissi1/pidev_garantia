package tn.esprit.infini2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.infini2.entities.Chat;
import tn.esprit.infini2.services.IChatService;

@RestController
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	IChatService chatService;

	// http://localhost:8083/PIDEV/chat/retrieve-all-Chats
		@GetMapping("/retrieve-all-Chats")
		@ResponseBody
		public List<Chat> getChats() {
		List<Chat> listChats = chatService.retrieveAllChats();
		return listChats;
		}
	// http://localhost:8083/PIDEV/chat/retrieve-all-conversations/{Chat-id}/{Chat-id1}
		@GetMapping("/retrieve-all-conversations/{Chat-id}/{Chat-id1}")
		@ResponseBody
		public List<Chat> getConversations(@PathVariable("Chat-id") Long id1,@PathVariable("Chat-id1") Long id2) {
		List<Chat> listChats = chatService.retrieveConversationsdistinct(id1,id2);
		return listChats;
		}
		//http://localhost:8085/PIDEV/chat/retrieve-conversation
	// http://localhost:8083/PIDEV/chat/retrieve-conversation/{Chat-id}/{Chat-id1}
		@GetMapping("/retrieve-conversation/{Chat-id}/{Chat-id1}")
		@ResponseBody
		public List<Chat> getConversation(@PathVariable("Chat-id") Long id1,@PathVariable("Chat-id1") Long id2) {
		List<Chat> listChats = chatService.retrieveConversation(id1,id2);
		return listChats;
		}
	// http://localhost:8083/PIDEV/chat/retrieve-Chat/2
		@GetMapping("/retrieve-Chat/{Chat-id}")
		@ResponseBody
		public Chat retrieveChat(@PathVariable("Chat-id") Long idChat) {
		return chatService.retrieveChat(idChat);
		}

	// http://localhost:8083/PIDEV/chat/add-Chat
		@PostMapping("/add-Chat")
		@ResponseBody
		public Chat addChat(@RequestBody Chat s)
		{
		Chat chat = chatService.addChat(s);
		return chat;
		}
	// http://localhost:8083/PIDEV/chat/remove-Chat/2
		@DeleteMapping("/remove-Chat/{Chat-id}")
		@ResponseBody
		public void removeChat(@PathVariable("Chat-id") Long ChatId) {
			chatService.deleteChat(ChatId);
		}

	// http://localhost:8083/PIDEV/chat/modify-Chat
		@PutMapping("/modify-Chat")
		@ResponseBody
		public Chat modifyChat(@RequestBody Chat Chat) {
		return chatService.updateChat(Chat);
		}
}
