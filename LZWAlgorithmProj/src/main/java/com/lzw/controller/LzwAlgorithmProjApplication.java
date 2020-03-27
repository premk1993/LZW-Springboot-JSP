package com.lzw.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lzw")
public class LzwAlgorithmProjApplication {

	public static void main(String[] args) throws MessagingException, IOException {
		
		ConfigurableApplicationContext context=SpringApplication.run(LzwAlgorithmProjApplication.class, args);
        Mail mail=context.getBean(Mail.class);
      //  Encode enc=context.getBean(Encode.class);
        controller cont=context.getBean(controller.class);
        cont.contextMap(context, mail);
		
	//	mail.sendEmailWithAttachment("premkumar.vem@gmail.com", "C:\\Users\\premk\\Documents\\workspace-spring-tool-suite-4-4.1.1.RELEASE\\Exploration\\test.txt", "hfbdkhvgjdf");
	
	}

}
