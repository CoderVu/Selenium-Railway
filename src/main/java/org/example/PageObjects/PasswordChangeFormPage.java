package org.example.PageObjects;

import org.example.Common.constants.Constant;
import org.example.Common.util.MailReader;
import org.example.Common.util.UrlExtractor;
import org.example.Config.EmailConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordChangeFormPage {
    // Objects
    MailReader MailReader = new MailReader();
    EmailConfig emailConfig = new EmailConfig();
    // Locators
    private final By _lblHeader = By.xpath("//legend");
    private final By _txtNewPassword = By.id("newPassword");
    private final By _txtConfirmPassword = By.id("confirmPassword");
    private final By _txtPasswordResetToken = By.id("resetToken");
    private final By _btnResetPassword = By.xpath("//input[@value='Reset Password']");
    private final By _lblErrorMessage = By.xpath("//p[@class='message error']");
    private final By _lblErrorMessageConfirmPasswordField = By.xpath("//label[@for='confirmPassword' and @class='validation-error']");
    private final By _lblErrorMessageTokenField = By.xpath("//label[@for='resetToken' and @class='validation-error']");

    // Elements
    protected WebElement getTxtNewPassword() {
        return Constant.WEBDRIVER.findElement(_txtNewPassword);
    }

    protected WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }

    protected WebElement getTxtPasswordResetToken() {
        return Constant.WEBDRIVER.findElement(_txtPasswordResetToken);
    }

    protected WebElement getBtnResetPassword() {
        return Constant.WEBDRIVER.findElement(_btnResetPassword);
    }

    protected WebElement getLblHeader() {
        return Constant.WEBDRIVER.findElement(_lblHeader);
    }

    protected WebElement getLblErrorMessage() {
        return Constant.WEBDRIVER.findElement(_lblErrorMessage);
    }

    protected WebElement getLblErrorMessageConfirmPasswordField() {
        return Constant.WEBDRIVER.findElement(_lblErrorMessageConfirmPasswordField);
    }

    protected WebElement getLblErrorMessageTokenField() {
        return Constant.WEBDRIVER.findElement(_lblErrorMessageTokenField);
    }

    // Methods
    public PasswordChangeFormPage resetPassword(String newPassword, String confirmPassword, String passwordResetToken) {
        String emailContent = MailReader.readEmail(emailConfig);
        String resetPasswordUrl = UrlExtractor.ConfirmationUrlResetPassword(emailContent);
        Constant.WEBDRIVER.get(resetPasswordUrl);
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(30));
        wait.until(webDriver -> Constant.WEBDRIVER.getCurrentUrl().equals(resetPasswordUrl));
        EnterPasswordResetToken(passwordResetToken);
        EnterNewPassword(newPassword);
        EnterConfirmPassword(confirmPassword);
        getBtnResetPassword().click();
        return this;
    }
    public PasswordChangeFormPage resetPassword(String newPassword, String confirmPassword) {
        String emailContent = MailReader.readEmail(emailConfig);
        String resetPasswordUrl = UrlExtractor.ConfirmationUrlResetPassword(emailContent);
        Constant.WEBDRIVER.get(resetPasswordUrl);
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(30));
        wait.until(webDriver -> Constant.WEBDRIVER.getCurrentUrl().equals(resetPasswordUrl));
        EnterNewPassword(newPassword);
        EnterConfirmPassword(confirmPassword);
        getBtnResetPassword().click();
        return this;
    }

    private void EnterNewPassword(String newPassword) {
        getTxtNewPassword().sendKeys(newPassword);
    }

    private void EnterConfirmPassword(String confirmPassword) {
        getTxtConfirmPassword().sendKeys(confirmPassword);
    }

    private void EnterPasswordResetToken(String passwordResetToken) {
        getTxtPasswordResetToken().sendKeys(passwordResetToken);
    }

    public String getLblHeaderText() {
        return getLblHeader().getText();
    }

    public String getLblErrorMessageText() {
        return getLblErrorMessage().getText();
    }

    public String getLblErrorMessageConfirmPasswordFieldText() {
        return getLblErrorMessageConfirmPasswordField().getText();
    }

    public String getLblErrorMessageTokenFieldText() {
        return getLblErrorMessageTokenField().getText();
    }

}
