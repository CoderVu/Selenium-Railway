package org.example.PO;

import org.example.common.constants.Constant;
import org.example.common.util.ScrollClickHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
    protected WebElement getLblRegisterSuccessMessage() {
        return Constant.WEBDRIVER.findElement(_lblRegisterSuccessMessage);
    }
    protected WebElement getLblRegisterErrorMessage() {
        return Constant.WEBDRIVER.findElement(_lblRegisterErrorMessage);
    }
    protected WebElement getLblPasswordErrorMessage() {
        return Constant.WEBDRIVER.findElement(_lblPasswordErrorMessage);
    }
    protected WebElement getLblPIDErrorMessage() {
        return Constant.WEBDRIVER.findElement(_lblPIDErrorMessage);
    }
    protected WebElement getBtnRegister() {
        return Constant.WEBDRIVER.findElement(_btnRegister);
    }

    // Methods
    public RegisterPage register(String email, String password, String confirmPassword, String pid) {
        EnterEmail(email);
        EnterPassword(password);
        EnterConfirmPassword(confirmPassword);
        EnterPID(pid);

        ScrollClickHandler ScrollClickHandler = new ScrollClickHandler(Constant.WEBDRIVER);
        ScrollClickHandler.click(getBtnRegister());
        return this;
    }
    private void EnterEmail(String email) {
        getTxtEmail().sendKeys(email);
    }
    private void EnterPassword(String password) {
        getTxtPassword().sendKeys(password);
    }
    private void EnterConfirmPassword(String confirmPassword) {
        getTxtConfirmPassword().sendKeys(confirmPassword);
    }
    private void EnterPID(String pid) {
        getTxtPID().sendKeys(pid);
    }
    public String getLblRegisterSuccessMessageText() {
        return getLblRegisterSuccessMessage().getText();
    }
    public String getLblRegisterErrorMessageText() {
        return getLblRegisterErrorMessage().getText();
    }
    public String getLblPasswordErrorMessageText() {
        return getLblPasswordErrorMessage().getText();
    }
    public String getLblPIDErrorMessageText() {
        return getLblPIDErrorMessage().getText();
    }
}
