package org.example.PageObjects;

import org.example.Common.Constants.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TimetablePage {
    // Locators
    private final By _lblHeader = By.xpath("//div[@id='content']/h1");
    private final By _linkBookTicket = By.xpath("//tr[td[2]='Huế' and td[3]='Sài Gòn']//a[text()='book ticket']");

    // Elements
    public WebElement getLblHeader() {
        return Constant.WEBDRIVER.findElement(_lblHeader);
    }

    // Methods
    public TimetablePage bookTicket(String departFrom, String arriveAt) {
        String xpath = String.format("//tr[td[2]='%s' and td[3]='%s']//a[text()='book ticket']", departFrom, arriveAt);
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement bookTicketButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);",  bookTicketButton);
        bookTicketButton.click();
        return this;
    }
}
