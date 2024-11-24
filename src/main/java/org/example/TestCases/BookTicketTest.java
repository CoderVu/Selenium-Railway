package org.example.TCs;

import org.example.common.constants.Constant;
import org.example.DataTest.DataTest;
import org.example.DataObjects.BookTicketPage;
import org.example.DataObjects.HomePage;
import org.example.DataObjects.LoginPage;
import org.example.DataObjects.TimetablePage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;

public class BookTicketTest {
    HomePage homePage;
    LoginPage loginPage;
    BookTicketPage bookTicketPage;
    TimetablePage timetablePage;
    SoftAssert softAssert;

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        homePage = new HomePage();
        loginPage = new LoginPage();
        bookTicketPage = new BookTicketPage();
        timetablePage = new TimetablePage();
        softAssert = new SoftAssert();
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }

    @Test(description = "TC14 - User can book 1 ticket at a time", dataProvider = "ticket_data", dataProviderClass = DataTest.class)
    public void TC14(LocalDate date, String departFrom, String arriveAt, String seatType, int ticketAmount)  {

        homePage.open();

        loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAMEACTIVE, Constant.PASSWORD);
        homePage.gotoBookTicketPage();

        bookTicketPage.bookTicket(date, departFrom, arriveAt, seatType, ticketAmount);


        bookTicketPage.isTicketInformationDisplayed(date, departFrom, arriveAt, seatType, ticketAmount);
        softAssert.assertTrue(bookTicketPage.isTicketInformationDisplayed(date, departFrom, arriveAt, seatType, ticketAmount), "Ticket information is not displayed as expected");
        softAssert.assertEquals(bookTicketPage.getLblHeaderText(), "Ticket booked successfully!", "Error message is not displayed as expected");
        softAssert.assertAll();
    }

    @Test(description = "TC15 - User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page", dataProvider = "trip_data", dataProviderClass = DataTest.class)
    public void TC15(String departFrom, String arriveAt) {

        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAMEACTIVE, Constant.PASSWORD);

        homePage.gotoTimetablePage();

        timetablePage.bookTicket(departFrom, arriveAt);
        softAssert.assertEquals(bookTicketPage.getLblHeaderText(), "Book ticket", "Error message is not displayed as expected");

        String departStation = bookTicketPage.getDepartFrom();
        String arriveStation = bookTicketPage.getArriveAt();
        softAssert.assertEquals( departStation,departFrom, "Depart station does not match the selected one");
        softAssert.assertEquals(arriveStation,arriveAt, "Arrive station does not match the selected one");
        softAssert.assertAll();
    }
}

