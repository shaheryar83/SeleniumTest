package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class AppointmentPage {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public AppointmentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Locators
    private By appointmentMenu = By.xpath("//span[normalize-space()='Appointments']");
    private By appointmentManagementMenu = By.xpath("//span[contains(@class,'menu-title') and normalize-space()='Appointments Management']");
    private By allAppointments = By.xpath("//span[normalize-space()='All Appointments']");
    private By walkInButton = By.id("WalkInButton");
    private By providerDropdown = By.cssSelector("span.select2-selection[aria-labelledby='select2-ProviderId-container']");
    private By providerOptions = By.xpath("//li[contains(@class,'select2-results__option')]");

    // Actions
    public void openWalkInModalForDoctor() throws InterruptedException {
        navigateToAllAppointments(appointmentMenu);
        openWalkInButton();
    }

    public void openWalkInModalForReceptionist() throws InterruptedException {
        navigateToAllAppointments(appointmentManagementMenu);
        openWalkInButton();
    }

    private void navigateToAllAppointments(By menuLocator) throws InterruptedException {
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(menuLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menu);
        menu.click();
        Thread.sleep(1500);

        WebElement allAppointmentsElement = wait.until(ExpectedConditions.elementToBeClickable(allAppointments));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allAppointmentsElement);
        allAppointmentsElement.click();
        Thread.sleep(2000);
    }

    private void openWalkInButton() throws InterruptedException {
        WebElement walkIn = wait.until(ExpectedConditions.visibilityOfElementLocated(walkInButton));
        wait.until(ExpectedConditions.elementToBeClickable(walkIn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", walkIn);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", walkIn);
        System.out.println("✅ Walk-In Appointment modal opened successfully!");
        Thread.sleep(1500);
    }

    public void selectPatient() throws InterruptedException {
        WebElement dropdown = driver.findElement(
                By.xpath("//span[@class='select2-selection__placeholder' and text()='Select a patient....']")
        );
        dropdown.click();
        Thread.sleep(800);

        List<WebElement> results = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//li[contains(@class,'select2-results__option')]")
                )
        );

        if (!results.isEmpty()) {
            String firstPatient = results.get(0).getText().trim();
            results.get(0).click();
            System.out.println("✅ Selected first patient: " + firstPatient);
        } else {
            System.out.println("⚠️ No patients available in dropdown.");
        }

        Thread.sleep(1500);
    }

    // ✅ Fixed selectSpeciality method
    public void selectSpeciality() {
        // Wait for the dropdown to be clickable
        WebElement specialityDropdown = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.id("select2-appointmentSpeciality-container")
            )
        );
        specialityDropdown.click();

        // Wait for the dropdown options to appear
        List<WebElement> options = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//li[contains(@class,'select2-results__option') and not(contains(@class,'loading'))]")
            )
        );

        // Pick the first option
        if (!options.isEmpty()) {
            String specialityName = options.get(0).getText();
            options.get(0).click();
            System.out.println("✅ Selected first speciality: " + specialityName);
        } else {
            throw new RuntimeException("❌ No speciality options available");
        }
    }

    public void selectTopDropdownOption(String placeholderText) throws InterruptedException {
        WebElement dropdown = driver.findElement(
            By.xpath("//span[@class='select2-selection__placeholder' and text()='" + placeholderText + "']")
        );
        dropdown.click();
        Thread.sleep(1000);

        List<WebElement> options = driver.findElements(By.xpath("//li[contains(@class,'select2-results__option')]"));
        if (!options.isEmpty()) {
            options.get(0).click();
            System.out.println("✅ Selected top option for: " + placeholderText);
        } else {
            System.out.println("⚠️ No options found for: " + placeholderText);
        }
        Thread.sleep(1500);
    }

    public void selectProvider(String providerName) throws InterruptedException {
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(providerDropdown));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);

        List<WebElement> clearButtons = dropdown.findElements(By.cssSelector(".select2-selection__clear"));
        if (!clearButtons.isEmpty()) {
            clearButtons.get(0).click();
            Thread.sleep(500);
        }

        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
        Thread.sleep(800);

        List<WebElement> options = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(providerOptions));

        boolean found = false;
        for (WebElement option : options) {
            String text = option.getText().trim();
            if (text.equalsIgnoreCase(providerName)) {
                option.click();
                System.out.println("✅ Selected provider: " + text);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new RuntimeException("❌ Provider not found: " + providerName);
        }

        Thread.sleep(1000);
    }

    public void saveAppointment() throws InterruptedException {
        driver.findElement(By.id("btnConfirm")).click();
        System.out.println("✅ Clicked 'Save' button.");

        WebElement yesBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(@class,'swal2-confirm') and text()='Yes']")
        ));
        yesBtn.click();
        System.out.println("✅ Clicked 'Yes' in confirmation popup.");
        Thread.sleep(2000);
    }
}
