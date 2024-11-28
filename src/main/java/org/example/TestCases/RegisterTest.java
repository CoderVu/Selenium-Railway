package org.example.TestCases;

import com.aventstack.extentreports.ExtentTest;
import org.example.Common.constants.Constant;
import org.example.Common.util.ExtentManager;
import org.example.DataObjects.Account;
import org.example.DataTest.DataTest;

import org.example.DataTypes.ExpectedTexts;
import org.example.PageObjects.HomePage;
import org.example.PageObjects.LoginPage;
import org.example.PageObjects.RegisterPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;


public class RegisterTest {
    Random random;
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    SoftAssert softAssert;
    ExtentTest extentTest;
    @BeforeMethod
    public void beforeMethod() {
        random = new Random();
        homePage = new HomePage();
        registerPage = new RegisterPage();
        softAssert = new SoftAssert();
        loginPage = new LoginPage();
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


    @Test(description = "TC07: User can create new account", dataProvider = "register_data", dataProviderClass = DataTest.class)
    public void TC07(Account account) {
        extentTest = ExtentManager.createTest("RegisterTest: TC07", "User can create a new account");
        homePage.open();
        homePage.gotoRegisterPage();
        String actualMessage = registerPage.registerConfirm(account);
        Assert.assertEquals(actualMessage, ExpectedTexts.REGISTER_SUCCESS.getText(), "Register success message is not displayed as expected");
    }

    @Test(description = "TC08: User can't login with an account hasn't been activated", dataProvider = "register_data", dataProviderClass = DataTest.class)
    public void TC08 (Account account) {
        extentTest = ExtentManager.createTest("RegisterTest: TC08", "User can't login with an account hasn't been activated");
        homePage.open();
        homePage.gotoRegisterPage();
        registerPage.registerNotConfirm(account);
        homePage.gotoLoginPage();
        loginPage.login(account);
        Assert.assertEquals(loginPage.getLoginErrorMsg(), ExpectedTexts.LOGIN_ERROT_NOT_ACTIVATED.getText(), "Error message is not displayed as expected");
    }

    @Test(description = "TC10: User can't create account with 'Confirm password' is not the same with 'Password'", dataProvider = "register_data_invalid", dataProviderClass = DataTest.class)
    public void TC10 (Account account) {
        extentTest = ExtentManager.createTest("RegisterTest: TC10", "User can't create account with 'Confirm password' is not the same with 'Password'");
        homePage.open();
        homePage.gotoRegisterPage();
        registerPage.register(account);
        Assert.assertEquals(registerPage.getLblRegisterErrorMessageText(), ExpectedTexts.FORM_ERROR.getText(), "Error message is not displayed as expected");
    }

    @Test(description = "TC11: User can't create account while password and PID are not the same", dataProvider = "register_data_empty", dataProviderClass = DataTest.class)
    public void TC11(Account account) {
        extentTest = ExtentManager.createTest("RegisterTest: TC11", "User can't create account while password and PID are not the same");
        homePage.open();
        homePage.gotoRegisterPage();
        registerPage.register(account);
        softAssert.assertEquals(registerPage.getLblRegisterErrorMessageText(), ExpectedTexts.FORM_ERROR.getText(), "Error message is not displayed as expected");
        softAssert.assertEquals(registerPage.getLblPasswordErrorMessageText(), ExpectedTexts.PASSWORD_LENGTH_ERROR.getText(), "Password error message is not displayed as expected");
        softAssert.assertEquals(registerPage.getLblPIDErrorMessageText(), ExpectedTexts.PID_LENGTH_ERROR.getText(), "PID error message is not displayed as expected");
        softAssert.assertAll();
    }
}
