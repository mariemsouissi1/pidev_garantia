package tn.esprit.infini2.services;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.infini2.repositories.ConversationRepository;
import tn.esprit.infini2.repositories.EmployeeRepository;
import tn.esprit.infini2.repositories.MessageRepository;
import tn.esprit.infini2.entities.Conversation;
import tn.esprit.infini2.entities.Conversation;
import tn.esprit.infini2.entities.Employee;
import tn.esprit.infini2.entities.Message;
import tn.esprit.infini2.entities.Offer;

@Service
public class ConversationServicelmpl implements IConversationService {
@Autowired
ConversationRepository conversationRepository;

@Autowired
MessageRepository messageRepository;

@Autowired
EmployeeRepository employeeRepository;




/////////////////////////////////////////add conversation///////////////////////////////////////

@Override
public String addConversation(Conversation c) {
	c.setConversation_visibility(true);
	List<Long> l = null ;
	c.setConversation_nonmembers(l);
	String s = null;
	if(c.getConversation_type().toString()=="Groupe")
	{
		if(c.getConversation_members().size()>=3) 
		{
			conversationRepository.save(c);
			s="Ajout effectué avec succés \r\n"+c.toString();
			
		}
		else 
			 s="vous ne pouvez pas creer le groupe "+c.getConversation_name()+"  il faut que le nombre des membres soit superieur à 3";
		return s;
	}
	else
	{
		if(c.getConversation_members().size()==2) 
		{
			conversationRepository.save(c);
			s="Ajout effectué avec succés \r\n"+c.toString();
			
		}
		else 
		{
			 s="vous ne pouvez pas creer la conversation ";
		}
	}
	return s;
}
/////////////////////////////////////Rename Conversation/////////////////////////////////////////////////////


public Conversation renameConversation(Long id, String name) {
	Conversation conv =conversationRepository.findById(id).get();
	conv.setConversation_name(name);
	conversationRepository.save(conv);
	return conv;
}

////////////////////////////////////quitter conversation/////////////////////////////////////////////////////


public Conversation quitterConversation(Long idconv,Long idempl) {
	Conversation conv =conversationRepository.findById(idconv).get();
	
	List<Long> members=conv.getConversation_members();
	List<Long> nonmembers=conv.getConversation_nonmembers();

	if(members.contains(idempl)) 
	{
		long i=(long) nonmembers.indexOf(idempl);
		members.remove(i);	
		members.remove(idempl);
		conv.setConversation_members(members);
		if(nonmembers.contains(idempl)==false)
		{
			nonmembers.add(idempl);
			conv.setConversation_nonmembers(nonmembers);

		}

	}
	//conv.setConversation_members(members);
	conversationRepository.save(conv);
	return conv;
}

/////////////////////////////////////////ajouter membre//////////////////////////////////////////////////////

@Override
public Conversation ajoutermembre(Long idconv, Long idempl) {

	Conversation conv =conversationRepository.findById(idconv).get();
	
	List<Long> members=conv.getConversation_members();
	List<Long> nonmembers=conv.getConversation_nonmembers();

	//Long i=(long) members.indexOf(idempl);
	//members.remove(i);
	//conv.setConversation_members(members);
	if(members.contains(idempl)==false)
	{
		members.add(idempl);
		conv.setConversation_members(members);
		if(nonmembers.contains(idempl))
		{
			nonmembers.remove(idempl);
		}
	}
	conv.setConversation_members(members);
	conv.setConversation_nonmembers(nonmembers);
	conversationRepository.save(conv);
	return conv;
}

/////////////////////////////////////retrieve conversation///////////////////////////////////////////////////

@Override
public String retrieveConversation(Long idconv,Long idempl) {
	List<Message> messages= messageRepository.RetrieveConversation(idconv,true);
	int nbr=messages.size();
	Conversation c=conversationRepository.findById(idconv).get();
	String s = c.getConversation_name()+"  conversation :\r\n";
if(c.getConversation_members().contains(idempl))
{	
	for(int i=0;i<nbr;i++)
	{
		//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		//Date date = messages.get(i).getMessage_date();        
		//String dateToStr = dateFormat.format(date);
		Long id1=messages.get(i).getId_sender();
		Employee e =employeeRepository.findById(id1).get();
		String name=e.getFirstName()+" "+e.getLastName();
		s+=
				messages.get(i).getMessage_date().toString()+" :\r\n"
				//+messages.get(i).getId_sender().toString()+":   "
				+name+":   "
			    +messages.get(i).getContenu_message()+" \r\n"
	    		+"Likes :"+messages.get(i).getNbre_like_message().toString()+"     "
	    		+"Dislikes :"+messages.get(i).getNbre_unlike_message().toString()+" \r\n";
	}	
	return s;
}
else if(c.getConversation_nonmembers().contains(idempl)) 
	{
		return "vous avez quitté le groupe";
	}
return "vous n'appartenez pas à ce groupe";
}


@Override
public void deleteConversation(Long conversationId) {
	// TODO Auto-generated method stub
	
}

//////////////////////////////////retrieve Conversations par id////////////////////////////////////////

@Override
public List<Conversation> retrieveConversationsparid(Long id) {
	List<Conversation> conversations= (List<Conversation>) conversationRepository.findAll();
	List<Conversation> conversationsID = new ArrayList<>();
	int nbr=conversations.size();
	for(int i=0;i<nbr;i++)
	{	
		Conversation c=conversations.get(i);
		if((c.getConversation_members().contains(id)) && (c.isConversation_visibility()==true) ) 
		{
			conversationsID.add(c);
		}
	}
	return conversationsID;
}

////////////////////////////retrieve members////////////////////////////////////////////////

@Override
public String retrievemembers(Long idconv) {
	Conversation c=conversationRepository.findById(idconv).get();
	List<Long> members=c.getConversation_members();
	String s = c.getConversation_name()+" : \r\n";
	int nbr=members.size();
	for(int i=0;i<nbr;i++) {
		Long id=members.get(i);
		Employee e=employeeRepository.findById(id).orElse(null);
		s+=e.getFirstName()+" "+e.getLastName()+"\r\n";
	}
	return s;
}


//////////////////////////////////archiver conv//////////////////////////////////////

public Conversation archiverConversation(Long id) {
		Conversation c=conversationRepository.findById(id).get();
		c.setConversation_visibility(false);
		conversationRepository.save(c);
		return c;
}
//////////////////////////////////disarchiver conv///////////////////////////////////

public Conversation desarchiverConversation(Long id) {
		Conversation c=conversationRepository.findById(id).get();
		c.setConversation_visibility(true);
		conversationRepository.save(c);
		return c;
}

//////////////////////////////////retrieve all conversation//////////////////////////

@Override
public List<Conversation> retrieveAllconversations() {

return (List<Conversation>)conversationRepository.findAll();
}

/////////////////////////////////////////retrieve conversation//////////////////////////////////////////

@Override
public Conversation retrieveconversation(Long id) {
return conversationRepository.findById(id).orElse(null);
}
}
