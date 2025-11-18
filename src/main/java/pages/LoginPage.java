package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    // Locators
    private By usernameField = By.id("UserNameOrEmail");
    private By passwordField = By.id("passwordInput");
    private By signInButton = By.xpath("//span[text()='Sign in']/parent::button");

    // NEW: EHR Dashboard text locator
    private By ehrDashboardText = By.xpath("//a[text()='EHR Dashboard']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void login(String username, String password) throws InterruptedException {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(signInButton).click();
        Thread.sleep(4000);
    }

    // NEW: Check if EHR Dashboard text is displayed
    public boolean isEhrDashboardVisible() {
        try {
            return driver.findElement(ehrDashboardText).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
