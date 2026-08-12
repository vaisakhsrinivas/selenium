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

public class DemoQaMenuPage {

    private static final String MENU_URL = "https://demoqa.com/menu/";

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    public DemoQaMenuPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void gotoMenuPage() {
        driver.get(MENU_URL);
        waitUtils.waitForPageLoad();
    }

    public void hoverMainItem2() {
        waitUtils.hover(By.xpath("//*[contains(text(),'Main Item 2')]"));
    }

    public void hoverSubSubList() {
        waitUtils.hover(By.xpath("//*[contains(text(),'SUB SUB LIST')]"));
    }

    public void clickSubSubItem2() {
        waitUtils.click(By.xpath("//*[contains(text(),'Sub Sub Item 2')]"));
    }
}
