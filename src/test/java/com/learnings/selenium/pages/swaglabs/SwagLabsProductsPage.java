package com.learnings.selenium.pages.swaglabs;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class SwagLabsProductsPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By productTitle = By.cssSelector(".title");
    private final By productItems = By.cssSelector(".inventory_item");
    private final By cartButton = By.cssSelector(".shopping_cart_link");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");

    public SwagLabsProductsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void verifyProductsPage() {
        Assert.assertEquals(waitUtils.getText(productTitle), "Products");
    }

    public void verifyProductItems() {
        List<WebElement> items = waitUtils.findAll(productItems);
        Assert.assertEquals(items.size(), driver.findElements(productItems).size());
        Assert.assertFalse(items.isEmpty());
    }

    public void addProductToCart(String productName) {
        By productButton = By.xpath(
                String.format("//div[contains(@class,'inventory_item')][contains(.,'%s')]//button", productName)
        );
        waitUtils.click(productButton);
    }

    public void removeProductFromCart(String productName) {
        addProductToCart(productName);
    }

    public void navigateToCart() {
        waitUtils.click(cartButton);
        waitUtils.waitForPageLoad();
    }

    public void verifyCartBadgeCount(int expectedCount) {
        if (expectedCount > 0) {
            Assert.assertEquals(waitUtils.getText(cartBadge), String.valueOf(expectedCount));
        } else {
            Assert.assertTrue(driver.findElements(cartBadge).isEmpty());
        }
    }
}
