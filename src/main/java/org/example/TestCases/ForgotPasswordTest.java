package org.example.TCs;

import org.example.common.constants.Constant;
import org.example.common.util.UrlExtractor;
import org.example.DataTest.DataTest;
import org.example.cf.EmailConfig;
import org.example.common.util.MailReader;
import org.example.DataObjects.ForgotPasswordPage;
import org.example.DataObjects.HomePage;
import org.example.DataObjects.LoginPage;
import org.example.DataObjects.PasswordChangePage;
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
    EmailConfig emailConfig;

    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        forgotPasswordPage = new ForgotPasswordPage();
        passwordChangePage = new PasswordChangePage();
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

    @Test(description = "Errors display when password reset token is blank", dataProvider = "forgot_password_data_valid", dataProviderClass = DataTest.class)
    public void TC12(String newPassword, String confirmPassword, String resetToken) throws InterruptedException {

        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.gotoForgotPasswordPage();

        forgotPasswordPage.sendInstructions(Constant.USERNAMEBLOCK);

        Thread.sleep(7000);

        String emailContent = MailReader.readEmail(emailConfig);

        String confirmationUrl = UrlExtractor.ConfirmationUrlResetPassword(emailContent);
        Constant.WEBDRIVER.get(confirmationUrl);

        passwordChangePage.resetPassword(newPassword, confirmPassword, resetToken);
        softAssert.assertEquals(passwordChangePage.getLblErrorMessageText(), "The password reset token is incorrect or may be expired. Visit the forgot password page to Generate a new one.","Error message is not displayed as expected");
        softAssert.assertEquals(passwordChangePage.getLblErrorMessageTokenFieldText(), "The password reset token is invalid.", "Error message is not displayed as expected");
        softAssert.assertAll();
    }
    @Test(description = "Errors display if password and confirm password don't match when resetting password", dataProvider = "forgot_password_data_invalid", dataProviderClass = DataTest.class)
    public void TC13(String newPassword, String confirmPassword) throws InterruptedException {

        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.gotoForgotPasswordPage();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        forgotPasswordPage.sendInstructions(Constant.USERNAMEBLOCK);

        Thread.sleep(7000);

        String emailContent = MailReader.readEmail(emailConfig);
        String confirmationUrl = UrlExtractor.ConfirmationUrlResetPassword(emailContent);
        Constant.WEBDRIVER.get(confirmationUrl);

        Assert.assertEquals(passwordChangePage.getLblHeaderText(), "Password Change Form", "Header is not displayed as expected");
        passwordChangePage.resetPassword(newPassword, confirmPassword);

        softAssert.assertEquals(passwordChangePage.getLblErrorMessageText(), "Could not reset password. Please correct the errors and try again.", "Error message is not displayed as expected");
        softAssert.assertEquals(passwordChangePage.getLblErrorMessageConfirmPasswordFieldText(),"The password confirmation did not match the new password.", "Error message is not displayed as expected");
        softAssert.assertAll();
    }

}
