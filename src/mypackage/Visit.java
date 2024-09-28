package mypackage;
/**
 * @author Christian Roa
 */
public class Visit {
    private Appointment appointment;
    private Visit next;

    public Visit() {
        this.appointment = null;
        this.next = null;
    }

    public Visit(Appointment appointment, Visit next) {
        this.appointment = appointment;
        this.next = next;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void setNext(Visit next) {
        this.next = next;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public Visit getNext() {
        return next;
    }

}
