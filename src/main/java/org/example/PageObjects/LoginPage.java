package org.example.PageObjects;

import org.example.Common.constants.Constant;
import org.example.Common.util.ScrollClickHandler;
import org.example.DataObjects.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {
    // Objects
    ScrollClickHandler scrollClickHandler;
    // Locators
    private final By _txtUsername = By.id("username");
    private final By _txtPassword = By.id("password");
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

    protected WebElement getLblLoginErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
    }

    protected WebElement getLblLoginPage() {
        return Constant.WEBDRIVER.findElement(_lblLoginPage);
    }

    protected WebElement getLinkForgotPassword() {
        return Constant.WEBDRIVER.findElement(_linkForgotPassword);
    }

    protected WebElement getBtnLogin() {
        return Constant.WEBDRIVER.findElement(_btnLogin);
    }

    // Methods
    public LoginPage login(Account account) {
        EnterUsername(account.getEmail());
        EnterPassword(account.getPassword());
        scrollClickHandler = new ScrollClickHandler(Constant.WEBDRIVER);
        scrollClickHandler.click(getBtnLogin());
        return this;
    }

    public LoginPage gotoForgotPasswordPage() {
        getLinkForgotPassword().click();
        return this;
    }

    private void EnterUsername(String username) {
        getTxtUsername().sendKeys(username);
    }

    private void EnterPassword(String password) {
        getTxtPassword().sendKeys(password);
    }

    public String getLoginErrorMsg() {
        return getLblLoginErrorMsg().getText();
    }

    public String getLoginPage() {
        return getLblLoginPage().getText();
    }

}
