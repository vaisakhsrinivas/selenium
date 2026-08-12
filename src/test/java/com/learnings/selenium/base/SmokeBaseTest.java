package com.learnings.selenium.base;

import com.learnings.selenium.utils.AuthSessionManager;
import com.learnings.selenium.utils.JsonDataReader;
import org.testng.annotations.BeforeMethod;

import java.util.Map;

public abstract class SmokeBaseTest extends BaseTest {

    protected Map<String, Object> jobCompassData;

    @BeforeMethod(alwaysRun = true)
    @Override
    public void setUp() {
        super.setUp();
        jobCompassData = JsonDataReader.readFirstObject("data/jobcompass_testdata.json");
        String dashboardUrl = JsonDataReader.getString(jobCompassData, "dashboardUrl");
        AuthSessionManager.loadCookies(driver, dashboardUrl, AuthSessionManager.DEFAULT_AUTH_FILE);
    }
}
