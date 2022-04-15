package tn.esprit.infini2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.esprit.infini2.services.EmailService;

@EnableScheduling
@SpringBootApplication
public class PiDev1Application {

	@Autowired
	private EmailService senderService;
	public static void main(String[] args) {
		SpringApplication.run(PiDev1Application.class, args);
	}























	@EventListener(ApplicationReadyEvent.class)
	public void sendMail(){
		senderService.sendSimpleMessage("mohamed.hachicha@esprit.tn","Confirmation email","Congrats! You're now an official customer of Garantia!");
	}

}
