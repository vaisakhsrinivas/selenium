package com.learnings.selenium.pages.jobcompass;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JobCompassApplicationPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By addApplicationButton = By.xpath("//button[contains(text(),'Add Application')]");
    private final By companyInput = By.cssSelector("input[placeholder='Google']");
    private final By positionInput = By.cssSelector("input[name='position']");
    private final By locationInput = By.cssSelector("input[name='location']");
    private final By salaryRangeInput = By.cssSelector("input[name='salary_range']");
    private final By jobUrlInput = By.cssSelector("input[name='job_url']");
    private final By notesInput = By.cssSelector("textarea[name='notes']");
    private final By submitButton = By.cssSelector("button[type='submit']");
    private final By searchInput = By.cssSelector("input[placeholder='Search company or position']");

    public JobCompassApplicationPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void addApplication() {
        waitUtils.click(addApplicationButton);
        waitUtils.waitForPageLoad();
    }

    public void addApplicationWithDetails(String jobTitle, String companyName, String jobLocation,
                                          String salaryRange, String jobUrl, String notes) {
        waitUtils.click(addApplicationButton);
        waitUtils.fill(companyInput, companyName);
        waitUtils.fill(positionInput, jobTitle);
        waitUtils.fill(locationInput, jobLocation);
        waitUtils.fill(salaryRangeInput, salaryRange);
        waitUtils.fill(jobUrlInput, jobUrl);
        waitUtils.fill(notesInput, notes);
        waitUtils.click(submitButton);
        waitUtils.fill(searchInput, jobTitle);
        waitUtils.waitForPageLoad();
    }

    public void navigateToDashboard() {
        waitUtils.click(By.xpath("//button[contains(text(),'Dashboard')]"));
        waitUtils.waitForPageLoad();
    }

    public int getTotalApplicationsCount() {
        return Integer.parseInt(waitUtils.getText(
                By.xpath("//p[preceding::text()[contains(.,'Total Applications')]][1]")
        ).trim());
    }
}
