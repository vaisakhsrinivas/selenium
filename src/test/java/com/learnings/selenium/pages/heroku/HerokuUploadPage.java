package com.learnings.selenium.pages.heroku;

import com.learnings.selenium.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.nio.file.Path;

public class HerokuUploadPage {

    private static final String UPLOAD_URL = "https://the-internet.herokuapp.com/upload";

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By fileInput = By.id("file-upload");
    private final By submitButton = By.id("file-submit");
    private final By uploadedFiles = By.id("uploaded-files");
    private final By heading = By.tagName("h3");

    public HerokuUploadPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void gotoUploadPage() {
        driver.get(UPLOAD_URL);
        waitUtils.waitForPageLoad();
    }

    public void uploadFile(Path filePath) {
        waitUtils.waitForVisible(fileInput).sendKeys(filePath.toAbsolutePath().toString());
        waitUtils.click(submitButton);
        waitUtils.waitForPageLoad();
    }

    public void verifyUploadSuccess(String expectedFileName) {
        Assert.assertTrue(waitUtils.getText(uploadedFiles).contains(expectedFileName));
        Assert.assertEquals(waitUtils.getText(heading), "File Uploaded!");
    }
}
