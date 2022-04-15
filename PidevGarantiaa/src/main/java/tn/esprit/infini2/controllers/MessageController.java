package tn.esprit.infini2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.infini2.entities.Message;
import tn.esprit.infini2.services.IMessageService;
@RestController
@RequestMapping("/message")
public class MessageController {
	@Autowired
	IMessageService messageService;
	
///////////////////////////////////////addMessage///////////////////////////////////////////
	
// http://localhost:8085/PIDEV/message/add-Message/1
		@PostMapping("/add-Message/{Conversation-id}")
		@ResponseBody
		public Message addMessage(@RequestBody Message s,@PathVariable ("Conversation-id") Long id)
		{
			Message message = messageService.addMessage(s,id);
			return message;
		}

///////////////////////////////////////////like message////////////////////////////////////////
		
// http://localhost:8085/PIDEV/message/like-message/1
				@PutMapping("/like-message/{Message-id}")
				@ResponseBody
				public Message like_message(@PathVariable("Message-id") Long id) {
					return messageService.Like_message(id);
				}	

///////////////////////////////////////////unlike message////////////////////////////////////////
				
//http://localhost:8085/PIDEV/message/unlike-message/1
			@PutMapping("/unlike-message/{Message-id}")
			@ResponseBody
			public Message modifyConversation(@PathVariable("Message-id") Long id) {
				return messageService.Unlike_message(id);
			}	
}
