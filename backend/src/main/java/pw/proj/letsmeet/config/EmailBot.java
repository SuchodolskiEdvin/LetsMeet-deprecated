package pw.proj.letsmeet.config;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailBot {
    private final Properties prop = new Properties();
    private final String adminEmail = "letsmeetmanagement@gmail.com";
    private final String adminEmailPassword = "Letsmeet!7";

    private final String recipient;

    public EmailBot(String recipient) {


        this.recipient = recipient;
    }

     public void sendRegistrationMail() {
         prop.put("mail.smtp.auth",true);
         prop.put("mail.smtp.starttls.enable","true");
         prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
         prop.put("mail.smtp.host","smtp.gmail.com");
         prop.put("mail.smtp.port","587");
         prop.put("mail.smtp.ssl.trust","smtp.gmail.com");

         Session session = Session.getInstance(prop, new Authenticator() {
             @Override
             protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication(adminEmail, adminEmailPassword);
             }
         });

         try {
             Message message = prepareMessage(session);

             Transport.send(message);
         } catch (MessagingException e) {
             e.printStackTrace();
         }
     }

     public Message prepareMessage(Session session) {

         Message message = new MimeMessage(session);

         try {
             message.setFrom(new InternetAddress(adminEmail));
             message.setRecipients(
                     Message.RecipientType.TO, InternetAddress.parse(recipient));
             message.setSubject("LetsMeet | Successful registration");

             String msg = "You have been successfuly registered in our service. Welcome!";

             MimeBodyPart mimeBodyPart = new MimeBodyPart();
             mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

             Multipart multipart = new MimeMultipart();
             multipart.addBodyPart(mimeBodyPart);

             message.setContent(multipart);

         } catch (Exception e) {
             System.err.println("Can't send email!");
         }

         return message;
     }



}
