package org.example.PageObjects;

import org.example.Common.constants.constant;

public class HomePage extends GeneralPage {
    // Locators


    // Elements

    // Methods
    public HomePage open() {
        constant.WEBDRIVER.get(constant.RAILWAY_URL);
        return this;
    }



}
