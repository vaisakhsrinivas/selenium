package com.learnings.selenium.pages.heroku;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class HerokuDropdownPage {

    private static final String DROPDOWN_URL = "https://the-internet.herokuapp.com/dropdown";

    private final WebDriver driver;
    private final WaitUtils waitUtils;
    private final By dropdown = By.id("dropdown");

    public HerokuDropdownPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void gotoDropdownPage() {
        driver.get(DROPDOWN_URL);
        waitUtils.waitForPageLoad();
    }

    public void selectOption(String value) {
        WebElement element = waitUtils.waitForVisible(dropdown);
        new Select(element).selectByValue(value);
    }

    public void verifySelectedValue(String expectedValue) {
        WebElement element = waitUtils.waitForVisible(dropdown);
        Select select = new Select(element);
        Assert.assertEquals(select.getFirstSelectedOption().getDomProperty("value"), expectedValue);
    }
}
