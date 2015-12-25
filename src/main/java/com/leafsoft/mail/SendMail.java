package com.leafsoft.mail;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.*;
import javax.mail.internet.*;

import com.leafsoft.util.AppResources;

public class SendMail 
{ 
	private static Logger LOGGER = Logger.getLogger(SendMail.class.getName());
    public static void send(String to, String sub,  String msg)
    { 
	   try
	   {
		   final AppResources appResources = AppResources.getInstance();
	 	/* Create an instance of MimeMessage, 
	 	      it accept MIME types and headers 
	 	   */
		   Session session = getSendMailSession(appResources);
	    MimeMessage message = new MimeMessage(session);
	       message.setFrom(new InternetAddress(appResources.getSmtpUser()));
	       message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
	       message.setSubject(sub);
	       message.setContent(msg,"text/html");
	
	       /* Transport class is used to deliver the message to the recipients */
	       
	       Transport.send(message);
	       
	       LOGGER.log(Level.INFO,"sendMail:::::"+to);
	 
	    }
	    catch(Exception e)
	    {
	    	 LOGGER.log(Level.SEVERE,"sendMail:::::Exception::::{0}"+to,e);
	    }
  } 
    
    public static Session getSendMailSession(final AppResources appResources) {
    	 //create an instance of Properties Class   
        Properties props = new Properties();
        
        /* Specifies the IP address of your default mail server
        	   for e.g if you are using gmail server as an email sever
              you will pass smtp.gmail.com as value of mail.smtp host. 
              As shown here in the code. 
              Change accordingly, if your email id is not a gmail id
           */
        props.put("mail.smtp.host", appResources.getSmtpHost());
        //below mentioned mail.smtp.port is optional
        props.put("mail.smtp.port", appResources.getSmtpPort());		
        props.put("mail.smtp.starttls.enable", appResources.getSmtpEnable());
        props.put("mail.smtp.auth", appResources.getSmtpAuth());
        /* Pass Properties object(props) and Authenticator object   
              for authentication to Session instance 
           */

       Session session = Session.getInstance(props,new javax.mail.Authenticator()
       {
     	  protected PasswordAuthentication getPasswordAuthentication() 
     	  {
     	 	 return new PasswordAuthentication(appResources.getSmtpUser(),appResources.getSmtpPassword());
     	  }
      });
       return session;
    }
}