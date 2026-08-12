package com.learnings.selenium.pages.swaglabs;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SwagLabsCheckoutInfoPage {

    private final WaitUtils waitUtils;

    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessageContainer = By.cssSelector(".error-message-container");

    public SwagLabsCheckoutInfoPage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver);
    }

    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        waitUtils.fill(firstNameInput, firstName);
        waitUtils.fill(lastNameInput, lastName);
        waitUtils.fill(postalCodeInput, postalCode);
    }

    public void clickContinue() {
        waitUtils.jsClick(continueButton);
        waitUtils.waitForPageLoad();
    }

    public void continueToOverview() {
        clickContinue();
        waitUtils.waitForUrlContains("checkout-step-two");
    }

    public void verifyErrorMessage() {
        Assert.assertTrue(waitUtils.waitForVisible(errorMessageContainer).isDisplayed());
    }
}
