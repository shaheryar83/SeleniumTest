package tests;

import org.testng.annotations.Test;
import pages.LoginPage;
import pages.AppointmentPage;

public class AppointmentTest extends BaseTest {

    @Test(priority = 1)
    public void testCreateWalkInAppointment() throws Exception {

        LoginPage loginPage = new LoginPage(driver);
        AppointmentPage appointmentPage = new AppointmentPage(driver);

        // Step 1: Login
        loginPage.login("usamaauto11@yopmail.com", "Tiger2346Planet");
        System.out.println("✅ Logged in successfully!");
        Thread.sleep(3000);

        // Step 2: Navigate → Walk-in Modal
        appointmentPage.openWalkInModalForDoctor();
        Thread.sleep(2000);
        // Step 3: Select patient
        appointmentPage.selectPatient();

        // Step 4: Select speciality
        appointmentPage.selectSpeciality();

        // Step 5: Select top appointment reason & type
        appointmentPage.selectTopDropdownOption("Select Appointment Reason");
        appointmentPage.selectTopDropdownOption("Select Appointment Type");

        // Step 6: Save the appointment
        appointmentPage.saveAppointment();

        System.out.println("🎯 Walk-in appointment created successfully!");
        driver.quit();
        driver = null;
    }
}