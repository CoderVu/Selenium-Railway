package org.example.PageObjects;

import org.example.Common.Constants.Constant;
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
        return Constant.WEBDRIVER.findElement(tabLogin);
    }
    protected WebElement getTabLogout() {
        return Constant.WEBDRIVER.findElement(tabLogout);
    }
    protected WebElement getLblWelcomeMessage() {
        return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
    }
    protected WebElement getTabRegister() {
        return Constant.WEBDRIVER.findElement(tabRegister);
    }
    protected WebElement getTabTimetable() {
        return Constant.WEBDRIVER.findElement(tabTimetable);
    }
    protected WebElement getTabBookTicket() {
        return Constant.WEBDRIVER.findElement(tabBookTicket);
    }
    protected WebElement getMyTicketTab() {
        return Constant.WEBDRIVER.findElement(myTicketTab);
    }
    protected WebElement getTabChangePassword() {

        return Constant.WEBDRIVER.findElement(tabChangePassword);
    }


    // Methods
    public String getWelcomeMessage() {
        try {
            return getLblWelcomeMessage().getText();
        } catch (Exception e) {
            return "";
        }
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
    public boolean isBookTicketTabDisplayed() {
        try {
            return getTabBookTicket().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMyTicketTabDisplayed() {
        try {
            return getMyTicketTab().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isChangePasswordTabDisplayed() {
        try {
            return getTabChangePassword().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogoutTabDisplayed() {
        try {
            return getTabLogout().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


}
