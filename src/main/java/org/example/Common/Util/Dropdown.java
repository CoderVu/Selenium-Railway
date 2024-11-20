package org.example.Common.Util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class Dropdown {
    public static void selectRandomOption(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);
        List<WebElement> options = select.getOptions();
        if (options.size() > 1) {
            Random random = new Random();
            int randomIndex = random.nextInt(options.size());
            select.selectByIndex(randomIndex);
        } else {
            System.out.println("Dropdown does not have selectable options.");
        }
    }
}
