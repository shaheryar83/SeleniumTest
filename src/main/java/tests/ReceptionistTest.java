package tests;

import org.testng.annotations.Test;
import pages.AppointmentPage;
import pages.LoginPage;

public class ReceptionistTest extends BaseTest {

    @Test(priority = 1)
    public void testCreateWalkInAppointmentAsReceptionist() throws Exception {

        LoginPage loginPage = new LoginPage(driver);
        AppointmentPage appointmentPage = new AppointmentPage(driver);

        // Step 1: Login with Receptionist credentials
        loginPage.login("automationrecep@yopmail.com", "Karma2253Jazz");
        System.out.println("✅ Logged in successfully as Receptionist!");
        Thread.sleep(2000);

        // Step 2: Navigate → Walk-in Modal
        appointmentPage.openWalkInModalForReceptionist();

        // Step 3: Select patient
        Thread.sleep(2000);
        appointmentPage.selectPatient();
        appointmentPage.selectProvider("Auto doc one spec and service");

        // Step 4: Select speciality
        //appointmentPage.selectSpeciality();

        // Step 5: Select top appointment reason & type
        appointmentPage.selectTopDropdownOption("Select Appointment Reason");
        appointmentPage.selectTopDropdownOption("Select Appointment Type");

        // Step 6: Save the appointment
        appointmentPage.saveAppointment();

        System.out.println("🎯 Walk-in appointment created successfully by Receptionist!");
    }
}