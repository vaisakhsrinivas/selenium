package com.learnings.selenium.pages.heroku;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HerokuStatusPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;
    private final By statusMessage = By.cssSelector("div.example p");

    public HerokuStatusPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void gotoStatusCodePage(int statusCode) {
        driver.get("https://the-internet.herokuapp.com/status_codes/" + statusCode);
        waitUtils.waitForPageLoad();
    }

    public void verifyStatusMessageContains(String expectedText) {
        Assert.assertTrue(waitUtils.getText(statusMessage).contains(expectedText));
    }
}
