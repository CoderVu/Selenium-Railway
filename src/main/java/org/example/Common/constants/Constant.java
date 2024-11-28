package org.example.Common.constants;

import org.openqa.selenium.WebDriver;

import java.time.format.DateTimeFormatter;

public class Constant {
    public static WebDriver WEBDRIVER;
    public static final String RAILWAY_URL = "http://saferailway.somee.com/Page/HomePage.cshtml";
    public static final String USERNAMEACTIVE = "vunguyen.170803@grr.la";
    public static final String USERNAMEBLOCK= "vunguyen.17082003@gmail.com";
    public static final String PASSWORD = "12345678";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

}
