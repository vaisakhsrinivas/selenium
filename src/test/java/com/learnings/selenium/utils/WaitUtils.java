package com.learnings.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this(driver, 10);
    }

    public WaitUtils(WebDriver driver, int timeoutSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForUrlContains(String partialUrl) {
        return wait.until(ExpectedConditions.urlContains(partialUrl));
    }

    public void click(By locator) {
        waitForClickable(locator).click();
    }

    public void fill(By locator, String text) {
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        return waitForVisible(locator).getText();
    }

    public void hover(By locator) {
        WebElement element = waitForVisible(locator);
        new Actions(driver).moveToElement(element).perform();
    }

    public void waitForPageLoad() {
        wait.until(webDriver -> {
            String state = ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState")
                    .toString();
            return "complete".equals(state);
        });
    }

    public List<WebElement> findAll(By locator) {
        waitForVisible(locator);
        return driver.findElements(locator);
    }
}
