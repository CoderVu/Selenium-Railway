package org.example.TestCases;

import org.example.Common.Constants.Constant;
import org.example.PageObjects.ChangePasswordPage;
import org.example.PageObjects.HomePage;
import org.example.PageObjects.LoginPage;
import org.example.PageObjects.MyTicketPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class LoginTest {

    HomePage homePage;
    LoginPage loginPage;
    String actualMsg;
    String expectedMsg;

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        homePage = new HomePage();
        loginPage = new LoginPage();

        // Initialize WebDriver
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        // Quit WebDriver
        Constant.WEBDRIVER.quit();
    }

    @Test(
            description = "TC01 - User can log into Railway with valid username and password"
    )
    public void TC01() {
        homePage.open();

        loginPage = homePage.gotoLoginPage();

        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        Assert.assertEquals(homePage.getWelcomeMessage(), "Welcome " + Constant.USERNAME, "Welcome message is not displayed as expected");
    }

    @Test
    public void TC02() {
        System.out.println("TC02: User can't login with blank 'Username' textbox");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String blankUsername = "";
        String password = Constant.PASSWORD;
        loginPage.login(blankUsername, password);

        String actualMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }
    @Test(
            description = "TC03: User cannot log into Railway with invalid password"
    )
    public void TC03() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String password = generateRandomPassword(new Random(), 8, 64);
        loginPage.login(Constant.USERNAME, password);

        String actualMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");

    }
    @Test
    public void TC04() {
        System.out.println("TC04: Login page displays when un-logged User clicks on \"Book ticket\" tab\n\n");

        HomePage homePage = new HomePage();
        homePage.open();

        homePage.gotoBookTicketPage();

        LoginPage loginPage = new LoginPage();
        String actualMsg = loginPage.getLblLoginPage().getText();
        String expectedMsg = "Login page";
        Assert.assertEquals(actualMsg, expectedMsg, "Directer to wrong page");

    }
    @Test
    public void TC05() {
        System.out.println("TC05: System shows message when user enters wrong password several times\n");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        String invalidPassword = generateRandomPassword(new Random(), 8, 64);

        String expectedMsgAfter4Attempts = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
        String actualMsg = "";

        for (int i = 1; i <= 4; i++) {
            loginPage.login(Constant.USERNAME, invalidPassword);
        }
        actualMsg = loginPage.getLblLoginErrorMsg().getText();
        // kiểm tra thông báo lỗi sau 4 lần
        Assert.assertEquals(actualMsg, expectedMsgAfter4Attempts, "Error message after 4 attempts is not displayed as expected");

        // đăng nhập lần thứ 5
        loginPage.login(Constant.USERNAME, invalidPassword);

        // kiểm tra thông báo lỗi sau lần thứ 5
        String expectedMsgAfter5Attempts = "You have used all 5 login attempts. Please try again in 15 minutes.";
        actualMsg = loginPage.getLblLoginErrorMsg().getText();
        Assert.assertEquals(actualMsg, expectedMsgAfter5Attempts, "Error message after 5 attempts is not displayed as expected");
    }
    @Test
    public void TC06() {
        System.out.println("TC06: Additional pages display once user logged in");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        Assert.assertTrue(homePage.isMyTicketTabDisplayed(), "'My ticket' tab is displayed");
        Assert.assertTrue(homePage.isChangePasswordTabDisplayed(), "'Change password' tab is displayed");
        Assert.assertTrue(homePage.isLogoutTabDisplayed(), "'Logout' tab is displayed");


        MyTicketPage myTicketPage = homePage.gotoMyTicketPage();
        Assert.assertTrue(myTicketPage.isMyTicketPageDisplayed(), "User is not directed to My ticket page");

        Assert.assertEquals(myTicketPage.getHeader(), "Manage ticket", "User is not directed to My ticket page");

        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();
        Assert.assertTrue(changePasswordPage.isChangePasswordPageDisplayed(), "User is not directed to Change password page");
    }

    private String generateRandomPassword(Random random, int minLength, int maxLength) {
        // common-lang3
        int passwordLength = minLength + random.nextInt(maxLength - minLength + 1);
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < passwordLength; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }
}
