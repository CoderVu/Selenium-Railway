package org.example.PageObjects;

import org.example.Common.constants.constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GeneralPage {
    // Locators
    private final By tabLogin = By.xpath("//a[@href='/Account/Login.cshtml']");
    private final By tabLogout = By.xpath("//a[@href='/Account/Logout']");
    private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
    private final By tabRegister = By.xpath("//a[@href='/Account/Register.cshtml']");
    private final By tabTimetable = By.xpath("//a[@href='TrainTimeListPage.cshtml']");
    private final By tabBookTicket = By.xpath("//a[@href='/Page/BookTicketPage.cshtml']");
    private final By myTicketTab = By.xpath("//a[@href='/Page/ManageTicket.cshtml']");
    private final By tabChangePassword = By.xpath("//a[@href='/Account/ChangePassword.cshtml']");

    // Elements
    protected WebElement getTabLogin() {
        return constant.WEBDRIVER.findElement(tabLogin);
    }
    protected WebElement getTabLogout() {
        return constant.WEBDRIVER.findElement(tabLogout);
    }
    protected WebElement getLblWelcomeMessage() {
        return constant.WEBDRIVER.findElement(lblWelcomeMessage);
    }
    protected WebElement getTabRegister() {
        return constant.WEBDRIVER.findElement(tabRegister);
    }
    protected WebElement getTabTimetable() {
        return constant.WEBDRIVER.findElement(tabTimetable);
    }
    protected WebElement getTabBookTicket() {
        return constant.WEBDRIVER.findElement(tabBookTicket);
    }
    protected WebElement getMyTicketTab() {
        return constant.WEBDRIVER.findElement(myTicketTab);
    }
    protected WebElement getTabChangePassword() {

        return constant.WEBDRIVER.findElement(tabChangePassword);
    }


    // Methods
    public String getWelcomeMessage() {
        return getLblWelcomeMessage().getText();
    }
    public LoginPage gotoLoginPage() {
        getTabLogin().click();
        return new LoginPage();
    }
    public RegisterPage gotoRegisterPage() {
        getTabRegister().click();
        return new RegisterPage();
    }
    public TimetablePage gotoTimetablePage() {
        getTabTimetable().click();
        return new TimetablePage();
    }
    public BookTicketPage gotoBookTicketPage() {
        getTabBookTicket().click();
        return new BookTicketPage();
    }
    public MyTicketPage gotoMyTicketPage() {
        getMyTicketTab().click();
        return new MyTicketPage();
    }

    public ChangePasswordPage gotoChangePasswordPage() {
        getTabChangePassword().click();
        return new ChangePasswordPage();
    }

    public boolean isMyTicketTabDisplayed() { return getMyTicketTab().isDisplayed() ? true : false;
    }


    public boolean isChangePasswordTabDisplayed() { return getTabChangePassword().isDisplayed() ? true : false;
    }

    public boolean isLogoutTabDisplayed() {
        return getTabLogout().isDisplayed() ? true : false;
    }

}
