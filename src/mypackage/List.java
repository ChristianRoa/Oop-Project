package mypackage;
/**
 * This class represents a list of appointments.
 * @author Christian Roa, Renil Khristi
 */
public class List {
    private Appointment[] appointments;
    private int size; // number of appointments in the array
    private static final int NOT_FOUND = -1; // A constant used to define something that was not found
    private static final int GROWTH_INCREMENT = 4;

    /**
     * Constructs a new List with an initial capacity of 4 appointments.
     */
    public List() {
        this.appointments = new Appointment[4];
        this.size = 0;
    }
    /*
    Gets the number of appointments in the array
    @return size
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Helper method that searches for a specific appointment in the array.
     *
     * @param appointment The appointment to find.
     * @return The index of the appointment if found; -1 if not found.
     */
    private int find(Appointment appointment){
        for (int i = 0; i < this.size; i++) {
            if(this.appointments[i].equals(appointment)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Helper method to increase the capacity of the appointments array by 4.
     */
    private void grow(){
        Appointment[] newAppointments = new Appointment[this.appointments.length + GROWTH_INCREMENT];
        for (int i = 0; i < this.size; i++) {
            newAppointments[i] = this.appointments[i];
        }
        this.appointments = newAppointments;
    }

    /**
     * Checks if the appointment list contains a specific appointment.
     *
     * @param appointment The appointment to search for.
     * @return true if the appointment is found, false otherwise.
     */
    public boolean contains(Appointment appointment){
        int appointmentCheck = find(appointment);
        return appointmentCheck != NOT_FOUND;
    }

    /**
     * Adds a new appointment to the end of the list. If the array is full, it calls {@code grow()}
     * to increase the capacity before adding the new appointment.
     *
     * @param appointment The appointment to add.
     */

    public void add(Appointment appointment){
       if(this.appointments.length == this.size){
           this.grow();
       }
       this.appointments[this.size++] = appointment;
    }

    /**
     * Removes an appointment from the list if it is found. If the appointment is removed,
     * the elements in the array are shifted to fill the gap.
     *
     * @param appointment The appointment to remove.
     */
    public void remove(Appointment appointment){
        int index = find(appointment);
        if (index == NOT_FOUND) {
            return;
        }
        for (int i = index; i < this.size - 1; i++) {
            appointments[i] = this.appointments[i+1];
        }
        this.appointments[--this.size] = null;
    }

    /**
     * Helper method that sorts the appointment list by patient name in alphabetical order.
     */
    private void sortByPatient(){
        for (int i = 1; i < this.size; i++) {
            Appointment appointment = this.appointments[i];
            int j = i-1;

            while (j >= 0 && this.appointments[j].getPatient().compareTo(appointment.getPatient()) > 0){
                this.appointments[j+1] = this.appointments[j];
                j--;
            }
            appointments[j+1] = appointment;
        }
    }

    /**
     * Helper method that sorts the appointment list by the location of the provider.
     */
    private void sortByLocation(){
        for (int i = 1; i < this.size; i++) {
            Appointment appointment = this.appointments[i];
            int j = i-1;

            while (j >= 0 && this.appointments[j].getProvider().getLocation().compareTo(appointment.getProvider().getLocation()) > 0){
                this.appointments[j+1] = this.appointments[j];
                j--;
            }
            appointments[j+1] = appointment;
        }
    }

    /**
     * Helper method that sorts the appointment list by appointment date and time.
     */
    private void sortByAppointment(){
        for (int i = 1; i < this.size; i++) {
            Appointment appointment = this.appointments[i];
            int j = i-1;

            while (j >= 0 && this.appointments[j].compareTo(appointment) > 0){
                this.appointments[j+1] = this.appointments[j];
                j--;
            }
            appointments[j+1] = appointment;
        }
    }

    /**
     * Prints the appointments ordered by patient profile and date/timeslot.
     */
    public void printByPatient(){
        sortByPatient();
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.appointments[i].getPatient().toString());
        }

    }

    /**
     * Prints the appointments ordered by location (county) and date/timeslot.
     */
    public void printByLocation(){
        sortByLocation();
         for (int i = 0; i < this.size; i++) {
            System.out.println(this.appointments[i].toString());
        }
    }

    /**
     * Prints the appointments ordered by date/timeslot and provider name.
     */
    public void printByAppointment(){
        sortByAppointment();
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.appointments[i].toString());
        }
    }
/*
    public static void main(String[] args) {
        testRemove();
    }
    private static void testContainsAndAdds(){
        List list = new List();
        Date date = new Date(1999, 5, 17);
        Profile patient1 = new Profile("Chris", "roa", date);
        Appointment appointment = new Appointment(date, Timeslot.SLOT2, patient1, Provider.PATEL);
        list.add(appointment);
        System.out.println(list.contains(appointment));
    }
    private static void testRemove(){
        List list = new List();
        Date date = new Date(1999, 5, 17);
        Profile patient1 = new Profile("Chris", "roa", date);
        Appointment appointment = new Appointment(date, Timeslot.SLOT2, patient1, Provider.PATEL);
        list.add(appointment);
        System.out.println(list.contains(appointment));
        list.remove(appointment);
        System.out.println(list.contains(appointment));
    }
    private static void testPrintByPatient(){
        Date date = new Date(1999, 5, 17);
        Profile patient1 = new Profile("Chris", "roa", date);
        Profile patient2 = new Profile("Kanye", "West", date);
        Profile patient3 = new Profile("Jay", "Z", date);

        List list = new List();
        Appointment appointment1 = new Appointment(date, Timeslot.SLOT2, patient1, Provider.ZIMNES);
        Appointment appointment2 = new Appointment(date, Timeslot.SLOT2, patient2, Provider.PATEL);
        Appointment appointment3 = new Appointment(date, Timeslot.SLOT2, patient3, Provider.RAMESH);
        list.add(appointment1);
        list.add(appointment2);
        list.add(appointment3);
        list.printByPatient();
    }
    private static void testPrintByLocation(){
        Date date = new Date(1999, 5, 17);
        Profile patient1 = new Profile("Chris", "roa", date);
        List list = new List();
        Appointment appointment1 = new Appointment(date, Timeslot.SLOT2, patient1, Provider.ZIMNES);
        Appointment appointment2 = new Appointment(date, Timeslot.SLOT2, patient1, Provider.PATEL);
        Appointment appointment3 = new Appointment(date, Timeslot.SLOT2, patient1, Provider.RAMESH);
        list.add(appointment1);
        list.add(appointment2);
        list.add(appointment3);
        list.printByLocation();
    }
    private static void testPrintByAppointment(){
        Date date = new Date(1999, 5, 17);
        Profile patient1 = new Profile("Chris", "roa", date);
        List list = new List();
        Appointment appointment1 = new Appointment(date, Timeslot.SLOT2, patient1, Provider.ZIMNES);
        Appointment appointment2 = new Appointment(date, Timeslot.SLOT1, patient1, Provider.PATEL);
        Appointment appointment3 = new Appointment(date, Timeslot.SLOT3, patient1, Provider.RAMESH);
        list.add(appointment1);
        list.add(appointment2);
        list.add(appointment3);
        list.printByAppointment();
    }
    */
}
