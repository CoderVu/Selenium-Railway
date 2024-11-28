package org.example.PageObjects;

import org.example.Common.constants.Constant;
import org.example.Common.util.MailReader;
import org.example.Common.util.ScrollClickHandler;
import org.example.Common.util.UrlExtractor;
import org.example.Config.EmailConfig;
import org.example.DataObjects.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    // Objects
    EmailConfig emailConfig = new EmailConfig();
    ScrollClickHandler ScrollClickHandler;
    // Locators
    private final By _txtEmail = By.id("email");
    private final By _txtPassword = By.id("password");
    private final By _txtConfirmPassword = By.id("confirmPassword");
    private final By _txtPID = By.id("pid");
    private final By _btnRegister = By.xpath("//input[@value='Register']");
    private final By _lblRegisterSuccessMessage = By.xpath("//div[@id='content']/h1");
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
    public RegisterPage register(Account account) {
        EnterEmail(account.getEmail());
        EnterPassword(account.getPassword());
        EnterConfirmPassword(account.getConfirmPassword());
        EnterPID(account.getPid());
        ScrollClickHandler = new ScrollClickHandler(Constant.WEBDRIVER);
        ScrollClickHandler.click(getBtnRegister());
        return this;
    }
    public String registerConfirm(Account account) {
        register(account);
        String successMessage = getLblRegisterSuccessMessage().getText();
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(15));
        wait.until(driver -> {
            String emailContent = MailReader.readEmail(emailConfig);
            String confirmationUrl = UrlExtractor.ConfirmationUrlRegister(emailContent);
            if (confirmationUrl != null) {
                Constant.WEBDRIVER.navigate().to(confirmationUrl);
                return true;
            }
            return false;
        });
        return successMessage;
    }
    public void registerNotConfirm(Account account) {
        register(account);
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
