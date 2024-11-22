package org.example.Common.util;

import org.testng.annotations.DataProvider;

import java.time.LocalDate;
import java.util.Random;

import static org.example.Common.util.Generate.generateRandomPassword;

public class DataTest {
    @DataProvider(name = "registerData")
    public static Object[][] provideRegisterData() {
        Random random = new Random();
        String email = Generate.generateEmail(random);
        String password = Generate.genaratePassword();
        String confirmPassword = password;
        String pid = Generate.generateRandomPID(random, 8, 20);
        return new Object[][]{
                {email, password, confirmPassword, pid},
        };
    }
    @DataProvider(name = "registerDataInvalid")
    public static Object[][] provideRegisterDataInvalid() {
        Random random = new Random();
        String email = Generate.generateEmail(random);
        String password = Generate.generateRandomPassword(random, 8, 64);
        String confirmPassword = Generate.generateRandomPassword(random, 8, 64);
        String pid = Generate.generateRandomPID(random, 8, 20);
        return new Object[][]{
                {email, password, confirmPassword, pid},
        };
    }
    @DataProvider(name = "registerDataEmpty")
    public static Object[][] provideRegisterDataEmpty() {
        Random random = new Random();
        String email = Generate.generateEmail(random);
        String password = Generate.generatePasswordEmpty();
        String confirmPassword = Generate.generatePasswordEmpty();
        String pid = Generate.generatePIPEmpty();
        return new Object[][]{
                {email, password, confirmPassword, pid},
        };
    }

    @DataProvider(name = "loginDataActive")
    public static Object[][] provideLoginData() {
        return new Object[][]{
                {"vunguyen.170803@grr.la", "12345678"},
        };
    }
    @DataProvider(name = "loginDataBlock")
    public static Object[][] provideLoginDataBlock() {
        return new Object[][]{
                {"vunguyen.17082003@gmail.com", "12345678"},
        };
    }
    @DataProvider(name = "loginDataInvalid")
    public static Object[][] provideLoginDataInvalid() {
        Random random = new Random();
        String randomPassword = generateRandomPassword(random, 8, 64);
        return new Object[][]{
                {"vunguyen.17082003@gmail.com", randomPassword},
        };
    }
    @DataProvider(name = "loginDataEmpty")
    public static Object[][] provideLoginDataEmpty() {
        return new Object[][]{
                {"", "12345678"},
        };
    }

    @DataProvider(name = "ticketData")
    public static Object[][] provideTicketData() {
        return new Object[][]{
                {LocalDate.now().plusDays(3), "Huế", "Đà Nẵng", "Soft bed with air conditioner", 1},
        };
    }
    @DataProvider(name = "ticketDataCancel")
    public static Object[][] provideTicketDataCancel() {
        return new Object[][]{
                {LocalDate.now().plusDays(3),"Sài Gòn", "Phan Thiết", "Hard seat", 2},
        };
    }

    @DataProvider(name = "tripData")
    public static Object[][] provideTripData() {
        return new Object[][]{
                {"Nha Trang", "Phan Thiết" },
        };
    }

    @DataProvider(name = "ChangePasswordData")
    public static Object[][] provideChangePasswordData() {
        return new Object[][]{
                {"12345678", "12345678" },
        };
    }

    @DataProvider(name = "ForgotPasswordDataValid")
    public static Object[][] provideForgotPasswordData() {
        return new Object[][]{
                {"12345678", "12345678", "" },
        };
    }
    @DataProvider(name = "ForgotPasswordDataInvalid")
    public static Object[][] provideForgotPasswordDataInvalid() {
        Random random = new Random();
        String randomPassword = generateRandomPassword(random, 8, 64);
        return new Object[][]{
                {"12345678", randomPassword},
        };
    }
}