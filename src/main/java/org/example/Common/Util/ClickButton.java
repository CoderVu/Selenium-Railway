package org.example.Common.Util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickButton {

    private WebDriver driver;

    public ClickButton(WebDriver driver) {
        this.driver = driver;
    }

    public void click(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click the button: " + e.getMessage(), e);
        }
    }
}
