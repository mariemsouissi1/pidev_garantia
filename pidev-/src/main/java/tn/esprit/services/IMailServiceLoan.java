package tn.esprit.services;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;

import tn.esprit.entities.Agent;
import tn.esprit.entities.CustomerAccount;


public interface IMailServiceLoan {

	public void sendEmailConfirmation(CustomerAccount customer,long idSimulation) throws MailException, MessagingException;
	public void sendEmailWithAttachment(CustomerAccount customer,Agent agent, String attch,long idLoan) throws MessagingException, MailException;
	public void sendEmailUnConfirmation(CustomerAccount customer,long idSimulation) throws MailException, MessagingException;
	public void sendEmailNotifAgent(Agent agent,int nbrSimulation) throws MailException, MessagingException;
}
