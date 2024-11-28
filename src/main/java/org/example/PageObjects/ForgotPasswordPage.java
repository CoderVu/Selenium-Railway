package org.example.PageObjects;

import org.example.Common.constants.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage {
    // Locators
    private final By _txtEmail = By.id("email");
    private final By _btnSendInstructions = By.xpath("//input[@value='Send Instructions']");
    // Elements
    protected WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(_txtEmail);
    }

    protected WebElement getBtnSendInstructions() {
        return Constant.WEBDRIVER.findElement(_btnSendInstructions);
    }
    // Methods
    public ForgotPasswordPage sendInstructions(String email) {
        EnterEmail(email);
        getBtnSendInstructions().click();
        return this;
    }
    private void EnterEmail(String email) {
        getTxtEmail().sendKeys(email);
    }

}
