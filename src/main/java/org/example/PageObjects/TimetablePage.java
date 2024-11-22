package org.example.PageObjects;

import org.example.Common.constants.Constant;
import org.example.Common.util.ClickButton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TimetablePage {
    // Locators



    // Elements


    // Methods
    public TimetablePage bookTicket(String departFrom, String arriveAt) {
        String xpath = String.format("//tr[td[2]='%s' and td[3]='%s']//a[text()='book ticket']", departFrom, arriveAt);
        ClickButton clickButton = new ClickButton(Constant.WEBDRIVER);
        clickButton.click(xpath);
        return this;
    }
}
