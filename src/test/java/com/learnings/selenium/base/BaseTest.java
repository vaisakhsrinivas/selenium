package com.learnings.selenium.base;

import com.learnings.selenium.utils.DriverManager;
import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WaitUtils waitUtils;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverManager.createDriver();
        waitUtils = new WaitUtils(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
