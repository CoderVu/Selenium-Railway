package org.example.PageObjects;

import org.example.Common.constants.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ChangePasswordPage {
    // Locators
    private final By _txtCurrentPassword = By.id("currentPassword");
    private final By _txtNewPassword = By.id("newPassword");
    private final By _txtConfirmPassword = By.id("confirmPassword");
    private final By _btnChangePassword = By.xpath("//input[@value='Change Password']");
    private final By _lblChangePasswordSuccessMessage = By.xpath("//p[@class='message success']");

    // Elements
    protected WebElement getTxtCurrentPassword() {
        return Constant.WEBDRIVER.findElement(_txtCurrentPassword);
    }

    protected WebElement getTxtNewPassword() {
        return Constant.WEBDRIVER.findElement(_txtNewPassword);
    }

    protected WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }

    protected WebElement getBtnChangePassword() {
        return Constant.WEBDRIVER.findElement(_btnChangePassword);
    }

    protected WebElement getLblChangePasswordSuccessMessage() {
        return Constant.WEBDRIVER.findElement(_lblChangePasswordSuccessMessage);
    }

    // Methods

    public ChangePasswordPage changePassword(String currentPassword, String newPassword, String confirmPassword) {
        EnterCurrentPassword(currentPassword);
        EnterNewPassword(newPassword);
        EnterConfirmPassword(confirmPassword);
        getBtnChangePassword().click();
        return this;
    }

    private void EnterCurrentPassword(String currentPassword) {
        getTxtCurrentPassword().sendKeys(currentPassword);
    }
    private void EnterNewPassword(String newPassword) {
        getTxtNewPassword().sendKeys(newPassword);
    }
    private void EnterConfirmPassword(String confirmPassword) {
        getTxtConfirmPassword().sendKeys(confirmPassword);
    }
    public String getSuccessMessage() {
        return getLblChangePasswordSuccessMessage().getText();
    }
}
