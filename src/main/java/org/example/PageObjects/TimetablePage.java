package org.example.PageObjects;

import org.example.Common.constants.Constant;
import org.example.Common.utils.ScrollClickHandler;
import org.example.DataTypes.Station;

public class TimetablePage {
    ScrollClickHandler ScrollClickHandler;
    // Locators


    // Elements


    // Methods
    public TimetablePage checkOutBookTicket(Station departFrom, Station arriveAt) {
        String xpath = String.format("//tr[td[2]='%s' and td[3]='%s']//a[text()='book ticket']", departFrom.getStationName(), arriveAt.getStationName());
        ScrollClickHandler = new ScrollClickHandler(Constant.WEBDRIVER);
        ScrollClickHandler.click(xpath);
        return this;
    }
}
