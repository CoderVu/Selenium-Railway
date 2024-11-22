package org.example.PageObjects;

import org.example.Common.constants.constant;
import org.example.Common.util.clickButtonByScroll;

public class TimetablePage {
    // Locators



    // Elements


    // Methods
    public TimetablePage bookTicket(String departFrom, String arriveAt) {
        String xpath = String.format("//tr[td[2]='%s' and td[3]='%s']//a[text()='book ticket']", departFrom, arriveAt);
        clickButtonByScroll clickButtonByScroll = new clickButtonByScroll(constant.WEBDRIVER);
        clickButtonByScroll.click(xpath);
        return this;
    }
}
