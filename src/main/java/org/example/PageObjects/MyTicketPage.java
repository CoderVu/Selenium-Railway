package org.example.PageObjects;

import org.example.Common.Constants.Constant;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyTicketPage {
    // Locators
    private final By _lblHeader = By.xpath("//div[@id='content']/h1");

    // Elements
    public By getLblHeader() {
        return _lblHeader;
    }

    // Methods
    public String getHeader() {
        return Constant.WEBDRIVER.findElement(_lblHeader).getText();
    }

    public boolean isMyTicketPageDisplayed() {
        return getHeader().equals("Manage ticket");
    }
    public void cancelTicket(String departFrom, String arriveAt, String seatType, String ticketAmount) {

        WebElement cancelButton = Constant.WEBDRIVER.findElement(By.xpath("//tr[td[text()='" + departFrom + "'] and td[text()='" + arriveAt + "'] and td[text()='" + seatType + "'] and td[text()='" + ticketAmount + "']]//input[@value='Cancel']"));
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement cancelTicketButton = wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);",  cancelTicketButton);
        cancelTicketButton.click();

     try {
        WebDriverWait alertWait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        alertWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = Constant.WEBDRIVER.switchTo().alert();
        alert.accept();
    } catch (Exception e) {
        System.out.println("No confirmation alert appeared: " + e.getMessage());
    }
}

    public void isTicketCanceled(String departFrom, String arriveAt, String seatType, String amount) {
        By ticket = By.xpath("//tr[td[text()='" + departFrom + "'] and td[text()='" + arriveAt + "'] and td[text()='" + seatType + "'] and td[text()='" + amount + "']]");
        new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOfElementLocated(ticket));
    }

}
