package utilities;

import mypackage.Doctor;
import mypackage.Location;
import mypackage.Profile;
import mypackage.Specialty;
import mypackage.Provider;
import mypackage.Technician;
import org.junit.Test;


import static org.junit.Assert.*;

public class ListTest {

    @Test
    public void addDoctor() {
        List<Provider> providerList = new List<>();
        Profile p = new Profile("John", "Snow", new Date(1990, 1, 1));
        Doctor doctor = new Doctor(p, Location.BRIDGEWATER, Specialty.ALLERGIST, "45");
        providerList.add(doctor);
        assertTrue(providerList.contains(doctor));
    }

    @Test
    public void addTech() {
        List<Provider> providerList = new List<>();
        Profile p = new Profile("John", "Snow", new Date(1990, 1, 1));
        Technician technician = new Technician(p, Location.BRIDGEWATER, 150);
        providerList.add(technician);
        assertTrue(providerList.contains(technician));
    }

    @Test
    public void removeDoctor() {
        List<Provider> providerList = new List<>();
        Profile p = new Profile("John", "Snow", new Date(1990, 1, 1));
        Doctor doctor = new Doctor(p, Location.BRIDGEWATER, Specialty.ALLERGIST, "45");
        providerList.add(doctor);
        providerList.remove(doctor);
        assertFalse(providerList.contains(doctor));
    }

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