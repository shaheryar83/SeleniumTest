package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup(Method method) {

        // Force WebDriverManager to use correct ChromeDriver for your Chrome version 142
        WebDriverManager.chromedriver().browserVersion("142").setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://qa.techsacare.com/Clinics");
        System.out.println("🌐 Browser launched and URL opened → " + method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {
        if (driver != null) {
            driver.quit();
            System.out.println("🛑 Browser closed ← " + result.getMethod().getMethodName());
            driver = null;
        }
    }
}
