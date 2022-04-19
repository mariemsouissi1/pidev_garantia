package tn.esprit.services;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;

import tn.esprit.entities.Agent;
import tn.esprit.entities.CustomerAccount;

public interface IMailService {

//	void Send(CustomerAccount customer) throws MailException;
//
//	void Sendd(String email, String body, String subject);

	void sendWithAttachment(CustomerAccount customer,Agent agent,String attachement) throws MailException, MessagingException;
}
