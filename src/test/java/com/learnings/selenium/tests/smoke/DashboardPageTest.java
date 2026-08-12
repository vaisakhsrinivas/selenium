package com.learnings.selenium.tests.smoke;

import com.learnings.selenium.base.SmokeBaseTest;
import com.learnings.selenium.pages.jobcompass.JobCompassDashboardPage;
import com.learnings.selenium.utils.JsonDataReader;
import org.testng.annotations.Test;

public class DashboardPageTest extends SmokeBaseTest {

    @Test
    public void verifyDashboardTitle() {
        driver.get(JsonDataReader.getString(jobCompassData, "dashboardUrl"));
        JobCompassDashboardPage dashboardPage = new JobCompassDashboardPage(driver);
        dashboardPage.verifyDashboardTitle();
    }
}
