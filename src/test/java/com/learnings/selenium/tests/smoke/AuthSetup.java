package com.learnings.selenium.tests.smoke;

import com.learnings.selenium.pages.jobcompass.JobCompassLoginPage;
import com.learnings.selenium.utils.AuthSessionManager;
import com.learnings.selenium.utils.DriverManager;
import com.learnings.selenium.utils.JsonDataReader;
import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

import java.util.Map;

public class AuthSetup {

    @BeforeSuite(alwaysRun = true)
    public void createSmokeTestSession() {
        Map<String, Object> testData = JsonDataReader.readFirstObject("data/jobcompass_testdata.json");

        WebDriver driver = DriverManager.createDriver();
        try {
            JobCompassLoginPage loginPage = new JobCompassLoginPage(driver);
            loginPage.gotoUrl(JsonDataReader.getString(testData, "url"));
            loginPage.login(
                    JsonDataReader.getString(testData, "email"),
                    JsonDataReader.getString(testData, "password")
            );
            new WaitUtils(driver).waitForPageLoad();
            AuthSessionManager.saveCookies(driver, AuthSessionManager.DEFAULT_AUTH_FILE);
        } finally {
            driver.quit();
        }
    }
}
