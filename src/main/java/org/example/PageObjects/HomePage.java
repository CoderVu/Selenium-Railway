package org.example.PageObjects;

import org.example.Common.constants.Constant;

public class HomePage extends GeneralPage {
    // Locators


    // Elements

    // Methods
    public HomePage open() {
        Constant.WEBDRIVER.get(Constant.RAILWAY_URL);
        return this;
    }


}
