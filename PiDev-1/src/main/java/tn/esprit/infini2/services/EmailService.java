package tn.esprit.infini2.services;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements Iemailservice {
	
	@Autowired
	JavaMailSender javaMailSender;

	public String sendEmail(String to,String subject,String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("Garantiaentreprise@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		
		javaMailSender.send(message);
		
		return "Mail sent successfully";
	}
	
	public String sendEmailwithAttachment() {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			
			MimeMessageHelper messageHelper = 
					new MimeMessageHelper(message, true);
			
			messageHelper.setFrom("");
			messageHelper.setTo("");
			messageHelper.setSubject("Test Subject");
			messageHelper.setText("Test Body");
			
			File file = new File("Path To File");
			
			messageHelper.addAttachment(file.getName(), file);
			
			javaMailSender.send(message);
			
			return "Mail sent successfully";
			
		} catch (Exception e) {
			return "Mail sent failed";
		}
	}
}
