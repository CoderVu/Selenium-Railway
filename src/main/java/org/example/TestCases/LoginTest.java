package org.example.TestCases;

import org.example.Common.constants.Constant;
import org.example.Common.util.DataTest;
import org.example.Common.util.Generate;
import org.example.PageObjects.ChangePasswordPage;
import org.example.PageObjects.HomePage;
import org.example.PageObjects.LoginPage;
import org.example.PageObjects.MyTicketPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class LoginTest {

    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        homePage = new HomePage();
        loginPage = new LoginPage();

        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }

    @Test( description = "TC01 - User can log into Railway with valid username and password", dataProvider = "loginDataActive", dataProviderClass = DataTest.class)
    public void TC01(String username, String password) {
        homePage.open();

        loginPage = homePage.gotoLoginPage();

        loginPage.login(username, password);

        Assert.assertEquals(homePage.getWelcomeMessage(), "Welcome " + username, "Welcome message is not displayed as expected");
    }

    @Test( description = "TC02: User can't login with blank 'Username' textbox", dataProvider = "loginDataEmpty", dataProviderClass = DataTest.class)
    public void TC02(String username, String password) {
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(username, password);

        Assert.assertEquals(loginPage.getLoginErrorMsg(), "There was a problem with your login and/or errors exist in your form.", "Error message is not displayed as expected");
    }

    @Test( description = "TC03: User cannot log into Railway with invalid password", dataProvider = "loginDataInvalid", dataProviderClass = DataTest.class)
    public void TC03(String username, String password) {
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        loginPage.login(username, password);
        Assert.assertEquals(loginPage.getLoginErrorMsg(), "There was a problem with your login and/or errors exist in your form.", "Error message is not displayed as expected");

    }
    @Test( description = "TC04: Login page displays when un-logged User clicks on 'Book ticket' tab")
    public void TC04() {
        homePage.open();

        homePage.gotoBookTicketPage();

        LoginPage loginPage = new LoginPage();
        Assert.assertEquals(loginPage.getLoginPage(), "Login page", "Directer to wrong page");

    }
    @Test(description = "TC05: System shows message when user enters wrong password several times", dataProvider = "loginDataInvalid", dataProviderClass = DataTest.class)
    public void TC05(String username, String invalidPassword) {

        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        loginPage.login(username, invalidPassword);
        loginPage.login(username, invalidPassword);
        loginPage.login(username, invalidPassword);
        loginPage.login(username, invalidPassword);

        // kiểm tra thông báo lỗi sau 4 lần
        Assert.assertEquals(loginPage.getLoginErrorMsg(), "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.", "Error message after 4 attempts is not displayed as expected");

        // đăng nhập lần thứ 5
        loginPage.login(username, invalidPassword);

        Assert.assertEquals(loginPage.getLoginErrorMsg(), "You have used all 5 login attempts. Please try again in 15 minutes.", "Error message after 5 attempts is not displayed as expected");
    }
    @Test(description = "TC06: Additional pages display once user logged in", dataProvider = "loginDataActive", dataProviderClass = DataTest.class)
    public void TC06(String username, String password) {


        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        loginPage.login(username, password);

        Assert.assertTrue(homePage.isMyTicketTabDisplayed(), "'My ticket' tab is displayed");
        Assert.assertTrue(homePage.isChangePasswordTabDisplayed(), "'Change password' tab is displayed");
        Assert.assertTrue(homePage.isLogoutTabDisplayed(), "'Logout' tab is displayed");

        MyTicketPage myTicketPage = homePage.gotoMyTicketPage();
        Assert.assertEquals(myTicketPage.getHeader(), "Manage ticket", "User is not directed to My ticket page");

        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();
        Assert.assertEquals(changePasswordPage.getHeader(),"Change password", "User is not directed to Change password page");
    }

}
