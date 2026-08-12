package com.learnings.selenium.pages.adhoc;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HerokuLoginPage {

    private static final String LOGIN_URL = "https://the-internet.herokuapp.com/login";

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");

    public HerokuLoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void gotoLoginPage() {
        driver.get(LOGIN_URL);
        waitUtils.waitForPageLoad();
    }

    public void login(String usernameValue, String passwordValue) {
        waitUtils.fill(username, usernameValue);
        waitUtils.fill(password, passwordValue);
        waitUtils.click(loginButton);
    }

    public String getFlashMessage() {
        return waitUtils.getText(By.id("flash"));
    }
}
