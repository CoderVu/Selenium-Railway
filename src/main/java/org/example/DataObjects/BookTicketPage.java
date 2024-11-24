package org.example.PO;

import org.example.common.constants.Constant;
import org.example.common.util.ScrollClickHandler;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;

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
    public BookTicketPage bookTicket(LocalDate departDate, String departFrom, String arriveAt, String seatType, int ticketAmount){
        selectDepartDate(departDate);
                            selectStations(departFrom, arriveAt);
        selectSeatType(seatType);
        selectTicketAmount(ticketAmount);
        ScrollClickHandler ScrollClickHandler = new ScrollClickHandler(Constant.WEBDRIVER);
        ScrollClickHandler.click(getBtnBookTicket());
        return this;
    }

    private void selectDepartDate(LocalDate date) {
        new Select(getCmbDepartDate()).selectByVisibleText(date.format(Constant.DATE_FORMATTER));
    }
    public void selectStations(String departFromStation, String arriveStation) {

        WebElement arriveDropdown = getCmbArriveAt();
        int initialOptionCount = new Select(arriveDropdown).getOptions().size();
        System.out.println("Before selecting departFrom: " + initialOptionCount);

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

        new Select(getCmbDepartFrom()).selectByVisibleText(departFromStation);

        try {
            wait.until(ExpectedConditions.stalenessOf(arriveDropdown));
        } catch (TimeoutException e) {
            System.out.println("Stale exception: " + e.getMessage());
        }

        arriveDropdown = getCmbArriveAt();

        arriveDropdown = wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(arriveDropdown)
        ));

        int updatedOptionCount = new Select(arriveDropdown).getOptions().size();
        System.out.println("After selecting departFrom: " + updatedOptionCount);

        if (updatedOptionCount == 0) {
            wait.until(driver -> {
                WebElement updatedArriveDropdown = driver.findElement(By.name("ArriveStation"));
                Select dropdown = new Select(updatedArriveDropdown);
                return dropdown.getOptions().size() > 0; // đợi cho đến khi dropdown có nhiều hơn 0 option
            });
        }
        new Select(arriveDropdown).selectByVisibleText(arriveStation);
    }


    private void selectSeatType(String seatType) {
        new Select(getCmbSeatType()).selectByVisibleText(seatType);
    }

    private void selectTicketAmount(int amount) {
        new Select(getCmbTicketAmount()).selectByVisibleText(String.valueOf(amount));
    }

    public boolean isTicketInformationDisplayed(LocalDate date, String departStation, String arriveStation, String seatType, int amount) {
        String formattedDate = date.format(Constant.DATE_FORMATTER);
        String xpath = "//table//tr[td[text()='" + departStation + "'] and td[text()='" + arriveStation + "'] and td[text()='" + seatType + "'] and td[text()='" + formattedDate + "'] and td[text()='" + amount + "']]";
        return isElementDisplayed(xpath);
    }

    private boolean isElementDisplayed(String xpath) {
        try {
            WebElement element = Constant.WEBDRIVER.findElement(By.xpath(xpath));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getDepartFrom(){
        return Constant.WEBDRIVER.findElement(_selectedDepartStation).getText();
    }
    public String getArriveAt(){
        return Constant.WEBDRIVER.findElement(_selectedArriveStation).getText();
    }
    public String getLblHeaderText(){
        return  getLblHeader().getText();
    }


}
