package org.example.TestCases;

import com.aventstack.extentreports.ExtentTest;
import org.example.Common.constants.Constant;
import org.example.Common.utils.ExtentManager;
import org.example.Config.EmailConfig;
import org.example.DataObjects.Account;
import org.example.DataTest.DataTest;
import org.example.DataTypes.ExpectedTexts;
import org.example.PageObjects.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class ForgotPasswordTest {
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    ForgotPasswordPage forgotPasswordPage;
    PasswordChangeFormPage passwordChangeFormPage;
    SoftAssert softAssert;
    EmailConfig emailConfig;
    ExtentTest extentTest;

    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        forgotPasswordPage = new ForgotPasswordPage();
        passwordChangeFormPage = new PasswordChangeFormPage();
        softAssert = new SoftAssert();
        emailConfig = new EmailConfig();
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


    @Test(description = "Errors display when password reset token is blank", dataProvider = "forgot_password_data_valid", dataProviderClass = DataTest.class)
    public void TC12(Account account, String newPassword, String confirmPassword, String resetToken)  {
        extentTest = ExtentManager.createTest("ForgotPasswordTest: TC12", "Errors display when password reset token is blank");
        homePage.open();
        homePage.gotoRegisterPage();
        registerPage.registerConfirm(account);
        homePage.gotoLoginPage();
        loginPage.gotoForgotPasswordPage();
        forgotPasswordPage.sendInstructions(account.getEmail());
        passwordChangeFormPage.resetPassword(newPassword, confirmPassword, resetToken);
        softAssert.assertEquals(passwordChangeFormPage.getLblHeaderText(), ExpectedTexts.HEADER_CHANGE_PASSWORD_FORM.getText(), "Header is not displayed as expected");
        softAssert.assertEquals(passwordChangeFormPage.getLblErrorMessageText(), "The password reset token is incorrect or may be expired. Visit the forgot password page to Generate a new one.","Error message is not displayed as expected");
        softAssert.assertEquals(passwordChangeFormPage.getLblErrorMessageTokenFieldText(), "The password reset token is invalid.", "Error message is not displayed as expected");
        softAssert.assertAll();
    }
    @Test(description = "Errors display if password and confirm password don't match when resetting password", dataProvider = "forgot_password_data_invalid", dataProviderClass = DataTest.class)
    public void TC13(Account account, String newPassword, String confirmPassword)  {
        extentTest = ExtentManager.createTest("ForgotPasswordTest: TC13", "Errors display if password and confirm password don't match when resetting password");
        homePage.open();
        homePage.gotoRegisterPage();
        registerPage.registerConfirm(account);
        homePage.gotoLoginPage();
        loginPage.gotoForgotPasswordPage();
        forgotPasswordPage.sendInstructions(account.getEmail());
        passwordChangeFormPage.resetPassword(newPassword, confirmPassword);
        softAssert.assertEquals(passwordChangeFormPage.getLblHeaderText(), ExpectedTexts.HEADER_CHANGE_PASSWORD_FORM.getText(), "Header is not displayed as expected");
        softAssert.assertEquals(passwordChangeFormPage.getLblErrorMessageText(), ExpectedTexts.CHANGE_PASSWORD_ERROR_PASSWORD.getText(), "Error message is not displayed as expected");
        softAssert.assertEquals(passwordChangeFormPage.getLblErrorMessageConfirmPasswordFieldText(), ExpectedTexts.CHANGE_PASSWORD_ERROR_CONFIRM.getText(), "Error message is not displayed as expected");
        softAssert.assertAll();
    }

}
