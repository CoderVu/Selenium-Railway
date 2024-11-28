package org.example.TestCases;

import com.aventstack.extentreports.ExtentTest;
import org.example.Common.constants.Constant;
import org.example.Common.utils.ExtentManager;
import org.example.DataObjects.Account;
import org.example.DataTest.DataTest;
import org.example.DataTypes.ExpectedTexts;
import org.example.PageObjects.ChangePasswordPage;
import org.example.PageObjects.HomePage;
import org.example.PageObjects.LoginPage;
import org.example.PageObjects.RegisterPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordTest {
    HomePage homePage;
    LoginPage loginPage;
    ChangePasswordPage changePasswordPage;
    Account account;
    RegisterPage registerPage;
    ExtentTest extentTest;

    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        changePasswordPage = new ChangePasswordPage();
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


    @Test(description = "TC09 - User can change password", dataProvider = "change_password_data", dataProviderClass = DataTest.class)
    public void TC09 (Account account, String newPassword, String confirmPassword) {
        extentTest = ExtentManager.createTest("ChangePasswordTest: TC09", "User can change password");
        homePage.open();
        homePage.gotoRegisterPage();
        registerPage.registerConfirm(account);
        homePage.gotoLoginPage();
        loginPage.login(account);
        homePage.gotoChangePasswordPage();
        changePasswordPage.changePassword(account.getPassword(), newPassword, confirmPassword);
        Assert.assertEquals(changePasswordPage.getSuccessMessage(), ExpectedTexts.CHANGE_PASSWORD_SUCCESS.getText(), "Error message is not displayed as expected");
    }
}
