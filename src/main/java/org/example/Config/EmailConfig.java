package org.example.Config;

import lombok.Data;

import java.util.Properties;
@Data
public class EmailConfig {

    private Properties properties;
    private String email;
    private String password;
    private String recipient;

    public EmailConfig() {
        this.email = "vunguyen.17082003@gmail.com";
        this.password = "ztxakyuwuvytnpwo";
        this.recipient = "thanhletraining03@gmail.com";
        this.properties = new Properties();
        this.properties.put("mail.store.protocol", "imaps");
        this.properties.put("mail.imaps.host", "imap.gmail.com");
        this.properties.put("mail.imaps.port", "993");
        this.properties.put("mail.imaps.ssl.enable", "true");
    }
}