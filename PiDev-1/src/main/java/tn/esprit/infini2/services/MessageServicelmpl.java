package tn.esprit.infini2.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.infini2.entities.Conversation;
import tn.esprit.infini2.entities.Message;
import tn.esprit.infini2.repositories.ConversationRepository;
import tn.esprit.infini2.repositories.MessageRepository;

@Service
public class MessageServicelmpl implements IMessageService{
@Autowired
MessageRepository messageRepository;

@Autowired
ConversationRepository conversationRepository;


////////////////////////////////////////add Message//////////////////////////////////////////////

public Message addMessage(Message c,long id) {
	c.setMessage_visibility(true);
	long in=0;
	c.setNbre_like_message(in);
	c.setNbre_unlike_message(in);
	c.setConv_id(id);
	Conversation conv=conversationRepository.findById(id).get();
	c.setConversation(conv);
	Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, 1);
    Date d = cal.getTime();
	//Date d =new Date();
	c.setMessage_date(d);
	messageRepository.save(c);
	return c;
}

/////////////////////////////////////like message////////////////////////////////////////////////////////

public Message Like_message(Long id) {
	Message msg =messageRepository.findById(id).get();
	msg.setNbre_like_message(msg.getNbre_like_message()+1);
	messageRepository.save(msg);
	return msg;
}

/////////////////////////////////////Unlike message////////////////////////////////////////////////////////

public Message Unlike_message(Long id) {
	Message msg =messageRepository.findById(id).get();
	msg.setNbre_unlike_message(msg.getNbre_unlike_message()+1);
	messageRepository.save(msg);
	return msg;
}

@Override
public Message updateMessage(Message Message) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void deleteMessage(Long MessageId) {
	// TODO Auto-generated method stub
	
}

@Override
public List<Message> retrieveAllMessages() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Message> retrieveMessagesdistinct(Long id1, Long id2) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Message> retrieveMessage(Long id1, Long id2) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Message retrieveMessage(Long idMessage) {
	// TODO Auto-generated method stub
	return null;
}
}
