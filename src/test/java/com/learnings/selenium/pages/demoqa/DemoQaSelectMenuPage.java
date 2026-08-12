package com.learnings.selenium.pages.demoqa;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DemoQaSelectMenuPage {

    private static final String SELECT_MENU_URL = "https://demoqa.com/select-menu";

    private final WebDriver driver;
    private final WaitUtils waitUtils;
    private final By carsMultiSelect = By.id("cars");

    public DemoQaSelectMenuPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void gotoSelectMenuPage() {
        driver.get(SELECT_MENU_URL);
        waitUtils.waitForPageLoad();
    }

    public void selectMultipleOptions(String... values) {
        WebElement element = waitUtils.waitForVisible(carsMultiSelect);
        Select select = new Select(element);
        select.deselectAll();
        for (String value : values) {
            select.selectByValue(value);
        }
    }

    public void verifySelectedOptions(String... expectedValues) {
        WebElement element = waitUtils.waitForVisible(carsMultiSelect);
        Select select = new Select(element);
        List<String> selected = select.getAllSelectedOptions().stream()
                .map(option -> option.getDomProperty("value"))
                .collect(Collectors.toList());

        Assert.assertEquals(selected, Arrays.asList(expectedValues));
    }
}
