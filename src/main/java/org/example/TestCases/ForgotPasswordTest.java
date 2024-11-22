package org.example.TestCases;

import org.example.Common.constants.Constant;
import org.example.Common.util.ConfirmUrl;
import org.example.Common.util.DataTest;
import org.example.Common.util.MailConfig;
import org.example.PageObjects.ForgotPasswordPage;
import org.example.PageObjects.HomePage;
import org.example.PageObjects.LoginPage;
import org.example.PageObjects.PasswordChangePage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ForgotPasswordTest {
    HomePage homePage;
    LoginPage loginPage;
    ForgotPasswordPage forgotPasswordPage;
    PasswordChangePage passwordChangePage;

    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        forgotPasswordPage = new ForgotPasswordPage();
        passwordChangePage = new PasswordChangePage();

        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }

    @Test(description = "Errors display when password reset token is blank", dataProvider = "ForgotPasswordDataValid", dataProviderClass = DataTest.class)
    public void TC12(String newPassword, String confirmPassword, String resetToken) throws InterruptedException {

        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.gotoForgotPasswordPage();

        forgotPasswordPage.sendInstructions(Constant.USERNAMEBLOCK);

        Thread.sleep(7000);

        String emailContent = MailConfig.ReadEmail();

        String confirmationUrl = ConfirmUrl.ConfirmationUrlResetPassword(emailContent);
        Constant.WEBDRIVER.get(confirmationUrl);

        passwordChangePage.resetPassword(newPassword, confirmPassword, resetToken);
        Assert.assertEquals(passwordChangePage.getLblErrorMessageText(), "The password reset token is incorrect or may be expired. Visit the forgot password page to generate a new one.","Error message is not displayed as expected");
        Assert.assertEquals(passwordChangePage.getLblErrorMessageTokenFieldText(), "The password reset token is invalid.", "Error message is not displayed as expected");

    }
    @Test(description = "Errors display if password and confirm password don't match when resetting password", dataProvider = "ForgotPasswordDataInvalid", dataProviderClass = DataTest.class)
    public void TC13(String newPassword, String confirmPassword) throws InterruptedException {

        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.gotoForgotPasswordPage();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        forgotPasswordPage.sendInstructions(Constant.USERNAMEBLOCK);

        Thread.sleep(7000);

        String emailContent = MailConfig.ReadEmail();
        String confirmationUrl = ConfirmUrl.ConfirmationUrlResetPassword(emailContent);
        Constant.WEBDRIVER.get(confirmationUrl);

        Assert.assertEquals(passwordChangePage.getLblHeaderText(), "Password Change Form", "Header is not displayed as expected");
        passwordChangePage.resetPassword(newPassword, confirmPassword);

        Assert.assertEquals(passwordChangePage.getLblErrorMessageText(), "Could not reset password. Please correct the errors and try again.", "Error message is not displayed as expected");
        Assert.assertEquals(passwordChangePage.getLblErrorMessageConfirmPasswordFieldText(),"The password confirmation did not match the new password.", "Error message is not displayed as expected");

    }

}
