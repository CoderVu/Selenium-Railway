package org.example.TCs;

import org.example.common.constants.Constant;
import org.example.DataTest.DataTest;
import org.example.DataObjects.BookTicketPage;
import org.example.DataObjects.HomePage;
import org.example.DataObjects.LoginPage;
import org.example.DataObjects.MyTicketPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class MyTicketTest {
    HomePage homePage;
    LoginPage loginPage;
    BookTicketPage bookTicketPage;
    MyTicketPage myTicketPage;

    @BeforeMethod
    public void beforeMethod() {

        homePage = new HomePage();
        loginPage = new LoginPage();
        bookTicketPage = new BookTicketPage();
        myTicketPage = new MyTicketPage();

        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
    @Test(description = "TC16 - User can cancel a ticket", dataProvider = "ticket_data_cancel", dataProviderClass = DataTest.class)
    public void TC16(LocalDate date, String departFrom, String arriveAt, String seatType, int amount) {

        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAMEACTIVE, Constant.PASSWORD);

        homePage.gotoBookTicketPage();
        bookTicketPage.bookTicket(date, departFrom, arriveAt, seatType, amount);

        homePage.gotoMyTicketPage();
        int ticketCountBeforeCancel = myTicketPage.getTicketCount();
        System.out.println(ticketCountBeforeCancel);

        myTicketPage.cancelTicket(departFrom, arriveAt, seatType, amount);
        int ticketCountAfterCancel = myTicketPage.getTicketCount();
        System.out.println(ticketCountAfterCancel);

        Assert.assertTrue(myTicketPage.isTicketDeleted(ticketCountBeforeCancel, ticketCountAfterCancel), "Ticket was not deleted correctly.");
    }

}
