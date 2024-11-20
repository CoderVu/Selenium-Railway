package org.example.TestCases;

import org.example.Common.Constants.Constant;
import org.example.PageObjects.BookTicketPage;
import org.example.PageObjects.HomePage;
import org.example.PageObjects.LoginPage;
import org.example.PageObjects.MyTicketPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyTicketTest {
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
    public void TC16() {
        System.out.println("TC16: User can cancel a ticket\n");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);


        homePage.gotoBookTicketPage();
        BookTicketPage bookTicketPage = new BookTicketPage();
        String departFrom = "Sài Gòn";
        String arriveAt = "Nha Trang";
        String seatType = "Hard seat";
        String amount = "1";
        bookTicketPage.bookTicket(departFrom, arriveAt, seatType, amount);

        homePage.gotoMyTicketPage();
        MyTicketPage myTicketPage = new MyTicketPage();

        homePage.gotoMyTicketPage();
        myTicketPage.cancelTicket(departFrom, arriveAt, seatType, amount);
        myTicketPage.isTicketCanceled(departFrom, arriveAt, seatType, amount);

    }
}
