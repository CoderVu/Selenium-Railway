package org.example.PageObjects;
import org.example.Common.constants.constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class ForgotPasswordPage {
    // Locators
    private final By _txtEmail = By.id("email");
    private final By _btnSendInstructions = By.xpath("//input[@value='Send Instructions']");

    // Elements
    protected WebElement getTxtEmail() {
        return constant.WEBDRIVER.findElement(_txtEmail);
    }
    protected WebElement getBtnSendInstructions() {
        return constant.WEBDRIVER.findElement(_btnSendInstructions);
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


