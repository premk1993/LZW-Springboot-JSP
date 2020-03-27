/**
 * 
 */
package com.lzw.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZonedDateTime;

import javax.mail.MessagingException;
import javax.mail.internet.ContentType;
import javax.servlet.http.HttpServletResponse;

import com.lzw.service.*;

/**
 * @author premk
 *
 */
@Component
@Controller
public class controller {
	
	ConfigurableApplicationContext apCcontext;
	Mail mail;
    @Autowired
	private Encode enc;
  //  private Decoder dcd;



    public void contextMap(ConfigurableApplicationContext context, Mail m) {
	
	apCcontext=context;
	mail=m;
	}

	@RequestMapping ("/")
	public String loginPage() {
		
		return "login";
	}
	
	@RequestMapping ("/login")

	public ModelAndView loginSucessful(@RequestParam ( "email") String email,@RequestParam ( "name") String name) {
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("email", email);
		mv.addObject("name", name);
		mv.addObject("hasBeenDecodedFlag","false");
		mv.addObject("hasBeenEncodedFlag","false");
		mv.setViewName("algorithm");
		return mv;
	}
	
	@RequestMapping ("/encode")

	public ModelAndView encode( @RequestParam ( "encodetext") String encode,@RequestParam ( "email") String email,@RequestParam ( "name") String name) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("email", email);
		mv.addObject("name", name);
		mv.addObject("encodedstring",enc.encoder(encode).toString());
		mv.addObject("encode",encode);
		mv.addObject("hasBeenDecodedFlag","false");
		mv.addObject("hasBeenEncodedFlag","true");
		mv.setViewName("algorithm");
		return mv;
	}
	
	@RequestMapping ("/encodetofile")

	public void encodetofile(HttpServletResponse response,@RequestParam ( "encodetext") String encode) {

		String filename=enc.generateEncodedOutputFile(enc.encoder(encode));
		Path path=Paths.get(filename);
		Files.exists(path);
		response.setContentType("application/txt");
        response.addHeader("Content-Disposition", "attachment; filename="+filename.substring(filename.lastIndexOf("/")+1));
        try {
			Files.copy(path, response.getOutputStream());
			response.getOutputStream().flush();
		} catch (IOException e) {
			System.out.println("Error occured while copying the file to output streeam of HTTP servelet in encodetofile().");
			e.printStackTrace();
		}
	}
	
	@RequestMapping ("/encodetoemail")

	public ModelAndView encodetoemail(@RequestParam ( "encodetext") String encode,@RequestParam ( "email") String email,@RequestParam ( "name") String name) throws MessagingException, IOException {
		
		String encodedFilePath=enc.generateEncodedOutputFile(enc.encoder(encode));
		mail.sendEmailWithAttachment(email, encodedFilePath, encode,name,true);
		ModelAndView mv = new ModelAndView();
		mv.addObject("encode",encode);
		mv.addObject("hasBeenDecodedFlag","false");
		mv.addObject("hasBeenEncodedFlag","false");
		mv.setViewName("algorithm");
		
		return mv;	
	}
	
	@RequestMapping ("/decode")

	public ModelAndView decode(@RequestParam ("decodetext") String decode, @RequestParam ( "email") String email,@RequestParam ( "name") String name) throws IOException {
		
		ModelAndView mv = new ModelAndView();
		Decoder dcd = new Decoder();
		mv.addObject("email", email);
		mv.addObject("name", name);
		mv.addObject("decodedstring",dcd.decoder(decode));
		mv.addObject("decode",decode);
		mv.addObject("hasBeenDecodedFlag","true");
		mv.addObject("hasBeenEncodedFlag","false");
		mv.setViewName("algorithm");
		return mv;
	}
	
	@RequestMapping ("/decodetofile")

	public void decodetofile(HttpServletResponse response,@RequestParam ( "decodetext") String decode) throws IOException {

		Decoder dcd = new Decoder();
	    String filePath=dcd.generateDecodedFile(dcd.decoder(decode));
		Path path=Paths.get(filePath);
		Files.exists(path);
		response.setContentType("application/txt");
        response.addHeader("Content-Disposition", "attachment; filename="+filePath.substring(filePath.lastIndexOf("/")+1));
        try {
			Files.copy(path, response.getOutputStream());
			response.getOutputStream().flush();
		} catch (IOException e) {
			System.out.println("Error occured while copying the file to output streeam of HTTP servelet in decodetofile().");
			e.printStackTrace();
		}
	}
	
	@RequestMapping ("/decodetoemail")

	public ModelAndView decodetoemail(@RequestParam ( "decodetext") String decode,@RequestParam ( "email") String email,@RequestParam ( "name") String name) throws MessagingException, IOException {
		Decoder dcd = new Decoder();
	    String decodedFilePath=dcd.generateDecodedFile(dcd.decoder(decode));
		mail.sendEmailWithAttachment(email, decodedFilePath, decode,name,false);
		ModelAndView mv = new ModelAndView();
		mv.addObject("decode",decode);
		mv.addObject("hasBeenDecodedFlag","false");
		mv.addObject("hasBeenEncodedFlag","false");
		mv.setViewName("algorithm");
		return mv;	
	}
	
}
