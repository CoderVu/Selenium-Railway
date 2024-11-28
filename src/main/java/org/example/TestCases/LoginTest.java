package org.example.TestCases;

import com.aventstack.extentreports.ExtentTest;
import org.example.Common.constants.Constant;
import org.example.Common.util.ExtentManager;
import org.example.DataObjects.Account;
import org.example.DataTest.DataTest;
import org.example.DataTypes.ExpectedTexts;
import org.example.PageObjects.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest {

    HomePage homePage;
    LoginPage loginPage;
    SoftAssert softAssert;
    ChangePasswordPage changePasswordPage;
    MyTicketPage myTicketPage;
    Account account;
    ExtentTest extentTest;

    @BeforeMethod
    public void beforeMethod() {
    System.out.println("Pre-condition");
    homePage = new HomePage();
    loginPage = new LoginPage();
    changePasswordPage = new ChangePasswordPage();
    myTicketPage = new MyTicketPage();
    softAssert = new SoftAssert();
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

    @Test( description = "TC01 - User can log into Railway with valid username and password")
    public void TC01() {
        extentTest = ExtentManager.createTest("LoginTest: TC01", "User can log into Railway with valid username and password");
        homePage.open();
        homePage.gotoLoginPage();
        account = new Account(Constant.USERNAMEACTIVE, Constant.PASSWORD);
        loginPage.login(account);
        Assert.assertEquals(homePage.getWelcomeMessage(), "Welcome " + account.getEmail(), "Welcome message is not displayed as expected");
    }

    @Test( description = "TC02: User can't login with blank 'Username' textbox", dataProvider = "login_data_empty", dataProviderClass = DataTest.class)
    public void TC02(Account account) {
        extentTest = ExtentManager.createTest("LoginTest: TC02", "User can't login with blank 'Username' textbox");
        homePage.open();
        homePage.gotoLoginPage();
        loginPage.login(account);
        Assert.assertEquals(loginPage.getLoginErrorMsg(), ExpectedTexts.LOGIN_ERROR.getText(), "Error message is not displayed as expected");
    }

    @Test( description = "TC03: User cannot log into Railway with invalid password", dataProvider = "login_data_invalid", dataProviderClass = DataTest.class)
    public void TC03(Account account) {
        extentTest = ExtentManager.createTest("LoginTest: TC03", "User cannot log into Railway with invalid password");
        homePage.open();
        homePage.gotoLoginPage();
        loginPage.login(account);
        Assert.assertEquals(loginPage.getLoginErrorMsg(), ExpectedTexts.LOGIN_ERROR.getText(), "Error message is not displayed as expected");

    }
    @Test( description = "TC04: Login page displays when un-logged User clicks on 'Book ticket' tab")
    public void TC04() {
        extentTest = ExtentManager.createTest("LoginTest: TC04", "Login page displays when un-logged User clicks on 'Book ticket' tab");
        homePage.open();
        homePage.gotoBookTicketPage();
        Assert.assertEquals(loginPage.getLoginPage(), ExpectedTexts.HEADER_LOGIN.getText(), "Directer to wrong page");

    }
    @Test(description = "TC05: System shows message when user enters wrong password several times", dataProvider = "login_data_invalid", dataProviderClass = DataTest.class)
    public void TC05(Account account) {
        extentTest = ExtentManager.createTest("LoginTest: TC05", "System shows message when user enters wrong password several times");
        homePage.open();
        homePage.gotoLoginPage();
        loginPage.login(account);
        loginPage.login(account);
        loginPage.login(account);
        loginPage.login(account);
        softAssert.assertEquals(loginPage.getLoginErrorMsg(), ExpectedTexts.LOGIN_ERROR_ATTEMPT.getText(), "Error message after 4 attempts is not displayed as expected");
        loginPage.login(account);
        softAssert.assertEquals(loginPage.getLoginErrorMsg(), ExpectedTexts.LOGIN_ERROR_TIMEOUT.getText(), "Error message after 5 attempts is not displayed as expected");
        softAssert.assertAll();
    }
    @Test(description = "TC06: Additional pages display once user logged in")
    public void TC06() {
        extentTest = ExtentManager.createTest("LoginTest: TC06", "Additional pages display once user logged in");
        homePage.open();
        homePage.gotoLoginPage();
        account = new Account(Constant.USERNAMEACTIVE, Constant.PASSWORD);
        loginPage.login(account);
        softAssert.assertTrue(homePage.isMyTicketTabDisplayed(), "My ticket tab is not displayed as expected");
        softAssert.assertTrue(homePage.isChangePasswordTabDisplayed(), "Change password tab is not displayed as expected");
        softAssert.assertTrue(homePage.isLogoutTabDisplayed(), "Logout tab is not displayed as expected");
        homePage.gotoMyTicketPage();
        softAssert.assertEquals(myTicketPage.getHeader(), ExpectedTexts.HEADER_MY_TICKET.getText(), "User is not directed to My ticket page");
        homePage.gotoChangePasswordPage();
        softAssert.assertEquals(homePage.getHeader(), ExpectedTexts.HEADER_CHANGE_PASSWORD.getText(),"User is not directed to Change password page");
        softAssert.assertAll();
    }

}
