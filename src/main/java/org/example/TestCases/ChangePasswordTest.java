package org.example.TestCases;

import org.example.Common.constants.constant;
import org.example.DataObjects.DataTest;
import org.example.PageObjects.ChangePasswordPage;
import org.example.PageObjects.HomePage;
import org.example.PageObjects.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordTest {
    HomePage homePage;
    LoginPage loginPage;
    ChangePasswordPage changePasswordPage;

    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        changePasswordPage = new ChangePasswordPage();

        System.out.println("Pre-condition");
        constant.WEBDRIVER = new ChromeDriver();
        constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        constant.WEBDRIVER.quit();
    }

    @Test(description = "TC09 - User can change password", dataProvider = "change_password_data", dataProviderClass = DataTest.class)
    public void TC09 (String newPassword, String confirmPassword) {

        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(constant.USERNAMEACTIVE, constant.PASSWORD);

        homePage.gotoChangePasswordPage();
        changePasswordPage.changePassword(constant.PASSWORD, newPassword, confirmPassword);

        Assert.assertEquals(changePasswordPage.getSuccessMessage(), "Your password has been updated!", "Error message is not displayed as expected");
    }
}
