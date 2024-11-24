package org.example.PO;

import org.example.common.constants.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PasswordChangePage {
    // Locators
    private final By _txtNewPassword = By.id("newPassword");
    private final By _txtConfirmPassword = By.id("confirmPassword");
    private final By _txtPasswordResetToken = By.id("resetToken");
    private final By _btnResetPassword = By.xpath("//input[@value='Reset Password']");
    private final By _lblErrorMessage = By.xpath("//p[@class='message error']");
    private final By _lblErrorMessageConfirmPasswordField = By.xpath("//label[@for='confirmPassword' and @class='validation-error']");
    private final By _lblErrorMessageTokenField = By.xpath("//label[@for='resetToken' and @class='validation-error']");
    private final By _lblHeader = By.xpath("//legend[normalize-space(text())='Password Change Form']");

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
    public PasswordChangePage resetPassword(String newPassword, String confirmPassword, String passwordResetToken) {
        EnterNewPassword(newPassword);
        EnterConfirmPassword(confirmPassword);
        EnterPasswordResetToken(passwordResetToken);
        getBtnResetPassword().click();
        return this;
    }
    public PasswordChangePage resetPassword(String newPassword, String confirmPassword) {
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