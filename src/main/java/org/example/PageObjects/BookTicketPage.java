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

    // Elements
    protected WebElement getLblHeader() {
        return Constant.WEBDRIVER.findElement(_lblHeader);
    }
    protected WebElement getCmbDepartDate() {
        return Constant.WEBDRIVER.findElement(_cmbDepartDate);
    }
    protected WebElement getCmbDepartFrom() {
        return Constant.WEBDRIVER.findElement(_cmbDepartFrom);
    }
    protected WebElement getCmbArriveAt() {
        return Constant.WEBDRIVER.findElement(_cmbArriveAt);
    }
    protected WebElement getCmbSeatType() {
        return Constant.WEBDRIVER.findElement(_cmbSeatType);
    }
    protected WebElement getCmbTicketAmount() {
        return Constant.WEBDRIVER.findElement(_cmbTicketAmount);
    }
    protected WebElement getBtnBookTicket() {
        return Constant.WEBDRIVER.findElement(_btnBookTicket);
    }

    // Methods
    public BookTicketPage bookTicket(LocalDate departDate, String departFrom, String arriveAt, String seatType, int ticketAmount) {
        selectDepartDate(departDate);
        selectDepartFrom(departFrom);
        selectArriveTo(arriveAt);
        selectSeatType(seatType);
        selectTicketAmount(ticketAmount);
        return this;
    }

    private void selectDepartDate(LocalDate date) {
        new Select(getCmbDepartDate()).selectByVisibleText(date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
    }

    private void selectDepartFrom(String station) {
        new Select(getCmbDepartFrom()).selectByVisibleText(station);

    }

    private void selectArriveTo(String station) {
        new Select(getCmbArriveAt()).selectByVisibleText(station);

    }

    private void selectSeatType(String seatType) {
        new Select(getCmbSeatType()).selectByVisibleText(seatType);

    }

    private void selectTicketAmount(int amount) {
        new Select(getCmbTicketAmount()).selectByVisibleText(String.valueOf(amount));

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
