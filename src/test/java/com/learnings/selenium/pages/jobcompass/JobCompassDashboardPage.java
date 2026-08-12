package com.learnings.selenium.pages.jobcompass;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class JobCompassDashboardPage {

    private final WaitUtils waitUtils;
    private final By dashboardTitle = By.xpath("//h1[contains(text(),'Dashboard')]");

    public JobCompassDashboardPage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver);
    }

    public void verifyDashboardTitle() {
        Assert.assertTrue(waitUtils.waitForVisible(dashboardTitle).isDisplayed());
        Assert.assertEquals(waitUtils.getText(dashboardTitle), "Dashboard");
    }
}
