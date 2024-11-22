package org.example.TestCases;

import org.example.Common.constants.constant;
import org.example.Common.util.confirmUrl;
import org.example.DataObjects.DataTest;
import org.example.Common.util.mailConfig;
import org.example.PageObjects.ForgotPasswordPage;
import org.example.PageObjects.HomePage;
import org.example.PageObjects.LoginPage;
import org.example.PageObjects.PasswordChangePage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class ForgotPasswordTest {
    HomePage homePage;
    LoginPage loginPage;
    ForgotPasswordPage forgotPasswordPage;
    PasswordChangePage passwordChangePage;
    SoftAssert softAssert;

    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        forgotPasswordPage = new ForgotPasswordPage();
        passwordChangePage = new PasswordChangePage();
        softAssert = new SoftAssert();

        System.out.println("Pre-condition");
        constant.WEBDRIVER = new ChromeDriver();
        constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        constant.WEBDRIVER.quit();
    }

    @Test(description = "Errors display when password reset token is blank", dataProvider = "forgot_password_data_valid", dataProviderClass = DataTest.class)
    public void TC12(String newPassword, String confirmPassword, String resetToken) throws InterruptedException {

        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.gotoForgotPasswordPage();

        forgotPasswordPage.sendInstructions(constant.USERNAMEBLOCK);

        Thread.sleep(7000);

        String emailContent = mailConfig.ReadEmail();

        String confirmationUrl = confirmUrl.ConfirmationUrlResetPassword(emailContent);
        constant.WEBDRIVER.get(confirmationUrl);

        passwordChangePage.resetPassword(newPassword, confirmPassword, resetToken);
        softAssert.assertEquals(passwordChangePage.getLblErrorMessageText(), "The password reset token is incorrect or may be expired. Visit the forgot password page to generate a new one.","Error message is not displayed as expected");
        softAssert.assertEquals(passwordChangePage.getLblErrorMessageTokenFieldText(), "The password reset token is invalid.", "Error message is not displayed as expected");
        softAssert.assertAll();
    }
    @Test(description = "Errors display if password and confirm password don't match when resetting password", dataProvider = "forgot_password_data_invalid", dataProviderClass = DataTest.class)
    public void TC13(String newPassword, String confirmPassword) throws InterruptedException {

        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.gotoForgotPasswordPage();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        forgotPasswordPage.sendInstructions(constant.USERNAMEBLOCK);

        Thread.sleep(7000);

        String emailContent = mailConfig.ReadEmail();
        String confirmationUrl = confirmUrl.ConfirmationUrlResetPassword(emailContent);
        constant.WEBDRIVER.get(confirmationUrl);

        Assert.assertEquals(passwordChangePage.getLblHeaderText(), "Password Change Form", "Header is not displayed as expected");
        passwordChangePage.resetPassword(newPassword, confirmPassword);

        softAssert.assertEquals(passwordChangePage.getLblErrorMessageText(), "Could not reset password. Please correct the errors and try again.", "Error message is not displayed as expected");
        softAssert.assertEquals(passwordChangePage.getLblErrorMessageConfirmPasswordFieldText(),"The password confirmation did not match the new password.", "Error message is not displayed as expected");
        softAssert.assertAll();
    }

}
