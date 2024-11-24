package org.example.PO;

import org.example.common.constants.Constant;
import org.example.common.util.ScrollClickHandler;

public class TimetablePage {
    // Locators



    // Elements


    // Methods
    public TimetablePage bookTicket(String departFrom, String arriveAt) {
        String xpath = String.format("//tr[td[2]='%s' and td[3]='%s']//a[text()='book ticket']", departFrom, arriveAt);
        ScrollClickHandler ScrollClickHandler = new ScrollClickHandler(Constant.WEBDRIVER);
        ScrollClickHandler.click(xpath);
        return this;
    }
}
