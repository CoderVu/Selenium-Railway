package org.example.PageObjects;

import org.example.Common.Constants.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    // Locators
    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@value='login']");
    private final By _lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
    private final By _lblLoginPage = By.xpath("//*[@id='content']/h1");
    private final By _linkForgotPassword = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");


    // Elements
    protected WebElement getTxtUsername() {
        return Constant.WEBDRIVER.findElement(_txtUsername);
    }
    protected WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }
    protected WebElement getBtnLogin() {
        return Constant.WEBDRIVER.findElement(_btnLogin);
    }
    public WebElement getLblLoginErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
    }
    public WebElement getLblLoginPage() {
        return Constant.WEBDRIVER.findElement(_lblLoginPage);
    }
    public WebElement getLinkForgotPassword() {
        return Constant.WEBDRIVER.findElement(_linkForgotPassword);
    }

    // Methods
    public LoginPage login(String username, String password) {
        getTxtUsername().sendKeys(username);
        getTxtPassword().sendKeys(password);

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(_btnLogin));

        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", loginButton);

        loginButton.click();
        return this;
    }
    public LoginPage gotoForgotPasswordPage() {
        getLinkForgotPassword().click();
        return this;
    }
}
