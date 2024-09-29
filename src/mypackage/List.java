package mypackage;
/**
 * @author Christian Roa
 */
public class List {
    private Appointment[] appointments;
    private int size; // number of appointments in the array
    private static final int notFound = -1; // A constant used to define something that was not found

    public List() {
        this.appointments = new Appointment[4];
        this.size = 0;
    }

    /** helper method that looks for specific appointment in the appointment array */
    private int find(Appointment appointment){
        for (int i = 0; i < this.size; i++) {
            if(this.appointments[i].equals(appointment)){
                return i;
            }
        }
        return notFound;
    }

    /** helper method to increase the capacity by 4 */
    private void grow(){
        // number capacity is incremented by
        int capacityIncrement = 4;
        Appointment[] newAppointments = new Appointment[this.appointments.length + capacityIncrement];
        for (int i = 0; i < this.size; i++) {
            newAppointments[i] = this.appointments[i];
        }
        this.appointments = newAppointments;
    }

    /** method to check if the appointment list contains a specific appointment*/
    public boolean contains(Appointment appointment){
        int appointmentCheck = find(appointment);
        return appointmentCheck != notFound;
    }

    /** checks if array is full and calls grow to increase capacity as needed. Then adds appointment to the end of the array*/
    public void add(Appointment appointment){
       if(this.appointments.length == this.size){
           this.grow();
       }
       this.appointments[this.size++] = appointment;
    }

    /** if appointment is found removes it from the list */
    public void remove(Appointment appointment){
        int index = find(appointment);
        if (index == notFound) {
            return;
        }
        for (int i = index; i < this.size - 1; i++) {
            appointments[i] = this.appointments[i+1];
        }
        this.appointments[--this.size] = null;
    }


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

    /** ordered by patient profile, date/timeslot */
    public void printByPatient(){
        sortByPatient();
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.appointments[i].getPatient().toString());
        }

    }


    /** ordered by county, date/timeslot */
    public void printByLocation(){
        sortByLocation();
         for (int i = 0; i < this.size; i++) {
            System.out.println(this.appointments[i].toString());
        }
    }

    /** ordered by date/timeslot, provider name */
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
