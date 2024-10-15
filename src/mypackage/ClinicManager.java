package mypackage;
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
    private CircularLinkedList.CircularNode trackingNode = rotationList.getHead();

    public ClinicManager() {
        scanner = new Scanner(System.in);
        appointments = new List<>();
        providers = new List<>();
        officeApps = new List<>();
        imagingApps = new List<>();

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
            Scanner fileScanner = new Scanner(new File("C:\\Users\\croaa\\IdeaProjects\\OOPproject\\src\\providers.txt"));
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
            System.out.println(provider.toString());
        }
    }
    private void fillRotationList(List<Provider> list){
        for (Provider provider : list) {
            if(provider instanceof Technician){
                rotationList.add(provider);
            }
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

        return switch (locationString) {
            case "BRIDGEWATER" -> Location.BRIDGEWATER;
            case "EDISON" -> Location.EDISON;
            case "PISCATAWAY" -> Location.PISCATAWAY;
            case "PRINCETON" -> Location.PRINCETON;
            case "MORRISTOWN" -> Location.MORRISTOWN;
            case "CLARK" -> Location.CLARK;
            default -> null;
        };
    }

    private Specialty parseSpecialty(String specialtyString) {
        if (specialtyString == null || specialtyString.isEmpty()) {
            return null;
        }

        specialtyString = specialtyString.toUpperCase().trim();

        return switch (specialtyString) {
            case "FAMILY" -> Specialty.FAMILY;
            case "PEDIATRICIAN" -> Specialty.PEDIATRICIAN;
            case "ALLERGIST" -> Specialty.ALLERGIST;
            default -> null;
        };
    }

    private void scheduleDocApp(StringTokenizer st) {
        String dateString = st.nextToken();
        String timeslotStr = st.nextToken();
        String firstName = st.nextToken();
        String lastName = st.nextToken();
        String dobString = st.nextToken();
        String doctorNPI = st.nextToken();

        Date appointmentDate = parseDate(dateString);
        Timeslot timeslot = Timeslot.getTimeSlot(Integer.parseInt(timeslotStr));
        Person patient = new Person(new Profile(firstName, lastName, parseDate(dobString)));
        Person provider = null;
        for(Provider p : providers){
            if(p instanceof  Doctor){
                if(((Doctor) p).getNpi().equals(doctorNPI)){
                    provider = p;
                    break;
                }
            }
        }
        if(provider == null){
            System.out.println("Provider not found.");
            return;
        }

        if (timeslot == null) {
            System.out.println(timeslotStr + " is not a valid timeslot.");
            return;
        }

        Appointment appointment = new Appointment(appointmentDate, timeslot, patient, provider);

        // Add the valid appointment to the list
        appointments.add(appointment);
        System.out.println(appointment + " booked.");
    }

    private boolean techAvailabilityChecker(Provider p, Timeslot timeslot) {
        for (Appointment a : appointments) {
            if(a.getProvider() instanceof Technician && a.getProviderAsProvider().equals(p)){
                if(a.getTimeslot().equals(timeslot)){
                    return false;
                }
            }
        }
        return true;
    }

    private void scheduleTechApp(StringTokenizer st) {
        String dateString = st.nextToken();
        String timeslotStr = st.nextToken();
        String firstName = st.nextToken();
        String lastName = st.nextToken();
        String dobString = st.nextToken();
        String imagingService = st.nextToken();

        Date appointmentDate = parseDate(dateString);
        Timeslot timeslot = Timeslot.getTimeSlot(Integer.parseInt(timeslotStr));
        Person patient = new Person(new Profile(firstName, lastName, parseDate(dobString)));

        CircularLinkedList.CircularNode temp = trackingNode;
        trackingNode = trackingNode.getNext();
        while(trackingNode!=temp){
            if(techAvailabilityChecker(trackingNode.getData(), timeslot)){
                Appointment appointment = new Appointment(appointmentDate, timeslot, patient, trackingNode.getData());
                appointments.add(appointment);
                System.out.println(appointment + " booked.");
                return;
            }
            trackingNode = trackingNode.getNext();
        }
    }

    private void cancelAppointment(StringTokenizer st) {
        String dateString = st.nextToken();
        String timeslotStr = st.nextToken();
        String firstName = st.nextToken();
        String lastName = st.nextToken();
        String dobString = st.nextToken();
        Person canceler = new Person(new Profile(firstName, lastName, parseDate(dobString)));

        Appointment temp = new Appointment(parseDate(dateString), Timeslot.getTimeSlot(Integer.parseInt(timeslotStr)), canceler, null);

        for(Appointment a : appointments){
            if(a.getDate().equals(temp.getDate()) && a.getTimeslot().equals(temp.getTimeslot()) && a.getPatient().equals(temp.getPatient())){
                appointments.remove(a);
            }
        }
    }

    private void rescheduleAppointment(StringTokenizer st) {
        String dateString = st.nextToken();
        String timeslotStr = st.nextToken();
        String firstName = st.nextToken();
        String lastName = st.nextToken();
        String dobString = st.nextToken();
        String newTimeslotStr = st.nextToken();

        Appointment comparer = new Appointment(null, null, null, null);
        Appointment temp = new Appointment(parseDate(dateString), Timeslot.getTimeSlot(Integer.parseInt(timeslotStr)), new Person(new Profile(firstName, lastName, parseDate(dobString))), null);
        for(Appointment a : appointments){
            if(a.getDate().equals(temp.getDate()) && a.getTimeslot().equals(temp.getTimeslot()) && a.getPatient().equals(temp.getPatient())){
                comparer = a;
                break;
            }
        }
        if(!appointments.contains(comparer)){
            System.out.println(comparer.toString() + " does not exist.");
        }
        else{
            appointments.remove(comparer);
            Appointment rescheduledApp = new Appointment(comparer.getDate(), Timeslot.getTimeSlot(Integer.parseInt(newTimeslotStr)), comparer.getPatient(), comparer.getProvider());
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
