package com.learnings.selenium.tests.smoke;

import com.learnings.selenium.base.SmokeBaseTest;
import com.learnings.selenium.pages.jobcompass.JobCompassLogoutPage;
import com.learnings.selenium.utils.JsonDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JobCompassLoginLogoutTest extends SmokeBaseTest {

    @Test
    public void validLoginStateVerification() {
        driver.get(JsonDataReader.getString(jobCompassData, "dashboardUrl"));
        Assert.assertTrue(driver.getCurrentUrl().contains("job-seeker-buddy-40.lovable.app"));
    }

    @Test
    public void logoutTest() {
        driver.get(JsonDataReader.getString(jobCompassData, "dashboardUrl"));

        JobCompassLogoutPage logoutPage = new JobCompassLogoutPage(driver);
        logoutPage.logout();

        Assert.assertTrue(driver.getCurrentUrl().contains("/auth"));
    }
}
