package com.lzw.controller;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
@Component
public class Mail {

	
	 @Autowired
	    private JavaMailSender javaMailSender;
	 
	 public void sendEmailWithAttachment(String recipentEmailId,String fileName,String mailBody,String name,Boolean encode) throws MessagingException, IOException {

	        MimeMessage msg = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
	        helper.setTo(recipentEmailId);
	        if(encode) {
	        helper.setSubject("LZW Encoded file in attachment");
	        helper.setText("<h2>Hello "+name +",</h2><br/><h3>Check attachment for Encoded file of below text!</h3><br/><p>"+mailBody+"</p>", true);
	        }
	        else {
	        helper.setSubject("LZW Decoded file in attachment");
	 	    helper.setText("<h2>Hello "+name +",</h2><br/><h3>Check attachment for Decoded file of below text!</h3><br/><p>"+mailBody+"</p>", true);
	        }
	        File file=new File(fileName);
	        helper.addAttachment(fileName.substring(fileName.lastIndexOf("/")+1), file);
	        javaMailSender.send(msg);
	        System.out.print("Mail Sent Successfully to "+recipentEmailId);
	    }
	
	
}
