package org.example.Common.utils;

import org.example.Config.EmailConfig;

import javax.mail.*;
import java.io.IOException;
import java.util.Arrays;

public class MailReader {

        public static String readEmail(EmailConfig emailConfig) {
            Session emailSession = Session.getInstance(emailConfig.getProperties());
            Store store = null;
            Folder emailFolder = null;
            String emailContent = "";
            try {
                store = emailSession.getStore("imaps");
                store.connect(emailConfig.getEmail(), emailConfig.getPassword());

                emailFolder = store.getFolder("INBOX");
                emailFolder.open(Folder.READ_ONLY);

                Message[] messages = emailFolder.getMessages();
                Arrays.sort(messages, (m1, m2) -> {
                    try {
                        return m2.getReceivedDate().compareTo(m1.getReceivedDate()); // sắp xếp theo ngày nhận email mới nhất lên đầu
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                });

                for (Message message : messages) {
                    String fromEmail = message.getFrom()[0].toString();
                    if (fromEmail.contains(emailConfig.getRecipient())) {
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
