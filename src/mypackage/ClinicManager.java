package mypackage;

import mypackage.Appointment;
import mypackage.Profile;
import mypackage.Person;
import mypackage.Provider;
import mypackage.Timeslot;
import mypackage.Location;
import utilities.CircularLinkedList;
import utilities.Date;
import utilities.List;
import utilities.Sort;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileNotFoundException;

public class ClinicManager {
    private Scanner scanner; // reads user input
    private List<Appointment> appointments; // stores all scheduled appointments
    private List<Appointment> officeApps;
    private List<Appointment> imagingApps;
    private List<Provider> providers;
    private CircularLinkedList rotationList = new CircularLinkedList();

    public ClinicManager() {
        scanner = new Scanner(System.in);
        appointments = new List<>();
        providers = new List<>();
    }

    public void run() {
        fillProviders();
        Sort.provider(providers);
        printProviders();
        fillRotationList(providers);
        rotationList.sort();
        rotationList.printTechProviders();
        System.out.println("ClinicManager is running...");
        String command;

        while (true) {
            System.out.print("");
            command = scanner.nextLine().trim();

            if (command.isEmpty()) {
                continue;
            }
            if (command.equals("Q")) {
                System.out.println("Scheduler terminated");
                break;
            }

            processCommand(command);
        }
        scanner.close();
    }

    private void processCommand(String commandLine) {
        StringTokenizer st = new StringTokenizer(commandLine, ",");
        String command = st.nextToken();

        switch (command) {
            case "D":
                scheduleDocApp(st);
                break;
            case "T":
                scheduleTechApp(st);
                break;
            case "C":
                cancelAppointment(st);
                break;
            case "R":
                rescheduleAppointment(st);
                break;
            case "PA":
                displaySortByApp();
                break;
            case "PP":
                displaySortByPatient();
                break;
            case "PL":
                displaySortByLocation();
                break;
            case "PS":
                displaySortBill();
                break;
            case "PO":
                displayOfficeApps();
                break;
            case "PI":
                displayImageApps();
                break;
            case "PC":
                displayExpectedCredit();
                break;
            case "Q":
                System.out.println("Clinic Manager terminated.");
                System.exit(0); // Exit the program on Q command
                break;
            default:
                System.out.println("Invalid command!");
        }
    }

    private void fillProviders(){
        try {
            // Using Scanner to read the file
            Scanner fileScanner = new Scanner(new File("providers.txt"));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }

                StringTokenizer st = new StringTokenizer(line);
                String type = st.nextToken(); // Either 'D' or 'T'
                String fname = st.nextToken();
                String lname = st.nextToken();
                String dobStr = st.nextToken();
                Date dob = parseDate(dobStr); // Assume you have a method to parse the date

                String location = st.nextToken();

                if (type.equals("D")) { // For doctors
                    String specialty = st.nextToken();
                    String npi = st.nextToken();
                    Profile profile = new Profile(fname, lname, dob);
                    Doctor doctor = new Doctor(profile, parseLocation(location), parseSpecialty(specialty), npi);
                    providers.add(doctor);

                } else if (type.equals("T")) { // For technicians
                    int rate = Integer.parseInt(st.nextToken());

                    Profile profile = new Profile(fname, lname, dob);
                    Technician technician = new Technician(profile, parseLocation(location), rate); // Assuming Technician class
                    providers.add(technician);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
    private void printProviders(){
        for (Provider provider : providers) {
            if (provider instanceof Doctor) {
                System.out.println(provider.getProfile().toString() + " " + provider.getLocation().toString() + "] [" + ((Doctor) provider).getSpecialty() + ", #" + ((Doctor) provider).getNpi() + "]");
            }
            else if (provider instanceof Technician) {
                System.out.println(provider.getProfile().toString() + " " + provider.getLocation().toString() + "] [rate: $" +(double) provider.getRate() + "]");
            }
            System.out.println();
        }
    }
    private void fillRotationList(List<Provider> list){
        for (Provider provider : list) {
            rotationList.add(provider);
        }
    }

    private boolean isInvalidAppointment(Appointment a) {
        if(!a.getDate().isValidDate()){
            System.out.println("Appointment date: " + a.getDate().toString() + " is not a valid calendar date.");
            return true;
        } else if (a.getDate().equals(Date.today())) {
            System.out.println("Appointment date: " + a.getDate().toString() + " is today or a date before today.");
            return true;
        } else if (a.getDate().isBeforeToday()) {
            System.out.println("Appointment date: " + a.getDate().toString() + " is today or a date before today.");
            return true;
        } else if (!a.getDate().isWeekday()) {
            System.out.println("Appointment date: " + a.getDate().toString() + " is Saturday or Sunday.");
            return true;
        }
        else if (!a.getDate().isWithinSixMonths()){
            System.out.println("Appointment date: " + a.getDate().toString() + " is not within six months.");
            return true;
        }
        return false;
    }

    private boolean isInvalidAppointment(Profile p) {
        if(!p.dob().isValidDate()){
            System.out.println("Appointment date: " + p.dob().toString() + " is not a valid calendar date.");
            return true;
        } else if (!p.dob().isBeforeToday()) {
            System.out.println("Appointment date: " + p.dob().toString() + " is today or a date after today.");
            return true;
        }
        return false;
    }

    private Date parseDate(String dateString) {
        StringTokenizer tokenizer = new StringTokenizer(dateString, "/");

        int month = Integer.parseInt(tokenizer.nextToken());  // Get month
        int day = Integer.parseInt(tokenizer.nextToken());    // Get day
        int year = Integer.parseInt(tokenizer.nextToken());   // Get year

        return new Date(year, month, day);
    }

    private Location parseLocation(String locationString) {
        if (locationString == null || locationString.isEmpty()) {
            return null;
        }

        // Convert to uppercase to handle case-insensitivity
        locationString = locationString.toUpperCase().trim();

        switch (locationString) {
            case "BRIDGEWATER":
                return Location.BRIDGEWATER;
            case "EDISON":
                return Location.EDISON;
            case "PISCATAWAY":
                return Location.PISCATAWAY;
            case "PRINCETON":
                return Location.PRINCETON;
            case "MORRISTOWN":
                return Location.MORRISTOWN;
            case "CLARK":
                return Location.CLARK;
            default:
                return null;
        }
    }

    private Specialty parseSpecialty(String specialtyString) {
        if (specialtyString == null || specialtyString.isEmpty()) {
            return null;
        }

        // Convert to uppercase to handle case-insensitivity
        specialtyString = specialtyString.toUpperCase().trim();

        switch (specialtyString) {
            case "FAMILY":
                return Specialty.FAMILY;
            case "PEDIATRICIAN":
                return Specialty.PEDIATRICIAN;
            case "ALLERGIST":
                return Specialty.ALLERGIST;
            default:
                return null;
        }
    }

    private void scheduleDocApp(StringTokenizer st) {
        String dateString = st.nextToken();
        String timeslotStr = st.nextToken();
        String firstName = st.nextToken();
        String lastName = st.nextToken();
        String dobString = st.nextToken();
        String providerName = st.nextToken();

        Date appointmentDate = parseDate(dateString);
        Timeslot timeslot = Timeslot.getTimeSlot(Integer.parseInt(timeslotStr));
        Person patient = new Person(new Profile(firstName, lastName, parseDate(dobString)));
        Person provider = new Person(new Profile(firstName, lastName, parseDate(dobString)));

        if (timeslot == null) {
            System.out.println(timeslotStr + " is not a valid timeslot.");
            return;
        }
        if (provider == null) {
            System.out.println(providerName + " - provider doesn't exist.");
            return;
        }

        Appointment appointment = new Appointment(appointmentDate, timeslot, patient, provider);

        if (isInvalidAppointment(appointment)) {
            return;
        }
        if(isInvalidAppointment(patient)){
            return;
        }

        // Add the valid appointment to the list
        appointments.add(appointment);
        System.out.println(appointment + " booked.");
    }

    private void scheduleTechApp(StringTokenizer st) {
        String dateString = st.nextToken();
        String timeslotStr = st.nextToken();
        String firstName = st.nextToken();
        String lastName = st.nextToken();
        String dobString = st.nextToken();
        String providerName = st.nextToken();

        Date appointmentDate = parseDate(dateString);
        Timeslot timeslot = parseTime(timeslotStr);
        Profile patient = new Profile(firstName, lastName, parseDate(dobString));
        Provider provider = parseProvider(providerName);

        if (timeslot == null) {
            System.out.println(timeslotStr + " is not a valid timeslot.");
            return;
        }
        if (provider == null) {
            System.out.println(providerName + " - provider doesn't exist.");
            return;
        }

        Appointment appointment = new Appointment(appointmentDate, timeslot, patient, provider);

        if (isInvalidAppointment(appointment)) {
            return;
        }
        if(isInvalidAppointment(patient)){
            return;
        }

        // Add the valid appointment to the list
        appointments.add(appointment);
        System.out.println(appointment + " booked.");
    }

    private void cancelAppointment(StringTokenizer st) {
        String dateString = st.nextToken();
        String timeslotStr = st.nextToken();
        String firstName = st.nextToken();
        String lastName = st.nextToken();
        String dobString = st.nextToken();
        String providerName = st.nextToken();

        Date appointmentDate = parseDate(dateString);
        Profile patient = new Profile(firstName, lastName, parseDate(dobString));

        Appointment a = new Appointment(appointmentDate, parseTime(timeslotStr), patient, parseProvider(providerName));
        if(!appointments.contains(a)){
            System.out.println(a.toString() + " does not exist.");
        }
        appointments.remove(a);
    }

    private void rescheduleAppointment(StringTokenizer st) {
        String dateString = st.nextToken();
        String timeslotStr = st.nextToken();
        String firstName = st.nextToken();
        String lastName = st.nextToken();
        String dobString = st.nextToken();
        String providerName = st.nextToken();

        Date appointmentDate = parseDate(dateString);
        Timeslot timeslot = parseTime(timeslotStr);
        Profile patient = new Profile(firstName, lastName, parseDate(dobString));
        Provider provider = parseProvider(providerName);

        Appointment a = new Appointment(appointmentDate, timeslot, patient, provider);
        if(!appointments.contains(a)){
            System.out.println(a.toString() + " does not exist.");
            return;
        }

        else{
            appointments.remove(a);
        }
    }

    private void displaySortByApp(){
        Sort.appointment(appointments, 'A');
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println(appointments.get(i).toString());
        }
    }
    private void displaySortByPatient() {
        Sort.appointment(appointments, 'P');
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println(appointments.get(i).toString());
        }
    }
    private void displaySortByLocation() {
        Sort.appointment(appointments, 'L');
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println(appointments.get(i).toString());
        }
    }
    private void displaySortBill(){
        Sort.appointment(appointments, 'S');
    }
    private void fillOfficeApps(){
        for(Appointment a : appointments){
            if(!(a instanceof  Imaging)){
                officeApps.add(a);
            }
        }
    }
    private void displayOfficeApps(){
        fillOfficeApps();
        Sort.appointment(officeApps, 'O');
        for (int i = 0; i < officeApps.size(); i++) {
            System.out.println(officeApps.get(i).toString());
        }
    }

    private void fillImageApps(){
        for(Appointment a : imagingApps){
            if(a instanceof  Imaging){
                imagingApps.add(a);
            }
        }
    }
    private void displayImageApps() {
        fillImageApps();
        Sort.appointment(imagingApps, 'I');
        for (int i = 0; i < imagingApps.size(); i++) {
            System.out.println(imagingApps.get(i).toString());
        }
    }
    private void displayExpectedCredit(){
        Sort.appointment(appointments, 'C');
    }

}
