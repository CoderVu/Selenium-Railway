package org.example.PageObjects;

import org.example.Common.constants.Constant;
import org.example.Common.util.ClickButton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

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
    public BookTicketPage bookTicket(LocalDate departDate, String departFrom, String arriveAt, String seatType, int ticketAmount) {
        selectDepartDate(departDate);
        selectDepartFrom(departFrom);
        selectArriveTo(arriveAt);
        selectSeatType(seatType);
        selectTicketAmount(ticketAmount);
        ClickButton clickButton = new ClickButton(Constant.WEBDRIVER);
        clickButton.click(getBtnBookTicket());
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

    public boolean isTicketInformationDisplayed(LocalDate date, String departStation, String arriveStation, String seatType, int amount) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        boolean isDateDisplayed = isElementDisplayed("//table//tr/td[text()='" + formattedDate + "']");
        boolean isDepartStationDisplayed = isElementDisplayed("//table//tr/td[text()='" + departStation + "']");
        boolean isArriveStationDisplayed = isElementDisplayed("//table//tr/td[text()='" + arriveStation + "']");
        boolean isSeatTypeDisplayed = isElementDisplayed("//table//tr/td[text()='" + seatType + "']");
        boolean isAmountDisplayed = isElementDisplayed("//table//tr/td[text()='" + amount + "']");

        return isDateDisplayed && isDepartStationDisplayed && isArriveStationDisplayed && isSeatTypeDisplayed && isAmountDisplayed;
    }
    private boolean isElementDisplayed(String xpath) {
        try {
            WebElement element = Constant.WEBDRIVER.findElement(By.xpath(xpath));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public Map<String, String> getDepartFromAndArriveAt() {
        WebElement departStationElement = Constant.WEBDRIVER.findElement(_selectedDepartStation);
        WebElement arriveStationElement = Constant.WEBDRIVER.findElement(_selectedArriveStation);
        Map<String, String> stationTexts = new HashMap<>();
        stationTexts.put("departStation", departStationElement.getText());
        stationTexts.put("arriveStation", arriveStationElement.getText());
        return stationTexts;
    }
    public String getLblHeaderText(){
        return  getLblHeader().getText();
    }


}
