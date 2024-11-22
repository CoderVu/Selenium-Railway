package org.example.DataObjects;

import org.example.Common.util.generate;
import org.testng.annotations.DataProvider;

import java.time.LocalDate;
import java.util.Random;

import static org.example.Common.util.generate.generateRandomPassword;

public class DataTest {
    @DataProvider(name = "register_data")
    public static Object[][] provideRegisterData() {
        Random random = new Random();
        String email = generate.generateEmail(random);
        String password = generate.genaratePassword();
        String confirmPassword = password;
        String pid = generate.generateRandomPID(random, 8, 20);
        return new Object[][]{
                {email, password, confirmPassword, pid},
        };
    }
    @DataProvider(name = "register_data_invalid")
    public static Object[][] provideRegisterDataInvalid() {
        Random random = new Random();
        String email = generate.generateEmail(random);
        String password = generate.generateRandomPassword(random, 8, 64);
        String confirmPassword = generate.generateRandomPassword(random, 8, 64);
        String pid = generate.generateRandomPID(random, 8, 20);
        return new Object[][]{
                {email, password, confirmPassword, pid},
        };
    }
    @DataProvider(name = "register_data_empty")
    public static Object[][] provideRegisterDataEmpty() {
        Random random = new Random();
        String email = generate.generateEmail(random);
        String password = generate.generatePasswordEmpty();
        String confirmPassword = generate.generatePasswordEmpty();
        String pid = generate.generatePIPEmpty();
        return new Object[][]{
                {email, password, confirmPassword, pid},
        };
    }

    @DataProvider(name = "login_data_active")
    public static Object[][] provideLoginData() {
        return new Object[][]{
                {"vunguyen.170803@grr.la", "12345678"},
        };
    }
    @DataProvider(name = "login_data_block")
    public static Object[][] provideLoginDataBlock() {
        return new Object[][]{
                {"vunguyen.17082003@gmail.com", "12345678"},
        };
    }
    @DataProvider(name = "login_data_invalid")
    public static Object[][] provideLoginDataInvalid() {
        Random random = new Random();
        String randomPassword = generateRandomPassword(random, 8, 64);
        return new Object[][]{
                {"vunguyen.17082003@gmail.com", randomPassword},
        };
    }
    @DataProvider(name = "login_data_empty")
    public static Object[][] provideLoginDataEmpty() {
        return new Object[][]{
                {"", "12345678"},
        };
    }

    @DataProvider(name = "ticket_data")
    public static Object[][] provideTicketData() {
        return new Object[][]{
                {LocalDate.now().plusDays(3), "Phan Thiết", "Đà Nẵng", "Soft bed with air conditioner", 1},
        };
    }
    @DataProvider(name = "ticket_data_cancel")
    public static Object[][] provideTicketDataCancel() {
        return new Object[][]{
                {LocalDate.now().plusDays(3),"Sài Gòn", "Phan Thiết", "Hard seat", 2},
        };
    }

    @DataProvider(name = "trip_data")
    public static Object[][] provideTripData() {
        return new Object[][]{
                {"Nha Trang", "Phan Thiết" },
        };
    }

    @DataProvider(name = "change_password_data")
    public static Object[][] provideChangePasswordData() {
        return new Object[][]{
                {"12345678", "12345678" },
        };
    }

    @DataProvider(name = "forgot_password_data_valid")
    public static Object[][] provideForgotPasswordData() {
        return new Object[][]{
                {"12345678", "12345678", "" },
        };
    }
    @DataProvider(name = "forgot_password_data_invalid")
    public static Object[][] provideForgotPasswordDataInvalid() {
        Random random = new Random();
        String randomPassword = generateRandomPassword(random, 8, 64);
        return new Object[][]{
                {"12345678", randomPassword},
        };
    }
}
