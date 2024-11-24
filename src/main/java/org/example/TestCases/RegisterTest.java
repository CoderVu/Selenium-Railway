package org.example.TCs;

import org.example.common.constants.Constant;
import org.example.common.util.UrlExtractor;
import org.example.DataTest.DataTest;

import org.example.cf.EmailConfig;
import org.example.common.util.MailReader;
import org.example.DataObjects.HomePage;
import org.example.DataObjects.LoginPage;
import org.example.DataObjects.RegisterPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;


public class RegisterTest {
    Random random;
    HomePage homePage;
    RegisterPage registerPage;
    SoftAssert softAssert;
    EmailConfig emailConfig;

    @BeforeMethod
    public void beforeMethod() {
        random = new Random();
        homePage = new HomePage();
        registerPage = new RegisterPage();
        softAssert = new SoftAssert();
        emailConfig = new EmailConfig("vunguyen.17082003@gmail.com",
                "ztxakyuwuvytnpwo",
                "thanhletraining03@gmail.com"
        );

        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }

    @Test(description = "TC07: User can create new account", dataProvider = "register_data", dataProviderClass = DataTest.class)
    public void TC07(String email, String password, String confirmPassword, String pid) throws InterruptedException{

        homePage.open();
        RegisterPage registerPage = homePage.gotoRegisterPage();

        registerPage.register(email, password, confirmPassword, pid);

        Assert.assertEquals(registerPage.getLblRegisterSuccessMessageText(), "Thank you for registering your account", "Register success message is not displayed as expected");

        Thread.sleep(10000);

        String emailContent = MailReader.readEmail(emailConfig);

        String confirmationUrl = UrlExtractor.ConfirmationUrlRegister(emailContent);
        Constant.WEBDRIVER.get(confirmationUrl);
    }

    @Test(description = "TC08: User can't login with an account hasn't been activated", dataProvider = "register_data", dataProviderClass = DataTest.class)
    public void TC08 (String email, String password, String confirmPassword, String pid) throws InterruptedException {

        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();
        registerPage.register(email, password, confirmPassword, pid);

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(email, password);
        
        Assert.assertEquals(loginPage.getLoginErrorMsg(), "Invalid username or password. Please try again.", "Error message is not displayed as expected");
    }

    @Test(description = "TC10: User can't create account with 'Confirm password' is not the same with 'Password'", dataProvider = "register_data_invalid", dataProviderClass = DataTest.class)
    public void TC10 (String email, String password, String confirmPassword, String pid) {

        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();
        registerPage.register(email, password, confirmPassword, pid);

        Assert.assertEquals(registerPage.getLblRegisterErrorMessageText(), "There're errors in the form. Please correct the errors and try again.", "Error message is not displayed as expected");
    }

    @Test(description = "TC11: User can't create account while password and PID are not the same", dataProvider = "register_data_empty", dataProviderClass = DataTest.class)
    public void TC11(String email, String password, String confirmPassword, String pid) {

        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();
        registerPage.register(email, password, confirmPassword, pid);

        softAssert.assertEquals(registerPage.getLblRegisterErrorMessageText(), "There're errors in the form. Please correct the errors and try again.", "Error message is not displayed as expected");
        softAssert.assertEquals(registerPage.getLblPasswordErrorMessageText(), "Invalid password length.", "Password error message is not displayed as expected");
        softAssert.assertEquals(registerPage.getLblPIDErrorMessageText(), "Invalid ID length.", "PID error message is not displayed as expected");

        softAssert.assertAll();
    }

}
