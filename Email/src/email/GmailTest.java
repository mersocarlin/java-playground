/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

/**
 *
 * @author Hemerson
 */
import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailTest {

    private static final String SMTP_HOSTNAME = "smtp.gmail.com";
    private static final String SMTP_PORT = "465";
    private static final String emailMsgTxt = "Texto da mensagem";
    private static final String emailSubjectTxt = "Envio de e-mail via Java!";
    private static final String emailFromAddress = "mersocarlin@gmail.com";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static final String[] sendTo = {"merso@hotmail.com","mersocarlin@gmail.com"};
    private String login = "login";
    private String senha = "senha";
   
    public static void main(String args[]) throws Exception {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        new GmailTest().sendSSLMessage(sendTo, emailSubjectTxt, emailMsgTxt,emailFromAddress);
        System.out.println("Sucessfully Sent mail to All Users");
    }

    public void sendSSLMessage(String recipients[], String subject,
            String message, String from) throws MessagingException {

        boolean debug = true;
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOSTNAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(login, senha);
                    }
                });

        session.setDebug(debug);

        Message msg = new MimeMessage(session);
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);

        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        Transport.send(msg);
    }
}
