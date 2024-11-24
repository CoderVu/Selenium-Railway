package org.example.CM.util;

import java.util.Random;

public class Generate {
    public static String generateRandomPassword(Random random, int minLength, int maxLength) {
        int passwordLength = minLength + random.nextInt(maxLength - minLength + 1);
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < passwordLength; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }
    public static String genaratePassword(){
        return "12345678";
    }
    public static  String generateEmail(Random random) {
        int randomNumber = 100 + random.nextInt(900);
        return "vunguyen.17082003+" + randomNumber + "@gmail.com";
    }
    public static  String generateRandomPID(Random random, int minLength, int maxLength) {
        int pidLength = minLength + random.nextInt(maxLength - minLength + 1);
        StringBuilder pid = new StringBuilder();
        for (int i = 0; i < pidLength; i++) {
            pid.append(random.nextInt(10));
        }
        return pid.toString();
    }

    public static String generatePasswordEmpty() {
        return "";
    }

    public static String generatePIPEmpty() {
        return "";
    }
}
