package mypackage;
/**
 * @author Christian Roa
 */
public class Appointment implements Comparable<Appointment> {
    private Date date;
    private Timeslot timeslot;
    private Profile patient;
    private Provider provider;

    public Appointment(Date date, Timeslot timeslot, Profile patient, Provider provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }
    /** date getter */
    public Date getDate() {
        return date;
    }
    /** timeslot getter */
    public Timeslot getTimeslot() {
        return timeslot;
    }
    /** profile getter */
    public Profile getPatient() {
        return patient;
    }
    /** provider getter */
    public Provider getProvider() {
        return provider;
    }
    /** date setter */
    public void setDate(Date date) {
        this.date = date;
    }
    /** timeslot setter */
    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }
    /** profile setter */
    public void setPatient(Profile patient) {
        this.patient = patient;
    }
    /** provider setter */
    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    /** */
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

    /** Override toString method to print to specifications*/
    @Override
    public String toString() {
        return this.date.toString() + " " + this.timeslot + " " + this.patient.toString() + " [" + this.provider.toString() + "]";
    }

    /** */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Appointment) {
            Appointment other = (Appointment) obj;
            return this.date.equals(other.date) && this.timeslot.equals(other.timeslot) && this.patient.equals(other.patient);
        }
        return false;
    }

    public static void main(String[] args) {
        testCompare();
        testEquals();
        testToString();
    }

    private static void testCompare(){
        Date date1 = new Date(1999, 5, 17);
        Date date2 = new Date(date1.getYear(), date1.getMonth(), date1.getDay());
        Profile patient1 = new Profile();
        Profile patient2 = new Profile();

        Appointment app1 = new Appointment(date1, Timeslot.SLOT2, patient1, Provider.slot1);
        Appointment app2 = new Appointment(date2, Timeslot.SLOT2, patient2, Provider.slot2);

        System.out.println("Equality: " + app1.compareTo(app2));
    }
    private static void testEquals(){
        Date date1 = new Date(1999, 5, 17);
        Date date2 = new Date(date1.getYear(), date1.getMonth(), date1.getDay());
        Profile patient1 = new Profile();
        Profile patient2 = new Profile();

        Appointment app1 = new Appointment(date1, Timeslot.SLOT2, patient1, Provider.slot1);
        Appointment app2 = new Appointment(date2, Timeslot.SLOT2, patient2, Provider.slot2);

        System.out.println("Equality: " + app1.compareTo(app2));
    }
    private static void testToString(){
        Date date1 = new Date(1999, 5, 17);
        Profile patient1 = new Profile();
        Appointment app = new Appointment(date1, Timeslot.SLOT2, patient1, Provider.slot1);
        System.out.println(app.toString());
    }
}