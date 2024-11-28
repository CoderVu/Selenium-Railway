package org.example.TestCases;

import com.aventstack.extentreports.ExtentTest;
import org.example.Common.constants.Constant;
import org.example.Common.utils.ExtentManager;
import org.example.DataObjects.Account;
import org.example.DataObjects.Ticket;
import org.example.DataTest.DataTest;
import org.example.PageObjects.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyTicketTest {
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    BookTicketPage bookTicketPage;
    MyTicketPage myTicketPage;
    Account account;
    ExtentTest extentTest;
    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        bookTicketPage = new BookTicketPage();
        myTicketPage = new MyTicketPage();
        account = new Account(Constant.USERNAMEACTIVE, Constant.PASSWORD);
        System.out.println("Pre-condition");
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

    @Test(description = "TC16 - User can cancel a ticket", dataProvider = "ticket_data_cancel", dataProviderClass = DataTest.class)
    public void TC16(Account account, Ticket ticket) {
        extentTest = ExtentManager.createTest("MyTicketTest: TC16", "User can cancel a ticket");
        homePage.open();
        homePage.gotoRegisterPage();
        registerPage.registerConfirm(account);
        homePage.gotoLoginPage();
        loginPage.login(account);
        homePage.gotoBookTicketPage();
        String ticketId = bookTicketPage.BookAndGetId(ticket);
        homePage.gotoMyTicketPage();
        myTicketPage.cancelTicketById(ticketId);
        Assert.assertFalse(myTicketPage.isTicketIdExists(ticketId), "Ticket is not cancelled successfully");
        extentTest.pass("TC16 passed");
    }


}
