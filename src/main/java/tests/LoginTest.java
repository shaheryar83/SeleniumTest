package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void testLogin() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("fawab786@yopmail.com", "Password@123");

        // Check EHR Dashboard text
        boolean isDashboardVisible = loginPage.isEhrDashboardVisible();
        Assert.assertTrue(isDashboardVisible, "❌ EHR Dashboard not found — Login may have failed!");

        System.out.println("✅ Login successful — 'EHR Dashboard' text is visible!");
    }
}
