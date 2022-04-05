package tn.pidev.services;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;

import tn.pidev.entities.Agent;
import tn.pidev.entities.CustomerAccount;

public interface IMailService {

//	void Send(CustomerAccount customer) throws MailException;
//
//	void Sendd(String email, String body, String subject);

	void sendWithAttachment(CustomerAccount customer,Agent agent,String attachement) throws MailException, MessagingException;
}
