package com.avy.mail.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avy.mail.dto.MailDetails;
import com.avy.mail.service.MailReadService;

@RestController
@RequestMapping("gmail-read")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MailReadController {

	@Autowired
	MailReadService mailReadService;

	@GetMapping("get")
	public ResponseEntity<?> getMailDetails() throws IOException, GeneralSecurityException, MessagingException, ParseException {

		List<MailDetails> readMails = mailReadService.readMails();
		return new ResponseEntity<>(readMails, HttpStatus.OK);

	}
}
