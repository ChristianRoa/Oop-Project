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

    /** */
    @Override
    public String toString() {}

    /** */
    @Override
    public boolean equals() {

    }
}