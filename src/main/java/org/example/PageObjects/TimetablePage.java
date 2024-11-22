package org.example.PageObjects;

import org.example.Common.constants.Constant;
import org.example.Common.util.ClickButtonByScroll;

public class TimetablePage {
    // Locators



    // Elements


    // Methods
    public TimetablePage bookTicket(String departFrom, String arriveAt) {
        String xpath = String.format("//tr[td[2]='%s' and td[3]='%s']//a[text()='book ticket']", departFrom, arriveAt);
        ClickButtonByScroll clickButtonByScroll = new ClickButtonByScroll(Constant.WEBDRIVER);
        clickButtonByScroll.click(xpath);
        return this;
    }
}
