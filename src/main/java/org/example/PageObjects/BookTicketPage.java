package org.example.PageObjects;

import org.example.Common.constants.constant;
import org.example.Common.util.clickButtonByScroll;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    private final By _selectedDepartStation = By.xpath("//select[@name='DepartStation']//option[@selected='selected']");
    private final By _selectedArriveStation = By.xpath("//select[@name='ArriveStation']//option[@selected='selected']");

    // Elements
    protected WebElement getLblHeader() {
        return constant.WEBDRIVER.findElement(_lblHeader);
    }
    protected WebElement getCmbDepartDate() {
        return constant.WEBDRIVER.findElement(_cmbDepartDate);
    }
    protected WebElement getCmbDepartFrom() {
        return constant.WEBDRIVER.findElement(_cmbDepartFrom);
    }
    protected WebElement getCmbArriveAt() {
        return constant.WEBDRIVER.findElement(_cmbArriveAt);
    }
    protected WebElement getCmbSeatType() {
        return constant.WEBDRIVER.findElement(_cmbSeatType);
    }
    protected WebElement getCmbTicketAmount() {
        return constant.WEBDRIVER.findElement(_cmbTicketAmount);
    }
    protected WebElement getBtnBookTicket() {
        return constant.WEBDRIVER.findElement(_btnBookTicket);
    }

    // Methods
    public BookTicketPage bookTicket(LocalDate departDate, String departFrom, String arriveAt, String seatType, int ticketAmount) throws InterruptedException {
        selectDepartDate(departDate);
        selectDepartFrom(departFrom);
        selectArriveTo(arriveAt);
        selectSeatType(seatType);
        selectTicketAmount(ticketAmount);
        clickButtonByScroll clickButtonByScroll = new clickButtonByScroll(constant.WEBDRIVER);
        clickButtonByScroll.click(getBtnBookTicket());
        return this;
    }

    private void selectDepartDate(LocalDate date) {
        new Select(getCmbDepartDate()).selectByVisibleText(date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
    }
    private void selectDepartFrom(String station) {
        new Select(getCmbDepartFrom()).selectByVisibleText(station);
    }
    public void selectArriveTo(String arriveStation) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.stalenessOf(getCmbArriveAt()));

        WebElement arriveDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("ArriveStation")));
        // Cho truong hop no chon departFrom xong doi 2-3s moi render ra departAt
        wait.until(driver -> {
            Select dropdown = new Select(arriveDropdown);
            return dropdown.getOptions().size() > 1;
        });

        Select arriveSelect = new Select(arriveDropdown);
        arriveSelect.selectByVisibleText(arriveStation);

        wait.until(driver -> arriveSelect.getFirstSelectedOption().getText().equals(arriveStation));
    }

    private void selectSeatType(String seatType) {
        new Select(getCmbSeatType()).selectByVisibleText(seatType);
    }

    private void selectTicketAmount(int amount) {
        new Select(getCmbTicketAmount()).selectByVisibleText(String.valueOf(amount));
    }

    public boolean isTicketInformationDisplayed(LocalDate date, String departStation, String arriveStation, String seatType, int amount) {
        String formattedDate = date.format(constant.DATE_FORMATTER);
        String xpath = "//table//tr[td[text()='" + departStation + "'] and td[text()='" + arriveStation + "'] and td[text()='" + seatType + "'] and td[text()='" + formattedDate + "'] and td[text()='" + amount + "']]";
        return isElementDisplayed(xpath);
    }

    private boolean isElementDisplayed(String xpath) {
        try {
            WebElement element = constant.WEBDRIVER.findElement(By.xpath(xpath));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getDepartFrom(){
        return constant.WEBDRIVER.findElement(_selectedDepartStation).getText();
    }
    public String getArriveAt(){
        return constant.WEBDRIVER.findElement(_selectedArriveStation).getText();
    }
    public String getLblHeaderText(){
        return  getLblHeader().getText();
    }


}
