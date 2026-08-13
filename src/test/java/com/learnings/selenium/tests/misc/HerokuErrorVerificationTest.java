package com.learnings.selenium.tests.misc;

import com.learnings.selenium.base.BaseTest;
import com.learnings.selenium.pages.adhoc.HerokuLoginPage;
import com.learnings.selenium.pages.heroku.HerokuStatusPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HerokuErrorVerificationTest extends BaseTest {

    private static final String VALID_USERNAME = "tomsmith";
    private static final String VALID_PASSWORD = "SuperSecretPassword!";
    private static final String INVALID_USERNAME = "testuser";
    private static final String INVALID_PASSWORD = "testuser!";

    // --- Status code tests (use HerokuStatusPage) ---

    private HerokuStatusPage statusPage;
    private HerokuLoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
            public void setUpPages(){
        statusPage = new HerokuStatusPage(driver);
        loginPage = new HerokuLoginPage(driver);
    }

    @DataProvider(name = "statusCodes")
    public Object[][] statusCodes(){
        return new Object[][]{
                {404,"This page returned a 404 status code"},
                {500, "This page returned a 500 status code"},
                {301, "This page returned a 301 status code"},
                {200, "This page returned a 200 status code"},
                {403, "This page returned a 403 status code"}
        };
    }

    @Test(dataProvider = "statusCodes")
    public void verifyStatusCode(int code, String message){
        statusPage.gotoStatusCodePage(code);
        statusPage.verifyStatusMessageContains(message);
    }

    /*@Test
    public void verify404Error() {
        // TODO: 1. Navigate to status code 404
        statusPage.gotoStatusCodePage(404);
        // TODO: 2. Verify message contains "This page returned a 404 status code"
        String status = "This page returned a 404 status code";
        statusPage.verifyStatusMessageContains(status);
    }

    @Test
    public void verify500Error() {
        // TODO: 1. Navigate to status code 500
        statusPage.gotoStatusCodePage(500);
        // TODO: 2. Verify message contains "This page returned a 500 status code"
        String status = "This page returned a 500 status code";
        statusPage.verifyStatusMessageContains(status);
    }

    @Test
    public void verify301Error() {
        // TODO: 1. Navigate to status code 301
        statusPage.gotoStatusCodePage(301);
        // TODO: 2. Verify message contains "This page returned a 301 status code"
        String status = "This page returned a 301 status code";
        statusPage.verifyStatusMessageContains(status);
    }

    @Test
    public void verify200Status() {
        // TODO: 1. Navigate to status code 200
        statusPage.gotoStatusCodePage(200);
        // TODO: 2. Verify message contains "This page returned a 200 status code"
        String status = "This page returned a 200 status code";
        statusPage.verifyStatusMessageContains(status);
    }

    @Test
    public void verify403Error() {
        // TODO: 1. Navigate to status code 403
        statusPage.gotoStatusCodePage(403);
        // TODO: 2. Verify message contains "403 Forbidden"
        String status = "This page returned a 403 status code";
        statusPage.verifyStatusMessageContains(status);
    }*/

    // --- Login message tests (use HerokuLoginPage) ---

    @Test
    public void verifyValidLogin() {
        // TODO: 1. gotoLoginPage()
        loginPage.gotoLoginPage();
        // TODO: 2. login(VALID_USERNAME, VALID_PASSWORD)
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);
        // TODO: 3. Get flash message, print it, assert it contains "You logged into a secure area!"
        String message = loginPage.getFlashMessage();
        System.out.println("Login Message: " + message);
        Assert.assertTrue(message.contains("You logged into a secure area!"));
    }

    @Test
    public void verifyInvalidLogin() {
        // TODO: 1. gotoLoginPage()
        loginPage.gotoLoginPage();
        // TODO: 2. login("invalidUser", "invalidPassword")
        loginPage.login(INVALID_USERNAME, INVALID_PASSWORD);
        // TODO: 3. Get flash message, print it, assert it contains "Your username is invalid!"
        String message = loginPage.getFlashMessage();
        System.out.println("Error Message: " + message);
        Assert.assertTrue(message.contains("Your username is invalid!"));

    }
}
