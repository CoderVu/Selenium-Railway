package org.example.TestCases;

import org.example.Common.constants.Constant;
import org.example.Common.util.DataTest;
import org.example.PageObjects.BookTicketPage;
import org.example.PageObjects.HomePage;
import org.example.PageObjects.LoginPage;
import org.example.PageObjects.TimetablePage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Map;

public class BookTicketTest {
    HomePage homePage;
    LoginPage loginPage;
    BookTicketPage bookTicketPage;
    TimetablePage timetablePage;

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        homePage = new HomePage();
        loginPage = new LoginPage();
        bookTicketPage = new BookTicketPage();
        timetablePage = new TimetablePage();
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }

    @Test(description = "TC14 - User can book 1 ticket at a time", dataProvider = "ticketData", dataProviderClass = DataTest.class)
    public void TC14(LocalDate date, String departFrom, String arriveAt, String seatType, int ticketAmount) throws InterruptedException {

        homePage.open();

        loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAMEACTIVE, Constant.PASSWORD);
        homePage.gotoBookTicketPage();

        bookTicketPage.bookTicket(date, departFrom, arriveAt, seatType, ticketAmount);
        Assert.assertEquals(bookTicketPage.getLblHeaderText(), "Ticket booked successfully!", "Error message is not displayed as expected");

        bookTicketPage.isTicketInformationDisplayed(date, departFrom, arriveAt, seatType, ticketAmount);
        Assert.assertTrue(bookTicketPage.isTicketInformationDisplayed(date, departFrom, arriveAt, seatType, ticketAmount), "Ticket information is not displayed as expected");

    }

    @Test(description = "TC15 - User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page", dataProvider = "tripData", dataProviderClass = DataTest.class)
    public void TC15(String departFrom, String arriveAt) {

        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAMEACTIVE, Constant.PASSWORD);

        homePage.gotoTimetablePage();

        timetablePage.bookTicket(departFrom, arriveAt);
        Assert.assertEquals(bookTicketPage.getLblHeaderText(), "Book ticket", "Error message is not displayed as expected");

        String departStation = bookTicketPage.getDepartFrom();
        String arriveStation = bookTicketPage.getArriveAt();
        Assert.assertEquals( departStation,departFrom, "Depart station does not match the selected one");
        Assert.assertEquals(arriveStation,arriveAt, "Arrive station does not match the selected one");
    }
}

