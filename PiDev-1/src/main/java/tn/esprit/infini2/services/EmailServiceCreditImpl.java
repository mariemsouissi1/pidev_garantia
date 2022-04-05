package tn.pidev.services;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import tn.pidev.entities.Credit;
import tn.pidev.entities.TransactionCredit;


@Service
public class EmailServiceCreditImpl implements IEmailServiceCredit{
	
		@Autowired
	    private JavaMailSender javaMailSender;
		
		
		public void sendEmailRejet(String email, Credit c){
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("garantiaentreprise@gmail.com");
			msg.setTo(email);
			msg.setSubject("Credit Denial");
	        msg.setText("Dear " + c.getCustomerCredit().getFirstNameCustomer()+ " " + c.getCustomerCredit().getLastNameCustomer() + ",\r\n" 
	        		+ "\r\n"
	        		+ "Thank you for your recent credit application with our company, we really appreciate yourefforts as well as interest in doing business with our company.\r\n"
	        		+ "\r\n"
	        		+ "Regretfully, this letter is being sent to notify you that your application has been declined due to the fact that your credit accruals are too high."
	        		+ "\r\n"
	        		+ "Nonetheless, you have the right to conform to the scope as well nature of the information stated in this letter. As long as it is not less than 15 days from the date youreceive this notification. Meanwhile, we can offer our services as well as professionalismin credit resolving to assist you in improving your creditworthiness value. \r\n"
	        		+ "\r\n"
	        		+ "Sincerely, Garantia MICRO-INSURANCE Group.");
	        javaMailSender.send(msg);
		}
		
		
		public void sendEmailAcceptance(String email, Credit c){
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("garantiaentreprise@gmail.com");
			msg.setTo(email);
			msg.setSubject("Acceptance Credit");
	        msg.setText("Dear " + c.getCustomerCredit().getFirstNameCustomer()+ " " + c.getCustomerCredit().getLastNameCustomer() + ",\r\n" 
	        		+ "\r\n"
	        		+ "I would like to inform you that your application for the credit has been approved. The details of your account are as follows :\r\n"
	        		+ "\r\n"
	        		+ "Credit Id : "+ c.getIdCredit() + "\r\n"
	        		+ "\r\n"
	        		+ "Amount Credit : " + c.getAmountCredit() + "\r\n"
	        		+ "\r\n"
	        		+ "Start Credit : " + c.getStartDate() + "\r\n"
	        		+ "\r\n"
	        		+ "Please do not disclose your credit details to strangers.\r\n"
	        		+ "\r\n"
	        		+ "The payments for the credit are to be made on "+ c.getLastDueDate() + ". For any queries contact us or you can visit our closest micro-insurance branch. Thank you for choosing our services.\r\n"
	        		+ "\r\n"
	        		+ "Sincerely, Garantia MICRO-INSURANCE Group.");
	        javaMailSender.send(msg);
		}
		


		@Override
		public void sendEmailUnpaidCredit(String email, Credit c) {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("garantiaentreprise@gmail.com");
			msg.setTo(email);
			msg.setSubject("Suppression is impossible !");
	        msg.setText("Dear " + c.getCustomerCredit().getFirstNameCustomer()+ " " + c.getCustomerCredit().getLastNameCustomer() + ",\r\n" 
	        		+ "\r\n"
	        		+ "Your request for credit suppression has been denied as it's unpaid credit. \r\n"
	        		+ "\r\n"
	        		+ "Sincerely, Garantia MICRO-INSURANCE Group.");
	        javaMailSender.send(msg);			
		}
		
		@Override
		public void sendEmailwithAttachment(){
		try {
			MimeMessage msg = javaMailSender.createMimeMessage();
			
			MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true);
			
			messageHelper.setFrom("");
			messageHelper.setTo("");
			messageHelper.setSubject("Test Subject");
			messageHelper.setText("Test Body");
			
			File file = new File("Path To File");
			
			messageHelper.addAttachment(file.getName(), file);
			
			javaMailSender.send(msg);		
			} 
			catch (Exception e) {
		}
	}


		@Override
		public void sendEmailUnverifiedTransaction(String email, TransactionCredit t) {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("garantiaentreprise@gmail.com");
			msg.setTo(email);
			msg.setSubject("Suppression is impossible !");
	        msg.setText("Dear " + t.getCreditTransaction().getCustomerCredit().getFirstNameCustomer() + " " + t.getCreditTransaction().getCustomerCredit().getLastNameCustomer() + ",\r\n" 
	        		+ "\r\n"
	        		+ "Your request for transaction suppression has been denied as it's unverfied transaction. \r\n"
	        		+ "\r\n"
	        		+ "Sincerely, Garantia MICRO-INSURANCE Group.");
	        javaMailSender.send(msg);				
		}
		
		
		@Override
		public void sendEmailRequest(String email, Credit c) {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("garantiaentreprise@gmail.com");
			msg.setTo(email);
			msg.setSubject("Request Received");
	        msg.setText("Dear " + c.getCustomerCredit().getFirstNameCustomer()+ " " + c.getCustomerCredit().getLastNameCustomer() + ",\r\n" 
	        		+ "\r\n"
	        		+ "Your request for credit has been successfully received and will be processed as soon as possible.  \r\n"
	        		+ "\r\n"
	        		+ "Sincerely, Garantia MICRO-INSURANCE Group.");
	        javaMailSender.send(msg);		
		}


		@Override
		public int generateCode() {
	        int rand_int;
			return rand_int = ThreadLocalRandom.current().nextInt();
		      
		}


}



