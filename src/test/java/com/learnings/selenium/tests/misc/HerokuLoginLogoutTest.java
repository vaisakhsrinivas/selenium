package com.learnings.selenium.tests.misc;

import com.learnings.selenium.base.BaseTest;
import com.learnings.selenium.pages.adhoc.HerokuLoginPage;
import com.learnings.selenium.pages.adhoc.HerokuLogoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HerokuLoginLogoutTest extends BaseTest {

    private static final String USERNAME = "tomsmith";
    private static final String PASSWORD = "SuperSecretPassword!";

    @Test
    public void validLoginTest() {
        HerokuLoginPage loginPage = new HerokuLoginPage(driver);
        loginPage.gotoLoginPage();
        loginPage.login(USERNAME, PASSWORD);

        String successMessage = loginPage.getFlashMessage();
        System.out.println("Success Message: " + successMessage);
        Assert.assertTrue(successMessage.contains("You logged into a secure area!"));
    }

    @Test
    public void logoutTest() {
        HerokuLoginPage loginPage = new HerokuLoginPage(driver);
        loginPage.gotoLoginPage();
        loginPage.login(USERNAME, PASSWORD);

        HerokuLogoutPage logoutPage = new HerokuLogoutPage(driver);
        logoutPage.logout();

        String logoutMessage = loginPage.getFlashMessage();
        System.out.println("Logout Message: " + logoutMessage);
        Assert.assertTrue(logoutMessage.contains("You logged out of the secure area!"));
    }
}
