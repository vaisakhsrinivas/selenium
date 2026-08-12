package com.learnings.selenium.pages.swaglabs;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SwagLabsLoginPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.cssSelector("input[type='submit']");
    private final By errorMessageContainer = By.cssSelector(".error-message-container");

    public SwagLabsLoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void gotoUrl(String url) {
        driver.get(url);
        waitUtils.waitForPageLoad();
    }

    public void login(String usernameValue, String passwordValue) {
        waitUtils.fill(username, usernameValue);
        waitUtils.fill(password, passwordValue);
        waitUtils.click(loginButton);
        waitUtils.waitForPageLoad();
        waitUtils.pressEscape();
    }

    public void verifyLoginSuccess() {
        waitUtils.waitForUrlContains("inventory");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    public void verifyLoginError() {
        Assert.assertTrue(waitUtils.waitForVisible(errorMessageContainer).isDisplayed());
    }
}
