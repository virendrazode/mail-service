package com.avy.mail.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.avy.mail.dto.MailDetails;

@Component
public class MailReadService {

	Logger logger = LoggerFactory.getLogger(MailReadService.class);
	
	@Value("${gmail.host}")
    private String host;

    @Value("${gmail.user}")
    private String user;

    @Value("${gmail.password}")
    private String password;
	
	public List<MailDetails> readMails() throws MessagingException, IOException, ParseException {

		Properties properties = new Properties();
		properties.put("mail.imap.host", "IMAP.gmail.com");
		properties.put("mail.imap.port", "995");
		properties.put("mail.imap.starttls.enable", "true");

		Session emailSession = Session.getDefaultInstance(properties);
		Store store = emailSession.getStore("imaps");
		store.connect(host, user, password);

		Folder emailFolder = store.getFolder("INBOX");
		

		emailFolder.open(Folder.READ_WRITE);
		Message[] messages = emailFolder.getMessages();

		

		List<MailDetails> listOfMails = new ArrayList<>();

		long totalCoutOfMail = messages.length;
		
		logger.info("total Cout Of Mail: "+totalCoutOfMail);
		long iterations = 0;
		if (totalCoutOfMail>200) {
			iterations = 200;
		}else if (totalCoutOfMail<=200) {
			iterations = totalCoutOfMail;
		}
		logger.info("iterations: "+iterations);
		for (int i = 0; i < iterations; i++) {
			Message message = messages[i];
			MailDetails mailDetails = new MailDetails();
			mailDetails.setSender(message.getFrom()[0].toString());
			mailDetails.setSubject(message.getSubject());
			listOfMails.add(mailDetails);
			logger.info(i+"\n");
			logger.info("Sender: "+message.getFrom()[0].toString()+"\n");
			logger.info("Subject: "+message.getSubject()+"\n");
		}
		
		for (Message msg : messages) {

			

		}

		emailFolder.close(true);
		store.close();

		return listOfMails;
	}

}