package com.example.emailre.network;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

public class NetworkController{
    private static Session session;
    public static Store store;
    public static String error;

public static Store OpenConnectionWithHostPOP3(String host, String port, final String username, final String password) {
    Properties props = new Properties();
    props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.pop3.socketFactory.fallback", "false");
    props.put("mail.pop3.socketFactory.port", port);
    props.put("mail.pop3.port", port);
    props.put("mail.pop3.host", host);
    props.put("mail.store.protocol", "pop3");
    Authenticator auth = new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    };
    session = Session.getDefaultInstance(props, auth);
    try{

        store = session.getStore();
        store.connect();

} catch (MessagingException me) {
        error = me.toString();
    }
    return store;
}

}
