package com.learnings.selenium.pages.freelance;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FreelanceLearnLoginPage {

    private static final String LOGIN_URL = "https://freelance-learn-automation.vercel.app/login";
    private static final String DASHBOARD_URL = "https://freelance-learn-automation.vercel.app/dashboard";

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By email = By.cssSelector("input[name='email1']");
    private final By password = By.cssSelector("input[name='password1']");
    private final By submitButton = By.cssSelector("button[type='submit']");
    private final By errorMessage = By.cssSelector(".errorMessage");

    public FreelanceLearnLoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void gotoLoginPage() {
        driver.get(LOGIN_URL);
        waitUtils.waitForPageLoad();
    }

    public void login(String emailValue, String passwordValue) {
        waitUtils.fill(email, emailValue);
        waitUtils.fill(password, passwordValue);
        waitUtils.click(submitButton);
        waitUtils.waitForPageLoad();
    }

    public void verifyDashboard() {
        waitUtils.waitForUrlContains("dashboard");
        Assert.assertEquals(driver.getCurrentUrl(), DASHBOARD_URL);
    }

    public String getErrorMessage() {
        return waitUtils.getText(errorMessage);
    }
}
