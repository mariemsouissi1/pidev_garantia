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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.infini2.entities.Conversation;
import tn.esprit.infini2.entities.Conversation;
import tn.esprit.infini2.entities.Conversation;
import tn.esprit.infini2.services.IConversationService;

@RestController
@RequestMapping("/conversation")

public class ConversationController {
	@Autowired
	IConversationService conversationService;
	
///////////////////////////////////////addConversation///////////////////////////////////////////
	
// http://localhost:8085/PIDEV/conversation/add-Conversation
		@PostMapping("/add-Conversation")
		@ResponseBody
		public String addConversation(@RequestBody Conversation c)
		{
			String s = conversationService.addConversation(c);
			return s;
		}

//////////////////////////////////////////quitter la conversation/////////////////////////////////////
		

		//http://localhost:8085/PIDEV/conversation/quitter-Conversation/1/3
@PutMapping("/quitter-Conversation/{Conversation-id}/{Employee-id}")
@ResponseBody
		public Conversation quitterConversation(@PathVariable("Conversation-id") Long idconv,@PathVariable("Employee-id") Long idempl) {
		return conversationService.quitterConversation(idconv ,idempl);
}	

//////////////////////////////////////////quitter la conversation/////////////////////////////////////


//http://localhost:8085/PIDEV/conversation/ajouter-member/1/2
@PutMapping("/ajouter-member/{Conversation-id}/{Employee-id}")
@ResponseBody
		public Conversation ajoutermembre(@PathVariable("Conversation-id") Long idconv,@PathVariable("Employee-id") Long idempl) {
		return conversationService.ajoutermembre(idconv ,idempl);
}	
		
////////////////////////////////////////////retrieve conversation//////////////////////////////////
	// http://localhost:8085/PIDEV/conversation/retrieve-conversation/1/2
		@GetMapping("/retrieve-conversation/{Conversation-id}/{Employee-id}")
		@ResponseBody
		public String getConversation(@PathVariable("Conversation-id") Long idconv ,@PathVariable("Employee-id") Long idempl) {
			String ch=conversationService.retrieveConversation(idconv, idempl);
			return ch;
		}

////////////////////////////////////////remove Conversation/////////////////////////////////////////		

// http://localhost:8085/PIDEV/Conversation/remove-Conversation/2
		@DeleteMapping("/remove-Conversation/{Conversation-id}")
		@ResponseBody
		public String removeConversation(@PathVariable("Conversation-id") Long ConversationId) {
			conversationService.deleteConversation(ConversationId);
			return("Message deleted");
		}

/////////////////////////////////////////modify Conversation////////////////////////////////////////

// http://localhost:8085/PIDEV/conversation/rename-Conversation/2
		@PutMapping("/rename-Conversation/{Conversation-id}")
		@ResponseBody
		public Conversation modifyConversation(@PathVariable("Conversation-id") Long id,@RequestParam String name) {
			return conversationService.renameConversation(id ,name);
		}	

//////////////////////////////////retrieve conversation par id///////////////////////////////////////////
	
	// http://localhost:8085/PIDEV/conversation/retrieve-Conversations-par-id/1
		@GetMapping("/retrieve-Conversations-par-id/{Employee-id}")
		@ResponseBody
		public List<Conversation> getConversations(@PathVariable("Employee-id") Long idempl) {
			List<Conversation> listConversations = conversationService.retrieveConversationsparid(idempl);
			return listConversations;
		}
	// http://localhost:8085/PIDEV/Conversation/retrieve-members/1
		@GetMapping("/retrieve-members/{Conversation-id}")
		@ResponseBody
		public String getmembers(@PathVariable("Conversation-id") Long id1) {
			String listmembers = conversationService.retrievemembers(id1);
			return listmembers;
		}

/////////////////////////////////////////archive Conversation//////////////////////////////////////////
		
// http://localhost:8085/PIDEV/conversation/archive-Conversation/2
	@PutMapping("/archive-Conversation/{Conversation-id}")
	@ResponseBody
	public Conversation archiveConversation(@PathVariable("Conversation-id") Long ConversationId) {
	return conversationService.archiverConversation(ConversationId);
	}

/////////////////////////////////////////desarchive Conversation//////////////////////////////////////////


// http://localhost:8085/PIDEV/conversation/desarchive-Conversation/2
	@PutMapping("/desarchive-Conversation/{Conversation-id}")
	@ResponseBody
	public Conversation desarchiveConversation(@PathVariable("Conversation-id") Long ConversationId) {
	return conversationService.desarchiverConversation(ConversationId);
	}
////////////////////////////////////////////////retrieve ///////////////////////////////////////////

	// http://localhost:8085/PIDEV/Conversation/retrieve-all-Conversations
	
	@GetMapping("/retrieve-all-Conversations")
	@ResponseBody
	public List<Conversation> getConversations() {
	List<Conversation> listConversations = conversationService.retrieveAllconversations();
	return listConversations;
	}

	// http://localhost:8085/PIDEV/Conversation/retrieve-Conversation/2
	
	@GetMapping("/retrieve-Conversation/{Conversation-id}")
	@ResponseBody
	public Conversation retrieveConversation(@PathVariable("Conversation-id") Long idConversation) {
	return conversationService.retrieveconversation(idConversation);
	}
}
