package org.example.DataTest;

import org.example.Common.utils.Generate;
import org.example.DataObjects.Account;
import org.example.DataObjects.Ticket;
import org.example.DataTypes.SeatType;
import org.example.DataTypes.Station;
import org.testng.annotations.DataProvider;


import java.time.LocalDate;
import java.util.Random;

import static org.example.Common.utils.Generate.generateRandomPassword;

public class DataTest {
    @DataProvider(name = "register_data")
    public static Object[][] provideRegisterData() {
        Random random = new Random();
        String email = Generate.generateEmail(random);
        String password = generateRandomPassword(random, 8, 64);
        String confirmPassword = password;
        String pid = Generate.generateRandomPID(random, 8, 20);
        Account account = new Account(email, password, confirmPassword, pid);
        return new Object[][]{
                {account},
        };
    }

    @DataProvider(name = "register_data_invalid")
    public static Object[][] provideRegisterDataInvalid() {
        Random random = new Random();
        String email = Generate.generateEmail(random);
        String password = Generate.generateRandomPassword(random, 8, 64);
        String confirmPassword = Generate.generateRandomPassword(random, 8, 64);
        String pid = Generate.generateRandomPID(random, 8, 20);
        Account account = new Account(email, password, confirmPassword, pid);
        return new Object[][]{
                {account},
        };
    }
    @DataProvider(name = "register_data_empty")
    public static Object[][] provideRegisterDataEmpty() {
        Random random = new Random();
        String email = Generate.generateEmail(random);
        String password = "";
        String confirmPassword = "";
        String pid = "";
        Account account = new Account(email, password, confirmPassword, pid);
        return new Object[][]{
                {account},
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
        String email = "vunguyen.17082003@gmail.com";
        String randomPassword = generateRandomPassword(random, 8, 64);
        Account account = new Account(email, randomPassword);
        return new Object[][]{
                {account},
        };
    }
    @DataProvider(name = "login_data_empty")
    public static Object[][] provideLoginDataEmpty() {
        String email = "";
        String password = "12345678";
        Account account = new Account(email, password);
        return new Object[][]{
                {account},
        };
    }

    @DataProvider(name = "ticket_data")
    public static Object[][] provideTicketData() {
        Random random = new Random();
        String email = Generate.generateEmail(random);
        String password = generateRandomPassword(random, 8, 64);
        String confirmPassword = password;
        String pid = Generate.generateRandomPID(random, 8, 20);
        Account account = new Account(email, password, confirmPassword, pid);

        LocalDate date = LocalDate.now().plusDays(3);
        Station departFrom = Station.PHAN_THIET;
        Station arriveAt = Station.DA_NANG;
        SeatType seatType = SeatType.SOFT_SEAT_AC;
        int amount = 1;
        Ticket ticket = new Ticket(date, departFrom, arriveAt, seatType, amount);
        return new Object[][]{
                {account, ticket},
        };
    }
    @DataProvider(name = "ticket_data_cancel")
    public static Object[][] provideTicketDataCancel() {
        Random random = new Random();
        String email = Generate.generateEmail(random);
        String password = generateRandomPassword(random, 8, 64);
        String confirmPassword = password;
        String pid = Generate.generateRandomPID(random, 8, 20);
        Account account = new Account(email, password, confirmPassword, pid);

        LocalDate date = LocalDate.now().plusDays(3);
        Station departFrom = Station.QUANG_NGAI;
        Station arriveAt = Station.HUE;
        SeatType seatType = SeatType.HARD_SEAT;
        int amount = 1;
        Ticket ticket = new Ticket(date, departFrom, arriveAt, seatType, amount);
        return new Object[][]{
                {account, ticket},
        };
    }

    @DataProvider(name = "trip_data")
    public static Object[][] provideTripData() {
        Random random = new Random();
        String email = Generate.generateEmail(random);
        String password = generateRandomPassword(random, 8, 64);
        String confirmPassword = password;
        String pid = Generate.generateRandomPID(random, 8, 20);
        Account account = new Account(email, password, confirmPassword, pid);
        Station departFrom = Station.NHA_TRANG;
        Station arriveAt = Station.PHAN_THIET;
        return new Object[][]{
                { account, departFrom, arriveAt },
        };
    }

    @DataProvider(name = "change_password_data")
    public static Object[][] provideChangePasswordData() {
        Random random = new Random();
        String email = Generate.generateEmail(random);
        String password = generateRandomPassword(random, 8, 64);
        String confirmPassword = password;
        String pid = Generate.generateRandomPID(random, 8, 20);
        Account account = new Account(email, password, confirmPassword, pid);
        return new Object[][]{
                {account, "12345678", "12345678" },
        };
    }

    @DataProvider(name = "forgot_password_data_valid")
    public static Object[][] provideForgotPasswordData() {
        Random random = new Random();
        String email = Generate.generateEmail(random);
        String password = generateRandomPassword(random, 8, 64);
        String confirmPassword = password;
        String pid = Generate.generateRandomPID(random, 8, 20);
        Account account = new Account(email, password, confirmPassword, pid);

        return new Object[][]{
                {account, "12345678", "12345678", "" },
        };
    }
    @DataProvider(name = "forgot_password_data_invalid")
    public static Object[][] provideForgotPasswordDataInvalid() {
        Random random = new Random();
        String email = Generate.generateEmail(random);
        String password = generateRandomPassword(random, 8, 64);
        String confirmPassword = password;
        String pid = Generate.generateRandomPID(random, 8, 20);
        Account account = new Account(email, password, confirmPassword, pid);
        String randomPassword = generateRandomPassword(random, 8, 64);
        return new Object[][]{
                {account, "12345678", randomPassword},
        };
    }
}
