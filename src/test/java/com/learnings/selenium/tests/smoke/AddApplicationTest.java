package com.learnings.selenium.tests.smoke;

import com.learnings.selenium.base.SmokeBaseTest;
import com.learnings.selenium.pages.jobcompass.JobCompassApplicationPage;
import com.learnings.selenium.utils.JsonDataReader;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class AddApplicationTest extends SmokeBaseTest {

    @Test
    public void addApplicationTest() {
        driver.get(JsonDataReader.getString(jobCompassData, "dashboardUrl"));

        JobCompassApplicationPage applicationPage = new JobCompassApplicationPage(driver);
        applicationPage.addApplication();

        Assert.assertTrue(driver.getCurrentUrl().contains("/add"));
        String label = JsonDataReader.getString(jobCompassData, "newapplicationlabel");
        Assert.assertTrue(waitUtils.waitForVisible(By.xpath("//*[contains(text(),'" + label + "')]")).isDisplayed());
    }

    @Test
    public void applicationDetailsAndCountTest() {
        driver.get(JsonDataReader.getString(jobCompassData, "dashboardUrl"));

        JobCompassApplicationPage applicationPage = new JobCompassApplicationPage(driver);
        int initialCount = getTotalApplicationsCount();

        Map<String, Object> applicationData = JsonDataReader.readJsonArray("data/jobcompass_testdata.json").get(1);
        String title = JsonDataReader.getString(applicationData, "jobTitle") + "-" + generateRandomNumber();

        applicationPage.addApplicationWithDetails(
                title,
                JsonDataReader.getString(applicationData, "companyName"),
                JsonDataReader.getString(applicationData, "jobLocation"),
                JsonDataReader.getString(applicationData, "salaryRange"),
                JsonDataReader.getString(applicationData, "jobUrl"),
                JsonDataReader.getString(applicationData, "notes")
        );

        Assert.assertTrue(waitUtils.waitForVisible(By.xpath("//*[contains(text(),'" + title + "')]")).isDisplayed());
        Assert.assertTrue(waitUtils.waitForVisible(
                By.xpath("//*[contains(text(),'" + JsonDataReader.getString(applicationData, "companyName") + "')]")
        ).isDisplayed());

        applicationPage.navigateToDashboard();
        driver.navigate().refresh();
        waitUtils.waitForPageLoad();

        int finalCount = getTotalApplicationsCount();
        Assert.assertEquals(finalCount, initialCount + 1);
    }

    private int getTotalApplicationsCount() {
        String countText = waitUtils.getText(
                By.xpath("//*[contains(text(),'Total Applications')]/following::p[1]")
        );
        return Integer.parseInt(countText.trim());
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 100000);
    }
}
