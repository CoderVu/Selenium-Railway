package org.example.TestCases;

import org.example.Common.Constants.Constant;
import org.example.Common.Util.MailConfig;
import org.example.PageObjects.HomePage;
import org.example.PageObjects.LoginPage;
import org.example.PageObjects.RegisterPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterTest {
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
    public void TC07() throws InterruptedException {
        System.out.println("TC07 - User can create new account");
        HomePage homePage = new HomePage();
        homePage.open();
        RegisterPage registerPage = homePage.gotoRegisterPage();
        Random random = new Random();

        String email = generateEmail(random);
        String password = generateRandomPassword(random, 8, 64);
        String pid = generateRandomPID(random, 8, 20);

        System.out.println("Generated Email: " + email);
        System.out.println("Generated Password: " + password);
        System.out.println("Generated PID: " + pid);

        String actualMsg = registerPage.register(email, password, password, pid).getLblRegisterSuccessMessage().getText();
        String expectedMsg = "Thank you for registering your account";
        Assert.assertEquals(actualMsg, expectedMsg, "Register success message is not displayed as expected");

        Thread.sleep(10000);

        String senderEmail = "thanhletraining03@gmail.com";
        String emailContent = MailConfig.ReadEmail(senderEmail);

        String confirmationUrl = extractConfirmationUrl(emailContent);
        Constant.WEBDRIVER.get(confirmationUrl);
    }

    @Test
    public void TC08 () {
        System.out.println("TC08: User can't login with an account hasn't been activated\n");

        HomePage homePage = new HomePage();
        homePage.open();
        RegisterPage registerPage = homePage.gotoRegisterPage();

        Random random = new Random();
        String email = generateEmail(random);
        String password = generateRandomPassword(random, 8, 64);
        String pid = generateRandomPID(random, 8, 20);

        registerPage.register(email, password, password, pid);

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(email, password);

        String actualErrorMessage = loginPage.getLblLoginErrorMsg().getText();
        String expectedErrorMessage = "Invalid username or password. Please try again.";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not displayed as expected");
    }

    @Test
    public void TC10 () {
        System.out.println("TC10: User can't create account with 'Confirm password' is not the same with 'Password'\n");

        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();
        Random random = new Random();
        String email = generateEmail(random);
        String password = generateRandomPassword(random, 8, 57);
        String pid = generateRandomPID(random, 8, 20);
        String confirmPassword = generateRandomPassword(random, 8, 57);

        registerPage.register(email, password, confirmPassword, pid);
        String actualErrorMessage = registerPage.getLblRegisterErrorMessage().getText();
        String expectedErrorMessage = "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not displayed as expected");
    }

    @Test
    public void TC11 () {
        System.out.println("TC11: User can't create account while password and PID fields are empty\n");

        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();
        Random random = new Random();
        String email = generateEmail(random);
        String password = "";
        String pid = "";

        registerPage.register(email, password, password, pid);
        try {
            String actualErrorMessage = registerPage.getLblRegisterErrorMessage().getText();
            String expectedErrorMessage = "There're errors in the form. Please correct the errors and try again.";
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not displayed as expected");
        } catch (AssertionError e) {
            System.out.println("Error with Register Error Message: " + e.getMessage());
        }

        try {
            String actualPasswordErrorMessage = registerPage.getLblPasswordErrorMessage().getText();
            String expectedPasswordErrorMessage = "Invalid password length.";
            Assert.assertEquals(actualPasswordErrorMessage, expectedPasswordErrorMessage, "Password error message is not displayed as expected");
        } catch (AssertionError e) {
            System.out.println("Error with Password Error Message: " + e.getMessage());
        }

        try {
            String actualPidErrorMessage = registerPage.getLblPIDErrorMessage().getText();
            String expectedPidErrorMessage = "Invalid ID length.";
            Assert.assertEquals(actualPidErrorMessage, expectedPidErrorMessage, "PID error message is not displayed as expected");
        } catch (AssertionError e) {
            System.out.println("Error with PID Error Message: " + e.getMessage());
        }
    }

    private String generateEmail(Random random) {
        int randomNumber = 100 + random.nextInt(900);
        return "vunguyen.17082003+" + randomNumber + "@gmail.com";
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


    private String generateRandomPID(Random random, int minLength, int maxLength) {
        int pidLength = minLength + random.nextInt(maxLength - minLength + 1);
        StringBuilder pid = new StringBuilder();
        for (int i = 0; i < pidLength; i++) {
            pid.append(random.nextInt(10));
        }
        return pid.toString();
    }

    private String extractConfirmationUrl(String emailContent) {

        String urlPattern = "http://www\\.saferailway\\.somee\\.com/Account/Confirm\\?confirmationCode=[a-zA-Z0-9%\\-=_&]+";
        Pattern pattern = Pattern.compile(urlPattern);
        Matcher matcher = pattern.matcher(emailContent);

        if (matcher.find()) {
            return matcher.group();
        }
        throw new RuntimeException("Confirmation URL not found in the email content");
    }
}
