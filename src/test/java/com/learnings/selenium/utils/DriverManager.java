package com.learnings.selenium.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverManager {

    private DriverManager() {
    }

    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("browser.headless", "true"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1080,600");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1080, 600));
        return driver;
    }
}
