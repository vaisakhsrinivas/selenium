package com.learnings.selenium.pages.adhoc;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HerokuLogoutPage {

    private final WaitUtils waitUtils;
    private final By logoutButton = By.xpath("//*[contains(@class,'signout')]");

    public HerokuLogoutPage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver);
    }

    public void logout() {
        waitUtils.click(logoutButton);
    }
}
