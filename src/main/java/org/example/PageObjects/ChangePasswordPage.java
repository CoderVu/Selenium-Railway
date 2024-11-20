package org.example.PageObjects;
import org.example.Common.Constants.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ChangePasswordPage {
    // Locators
    private final By _lblHeader = By.xpath("//div[@id='content']/h1");
    private final By _txtCurrentPassword = By.id("currentPassword");
    private final By _txtNewPassword = By.id("newPassword");
    private final By _txtConfirmPassword = By.id("confirmPassword");
    private final By _btnChangePassword = By.xpath("//input[@value='Change Password']");
    private final By _lblChangePasswordSuccessMessage = By.xpath("//p[@class='message success']");

    // Elements
    public WebElement getLblHeader() {
        return Constant.WEBDRIVER.findElement(_lblHeader);
    }
    public WebElement getTxtCurrentPassword() {
        return Constant.WEBDRIVER.findElement(_txtCurrentPassword);
    }
    public WebElement getTxtNewPassword() {
        return Constant.WEBDRIVER.findElement(_txtNewPassword);
    }
    public WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }
    public WebElement getBtnChangePassword() {
        return Constant.WEBDRIVER.findElement(_btnChangePassword);
    }
    public WebElement getLblChangePasswordSuccessMessage() {
        return Constant.WEBDRIVER.findElement(_lblChangePasswordSuccessMessage);
    }



    // Methods
    public String getHeader() {
        return Constant.WEBDRIVER.findElement(_lblHeader).getText();
    }

    public boolean isChangePasswordPageDisplayed() {
        return getHeader().equals("Change password");
    }
    public ChangePasswordPage changePassword(String currentPassword, String newPassword, String confirmPassword) {
        getTxtCurrentPassword().sendKeys(currentPassword);
        getTxtNewPassword().sendKeys(newPassword);
        getTxtConfirmPassword().sendKeys(confirmPassword);
        getBtnChangePassword().click();
        return this;
    }

}
