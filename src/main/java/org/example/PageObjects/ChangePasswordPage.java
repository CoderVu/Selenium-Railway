package org.example.PageObjects;
import org.example.Common.constants.constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ChangePasswordPage {
    // Locators
    private final By _lblHeader = By.xpath("//*[@id='content']/h1");
    private final By _txtCurrentPassword = By.id("currentPassword");
    private final By _txtNewPassword = By.id("newPassword");
    private final By _txtConfirmPassword = By.id("confirmPassword");
    private final By _btnChangePassword = By.xpath("//input[@value='Change Password']");
    private final By _lblChangePasswordSuccessMessage = By.xpath("//p[@class='message success']");

    // Elements
    protected WebElement getTxtCurrentPassword() {
        return constant.WEBDRIVER.findElement(_txtCurrentPassword);
    }
    protected WebElement getTxtNewPassword() {
        return constant.WEBDRIVER.findElement(_txtNewPassword);
    }
    protected WebElement getTxtConfirmPassword() {
        return constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }
    protected WebElement getBtnChangePassword() {
        return constant.WEBDRIVER.findElement(_btnChangePassword);
    }
    protected WebElement getLblChangePasswordSuccessMessage() {
        return constant.WEBDRIVER.findElement(_lblChangePasswordSuccessMessage);
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
    public String getHeader() {
        return constant.WEBDRIVER.findElement(_lblHeader).getText();
    }
}
