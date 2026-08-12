package com.learnings.selenium.pages.jobcompass;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class JobCompassLoginPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By email = By.cssSelector("input[type='email']");
    private final By password = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");

    public JobCompassLoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void gotoUrl(String url) {
        driver.get(url);
        waitUtils.waitForPageLoad();
    }

    public void login(String emailValue, String passwordValue) {
        waitUtils.fill(email, emailValue);
        waitUtils.fill(password, passwordValue);
        waitUtils.click(loginButton);
        waitUtils.waitForPageLoad();
    }
}
