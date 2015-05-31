/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gmail;

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

//    private static final String SMTP_HOSTNAME = "smtp.gmail.com";
//    private static final String SMTP_PORT = "465";
//    private static final String emailMsgTxt = "Texto da mensagem: você é um viadinho!";
//    private static final String emailSubjectTxt = "Envio de e-mail via Java!";
//    private static final String emailFromAddress = "mersocarlin@gmail.com";
//    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
//    private static final String[] sendTo = {"jefferson_rsrio@hotmail.com","sdepnqscn1@yahoo.com.br","merso@hotmail.com"};
    private String login;
    private String senha;
    private static String SMTP_HOSTNAME;
    private static String SMTP_PORT;
    private static String emailMsgTxt;
    private static String emailSubjectTxt;
    private static String emailFromAddress;
    private static String SSL_FACTORY;
    private static String[] sendTo;

    public GmailTest() {
        this.SMTP_HOSTNAME = "smtp.gmail.com";
        this.SMTP_PORT = "465";
        this.emailMsgTxt = "";
        this.emailSubjectTxt = "";
        this.emailFromAddress = "";
        this.SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        this.sendTo = new String[]{""};
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static String getEmailFromAddress() {
        return emailFromAddress;
    }

    public static void setEmailFromAddress(String emailFAddress) {
        emailFromAddress = emailFAddress;
    }

    public static String getEmailMsgTxt() {
        return emailMsgTxt;
    }

    public static void setEmailMsgTxt(String emailMTxt) {
        emailMsgTxt = emailMTxt;
    }

    public static String getEmailSubjectTxt() {
        return emailSubjectTxt;
    }

    public static void setEmailSubjectTxt(String emailSubTxt) {
        emailSubjectTxt = emailSubTxt;
    }

    public static String[] getSendTo() {
        return sendTo;
    }

    public static void setSendTo(String[] sTo) {
        sendTo = sTo;
    }

    public static void main(String args[]) throws Exception {
        GmailTest email = new GmailTest();
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        //new GmailTest().sendSSLMessage(sendTo, emailSubjectTxt, emailMsgTxt,emailFromAddress);
        email.sendSSLMessage(sendTo, emailSubjectTxt, emailMsgTxt, emailFromAddress);
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
