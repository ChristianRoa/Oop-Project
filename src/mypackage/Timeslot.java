package mypackage;
/**
 * @author Christian Roa
 */
public enum Timeslot {
    SLOT1 (9,0),
    SLOT2 (10,45),
    SLOT3 (11,15),
    SLOT4 (13,30),
    SLOT5 (15,0),
    SLOT6 (16,15);

    private final int hour;
    private final int minute;

    private Timeslot(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }
}
