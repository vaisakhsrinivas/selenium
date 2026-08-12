package com.learnings.selenium.pages.jobcompass;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JobCompassLogoutPage {

    private final WaitUtils waitUtils;
    private final By logoutButton = By.xpath("//button[contains(text(),'Sign Out')]");

    public JobCompassLogoutPage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver);
    }

    public void logout() {
        waitUtils.click(logoutButton);
        waitUtils.waitForPageLoad();
    }
}
