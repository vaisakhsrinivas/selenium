package com.learnings.selenium.tests.swaglabs;

import com.learnings.selenium.base.BaseTest;
import com.learnings.selenium.pages.swaglabs.*;
import com.learnings.selenium.utils.JsonDataReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class SwagLabsInventoryTest extends BaseTest {

    private Map<String, Object> testData;
    private Map<String, Object> customerData;

    @BeforeMethod(alwaysRun = true)
    public void loginBeforeEachTest() {
        testData = JsonDataReader.readFirstObject("data/swaglabs_testdata.json");
        customerData = JsonDataReader.readFirstObject("data/swaglabs_checkout_testdata.json");

        SwagLabsLoginPage loginPage = new SwagLabsLoginPage(driver);
        loginPage.gotoUrl(JsonDataReader.getString(testData, "url"));
        loginPage.login(
                JsonDataReader.getString(testData, "username"),
                JsonDataReader.getString(testData, "password")
        );
    }

    @Test
    public void verifyProductsPage() {
        SwagLabsProductsPage inventoryPage = new SwagLabsProductsPage(driver);
        inventoryPage.verifyProductsPage();
        inventoryPage.verifyProductItems();
    }

    @Test
    public void addItemAndCompleteCheckout() {
        SwagLabsProductsPage inventoryPage = new SwagLabsProductsPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.verifyCartBadgeCount(1);
        inventoryPage.navigateToCart();

        SwagLabsCartPage cartPage = new SwagLabsCartPage(driver);
        cartPage.verifyCartCount(1);
        cartPage.verifyItemInCart("Sauce Labs Backpack");
        cartPage.proceedToCheckout();

        SwagLabsCheckoutInfoPage checkoutPage = new SwagLabsCheckoutInfoPage(driver);
        checkoutPage.fillCheckoutInformation(
                JsonDataReader.getString(customerData, "firstName"),
                JsonDataReader.getString(customerData, "lastName"),
                JsonDataReader.getString(customerData, "postalCode")
        );
        checkoutPage.continueToOverview();

        SwagLabsCheckoutCompletePage checkoutCompletePage = new SwagLabsCheckoutCompletePage(driver);
        checkoutCompletePage.verifyCheckoutComplete();
    }

    @Test
    public void checkoutWithMissingInformation() {
        SwagLabsProductsPage inventoryPage = new SwagLabsProductsPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Bike Light");
        inventoryPage.verifyCartBadgeCount(1);
        inventoryPage.navigateToCart();

        SwagLabsCartPage cartPage = new SwagLabsCartPage(driver);
        cartPage.proceedToCheckout();

        SwagLabsCheckoutInfoPage checkoutPage = new SwagLabsCheckoutInfoPage(driver);
        checkoutPage.clickContinue();
        checkoutPage.verifyErrorMessage();
    }
}
