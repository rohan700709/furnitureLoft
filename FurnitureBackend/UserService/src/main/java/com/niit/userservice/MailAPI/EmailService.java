package com.niit.userservice.MailAPI;
import com.niit.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.SimpleMailMessage;

import java.util.Properties;

public class EmailService {

    private JavaMailSender mailSender;
    @Autowired
    public EmailService() {
        this.mailSender = createMailSender();
    }

    private JavaMailSender createMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("furnitureloft2023@gmail.com");
        mailSender.setPassword("wwwpboekiwiqrywu");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        System.out.println(mailSender);
        return mailSender;
    }

    public void sendEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getUserEmail());
        message.setSubject("Welcome to the Furniture LOFT");
        message.setText("Dear "+ user.getUserName()
                + ",\n\nThank you for registering with furnitureLOFT with email. "
                +user.getUserEmail()+" and password "+user.getUserPassword()+
                " We extend our gratitude for choosing our services. ThankYOU!!");
        System.out.println(message);
        System.out.println("Before calling of mail sender instance inside sendEmail() method");
        mailSender.send(message);
    }
}
//
//import com.niit.userservice.model.User;
//import java.util.Properties;
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class EmailService {
//
//    private String host = "smtp.gmail.com"; // SMTP server
//    private int port = 587; // default SMTP port
//    private String senderEmail = "02prashant1998@gmail.com";
//    private String password = "12!!tyagisha!!34";
//
//    public void sendEmail(User user)  {
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", port);
//
//        try {
//
//            Authenticator auth = new Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(senderEmail, password);
//                }
//            };
//
//            System.out.println("After authenticator "+auth);
//
//            Session session = Session.getInstance(properties, auth);
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(senderEmail));
//            message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(user.getUserEmail()));
//            message.setSubject("Welcome to the Furniture LOFT");
//            message.setText("Dear "+ user.getUserName()
//                    + ",\n\nThank you for registering with furnitureLOFT with email "
//                            +user.getUserEmail()+" and password "+user.getUserPassword()+
//                    " We extend our gratitude for chosing our services. ThankYOU!!");
//            Transport.send(message);
//            System.out.println(message);
//        }
//        catch (MessagingException e)
//        {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//        catch(Exception e)
//        {
//            System.out.println(e.getMessage());
//        }
//    }
//}
//import com.niit.userservice.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//
//import java.util.Properties;
//import javax.mail.*;
//import javax.mail.internet.*;
//
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//
//    public void sendEmail(User user){
//
//        final String email = "furnitureloft2023@gmail.com";
//        final String password = "wwwpboekiwiqrywu";
//
////        Properties props = new Properties();
////        props.put("mail.smtp.auth", "true");
////        props.put("mail.smtp.starttls.enable", "true");
////        props.put("mail.smtp.host", "smtp.gmail.com");
////        props.put("mail.smtp.port", "587");
//
////        Session session = Session.getInstance(props,
////                new javax.mail.Authenticator() {
////                    protected PasswordAuthentication getPasswordAuthentication() {
////                        return new PasswordAuthentication(email, password);
////                    }
////                });
//
//        try {
//
////            MimeMessage message = new MimeMessage(session);
//            SimpleMailMessage message=new SimpleMailMessage();
////            message.setFrom(new InternetAddress(email));
//            message.setFrom(email);
////            message.setRecipients(Message.RecipientType.TO,
////                    InternetAddress.parse(user.getUserEmail()));
//            message.setTo(user.getUserEmail());
//            message.setSubject("Welcome to the Furniture LOFT");
//            message.setText("Dear "+ user.getUserName()
//                    + ",\n\nThank you for registering with furnitureLOFT with email "
//                            +user.getUserEmail()+" and password "+user.getUserPassword()+
//                    " We extend our gratitude for choosing our services. ThankYOU!!");
////            message.setText("Dear Mail API User,"
////                    + "\n\n This is a test mail from Java Mail API!");
//              javaMailSender.send(message);
////            Transport.send(message);
//
//            System.out.println("Mail sent successfully!");
//
//        }
//        catch (Exception e)
//          {
//            System.out.println(e.getMessage());
//          }
//    }
//

