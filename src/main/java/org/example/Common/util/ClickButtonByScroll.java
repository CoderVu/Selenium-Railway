package org.example.Common.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickButtonByScroll {

    private WebDriver driver;

    public ClickButtonByScroll(WebDriver driver) {
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
    public void click(String xpath){
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click the button: " + e.getMessage(), e);
        }
    }

}
