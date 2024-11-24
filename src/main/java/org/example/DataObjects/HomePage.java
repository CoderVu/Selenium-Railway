package org.example.PO;

import org.example.common.constants.Constant;

public class HomePage extends GeneralPage {
    // Locators


    // Elements

    // Methods
    public HomePage open() {
        Constant.WEBDRIVER.get(Constant.RAILWAY_URL);
        return this;
    }



}
