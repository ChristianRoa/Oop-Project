package mypackage;
/**
 * This class models an appointment with attributes for date, timeslot, patient, and provider.
 * @author Christian Roa, Renil Khristi
 */
public class Appointment implements Comparable<Appointment> {
    private Date date;
    private Timeslot timeslot;
    private Profile patient;
    private Provider provider;

    /**
     * Constructor to create an Appointment object with the given date, timeslot, patient, and provider.
     *
     * @param date     the date of the appointment
     * @param timeslot the timeslot of the appointment
     * @param patient  the patient associated with the appointment
     * @param provider the provider for the appointment
     */
    public Appointment(Date date, Timeslot timeslot, Profile patient, Provider provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    /**
     * Retrieves the date of the appointment.
     *
     * @return the date of the appointment
     */
    /** date getter */
    public Date getDate() {
        return date;
    }

    /**
     * Retrieves the timeslot of the appointment.
     *
     * @return the timeslot of the appointment
     */
    public Timeslot getTimeslot() {
        return timeslot;
    }

    /**
     * Retrieves the patient profile associated with the appointment.
     *
     * @return the patient profile
     */
    public Profile getPatient() {
        return patient;
    }

    /**
     * Retrieves the provider of the appointment.
     *
     * @return the provider of the appointment
     */
    public Provider getProvider() {
        return provider;
    }

    /**
     * Updates the date of the appointment.
     *
     * @param date the new date of the appointment
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Updates the timeslot of the appointment.
     *
     * @param timeslot the new timeslot of the appointment
     */
    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * Updates the patient profile associated with the appointment.
     *
     * @param patient the new patient profile
     */
    public void setPatient(Profile patient) {
        this.patient = patient;
    }

    /**
     * Updates the provider of the appointment.
     *
     * @param provider the new provider of the appointment
     */
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    /**
     * Compares this appointment to another appointment.
     * The comparison is based on date, timeslot, patient, and provider in that order.
     *
     * @param obj the appointment to be compared
     * @return a negative integer, zero, or a positive integer if this appointment is less than,
     *         equal to, or greater than the specified appointment
     */
    @Override
    public int compareTo(Appointment obj) {
        int dateCompare = this.date.compareTo(obj.date);
        if (dateCompare != 0) {
            return dateCompare;
        }

        int timeslotCompare = this.timeslot.compareTo(obj.timeslot);
        if (timeslotCompare != 0) {
            return timeslotCompare;
        }

        int patientCompare = this.patient.compareTo(obj.patient);
        if (patientCompare != 0) {
            return patientCompare;
        }
        return this.provider.compareTo(obj.provider);
    }

    /**
     * Returns a string representation of the appointment, including date, timeslot, patient, and provider.
     *
     * @return a string representation of the appointment
     */
    @Override
    public String toString() {
        return this.date.toString() + " " + this.timeslot + " " + this.patient.toString() + " [" + this.provider.toString() + "]";
    }

    /**
     * Checks whether this appointment is equal to another object.
     * The appointments are considered equal if they have the same date, timeslot, and patient.
     *
     * @param obj the object to compare to
     * @return true if the appointments are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Appointment) {
            Appointment other = (Appointment) obj;
            return this.date.equals(other.date) && this.timeslot.equals(other.timeslot) && this.patient.equals(other.patient);
        }
        return false;
    }

   /* public static void main(String[] args) {
        testCompare();
        testEquals();
        testToString();
    }

    private static void testCompare(){
        Date date1 = new Date(1999, 5, 17);
        Date date2 = new Date(date1.getYear(), date1.getMonth(), date1.getDay());
        Profile patient1 = new Profile("chris", "roa", date1);
        Profile patient2 = new Profile("renil", "kristi", date2);

        Appointment app1 = new Appointment(date1, Timeslot.SLOT2, patient1, Provider.PATEL);
        Appointment app2 = new Appointment(date2, Timeslot.SLOT2, patient1, Provider.PATEL);

        System.out.println("Equality: " + app1.compareTo(app2));
    }
    private static void testEquals(){
        Date date1 = new Date(1999, 5, 17);
        Date date2 = new Date(date1.getYear(), date1.getMonth(), date1.getDay());
        Profile patient1 = new Profile("chris", "roa", date1);
        Profile patient2 = new Profile("renil", "kristi", date2);

        Appointment app1 = new Appointment(date1, Timeslot.SLOT2, patient1, Provider.PATEL);
        Appointment app2 = new Appointment(date2, Timeslot.SLOT2, patient1, Provider.HARPER);

        System.out.println("Equality: " + app1.equals(app2));
    }

    private static void testToString(){
        Date date1 = new Date(1999, 5, 17);
        Profile patient1 = new Profile("chris", "roa", date1);
        Appointment app = new Appointment(date1, Timeslot.SLOT2, patient1, Provider.PATEL);
        System.out.println(app.toString());

    } */
}