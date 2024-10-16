package utilities;

import mypackage.Doctor;
import mypackage.Location;
import mypackage.Profile;
import mypackage.Specialty;
import mypackage.Provider;
import mypackage.Technician;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class tests the functionality of the custom List class using Provider objects,
 * specifically Doctors and Technicians.
 */
public class ListTest {

    /**
     * Tests adding a Doctor to the List and verifies that the List contains the Doctor.
     */
    @Test
    public void addDoctor() {
        List<Provider> providerList = new List<>();
        Profile p = new Profile("John", "Snow", new Date(1990, 1, 1));
        Doctor doctor = new Doctor(p, Location.BRIDGEWATER, Specialty.ALLERGIST, "45");
        providerList.add(doctor);
        assertTrue(providerList.contains(doctor));
    }

    /**
     * Tests adding a Technician to the List and verifies that the List contains the Technician.
     */
    @Test
    public void addTech() {
        List<Provider> providerList = new List<>();
        Profile p = new Profile("John", "Snow", new Date(1990, 1, 1));
        Technician technician = new Technician(p, Location.BRIDGEWATER, 150);
        providerList.add(technician);
        assertTrue(providerList.contains(technician));
    }

    /**
     * Tests removing a Doctor from the List and verifies that the List no longer contains the Doctor.
     */
    @Test
    public void removeDoctor() {
        List<Provider> providerList = new List<>();
        Profile p = new Profile("John", "Snow", new Date(1990, 1, 1));
        Doctor doctor = new Doctor(p, Location.BRIDGEWATER, Specialty.ALLERGIST, "45");
        providerList.add(doctor);
        providerList.remove(doctor);
        assertFalse(providerList.contains(doctor));
    }

    /**
     * Tests removing a Technician from the List and verifies that the List no longer contains the Technician.
     */
    @Test
    public void removeTech() {
        List<Provider> providerList = new List<>();
        Profile p = new Profile("John", "Snow", new Date(1990, 1, 1));
        Technician technician = new Technician(p, Location.BRIDGEWATER, 150);
        providerList.add(technician);
        providerList.remove(technician);
        assertFalse(providerList.contains(technician));
    }
}
