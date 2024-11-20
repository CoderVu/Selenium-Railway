package org.example.PageObjects;

import org.example.Common.Constants.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.Duration.ofSeconds;

public class BookTicketPage {
    // Locators
    private final By _lblHeader = By.xpath("//div[@id='content']/h1");
    private final By _cmbDepartDate = By.name("Date");
    private final By _cmbDepartFrom = By.name("DepartStation");
    private final By _cmbArriveAt = By.name("ArriveStation");
    private final By _cmbSeatType = By.name("SeatType");
    private final By _cmbTicketAmount = By.name("TicketAmount");
    private final By _btnBookTicket = By.xpath("//input[@value='Book ticket']");
//    private final By _departStation = By.xpath("//table//tr/td[text()='Sài Gòn']");
//    private final By _arriveStation = By.xpath("//table//tr/td[text()='Phan Thiết']");
//    private final By _seatType = By.xpath("//table//tr/td[text()='Soft bed with air conditioner']");
//    private final By _amount = By.xpath("//table//tr/td[text()='1']");
//    private final By _date = By.xpath("//table//tr/td[text()='20-11-2024']");

    // Elements
    public WebElement getLblHeader() {
        return Constant.WEBDRIVER.findElement(_lblHeader);
    }
    public WebElement getCmbDepartDate() {
        return Constant.WEBDRIVER.findElement(_cmbDepartDate);
    }
    public WebElement getCmbDepartFrom() {
        return Constant.WEBDRIVER.findElement(_cmbDepartFrom);
    }
    public WebElement getCmbArriveAt() {
        return Constant.WEBDRIVER.findElement(_cmbArriveAt);
    }
    public WebElement getCmbSeatType() {
        return Constant.WEBDRIVER.findElement(_cmbSeatType);
    }
    public WebElement getCmbTicketAmount() {
        return Constant.WEBDRIVER.findElement(_cmbTicketAmount);
    }
    public WebElement getBtnBookTicket() {
        return Constant.WEBDRIVER.findElement(_btnBookTicket);
    }


    // Methods
    public BookTicketPage bookTicket(String departFrom, String arriveAt, String seatType, String ticketAmount) {
        LocalDate currentDate = LocalDate.now();
        LocalDate threeDaysAfter = currentDate.plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = threeDaysAfter.format(formatter);
        new Select(getCmbDepartDate()).selectByVisibleText(formattedDate);
        new Select(getCmbDepartFrom()).selectByVisibleText(departFrom);

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        new Select(getCmbArriveAt()).selectByVisibleText(arriveAt);
        new Select(getCmbSeatType()).selectByVisibleText(seatType);
        new Select(getCmbTicketAmount()).selectByVisibleText(ticketAmount);

        WebElement bookTicketButton = wait.until(ExpectedConditions.elementToBeClickable(_btnBookTicket));
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);",  bookTicketButton);
        bookTicketButton.click();
        return this;
    }
    public boolean isTicketInformationDisplayed(String DepartDate ,String departStation, String arriveStation, String seatType, String amount) {
        WebElement dateElement = Constant.WEBDRIVER.findElement(By.xpath("//table//tr/td[text()='" + DepartDate + "']"));
        WebElement departStationElement = Constant.WEBDRIVER.findElement(By.xpath("//table//tr/td[text()='" + departStation + "']"));
        WebElement arriveStationElement = Constant.WEBDRIVER.findElement(By.xpath("//table//tr/td[text()='" + arriveStation + "']"));
        WebElement seatTypeElement = Constant.WEBDRIVER.findElement(By.xpath("//table//tr/td[text()='" + seatType + "']"));
        WebElement amountElement = Constant.WEBDRIVER.findElement(By.xpath("//table//tr/td[text()='" + amount + "']"));
        return dateElement.isDisplayed() && departStationElement.isDisplayed() && arriveStationElement.isDisplayed() && seatTypeElement.isDisplayed() && amountElement.isDisplayed();
    }

    public void isDepartFromAndArriveAtCorrect() {
        WebElement departStationElement = Constant.WEBDRIVER.findElement(By.xpath("//select[@name='DepartStation']//option[@selected='selected']"));
        WebElement arriveStationElement = Constant.WEBDRIVER.findElement(By.xpath("//select[@name='ArriveStation']//option[@selected='selected']"));
        Assert.assertEquals(departStationElement.getText(), "Huế");
        Assert.assertEquals(arriveStationElement.getText(), "Sài Gòn");
    }

}
