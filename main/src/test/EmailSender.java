/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author sumit
 */
public class EmailSender {
    
    public static boolean sendMail (String from, String password, String message, String to[])
    {
        String host = "smtp.gmail.com";
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smt.socks.host", "127.0.0.1");
        props.put("mail.smt.socks.port", "9151");
        
        
        Session session = Session.getDefaultInstance(props,null);
        MimeMessage mimemessage = new MimeMessage(session);
        
        try
        {
            mimemessage.setFrom(new InternetAddress(from));
            InternetAddress[] toaddress = new InternetAddress[to.length];
            
            for(int i=0;i<to.length;i++)
            {
                toaddress[i] = new InternetAddress(to[i]);
            }
             
            mimemessage.addRecipients(Message.RecipientType.TO, toaddress);
            
            mimemessage.setSubject("test mail");
            mimemessage.setText(message);
            
            Transport transport = session.getTransport("smtp");
            transport.connect(host, 8080, host, password);
            transport.sendMessage(mimemessage, mimemessage.getAllRecipients());
            transport.close();
            
            return true;
            
        }catch(MessagingException me){
            me.printStackTrace();
        }
        
        return false;
    }
    
}
