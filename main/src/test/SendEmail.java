/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
/**
 *
 * @author sumit
 */
public class SendEmail {
    
    public static void main(String [] args)
   {    
       System.setProperty("http.proxyHost", "10.3.100.211");
       System.setProperty("http.proxyPort","8080");
      // Recipient's email ID needs to be mentioned.
      String to = "sumitgoswami075@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "sumitgoswami075@gmail.com";

      // Assuming you are sending email from localhost
      String host = "smtp.gmail.com:465";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      
      properties.setProperty("mail.smtp.host", host);
      properties.setProperty("mail.user", "xprudhvi123@gmail.com");
      properties.setProperty("mail.password", "xprudhvi");
      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);
      

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("test mail");

         // Now set the actual message
         message.setText("aa ja saale kab se bhej rha hu.");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
    
}
