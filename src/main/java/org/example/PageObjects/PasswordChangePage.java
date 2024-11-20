package org.example.PageObjects;

import org.example.Common.Constants.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PasswordChangePage {
    // Locators
    private final By _txtNewPassword = By.id("newPassword");
    private final By _txtConfirmPassword = By.id("confirmPassword");
    private final By _txtPasswordResetToken = By.id("resetToken");
    private final By _btnResetPassword = By.xpath("//input[@value='Reset Password']");
    private final By _lblSuccessMessage = By.xpath("//p[@class='message success']");
    private final By _lblErrorMessageAboveForm = By.xpath("//p[@class='message error']");
    private final By _lblErrorMessageNextToTheConfirmPasswordField = By.xpath("//label[@for='confirmPassword' and @class='validation-error']");
    private final By _lblErrorMessageNextToTokenField = By.xpath("//label[@for='resetToken' and @class='validation-error']");
    private final By _lblHeader = By.xpath("//legend[normalize-space(text())='Password Change Form']");

    // Elements
    public WebElement getTxtNewPassword() {
        return Constant.WEBDRIVER.findElement(_txtNewPassword);
    }
    public WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }
    public WebElement getTxtPasswordResetToken() {
        return Constant.WEBDRIVER.findElement(_txtPasswordResetToken);
    }
    public WebElement getBtnResetPassword() {
        return Constant.WEBDRIVER.findElement(_btnResetPassword);
    }
    public WebElement getLblHeader() {
        return Constant.WEBDRIVER.findElement(_lblHeader);
    }
    public WebElement getLblSuccessMessage() {
        return Constant.WEBDRIVER.findElement(_lblSuccessMessage);
    }
    public WebElement getLblErrorMessageAboveForm() {
        return Constant.WEBDRIVER.findElement(_lblErrorMessageAboveForm);
    }
    public WebElement getLblErrorMessageNextToTheConfirmPasswordField() {
        return Constant.WEBDRIVER.findElement(_lblErrorMessageNextToTheConfirmPasswordField);
    }
    public WebElement getLblErrorMessageNextToTokenField() {
        return Constant.WEBDRIVER.findElement(_lblErrorMessageNextToTokenField);
    }

    // Methods
    public PasswordChangePage resetPassword(String newPassword, String confirmPassword, String passwordResetToken) {
        getTxtNewPassword().sendKeys(newPassword);
        getTxtConfirmPassword().sendKeys(confirmPassword);
        getTxtPasswordResetToken().sendKeys(passwordResetToken);
        getBtnResetPassword().click();
        return this;
    }
    public PasswordChangePage resetPassword(String newPassword, String confirmPassword) {
        getTxtNewPassword().sendKeys(newPassword);
        getTxtConfirmPassword().sendKeys(confirmPassword);
        getBtnResetPassword().click();
        return this;
    }

}