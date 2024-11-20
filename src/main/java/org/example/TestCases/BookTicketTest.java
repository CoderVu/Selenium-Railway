package org.example.TestCases;

import org.example.Common.Constants.Constant;
import org.example.PageObjects.BookTicketPage;
import org.example.PageObjects.HomePage;
import org.example.PageObjects.LoginPage;
import org.example.PageObjects.TimetablePage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookTicketTest {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void TC14(){
        System.out.println("TC14: User can book 1 ticket at a time\n");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        homePage.gotoBookTicketPage();

        BookTicketPage bookTicketPage = new BookTicketPage();

        String departFrom = "Sài Gòn";
        String arriveAt = "Nha Trang";
        String seatType = "Soft bed with air conditioner";
        String ticketAmount = "1";
        bookTicketPage.bookTicket(departFrom, arriveAt, seatType, ticketAmount);

        String actualMsg = bookTicketPage.getLblHeader().getText();
        String expectedMsg = "Ticket booked successfully!";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");

        // Check ticket information
        LocalDate threeDaysAfter = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = threeDaysAfter.format(formatter);
        bookTicketPage.isTicketInformationDisplayed(formattedDate, departFrom, arriveAt, seatType, ticketAmount);
        Assert.assertTrue(bookTicketPage.isTicketInformationDisplayed(formattedDate, departFrom, arriveAt, seatType, ticketAmount));

    }
    @Test
    public void TC15(){
        System.out.println("TC15: User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page\n");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        homePage.gotoTimetablePage();

        TimetablePage timetablePage = new TimetablePage();

        String departFrom = "Huế";
        String arriveAt = "Sài Gòn";
        timetablePage.bookTicket(departFrom, arriveAt);

        BookTicketPage bookTicketPage = new BookTicketPage();
        String actualMsg = bookTicketPage.getLblHeader().getText();
        String expectedMsg = "Book ticket";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");

        bookTicketPage.isDepartFromAndArriveAtCorrect();

    }
}
