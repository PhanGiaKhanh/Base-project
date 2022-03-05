package com.khanhpg.springemailclient;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: KhanhPG
 * @since: 05/03/2022
 */
@Slf4j
@Service
public class EmailSenderService {
	private static final String EMAIL_SEND = "khanhpgcode@gmail.com";
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	public void sendSimpleEmail(String toEmail, String body, String subject) {
		 SimpleMailMessage msg = new SimpleMailMessage();
		 msg.setFrom(EMAIL_SEND);
		 msg.setTo(toEmail);
		 msg.setText(body);
		 msg.setSubject(subject);
		 
		 // send email
		 mailSender.send(msg);
		 log.info("Mail send...");
	}
	
	public void sendEmailWithAttachment(String toEmail, String body, String subject, String attachment) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setFrom(EMAIL_SEND);
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);
		
		// Add attachment in email
		FileSystemResource fileSystem = new FileSystemResource(new File(attachment));
		mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);
		// send email
		mailSender.send(mimeMessage);
		 log.info("Mail send attachment...");
	}
}
