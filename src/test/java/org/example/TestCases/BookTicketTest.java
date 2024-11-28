package org.example.TestCases;

import com.aventstack.extentreports.ExtentTest;
import org.example.Common.constants.Constant;
import org.example.Common.utils.ExtentManager;
import org.example.DataObjects.Account;
import org.example.DataObjects.Ticket;
import org.example.DataTest.DataTest;
import org.example.DataTypes.ExpectedTexts;
import org.example.DataTypes.Station;
import org.example.PageObjects.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class BookTicketTest {
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    BookTicketPage bookTicketPage;
    TimetablePage timetablePage;
    SoftAssert softAssert;
    Account account;
    ExtentTest extentTest;

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        homePage = new HomePage();
        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        bookTicketPage = new BookTicketPage();
        timetablePage = new TimetablePage();
        softAssert = new SoftAssert();
        account = new Account(Constant.USERNAMEACTIVE, Constant.PASSWORD);
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail("Test failed: " + result.getThrowable().getMessage());
            String screenshotPath = ExtentManager.captureScreenshot(Constant.WEBDRIVER, result.getName());
            extentTest.addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip("Test skipped: " + result.getThrowable().getMessage());
        }
        ExtentManager.flush();
        Constant.WEBDRIVER.quit();
    }

    @Test(description = "TC14 - User can book 1 ticket at a time", dataProvider = "ticket_data", dataProviderClass = DataTest.class)
    public void TC14(Account account, Ticket ticket) {
        extentTest = ExtentManager.createTest("BookTicketTest: TC14", "User can book 1 ticket at a time");
        homePage.open();
        homePage.gotoRegisterPage();
        registerPage.registerConfirm(account);
        homePage.gotoLoginPage();
        loginPage.login(account);
        homePage.gotoBookTicketPage();
        bookTicketPage.bookTicket(ticket);
        bookTicketPage.isTicketInformationDisplayed(ticket);
        softAssert.assertTrue(bookTicketPage.isTicketInformationDisplayed(ticket), "Ticket information is not displayed as expected");
        softAssert.assertEquals(homePage.getHeader(), ExpectedTexts.TICKET_BOOK_SUCCESS.getText(), "Error message is not displayed as expected");
        softAssert.assertAll();
    }
    @Test(description = "TC15 - User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page", dataProvider = "trip_data", dataProviderClass = DataTest.class)
    public void TC15(Account account, Station departFrom, Station arriveAt) {
        extentTest = ExtentManager.createTest("BookTicketTest: TC15", "User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page");
        homePage.open();
        homePage.gotoRegisterPage();
        registerPage.registerConfirm(account);
        homePage.gotoLoginPage();
        loginPage.login(account);
        homePage.gotoTimetablePage();
        timetablePage.checkOutBookTicket(departFrom, arriveAt);
        softAssert.assertEquals(homePage.getHeader(), ExpectedTexts.HEADER_BOOKING.getText(), "Error message is not displayed as expected");
        String departStation = bookTicketPage.getDepartFrom();
        String arriveStation = bookTicketPage.getArriveAt();
        softAssert.assertEquals(departStation, departFrom.getStationName(), "Depart station does not match the selected one");
        softAssert.assertEquals(arriveStation, arriveAt.getStationName(), "Arrive station does not match the selected one");
        softAssert.assertAll();
    }
}

