package org.example.TestCases;

import org.example.Common.constants.Constant;
import org.example.Common.util.DataTest;
import org.example.PageObjects.BookTicketPage;
import org.example.PageObjects.HomePage;
import org.example.PageObjects.LoginPage;
import org.example.PageObjects.MyTicketPage;
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
    @Test(description = "TC16 - User can cancel a ticket", dataProvider = "ticketDataCancel", dataProviderClass = DataTest.class)
    public void TC16(LocalDate date, String departFrom, String arriveAt, String seatType, int amount) throws InterruptedException {

        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAMEACTIVE, Constant.PASSWORD);

        homePage.gotoBookTicketPage();
        bookTicketPage.bookTicket(date, departFrom, arriveAt, seatType, amount);

        homePage.gotoMyTicketPage();
        int ticketCountBeforeCancel = myTicketPage.getTicketCount();

        myTicketPage.cancelTicket(departFrom, arriveAt, seatType, amount);
        int ticketCountAfterCancel = myTicketPage.getTicketCount();

        Assert.assertTrue(myTicketPage.isTicketDeleted(ticketCountBeforeCancel, ticketCountAfterCancel), "Ticket was not deleted correctly.");
    }

}
