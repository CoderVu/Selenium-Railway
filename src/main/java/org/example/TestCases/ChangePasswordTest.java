package org.example.TestCases;

import org.example.Common.constants.Constant;
import org.example.Common.util.DataTest;
import org.example.Common.util.Generate;
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
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }

    @Test(description = "TC09 - User can change password", dataProvider = "ChangePasswordData", dataProviderClass = DataTest.class)
    public void TC09 (String newPassword, String confirmPassword) {

        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAMEACTIVE, Constant.PASSWORD);

        homePage.gotoChangePasswordPage();
        changePasswordPage.changePassword(Constant.PASSWORD, newPassword, confirmPassword);

        Assert.assertEquals(changePasswordPage.getSuccessMessage(), "Your password has been updated!", "Error message is not displayed as expected");
    }
}
