package com.khanhpg.springemailclient;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringEmailClientApplication {
	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(SpringEmailClientApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		//	emailSenderService.sendSimpleEmail("khanhphan900@gmail.com", 
												// "This is the Eemail Body...", 
												//	"This is the Email Subject");
		emailSenderService.sendEmailWithAttachment("khanhphan900@gmail.com",
				"This is the Eemail Body attachment...", 
				"This is the Email Subject attachment", 
				"D:\\img\\DOUGNUTS\\DOUGNUTS_1.jpg");
	}
}
