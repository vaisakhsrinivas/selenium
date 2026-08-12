package com.learnings.selenium.pages.swaglabs;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SwagLabsCartPage {

    private final WaitUtils waitUtils;

    private final By cartItems = By.cssSelector(".cart_item");
    private final By checkoutButton = By.id("checkout");

    public SwagLabsCartPage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver);
    }

    public void verifyCartCount(int expectedCount) {
        Assert.assertEquals(waitUtils.findAll(cartItems).size(), expectedCount);
    }

    public void proceedToCheckout() {
        waitUtils.click(checkoutButton);
        waitUtils.waitForPageLoad();
    }

    public void verifyItemInCart(String productName) {
        By itemLocator = By.xpath(String.format("//div[contains(@class,'cart_item')][contains(.,'%s')]", productName));
        Assert.assertTrue(waitUtils.waitForVisible(itemLocator).isDisplayed());
    }
}
