package org.example.PageObjects;

import org.example.Common.constants.Constant;
import org.example.Common.utils.ScrollClickHandler;
import org.example.DataObjects.Ticket;
import org.example.DataTypes.SeatType;
import org.example.DataTypes.Station;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;

public class BookTicketPage {
        // Locators

        private final By _cmbDepartDate = By.name("Date");
        private final By _cmbDepartFrom = By.name("DepartStation");
        private final By _cmbArriveAt = By.name("ArriveStation");
        private final By _cmbSeatType = By.name("SeatType");
        private final By _cmbTicketAmount = By.name("TicketAmount");
        private final By _btnBookTicket = By.xpath("//input[@value='Book ticket']");
        private String dynamicTableCell = "//table//td[text()='%s'][@id='%s']";

        // Elements
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

        protected WebElement getBookSuccessTableRow(String text, String id) {
            return Constant.WEBDRIVER.findElement(By.xpath(String.format(dynamicTableCell, text, id)));
        }

        // Methods
        public String getBookingIdFromUrl() {
            String currentUrl = Constant.WEBDRIVER.getCurrentUrl();
            String id = currentUrl.substring(currentUrl.indexOf("id=") + 3);
            return id;
        }

        public BookTicketPage bookTicket(Ticket ticket) {
            selectDepartDate(ticket.getDepartDate());
            selectDepartFrom(ticket.getFrom());
            selectArriveAt(ticket.getTo());
            selectSeatType(ticket.getSeatType());
            selectTicketAmount(ticket.getTicketAmount());
            ScrollClickHandler ScrollClickHandler = new ScrollClickHandler(Constant.WEBDRIVER);
            ScrollClickHandler.click(getBtnBookTicket());
            return this;
        }
        public String BookAndGetId(Ticket ticket) {
            bookTicket(ticket);
            String bookingId = getBookingIdFromUrl();
            return bookingId;
        }


        private void selectDepartDate(LocalDate date) {
            new Select(getCmbDepartDate()).selectByVisibleText(date.format(Constant.DATE_FORMATTER));
        }
        private void selectDepartFrom(Station departFromStation) {
            new Select(getCmbDepartFrom()).selectByVisibleText(departFromStation.getStationName());
        }

        private void selectArriveAt(Station arriveStation) {
        WebElement arriveDropdown = getCmbArriveAt();
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
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
        if (updatedOptionCount == 0) {
            wait.until(driver -> {
                WebElement updatedArriveDropdown = driver.findElement(By.name("ArriveStation"));
                Select dropdown = new Select(updatedArriveDropdown);
                return dropdown.getOptions().size() > 0;
            });
        }
        new Select(arriveDropdown).selectByVisibleText(arriveStation.getStationName());
    }

    private void selectSeatType(SeatType seatType) {
        new Select(getCmbSeatType()).selectByVisibleText(seatType.getDescription());
    }

    private void selectTicketAmount(int amount) {
        new Select(getCmbTicketAmount()).selectByVisibleText(String.valueOf(amount));
    }

    public boolean isTicketInformationDisplayed(Ticket ticket) {
        String formattedDate = ticket.getDepartDate().format(Constant.DATE_FORMATTER);
        String xpath = String.format(
                "//table//tr[td[text()='%s'] and td[text()='%s'] and td[text()='%s'] and td[text()='%s'] and td[text()='%d']]",
                ticket.getFrom().getStationName(), ticket.getTo().getStationName(), ticket.getSeatType().getDescription(), formattedDate, ticket.getTicketAmount()
        );
        return isElementDisplayed(xpath);
    }

    private boolean isElementDisplayed(String xpath) {
        try {
            return Constant.WEBDRIVER.findElement(By.xpath(xpath)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getDepartFrom() {
        return new Select(getCmbDepartFrom()).getFirstSelectedOption().getText();
    }

    public String getArriveAt() {
        return new Select(getCmbArriveAt()).getFirstSelectedOption().getText();
    }
}


