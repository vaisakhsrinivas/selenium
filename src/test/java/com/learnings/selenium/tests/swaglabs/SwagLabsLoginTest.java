package com.learnings.selenium.tests.swaglabs;

import com.learnings.selenium.base.BaseTest;
import com.learnings.selenium.pages.swaglabs.SwagLabsLoginPage;
import com.learnings.selenium.utils.JsonDataReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class SwagLabsLoginTest extends BaseTest {

    private Map<String, Object> testData;
    private SwagLabsLoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setUpLoginPage() {
        testData = JsonDataReader.readFirstObject("data/swaglabs_testdata.json");
        loginPage = new SwagLabsLoginPage(driver);
    }

    @Test
    public void validLoginTest() {
        loginPage.gotoUrl(JsonDataReader.getString(testData, "url"));
        loginPage.login(
                JsonDataReader.getString(testData, "username"),
                JsonDataReader.getString(testData, "password")
        );
        loginPage.verifyLoginSuccess();
    }

    @Test
    public void invalidLoginTest() {
        loginPage.gotoUrl(JsonDataReader.getString(testData, "url"));
        loginPage.login(
                JsonDataReader.getString(testData, "username"),
                JsonDataReader.getString(testData, "wrong_password")
        );
        loginPage.verifyLoginError();
    }
}
