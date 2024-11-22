package org.example.Common.util;

import javax.mail.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class MailConfig {

    public static String ReadEmail() {

        String user = "vunguyen.17082003@gmail.com";
        String password = "ztxakyuwuvytnpwo";

        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", "imap.gmail.com");
        properties.put("mail.imaps.port", "993");
        properties.put("mail.imaps.ssl.enable", "true");
        Session emailSession = Session.getInstance(properties);
        Store store = null;
        Folder emailFolder = null;
        String emailContent = "";
        String senderEmail = "thanhletraining03@gmail.com";


        try {
            store = emailSession.getStore("imaps");
            store.connect(user, password);

            emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();
            Arrays.sort(messages, (m1, m2) -> {
                try {
                    return m2.getReceivedDate().compareTo(m1.getReceivedDate()); // để lấy email mới nhất
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });

            for (Message message : messages) {
                String fromEmail = message.getFrom()[0].toString();
                if (fromEmail.contains(senderEmail)) {
                    emailContent = message.getContent().toString();
                    System.out.println("Email Content: " + emailContent);
                    break;
                }
            }
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to receive email: " + e.getMessage());
        } finally {
            try {
                if (emailFolder != null && emailFolder.isOpen()) {
                    emailFolder.close(false);
                }
                if (store != null) {
                    store.close();
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return emailContent;
    }
}