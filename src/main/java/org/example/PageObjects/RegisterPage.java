package org.example.PageObjects;

import org.example.Common.Constants.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    // Locators
    private final By _txtEmail = By.id("email");
    private final By _txtPassword = By.id("password");
    private final By _txtConfirmPassword = By.id("confirmPassword");
    private final By _txtPID = By.id("pid");
    private final By _btnRegister = By.xpath("//input[@value='Register']");
    private final By _lblRegisterSuccessMessage = By.xpath("//*[@id='content']/h1");
    private final By _lblRegisterErrorMessage = By.xpath("//p[@class='message error']");
    private final By _lblPasswordErrorMessage = By.xpath("//label[@for='password' and @class='validation-error']");
    private final By _lblPIDErrorMessage = By.xpath("//label[@for='pid' and @class='validation-error']");

    // Elements
    protected WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(_txtEmail);
    }
    protected WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }
    protected WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }
    protected WebElement getTxtPID() {
        return Constant.WEBDRIVER.findElement(_txtPID);
    }
    public WebElement getLblRegisterSuccessMessage() {
        return Constant.WEBDRIVER.findElement(_lblRegisterSuccessMessage);
    }
    public WebElement getLblRegisterErrorMessage() {
        return Constant.WEBDRIVER.findElement(_lblRegisterErrorMessage);
    }
    public WebElement getLblPasswordErrorMessage() {
        return Constant.WEBDRIVER.findElement(_lblPasswordErrorMessage);
    }
    public WebElement getLblPIDErrorMessage() {
        return Constant.WEBDRIVER.findElement(_lblPIDErrorMessage);
    }

    // Methods
    public RegisterPage register(String email, String password, String confirmPassword, String pid) {
        getTxtEmail().sendKeys(email);
        getTxtPassword().sendKeys(password);
        getTxtConfirmPassword().sendKeys(confirmPassword);
        getTxtPID().sendKeys(pid);


        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(_btnRegister));

        // Cuộn xuống cuối trang để click vào nút Register
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", registerButton);

        registerButton.click();
        return this;
    }
}
