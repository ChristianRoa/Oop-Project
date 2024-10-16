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
    private CircularLinkedList.CircularNode trackingNode;
    private List<Patient> medicalRecords;

    public ClinicManager() {
        scanner = new Scanner(System.in);
        appointments = new List<>();
        providers = new List<>();
        officeApps = new List<>();
        imagingApps = new List<>();
        medicalRecords = new List<>();

    }

    public void run() {
        fillProviders();
        Sort.provider(providers);
        printProviders();
        fillRotationList(providers);
        rotationList.sort();
        trackingNode = rotationList.getHead();
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

        try {
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
        } catch (Exception e) {
            // Catch any general exceptions that might occur and print the message
            System.out.println(e.getMessage());
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
    private boolean docAvailabilityChecker(Provider p, Timeslot timeslot){
        for (Appointment a : appointments) {
            if (a.getProvider() instanceof Doctor && a.getProviderAsProvider().equals(p)) {
                if (a.getTimeslot().equals(timeslot)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void scheduleDocApp(StringTokenizer st) {
        boolean correctTokens = true;
        if (st.countTokens() < 6) {
            correctTokens = false;
        }
        String dateString = st.nextToken();
        String timeslotStr = st.nextToken();
        String firstName = st.nextToken();
        String lastName = st.nextToken();
        String dobString = st.nextToken();
        String doctorNPI = st.nextToken();
        Date appointmentDate = parseDate(dateString);
        Person patient = new Person(new Profile(firstName, lastName, parseDate(dobString)));
        Person provider = null;

        for (Provider p : providers) {
            if (p instanceof Doctor) {
                if (((Doctor) p).getNpi().equals(doctorNPI)) {
                    provider = p;
                    break;
                }
            }
        }

        if (!appointmentDate.isValidDate()) {
            throw new IllegalArgumentException("Appointment date: " + appointmentDate.toString() + " is not a valid calendar date.");
        }
        if ((appointmentDate.equals(Date.today())) || appointmentDate.isBeforeToday()) {
            throw new IllegalArgumentException("Appointment date: " + appointmentDate.toString() + " is today or a date before today.");
        }
        if (!appointmentDate.isWeekday()) {
            throw new IllegalArgumentException("Appointment date: " + appointmentDate.toString() + " is Saturday or Sunday.");
        }
        if (!appointmentDate.isWithinSixMonths()) {
            throw new IllegalArgumentException("Appointment date: " + appointmentDate.toString() + " is not within six months.");
        }
        if (Timeslot.getTimeSlot(timeslotStr) == null) {
            throw new IllegalArgumentException(timeslotStr + " is not a valid timeslot.");
        }
        Timeslot timeslot = Timeslot.getTimeSlot(timeslotStr);
        if (!parseDate(dobString).isValidDate()) {
            throw new IllegalArgumentException("Patient dob: " + dobString + " is not a valid calendar date");
        }
        if (!parseDate(dobString).isBeforeToday()) {
            throw new IllegalArgumentException("Patient dob: " + dobString + " is today or a date after today.");
        }
        if (!correctTokens) {
            throw new IllegalArgumentException("Missing data tokens.");
        }
        if (appointments.contains(new Appointment(appointmentDate, timeslot, patient, null))) {
            throw new IllegalArgumentException(patient.getProfile().toString() + " has an existing appointment at the same time slot");
        }
        if (provider == null) {
            throw new IllegalArgumentException(doctorNPI + " - provider doesn't exist;");
        }
        if (!docAvailabilityChecker((Provider) provider, timeslot)) {
            throw new IllegalArgumentException(provider.toString() + " is not available at slot " + timeslotStr);
        }

        Appointment appointment = new Appointment(appointmentDate, timeslot, patient, provider);
        appointments.add(appointment);
        System.out.println(appointment + " booked.");
    }


    private boolean techAvailabilityChecker(Provider p, Timeslot timeslot) {
        for (Appointment a : appointments) {
            if(a.getProvider() instanceof Technician && ((Technician) a.getProvider()).getLocation().equals(p.getLocation())){
                if(a.getTimeslot().equals(timeslot)){
                    return false;
                }
            }
        }
        return true;
    }

    private void scheduleTechApp(StringTokenizer st) {
        boolean correctTokens = true;
        if (st.countTokens() < 6) {
            correctTokens = false;
        }
        String dateString = st.nextToken();
        String timeslotStr = st.nextToken();
        String firstName = st.nextToken();
        String lastName = st.nextToken();
        String dobString = st.nextToken();
        String imagingService = st.nextToken();
        Date appointmentDate = parseDate(dateString);
        Person patient = new Person(new Profile(firstName, lastName, parseDate(dobString)));

        if(!appointmentDate.isValidDate()){
            throw new IllegalArgumentException("Appointment date: " + appointmentDate.toString() + " is not a valid calendar date.");
        }
        if((appointmentDate.equals(Date.today())) || appointmentDate.isBeforeToday()){
            throw new IllegalArgumentException("Appointment date: " + appointmentDate.toString() + " is today or a date before today.");
        }
        if(!appointmentDate.isWeekday()){
            throw new IllegalArgumentException("Appointment date: " + appointmentDate.toString() + " is Saturday or Sunday.");
        }
        if(!appointmentDate.isWithinSixMonths()){
            throw new IllegalArgumentException("Appointment date: " + appointmentDate.toString() + " is not within six months.");
        }
        if(Timeslot.getTimeSlot(timeslotStr) == null){
            throw new IllegalArgumentException(timeslotStr + " is not a valid timeslot.");
        }
        Timeslot timeslot = Timeslot.getTimeSlot(timeslotStr);
        if(!parseDate(dobString).isValidDate()){
            throw new IllegalArgumentException("Patient dob: " + parseDate(dobString).toString() + " is not a valid calendar date");
        }
        if(!parseDate(dobString).isBeforeToday()){
            throw new IllegalArgumentException("Patient dob: " + parseDate(dobString).toString() + " is today or a date after today.");
        }

        if(!correctTokens){
            throw new IllegalArgumentException("Missing data tokens.");
        }
        if(appointments.contains(new Appointment(appointmentDate, timeslot, patient, null))){
            throw new IllegalArgumentException(patient.getProfile().toString() + " has an existing appointment at the same time slot");
        }
        try {
            Radiology.valueOf(imagingService.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(imagingService + " imaging service not provided.");
        }
        CircularLinkedList.CircularNode temp = trackingNode;
        trackingNode = trackingNode.getNext();
        boolean appointmentBooked = false;
        while(trackingNode!=temp){
            if(techAvailabilityChecker(trackingNode.getData(), timeslot)){
                Appointment appointment = new Appointment(appointmentDate, timeslot, patient, trackingNode.getData());
                appointments.add(appointment);
                appointmentBooked = true;
                System.out.println(appointment + " [" + imagingService + "] booked.");
                return;
            }
            trackingNode = trackingNode.getNext();
        }

        if(!appointmentBooked){
            throw new IllegalArgumentException("Cannot find an available technician at all locations for " + imagingService + " at slot " + timeslotStr + ".");
        }
    }

    private void cancelAppointment(StringTokenizer st) {
        String dateString = st.nextToken();
        String timeslotStr = st.nextToken();
        String firstName = st.nextToken();
        String lastName = st.nextToken();
        String dobString = st.nextToken();
        Person canceler = new Person(new Profile(firstName, lastName, parseDate(dobString)));

        Appointment temp = new Appointment(parseDate(dateString), Timeslot.getTimeSlot(timeslotStr), canceler, null);

        for(Appointment a : appointments){
            if(a.getDate().equals(temp.getDate()) && a.getTimeslot().equals(temp.getTimeslot()) && a.getPatient().equals(temp.getPatient())){
                System.out.println(a.toString() + " appointment has been cancelled.");
                appointments.remove(a);
                return;
            }
        }
        System.out.println(temp.toString() + " appointment does not exist.");
    }

    private void rescheduleAppointment(StringTokenizer st) {
        String dateString = st.nextToken();
        String timeslotStr = st.nextToken();
        String firstName = st.nextToken();
        String lastName = st.nextToken();
        String dobString = st.nextToken();
        String newTimeslotStr = st.nextToken();

        Appointment comparer = new Appointment(null, null, null, null);
        Appointment temp = new Appointment(parseDate(dateString), Timeslot.getTimeSlot(timeslotStr), new Person(new Profile(firstName, lastName, parseDate(dobString))), null);
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
            Appointment rescheduledApp = new Appointment(comparer.getDate(), Timeslot.getTimeSlot(newTimeslotStr), comparer.getPatient(), comparer.getProvider());
            appointments.add(rescheduledApp);
            System.out.println("Rescheduled to " + rescheduledApp.toString());
        }
    }

    private void displaySortByApp(){
        if (appointments.isEmpty()) {
            System.out.println("Schedule calendar is empty.");
            return;
        }
        System.out.println();
        System.out.println(" ** List of appointments, ordered by date/time/provider.");
        Sort.appointment(appointments, 'A');
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println(appointments.get(i).toString());
        }
        System.out.println("** end of list **");
    }
 // fix
    private void displaySortByPatient() {
        if (appointments.isEmpty()) {
            System.out.println("Schedule calendar is empty.");
            return;
        }
        System.out.println();
        System.out.println(" ** List of appointments, ordered by Patient/Date/Time.");
        Sort.appointment(appointments, 'P');
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println(appointments.get(i).toString());
        }
        System.out.println("** end of list **");
    }

    private void displaySortByLocation() {
        if (appointments.isEmpty()) {
            System.out.println("Schedule calendar is empty.");
            return;
        }
        System.out.println();
        System.out.println("** List of appointments, ordered by county/date/time **");
        Sort.appointment(appointments, 'L');
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println(appointments.get(i).toString());
        }
        System.out.println("** end of list **");
    }
    private void displaySortBill() {
        if (appointments.isEmpty()) {
            System.out.println("Schedule calendar is empty.");
            return;
        }

        System.out.println();
        System.out.println("** Billing statement ordered by patient **");

        // Sort appointments by patient
        Sort.appointment(appointments, 'P');

        // We use a standard for loop since we will modify appointments
        for (int i = 0; i < appointments.size(); ) { // Use while loop to manage index manually
            Appointment a = appointments.get(i);
            Profile temp = a.getPatient().getProfile();
            Patient existingPatient = null;

            // Check if the patient already exists in medical records
            for (Patient patient : medicalRecords) {
                if (patient.getProfile().equals(temp)) {
                    existingPatient = patient;
                    break;
                }
            }

            // If the patient does not exist, create a new one with an empty visit list
            if (existingPatient == null) {
                existingPatient = new Patient(temp, null); // Start with no visits
                medicalRecords.add(existingPatient);
            }

            // Create a new visit with the current appointment
            Visit newVisit = new Visit(a, null);

            // Add the new visit to the patient's linked list of visits
            if (existingPatient.getVisits() == null) {
                // If there are no visits, set the new visit as the first one
                existingPatient.setVisits(newVisit);
            } else {
                // Otherwise, find the last visit and add the new visit there
                Visit currentVisit = existingPatient.getVisits();
                while (currentVisit.getNext() != null) {
                    currentVisit = currentVisit.getNext();
                }
                currentVisit.setNext(newVisit);
            }

            // Remove the processed appointment from the appointments list
            appointments.remove(a); // Remove the current appointment

            // Do not increment the index; the next appointment will now be at the current index
            // because we removed the current one.
        }

        for (Patient p : medicalRecords) {
            System.out.println(p.toString() + "[due: $" + p.charge() + "]");
        }

        System.out.println("** end of list **");
    }



    private void fillOfficeApps(){
        for(Appointment a : appointments){
            if(!(a instanceof  Imaging)){
                officeApps.add(a);
            }
        }
    }
    private void displayOfficeApps() {
        if (officeApps.isEmpty()) {
            System.out.println("Schedule calendar is empty.");
            return;
        }
        System.out.println();
        System.out.println(" ** List of office appointments, ordered by county/date/time.");
        fillOfficeApps();
        Sort.appointment(officeApps, 'O'); // Sort by office appointments
        for (int i = 0; i < officeApps.size(); i++) {
            System.out.println(officeApps.get(i).toString());
        }
        System.out.println("** end of list **");
    }
    private void fillImageApps(){
        for(Appointment a : imagingApps){
            if(a instanceof  Imaging){
                imagingApps.add(a);
            }
        }
    }
    private void displayImageApps() {
        if (imagingApps.isEmpty()) {
            System.out.println("Schedule calendar is empty.");
            return;
        }
        System.out.println();
        System.out.println("** List of radiology appointments ordered by county/date/time.");
        fillImageApps();
        Sort.appointment(imagingApps, 'I');
        for (int i = 0; i < imagingApps.size(); i++) {
            System.out.println(imagingApps.get(i).toString());
        }
        System.out.println("** end of list **");
    }
    private void displayExpectedCredit(){
        if (providers.isEmpty()) {
            System.out.println("Schedule calendar is empty.");
            return;
        }
        System.out.println();
        System.out.println(" ** Credit amount ordered by provider. **");
        for(Provider p : providers){
            double rate = 0;
            for(Appointment a : appointments){
                if (p.getProfile().compareTo(a.getProvider().getProfile()) == 0 && p.getRate() == a.getProviderAsProvider().getRate()) {
                    rate = rate + p.getRate();
                }
            }
            System.out.println(p.getProfile().toString() + "[credit amount: $" + rate + "]");
        }
        System.out.println("** end of list **");
    }
}
