package org.example.TestCases;

import org.example.Common.Constants.Constant;
import org.example.Common.Util.MailConfig;
import org.example.PageObjects.ForgotPasswordPage;
import org.example.PageObjects.HomePage;
import org.example.PageObjects.LoginPage;
import org.example.PageObjects.PasswordChangePage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPasswordTest {

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void TC12() throws InterruptedException{
        System.out.println("TC12: Errors display when password reset token is blank\n");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.gotoForgotPasswordPage();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        forgotPasswordPage.sendInstructions(Constant.USERNAME);

        Thread.sleep(10000);

        String senderEmail = "thanhletraining03@gmail.com";
        String emailContent = MailConfig.ReadEmail(senderEmail);

        String confirmationUrl = extractConfirmationUrl(emailContent);
        Constant.WEBDRIVER.get(confirmationUrl);

        PasswordChangePage passwordChangePage = new PasswordChangePage();

        String newPassword = generatePassword();
        String confirmPassword = newPassword;
        String resetToken = "";
        passwordChangePage.resetPassword(newPassword, confirmPassword, resetToken);

        String actualMsg1 = passwordChangePage.getLblErrorMessageAboveForm().getText();
        String expectedMsg1 = "The password reset token is incorrect or may be expired. Visit the forgot password page to generate a new one.";
        Assert.assertEquals(actualMsg1, expectedMsg1, "Error message is not displayed as expected");

        String actualMsg2 = passwordChangePage.getLblErrorMessageNextToTokenField().getText();
        String expectedMsg2 = "The password reset token is invalid.";
        Assert.assertEquals(actualMsg2, expectedMsg2, "Error message is not displayed as expected");

        // Fail vi chi co success message

    }
    @Test
    public void TC13() throws InterruptedException {
        System.out.println("TC13: Errors display if password and confirm password don't match when resetting password\n");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.gotoForgotPasswordPage();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        forgotPasswordPage.sendInstructions(Constant.USERNAME);

        Thread.sleep(10000);

        String senderEmail = "thanhletraining03@gmail.com";
        String emailContent = MailConfig.ReadEmail(senderEmail);

        String confirmationUrl = extractConfirmationUrl(emailContent);
        Constant.WEBDRIVER.get(confirmationUrl);

        PasswordChangePage passwordChangePage = new PasswordChangePage();

        String actualMsg1 = passwordChangePage.getLblHeader().getText();
        String expectedMsg1 = "Password Change Form";
        Assert.assertEquals(actualMsg1, expectedMsg1, "Header is not displayed as expected");

        String newPassword = generatePassword();
        String confirmPassword = generateRandomPassword(new Random(), 8, 64);

        passwordChangePage.resetPassword(newPassword, confirmPassword);

        String actualMsg2 = passwordChangePage.getLblErrorMessageAboveForm().getText();
        String expectedMsg2 = "Could not reset password. Please correct the errors and try again.";
        Assert.assertEquals(actualMsg2, expectedMsg2, "Error message is not displayed as expected");

        String actualMsg3 = passwordChangePage.getLblErrorMessageNextToTheConfirmPasswordField().getText();
        String expectedMsg3 = "The password confirmation did not match the new password.";
        Assert.assertEquals(actualMsg3, expectedMsg3, "Error message is not displayed as expected");

    }

    private String generatePassword() {
        String password = "12345678";
        return password;
    }
    private String generateRandomPassword(Random random, int minLength, int maxLength) {
        int passwordLength = minLength + random.nextInt(maxLength - minLength + 1);
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < passwordLength; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }
    private String extractConfirmationUrl(String emailContent) {

        String urlPattern = "http://www\\.saferailway\\.somee\\.com/Account/PasswordReset\\?resetToken=[a-zA-Z0-9%\\-=_&]+";
        Pattern pattern = Pattern.compile(urlPattern);
        Matcher matcher = pattern.matcher(emailContent);

        if (matcher.find()) {
            return matcher.group(); // lấy ra đoạn url khớp với pattern
        }
        throw new RuntimeException("Confirmation URL not found in the email content");
    }
}
