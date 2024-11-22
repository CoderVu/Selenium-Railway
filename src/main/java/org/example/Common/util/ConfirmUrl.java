package org.example.Common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfirmUrl {

    public static String ConfirmationUrlRegister(String emailContent) {

        String urlPattern = "http://www\\.saferailway\\.somee\\.com/Account/Confirm\\?confirmationCode=[a-zA-Z0-9%/=]+";
        Pattern pattern = Pattern.compile(urlPattern);
        Matcher matcher = pattern.matcher(emailContent);

        if (matcher.find()) {
            return matcher.group();
        }
        throw new RuntimeException("Confirmation URL not found in the email content");
    }
    public static String ConfirmationUrlResetPassword(String emailContent) {

        String urlPattern = "http://www\\.saferailway\\.somee\\.com/Account/PasswordReset\\?resetToken=[a-zA-Z0-9%\\-=_&]+";
        Pattern pattern = Pattern.compile(urlPattern);
        Matcher matcher = pattern.matcher(emailContent);

        if (matcher.find()) {
            return matcher.group();
        }
        throw new RuntimeException("Confirmation URL not found in the email content");
    }
}
