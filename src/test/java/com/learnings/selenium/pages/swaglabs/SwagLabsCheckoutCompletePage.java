package com.learnings.selenium.pages.swaglabs;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SwagLabsCheckoutCompletePage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By checkoutOverview = By.cssSelector(".title");
    private final By checkoutFinish = By.id("finish");
    private final By completeOrder = By.cssSelector(".complete-header");

    public SwagLabsCheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void verifyCheckoutComplete() {
        waitUtils.waitForUrlContains("checkout-step-two");
        Assert.assertEquals(waitUtils.getText(checkoutOverview), "Checkout: Overview");
        waitUtils.jsClick(checkoutFinish);
        waitUtils.waitForUrlContains("checkout-complete");
        Assert.assertEquals(waitUtils.getText(checkoutOverview), "Checkout: Complete!");
        Assert.assertEquals(waitUtils.getText(completeOrder), "Thank you for your order!");
    }
}
