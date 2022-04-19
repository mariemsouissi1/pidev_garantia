package tn.esprit.infini2.services;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;

import tn.esprit.infini2.entities.Agent;
import tn.esprit.infini2.entities.CustomerAccount;

public interface IMailService {

//	void Send(CustomerAccount customer) throws MailException;
//
//	void Sendd(String email, String body, String subject);

	void sendWithAttachment(CustomerAccount customer,Agent agent,String attachement) throws MailException, MessagingException;
}
