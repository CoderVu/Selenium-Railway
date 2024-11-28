package org.example.PageObjects;

import org.example.Common.constants.Constant;
import org.example.Common.utils.ScrollClickHandler;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyTicketPage {
    // Objects
    ScrollClickHandler scrollClickHandler;
    // Locators
    private final By _lblHeader = By.xpath("//div[@id='content']/h1");
    // Elements

    // Methods
    public String getHeader() {
        return Constant.WEBDRIVER.findElement(_lblHeader).getText();
    }

    public void cancelTicketById(String ticketId) {
        String xpath = String.format("//input[@type='button' and @value='Cancel' and contains(@onclick, '%s')]", ticketId);
        WebElement cancelButton = Constant.WEBDRIVER.findElement(By.xpath(xpath));
        scrollClickHandler = new ScrollClickHandler(Constant.WEBDRIVER);
        scrollClickHandler.click(cancelButton);
        WebDriverWait alertWait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(3));
        alertWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = Constant.WEBDRIVER.switchTo().alert();
        alert.accept();
    }


    public boolean isTicketIdExists(String ticketId) {
        String xpath = String.format("//input[@type='button' and @value='Cancel' and contains(@onclick, '%s')]", ticketId);
        try {
            WebElement ticketElement = Constant.WEBDRIVER.findElement(By.xpath(xpath));
            return ticketElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
