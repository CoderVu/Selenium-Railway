package org.example.PageObjects;
import org.example.Common.Constants.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class ForgotPasswordPage {
    // Locators
    private final By _txtEmail = By.id("email");
    private final By _btnSendInstructions = By.xpath("//input[@value='Send Instructions']");
    private final By _lblEmailErrorMessage = By.xpath("//label[@class='validation-error']");

    // Elements
    public WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(_txtEmail);
    }
    public WebElement getBtnSendInstructions() {
        return Constant.WEBDRIVER.findElement(_btnSendInstructions);
    }
    public WebElement getLblEmailErrorMessage() {
        return Constant.WEBDRIVER.findElement(_lblEmailErrorMessage);
    }

    // Methods
    public ForgotPasswordPage sendInstructions(String email) {
        getTxtEmail().sendKeys(email);
        getBtnSendInstructions().click();
        return this;
    }
}


