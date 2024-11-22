package org.example.PageObjects;

import org.example.Common.constants.Constant;
import org.example.Common.util.ClickButtonByScroll;
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
        return Constant.WEBDRIVER.findElement(_lblHeader).getText();
    }

public void cancelTicket(String departFrom, String arriveAt, String seatType, int ticketAmount) {
    WebElement cancelButton = Constant.WEBDRIVER.findElement(By.xpath("//table//tr[td[text()='" + departFrom + "'] and td[text()='" + arriveAt + "'] and td[text()='" + seatType + "'] and td[text()='" + ticketAmount + "']]//input[@value='Cancel']"));
    ClickButtonByScroll clickButtonByScroll = new ClickButtonByScroll(Constant.WEBDRIVER);
    clickButtonByScroll.click(cancelButton);
    WebDriverWait alertWait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(3));
    alertWait.until(ExpectedConditions.alertIsPresent());

    Alert alert = Constant.WEBDRIVER.switchTo().alert();
    alert.accept();


}
    public boolean isTicketDeleted(int ticketCountBeforeCancel, int ticketCountAfterCancel) {
        return ticketCountBeforeCancel - ticketCountAfterCancel == 1;
    }

    public int getTicketCount() {
        return Constant.WEBDRIVER.findElements(By.xpath("//tr[td[text()]]")).size();
    }

}
