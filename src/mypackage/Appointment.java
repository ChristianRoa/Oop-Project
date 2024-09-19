package mypackage;
/**
 * @author Christian Roa
 */
public class Appointment implements Comparable<Appointment> {
    private Date date;
    private Timeslot timeslot;
    private Profile patient;
    private Provider provider;

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
}