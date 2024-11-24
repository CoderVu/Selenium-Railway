package org.example.DT;

import org.example.common.util.Generate;
import org.testng.annotations.DataProvider;

import java.time.LocalDate;
import java.util.Random;

import static org.example.common.util.Generate.generateRandomPassword;

public class DataTest {
    @DataProvider(name = "register_data")
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
    @DataProvider(name = "register_data_invalid")
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
    @DataProvider(name = "register_data_empty")
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
            //{LocalDate.now().plusDays(3), "Sài Gòn", "Huế", "Soft bed with air conditioner", 1},
        };
    }
    @DataProvider(name = "ticket_data_cancel")
    public static Object[][] provideTicketDataCancel() {
        return new Object[][]{
                {LocalDate.now().plusDays(3),"Quảng Ngãi", "Huế", "Hard seat", 1},
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
