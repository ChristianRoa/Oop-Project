package mypackage;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Scheduler {
    private Scanner scanner; // reads user input
    private List appointments; // stores all scheduled appointments

    public Scheduler() {
        scanner = new Scanner(System.in);
        appointments = new List();
    }

    public void run() {
        System.out.println("Scheduler is running.");
        String command;

        while (true) {
            System.out.print(" ");
            command = scanner.nextLine().trim();

            if (command.isEmpty()) {
                continue;
            }

            if (command.equals("Q")) {
                System.out.println("Scheduler terminated");
                break;
            }

            processCommand(command); // Keeps the loop under 40 lines
        }
        scanner.close();
    }

    private void processCommand(String commandLine) {
        StringTokenizer st = new StringTokenizer(commandLine, ",");
        String command = st.nextToken();

        switch (command) {
            case "S":
                scheduleAppointment(st);
                break;
            case "C":
                cancelAppointment(st);
                break;
            case "R":
                rescheduleAppointment(st);
                break;
            case "PA":
                displayAppointmentsSortedByDate();
                break;
            case "PP":
                displayAppointmentsSortedByPatient();
                break;
            case "PL":
                displayAppointmentsSortedByLocation();
                break;
            case "PS":
                displayBillingStatements();
                break;
            case "Q":
                System.out.println("Scheduler terminated");
                System.exit(0); // Exit the program on Q command
                break;
            default:
                System.out.println("Invalid command!");
                // Return early so no further commands are processed
                return;
        }
    }


    private boolean isInvalidAppointment(Appointment a) {
        if(!a.getDate().isValidDate()){
            System.out.println("Appointment date: " + a.getDate().toString() + " is not a valid calendar date.");
            return true;
        } else if (a.getDate().equals(Date.today())) {
            System.out.println("Appointment date: " + a.getDate().toString() + " is today or a date before today");
            return true;
        } else if (a.getDate().isBeforeToday()) {
            System.out.println("Appointment date: " + a.getDate().toString() + " is today or a date before today");
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

    private Date parseDate(String dateString) {
        StringTokenizer tokenizer = new StringTokenizer(dateString, "/");

        int month = Integer.parseInt(tokenizer.nextToken());  // Get month
        int day = Integer.parseInt(tokenizer.nextToken());    // Get day
        int year = Integer.parseInt(tokenizer.nextToken());   // Get year

        return new Date(year, month, day);
    }

    private Timeslot parseTime(String timeString) {
        try {
            int time = Integer.parseInt(timeString); // Check if the input is numeric
            switch (time) {
                case 1: return Timeslot.SLOT1;
                case 2: return Timeslot.SLOT2;
                case 3: return Timeslot.SLOT3;
                case 4: return Timeslot.SLOT4;
                case 5: return Timeslot.SLOT5;
                case 6: return Timeslot.SLOT6;
                default:
                    System.out.println(time + " is not a valid time slot."); // Invalid number
                    return null;
            }
        } catch (NumberFormatException e) {
            System.out.println(timeString + " is not a valid time slot."); // Non-numeric input
            return null;
        }
    }

    private Provider parseProvider(String providerName) {
        providerName = providerName.trim().toUpperCase();
        switch (providerName) {
            case "PATEL": return Provider.PATEL;
            case "LIM": return Provider.LIM;
            case "ZIMNES": return Provider.ZIMNES;
            case "HARPER": return Provider.HARPER;
            case "KAUR": return Provider.KAUR;
            case "TAYLOR": return Provider.TAYLOR;
            case "RAMESH": return Provider.RAMESH;
            case "CERAVOLO": return Provider.CERAVOLO;
            default:
                System.out.println(providerName + " is not a valid provider.");
                return null;
        }
    }


    private void scheduleAppointment(StringTokenizer st) {
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
        if (isInvalidAppointment(a)) {
            return;
        } else {
            appointments.add(a);
            System.out.println(a + " booked.");
        }

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

    private void displayAppointmentsSortedByDate() {
    	if(appointments.getSize()==0) {
    		System.out.println("The schedule calender is empty.");
    	}
    	else {
        System.out.println("** Appointments ordered by county/date/time **");
        appointments.printByAppointment();
    	}
    }

    private void displayAppointmentsSortedByPatient() {
    	if(appointments.getSize()==0) {
    		System.out.println("The schedule calender is empty.");
    	}
    	else {
        System.out.println("** Appointments ordered by county/date/time **");
        appointments.printByPatient();
    	}
    }

    private void displayAppointmentsSortedByLocation() {
    	if(appointments.getSize()==0) {
    		System.out.println("The schedule calender is empty.");
    	}
    	else {
        System.out.println("** Appointments ordered by county/date/time **");
        appointments.printByLocation();
    	}
    }

    private void displayBillingStatements() {

    }


}
