package org.example.PageObjects;

import org.example.Common.constants.constant;
import org.example.Common.util.clickButtonByScroll;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyTicketPage {
    // Locators
    private final By _lblHeader = By.xpath("//div[@id='content']/h1");
    // Elements

    // Methods
    public String getHeader() {
        return constant.WEBDRIVER.findElement(_lblHeader).getText();
    }

    public void cancelTicket(String departFrom, String arriveAt, String seatType, int ticketAmount) {
        WebElement cancelButton = constant.WEBDRIVER.findElement(By.xpath("//table//tr[td[text()='" + departFrom + "'] and td[text()='" + arriveAt + "'] and td[text()='" + seatType + "'] and td[text()='" + ticketAmount + "']]//input[@value='Cancel']"));
        clickButtonByScroll clickButtonByScroll = new clickButtonByScroll(constant.WEBDRIVER);
        clickButtonByScroll.click(cancelButton);
        WebDriverWait alertWait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(3));
        alertWait.until(ExpectedConditions.alertIsPresent());

        Alert alert = constant.WEBDRIVER.switchTo().alert();
        alert.accept();


    }
    public boolean isTicketDeleted(int ticketCountBeforeCancel, int ticketCountAfterCancel) {
        return ticketCountBeforeCancel - ticketCountAfterCancel == 1;
    }

    public int getTicketCount() {
        return constant.WEBDRIVER.findElements(By.xpath("//tr[td[text()]]")).size();
    }

}
