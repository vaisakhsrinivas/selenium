package com.learnings.selenium.pages.orangehrm;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class OrangeHrmLoginPage {

    private static final String LOGIN_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private static final String DASHBOARD_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By username = By.cssSelector("input[placeholder='Username']");
    private final By password = By.cssSelector("input[placeholder='Password']");
    private final By loginButton = By.xpath("//button[normalize-space()='Login']");
    private final By userMenuButton = By.xpath("//button[contains(@class,'ox-userdropdown-tab')]");
    private final By logoutLink = By.xpath("//a[normalize-space()='Logout']");

    public OrangeHrmLoginPage(WebDriver driver) {
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
        waitUtils.waitForPageLoad();
    }

    public void verifyDashboard() {
        waitUtils.waitForUrlContains("dashboard/index");
        Assert.assertEquals(driver.getCurrentUrl(), DASHBOARD_URL);
    }

    public void logout() {
        waitUtils.click(userMenuButton);
        waitUtils.click(logoutLink);
        waitUtils.waitForPageLoad();
    }

    public void verifyLoginPage() {
        waitUtils.waitForUrlContains("auth/login");
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"));
    }
}
