package org.example.cf;

import lombok.Data;

import java.util.Properties;
@Data
public class EmailConfig {

    private String user;
    private String password;
    private String senderEmail;
    private Properties properties;

    public EmailConfig(String user, String password, String senderEmail) {
        this.user = user;
        this.password = password;
        this.senderEmail = senderEmail;
        this.properties = new Properties();
        this.properties.put("mail.store.protocol", "imaps");
        this.properties.put("mail.imaps.host", "imap.gmail.com");
        this.properties.put("mail.imaps.port", "993");
        this.properties.put("mail.imaps.ssl.enable", "true");
    }
}
