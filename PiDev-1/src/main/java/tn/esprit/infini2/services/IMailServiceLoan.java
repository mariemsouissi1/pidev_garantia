package tn.esprit.infini2.services;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;

import tn.esprit.infini2.entities.Agent;
import tn.esprit.infini2.entities.CustomerAccount;




public interface IMailServiceLoan {

	public void sendEmailConfirmation(CustomerAccount customer,long idSimulation) throws MailException, MessagingException;
	public void sendEmailWithAttachment(CustomerAccount customer,Agent agent, String attch,long idLoan) throws MessagingException, MailException;
	public void sendEmailUnConfirmation(CustomerAccount customer,long idSimulation) throws MailException, MessagingException;
	public void sendEmailNotifAgent(Agent agent,int nbrSimulation) throws MailException, MessagingException;
}
