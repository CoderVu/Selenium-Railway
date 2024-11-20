package org.example.TestCases;

import org.example.Common.Constants.Constant;
import org.example.PageObjects.ChangePasswordPage;
import org.example.PageObjects.HomePage;
import org.example.PageObjects.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordTest {
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
    public void TC09 () {
        System.out.println("TC09: User can change password\n");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        homePage.gotoChangePasswordPage();
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();


        String newPassword = generateRandomPassword();
        changePasswordPage.changePassword(Constant.PASSWORD, newPassword, newPassword);

        String actualMsg = changePasswordPage.getLblChangePasswordSuccessMessage().getText();
        String expectedMsg = "Your password has been updated!";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }

    private String generateRandomPassword() {
        String password = "12345678";
        return password;
    }
}
