package com.avy.mail.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class MailDetails {

	private String sender;

	private String subject;

}
